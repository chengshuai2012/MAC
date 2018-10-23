package com.link.cloud.api.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @author qianlu
 * @date 2018/9/26.
 * GitHub：qiandailu
 * email：zar.l@qq.com
 * description：
 */
public class PrivateEduBean implements Serializable {


    /**
     * coachName : 贾
     * fitnessCourseName : 散打私教课
     * address : 深圳市宝安区
     * flag : b1,b2,b3
     * courseReleasePkcode : g7439r25
     * priceLevel : [{"courseTotal":"1","totalPrice":"0.1","courseUnit":"0.10"},{"courseTotal":"2","totalPrice":"0.2","courseUnit":"0.10"},{"courseTotal":"3","totalPrice":"0.3","courseUnit":"0.10"}]
     */

    private String coachName;
    private String fitnessCourseName;
    private String address;
    private String flag;
    private String courseReleasePkcode;
    private List<PriceLevelBean> priceLevel;
    private String imgurl;


    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getCourseReleasePkcode() {
        return courseReleasePkcode;
    }

    public void setCourseReleasePkcode(String courseReleasePkcode) {
        this.courseReleasePkcode = courseReleasePkcode;
    }

    public List<PriceLevelBean> getPriceLevel() {
        return priceLevel;
    }

    public void setPriceLevel(List<PriceLevelBean> priceLevel) {
        this.priceLevel = priceLevel;
    }

}
