package com.link.cloud.api.bean;

import java.io.Serializable;

/**
 * 作者：qianlu on 2018/10/23 11:18
 * 邮箱：zar.l@qq.com
 */
public class PriceLevelBean  implements Serializable{

    /**
     * courseTotal : 1
     * totalPrice : 0.1
     * courseUnit : 0.10
     */

    private String courseTotal;
    private String totalPrice;
    private String courseUnit;

    public String getCourseTotal() {
        return courseTotal;
    }

    public void setCourseTotal(String courseTotal) {
        this.courseTotal = courseTotal;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getCourseUnit() {
        return courseUnit;
    }

    public void setCourseUnit(String courseUnit) {
        this.courseUnit = courseUnit;
    }
}
