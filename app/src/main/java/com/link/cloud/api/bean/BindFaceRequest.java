package com.link.cloud.api.bean;

/**
 * Created by 49488 on 2018/12/17.
 */

public class BindFaceRequest {

    /**
     * createTime : 2018-12-17T07:00:52.654Z
     * face : string
     * faceBase64 : string
     * id : 0
     * isadmin : 0
     * merchantId : 0
     * phone : string
     * userType : 0
     * uuid : string
     */

    private String createTime;
    private String face;
    private String faceBase64;
    private int id;
    private int isadmin;
    private int merchantId;
    private String phone;
    private int userType;
    private String uuid;

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getFace() {
        return face;
    }

    public void setFace(String face) {
        this.face = face;
    }

    public String getFaceBase64() {
        return faceBase64;
    }

    public void setFaceBase64(String faceBase64) {
        this.faceBase64 = faceBase64;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIsadmin() {
        return isadmin;
    }

    public void setIsadmin(int isadmin) {
        this.isadmin = isadmin;
    }

    public int getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(int merchantId) {
        this.merchantId = merchantId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
