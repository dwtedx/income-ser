package com.dwtedx.income.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dwtedx.income.dao.IDiTypeMapper;
import com.dwtedx.income.exception.DiException;
import com.dwtedx.income.model.TypeModel;
import com.dwtedx.income.pojo.DiType;
import com.dwtedx.income.service.IDiTypeService;
import com.dwtedx.income.utility.CommonUtility;
import com.dwtedx.income.utility.ICConsants;

@Service("diTypeService")
public class DiTypeService implements IDiTypeService {
	
	@Resource
	private IDiTypeMapper diTypeMapper;
	
	@Override
	public DiType getTypeById(int userId) {
		return diTypeMapper.selectByPrimaryKey(userId);
	}

	@Override
	public int saveType(TypeModel modelBody) throws DiException {
		if(0 == modelBody.getUserid()){
			throw new DiException("添加类别需要先登录哦");
		}
		if(CommonUtility.isEmpty(modelBody.getIcon())){
			throw new DiException("类别图标不能为空");
		}
		if(CommonUtility.isEmpty(modelBody.getColor())){
			throw new DiException("类别颜色不能为空");
		}
		DiType type = new DiType();
		type.setUsername(modelBody.getUsername());
		type.setUserid(modelBody.getUserid());
		type.setName(modelBody.getName());
		type.setType(modelBody.getType());
		type.setIcon(modelBody.getIcon());
		type.setColor(modelBody.getColor());
		type.setSequence(modelBody.getSequence());
		type.setDeletefalag(ICConsants.DELETEFALAG_NOTDELETE);
		type.setRemark(modelBody.getRemark());
		type.setCreatetime(CommonUtility.getCurrentDateTime());
		type.setUpdatetime(CommonUtility.getCurrentDateTime());
		int row = diTypeMapper.insertSelective(type);
		if(row > 0){
			return type.getId();
		}
		return 0;
	}

	@Override
	public void updateType(TypeModel modelBody) throws DiException {
		if(CommonUtility.isEmpty(modelBody.getIcon())){
			throw new DiException("类别图标不能为空");
		}
		if(CommonUtility.isEmpty(modelBody.getColor())){
			throw new DiException("类别颜色不能为空");
		}
		DiType diType = diTypeMapper.selectByPrimaryKey(modelBody.getServerid());
		diType.setName(modelBody.getName());
		diType.setIcon(modelBody.getIcon());
		diType.setColor(modelBody.getColor());
		diType.setRemark(modelBody.getRemark());
		diType.setUpdatetime(CommonUtility.getCurrentDateTime());
		diTypeMapper.updateByPrimaryKey(diType);
	}

	@Override
	public void deleteType(int id) {
		DiType type = diTypeMapper.selectByPrimaryKey(id);
		type.setDeletefalag(ICConsants.DELETEFALAG_DELETEED);
		diTypeMapper.updateByPrimaryKey(type);
	}

	@Override
	public List<DiType> getTypeByuserId(int id) {
		return diTypeMapper.selectTypeByUserId(id);
	}

}
