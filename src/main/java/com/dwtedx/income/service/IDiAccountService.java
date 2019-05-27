package com.dwtedx.income.service;

import java.util.List;

import com.dwtedx.income.exception.DiException;
import com.dwtedx.income.model.AccountModel;
import com.dwtedx.income.pojo.DiAccount;

public interface IDiAccountService {
	
	public DiAccount getAccountById(int userId);
	
	public int saveAccount(AccountModel modelBody) throws DiException;

	public void deleteAccount(int id);

	public List<DiAccount> getAccountByuserId(int id);
	
}
