package com.link.cloud;

import android.os.Handler;
import android.os.Looper;

import com.zitech.framework.BaseApplication;

public class MacApplication extends BaseApplication {

    private User user;
    private Handler mainThreadHandler;

    @Override
    public void onCreate() {
        super.onCreate();
        user = new User();
        mainThreadHandler = new Handler(Looper.getMainLooper());
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

}
