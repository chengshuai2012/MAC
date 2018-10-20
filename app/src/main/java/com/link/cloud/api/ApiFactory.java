package com.link.cloud.api;

import com.link.cloud.User;
import com.link.cloud.api.bean.LessonBean;
import com.link.cloud.api.bean.PrivateEduBean;
import com.link.cloud.api.bean.UserBindBean;
import com.link.cloud.api.dataSourse.UserList;
import com.link.cloud.api.request.EdituserRequest;
import com.link.cloud.api.request.GetUserPages;
import com.link.cloud.api.request.PageRequest;
import com.link.cloud.api.response.PageResponse;
import com.zitech.framework.data.network.RetrofitClient;
import com.zitech.framework.data.network.response.ApiResponse;
import com.zitech.framework.data.network.subscribe.SchedulersCompat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public static Observable<ApiResponse> appLogin() {
        return getApiService().appLogin("pub", "appLogin", "HJKF", "0D874A5A3B0C3AAB71E35EE325693762").map(new HttpResultFunc()).compose(SchedulersCompat.applyIoSchedulers());
    }
   /**
     * 获取准备开课课程详情(开课前30分可获取)
     * @return
     */
    public static Observable<ApiResponse> getRecentLesson() {
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
        return getApiService().admissionCourse("app", "admissionCourse", uuid, courseReleasePkcode, User.get().getToken()).map(new HttpResultFunc()).compose(SchedulersCompat.applyIoSchedulers());
    }

    /**
     * 课程列表
     *
     * @return
     */
    public static Observable<ApiResponse<List<LessonBean>>>  courseList(String begDate) {
        return getApiService().courseList("app", "courseList", begDate, User.get().getToken()).map(new HttpResultFunc()).compose(SchedulersCompat.applyIoSchedulers());
    }


    /**
     * 修改指纹
     *
     * @return
     */
    public static Observable<ApiResponse> edituser(EdituserRequest request) {
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
    public static Observable<ApiResponse> coursedetail(String courseReleasePkcode) {
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
    public static Observable<ApiResponse> findcoach(String uid) {
        return getApiService().findcoach("app", "findCoach", uid, User.get().getToken()).map(new HttpResultFunc()).compose(SchedulersCompat.applyIoSchedulers());
    }

    /**
     * 查询顾客私教课程
     * uid 顾客ID
     * cid 教练ID
     * kid 课程ID
     *
     * @return
     */
    public static Observable<ApiResponse> findpersonalcourse(String uid, String cid) {
        return getApiService().findpersonalcourse("app", "findPersonalCourse", uid, cid, User.get().getToken()).map(new HttpResultFunc()).compose(SchedulersCompat.applyIoSchedulers());
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
        return getApiService().getUsers(User.get().getToken(),request).map(new HttpResultFunc()).compose(SchedulersCompat.applyIoSchedulers());
    }

    /**
     * 团课用户
     *
     * @return
     */
    public static Observable<ApiResponse> getGroupUsers(String  courseReleasePkcode) {
        return getApiService().getGroupUsers("app", "courseUsers",User.get().getToken(),courseReleasePkcode).map(new HttpResultFunc()).compose(SchedulersCompat.applyIoSchedulers());
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
