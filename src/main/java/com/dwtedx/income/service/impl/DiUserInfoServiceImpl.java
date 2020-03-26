package com.dwtedx.income.service.impl;

import java.util.Calendar;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dwtedx.income.dao.IDiSendSmsMapper;
import com.dwtedx.income.dao.IDiUserInfoMapper;
import com.dwtedx.income.dao.IDiUserinviteinfoMapper;
import com.dwtedx.income.dao.IDiUservipinfoMapper;
import com.dwtedx.income.exception.DiException;
import com.dwtedx.income.model.PassWordModel;
import com.dwtedx.income.model.UserInfoModel;
import com.dwtedx.income.pojo.DiSendSms;
import com.dwtedx.income.pojo.DiUserInfo;
import com.dwtedx.income.pojo.DiUserinviteinfo;
import com.dwtedx.income.pojo.DiUservipinfo;
import com.dwtedx.income.service.IDiUserInfoService;
import com.dwtedx.income.utility.Base64ImageUtility;
import com.dwtedx.income.utility.CommonUtility;
import com.dwtedx.income.utility.ICConsants;
import com.dwtedx.income.utility.MD5Util;
import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;

@Service("diUserInfoService")
public class DiUserInfoServiceImpl implements IDiUserInfoService {

	@Resource
	private IDiUserInfoMapper diUserInfoMapper;
	@Resource
	private IDiSendSmsMapper diSendSmsMapper;
	@Resource
	private IDiUserinviteinfoMapper diUserinviteinfoMapper;
	@Resource
	private IDiUservipinfoMapper diUservipinfoMapper;

	@Override
	public DiUserInfo getUserById(int userId) {
		DiUserInfo userInfo = this.diUserInfoMapper.selectByPrimaryKey(userId);
		//2020-03-26检查VIP过期
		if (null != userInfo && userInfo.getVipflag() == ICConsants.VIP_TYPE_VIP) {
            if(-1 == CommonUtility.compareDate(userInfo.getVipendtime(), new Date())) {
            	System.out.println("过期...");
            }
        }
		return userInfo;
	}

	@Override
	public DiUserInfo getUserByOtherId(UserInfoModel userInfoModel, String clientsid) {
		DiUserInfo diUserInfo = null;
		switch (userInfoModel.getOthertype()) {
		case ICConsants.OTHER_LOGIN_QQ:
			diUserInfo = this.diUserInfoMapper.selectByQQOpenId(userInfoModel.getQqopenid());
			if(null == diUserInfo){
				DiUserInfo record = new DiUserInfo();
				record.setName(userInfoModel.getName());
				record.setHead(userInfoModel.getHead());
				record.setSex(userInfoModel.getSex());
				record.setQqopenid(userInfoModel.getQqopenid());
				record.setClientsid(clientsid);
				record.setUpdatetime(CommonUtility.getCurrentDateTime());
				record.setCreatetime(CommonUtility.getCurrentDateTime());
				int number = this.diUserInfoMapper.insertSelective(record);
				if(number > 0){
					diUserInfo = this.diUserInfoMapper.selectByQQOpenId(userInfoModel.getQqopenid());
				}
			}else{
				DiUserInfo record = new DiUserInfo();
				record.setId(diUserInfo.getId());
				record.setName(userInfoModel.getName());
				record.setHead(userInfoModel.getHead());
				record.setSex(userInfoModel.getSex());
				record.setUpdatetime(CommonUtility.getCurrentDateTime());
				this.diUserInfoMapper.updateByPrimaryKeySelective(record);
			}
			break;
		case ICConsants.OTHER_LOGIN_SINA:
			diUserInfo = this.diUserInfoMapper.selectBySinaOpenId(userInfoModel.getSinaopenid());
			if(null == diUserInfo){
				DiUserInfo record = new DiUserInfo();
				record.setName(userInfoModel.getName());
				record.setHead(userInfoModel.getHead());
				record.setSex(userInfoModel.getSex());
				record.setSinaopenid(userInfoModel.getSinaopenid());
				record.setClientsid(clientsid);
				record.setUpdatetime(CommonUtility.getCurrentDateTime());
				record.setCreatetime(CommonUtility.getCurrentDateTime());
				int number = this.diUserInfoMapper.insertSelective(record);
				if(number > 0){
					diUserInfo = this.diUserInfoMapper.selectBySinaOpenId(userInfoModel.getSinaopenid());
				}
			}else{
				DiUserInfo record = new DiUserInfo();
				record.setId(diUserInfo.getId());
				record.setName(userInfoModel.getName());
				record.setHead(userInfoModel.getHead());
				record.setSex(userInfoModel.getSex());
				record.setUpdatetime(CommonUtility.getCurrentDateTime());
				this.diUserInfoMapper.updateByPrimaryKeySelective(record);
			}
			break;
		case ICConsants.OTHER_LOGIN_WEIXIN:
			diUserInfo = this.diUserInfoMapper.selectByWeixinOpenId(userInfoModel.getWeixinopenid());
			if(null == diUserInfo){
				DiUserInfo record = new DiUserInfo();
				record.setName(userInfoModel.getName());
				record.setHead(userInfoModel.getHead());
				record.setSex(userInfoModel.getSex());
				record.setWeixinopenid(userInfoModel.getWeixinopenid());
				record.setClientsid(clientsid);
				record.setUpdatetime(CommonUtility.getCurrentDateTime());
				record.setCreatetime(CommonUtility.getCurrentDateTime());
				int number = this.diUserInfoMapper.insertSelective(record);
				if(number > 0){
					diUserInfo = this.diUserInfoMapper.selectByWeixinOpenId(userInfoModel.getWeixinopenid());
				}
			}else{
				DiUserInfo record = new DiUserInfo();
				record.setId(diUserInfo.getId());
				record.setName(userInfoModel.getName());
				record.setHead(userInfoModel.getHead());
				record.setSex(userInfoModel.getSex());
				record.setUpdatetime(CommonUtility.getCurrentDateTime());
				this.diUserInfoMapper.updateByPrimaryKeySelective(record);
			}
			break;
			
		}
		return diUserInfo;
	}

	@Override
	public DiUserInfo uploadPic(int userId, String imgData) throws DiException {
		byte[] uploadBytes = Base64ImageUtility.generateImage(imgData);
		if (null == imgData || null == uploadBytes) {
			throw new DiException("图片数据异常");
		}

		// 构造一个带指定Zone对象的配置类
		Configuration cfg = new Configuration(Region.region2());
		// ...其他参数参考类注释
		UploadManager uploadManager = new UploadManager(cfg);
		// ...生成上传凭证，然后准备上传
		
		// 默认不指定key的情况下，以文件内容的hash值作为文件名
		String key = CommonUtility.getTempImageName(userId);

		Auth auth = Auth.create(ICConsants.QINIU_ACCESSKEY, ICConsants.QINIU_SECRETKEY);
		String upToken = auth.uploadToken(ICConsants.BUCKET_ICHEAD);
		
		DefaultPutRet putRet = null;
		try {
			Response response = uploadManager.put(uploadBytes, key, upToken);
			// 解析上传成功的结果
			putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
			System.out.println(putRet.key);
			System.out.println(putRet.hash);
		} catch (QiniuException ex) {
			Response r = ex.response;
			System.err.println(r.toString());
			throw new DiException(r.toString());
		}
		
		//数据库修改
		DiUserInfo diUserInfo = this.diUserInfoMapper.selectByPrimaryKey(userId);
		diUserInfo.setHead(ICConsants.ICHEAD_HTTP_URL + putRet.key);
		this.diUserInfoMapper.updateByPrimaryKey(diUserInfo);
		
		return diUserInfo;
	}

	@Override
	public DiUserInfo updateUserName(int userId, String userName) throws DiException {
		if(CommonUtility.isEmpty(userName)){
			throw new DiException("用户名不能为空");
		}
		
		if(!CommonUtility.isNumericOrABC(userName)){
			throw new DiException("用户名只是为字母和数字");
		}
		//检测用户名
		DiUserInfo selectUserInfo = this.diUserInfoMapper.selectByUserName(userName);
		if(null != selectUserInfo){
			throw new DiException("用户名重复啦");
		}
		
		//数据库修改
		DiUserInfo diUserInfo = this.diUserInfoMapper.selectByPrimaryKey(userId);
		diUserInfo.setUsername(userName);
		this.diUserInfoMapper.updateByPrimaryKeySelective(diUserInfo);
		return diUserInfo;
	}

	@Override
	public DiUserInfo updateUserInfo(int userId, UserInfoModel serInfoModel) throws DiException {
		if(CommonUtility.isEmpty(serInfoModel.getName())){
			throw new DiException("昵称不能为空");
		}
//		if(!CommonUtility.isPhoneNumberValid(serInfoModel.getPhone())){
//			throw new DiException("手机号格式错误");
//		}
		
		//数据库修改
		DiUserInfo diUserInfo = this.diUserInfoMapper.selectByPrimaryKey(userId);
		diUserInfo.setName(serInfoModel.getName());
//		diUserInfo.setPhone(serInfoModel.getPhone());
		diUserInfo.setEmail(serInfoModel.getEmail());
		diUserInfo.setWork(serInfoModel.getWork());
		diUserInfo.setWeixin(serInfoModel.getWeixin());
		diUserInfo.setQq(serInfoModel.getQq());
		diUserInfo.setSex(serInfoModel.getSex());
		diUserInfo.setBirthday(CommonUtility.dateToString(serInfoModel.getBirthday(), "yyyy-MM-dd"));
		diUserInfo.setSignature(serInfoModel.getSignature());

		this.diUserInfoMapper.updateByPrimaryKeySelective(diUserInfo);
		return diUserInfo;
	}

	/**
	 * @param type 1：初始化密码 2：修改密码
	 */
	@Override
	public DiUserInfo updatePassWord(int userId, PassWordModel passWordModel) throws DiException {
		//type 1：初始化密码 2：修改密码
		if (1 == passWordModel.getType()) {
            if(CommonUtility.isEmpty(passWordModel.getPasswordnew()) || CommonUtility.isEmpty(passWordModel.getPasswordconfig())){
            	throw new DiException("新密码和确认密码不能为空");
            }
            if(!passWordModel.getPasswordnew().equals(passWordModel.getPasswordconfig())){
            	throw new DiException("两次输入的密码不想同");
            }
            //数据库修改
    		DiUserInfo diUserInfo = this.diUserInfoMapper.selectByPrimaryKey(userId);
    		diUserInfo.setPassword(MD5Util.stringToMD5(passWordModel.getPasswordnew()));
    		this.diUserInfoMapper.updateByPrimaryKeySelective(diUserInfo);
    		return diUserInfo;
        } else if (2 == passWordModel.getType()){
        	DiUserInfo diUserInfo = this.diUserInfoMapper.selectByPrimaryKey(userId);
        	if(!MD5Util.stringToMD5(passWordModel.getPassword()).equals(diUserInfo.getPassword())){
        		throw new DiException("旧密码不正确");
        	}
        	if(CommonUtility.isEmpty(passWordModel.getPasswordnew()) || CommonUtility.isEmpty(passWordModel.getPasswordconfig())){
            	throw new DiException("新密码和确认密码不能为空");
            }
            if(!passWordModel.getPasswordnew().equals(passWordModel.getPasswordconfig())){
            	throw new DiException("两次输入的密码不相同");
            }
            //数据库修改
    		diUserInfo.setPassword(MD5Util.stringToMD5(passWordModel.getPasswordnew()));
    		this.diUserInfoMapper.updateByPrimaryKeySelective(diUserInfo);
    		return diUserInfo;
        }
		return null;
	}

	@Override
	public DiUserInfo login(UserInfoModel userInfoModel) throws DiException {
		DiUserInfo diUserInfo = this.diUserInfoMapper.selectByUserNameAndPassWord(userInfoModel.getUsername(), MD5Util.stringToMD5(userInfoModel.getPassword()));
		if(null == diUserInfo){
			throw new DiException("用户名或密码错误");
		}
		return diUserInfo;
	}

	@Override
	public DiUserInfo register(UserInfoModel userInfoModel, String clientsid) throws DiException {
		if(CommonUtility.isEmpty(userInfoModel.getUsername())){
        	throw new DiException("用户名不能为空");
        }
    	if(CommonUtility.isEmpty(userInfoModel.getPassword())){
        	throw new DiException("密码不能为空");
        }
    	//检测用户名
		DiUserInfo selectUserInfo = this.diUserInfoMapper.selectByUserName(userInfoModel.getUsername());
		if(null != selectUserInfo){
			throw new DiException("用户名重复啦");
		}
        
    	DiUserInfo diUserInfo = null;
		switch (userInfoModel.getOthertype()) {
		case ICConsants.OTHER_LOGIN_REGISTER:
			DiUserInfo recordregister = new DiUserInfo();
			recordregister.setUsername(userInfoModel.getUsername());
			recordregister.setPassword(MD5Util.stringToMD5(userInfoModel.getPassword()));
			recordregister.setName(userInfoModel.getUsername());
			recordregister.setHead("http://ichead.dwtedx.com/userhead.png");
			recordregister.setSex("男");
			recordregister.setClientsid(clientsid);
			recordregister.setUpdatetime(CommonUtility.getCurrentDateTime());
			recordregister.setCreatetime(CommonUtility.getCurrentDateTime());
			int numberregister = this.diUserInfoMapper.insertSelective(recordregister);
			if(numberregister > 0){
				diUserInfo = this.diUserInfoMapper.selectByUserName(userInfoModel.getUsername());
			}

			break;
			
		case ICConsants.OTHER_LOGIN_QQ:
			DiUserInfo recordqq = new DiUserInfo();
			recordqq.setUsername(userInfoModel.getUsername());
			recordqq.setPassword(MD5Util.stringToMD5(userInfoModel.getPassword()));
			recordqq.setName(userInfoModel.getName());
			recordqq.setHead(userInfoModel.getHead());
			recordqq.setSex(userInfoModel.getSex());
			recordqq.setQqopenid(userInfoModel.getQqopenid());
			recordqq.setClientsid(clientsid);
			recordqq.setUpdatetime(CommonUtility.getCurrentDateTime());
			recordqq.setCreatetime(CommonUtility.getCurrentDateTime());
			int numberqq = this.diUserInfoMapper.insertSelective(recordqq);
			if(numberqq > 0){
				diUserInfo = this.diUserInfoMapper.selectByQQOpenId(userInfoModel.getQqopenid());
			}

			break;
			
		case ICConsants.OTHER_LOGIN_SINA:
			diUserInfo = this.diUserInfoMapper.selectBySinaOpenId(userInfoModel.getSinaopenid());
			DiUserInfo recordsina = new DiUserInfo();
			recordsina.setUsername(userInfoModel.getUsername());
			recordsina.setPassword(MD5Util.stringToMD5(userInfoModel.getPassword()));
			recordsina.setName(userInfoModel.getName());
			recordsina.setHead(userInfoModel.getHead());
			recordsina.setSex(userInfoModel.getSex());
			recordsina.setSinaopenid(userInfoModel.getSinaopenid());
			recordsina.setClientsid(clientsid);
			recordsina.setUpdatetime(CommonUtility.getCurrentDateTime());
			recordsina.setCreatetime(CommonUtility.getCurrentDateTime());
			int numbersina = this.diUserInfoMapper.insertSelective(recordsina);
			if(numbersina > 0){
				diUserInfo = this.diUserInfoMapper.selectBySinaOpenId(userInfoModel.getSinaopenid());
			}
		
			break;
			
		}
		return diUserInfo;
	}
	
	
	@Override
	public DiUserInfo registerByPhone(UserInfoModel userInfoModel, String clientsid) throws DiException {
		if(CommonUtility.isEmpty(userInfoModel.getUsername())){
        	throw new DiException("用户名不能为空");
        }
    	if(CommonUtility.isEmpty(userInfoModel.getPassword())){
        	throw new DiException("密码不能为空");
        }
    	//检测手机号
		DiUserInfo selectUserInfo = this.diUserInfoMapper.selectByPhone(userInfoModel.getUsername());
		if(null != selectUserInfo){
			throw new DiException("手机号已经注册了");
		}
		//检测手机验证码
		DiSendSms diSendSms = diSendSmsMapper.selectByFristRow(userInfoModel.getUsername());
		if(null == diSendSms){
			throw new DiException("请先获取验证码");
		}
		long dataOld = diSendSms.getCreatetime().getTime();
		long dataNew = CommonUtility.getCurrentDateTime().getTime();
		long diff = dataNew - dataOld;
		long min = diff / 60000;
		if(min > 30){
			throw new DiException("验证码已经过期！请重新获取");
		}
		if(!userInfoModel.getSmscode().equals(diSendSms.getParamstr())){
			throw new DiException("验证码错误！请检验后重试！若丢失验证码，请在30分钟后重新获取");
		}
        
    	DiUserInfo diUserInfo = null;
		switch (userInfoModel.getOthertype()) {
		case ICConsants.OTHER_LOGIN_REGISTER:
			DiUserInfo recordregister = new DiUserInfo();
			recordregister.setPhone(userInfoModel.getUsername());
			recordregister.setPassword(MD5Util.stringToMD5(userInfoModel.getPassword()));
			recordregister.setHead("http://ichead.dwtedx.com/userhead.png");
			recordregister.setSex("男");
			recordregister.setClientsid(clientsid);
			recordregister.setUpdatetime(CommonUtility.getCurrentDateTime());
			recordregister.setCreatetime(CommonUtility.getCurrentDateTime());
			int numberregister = this.diUserInfoMapper.insertSelective(recordregister);
			if(numberregister > 0){
				diUserInfo = this.diUserInfoMapper.selectByPhone(userInfoModel.getUsername());
			}

			break;
			
		case ICConsants.OTHER_LOGIN_QQ:
			DiUserInfo recordqq = new DiUserInfo();
			recordqq.setPhone(userInfoModel.getUsername());
			recordqq.setPassword(MD5Util.stringToMD5(userInfoModel.getPassword()));
			recordqq.setHead(userInfoModel.getHead());
			recordqq.setName(userInfoModel.getName());
			recordqq.setSex(userInfoModel.getSex());
			recordqq.setQqopenid(userInfoModel.getQqopenid());
			recordqq.setClientsid(clientsid);
			recordqq.setUpdatetime(CommonUtility.getCurrentDateTime());
			recordqq.setCreatetime(CommonUtility.getCurrentDateTime());
			int numberqq = this.diUserInfoMapper.insertSelective(recordqq);
			if(numberqq > 0){
				diUserInfo = this.diUserInfoMapper.selectByPhone(userInfoModel.getUsername());
			}

			break;
			
		case ICConsants.OTHER_LOGIN_SINA:
			diUserInfo = this.diUserInfoMapper.selectBySinaOpenId(userInfoModel.getSinaopenid());
			DiUserInfo recordsina = new DiUserInfo();
			recordsina.setPhone(userInfoModel.getUsername());
			recordsina.setPassword(MD5Util.stringToMD5(userInfoModel.getPassword()));
			recordsina.setHead(userInfoModel.getHead());
			recordsina.setName(userInfoModel.getName());
			recordsina.setSex(userInfoModel.getSex());
			recordsina.setSinaopenid(userInfoModel.getSinaopenid());
			recordsina.setClientsid(clientsid);
			recordsina.setUpdatetime(CommonUtility.getCurrentDateTime());
			recordsina.setCreatetime(CommonUtility.getCurrentDateTime());
			int numbersina = this.diUserInfoMapper.insertSelective(recordsina);
			if(numbersina > 0){
				diUserInfo = this.diUserInfoMapper.selectByPhone(userInfoModel.getUsername());
			}
		
			break;
			
		}
		//2020-03-24邀请用户注册送VIP
		DiUserinviteinfo userinviteinfo = diUserinviteinfoMapper.selectUserinviteByPhone(userInfoModel.getUsername());
		if(null != userinviteinfo) {
			DiUserInfo userInviteUserInfo = diUserInfoMapper.selectByPrimaryKey(userinviteinfo.getUserid());
			//VIP记录 时间计算
			Date date = new Date();
			if (userInviteUserInfo.getVipflag() == ICConsants.VIP_TYPE_VIP) {
                date = userInviteUserInfo.getVipendtime();
            }
			Calendar cal = Calendar.getInstance();
            cal.setTime(date);//设置起时间 
            cal.add(Calendar.MONTH, 1);//增加一个月
			DiUservipinfo uservipinfo = new DiUservipinfo();
			uservipinfo.setUserid(userinviteinfo.getUserid());
			uservipinfo.setMonths(1);
			uservipinfo.setStarttime(date);
			uservipinfo.setEndtime(cal.getTime());
			uservipinfo.setType(1);//0:充值VIp 1:活动获得VIP
			uservipinfo.setTypename("邀请好友得1月VIP");
			uservipinfo.setDeleteflag(ICConsants.DELETEFALAG_NOTDELETE);
			uservipinfo.setCreatetime(new Date());
			diUservipinfoMapper.insertSelective(uservipinfo);
			//升级VIP
			userInviteUserInfo.setVipflag(ICConsants.VIP_TYPE_VIP);
			userInviteUserInfo.setVipendtime(cal.getTime());
			this.diUserInfoMapper.updateByPrimaryKeySelective(userInviteUserInfo);
			//记录邀请状态完成
			userinviteinfo.setGivevip(1);//0:没有赠与 1:已经发放VIP
			userinviteinfo.setStatus(2);//0:未发送邀请 1:已经邀请 2:邀请成功
			diUserinviteinfoMapper.updateByPrimaryKeySelective(userinviteinfo);
		}
		return diUserInfo;
	}
	
	@Override
	public DiUserInfo getUserByOtherIdV2(UserInfoModel userInfoModel, String clientsid) {
		DiUserInfo diUserInfo = null;
		switch (userInfoModel.getOthertype()) {
		case ICConsants.OTHER_LOGIN_QQ:
			diUserInfo = this.diUserInfoMapper.selectByQQOpenId(userInfoModel.getQqopenid());
			break;
			
		case ICConsants.OTHER_LOGIN_SINA:
			diUserInfo = this.diUserInfoMapper.selectBySinaOpenId(userInfoModel.getSinaopenid());
			break;
			
		case ICConsants.OTHER_LOGIN_WEIXIN:
			diUserInfo = this.diUserInfoMapper.selectByWeixinOpenId(userInfoModel.getWeixinopenid());
			break;
			
		}
		return diUserInfo;
	}
	
	@Override
	public DiUserInfo updateUserPhone(int userId, UserInfoModel modelBody) throws DiException {
		if(CommonUtility.isEmpty(modelBody.getPhone())){
			throw new DiException("手机号不能为空");
		}
		//检测手机号
		DiUserInfo selectUserInfo = this.diUserInfoMapper.selectByPhone(modelBody.getPhone());
		if(null != selectUserInfo){
			throw new DiException("手机号重复啦");
		}
		//检测手机验证码
		DiSendSms diSendSms = diSendSmsMapper.selectByFristRow(modelBody.getPhone());
		if(null == diSendSms){
			throw new DiException("请先获取验证码");
		}
		long dataOld = diSendSms.getCreatetime().getTime();
		long dataNew = CommonUtility.getCurrentDateTime().getTime();
		long diff = dataNew - dataOld;
		long min = diff / 60000;
		if(min > 30){
			throw new DiException("验证码已经过期！请重新获取");
		}
		if(!modelBody.getSmscode().equals(diSendSms.getParamstr())){
			throw new DiException("验证码错误！请检验后重试！若丢失验证码，请在30分钟后重新获取");
		}
		
		//数据库修改
		DiUserInfo diUserInfo = this.diUserInfoMapper.selectByPrimaryKey(userId);
		diUserInfo.setPhone(modelBody.getPhone());
		this.diUserInfoMapper.updateByPrimaryKeySelective(diUserInfo);
		return diUserInfo;
	}
	
	@Override
	public DiUserInfo reSetUserPass(int userId, UserInfoModel modelBody) throws DiException {
		if(CommonUtility.isEmpty(modelBody.getPhone())){
			throw new DiException("手机号不能为空");
		}
		//检测手机验证码
		DiSendSms diSendSms = diSendSmsMapper.selectByFristRow(modelBody.getPhone());
		if(null == diSendSms){
			throw new DiException("请先获取验证码");
		}
		long dataOld = diSendSms.getCreatetime().getTime();
		long dataNew = CommonUtility.getCurrentDateTime().getTime();
		long diff = dataNew - dataOld;
		long min = diff / 60000;
		if(min > 30){
			throw new DiException("验证码已经过期！请重新获取");
		}
		if(!modelBody.getSmscode().equals(diSendSms.getParamstr())){
			throw new DiException("验证码错误！请检验后重试！若丢失验证码，请在30分钟后重新获取");
		}
		
		//数据库修改
		DiUserInfo diUserInfo = this.diUserInfoMapper.selectByPhone(modelBody.getPhone());
		diUserInfo.setPassword(MD5Util.stringToMD5(modelBody.getPassword()));
		this.diUserInfoMapper.updateByPrimaryKeySelective(diUserInfo);
		return diUserInfo;
	}
	
	/**
	 * 升级VIP
	 */
	@Override
	public void updateUserVipInfo(int userId, Date endTime) throws DiException {
		//检测
		DiUserInfo diUserInfo = this.diUserInfoMapper.selectByPrimaryKey(userId);
		if(null == diUserInfo){
			throw new DiException("用户未找到");
		}
		
		//数据库修改
		diUserInfo.setVipflag(ICConsants.VIP_TYPE_VIP);
		diUserInfo.setVipendtime(endTime);
		this.diUserInfoMapper.updateByPrimaryKeySelective(diUserInfo);
	}
	
}
