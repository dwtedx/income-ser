package com.dwtedx.income.service;

import java.util.List;

import com.dwtedx.income.model.BaseModel;
import com.dwtedx.income.model.TopicModel;
import com.dwtedx.income.model.TopicvoteModel;
import com.dwtedx.income.model.TopicvoteresultModel;

public interface IDiTopicService {
	
	public List<TopicModel> findTopics(BaseModel model, int userid);

	public List<TopicvoteModel> seveVoteResult(TopicvoteresultModel body);


}
