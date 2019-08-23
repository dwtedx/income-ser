package com.dwtedx.income.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import com.dwtedx.income.dao.IDiTopicMapper;
import com.dwtedx.income.dao.IDiTopicimgMapper;
import com.dwtedx.income.dao.IDiTopictalkMapper;
import com.dwtedx.income.dao.IDiUserInfoMapper;
import com.dwtedx.income.model.BaseModel;
import com.dwtedx.income.model.TopicModel;
import com.dwtedx.income.model.TopicimgModel;
import com.dwtedx.income.pojo.DiTopic;
import com.dwtedx.income.pojo.DiTopicimg;
import com.dwtedx.income.pojo.DiUserInfo;
import com.dwtedx.income.service.IDiTopicService;

@Service("diTopicService")
public class DiTopicServiceImpl implements IDiTopicService {

	@Resource
	private IDiUserInfoMapper diUserInfoMapper;
	@Resource
	private IDiTopicMapper diTopicMapper;
	@Resource
	private IDiTopicimgMapper diTopicimgMapper;
	@Resource
	private IDiTopictalkMapper diTopictalkMapper;
	
	private ModelMapper modelMapper;
	
	public DiTopicServiceImpl() {
		modelMapper = new ModelMapper();
	}

	@Override
	public List<TopicModel> findTopics(BaseModel model) {
		List<DiTopic> pojos = diTopicMapper.selectTopics(model.getStart(), model.getLength());
		
		List<TopicModel> models = new ArrayList<TopicModel>();
		TopicModel topicmodel = null;
		List<DiTopicimg> topicimgs;
		DiUserInfo userInfo;
		//models = modelMapper.map(itemlist, new TypeToken<List<TopicModel>>() {}.getType());
		for (DiTopic diTopic : pojos) {
			topicmodel = modelMapper.map(diTopic, TopicModel.class);
			//查找用户
			userInfo = diUserInfoMapper.selectByPrimaryKey(diTopic.getUserid());
			topicmodel.setUsername(userInfo.getName());
			topicmodel.setUserpath(userInfo.getHead());
			//查找图片
			topicimgs = diTopicimgMapper.selectInsTopicimgs(diTopic.getId());
			topicmodel.setTopicimg(modelMapper.map(topicimgs, new TypeToken<List<TopicimgModel>>() {}.getType()));
			//查询回复数量
			topicmodel.setTalkcount(diTopictalkMapper.selectTopictalkCount(diTopic.getId()));
			models.add(topicmodel);
		}
		return models;
	}
	


}
