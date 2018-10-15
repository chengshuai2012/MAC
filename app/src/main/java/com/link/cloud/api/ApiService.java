package com.link.cloud.api;

import com.link.cloud.api.bean.PrivateEduBean;
import com.link.cloud.api.request.PageRequest;
import com.link.cloud.api.response.PageResponse;
import com.zitech.framework.data.network.RetrofitClient;
import com.zitech.framework.data.network.response.ApiResponse;

import java.util.Map;

import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
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
    @POST(ApiConstants.SENDVCODE)
    @Headers("Content-Type:" + RetrofitClient.FORM)
    Observable<ApiResponse> sendVCode(@Path("app") String app, @Path("sendVCode") String sendVCode, @Path("phone") String phone, @Header("access-token") String devid);


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
