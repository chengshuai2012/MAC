package com.link.cloud.api;

import com.link.cloud.api.bean.PrivateEduBean;
import com.link.cloud.api.request.PageRequest;
import com.link.cloud.api.response.PageResponse;
import com.zitech.framework.data.network.RetrofitClient;
import com.zitech.framework.data.network.response.ApiResponse;

import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rx.Observable;

/**
 * 以后可以根据不同业务模块分离
 * Created by lu on 2016/6/2.
 */
public interface ApiService {

    /**
     * 4.2 我的团队
     *
     * @param
     * @returnscheduleReport
     * @see
     */
    @POST(ApiConstants.TEAMLIST)
    @Headers("Content-Type:" + RetrofitClient.JSON)
    Observable<ApiResponse<PageResponse<PrivateEduBean>>> privateEduList(@Body PageRequest request);


}
