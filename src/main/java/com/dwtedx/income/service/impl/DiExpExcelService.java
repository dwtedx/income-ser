package com.dwtedx.income.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Service;

import com.dwtedx.income.dao.IDiExpexcelMapper;
import com.dwtedx.income.dao.IDiIncomeMapper;
import com.dwtedx.income.exception.DiException;
import com.dwtedx.income.model.BaseModel;
import com.dwtedx.income.model.ExpexcelModel;
import com.dwtedx.income.pojo.DiExpexcel;
import com.dwtedx.income.pojo.DiIncome;
import com.dwtedx.income.service.IDiExpExcelService;
import com.dwtedx.income.utility.CommonUtility;
import com.dwtedx.income.utility.ExcelUtil;
import com.dwtedx.income.utility.ICConsants;
import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;

@Service("diExpExcelService")
public class DiExpExcelService implements IDiExpExcelService {
	
	public static final int STATUS_EXPING = 1;
	public static final int STATUS_EXPDONE = 2;
	public static final int STATUS_EXPERR = 3;
	
	@Resource
	private IDiExpexcelMapper diExpexcelMapper;
	@Resource
	private IDiIncomeMapper diIncomeMapper;
	
	private ModelMapper modelMapper;
	
	public DiExpExcelService() {
		modelMapper = new ModelMapper();
	}

	@Override
	public List<ExpexcelModel> findExpexcels(BaseModel model, int userid) {
		List<ExpexcelModel> expexcelModels = new ArrayList<>();
		ExpexcelModel expexcelModel = null;
		List<DiExpexcel> pojos = diExpexcelMapper.selectExpExcels(model.getStart(), model.getLength(), userid);
		for (DiExpexcel diExpexcel : pojos) {
			expexcelModel = modelMapper.map(diExpexcel, ExpexcelModel.class);
			expexcelModel.setRecordtimestart(null != diExpexcel.getRecordtimestart() ? CommonUtility.stringDateFormartyyyyMMdd(diExpexcel.getRecordtimestart()) : "");
			expexcelModel.setRecordtimeend(null != diExpexcel.getRecordtimeend() ? CommonUtility.stringDateFormartyyyyMMdd(diExpexcel.getRecordtimeend()) : "");
			expexcelModel.setCreatetime(CommonUtility.stringDateFormart(diExpexcel.getCreatetime()));
			expexcelModel.setFilepath(ICConsants.ICFILE_HTTP_URL + diExpexcel.getFilepath());
			expexcelModels.add(expexcelModel);
		}
		return expexcelModels;
	}

	@Override
	public ExpexcelModel findExpExcel(int id, int userid) throws DiException {
		DiExpexcel pojo = diExpexcelMapper.selectByPrimaryKey(id);
		ExpexcelModel model = modelMapper.map(pojo, ExpexcelModel.class);
		return model;
	}
	
	@Override
	public ExpexcelModel findLastExpExcel(int userId) {
		DiExpexcel pojo = diExpexcelMapper.selectByLastExp(userId);
		ExpexcelModel model = modelMapper.map(pojo, ExpexcelModel.class);
		return model;
	}

	@Override
	public void deleteExpExcel(int id, int userid) throws DiException {
		DiExpexcel pojo = diExpexcelMapper.selectByPrimaryKey(id);
		if(null == pojo) {
			throw new DiException("数据异常失败，请稍后重试");
		}
		if(userid != pojo.getUserid()) {
			throw new DiException("只能删除自己的话题哦");
		}
		pojo.setDeleteflag(ICConsants.DELETEFALAG_DELETEED);
		int result = diExpexcelMapper.updateByPrimaryKeySelective(pojo);
		if (0 == result) {
			throw new DiException("删除失败，请稍后重试");
		}
	}

	@Override
	public int poolCount(ExpexcelModel model) throws DiException {
		if(null == model || CommonUtility.isEmail(model.getName())) {
			throw new DiException("数据异常，请稍后重试");
		}
		//类型id
		String[] typeId = null;
		String[] accountid = null;
		if(null != model.getTypeid()) {
			typeId = model.getTypeid().split(",");
		}
		if(null != model.getAccountid()) {
			accountid = model.getAccountid().split(",");
		}
		int result = diIncomeMapper.selectByExpExcelsPool(model.getUserid(), model.getRole(), model.getMoneysumstart(), model.getMoneysumend(), typeId, accountid, model.getRecordtimestart(), model.getRecordtimeend());
		return result;
	}
	
	@Override
	public void saveExpExcel(ExpexcelModel model) throws DiException {
		if(null == model || CommonUtility.isEmail(model.getName())) {
			throw new DiException("数据异常，请稍后重试");
		}
		
		//对象转换
		ModelMapper modelMapperProfile = new ModelMapper();
		modelMapperProfile.addMappings(new PropertyMap<ExpexcelModel, DiExpexcel>() {
			protected void configure() {
				// 自己设置对应关系
				// source生成目标类，destination数据来源类，这两个单词可以理解成两个指针，代指类
				map().setRecordtimestart(null);
				map().setRecordtimeend(null);
				map().setUpdatetime(new Date());
				map().setCreatetime(new Date());
			}
		});
		DiExpexcel record = modelMapperProfile.map(model, DiExpexcel.class);
		record.setRecordtimestart(CommonUtility.dateToString(model.getRecordtimestart()));
		record.setRecordtimeend(CommonUtility.dateToString(model.getRecordtimeend()));
		diExpexcelMapper.insertSelective(record);
		
		//开始导出
		model.setId(record.getId());
		ExecutorService executor = Executors.newCachedThreadPool();
		executor.submit(()->{
            WriteExcelThread excelThread = new WriteExcelThread(model);
    		excelThread.run();
        });
	}
	
	public class WriteExcelThread extends Thread {
		
		private ExpexcelModel model;
		
		public WriteExcelThread(ExpexcelModel model) {
			this.model = model;
		}
		
		public void run() {
			DiExpexcel expexcel = diExpexcelMapper.selectByPrimaryKey(model.getId());
			expexcel.setStatus(STATUS_EXPING);
			diExpexcelMapper.updateByPrimaryKeySelective(expexcel);
			//类型id
			String[] typeId = null;
			String[] accountid = null;
			if(null != model.getTypeid()) {
				typeId = model.getTypeid().split(",");
			}
			if(null != model.getAccountid()) {
				accountid = model.getAccountid().split(",");
			}
			List<DiIncome> pojos = diIncomeMapper.selectByExpExcels(model.getUserid(), model.getRole(), model.getMoneysumstart(), model.getMoneysumend(), typeId, accountid, model.getRecordtimestart(), model.getRecordtimeend());
			
			//判断文件是否存在
			String fileserverExpexcelPath = "";
    	    try {
    	    	Properties properties = new Properties();
				properties = PropertiesLoaderUtils.loadAllProperties("filepath.properties");
				fileserverExpexcelPath = properties.getProperty("fileserver_expexcel_path").toString() + "exp_excel_temp_" + model.getUserid() + ".xls";
		        File file = new File(fileserverExpexcelPath);
		        if(file.exists()){
		        	file.delete();
		        }
			} catch (IOException e) {
				e.printStackTrace();
			}
	        
	        //读取迭代List
	        ArrayList<ArrayList<Object>> result = new ArrayList<ArrayList<Object>>();
	        ArrayList<Object> titleobjects = new ArrayList<Object>();
	        titleobjects.add("用户名");
	        titleobjects.add("账单类型");
	        titleobjects.add("金额");
	        titleobjects.add("消费类别");
	        titleobjects.add("钱包/支付方式");
	        titleobjects.add("账单时间");
	        titleobjects.add("记录数据");
	        titleobjects.add("备注");
        	result.add(titleobjects);
	        for (DiIncome income : pojos) {
	        	ArrayList<Object> objects = new ArrayList<Object>();
	        	objects.add(income.getUsername());
	        	objects.add(ICConsants.INCOME_ROLE_INCOME == model.getRole() ? "收入" : "支出");
	        	objects.add(income.getMoneysum());
	        	objects.add(income.getType());
	        	objects.add(income.getAccount());
	        	objects.add(CommonUtility.stringDateFormart(income.getRecordtime()));
	        	objects.add(CommonUtility.stringDateFormart(income.getCreatetime()));
	        	objects.add(income.getRemark());
	        	result.add(objects);
			}
	        boolean expResult = ExcelUtil.writeExcel(result, fileserverExpexcelPath);
	        if(!expResult) {
				expexcel.setStatus(STATUS_EXPERR);
				diExpexcelMapper.updateByPrimaryKeySelective(expexcel);
				return;
	        }
	        //上传服务器
	        //构造一个带指定 Region 对象的配置类
	        Configuration cfg = new Configuration(Region.region2());
	        //...其他参数参考类注释
	        UploadManager uploadManager = new UploadManager(cfg);
	        
	        //默认不指定key的情况下，以文件内容的hash值作为文件名
	        String key = CommonUtility.getTempImageName( model.getUserid(), "xls");	        
	        Auth auth = Auth.create(ICConsants.QINIU_ACCESSKEY, ICConsants.QINIU_SECRETKEY);
			String upToken = auth.uploadToken(ICConsants.BUCKET_ICFILE);
	        try {
	            Response response = uploadManager.put(fileserverExpexcelPath, key, upToken);
	            //解析上传成功的结果
	            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
	            System.out.println(putRet.key);
	            System.out.println(putRet.hash);
	            //数据库修改
	            expexcel.setStatus(STATUS_EXPDONE);
	            expexcel.setFilepath("/" + putRet.key);
				diExpexcelMapper.updateByPrimaryKeySelective(expexcel);
				return;
	        } catch (QiniuException ex) {
	            Response r = ex.response;
	            System.err.println(r.toString());
	            try {
	                System.err.println(r.bodyString());
	            } catch (QiniuException ex2) {
	                //ignore
	            }
	        }
		}
	}

}
