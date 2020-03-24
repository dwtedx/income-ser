package com.dwtedx.income.service;

import java.util.List;

import com.dwtedx.income.exception.DiException;
import com.dwtedx.income.model.UserinviteModel;

public interface IDiUserInviteService {

	public void saveInvite(UserinviteModel model) throws DiException;

	public List<UserinviteModel> getUserInviteByUserId(int userId, int status);

}
