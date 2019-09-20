package com.dwtedx.income.model;

/**
 * Created by dwtedx(qinyl dwtedx.com) on 17/10/11.
 * Company 路之遥网络科技有限公司
 * Description 类用途 (TODO)
 */

public class TaobaoSearchItemModel {

    /**
     * item_url : http://item.taobao.com/item.htm?id=556567547234
     * nick : angle792
     * num_iid : 556567547234
     * pict_url : http://img4.tbcdn.cn/tfscom/i1/2813979144/TB2C8EBXd_gFuJjy0FeXXapjFXa_!!2813979144.jpg
     * provcity : 广东 广州
     * reserve_price : 108.00
     * seller_id : 2813979144
     * small_images : {"string":["http://img2.tbcdn.cn/tfscom/i2/2813979144/TB2OxsEXgzeFuJjy0FjXXb2ppXa_!!2813979144.jpg","http://img3.tbcdn.cn/tfscom/i3/2813979144/TB2dbsDXczfFuJjy0FhXXbxmFXa_!!2813979144.jpg","http://img4.tbcdn.cn/tfscom/i4/2813979144/TB2XikCXnHfFuJjSszbXXcAwFXa_!!2813979144.jpg","http://img4.tbcdn.cn/tfscom/i1/2813979144/TB2F3.DXcbfFuJjy1zdXXa1gVXa_!!2813979144.jpg"]}
     * title : 喇叭裤夏女高腰 韩版学生显瘦2017新款微喇黑色ulzzang九分牛仔裤
     * user_type : 0
     * volume : 7162
     * zk_final_price : 59.00
     */

    private String item_url;
    private String nick;
    private String num_iid;
    private String pict_url;
    private String provcity;
    private String reserve_price;
    private String seller_id;
    private String title;
    private int user_type;
    private int volume;
    private String zk_final_price;
    private String tag_content;

    public String getItem_url() {
        return item_url;
    }

    public void setItem_url(String item_url) {
        this.item_url = item_url;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getNum_iid() {
        return num_iid;
    }

    public void setNum_iid(String num_iid) {
        this.num_iid = num_iid;
    }

    public String getPict_url() {
        return pict_url;
    }

    public void setPict_url(String pict_url) {
        this.pict_url = pict_url;
    }

    public String getProvcity() {
        return provcity;
    }

    public void setProvcity(String provcity) {
        this.provcity = provcity;
    }

    public String getReserve_price() {
        return reserve_price;
    }

    public void setReserve_price(String reserve_price) {
        this.reserve_price = reserve_price;
    }

    public String getSeller_id() {
        return seller_id;
    }

    public void setSeller_id(String seller_id) {
        this.seller_id = seller_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getUser_type() {
        return user_type;
    }

    public void setUser_type(int user_type) {
        this.user_type = user_type;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public String getZk_final_price() {
        return zk_final_price;
    }

    public void setZk_final_price(String zk_final_price) {
        this.zk_final_price = zk_final_price;
    }

    public String getTag_content() {
        return tag_content;
    }

    public void setTag_content(String tag_content) {
        this.tag_content = tag_content;
    }
}
