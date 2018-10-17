package com.link.cloud.api;

import com.link.cloud.api.bean.LessonBean;
import com.link.cloud.api.bean.PrivateEduBean;
import com.link.cloud.api.request.EdituserRequest;
import com.link.cloud.api.request.PageRequest;
import com.link.cloud.api.response.PageResponse;
import com.zitech.framework.data.network.RetrofitClient;
import com.zitech.framework.data.network.response.ApiResponse;

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
    Observable<ApiResponse> admissionCourse(@Path("app") String app, @Path("admissionCourse") String admissionCourse, @Path("uid") String uid, @Path("cid") String cid, @Header("access-token") String token);


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
     * 修改指纹
     *
     * @param
     * @return
     * @see {app}/{edituser}
     */
    @POST(ApiConstants.EDITUSER)
    @Headers("Content-Type:" + RetrofitClient.FORM)
    Observable<ApiResponse> edituser(@Path("app") String app, @Path("edituser") String edituser, @Header("access-token") String token, @Body EdituserRequest request);


    /**
     * 绑定用户
     *
     * @param
     * @return
     * @see "{app}/{bindUser}/{phone}/{vcode}";
     */
    @GET(ApiConstants.BINDUSER)
    @Headers("Content-Type:" + RetrofitClient.FORM)
    Observable<ApiResponse> binduser(@Path("app") String app, @Path("bindUser") String bindUser, @Path("phone") String phone, @Path("vcode") String vcode, @Header("access-token") String token);


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
    Observable<ApiResponse> findcoach(@Path("app") String app, @Path("findCoach") String findCoach, @Path("uid") String uid, @Header("access-token") String token);

    /**
     * 查询顾客私教课程
     *
     * @param
     * @return
     * @see {app}/{findPersonalCourse}/{uid}/{cid}
     */
    @GET(ApiConstants.FINDPERSONALCOURSE)
    @Headers("Content-Type:" + RetrofitClient.FORM)
    Observable<ApiResponse> findpersonalcourse(@Path("app") String app, @Path("findPersonalCourse") String findPersonalCourse, @Path("uid") String uid, @Path("cid") String cid, @Header("access-token") String token);


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


}
