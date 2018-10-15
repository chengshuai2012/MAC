package com.link.cloud.api;

import com.link.cloud.User;
import com.link.cloud.api.bean.PrivateEduBean;
import com.link.cloud.api.request.PageRequest;
import com.link.cloud.api.response.PageResponse;
import com.zitech.framework.data.network.RetrofitClient;
import com.zitech.framework.data.network.response.ApiResponse;
import com.zitech.framework.data.network.subscribe.SchedulersCompat;

import java.util.HashMap;
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
     * 我的团队
     *
     * @return
     */
    public static Observable<ApiResponse<PageResponse<PrivateEduBean>>> privateEduList(PageRequest request) {
        return getApiService().privateEduList(request).map(new HttpResultFunc()).compose(SchedulersCompat.applyIoSchedulers());
    }


    /**
     * 我的团队
     *
     * @return
     */
    public static Observable<ApiResponse> appLogin() {
        return getApiService().appLogin("pub", "appLogin", "HJKF", "0D874A5A3B0C3AAB71E35EE325693762").map(new HttpResultFunc()).compose(SchedulersCompat.applyIoSchedulers());
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
