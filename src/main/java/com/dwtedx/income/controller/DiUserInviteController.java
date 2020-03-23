package com.dwtedx.income.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dwtedx.income.exception.DiException;
import com.dwtedx.income.model.BaseModel;
import com.dwtedx.income.model.UserinviteModel;
import com.dwtedx.income.model.common.MessageInfo;
import com.dwtedx.income.model.common.ResultInfo;
import com.dwtedx.income.service.IDiUserInviteService;

@Controller
@RequestMapping("/vipinvite")
public class DiUserInviteController {

	@Resource
	private IDiUserInviteService diUserInviteService;


	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ResultInfo toSave(@RequestBody MessageInfo<UserinviteModel> model) throws DiException {

		diUserInviteService.saveInvite(model.getBody());
		ResultInfo resultInfo = new ResultInfo();
		return resultInfo;
	}

	@ResponseBody
	@RequestMapping(value = "/find", method = RequestMethod.POST)
	public ResultInfo toFindInvite(@RequestBody MessageInfo<BaseModel> model) throws DiException {

		List<UserinviteModel> list = diUserInviteService.getUserInviteByUserId(model.getBody().getId());
		ResultInfo resultInfo = new ResultInfo();
		resultInfo.setBody(list);
		return resultInfo;

	}

}
