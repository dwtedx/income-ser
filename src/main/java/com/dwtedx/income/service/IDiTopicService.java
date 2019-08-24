package com.dwtedx.income.service;

import java.util.List;

import com.dwtedx.income.model.BaseModel;
import com.dwtedx.income.model.TopicModel;

public interface IDiTopicService {
	
	public List<TopicModel> findTopics(BaseModel model, int userid);


}
