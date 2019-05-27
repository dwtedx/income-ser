package com.dwtedx.income.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dwtedx.income.dao.IDiAccountMapper;
import com.dwtedx.income.exception.DiException;
import com.dwtedx.income.model.AccountModel;
import com.dwtedx.income.pojo.DiAccount;
import com.dwtedx.income.service.IDiAccountService;
import com.dwtedx.income.utility.CommonUtility;

@Service("diAccountService")
public class DiAccountService implements IDiAccountService {
	
	public static final int DELETEFALAG_NOTDELETE = 0;
	public static final int DELETEFALAG_DELETEED = 1;
	
	@Resource
	private IDiAccountMapper diAccountMapper;
	
	@Override
	public DiAccount getAccountById(int userId) {
		return diAccountMapper.selectByPrimaryKey(userId);
	}

	@Override
	public int saveAccount(AccountModel modelBody) throws DiException {
		if(0 == modelBody.getUserid()){
			throw new DiException("添加账户需要先登录哦");
		}
		if(CommonUtility.isEmpty(modelBody.getIcon())){
			throw new DiException("账户图标不能为空");
		}
		if(CommonUtility.isEmpty(modelBody.getColor())){
			throw new DiException("账户颜色不能为空");
		}
		if(0 == modelBody.getId()) {
			DiAccount account = new DiAccount();
			account.setMoneysum(modelBody.getMoneysum());
			account.setUsername(modelBody.getUsername());
			account.setUserid(modelBody.getUserid());
			account.setName(modelBody.getName());
			account.setType(modelBody.getType());
			account.setIcon(modelBody.getIcon());
			account.setColor(modelBody.getColor());
			account.setSequence(modelBody.getSequence());
			account.setDeletefalag(DELETEFALAG_NOTDELETE);
			account.setRemark(modelBody.getRemark());
			account.setCreatetime(CommonUtility.getCurrentDateTime());
			account.setUpdatetime(CommonUtility.getCurrentDateTime());
			int row = diAccountMapper.insertSelective(account);
			if(row > 0){
				return account.getId();
			}
		}else {
			//修改
			DiAccount account = new DiAccount();
			account.setId(modelBody.getServerid());
			account.setMoneysum(modelBody.getMoneysum());
			account.setUsername(modelBody.getUsername());
			account.setUserid(modelBody.getUserid());
			account.setName(modelBody.getName());
			account.setType(modelBody.getType());
			account.setIcon(modelBody.getIcon());
			account.setColor(modelBody.getColor());
			account.setSequence(modelBody.getSequence());
			account.setDeletefalag(DELETEFALAG_NOTDELETE);
			account.setRemark(modelBody.getRemark());
			account.setUpdatetime(CommonUtility.getCurrentDateTime());
			int row = diAccountMapper.updateByPrimaryKeySelective(account);
			if(row > 0){
				return modelBody.getServerid();
			}
		}
		return 0;
	}

	@Override
	public void deleteAccount(int id) {
		DiAccount account = diAccountMapper.selectByPrimaryKey(id);
		account.setDeletefalag(DELETEFALAG_DELETEED);
		diAccountMapper.updateByPrimaryKey(account);
	}

	@Override
	public List<DiAccount> getAccountByuserId(int id) {
		return diAccountMapper.selectAccountByUserId(id);
	}

}
