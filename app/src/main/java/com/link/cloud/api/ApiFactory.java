package com.link.cloud.api;

import com.link.cloud.User;
import com.link.cloud.api.bean.APPVersionBean;
import com.link.cloud.api.bean.DeviceBean;
import com.link.cloud.api.bean.LessonBean;
import com.link.cloud.api.bean.PrivateEduBean;
import com.link.cloud.api.bean.SingleUser;
import com.link.cloud.api.bean.UserBindBean;
import com.link.cloud.api.dataSourse.CoachInfo;
import com.link.cloud.api.dataSourse.GroupLessonInResource;
import com.link.cloud.api.dataSourse.GroupLessonUser;
import com.link.cloud.api.dataSourse.UserList;
import com.link.cloud.api.request.BindFinger;
import com.link.cloud.api.request.EdituserRequest;
import com.link.cloud.api.request.GetUserPages;
import com.link.cloud.api.request.LessonPred;
import com.link.cloud.api.request.PageRequest;
import com.link.cloud.api.response.CoachBean;
import com.link.cloud.api.response.PageResponse;
import com.zitech.framework.data.network.RetrofitClient;
import com.zitech.framework.data.network.response.ApiResponse;
import com.zitech.framework.data.network.subscribe.SchedulersCompat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Api协议工厂，具体方法代码通过{@link }来生成
 */
public class ApiFactory {

    private static Map<String, Object> mCache = new HashMap();


    public static ApiService getApiService() {
        return getService(ApiConstants.DAILY_DAILY_URL, ApiService.class);
    }

    public static <T> T getService(String baseUrl, Class<T> cls) {
        Object target = mCache.get(baseUrl);
        if (target == null || !cls.isAssignableFrom(target.getClass())) {
            target = RetrofitClient.getInstance().create(baseUrl, cls);
            mCache.put(baseUrl, target);
        }
        return (T) target;
    }


    /**
     * 发送验证码
     *
     * @return
     */
    public static Observable<ApiResponse> sendVCode(String phone) {
        return getApiService().sendVCode("app", "sendVCode", phone, User.get().getToken()).map(new HttpResultFunc()).compose(SchedulersCompat.applyIoSchedulers());
    }


    /**
     * APP登录
     * <p>
     * deviceCode
     * <p>
     * password
     *
     * @return
     */
    public static Observable<ApiResponse<DeviceBean>> appLogin(String userName, String password) {
        return getApiService().appLogin("pub", "appLogin", userName, password).map(new HttpResultFunc()).compose(SchedulersCompat.applyIoSchedulers());
    }

    /**
     * 获取准备开课课程详情(开课前30分可获取)
     *
     * @return
     */
    public static Observable<ApiResponse<ArrayList<GroupLessonInResource>>> getRecentLesson() {
        return getApiService().getRecentClass(User.get().getToken()).map(new HttpResultFunc()).compose(SchedulersCompat.applyIoSchedulers());
    }


    /**
     * 团课入场
     * *  uid 顾客ID
     * cid 教练ID
     * kid 课程ID
     *
     * @return
     */
    public static Observable<ApiResponse> admissionCourse(String uuid, String courseReleasePkcode) {
        return getApiService().admissionCourse("app", "courseAdmission", uuid, courseReleasePkcode, User.get().getToken()).map(new HttpResultFunc()).compose(SchedulersCompat.applyIoSchedulers());
    }

    /**
     * 课程列表
     *
     * @return
     */
    public static Observable<ApiResponse<List<LessonBean>>> courseList() {
        return getApiService().courseList("app", "courseList", User.get().getToken()).map(new HttpResultFunc()).compose(SchedulersCompat.applyIoSchedulers());
    }


    /**
     * 修改指纹
     *
     * @return
     */
    public static Observable<ApiResponse> edituser(BindFinger request) {
        return getApiService().edituser(User.get().getToken(), request).map(new HttpResultFunc()).compose(SchedulersCompat.applyIoSchedulers());
    }


    /**
     * 绑定用户
     *
     * @return
     */
    public static Observable<ApiResponse<EdituserRequest>> binduser(String phone, String vCode) {
        return getApiService().binduser("app", "bindUser", phone, vCode, User.get().getToken()).map(new HttpResultFunc()).compose(SchedulersCompat.applyIoSchedulers());
    }

    /**
     * 课程详情
     *
     * @return
     */
    public static Observable<ApiResponse<CoachInfo>> coursedetail(String courseReleasePkcode) {
        return getApiService().coursedetail("app", "courseDetail", courseReleasePkcode, User.get().getToken()).map(new HttpResultFunc()).compose(SchedulersCompat.applyIoSchedulers());
    }


    /**
     * 查询顾客的私教信息
     * <p>
     * *  uid 顾客ID
     * cid 教练ID
     * kid 课程ID
     *
     * @return
     */
    public static Observable<ApiResponse<CoachBean>> findcoach(String uid, String memberCoursePkcode) {
        return getApiService().findcoach("app", "findCoach", uid, memberCoursePkcode, User.get().getToken()).map(new HttpResultFunc()).compose(SchedulersCompat.applyIoSchedulers());
    }


    /**
     * 我的团队
     *
     * @return
     */
    public static Observable<ApiResponse<PageResponse<PrivateEduBean>>> privateEduList(PageRequest request) {
        return getApiService().privateEduList(request).map(new HttpResultFunc()).compose(SchedulersCompat.applyIoSchedulers());
    }

    /**
     * 用户列表
     *
     * @return
     */
    public static Observable<ApiResponse<UserList>> getUsers(GetUserPages request) {
        return getApiService().getUsers(User.get().getToken(), request).map(new HttpResultFunc()).compose(SchedulersCompat.applyIoSchedulers());
    }

    /**
     * 团课用户
     *
     * @return
     */
    public static Observable<ApiResponse<GroupLessonUser>> getGroupUsers(String courseReleasePkcode) {
        return getApiService().getGroupUsers("app", "courseUsers", User.get().getToken(), courseReleasePkcode).map(new HttpResultFunc()).compose(SchedulersCompat.applyIoSchedulers());
    }

    /**
     * 购买二维码
     *
     * @return
     */
    public static Observable<ApiResponse> getBuyQrcode(String courseReleasePkcode) {
        return getApiService().getBuyQrcode("app", "prebuyCourse", User.get().getToken(), courseReleasePkcode).map(new HttpResultFunc()).compose(SchedulersCompat.applyIoSchedulers());
    }

    /**
     * 获取顾客的私教课
     *
     * @return
     */
    public static Observable<ApiResponse<LessonPred>> getPersonalClass(String uuid) {
        return getApiService().getPersonalClass("app", "findPersonalCourse", User.get().getToken(), uuid).map(new HttpResultFunc()).compose(SchedulersCompat.applyIoSchedulers());
    }

    /**
     * 消私教课程
     *
     * @return
     */
    public static Observable<ApiResponse> consumePrivate(String uuid, String memberCoursePkcode, String coachid) {
        return getApiService().consumePrivate("app", "finishCourse", User.get().getToken(), uuid, memberCoursePkcode, coachid).map(new HttpResultFunc()).compose(SchedulersCompat.applyIoSchedulers());
    }

    /**
     * 验证用户是否是管理员
     *
     * @return
     */
    public static Observable<ApiResponse> validateAdmin(String uuid) {
        return getApiService().validateAdmin("app", "validateAdmin", User.get().getToken(), uuid).map(new HttpResultFunc()).compose(SchedulersCompat.applyIoSchedulers());
    }

    /**
     * 验证机器密码
     *
     * @return
     */
    public static Observable<ApiResponse> validatePassword(String password) {
        return getApiService().validatePassword("app", "validatePassword", User.get().getToken(), password).map(new HttpResultFunc()).compose(SchedulersCompat.applyIoSchedulers());
    }

    /**
     * 验证机器密码
     *
     * @return
     */
    public static Observable<ApiResponse<List<PrivateEduBean>>> privatecourselist() {
        return getApiService().privatecourselist("app", "privateCourseList", User.get().getToken()).map(new HttpResultFunc()).compose(SchedulersCompat.applyIoSchedulers());
    }

    /**
     * 验证机器密码
     *
     * @return
     * @returnscheduleReport "{app}/{prebuyPrivateCourse}/{courseReleasePkcode}/{level}";
     */
    public static Observable<ApiResponse> prebuyPrivateCourse(String courseReleasePkcode,String level) {
        return getApiService().prebuyprivatecourse("app", "prebuyPrivateCourse", courseReleasePkcode, level, User.get().getToken()).map(new HttpResultFunc()).compose(SchedulersCompat.applyIoSchedulers());
    }
/**
     * 余额支付
     *
     * @return
     * @returnscheduleReport "{app}/{prebuyPrivateCourse}/{courseReleasePkcode}/{level}";
     */
    public static Observable<ApiResponse> payByRest(String courseReleasePkcode,String uuid) {
        return getApiService().payByRest("app", "balanceBuyCourse", courseReleasePkcode, uuid, User.get().getToken()).map(new HttpResultFunc()).compose(SchedulersCompat.applyIoSchedulers());
    }

/**
     * 余额支付
     *
     * @return
     * @returnscheduleReport "{app}/{prebuyPrivateCourse}/{courseReleasePkcode}/{level}";
     */
    public static Observable<ApiResponse<SingleUser>> getSingle(String uuid) {
        return getApiService().getSingle("app", "user", uuid, User.get().getToken()).map(new HttpResultFunc()).compose(SchedulersCompat.applyIoSchedulers());
    }
/**
     * 二维码团课进场
     *
     * @return
     * @returnscheduleReport "{app}/{prebuyPrivateCourse}/{courseReleasePkcode}/{level}";
     */
    public static Observable<ApiResponse> CourseInByQrcode(RequestBody  qrcode) {
        return getApiService().CourseInByQrcode("app", "courseAdmissionByQrCode", qrcode, User.get().getToken()).map(new HttpResultFunc()).compose(SchedulersCompat.applyIoSchedulers());
    }
/**
     * 余额支付
     *
     * @return
     * @returnscheduleReport "{app}/{prebuyPrivateCourse}/{courseReleasePkcode}/{level}";
     */
    public static Observable<ApiResponse<EdituserRequest>> BindByQrcode(RequestBody  qrcode) {
        return getApiService().BindByQrcode("app", "bindUserByQrCode", qrcode, User.get().getToken()).map(new HttpResultFunc()).compose(SchedulersCompat.applyIoSchedulers());
    }
    /**
     * APP版本
     */

    public static Observable<ApiResponse<APPVersionBean>> getAppVersion() {
        return getApiService().getAppVersion("app", "appVersion", 1, User.get().getToken()).map(new HttpResultFunc()).compose(SchedulersCompat.applyIoSchedulers());
    }


//    /**
//     * 发送短信验证码
//     *
//     * @return
//     */
//    public static Observable<ApiResponse> sendCode(PhoneRequest request) {
//        return getApiNewsFlashgetList().sendCode(request).map(new HttpResultFunc()).compose(SchedulersCompat.applyIoSchedulers());
//    }


}
