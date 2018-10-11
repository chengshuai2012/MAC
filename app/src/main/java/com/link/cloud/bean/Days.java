package com.link.cloud.bean;

/**
 * Created by 49488 on 2018/10/6.
 */

public class Days {
    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    @Override
    public String toString() {
        return "Days{" +
                "day='" + day + '\'' +
                ", week='" + week + '\'' +
                ", lauar='" + lauar + '\'' +
                '}';
    }

    public String getLauar() {
        return lauar;
    }

    public void setLauar(String lauar) {
        this.lauar = lauar;
    }

    public String day;
    public String week;
    public String lauar;
}
