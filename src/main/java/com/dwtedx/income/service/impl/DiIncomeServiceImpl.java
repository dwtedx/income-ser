package com.dwtedx.income.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dwtedx.income.dao.IDiIncomeMapper;
import com.dwtedx.income.dao.IDiScanMapper;
import com.dwtedx.income.exception.DiException;
import com.dwtedx.income.model.IncomeModel;
import com.dwtedx.income.model.RegainModel;
import com.dwtedx.income.model.ScanModel;
import com.dwtedx.income.pojo.DiIncome;
import com.dwtedx.income.pojo.DiScan;
import com.dwtedx.income.service.IDiIncomeService;
import com.dwtedx.income.utility.CommonUtility;
import com.dwtedx.income.utility.ICConsants;

@Service("diIncomeService")
public class DiIncomeServiceImpl implements IDiIncomeService {

	@Resource
	private IDiIncomeMapper diIncomeMapper;
	@Resource
	private IDiScanMapper diScanMapper;
	
	@Override
	public DiIncome getIncomeById(int userId) {
		
		return this.diIncomeMapper.selectByPrimaryKey(userId);
	}
	
	public void deleteIncome(IncomeModel incomeModel) {
		this.diIncomeMapper.deleteByClientidAndUserId(new DiIncome(incomeModel.getClientid(), incomeModel.getUserid()));
	}

	@Override
	public List<IncomeModel> saveIncomes(ArrayList<IncomeModel> modelBody) throws DiException {
		DiIncome income = null;
		DiScan scan = null;
		for (IncomeModel incomeModel : modelBody) {
			if(0 == incomeModel.getUserid()){
				throw new DiException("数据同步需要先登录哦");
			}
			//检测是否是开始节点
			if(ICConsants.INCOME_ROLE_START == incomeModel.getRole()){
				List<DiIncome> dbDiIncomes = this.diIncomeMapper.selectByRoleAndUserId(incomeModel.getRole(), incomeModel.getUserid());
				if(dbDiIncomes.size() > 0){
					continue;
				}
			}
			income = new DiIncome();
			income.setClientid(incomeModel.getId());
			income.setUsername(incomeModel.getUsername());
			income.setUserid(incomeModel.getUserid());
			income.setRole(incomeModel.getRole());
			income.setMoneysum(incomeModel.getMoneysum());
			income.setType(incomeModel.getType());
			income.setTypeid(incomeModel.getTypeid());
			income.setAccount(incomeModel.getAccount());
			income.setAccountid(incomeModel.getAccountid());
			income.setRemark(incomeModel.getRemark());
			income.setLocation(incomeModel.getLocation());
			income.setVoicepath(incomeModel.getVoicepath());
			income.setImagepath(incomeModel.getImagepath());
			income.setRecordtype(incomeModel.getRecordtype());
			income.setRecordtime(CommonUtility.dateToString(incomeModel.getRecordtime()));
			income.setDeletefalag(incomeModel.getDeletefalag());
			income.setCreatetime(CommonUtility.dateToString(incomeModel.getCreatetime()));
			income.setUpdatetime(CommonUtility.dateToString(incomeModel.getUpdatetime()));
			
			diIncomeMapper.insertSelective(income);
			incomeModel.setServerid(income.getId());
			//保存扫单
			if(income.getRecordtype() == ICConsants.INCOME_RECORD_TYPE_1 
					&& null != incomeModel.getScanList() 
					&& incomeModel.getScanList().size() > 0) {
				for(int i = 0; i < incomeModel.getScanList().size(); i++) {
					scan = new DiScan();
					scan.setUsername(incomeModel.getUsername());
					scan.setUserid(incomeModel.getUserid());
					scan.setIncomeid(income.getId());
					scan.setMoneysum(incomeModel.getScanList().get(i).getMoneysum());
					scan.setName(incomeModel.getScanList().get(i).getName());
					scan.setStore(incomeModel.getScanList().get(i).getStore());
					scan.setBrand(incomeModel.getScanList().get(i).getBrand());
					scan.setQuantity(incomeModel.getScanList().get(i).getQuantity());
					scan.setType(incomeModel.getScanList().get(i).getType());
					scan.setSequence(i);
					scan.setRemark(incomeModel.getScanList().get(i).getRemark());
					scan.setCreatetime(CommonUtility.dateToString(incomeModel.getRecordtime()));
					scan.setUpdatetime(CommonUtility.dateToString(incomeModel.getRecordtime()));
					scan.setDeletefalag(ICConsants.DELETEFALAG_NOTDELETE);
					diScanMapper.insertSelective(scan);
					incomeModel.getScanList().get(i).setServerid(scan.getId());
				}
			}
		}
		
		return modelBody;
		
	}

	@Override
	public void saveIncomeSingle(IncomeModel incomeModel) throws DiException {
		if(0 == incomeModel.getUserid()){
			throw new DiException("数据同步需要先登录哦");
		}
		//得到原来的记录
		DiIncome income = diIncomeMapper.selectByClientidAndUserId(new DiIncome(incomeModel.getId(), incomeModel.getUserid()));
		if(null == income){
			throw new DiException("该数据未同步到服务器哦");
		}
		income.setMoneysum(incomeModel.getMoneysum());
		income.setType(incomeModel.getType());
		income.setTypeid(incomeModel.getTypeid());
		income.setAccount(incomeModel.getAccount());
		income.setAccountid(incomeModel.getAccountid());
		income.setRemark(incomeModel.getRemark());
		income.setLocation(incomeModel.getLocation());
		income.setVoicepath(incomeModel.getVoicepath());
		income.setImagepath(incomeModel.getImagepath());
		income.setRecordtype(incomeModel.getRecordtype());
		income.setRecordtime(CommonUtility.dateToString(incomeModel.getRecordtime()));
		income.setDeletefalag(incomeModel.getDeletefalag());
		income.setUpdatetime(CommonUtility.dateToString(incomeModel.getUpdatetime()));
		diIncomeMapper.updateByPrimaryKeySelective(income);
	}

	@Override
	public List<IncomeModel> getIncomesByUserId(RegainModel modelBody) throws DiException {
		if(modelBody.getId() == 0){
			throw new DiException("您当前还未登录哦");
		}
		List<IncomeModel> incomeModelList = new ArrayList<IncomeModel>();
		IncomeModel incomeModel = null;
		List<DiIncome> dbDiIncomes =  diIncomeMapper.selectIncomesByUserId(modelBody.getId(), modelBody.getStart(), modelBody.getLength());
		for (DiIncome diIncome : dbDiIncomes) {
			incomeModel = new IncomeModel();
			incomeModel.setId(diIncome.getId());
			incomeModel.setClientid(diIncome.getClientid());
			incomeModel.setUsername(diIncome.getUsername());
			incomeModel.setUserid(diIncome.getUserid());
			incomeModel.setRole(diIncome.getRole());
			incomeModel.setMoneysum(diIncome.getMoneysum());
			incomeModel.setType(diIncome.getType());
			incomeModel.setTypeid(diIncome.getTypeid());
			incomeModel.setAccount(diIncome.getAccount());
			incomeModel.setAccountid(diIncome.getAccountid());
			incomeModel.setRemark(diIncome.getRemark());
			incomeModel.setLocation(diIncome.getLocation());
			incomeModel.setVoicepath(diIncome.getVoicepath());
			incomeModel.setImagepath(diIncome.getImagepath());
			incomeModel.setRecordtype(null != diIncome.getRecordtype()?diIncome.getRecordtype():0);
			incomeModel.setRecordtime(CommonUtility.stringDateFormart(diIncome.getRecordtime()));
			incomeModel.setDeletefalag(null != diIncome.getDeletefalag()?diIncome.getDeletefalag():0);
			incomeModel.setCreatetime(CommonUtility.stringDateFormart(diIncome.getCreatetime()));
			incomeModel.setUpdatetime(CommonUtility.stringDateFormart(diIncome.getUpdatetime()));
			incomeModelList.add(incomeModel);
			//扫单
			if(null != diIncome.getRecordtype() && diIncome.getRecordtype() == ICConsants.INCOME_RECORD_TYPE_1) {
				ScanModel scanModel;
				List<ScanModel> scanModelList = null;
				List<DiScan> diScans = diScanMapper.selectIncomesByIncomeId(diIncome.getId());
				if(null != diScans && diScans.size() > 0) {
					scanModelList = new ArrayList<ScanModel>();
					for (DiScan diScan : diScans) {
						scanModel = new ScanModel();
						scanModel.setId(diScan.getId());
						scanModel.setUsername(diScan.getUsername());
						scanModel.setUserid(diScan.getUserid());
						scanModel.setMoneysum(diScan.getMoneysum());
						scanModel.setName(diScan.getName());
						scanModel.setStore(diScan.getStore());
						scanModel.setBrand(diScan.getBrand());
						scanModel.setQuantity(diScan.getQuantity());
						scanModel.setType(diScan.getType());
						scanModel.setSequence(diScan.getSequence());
						scanModel.setRemark(diScan.getRemark());
						scanModel.setCreatetime(CommonUtility.stringDateFormart(diScan.getCreatetime()));
						scanModel.setUpdatetime(CommonUtility.stringDateFormart(diScan.getUpdatetime()));
						scanModel.setDeletefalag(diScan.getDeletefalag());						
						scanModelList.add(scanModel);
					}
					incomeModel.setScanList(scanModelList);
				}	
			}
		}
		return incomeModelList;
	}

	@Override
	public void deleteIncomeById(IncomeModel incomeModel) throws DiException {
		//this.diIncomeMapper.deleteByPrimaryKey(body.getServerid());
		DiIncome income = diIncomeMapper.selectByPrimaryKey(incomeModel.getServerid());
		income.setDeletefalag(ICConsants.DELETEFALAG_DELETEED);
		diIncomeMapper.updateByPrimaryKey(income);
		//删除扫单
		if(income.getRecordtype() == ICConsants.INCOME_RECORD_TYPE_1 
				&& null != incomeModel.getScanList() 
				&& incomeModel.getScanList().size() > 0) {
			DiScan scan;
			for(int i = 0; i < incomeModel.getScanList().size(); i++) {
				scan = new DiScan();
				scan.setId(incomeModel.getScanList().get(i).getServerid());
				scan.setDeletefalag(ICConsants.DELETEFALAG_DELETEED);
				diScanMapper.updateByPrimaryKeySelective(scan);
			}
		}
	}
	
	@Override
	public void saveIncomeSingleById(IncomeModel incomeModel) throws DiException {
		if(0 == incomeModel.getUserid()){
			throw new DiException("数据同步需要先登录哦");
		}
		//得到原来的记录
		DiIncome income = diIncomeMapper.selectByPrimaryKey(incomeModel.getServerid());
		if(null == income){
			throw new DiException("该数据未同步到服务器哦");
		}
		if(income.getUserid() != incomeModel.getUserid()){
			throw new DiException("该数据在服务器不属于你哦");
		}
		income.setMoneysum(incomeModel.getMoneysum());
		income.setType(incomeModel.getType());
		income.setTypeid(incomeModel.getTypeid());
		income.setAccount(incomeModel.getAccount());
		income.setAccountid(incomeModel.getAccountid());
		income.setRemark(incomeModel.getRemark());
		income.setLocation(incomeModel.getLocation());
		income.setVoicepath(incomeModel.getVoicepath());
		income.setImagepath(incomeModel.getImagepath());
		income.setRecordtype(incomeModel.getRecordtype());
		income.setRecordtime(CommonUtility.dateToString(incomeModel.getRecordtime()));
		income.setDeletefalag(incomeModel.getDeletefalag());
		income.setUpdatetime(CommonUtility.dateToString(incomeModel.getUpdatetime()));
		diIncomeMapper.updateByPrimaryKeySelective(income);
	}

	@Override
	public IncomeModel saveIncomeScanSingleById(IncomeModel incomeModel) throws DiException {
		if(0 == incomeModel.getUserid()){
			throw new DiException("数据同步需要先登录哦");
		}
		//得到原来的记录
		DiIncome income = diIncomeMapper.selectByPrimaryKey(incomeModel.getServerid());
		if(null == income){
			throw new DiException("该数据未同步到服务器哦");
		}
		if(income.getUserid() != incomeModel.getUserid()){
			throw new DiException("该数据在服务器不属于你哦");
		}
		income.setMoneysum(incomeModel.getMoneysum());
		income.setType(incomeModel.getType());
		income.setTypeid(incomeModel.getTypeid());
		income.setAccount(incomeModel.getAccount());
		income.setAccountid(incomeModel.getAccountid());
		income.setRemark(incomeModel.getRemark());
		income.setLocation(incomeModel.getLocation());
		income.setVoicepath(incomeModel.getVoicepath());
		income.setImagepath(incomeModel.getImagepath());
		income.setRecordtype(incomeModel.getRecordtype());
		income.setRecordtime(CommonUtility.dateToString(incomeModel.getRecordtime()));
		income.setDeletefalag(incomeModel.getDeletefalag());
		income.setUpdatetime(CommonUtility.dateToString(incomeModel.getUpdatetime()));
		diIncomeMapper.updateByPrimaryKeySelective(income);
		//保存扫单
		DiScan scan = null;
		if(income.getRecordtype() == ICConsants.INCOME_RECORD_TYPE_1 
				&& null != incomeModel.getScanList() 
				&& incomeModel.getScanList().size() > 0) {
			for(int i = 0; i < incomeModel.getScanList().size(); i++) {
				if(incomeModel.getScanList().get(i).getServerid() > 0) {
					scan = diScanMapper.selectByPrimaryKey(incomeModel.getScanList().get(i).getServerid());
				}else {
					scan = new DiScan();
				}
				scan.setUsername(incomeModel.getUsername());
				scan.setUserid(incomeModel.getUserid());
				scan.setIncomeid(income.getId());
				scan.setMoneysum(incomeModel.getScanList().get(i).getMoneysum());
				scan.setName(incomeModel.getScanList().get(i).getName());
				scan.setStore(incomeModel.getScanList().get(i).getStore());
				scan.setBrand(incomeModel.getScanList().get(i).getBrand());
				scan.setQuantity(incomeModel.getScanList().get(i).getQuantity());
				scan.setType(incomeModel.getScanList().get(i).getType());
				scan.setSequence(i);
				scan.setRemark(incomeModel.getScanList().get(i).getRemark());
				scan.setCreatetime(CommonUtility.dateToString(incomeModel.getRecordtime()));
				scan.setUpdatetime(CommonUtility.dateToString(incomeModel.getRecordtime()));
				scan.setDeletefalag(incomeModel.getScanList().get(i).getDeletefalag());
				if(incomeModel.getScanList().get(i).getServerid() > 0) {
					diScanMapper.updateByPrimaryKey(scan);
				}else {
					diScanMapper.insertSelective(scan);
					incomeModel.getScanList().get(i).setServerid(scan.getId());
				}
			}
		}
		return incomeModel;
	}

	@Override
	public void updateIncomeBeForTime(IncomeModel modelBody, int userid) throws DiException {
		if(0 == userid) {
			throw new DiException("请先登录，同步云端记账时间");
		}
		List<DiIncome> startIncomes = diIncomeMapper.selectByRoleAndUserId(ICConsants.INCOME_ROLE_START, userid);
		if(null == startIncomes || startIncomes.size() == 0) {
			throw new DiException("您还未同步账目到云端");
		}
		
		Date recordtime = CommonUtility.dateToString(modelBody.getRecordtime());
		if(null == recordtime) {
			throw new DiException("记账时间异常");
		}
		
		DiIncome startIncome = startIncomes.get(0);
		startIncome.setRecordtime(recordtime);
		diIncomeMapper.updateByPrimaryKeySelective(startIncome);
	}

}
