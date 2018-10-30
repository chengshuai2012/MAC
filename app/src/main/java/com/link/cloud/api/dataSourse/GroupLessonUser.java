package com.link.cloud.api.dataSourse;

import java.util.ArrayList;

import io.realm.RealmList;

/**
 * Created by 49488 on 2018/10/21.
 */

public class GroupLessonUser {
    private ArrayList<UserInfosBean> userInfos;
    private RealmList<com.link.cloud.bean.GroupLessonUser> fingerprints;

    public ArrayList<UserInfosBean> getUserInfos() {
        return userInfos;
    }

    public void setUserInfos(ArrayList<UserInfosBean> userInfos) {
        this.userInfos = userInfos;
    }

    public RealmList<com.link.cloud.bean.GroupLessonUser> getFingerprints() {
        return fingerprints;
    }

    public void setFingerprints(RealmList<com.link.cloud.bean.GroupLessonUser> fingerprints) {
        this.fingerprints = fingerprints;
    }

    public static class UserInfosBean {

        /**
         * id : 29
         * merchantId : 1
         * uid : 6csld1v8
         * uuid : ccbe4f454ffc4e64a7644c8c9409edf9
         * phone : 18574107629
         * sex : 1
         * headImg :
         * userType : 1
         * status : 1
         * createTime : 2018-10-30 14:51:03
         * nickname : 晟敏
         * isadmin : 0
         * admission : 0
         * bookid : oznliemq
         */

        private int id;
        private int merchantId;
        private String uid;
        private String uuid;
        private String phone;
        private int sex;
        private String headImg;
        private int userType;
        private int status;
        private String createTime;
        private String nickname;
        private int isadmin;
        private int admission;
        private String bookid;

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

        public int getIsadmin() {
            return isadmin;
        }

        public void setIsadmin(int isadmin) {
            this.isadmin = isadmin;
        }

        public int getAdmission() {
            return admission;
        }

        public void setAdmission(int admission) {
            this.admission = admission;
        }

        public String getBookid() {
            return bookid;
        }

        public void setBookid(String bookid) {
            this.bookid = bookid;
        }
    }

}
