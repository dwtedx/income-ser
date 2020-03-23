package com.dwtedx.income.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.dwtedx.income.dao.IDiUserinviteinfoMapper;
import com.dwtedx.income.exception.DiException;
import com.dwtedx.income.model.UserinviteModel;
import com.dwtedx.income.pojo.DiUserinviteinfo;
import com.dwtedx.income.service.IDiUserInviteService;

@Service("diUserInviteService")
public class DiUserInviteService implements IDiUserInviteService {

	@Resource
	private IDiUserinviteinfoMapper diUserinviteinfoMapper;
	
	private ModelMapper modelMapper;
	
	public DiUserInviteService() {
		modelMapper = new ModelMapper();
	}

	
	@Override
	public void saveInvite(UserinviteModel model) throws DiException {
		DiUserinviteinfo userinviteinfo = modelMapper.map(model, DiUserinviteinfo.class);
		if(userinviteinfo.getId() > 0) {
			int id = diUserinviteinfoMapper.updateByPrimaryKeySelective(userinviteinfo);
			if(id == 0) {
				throw new DiException("邀请信息保存异常");
			}
		}else {
			int id = diUserinviteinfoMapper.insertSelective(userinviteinfo);
			if(id == 0) {
				throw new DiException("邀请信息保存异常");
			}
		}
	}

	@Override
	public List<UserinviteModel> getUserInviteByUserId(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

}
