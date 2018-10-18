package com.link.cloud.api.bean;

/**
 * Created by 49488 on 2018/10/18.
 */

public class UserBindBean {

    /**
     * id : 7
     * merchantId : 1
     * uid : unz4sikj
     * uuid : ee7d803fdaba471fadea907de018f39c
     * phone : 18574107629
     * sex : 1
     * headImg :
     * fingerprint : null
     * userType : 1
     * status : 1
     * createTime : 2018-10-18 17:35:05
     * nickname :
     */

    private int id;
    private int merchantId;
    private String uid;
    private String uuid;
    private String phone;
    private int sex;
    private String headImg;
    private Object fingerprint;
    private int userType;
    private int status;
    private String createTime;
    private String nickname;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(int merchantId) {
        this.merchantId = merchantId;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public Object getFingerprint() {
        return fingerprint;
    }

    public void setFingerprint(Object fingerprint) {
        this.fingerprint = fingerprint;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
