package com.link.cloud.api.bean;

/**
 * Created by chengshuai on 2019/4/10.
 */

public class RequestBindFace {
    String phone;
    String faceBase64;
    String deviceId;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFaceBase64() {
        return faceBase64;
    }

    public void setFaceBase64(String faceBase64) {
        this.faceBase64 = faceBase64;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }
}
