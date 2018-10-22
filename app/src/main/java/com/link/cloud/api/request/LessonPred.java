package com.link.cloud.api.request;


import java.util.List;

/**
 * Created by 49488 on 2018/10/21.
 */

public class LessonPred {

    private List<BookBean> book;
    private List<NotbookBean> notbook;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    private User user;
    public List<BookBean> getBook() {
        return book;
    }
    public class User{
        /**
         * phone : 18574107629
         * name : 匿名会员
         * nikename : 匿名会员
         */

        private String phone;
        private String name;
        private String nikename;

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getNikename() {
            return nikename;
        }

        public void setNikename(String nikename) {
            this.nikename = nikename;
        }
    }
    public void setBook(List<BookBean> book) {
        this.book = book;
    }

    public List<NotbookBean> getNotbook() {
        return notbook;
    }

    public void setNotbook(List<NotbookBean> notbook) {
        this.notbook = notbook;
    }

    public static class BookBean {
        /**
         * oneHour : 1
         * coachName : yanqing
         * fitnessCourseName : 散打私教课
         * useNum : 1
         * memberCoursePkcode : 6epu54l8
         * endtime : 2018-10-21 20:13:53
         * coachNikename : yanqing
         * begtime : 2018-10-21 19:13:45
         * remainder : 0
         * totalUseNum : 1
         * isday : 1
         */

        private int oneHour;
        private String coachName;
        private String fitnessCourseName;
        private int useNum;
        private String memberCoursePkcode;
        private String endtime;
        private String coachNikename;
        private String begtime;
        private int remainder;
        private int totalUseNum;
        private int isday;

        public int getOneHour() {
            return oneHour;
        }

        public void setOneHour(int oneHour) {
            this.oneHour = oneHour;
        }

        public String getCoachName() {
            return coachName;
        }

        public void setCoachName(String coachName) {
            this.coachName = coachName;
        }

        public String getFitnessCourseName() {
            return fitnessCourseName;
        }

        public void setFitnessCourseName(String fitnessCourseName) {
            this.fitnessCourseName = fitnessCourseName;
        }

        public int getUseNum() {
            return useNum;
        }

        public void setUseNum(int useNum) {
            this.useNum = useNum;
        }

        public String getMemberCoursePkcode() {
            return memberCoursePkcode;
        }

        public void setMemberCoursePkcode(String memberCoursePkcode) {
            this.memberCoursePkcode = memberCoursePkcode;
        }

        public String getEndtime() {
            return endtime;
        }

        public void setEndtime(String endtime) {
            this.endtime = endtime;
        }

        public String getCoachNikename() {
            return coachNikename;
        }

        public void setCoachNikename(String coachNikename) {
            this.coachNikename = coachNikename;
        }

        public String getBegtime() {
            return begtime;
        }

        public void setBegtime(String begtime) {
            this.begtime = begtime;
        }

        public int getRemainder() {
            return remainder;
        }

        public void setRemainder(int remainder) {
            this.remainder = remainder;
        }

        public int getTotalUseNum() {
            return totalUseNum;
        }

        public void setTotalUseNum(int totalUseNum) {
            this.totalUseNum = totalUseNum;
        }

        public int getIsday() {
            return isday;
        }

        public void setIsday(int isday) {
            this.isday = isday;
        }
    }

    public static class NotbookBean {
        /**
         * oneHour : 1
         * coachName : 无敌教练
         * fitnessCourseName : 散打私教课
         * useNum : 1
         * memberCoursePkcode : 5g7fj2yz
         * coachNikename : 无敌教练
         * remainder : 0
         * totalUseNum : 1
         * isday : 0
         */

        private int oneHour;
        private String coachName;
        private String fitnessCourseName;
        private int useNum;
        private String memberCoursePkcode;
        private String coachNikename;
        private int remainder;
        private int totalUseNum;
        private int isday;

        public int getOneHour() {
            return oneHour;
        }

        public void setOneHour(int oneHour) {
            this.oneHour = oneHour;
        }

        public String getCoachName() {
            return coachName;
        }

        public void setCoachName(String coachName) {
            this.coachName = coachName;
        }

        public String getFitnessCourseName() {
            return fitnessCourseName;
        }

        public void setFitnessCourseName(String fitnessCourseName) {
            this.fitnessCourseName = fitnessCourseName;
        }

        public int getUseNum() {
            return useNum;
        }

        public void setUseNum(int useNum) {
            this.useNum = useNum;
        }

        public String getMemberCoursePkcode() {
            return memberCoursePkcode;
        }

        public void setMemberCoursePkcode(String memberCoursePkcode) {
            this.memberCoursePkcode = memberCoursePkcode;
        }

        public String getCoachNikename() {
            return coachNikename;
        }

        public void setCoachNikename(String coachNikename) {
            this.coachNikename = coachNikename;
        }

        public int getRemainder() {
            return remainder;
        }

        public void setRemainder(int remainder) {
            this.remainder = remainder;
        }

        public int getTotalUseNum() {
            return totalUseNum;
        }

        public void setTotalUseNum(int totalUseNum) {
            this.totalUseNum = totalUseNum;
        }

        public int getIsday() {
            return isday;
        }

        public void setIsday(int isday) {
            this.isday = isday;
        }
    }
}
