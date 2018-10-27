package com.link.cloud.bean;


import com.link.cloud.api.bean.LessonItemBean;

import java.util.List;

/**
 * Created by 49488 on 2018/10/27.
 */

public class MainFragment {
    String tip;

    public List<LessonItemBean> getCourses() {
        return courses;
    }

    public void setCourses(List<LessonItemBean> courses) {
        this.courses = courses;
    }

    private List<LessonItemBean> courses;
    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }


}
