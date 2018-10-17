package com.link.cloud.api.bean;

import java.util.List;

/**
 * 作者：qianlu on 2018/10/17 15:54
 * 邮箱：zar.l@qq.com
 */
public class LessonBean {
    private String date;
    private List<LessonItemBean> courses;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<LessonItemBean> getCourses() {
        return courses;
    }

    public void setCourses(List<LessonItemBean> courses) {
        this.courses = courses;
    }
}
