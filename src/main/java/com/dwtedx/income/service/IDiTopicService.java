package com.dwtedx.income.service;

import java.util.List;

import com.dwtedx.income.exception.DiException;
import com.dwtedx.income.model.BaseModel;
import com.dwtedx.income.model.TopicModel;
import com.dwtedx.income.model.TopicimgModel;
import com.dwtedx.income.model.TopictalkModel;
import com.dwtedx.income.model.TopicvoteModel;
import com.dwtedx.income.model.TopicvoteresultModel;

public interface IDiTopicService {
	
	public List<TopicModel> findTopics(BaseModel model, int userid);

	public TopicModel findTopic(int id, int userid) throws DiException;
	
	public TopicimgModel uploadImg(String path, int userId) throws DiException;
	
	public void seveTopic(TopicModel model) throws DiException;
	
	public List<TopicvoteModel> seveVoteResult(TopicvoteresultModel body);

	public void seveTopicLiked(int id, int userid) throws DiException;

	public void seveTopicTalk(TopictalkModel body) throws DiException;
	
	public List<TopicModel> findMyTopics(BaseModel model, int userid);

	public void seveTopicShare(int id, int userId, String sharetype) throws DiException;

}
