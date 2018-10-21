package com.link.cloud.api;

import com.link.cloud.api.bean.LessonBean;
import com.link.cloud.api.bean.PrivateEduBean;
import com.link.cloud.api.bean.UserBindBean;
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

import java.util.ArrayList;
import java.util.List;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;

/**
 * 以后可以根据不同业务模块分离
 * Created by lu on 2016/6/2.
 */
public interface ApiService {


    /**
     * APP登录
     *
     * @param
     * @return
     * @see
     */
    @POST(ApiConstants.APPLOGIN)
    @Headers("Content-Type:" + RetrofitClient.FORM)
    Observable<ApiResponse> appLogin(@Path("pub") String pub, @Path("appLogin") String appLogin, @Path("deviceCode") String deviceCode, @Path("password") String password);


    /**
     * 获取验证码
     *
     * @param
     * @return
     * @see /{app}/{sendVCode}/{phone}
     */
    @GET(ApiConstants.SENDVCODE)
    @Headers("Content-Type:" + RetrofitClient.FORM)
    Observable<ApiResponse> sendVCode(@Path("app") String app, @Path("sendVCode") String sendVCode, @Path("phone") String phone, @Header("access-token") String token);


    /**
     * 团课入场
     *
     * @param
     * @return
     * @see {app}/{admissionCourse}/{uid}/{cid}
     */
    @POST(ApiConstants.ADMISSIONCOURSE)
    @Headers("Content-Type:" + RetrofitClient.FORM)
    Observable<ApiResponse> admissionCourse(@Path("app") String app, @Path("courseAdmission") String admissionCourse, @Path("uuid") String uid, @Path("courseReleasePkcode") String cid, @Header("access-token") String token);


    /**
     * 课程列表
     *
     * @param
     * @return
     * @see {app}/{courseList}/{begDate}
     */
    @POST(ApiConstants.COURSELIST)
    @Headers("Content-Type:" + RetrofitClient.FORM)
    Observable<ApiResponse<List<LessonBean>>> courseList(@Path("app") String app, @Path("courseList") String courseList, @Path("begDate") String begDate, @Header("access-token") String token);


    /**
     * 分页获取指静脉
     *
     * @param
     * @return
     *
     */
    @POST(ApiConstants.GETUSERS)
    @Headers("Content-Type:" + RetrofitClient.JSON)
    Observable<ApiResponse<UserList>> getUsers(@Header("access-token") String token, @Body GetUserPages request);

 /**
     * 修改指纹
     *
     * @param
     * @return
     * @see {app}/{edituser}
     */
    @POST(ApiConstants.EDITUSER)
    @Headers("Content-Type:" + RetrofitClient.JSON)
    Observable<ApiResponse> edituser(@Header("access-token") String token, @Body BindFinger request);

    /**
     * 绑定用户
     *
     * @param
     * @return
     * @see "{app}/{bindUser}/{phone}/{vcode}";
     */
    @GET(ApiConstants.BINDUSER)
    @Headers("Content-Type:" + RetrofitClient.FORM)
    Observable<ApiResponse<EdituserRequest>> binduser(@Path("app") String app, @Path("bindUser") String bindUser, @Path("phone") String phone, @Path("vcode") String vcode, @Header("access-token") String token);


    /**
     * 课程详情
     *
     * @param
     * @return
     * @see {app}/{courseDetail}/{courseReleasePkcode}
     */
    @GET(ApiConstants.COURSEDETAIL)
    @Headers("Content-Type:" + RetrofitClient.FORM)
    Observable<ApiResponse> coursedetail(@Path("app") String app, @Path("courseDetail") String courseDetail, @Path("courseReleasePkcode") String courseReleasePkcode, @Header("access-token") String token);


    /**
     * 查询顾客的私教信息
     *
     * @param
     * @return
     * @see {app}/{findCoach}/{uid}
     */
    @GET(ApiConstants.FINDCOACH)
    @Headers("Content-Type:" + RetrofitClient.FORM)
    Observable<ApiResponse<CoachBean>> findcoach(@Path("app") String app, @Path("findCoach") String findCoach, @Path("uuid") String uuid, @Path("memberCoursePkcode") String memberCoursePkcode, @Header("access-token") String token);


    /**
     * 4.2 我的团队
     *
     * @param
     * @returnscheduleReport
     * @see
     */
    @POST(ApiConstants.SENDVCODE)
    @Headers("Content-Type:" + RetrofitClient.JSON)
    Observable<ApiResponse<PageResponse<PrivateEduBean>>> privateEduList(@Body PageRequest request);

    /**
     * 获取准备开课课程详情(开课前30分可获取)
     *
     * @param
     * @returnscheduleReport
     * @see
     */
    @GET(ApiConstants.GETRECENTCLASS)
    @Headers("Content-Type:" + RetrofitClient.JSON)
    Observable <ApiResponse<ArrayList<GroupLessonInResource>>> getRecentClass(@Header("access-token")String token);

    /**
     * 获取团课学员
     *
     * @param
     * @returnscheduleReport
     * @see
     */
    @GET(ApiConstants.GETGROUPLESSONMEMBER)
    @Headers("Content-Type:" + RetrofitClient.JSON)
    Observable<ApiResponse<GroupLessonUser>> getGroupUsers(@Path("app")String app, @Path("courseUsers")String courseUsers, @Header("access-token")String token, @Path("courseReleasePkcode")String courseReleasePkcode);

    /**
     * 获取团课购买二维码
     *
     * @param
     * @returnscheduleReport
     * @see
     */
    @POST(ApiConstants.PREBUYCOURSE)
    @Headers("Content-Type:" + RetrofitClient.JSON)
    Observable <ApiResponse>getBuyQrcode(@Path("app")String app,@Path("prebuyCourse") String prebuyCourse, @Header("access-token")String token, @Path("courseReleasePkcode")String courseReleasePkcode);
    /**
     * 获取顾客的私教课
     *
     * @param
     * @returnscheduleReport
     * @see
     */
    @GET(ApiConstants.GETPERSONALCLASS)
    @Headers("Content-Type:" + RetrofitClient.JSON)
    Observable <ApiResponse<LessonPred>>getPersonalClass(@Path("app")String app, @Path("findPersonalCourse") String findPersonalCourse, @Header("access-token")String token, @Path("uuid")String uuid);
    /**
     * 获取顾客的私教课
     *
     * @param
     * @returnscheduleReport
     * @see
     */
    @GET(ApiConstants.CONSUNMEPRITE)
    @Headers("Content-Type:" + RetrofitClient.JSON)
    Observable <ApiResponse>consumePrivate(@Path("app")String app, @Path("finishCourse")String finishCourse,@Header("access-token") String token,  @Path("uuid")String uuid,  @Path("memberCoursePkcode")String memberCoursePkcode,  @Path("coachid")String coachid);
    /**
     * 验证用户是否是管理员
     *
     * @param
     * @returnscheduleReport
     * @see
     */
    @POST(ApiConstants.VALIDATEADMIN)
    @Headers("Content-Type:" + RetrofitClient.JSON)
    Observable  <ApiResponse>validateAdmin(@Path("app")String app, @Path("validateAdmin")String validateAdmin, @Header("access-token")String token, @Path("uuid")String uuid);
    /**
     * 验证机器密码
     *
     * @param
     * @returnscheduleReport
     * @see
     */
    @POST(ApiConstants.VALIDATEPASS)
    @Headers("Content-Type:" + RetrofitClient.JSON)
    Observable  <ApiResponse>validatePassword(@Path("app")String app,@Path("validatePassword") String validatePassword, @Header("access-token")String token, @Path("password")String password);
}
