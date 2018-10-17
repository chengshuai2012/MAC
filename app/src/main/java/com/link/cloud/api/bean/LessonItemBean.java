package com.link.cloud.api.bean;

/**
 * 作者：qianlu on 2018/10/17 15:09
 * 邮箱：zar.l@qq.com
 */
public class LessonItemBean {


    /**
     * id : 2
     * date : 2018-10-17 00:00:00
     * fitnessCourseName : 肩颈瑜伽释放
     * courseReleaseDiscountStatus : 1
     * courseReleasePkcode : wxjs489m
     * storeCoachHeadimg : http://www.handyfitness.com.cn:8094/api/file/uploadfile/img/1539341553401.png
     * storeCoachTopimg : http://www.handyfitness.com.cn:8094/api/file/uploadfile/img/1539341566551.png
     * coursePlanBegtime : 10:30
     * storeCoachName : 杨瑞研
     * storeName : 白石龙店
     * storeNikename : 白石龙店
     * courseReleaseMoney : 89
     * coursePlanEndtime : 11:30
     * storeCoachNikename : Mocca
     * branchId : 1
     * merchantId : 1
     */

    private int id;
    private String date;
    private String fitnessCourseName;
    private int courseReleaseDiscountStatus;
    private String courseReleasePkcode;
    private String storeCoachHeadimg;
    private String storeCoachTopimg;
    private String coursePlanBegtime;
    private String storeCoachName;
    private String storeName;
    private String storeNikename;
    private String courseReleaseMoney;
    private String coursePlanEndtime;
    private String storeCoachNikename;
    private int branchId;
    private int merchantId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFitnessCourseName() {
        return fitnessCourseName;
    }

    public void setFitnessCourseName(String fitnessCourseName) {
        this.fitnessCourseName = fitnessCourseName;
    }

    public int getCourseReleaseDiscountStatus() {
        return courseReleaseDiscountStatus;
    }

    public void setCourseReleaseDiscountStatus(int courseReleaseDiscountStatus) {
        this.courseReleaseDiscountStatus = courseReleaseDiscountStatus;
    }

    public String getCourseReleasePkcode() {
        return courseReleasePkcode;
    }

    public void setCourseReleasePkcode(String courseReleasePkcode) {
        this.courseReleasePkcode = courseReleasePkcode;
    }

    public String getStoreCoachHeadimg() {
        return storeCoachHeadimg;
    }

    public void setStoreCoachHeadimg(String storeCoachHeadimg) {
        this.storeCoachHeadimg = storeCoachHeadimg;
    }

    public String getStoreCoachTopimg() {
        return storeCoachTopimg;
    }

    public void setStoreCoachTopimg(String storeCoachTopimg) {
        this.storeCoachTopimg = storeCoachTopimg;
    }

    public String getCoursePlanBegtime() {
        return coursePlanBegtime;
    }

    public void setCoursePlanBegtime(String coursePlanBegtime) {
        this.coursePlanBegtime = coursePlanBegtime;
    }

    public String getStoreCoachName() {
        return storeCoachName;
    }

    public void setStoreCoachName(String storeCoachName) {
        this.storeCoachName = storeCoachName;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStoreNikename() {
        return storeNikename;
    }

    public void setStoreNikename(String storeNikename) {
        this.storeNikename = storeNikename;
    }

    public String getCourseReleaseMoney() {
        return courseReleaseMoney;
    }

    public void setCourseReleaseMoney(String courseReleaseMoney) {
        this.courseReleaseMoney = courseReleaseMoney;
    }

    public String getCoursePlanEndtime() {
        return coursePlanEndtime;
    }

    public void setCoursePlanEndtime(String coursePlanEndtime) {
        this.coursePlanEndtime = coursePlanEndtime;
    }

    public String getStoreCoachNikename() {
        return storeCoachNikename;
    }

    public void setStoreCoachNikename(String storeCoachNikename) {
        this.storeCoachNikename = storeCoachNikename;
    }

    public int getBranchId() {
        return branchId;
    }

    public void setBranchId(int branchId) {
        this.branchId = branchId;
    }

    public int getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(int merchantId) {
        this.merchantId = merchantId;
    }
}
