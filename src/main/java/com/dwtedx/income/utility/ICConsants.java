package com.dwtedx.income.utility;

public class ICConsants {
	
	public static final int CLIENTTYPE_ANDROID = 1;//1:Android   2:Android Pad    3:iPhone    4:iPad
	public static final int CLIENTTYPE_ANDROID_PAD = 2;//1:Android   2:Android Pad    3:iPhone    4:iPad
	public static final int CLIENTTYPE_IPHONE = 3;//1:Android   2:Android Pad    3:iPhone    4:iPad
	public static final int CLIENTTYPE_IPAD = 4;//1:Android   2:Android Pad    3:iPhone    4:iPad
	
	/// 10000	请求成功
    /// 10001	请求失败
    /// 10002   验证用户token错误
	public static final int ERRORCODE_10000 = 10000;
	public static final int ERRORCODE_10001 = 10001;
	public static final int ERRORCODE_10002 = 10002;
	
	
	public final static int INCOME_ROLE_INCOME = 1;//收入
    public final static int INCOME_ROLE_PAYING = 2;//支出
    public final static int INCOME_ROLE_START = 3;//开始节点
    public final static int INCOME_ROLE_POOL = 4;//开始节点
    public final static int INCOME_ROLE_ADD_TYPE = 5;//添加类型节点

    public final static int INCOME_RECORD_NOT_UPDATE = 1;//未上传
    public final static int INCOME_RECORD_UPDATEED = 2;//已上传
	
	public final static int OTHER_LOGIN_REGISTER = 0;
	public final static int OTHER_LOGIN_WEIXIN = 1;
    public final static int OTHER_LOGIN_QQ = 2;
    public final static int OTHER_LOGIN_SINA = 3;
    
    //七牛图片上传
    public final static String QINIU_ACCESSKEY = "yhhUpgnwLwN2r_O6Keu460-mvn39zAuY8zI6UK6q";
    public final static String QINIU_SECRETKEY = "rZkU4fmmwR3qMAuxJ6U6NWZ3ZR372mGGwEjtR__k";
    public final static String BUCKET_ICHEAD = "ichead";
    public final static String BUCKET_ICIMAGES = "icimages";
    public final static String BUCKET_ICFILE = "icfile";
    public final static String ICHEAD_HTTP_URL = "http://ichead.dwtedx.com/";
    public final static String ITOPIC_HTTP_URL = "http://icimages.dwtedx.com";
    public final static String ICFILE_HTTP_URL = "http://icfile.dwtedx.com";
    
    
    public final static int INCOME_RECORD_TYPE_0 = 0;//普通记账
    public final static int INCOME_RECORD_TYPE_1 = 1;//扫单

    public final static int DELETEFALAG_NOTDELETE = 0;
    public final static int DELETEFALAG_DELETEED = 1;//删除
    
    
    //1：话题  2：投票
    public final static int TOPIC_TYPE_TALK = 1;
    public final static int TOPIC_TYPE_VOTE = 2;
    
    //0:充值VIp 1:活动获得VIP
    public final static int VIP_TYPE_BUY = 0;
    public final static int VIP_TYPE_GIVE = 1;

}
