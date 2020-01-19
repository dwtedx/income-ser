package com.dwtedx.income.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
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

@Service("diExpExcelService")
public class DiExpExcelService implements IDiExpExcelService {
	
	public static final int STATUS_EXPING = 1;
	public static final int STATUS_EXPDONE = 2;
	
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
		List<DiExpexcel> pojos = diExpexcelMapper.selectExpExcels(model.getStart(), model.getLength(), userid);
		List<ExpexcelModel> expexcelModels = modelMapper.map(pojos, new TypeToken<List<ExpexcelModel>>() {}.getType());
		return expexcelModels;
	}

	@Override
	public ExpexcelModel findExpExcel(int id, int userid) throws DiException {
		DiExpexcel pojo = diExpexcelMapper.selectByPrimaryKey(id);
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
	        ExcelUtil.writeExcel(result, fileserverExpexcelPath);
		}
	}

}
