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
    public static final String ADMISSIONCOURSE = "{app}/{courseAdmission}/{uuid}/{courseReleasePkcode}";

    //课程列表
    public static final String COURSELIST = "{app}/{courseList}/{begDate}";

    //修改指纹
    public static final String EDITUSER = "app/addFingerprints";

    //分页获取指静脉
    public static final String GETUSERS = "app/users";

    //获取准备开课课程详情(开课前30分可获取)
    public static final String GETRECENTCLASS = "app/findStartCourse";

    //绑定用户
    public static final String BINDUSER = "{app}/{bindUser}/{phone}/{vcode}";

    //课程详情
    public static final String COURSEDETAIL = "{app}/{courseDetail}/{courseReleasePkcode}";

    //获取团课学员
    public static final String GETGROUPLESSONMEMBER = "{app}/{courseUsers}/{courseReleasePkcode}";

    //获取购买课程支付信息
    public static final String PREBUYCOURSE = "{app}/{prebuyCourse}/{courseReleasePkcode}";

    //获取顾客的私教课
    public static final String GETPERSONALCLASS = "{app}/{findPersonalCourse}/{uuid}";

    //获取私教课教练
    public static final String FINDCOACH = "{app}/{findCoach}/{uuid}/{memberCoursePkcode}";

    //消私教课程
    public static final String CONSUNMEPRITE = "{app}/{finishCourse}/{uuid}/{memberCoursePkcode}/{coachid}";

    //验证用户是否是管理员
    public static final String VALIDATEADMIN = "{app}/{validateAdmin}/{uuid}";

    //验证机器密码
    public static final String VALIDATEPASS = "{app}/{validatePassword}/{password}";

    //获取私教课程列表
    public static final String PRIVATECOURSELIST = "{app}/{privateCourseList}";

    //获取购买私教课程支付信息
    public static final String PREBUYPRIVATECOURSE = "{app}/{prebuyPrivateCourse}/{courseReleasePkcode}/{level}";

    //余额支付
    public static final String BUYBYREST = "{app}/{balanceBuyCourse}/{uuid}/{courseReleasePkcode}";













}
