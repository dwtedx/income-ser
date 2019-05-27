package com.dwtedx.income.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dwtedx.income.exception.DiException;
import com.dwtedx.income.model.PassWordModel;
import com.dwtedx.income.model.UserInfoModel;
import com.dwtedx.income.model.common.MessageInfo;
import com.dwtedx.income.model.common.ResultInfo;
import com.dwtedx.income.pojo.DiUserInfo;
import com.dwtedx.income.service.IDiUserInfoService;

@Controller
@RequestMapping("/user")
public class DiUserInfoController {

	@Resource
	private IDiUserInfoService diUserInfoService;

	@ResponseBody
	@RequestMapping(value = "/userbyid", method = RequestMethod.POST)
	public ResultInfo toDiUserInfoById(@RequestBody MessageInfo<UserInfoModel> model) {

		UserInfoModel incomeModel = model.getBody();
		DiUserInfo diUserInfo = this.diUserInfoService.getUserById(incomeModel.getId());

		ResultInfo resultInfo = new ResultInfo();
		resultInfo.setBody(diUserInfo);
		return resultInfo;
	}

	@ResponseBody
	@RequestMapping(value = "/otherlogin", method = RequestMethod.POST)
	public ResultInfo toOtherLogin(@RequestBody MessageInfo<UserInfoModel> model) {

		UserInfoModel modelBody = model.getBody();

		DiUserInfo diUserInfo = this.diUserInfoService.getUserByOtherId(modelBody, model.getHead().getClientSID());

		ResultInfo resultInfo = new ResultInfo();
		resultInfo.setBody(diUserInfo);
		return resultInfo;
	}

	@ResponseBody
	@RequestMapping(value = "/uploadpic", method = RequestMethod.POST)
	public ResultInfo toUploadPic(@RequestBody MessageInfo<UserInfoModel> model) throws DiException {

		UserInfoModel modelBody = model.getBody();
		DiUserInfo userInfo = this.diUserInfoService.uploadPic(modelBody.getId(), modelBody.getHead());
		
		ResultInfo resultInfo = new ResultInfo();
		resultInfo.setBody(userInfo);
		return resultInfo;
	}
	
	@ResponseBody
	@RequestMapping(value = "/updateusername", method = RequestMethod.POST)
	public ResultInfo toUpdateUserName(@RequestBody MessageInfo<UserInfoModel> model) throws DiException {

		UserInfoModel modelBody = model.getBody();
		DiUserInfo userInfo = this.diUserInfoService.updateUserName(model.getHead().getUserId(), modelBody.getUsername());
		
		ResultInfo resultInfo = new ResultInfo();
		resultInfo.setBody(userInfo);
		return resultInfo;
	}
	
	@ResponseBody
	@RequestMapping(value = "/updateuserinfo", method = RequestMethod.POST)
	public ResultInfo toUpdateUserInfo(@RequestBody MessageInfo<UserInfoModel> model) throws DiException {
		
		UserInfoModel modelBody = model.getBody();
		DiUserInfo userInfo = this.diUserInfoService.updateUserInfo(model.getHead().getUserId(), modelBody);
		
		ResultInfo resultInfo = new ResultInfo();
		resultInfo.setBody(userInfo);
		return resultInfo;
	}
	
	@ResponseBody
	@RequestMapping(value = "/updatepassword", method = RequestMethod.POST)
	public ResultInfo toUpdatePassWord(@RequestBody MessageInfo<PassWordModel> model) throws DiException {
		
		PassWordModel modelBody = model.getBody();
		DiUserInfo userInfo = this.diUserInfoService.updatePassWord(model.getHead().getUserId(), modelBody);
		
		ResultInfo resultInfo = new ResultInfo();
		resultInfo.setBody(userInfo);
		return resultInfo;
	}
	
	@ResponseBody
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResultInfo toLogin(@RequestBody MessageInfo<UserInfoModel> model) throws DiException {
		
		UserInfoModel modelBody = model.getBody();
		DiUserInfo userInfo = this.diUserInfoService.login(modelBody);
		
		ResultInfo resultInfo = new ResultInfo();
		resultInfo.setBody(userInfo);
		return resultInfo;
	}
	

	@ResponseBody
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResultInfo toRegister(@RequestBody MessageInfo<UserInfoModel> model) throws DiException {
		
		UserInfoModel modelBody = model.getBody();
		DiUserInfo userInfo = this.diUserInfoService.register(modelBody, model.getHead().getClientSID());
		
		ResultInfo resultInfo = new ResultInfo();
		resultInfo.setBody(userInfo);
		return resultInfo;
	}
	
	@ResponseBody
	@RequestMapping(value = "/registerbyphone", method = RequestMethod.POST)
	public ResultInfo toRegisterByPhone(@RequestBody MessageInfo<UserInfoModel> model) throws DiException {
		
		UserInfoModel modelBody = model.getBody();
		DiUserInfo userInfo = this.diUserInfoService.registerByPhone(modelBody, model.getHead().getClientSID());
		
		ResultInfo resultInfo = new ResultInfo();
		resultInfo.setBody(userInfo);
		return resultInfo;
	}
	
	@ResponseBody
	@RequestMapping(value = "/otherloginv2", method = RequestMethod.POST)
	public ResultInfo toOtherLoginV2(@RequestBody MessageInfo<UserInfoModel> model) {

		UserInfoModel modelBody = model.getBody();

		DiUserInfo diUserInfo = this.diUserInfoService.getUserByOtherIdV2(modelBody, model.getHead().getClientSID());

		ResultInfo resultInfo = new ResultInfo();
		resultInfo.setBody(diUserInfo);
		return resultInfo;
	}
	
	@ResponseBody
	@RequestMapping(value = "/updateuserphone", method = RequestMethod.POST)
	public ResultInfo toUpdateUserPhone(@RequestBody MessageInfo<UserInfoModel> model) throws DiException {

		UserInfoModel modelBody = model.getBody();
		DiUserInfo userInfo = this.diUserInfoService.updateUserPhone(model.getHead().getUserId(), modelBody);
		
		ResultInfo resultInfo = new ResultInfo();
		resultInfo.setBody(userInfo);
		return resultInfo;
	}
	
	@ResponseBody
	@RequestMapping(value = "/resetpassword", method = RequestMethod.POST)
	public ResultInfo toReSetPassword(@RequestBody MessageInfo<UserInfoModel> model) throws DiException {
		
		UserInfoModel modelBody = model.getBody();
		DiUserInfo userInfo = this.diUserInfoService.reSetUserPass(model.getHead().getUserId(), modelBody);
		
		ResultInfo resultInfo = new ResultInfo();
		resultInfo.setBody(userInfo);
		return resultInfo;
	}
	
}
