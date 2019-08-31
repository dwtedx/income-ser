package com.dwtedx.income.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dwtedx.income.exception.DiException;
import com.dwtedx.income.model.TaobaoActivityInfo;
import com.dwtedx.income.model.TaobaoModel;
import com.dwtedx.income.model.common.MessageInfo;
import com.dwtedx.income.model.common.ResultInfo;
import com.dwtedx.income.pojo.TbCategoryInfo;
import com.dwtedx.income.pojo.TbItemInfo;
import com.dwtedx.income.service.ITbActivityService;
import com.dwtedx.income.service.ITbTaobaoService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.TbkItemGetRequest;
import com.taobao.api.request.TbkItemRecommendGetRequest;
import com.taobao.api.response.TbkItemGetResponse;
import com.taobao.api.response.TbkItemRecommendGetResponse;

@Controller
@RequestMapping("/taobao")
public class DiTaobaoExcelController {
	
	private static final String AppKey = "24634592";
	private static final String AppSecret = "8fcac7917f70ef317fb679611f70c04c";

	@Resource
	private ITbTaobaoService tbTaobaoService;
	@Resource
	private ITbActivityService tbActivityService;
	
	@ResponseBody
	@RequestMapping(value = "/search", method = RequestMethod.POST) 
	public ResultInfo toSearch(@RequestBody MessageInfo<TaobaoModel> model) throws ApiException, IOException{
		
		long page = (model.getBody().getStart() / 20) + 1;
		
		//http://gw.api.taobao.com/router/rest?sign=0200F8088A292ABE1E6ED44EBDF7EFDA&timestamp=2017-10-11+15%3A49%3A31&v=2.0&app_key=24634592&method=taobao.tbk.item.get&partner_id=top-apitools&format=json&cat=16%2C18&q=%E5%A5%B3%E8%A3%85&force_sensitive_param_fuzzy=true&fields=num_iid%2Ctitle%2Cpict_url%2Csmall_images%2Creserve_price%2Czk_final_price%2Cuser_type%2Cprovcity%2Citem_url%2Cseller_id%2Cvolume%2Cnick
			
		TaobaoClient client = new DefaultTaobaoClient("http://gw.api.taobao.com/router/rest", AppKey, AppSecret);
		TbkItemGetRequest req = new TbkItemGetRequest();
		req.setFields("num_iid,title,pict_url,small_images,reserve_price,zk_final_price,user_type,provcity,item_url,seller_id,volume,nick");
		req.setQ(model.getBody().getSearch());
		//req.setQ("9.9包邮");
		//req.setCat("16,18");
		//req.setItemloc("杭州");
		req.setSort("tk_total_commi");
		//req.setIsTmall(false);
		//req.setIsOverseas(false);
		//req.setStartPrice(10L);
		//req.setEndPrice(10L);
		//req.setStartTkRate(123L);
		//req.setEndTkRate(123L);
		//req.setPlatform(1L);
		req.setPageNo(page);
		//req.setPageSize(20L);
		TbkItemGetResponse rsp = client.execute(req);
		//System.out.println(rsp.getBody());
		
		JsonNode results = null;
		try{
			// 准备工作 传入vo请参照第一篇里面的实体。此处不再重新贴上代码 浪费大家时间
			ObjectMapper mapper = new ObjectMapper();
			JsonNode node = mapper.readTree(rsp.getBody());// 这里的JsonNode和XML里面的Node很像
			results =  node.get("tbk_item_get_response").get("results").get("n_tbk_item");
		}catch (Exception e) {
			e.printStackTrace();
		}

		ResultInfo resultInfo = new ResultInfo();
		resultInfo.setBody(results);
		return resultInfo;
	}
	
	@ResponseBody
	@RequestMapping(value = "/recommend", method = RequestMethod.POST) 
	public ResultInfo toRecommend(@RequestBody MessageInfo<TaobaoModel> model) throws ApiException, IOException{
		
		TaobaoClient client = new DefaultTaobaoClient("http://gw.api.taobao.com/router/rest", AppKey, AppSecret);
		TbkItemRecommendGetRequest req = new TbkItemRecommendGetRequest();
		req.setFields("num_iid,title,pict_url,small_images,reserve_price,zk_final_price,user_type,provcity,item_url,seller_id,volume,nick");
		req.setNumIid(model.getBody().getNumid());
		TbkItemRecommendGetResponse response = client.execute(req);
		
		JsonNode results = null;
		try{
			// 准备工作 传入vo请参照第一篇里面的实体。此处不再重新贴上代码 浪费大家时间
			ObjectMapper mapper = new ObjectMapper();
			JsonNode node = mapper.readTree(response.getBody());// 这里的JsonNode和XML里面的Node很像
			results =  node.get("tbk_item_recommend_get_response").get("results").get("n_tbk_item");
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		ResultInfo resultInfo = new ResultInfo();
		resultInfo.setBody(results);
		return resultInfo;
	}
	
	@ResponseBody
	@RequestMapping(value = "/toptaobaoiteminfo", method = RequestMethod.POST) 
	public ResultInfo toTopTaobaoItemInfo(@RequestBody MessageInfo<TaobaoModel> model) throws DiException{
		
		List<TbItemInfo> tbItemInfoList = tbTaobaoService.getTopTaobaoItemInfo(model.getBody());

		ResultInfo resultInfo = new ResultInfo();
		resultInfo.setBody(tbItemInfoList);
		return resultInfo;
	}
	
	@ResponseBody
	@RequestMapping(value = "/nineninetaobaoitem", method = RequestMethod.POST) 
	public ResultInfo toNineNineTaobaoItem(@RequestBody MessageInfo<TaobaoModel> model) throws DiException{
		
		List<TbItemInfo> tbItemInfoList = tbTaobaoService.getNineNineTaobaoItem(model.getBody());
		
		ResultInfo resultInfo = new ResultInfo();
		resultInfo.setBody(tbItemInfoList);
		return resultInfo;
	}
	
	@ResponseBody
	@RequestMapping(value = "/womensclothingitem", method = RequestMethod.POST) 
	public ResultInfo toWomensClothingItem(@RequestBody MessageInfo<TaobaoModel> model) throws DiException{
		
		List<TbItemInfo> tbItemInfoList = tbTaobaoService.getWomensClothingItem(model.getBody());
		
		ResultInfo resultInfo = new ResultInfo();
		resultInfo.setBody(tbItemInfoList);
		return resultInfo;
	}
	
	@ResponseBody
	@RequestMapping(value = "/couponitem", method = RequestMethod.POST) 
	public ResultInfo toCouponItem(@RequestBody MessageInfo<TaobaoModel> model) throws DiException{
		
		List<TbItemInfo> tbItemInfoList = tbTaobaoService.getCouponItem(model.getBody());
		
		ResultInfo resultInfo = new ResultInfo();
		resultInfo.setBody(tbItemInfoList);
		return resultInfo;
	}
	
	@ResponseBody
	@RequestMapping(value = "/manclothingitem", method = RequestMethod.POST) 
	public ResultInfo toManClothingItem(@RequestBody MessageInfo<TaobaoModel> model) throws DiException{
		
		List<TbItemInfo> tbItemInfoList = tbTaobaoService.getManClothingItem(model.getBody());
		
		ResultInfo resultInfo = new ResultInfo();
		resultInfo.setBody(tbItemInfoList);
		return resultInfo;
	}
	
	@ResponseBody
	@RequestMapping(value = "/snacksitem", method = RequestMethod.POST) 
	public ResultInfo toSnacksItem(@RequestBody MessageInfo<TaobaoModel> model) throws DiException{
		
		List<TbItemInfo> tbItemInfoList = tbTaobaoService.getSnacksItem(model.getBody());
		
		ResultInfo resultInfo = new ResultInfo();
		resultInfo.setBody(tbItemInfoList);
		return resultInfo;
	}
	
	@ResponseBody
	@RequestMapping(value = "/makeupsitem", method = RequestMethod.POST) 
	public ResultInfo toMakeupsItem(@RequestBody MessageInfo<TaobaoModel> model) throws DiException{
		
		List<TbItemInfo> tbItemInfoList = tbTaobaoService.getMakeupsItem(model.getBody());
		
		ResultInfo resultInfo = new ResultInfo();
		resultInfo.setBody(tbItemInfoList);
		return resultInfo;
	}
	
	@ResponseBody
	@RequestMapping(value = "/digitalitem", method = RequestMethod.POST) 
	public ResultInfo toDigitalItem(@RequestBody MessageInfo<TaobaoModel> model) throws DiException{
		
		List<TbItemInfo> tbItemInfoList = tbTaobaoService.getDigitalItem(model.getBody());
		
		ResultInfo resultInfo = new ResultInfo();
		resultInfo.setBody(tbItemInfoList);
		return resultInfo;
	}
	
	@ResponseBody
	@RequestMapping(value = "/underwearitem", method = RequestMethod.POST) 
	public ResultInfo toUnderwearItem(@RequestBody MessageInfo<TaobaoModel> model) throws DiException{
		
		List<TbItemInfo> tbItemInfoList = tbTaobaoService.getUnderwearItem(model.getBody());
		
		ResultInfo resultInfo = new ResultInfo();
		resultInfo.setBody(tbItemInfoList);
		return resultInfo;
	}
	
	@ResponseBody
	@RequestMapping(value = "/childrenitem", method = RequestMethod.POST) 
	public ResultInfo toChildrenItem(@RequestBody MessageInfo<TaobaoModel> model) throws DiException{
		
		List<TbItemInfo> tbItemInfoList = tbTaobaoService.getChildrenItem(model.getBody());
		
		ResultInfo resultInfo = new ResultInfo();
		resultInfo.setBody(tbItemInfoList);
		return resultInfo;
	}
	
	@ResponseBody
	@RequestMapping(value = "/motherbabyitem", method = RequestMethod.POST) 
	public ResultInfo toMotherBabyitemItem(@RequestBody MessageInfo<TaobaoModel> model) throws DiException{
		
		List<TbItemInfo> tbItemInfoList = tbTaobaoService.getMotherBabyItem(model.getBody());
		
		ResultInfo resultInfo = new ResultInfo();
		resultInfo.setBody(tbItemInfoList);
		return resultInfo;
	}
	
	@ResponseBody
	@RequestMapping(value = "/livehomeitem", method = RequestMethod.POST) 
	public ResultInfo toLiveHomeItem(@RequestBody MessageInfo<TaobaoModel> model) throws DiException{
		
		List<TbItemInfo> tbItemInfoList = tbTaobaoService.getLiveHomeItem(model.getBody());
		
		ResultInfo resultInfo = new ResultInfo();
		resultInfo.setBody(tbItemInfoList);
		return resultInfo;
	}
	
	@ResponseBody
	@RequestMapping(value = "/luggageitem", method = RequestMethod.POST) 
	public ResultInfo toLuggageItem(@RequestBody MessageInfo<TaobaoModel> model) throws DiException{
		
		List<TbItemInfo> tbItemInfoList = tbTaobaoService.getLuggageItem(model.getBody());
		
		ResultInfo resultInfo = new ResultInfo();
		resultInfo.setBody(tbItemInfoList);
		return resultInfo;
	}
	
	@ResponseBody
	@RequestMapping(value = "/otheritem", method = RequestMethod.POST) 
	public ResultInfo toOtherItem(@RequestBody MessageInfo<TaobaoModel> model) throws DiException{
		
		List<TbItemInfo> tbItemInfoList = tbTaobaoService.getOtherItem(model.getBody());
		
		ResultInfo resultInfo = new ResultInfo();
		resultInfo.setBody(tbItemInfoList);
		return resultInfo;
	}
	
	@ResponseBody
	@RequestMapping(value = "/randitem", method = RequestMethod.POST) 
	public ResultInfo Rand(@RequestBody MessageInfo<TaobaoModel> model) throws DiException{
		
		List<TbItemInfo> tbItemInfoList = tbTaobaoService.getRandItem(model.getBody());
		
		ResultInfo resultInfo = new ResultInfo();
		resultInfo.setBody(tbItemInfoList);
		return resultInfo;
	}
	
	@ResponseBody
	@RequestMapping(value = "/taobaoactivityinfo", method = RequestMethod.POST) 
	public ResultInfo toTaobaoActivityInfo(@RequestBody MessageInfo<TaobaoModel> model) throws DiException{
		
		TaobaoActivityInfo taobaoActivityInfo = tbActivityService.selectByLastActivity();
		
		ResultInfo resultInfo = new ResultInfo();
		resultInfo.setBody(taobaoActivityInfo);
		return resultInfo;
	}
	
	@ResponseBody
	@RequestMapping(value = "/categorytop", method = RequestMethod.POST) 
	public ResultInfo toCategoryTop(@RequestBody MessageInfo<TaobaoModel> model) throws DiException{
		
		List<TbCategoryInfo> tbItemInfoList = tbTaobaoService.getCategoryTop(model.getBody());
		
		ResultInfo resultInfo = new ResultInfo();
		resultInfo.setBody(tbItemInfoList);
		return resultInfo;
	}
	
	@ResponseBody
	@RequestMapping(value = "/categoryiditem", method = RequestMethod.POST) 
	public ResultInfo toCategoryIdItem(@RequestBody MessageInfo<TaobaoModel> model) throws DiException{
		
		List<TbItemInfo> tbItemInfoList = tbTaobaoService.getCategoryIdItem(model.getBody());
		
		ResultInfo resultInfo = new ResultInfo();
		resultInfo.setBody(tbItemInfoList);
		return resultInfo;
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/refreshtaobaoiteminfo", method = RequestMethod.POST) 
	public ResultInfo toRefreshTaobaoItemInfo(@RequestBody MessageInfo<TaobaoModel> model) throws DiException, IOException{

		tbTaobaoService.refreshTaobaoItemInfo(model.getBody());

		ResultInfo resultInfo = new ResultInfo();
		return resultInfo;
	}
	
}
