package com.dwtedx.income.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import com.dwtedx.income.dao.IDiExpexcelMapper;
import com.dwtedx.income.dao.IDiIncomeMapper;
import com.dwtedx.income.exception.DiException;
import com.dwtedx.income.model.BaseModel;
import com.dwtedx.income.model.ExpexcelModel;
import com.dwtedx.income.pojo.DiExpexcel;
import com.dwtedx.income.pojo.DiIncome;
import com.dwtedx.income.service.IDiExpExcelService;
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
			throw new DiException("分享失败，请稍后重试");
		}
	}

	@Override
	public int poolCount(ExpexcelModel model) throws DiException {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public void saveExpExcel(ExpexcelModel model) throws DiException {
		//类型id
		String[] typeId = model.getTypeid().split(",");
		String[] accountid = model.getAccountid().split(",");
		List<DiIncome> pojos = diIncomeMapper.selectByExpExcels(model.getUserid(), model.getRole(), model.getMoneysumstart(), model.getMoneysumend(), typeId, accountid, model.getRecordtimestart(), model.getRecordtimeend());
		
		System.out.println("共找到数据：" + pojos.size());
	}

}
