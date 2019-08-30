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
import com.dwtedx.income.dao.IDiTopicvoteMapper;
import com.dwtedx.income.dao.IDiTopicvoteresultMapper;
import com.dwtedx.income.dao.IDiUserInfoMapper;
import com.dwtedx.income.exception.DiException;
import com.dwtedx.income.model.BaseModel;
import com.dwtedx.income.model.TopicModel;
import com.dwtedx.income.model.TopicimgModel;
import com.dwtedx.income.model.TopicvoteModel;
import com.dwtedx.income.model.TopicvoteresultModel;
import com.dwtedx.income.pojo.DiTopic;
import com.dwtedx.income.pojo.DiTopicimg;
import com.dwtedx.income.pojo.DiTopicvote;
import com.dwtedx.income.pojo.DiTopicvoteresult;
import com.dwtedx.income.pojo.DiUserInfo;
import com.dwtedx.income.service.IDiTopicService;
import com.dwtedx.income.utility.CommonUtility;

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
	@Resource
	private IDiTopicvoteMapper diTopicvoteMapper;
	@Resource
	private IDiTopicvoteresultMapper diTopicvoteresultMapper;
	
	private ModelMapper modelMapper;
	
	public DiTopicServiceImpl() {
		modelMapper = new ModelMapper();
	}

	@Override
	public List<TopicModel> findTopics(BaseModel bodymodel, int userid) {
		List<DiTopic> pojos = diTopicMapper.selectTopics(bodymodel.getStart(), bodymodel.getLength());
		
		List<TopicModel> models = new ArrayList<TopicModel>();
		TopicModel model = null;
		List<DiTopicimg> topicimgs;
		DiUserInfo userInfo;
		//models = modelMapper.map(itemlist, new TypeToken<List<TopicModel>>() {}.getType());
		for (DiTopic pojo : pojos) {
			model = modelMapper.map(pojo, TopicModel.class);
			//查找用户
			userInfo = diUserInfoMapper.selectByPrimaryKey(pojo.getUserid());
			model.setUsername(userInfo.getName());
			model.setUserpath(userInfo.getHead());
			//查找图片
			topicimgs = diTopicimgMapper.selectInsTopicimgs(pojo.getId());
			model.setTopicimg(modelMapper.map(topicimgs, new TypeToken<List<TopicimgModel>>() {}.getType()));
			//查询回复数量
			model.setTalkcount(diTopictalkMapper.selectTopictalkCount(pojo.getId()));
			
			//查找投票
			//是否投过票
			List<DiTopicvoteresult>  topicvoteresults =  diTopicvoteresultMapper.selectInsTopicvoteresultByUserId(pojo.getId(), userid);
			if(null != topicvoteresults && topicvoteresults.size() > 0) {
				model.setVoted(true);
			}
			
			//投票结果
			int allNum = diTopicvoteresultMapper.selectInsTopicvoteresultCount(pojo.getId(), 0);
			model.setVotecount(allNum);
			
			List<TopicvoteModel> topicvoteModels = new ArrayList<TopicvoteModel>();
			TopicvoteModel  topicvoteModel;
			List<DiTopicvote> topicvotes = diTopicvoteMapper.selectInsTopicvoteByTopicId(pojo.getId());
			for (DiTopicvote topicvote : topicvotes) {
				topicvoteModel = modelMapper.map(topicvote, TopicvoteModel.class);
				//当前用户是否已投票
				for(DiTopicvoteresult voteresult : topicvoteresults) {
					if(topicvote.getId() == voteresult.getTopicvoteid()) {
						topicvoteModel.setChecked(true);
					}
				}
				//计算
				int itemNum = diTopicvoteresultMapper.selectInsTopicvoteresultCount(pojo.getId(), topicvote.getId());
				topicvoteModel.setPersonnum(itemNum);
				if(allNum > 0) {
					float aVal = itemNum / (float)allNum;
					aVal = aVal * 100;
					topicvoteModel.setPercent(CommonUtility.zeroPlaces(aVal));
				}else {
					topicvoteModel.setPercent("0");
				}
				topicvoteModels.add(topicvoteModel);
			}
			model.setTopicvote(topicvoteModels);
			
			models.add(model);
		}
		return models;
	}

	@Override
	public List<TopicvoteModel> seveVoteResult(TopicvoteresultModel body) {
		DiTopicvoteresult pojo = modelMapper.map(body, DiTopicvoteresult.class);
		diTopicvoteresultMapper.insertSelective(pojo);
		
		//查找投票
		//是否投过票
		List<DiTopicvoteresult>  topicvoteresults =  diTopicvoteresultMapper.selectInsTopicvoteresultByUserId(pojo.getTopicid(), pojo.getUserid());
		
		//投票结果
		int allNum = diTopicvoteresultMapper.selectInsTopicvoteresultCount(pojo.getTopicid(), 0);
		
		List<TopicvoteModel> topicvoteModels = new ArrayList<TopicvoteModel>();
		TopicvoteModel  topicvoteModel;
		List<DiTopicvote> topicvotes = diTopicvoteMapper.selectInsTopicvoteByTopicId(pojo.getTopicid());
		for (DiTopicvote topicvote : topicvotes) {
			topicvoteModel = modelMapper.map(topicvote, TopicvoteModel.class);
			//当前用户是否已投票
			for(DiTopicvoteresult voteresult : topicvoteresults) {
				if(topicvote.getId() == voteresult.getTopicvoteid()) {
					topicvoteModel.setChecked(true);
				}
			}
			//计算
			int itemNum = diTopicvoteresultMapper.selectInsTopicvoteresultCount(pojo.getTopicid(), topicvote.getId());
			topicvoteModel.setPersonnum(itemNum);
			if(allNum > 0) {
				float aVal = itemNum / (float)allNum;
				aVal = aVal * 100;
				topicvoteModel.setPercent(CommonUtility.zeroPlaces(aVal));
			}else {
				topicvoteModel.setPercent("0");
			}
			topicvoteModels.add(topicvoteModel);
		}
		return topicvoteModels;
	}

	@Override
	public void seveTopicLiked(int id) throws DiException {
		DiTopic pojo = diTopicMapper.selectByPrimaryKey(id);
		if(null != pojo.getLiked()) {
			pojo.setLiked(pojo.getLiked() + 1);
		}else {
			pojo.setLiked(1);
		}
		int result = diTopicMapper.updateByPrimaryKeySelective(pojo);
		if(0 == result) {
			throw new DiException("点赞失败，请稍后重试");
		}
	}
	


}
