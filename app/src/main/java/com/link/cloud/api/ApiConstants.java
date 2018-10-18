package com.link.cloud.api;


/**
 * Created by qianlu on 2018/5/16.
 * 邮箱：zar.l@qq.com
 */
public class ApiConstants {
    //API接口地址
    public static final String DAILY_DAILY_URL = "http://alx.patpang.cn/";


    //获取验证码
    public static final String SENDVCODE = "{app}/{sendVCode}/{phone}";

    //APP登录
    public static final String APPLOGIN = "{pub}/{appLogin}/{deviceCode}/{password}";

    //团课入场
    public static final String ADMISSIONCOURSE = "{app}/{admissionCourse}/{uid}/{cid}";

    //课程列表
    public static final String COURSELIST = "{app}/{courseList}/{begDate}";

    //修改指纹
    public static final String EDITUSER = "app/editUser";

    //分页获取指静脉
    public static final String GETUSERS = "app/users";

    //绑定用户
    public static final String BINDUSER = "{app}/{bindUser}/{phone}/{vcode}";

    //课程详情
    public static final String COURSEDETAIL = "{app}/{courseDetail}/{courseReleasePkcode}";

    //查询顾客的私教信息
    public static final String FINDCOACH = "{app}/{findCoach}/{uid}";

    //查询顾客私教课程
    public static final String FINDPERSONALCOURSE = "{app}/{findPersonalCourse}/{uid}/{cid}";

    //查询顾客私教课程
    public static final String FINISHCOURSE = "{app}/{finishCourse}/{uid}/{cid}/{kid}";


}
