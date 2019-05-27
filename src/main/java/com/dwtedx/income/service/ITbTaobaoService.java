package com.dwtedx.income.service;

import java.io.IOException;
import java.util.List;

import com.dwtedx.income.exception.DiException;
import com.dwtedx.income.model.TaobaoModel;
import com.dwtedx.income.pojo.TbCategoryInfo;
import com.dwtedx.income.pojo.TbItemInfo;

public interface ITbTaobaoService {
		
	public List<TbItemInfo> getTopTaobaoItemInfo(TaobaoModel modelBody) throws DiException;

	public List<TbItemInfo> getTaobaoItemByCategoryId(TaobaoModel modelBody);
	
	public List<TbItemInfo> getNineNineTaobaoItem(TaobaoModel modelBody) throws DiException;
	
	public void refreshTaobaoItemInfo(TaobaoModel modelBody) throws DiException, IOException;

	public List<TbItemInfo> getWomensClothingItem(TaobaoModel modelBody) throws DiException;

	public List<TbItemInfo> getCouponItem(TaobaoModel modelBody) throws DiException;

	public List<TbItemInfo> getManClothingItem(TaobaoModel body) throws DiException;

	public List<TbItemInfo> getSnacksItem(TaobaoModel body) throws DiException;

	public List<TbItemInfo> getMakeupsItem(TaobaoModel body) throws DiException;

	public List<TbItemInfo> getDigitalItem(TaobaoModel body) throws DiException;

	public List<TbItemInfo> getUnderwearItem(TaobaoModel body) throws DiException;

	public List<TbItemInfo> getChildrenItem(TaobaoModel body) throws DiException;

	public List<TbItemInfo> getLiveHomeItem(TaobaoModel modelBody) throws DiException;

	public List<TbItemInfo> getLuggageItem(TaobaoModel body) throws DiException;

	public List<TbItemInfo> getOtherItem(TaobaoModel body) throws DiException;

	public List<TbItemInfo> getMotherBabyItem(TaobaoModel modelBody) throws DiException;

	public List<TbCategoryInfo> getCategoryTop(TaobaoModel modelBody) throws DiException;

	public List<TbItemInfo> getRandItem(TaobaoModel modelBody) throws DiException;

	public List<TbItemInfo> getCategoryIdItem(TaobaoModel body) throws DiException;
	
}
