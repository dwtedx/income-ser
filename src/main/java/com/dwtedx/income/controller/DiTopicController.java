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
import com.dwtedx.income.model.TopicModel;
import com.dwtedx.income.model.TopicvoteModel;
import com.dwtedx.income.model.TopicvoteresultModel;
import com.dwtedx.income.model.common.MessageInfo;
import com.dwtedx.income.model.common.ResultInfo;
import com.dwtedx.income.service.IDiTopicService;

@Controller
@RequestMapping("/topic")
public class DiTopicController {

	@Resource
	private IDiTopicService diTopicService;
	
	@ResponseBody
	@RequestMapping(value = "/index", method = RequestMethod.POST) 
	public ResultInfo toIndex(@RequestBody MessageInfo<BaseModel> model){
			
		List<TopicModel> topics = diTopicService.findTopics(model.getBody(), model.getHead().getUserId());
		
		ResultInfo resultInfo = new ResultInfo();
		resultInfo.setBody(topics);
		return resultInfo;
	}
	
	@ResponseBody
	@RequestMapping(value = "/sevevoteresult", method = RequestMethod.POST) 
	public ResultInfo toSeveVoteResult(@RequestBody MessageInfo<TopicvoteresultModel> model){
			
		List<TopicvoteModel> retult = diTopicService.seveVoteResult(model.getBody());
		
		ResultInfo resultInfo = new ResultInfo();
		resultInfo.setBody(retult);
		return resultInfo;
	}
	
	@ResponseBody
	@RequestMapping(value = "/sevetopicliked", method = RequestMethod.POST) 
	public ResultInfo toSeveTopicLiked(@RequestBody MessageInfo<BaseModel> model) throws DiException{
			
		diTopicService.seveTopicLiked(model.getBody().getId());
		
		ResultInfo resultInfo = new ResultInfo();
		return resultInfo;
	}
	
}
