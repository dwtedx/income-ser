package com.dwtedx.income.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import com.dwtedx.income.dao.IDiTopicMapper;
import com.dwtedx.income.dao.IDiTopicimgMapper;
import com.dwtedx.income.dao.IDiTopiclikeMapper;
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
import com.dwtedx.income.pojo.DiTopiclike;
import com.dwtedx.income.pojo.DiTopicvote;
import com.dwtedx.income.pojo.DiTopicvoteresult;
import com.dwtedx.income.pojo.DiUserInfo;
import com.dwtedx.income.service.IDiTopicService;
import com.dwtedx.income.utility.Base64ImageUtility;
import com.dwtedx.income.utility.CommonUtility;
import com.dwtedx.income.utility.ICConsants;
import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;

@Service("diTopicService")
public class DiTopicServiceImpl implements IDiTopicService {

	@Resource
	private IDiUserInfoMapper diUserInfoMapper;
	@Resource
	private IDiTopicMapper diTopicMapper;
	@Resource
	private IDiTopicimgMapper diTopicimgMapper;
	@Resource
	private IDiTopiclikeMapper diTopiclikeMapper;
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
	public TopicimgModel uploadImg(String path, int userId) throws DiException {
		byte[] uploadBytes = Base64ImageUtility.generateImage(path);
		if (null == path || null == uploadBytes) {
			throw new DiException("图片数据异常");
		}

		// 构造一个带指定Zone对象的配置类
		Configuration cfg = new Configuration(Zone.zone2());
		// ...其他参数参考类注释
		UploadManager uploadManager = new UploadManager(cfg);
		// ...生成上传凭证，然后准备上传
		
		// 默认不指定key的情况下，以文件内容的hash值作为文件名
		String key = CommonUtility.getTempImageName(userId, "jpg");

		Auth auth = Auth.create(ICConsants.QINIU_ACCESSKEY, ICConsants.QINIU_SECRETKEY);
		String upToken = auth.uploadToken(ICConsants.BUCKET_ICIMAGES);
		
		DefaultPutRet putRet = null;
		try {
			Response response = uploadManager.put(uploadBytes, key, upToken);
			// 解析上传成功的结果
			putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
			System.out.println(putRet.key);
			System.out.println(putRet.hash);
		} catch (QiniuException ex) {
			Response r = ex.response;
			System.err.println(r.toString());
			throw new DiException(r.toString());
		}
		
		TopicimgModel topicimgModel = new TopicimgModel();
		topicimgModel.setPath("/" + putRet.key);		
		return topicimgModel;
	}

	@Override
	public void seveTopic(TopicModel model) throws DiException {
		//保存topic
		if(model.getUserid() == 0) {
			throw new DiException("请先登录");
		}
		if(CommonUtility.isEmpty(model.getDescription())) {
			throw new DiException("内容不能为空");
		}
		
		DiTopic pojo = modelMapper.map(model, DiTopic.class);
		//是否有投票
		if(null != model.getTopicvote() && model.getTopicvote().size() > 0) {
			pojo.setType(ICConsants.TOPIC_TYPE_VOTE);
		}else {
			pojo.setType(ICConsants.TOPIC_TYPE_TALK);
		}
		pojo.setCreatetime(new Date());
		int result = diTopicMapper.insertSelective(pojo);
		//保存图片
		if(result > 0 && null != model.getTopicimg() && model.getTopicimg().size() > 0) {
			for (TopicimgModel topicimgModel : model.getTopicimg()) {
				DiTopicimg topicimg = new DiTopicimg();
				topicimg.setPath(topicimgModel.getPath());
				topicimg.setWidth(topicimgModel.getWidth());
				topicimg.setHeight(topicimgModel.getHeight());
				topicimg.setTopicid(pojo.getId());
				diTopicimgMapper.insertSelective(topicimg);
			}
		}
		//保存投票项目
		if(result > 0 && null != model.getTopicvote() && model.getTopicvote().size() > 0) {
			for (TopicvoteModel topicvoteModel : model.getTopicvote()) {
				DiTopicvote topicvote = new DiTopicvote();
				topicvote.setName(topicvoteModel.getName());
				topicvote.setTopicid(pojo.getId());
				diTopicvoteMapper.insertSelective(topicvote);
			}
		}
		
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
	public void seveTopicLiked(int id, int userid) throws DiException {
		//是否已经点赞
		List<DiTopiclike> topiclikes = diTopiclikeMapper.selectDiTopiclikeByUserId(id, userid);
		if(null != topiclikes && topiclikes.size() > 0) {
			throw new DiException("不能重复点赞哦");
		}
		DiTopiclike pojoLike = new DiTopiclike();
		pojoLike.setTopicid(id);
		pojoLike.setUserid(userid);
		diTopiclikeMapper.insertSelective(pojoLike);
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
