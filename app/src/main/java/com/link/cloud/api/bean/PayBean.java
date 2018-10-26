package com.link.cloud.api.bean;

/**
 * Created by 49488 on 2018/10/26.
 */

public class PayBean {

    /**
     * data : {"userInfo":{"createTime":1540542728000,"headImg":"","id":28,"merchantId":1,"nickname":"晟敏","phone":"13726261348","sex":1,"status":1,"uid":"vsco1xsn","userType":1,"uuid":"5222c038f2354154aa9bdbd87ad9537f"},"courseType":"1","courseReleasePkcode":"t4zaiv3u","deviceId":"HJKF"}
     * msgType : BUY_COURSE_NOTIFY
     */

    private DataBean data;
    private String msgType;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public static class DataBean {
        /**
         * userInfo : {"createTime":1540542728000,"headImg":"","id":28,"merchantId":1,"nickname":"晟敏","phone":"13726261348","sex":1,"status":1,"uid":"vsco1xsn","userType":1,"uuid":"5222c038f2354154aa9bdbd87ad9537f"}
         * courseType : 1
         * courseReleasePkcode : t4zaiv3u
         * deviceId : HJKF
         */

        private UserInfoBean userInfo;
        private String courseType;
        private String courseReleasePkcode;
        private String deviceId;

        public UserInfoBean getUserInfo() {
            return userInfo;
        }

        public void setUserInfo(UserInfoBean userInfo) {
            this.userInfo = userInfo;
        }

        public String getCourseType() {
            return courseType;
        }

        public void setCourseType(String courseType) {
            this.courseType = courseType;
        }

        public String getCourseReleasePkcode() {
            return courseReleasePkcode;
        }

        public void setCourseReleasePkcode(String courseReleasePkcode) {
            this.courseReleasePkcode = courseReleasePkcode;
        }

        public String getDeviceId() {
            return deviceId;
        }

        public void setDeviceId(String deviceId) {
            this.deviceId = deviceId;
        }

        public static class UserInfoBean {
            /**
             * createTime : 1540542728000
             * headImg :
             * id : 28
             * merchantId : 1
             * nickname : 晟敏
             * phone : 13726261348
             * sex : 1
             * status : 1
             * uid : vsco1xsn
             * userType : 1
             * uuid : 5222c038f2354154aa9bdbd87ad9537f
             */

            private long createTime;
            private String headImg;
            private int id;
            private int merchantId;
            private String nickname;
            private String phone;
            private int sex;
            private int status;
            private String uid;
            private int userType;
            private String uuid;

            public long getCreateTime() {
                return createTime;
            }

            public void setCreateTime(long createTime) {
                this.createTime = createTime;
            }

            public String getHeadImg() {
                return headImg;
            }

            public void setHeadImg(String headImg) {
                this.headImg = headImg;
            }

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

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
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

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getUid() {
                return uid;
            }

            public void setUid(String uid) {
                this.uid = uid;
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
    }
}
