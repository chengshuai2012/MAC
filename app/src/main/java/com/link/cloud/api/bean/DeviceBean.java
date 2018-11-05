package com.link.cloud.api.bean;

/**
 * Created by 49488 on 2018/11/5.
 */

public class DeviceBean {


    /**
     * deviceInfo : {"id":7,"code":"12:F9:99:6A:2E:54","deviceModelId":1,"deviceTypeId":1001,"deviceVersionId":1,"branchId":1,"status":1,"networkingStatus":2,"createTime":"2018-11-05 10:27:59","merchantId":1,"bind":true,"endTime":"2019-01-31 00:00:00","startTime":"2018-11-05 00:00:00","bindType":1,"remark":"测试一体机","merchantCode":"KHJJD653","merchantName":"金证系统指静脉对接","branchCode":"FDBM1236","branchName":"分店B","deviceType":"一体机","deviceModel":"SX1527","deviceVersion":"1.0.0","pw":null}
     * token : eyJhbGciOiJIUzUxMiJ9.eyJsb2dpblR5cGUiOjIsImp0aSI6IjEyOkY5Ojk5OjZBOjJFOjU0IiwiaXAiOiIxMTMuMTEwLjIzMC4xMiIsInVhIjoiNzdhMzEiLCJub25jZSI6ImZ2dVFuWnBLIiwiaWF0IjoxNTQxNDEzMDg4LCJleHAiOjE1NDE0OTk0ODh9.RbtgOZMrQCk5UIivR3t0DBfnKeGUUjlflT1XayQigj_SMlmJPBlsE_cgf8JnmaWlv5-8hUp4DzzYx8kvm-QNVg
     */

    private DeviceInfoBean deviceInfo;
    private String token;

    public DeviceInfoBean getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(DeviceInfoBean deviceInfo) {
        this.deviceInfo = deviceInfo;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public static class DeviceInfoBean {
        /**
         * id : 7
         * code : 12:F9:99:6A:2E:54
         * deviceModelId : 1
         * deviceTypeId : 1001
         * deviceVersionId : 1
         * branchId : 1
         * status : 1
         * networkingStatus : 2
         * createTime : 2018-11-05 10:27:59
         * merchantId : 1
         * bind : true
         * endTime : 2019-01-31 00:00:00
         * startTime : 2018-11-05 00:00:00
         * bindType : 1
         * remark : 测试一体机
         * merchantCode : KHJJD653
         * merchantName : 金证系统指静脉对接
         * branchCode : FDBM1236
         * branchName : 分店B
         * deviceType : 一体机
         * deviceModel : SX1527
         * deviceVersion : 1.0.0
         * pw : null
         */

        private int id;
        private String code;
        private int deviceModelId;
        private int deviceTypeId;
        private int deviceVersionId;
        private int branchId;
        private int status;
        private int networkingStatus;
        private String createTime;
        private int merchantId;
        private boolean bind;
        private String endTime;
        private String startTime;
        private int bindType;
        private String remark;
        private String merchantCode;
        private String merchantName;
        private String branchCode;
        private String branchName;
        private String deviceType;
        private String deviceModel;
        private String deviceVersion;
        private Object pw;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public int getDeviceModelId() {
            return deviceModelId;
        }

        public void setDeviceModelId(int deviceModelId) {
            this.deviceModelId = deviceModelId;
        }

        public int getDeviceTypeId() {
            return deviceTypeId;
        }

        public void setDeviceTypeId(int deviceTypeId) {
            this.deviceTypeId = deviceTypeId;
        }

        public int getDeviceVersionId() {
            return deviceVersionId;
        }

        public void setDeviceVersionId(int deviceVersionId) {
            this.deviceVersionId = deviceVersionId;
        }

        public int getBranchId() {
            return branchId;
        }

        public void setBranchId(int branchId) {
            this.branchId = branchId;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getNetworkingStatus() {
            return networkingStatus;
        }

        public void setNetworkingStatus(int networkingStatus) {
            this.networkingStatus = networkingStatus;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public int getMerchantId() {
            return merchantId;
        }

        public void setMerchantId(int merchantId) {
            this.merchantId = merchantId;
        }

        public boolean isBind() {
            return bind;
        }

        public void setBind(boolean bind) {
            this.bind = bind;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public int getBindType() {
            return bindType;
        }

        public void setBindType(int bindType) {
            this.bindType = bindType;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getMerchantCode() {
            return merchantCode;
        }

        public void setMerchantCode(String merchantCode) {
            this.merchantCode = merchantCode;
        }

        public String getMerchantName() {
            return merchantName;
        }

        public void setMerchantName(String merchantName) {
            this.merchantName = merchantName;
        }

        public String getBranchCode() {
            return branchCode;
        }

        public void setBranchCode(String branchCode) {
            this.branchCode = branchCode;
        }

        public String getBranchName() {
            return branchName;
        }

        public void setBranchName(String branchName) {
            this.branchName = branchName;
        }

        public String getDeviceType() {
            return deviceType;
        }

        public void setDeviceType(String deviceType) {
            this.deviceType = deviceType;
        }

        public String getDeviceModel() {
            return deviceModel;
        }

        public void setDeviceModel(String deviceModel) {
            this.deviceModel = deviceModel;
        }

        public String getDeviceVersion() {
            return deviceVersion;
        }

        public void setDeviceVersion(String deviceVersion) {
            this.deviceVersion = deviceVersion;
        }

        public Object getPw() {
            return pw;
        }

        public void setPw(Object pw) {
            this.pw = pw;
        }
    }
}
