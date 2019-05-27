package com.dwtedx.income.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dwtedx.income.dao.ITbCategoryInfoMapper;
import com.dwtedx.income.dao.ITbItemInfoMapper;
import com.dwtedx.income.exception.DiException;
import com.dwtedx.income.model.TaobaoModel;
import com.dwtedx.income.pojo.TbCategoryInfo;
import com.dwtedx.income.pojo.TbItemInfo;
import com.dwtedx.income.service.ITbTaobaoService;
import com.dwtedx.income.utility.CommonUtility;
import com.dwtedx.income.utility.ExcelUtil;

@Service("diTaobaoService")
public class TbTaobaoService implements ITbTaobaoService {

	// 淘宝产品类型
	public final static int TAOBAO_TYPE_TIANMAO = 1;
	public final static int TAOBAO_TYPE_TAOBAO = 0;

	public static final String TYPE_TIANMAO = "天猫";// 1
	public static final String TYPE_TAOBAO = "淘宝";// 0

	@Resource
	private ITbCategoryInfoMapper tbCategoryInfoMapper;
	@Resource
	private ITbItemInfoMapper tbItemInfoMapper;

	@Override
	public List<TbItemInfo> getTopTaobaoItemInfo(TaobaoModel modelBody) throws DiException {
		List<TbItemInfo> itemlist = tbItemInfoMapper.selectByTopTaobaoItem(modelBody.getStart(), modelBody.getLength());
		return itemlist;
	}

	@Override
	public List<TbItemInfo> getTaobaoItemByCategoryId(TaobaoModel modelBody) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TbItemInfo> getNineNineTaobaoItem(TaobaoModel modelBody) throws DiException {
		List<TbItemInfo> itemlist = tbItemInfoMapper.selectByNineNineItem(modelBody.getStart(), modelBody.getLength());
		return itemlist;
	}

	@Override
	public List<TbItemInfo> getWomensClothingItem(TaobaoModel modelBody) throws DiException {
		// 女装/女士精品 女鞋
		Integer[] ids = { 9, 11 };
		List<TbItemInfo> itemlist = tbItemInfoMapper.selectByCategorysItem(ids, modelBody.getStart(),
				modelBody.getLength());
		return itemlist;
	}

	@Override
	public List<TbItemInfo> getCouponItem(TaobaoModel modelBody) throws DiException {
		List<TbItemInfo> itemlist = tbItemInfoMapper.selectByCouponItem(modelBody.getStart(), modelBody.getLength());
		return itemlist;
	}

	@Override
	public List<TbItemInfo> getManClothingItem(TaobaoModel modelBody) throws DiException {
		// 男装 皮带/配件 男鞋
		Integer[] ids = { 26, 29, 25 };
		List<TbItemInfo> itemlist = tbItemInfoMapper.selectByCategorysItem(ids, modelBody.getStart(),
				modelBody.getLength());
		return itemlist;
	}

	@Override
	public List<TbItemInfo> getSnacksItem(TaobaoModel modelBody) throws DiException {
		// 零食 咖啡/麦片/冲饮
		Integer[] ids = { 20, 16 };
		List<TbItemInfo> itemlist = tbItemInfoMapper.selectByCategorysItem(ids, modelBody.getStart(),
				modelBody.getLength());
		return itemlist;
	}

	@Override
	public List<TbItemInfo> getMakeupsItem(TaobaoModel modelBody) throws DiException {
		// 彩妆 美容 美发
		Integer[] ids = { 3, 12, 35 };
		List<TbItemInfo> itemlist = tbItemInfoMapper.selectByCategorysItem(ids, modelBody.getStart(),
				modelBody.getLength());
		return itemlist;
	}

	@Override
	public List<TbItemInfo> getDigitalItem(TaobaoModel modelBody) throws DiException {
		// 3C 智能设备 手机 u盘 大家电
		Integer[] ids = { 10, 70, 79, 67, 61 };
		List<TbItemInfo> itemlist = tbItemInfoMapper.selectByCategorysItem(ids, modelBody.getStart(),
				modelBody.getLength());
		return itemlist;
	}

	@Override
	public List<TbItemInfo> getUnderwearItem(TaobaoModel modelBody) throws DiException {
		// 内衣
		Integer[] ids = { 1 };
		List<TbItemInfo> itemlist = tbItemInfoMapper.selectByCategorysItem(ids, modelBody.getStart(),
				modelBody.getLength());
		return itemlist;
	}

	@Override
	public List<TbItemInfo> getChildrenItem(TaobaoModel modelBody) throws DiException {
		// 童装 童鞋 玩具
		Integer[] ids = { 7, 22, 41 };
		List<TbItemInfo> itemlist = tbItemInfoMapper.selectByCategorysItem(ids, modelBody.getStart(),
				modelBody.getLength());
		return itemlist;
	}

	@Override
	public List<TbItemInfo> getMotherBabyItem(TaobaoModel modelBody) throws DiException {
		// 尿片 孕妇装
		Integer[] ids = { 21, 15 };
		List<TbItemInfo> itemlist = tbItemInfoMapper.selectByCategorysItem(ids, modelBody.getStart(),
				modelBody.getLength());
		return itemlist;
	}

	@Override
	public List<TbItemInfo> getLiveHomeItem(TaobaoModel modelBody) throws DiException {
		// 床上用品 居家日用 洗护 餐具 保健 布艺 清洁工具 生活电器 厨房 厨房电器 家居饰品
		Integer[] ids = { 8, 14, 18, 32, 33, 17, 2, 34, 39, 50, 48 };
		List<TbItemInfo> itemlist = tbItemInfoMapper.selectByCategorysItem(ids, modelBody.getStart(),
				modelBody.getLength());
		return itemlist;
	}

	@Override
	public List<TbItemInfo> getLuggageItem(TaobaoModel modelBody) throws DiException {
		// 箱包
		Integer[] ids = { 28 };
		List<TbItemInfo> itemlist = tbItemInfoMapper.selectByCategorysItem(ids, modelBody.getStart(),
				modelBody.getLength());
		return itemlist;
	}

	@Override
	public List<TbItemInfo> getOtherItem(TaobaoModel modelBody) throws DiException {
		Integer[] ids = { 9, 11, 26, 29, 25, 20, 16, 3, 12, 35, 10, 70, 79, 67, 61, 1, 7, 22, 41, 8, 14, 18, 32, 33, 17,
				2, 34, 39, 50, 48, 28, 21, 15 };
		List<TbItemInfo> itemlist = tbItemInfoMapper.selectByNotCategorysItem(ids, modelBody.getStart(),
				modelBody.getLength());
		return itemlist;
	}

	@Override
	public List<TbCategoryInfo> getCategoryTop(TaobaoModel modelBody) throws DiException {
		List<TbCategoryInfo> itemlist = tbCategoryInfoMapper.selectByCategoryTop();
		return itemlist;
	}

	@Override
	public List<TbItemInfo> getRandItem(TaobaoModel modelBody) throws DiException {
		List<TbItemInfo> itemlist = tbItemInfoMapper.selectByRandItem();
		return itemlist;
	}

	@Override
	public List<TbItemInfo> getCategoryIdItem(TaobaoModel modelBody) throws DiException {
		List<TbItemInfo> itemlist = tbItemInfoMapper.selectByCategoryIdItem(modelBody.getCategoryId(),
				modelBody.getStart(), modelBody.getLength());
		return itemlist;
	}

	@Override
	public void refreshTaobaoItemInfo(TaobaoModel modelBody) throws DiException, IOException {
		if (!"refresh".equals(modelBody.getSearch())) {
			new DiException("异常访问");
		}

		ExcelThread excelThread = new ExcelThread();
		excelThread.start();

	}

	public class ExcelThread extends Thread {
		public void run() {
			tbItemInfoMapper.deleteAll();

			ClassLoader classLoader = getClass().getClassLoader();
			File file = new File(classLoader.getResource("taobaoitem.xls").getFile());

			int userType;
			int categoryId;
			TbCategoryInfo categoryInfo;
			TbCategoryInfo categoryInfoDb;
			TbItemInfo itemInfo;
			TbItemInfo itemInfoDb;

			ArrayList<ArrayList<Object>> readExcel = ExcelUtil.readExcel(file);
			for (ArrayList<Object> arrayList : readExcel) {

				/**
				 * [558973762527, 加绒打底裤女外穿加厚秋冬2017新款韩版百搭魔术紧身小脚黑色裤子,
				 * http://img.alicdn.com/bao/uploaded/i1/2086112839/TB1LUxxh50TMKJjSZFNXXa_1FXa_!!0-item_pic.jpg,
				 * http://item.taobao.com/item.htm?id=558973762527, 女装/女士精品,
				 * https://s.click.taobao.com/t?e=m%3D2%26s%3Dfj5tAtCY2dscQipKwQzePOeEDrYVVa64K7Vc7tFgwiFRAdhuF14FMb5oVZyeo4O25x%2BIUlGKNpUaWhx%2BqGV%2FIbmC%2B6Ir001q6JDiZr6NjSPMzhrgZv%2FStgyIE64ZpKR9Ska33%2Bz2tASpQN%2Bi3okbbJOpW2gUp7uQIYULNg46oBA%3D,
				 * 69.00, 901, 5.50, 3.80, 红笛格旗舰店, 2086112839, 红笛格旗舰店, 天猫,
				 * 7b71ac63c918405e9fde9c03e1c002ae, 10000, 10000, 满68元减10元,
				 * 2017-10-14, 2017-10-21,
				 * https://taoquan.taobao.com/coupon/unify_apply.htm?sellerId=2086112839&activityId=7b71ac63c918405e9fde9c03e1c002ae,
				 * https://uland.taobao.com/coupon/edetail?e=ngW92Z87KLYN%2BoQUE6FNzLJ0iUlMmtbCl%2BleiOJe%2Fg82pBqXNLtfmUSYnPwzMcPWXCIIhmg8%2Fg65P55O47FyWhpywujSvOp2nUIklpPPqYJNGO4Yr8%2F3fEkV28FNZ4XpafjiqKfgEbSYAeXOCnfB2gSVH0ge1z6o&pid=mm_115403208_16988206_66306847&af=1]
				 */
				if ("商品id".equals(arrayList.get(0).toString())) {
					continue;
				}

				userType = TYPE_TIANMAO.equals(arrayList.get(13)) ? TAOBAO_TYPE_TIANMAO : TAOBAO_TYPE_TAOBAO;

				// 判断是否是最后一行
				if (CommonUtility.isEmpty(arrayList.get(0).toString())
						|| CommonUtility.isEmpty(arrayList.get(4).toString())) {
					break;
				}

				categoryInfoDb = tbCategoryInfoMapper.selectByCategoryName(arrayList.get(4).toString());
				if (categoryInfoDb == null) {
					categoryInfo = new TbCategoryInfo();
					categoryInfo.setCategoryName(arrayList.get(4).toString());
					categoryInfo.setCreatetime(Calendar.getInstance().getTime());

					tbCategoryInfoMapper.insert(categoryInfo);
					categoryId = tbCategoryInfoMapper.selectByCategoryName(arrayList.get(4).toString()).getId();
				} else {
					categoryId = categoryInfoDb.getId();
				}

				// 计算价格
				String couponVal = null;
				try {
					couponVal = arrayList.get(17).toString();
					Pattern pattern = Pattern.compile("\\d+");
					Matcher matcher = pattern.matcher(couponVal);
					while (matcher.find()) {
						couponVal = matcher.group(0);
					}
				} catch (Exception e) {
					e.printStackTrace();
					couponVal = "0";
				}

				itemInfo = new TbItemInfo(arrayList.get(3).toString(), arrayList.get(10).toString(),
						arrayList.get(0).toString(), categoryId, arrayList.get(2).toString(), null, "0",
						arrayList.get(11).toString(), null, arrayList.get(1).toString(), userType,
						Integer.parseInt(arrayList.get(7).toString()), arrayList.get(6).toString(),
						arrayList.get(5).toString(), arrayList.get(8).toString(), arrayList.get(9).toString(),
						arrayList.get(12).toString(), couponVal, arrayList.get(14).toString(), arrayList.get(20).toString(),
						arrayList.get(21).toString(), Integer.parseInt(arrayList.get(15).toString()),
						Integer.parseInt(arrayList.get(16).toString()), arrayList.get(18).toString(),
						arrayList.get(19).toString(), null, Calendar.getInstance().getTime(),
						Calendar.getInstance().getTime());

				itemInfoDb = tbItemInfoMapper.selectByTaobaoUid(arrayList.get(0).toString());
				if (itemInfoDb == null) {
					tbItemInfoMapper.insert(itemInfo);
				} else {
					itemInfoDb.setCreatetime(Calendar.getInstance().getTime());
					itemInfoDb.setCoupon(couponVal);
					itemInfoDb.setZkFinalPrice(arrayList.get(6).toString());
					itemInfoDb.setVolume(Integer.parseInt(arrayList.get(7).toString()));
					tbItemInfoMapper.updateByPrimaryKey(itemInfoDb);
				}

			}
		}
	}

}
