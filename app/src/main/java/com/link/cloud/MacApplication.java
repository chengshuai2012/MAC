package com.link.cloud;

import android.os.Handler;
import android.os.Looper;

import com.link.cloud.api.ApiFactory;
import com.link.cloud.utils.Venueutils;
import com.link.cloud.widget.MyLoadViewFactory;
import com.shizhefei.mvc.MVCHelper;
import com.zitech.framework.BaseApplication;
import com.zitech.framework.data.network.response.ApiResponse;
import com.zitech.framework.data.network.subscribe.ProgressSubscriber;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MacApplication extends BaseApplication {

    private User user;
    private Handler mainThreadHandler;
    public static Venueutils venueUtils;

    public static Venueutils getVenueUtils() {
        synchronized (Venueutils.class) {
            if (venueUtils == null) {
                venueUtils = new Venueutils();
            }
            return venueUtils;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        user = new User();
        getToken();
        mainThreadHandler = new Handler(Looper.getMainLooper());
        MVCHelper.setLoadViewFactory(new MyLoadViewFactory());
        Realm.init(this);
        RealmConfiguration configuration = new RealmConfiguration.Builder()
                .name("Mac.realm")
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(configuration);
    }

    public void post(Runnable r) {
        mainThreadHandler.post(r);
    }


    public User getUser() {
        return user;
    }


    public static MacApplication getInstance() {
        return (MacApplication) BaseApplication.getInstance();
    }


    private void getToken() {
        ApiFactory.appLogin().subscribe(new ProgressSubscriber<ApiResponse>(this) {
            @Override
            public void onNext(ApiResponse response) {
                User.get().setToken((String) response.getData());
            }
        });
    }

}
