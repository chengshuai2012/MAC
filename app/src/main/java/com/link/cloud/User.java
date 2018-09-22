package com.link.cloud;


import com.zitech.framework.SP;

/**
 * Created by ymh on 2016/7/1 0001.
 */
public class User {

    private static final String CHANNEL = "channel";
    private static final String CUSTOMERID = "customerId";
    private static final String MOBILE = "mobile";
    private static final String NICKNAME = "nickName";
    private static final String HEADIMGURL = "headImgUrl";
    private static final String REALNAME = "realName";
    private static final String IDCARD = "idCard";
    private static final String IDCARDIMG1 = "idcardImg1";
    private static final String IDCARDIMG2 = "idcardImg2";
    private static final String REALNAMESTATUS = "realNameStatus";
    private static final String VIP = "vip";
    private static final String TOKEN = "token";
    private static final String VIPEXPENSES = "vipExpenses";//VIP会员费用
    private static final String OUTFEE = "outfee";//提币手续费比例
    private static final String COINPRICE = "coinprice";//虚拟币价格
    private static final String ICONINTRO = "iconintro";//虚拟币介绍
    private static final String ICONQRCODEURL = "iconqrcodeurl";
    private static final String ICONADDRESS = "iconaddress";




    private SP sp;
    private String channel;
    private int customerId;
    private String mobile;
    private String nickName;
    private String headImgUrl;
    private String realName;
    private String idCard;
    private String idcardImg1;
    private String idcardImg2;
    private int realNameStatus;
    private boolean vip;
    private String token;
    private String vipExpenses;
    private String outfee;
    private String coinprice;
    private String iconintro;
    private String iconqrcodeurl;
    private String iconaddress;


    public static User get() {
        return MacApplication.getInstance().getUser();
    }

    public User() {
        super();
        sp = new SP("USER_DATA");
        token = sp.getString(TOKEN, "");
        channel = sp.getString(CHANNEL, "");
        customerId = sp.getInt(CUSTOMERID, 0);
        mobile = sp.getString(MOBILE, "");
        nickName = sp.getString(NICKNAME, "");
        headImgUrl = sp.getString(HEADIMGURL, "");
        realName = sp.getString(REALNAME, "");
        idCard = sp.getString(IDCARD, "");
        idcardImg1 = sp.getString(IDCARDIMG1, "");
        idcardImg2 = sp.getString(IDCARDIMG2, "");
        realNameStatus = sp.getInt(REALNAMESTATUS, 0);
        vip = sp.getBoolean(VIP, false);
        vipExpenses = sp.getString(VIPEXPENSES, "");
        outfee = sp.getString(OUTFEE, "");
        coinprice = sp.getString(COINPRICE, "");
        iconintro = sp.getString(ICONINTRO, "");
        iconqrcodeurl=sp.getString(ICONQRCODEURL,"");
        iconaddress=sp.getString(ICONADDRESS,"");

    }


    public String getIconaddress() {
        return iconaddress;
    }

    public void setIconaddress(String iconaddress) {
        this.iconaddress = iconaddress;
        sp.putString(ICONADDRESS,iconaddress);
    }

    public String getIconqrcodeurl() {
        return iconqrcodeurl;
    }

    public void setIconqrcodeurl(String iconqrcodeurl) {
        this.iconqrcodeurl = iconqrcodeurl;
        sp.putString(ICONQRCODEURL, iconqrcodeurl);
    }

    public String getCoinprice() {
        return coinprice;
    }

    public void setCoinprice(String coinprice) {
        this.coinprice = coinprice;
        sp.putString(COINPRICE, coinprice);
    }

    public String getIconintro() {
        return iconintro;
    }

    public void setIconintro(String iconintro) {
        this.iconintro = iconintro;
        sp.putString(ICONINTRO, iconintro);
    }

    public String getOutfee() {
        return outfee;
    }

    public void setOutfee(String outfee) {
        this.outfee = outfee;
        sp.putString(OUTFEE, outfee);
    }

    public String getVipExpenses() {
        return vipExpenses;
    }

    public void setVipExpenses(String vipExpenses) {
        this.vipExpenses = vipExpenses;
        sp.putString(VIPEXPENSES, vipExpenses);
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
        sp.putString(CHANNEL, channel);
    }

    public int getCustomerId() {
        return customerId;

    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
        sp.putInt(CUSTOMERID, customerId);
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
        sp.putString(MOBILE, mobile);
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
        sp.putString(NICKNAME, nickName);
    }

    public String getHeadImgUrl() {
        return headImgUrl;
    }

    public void setHeadImgUrl(String headImgUrl) {
        this.headImgUrl = headImgUrl;
        sp.putString(HEADIMGURL, headImgUrl);
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
        sp.putString(REALNAME, realName);
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
        sp.putString(IDCARD, idCard);
    }

    public String getIdcardImg1() {
        return idcardImg1;
    }

    public void setIdcardImg1(String idcardImg1) {
        this.idcardImg1 = idcardImg1;
        sp.putString(IDCARDIMG1, idcardImg1);
    }

    public String getIdcardImg2() {
        return idcardImg2;
    }

    public void setIdcardImg2(String idcardImg2) {
        this.idcardImg2 = idcardImg2;
        sp.putString(IDCARDIMG2, idcardImg2);
    }

    public int getRealNameStatus() {
        return realNameStatus;
    }

    public void setRealNameStatus(int realNameStatus) {
        this.realNameStatus = realNameStatus;
        sp.putInt(REALNAMESTATUS, realNameStatus);
    }

    public boolean isVip() {
        return vip;
    }

    public void setVip(boolean vip) {
        this.vip = vip;
        sp.putBoolean(VIP, vip);

    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
        sp.putString(TOKEN, token);
    }


    public void clear() {

        sp.remove(TOKEN);
        token = "";

        sp.remove(CHANNEL);
        channel = "";
        sp.remove(CUSTOMERID);
        customerId = 0;
        sp.remove(MOBILE);
        mobile = "";
        sp.remove(NICKNAME);
        nickName = "";
        sp.remove(HEADIMGURL);
        headImgUrl = "";
        sp.remove(REALNAME);
        realName = "";
        sp.remove(IDCARD);
        idCard = "";
        sp.remove(IDCARDIMG1);
        idcardImg1 = "";
        sp.remove(IDCARDIMG2);
        idcardImg2 = "";
        sp.remove(REALNAMESTATUS);
        realNameStatus = 0;
        sp.remove(VIP);
        vip = false;


    }
}
