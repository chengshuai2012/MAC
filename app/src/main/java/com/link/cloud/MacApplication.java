package com.link.cloud;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.iflytek.cloud.Setting;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechUtility;
import com.link.cloud.utils.Venueutils;
import com.link.cloud.widget.MyLoadViewFactory;
import com.shizhefei.mvc.MVCHelper;
import com.zitech.framework.BaseApplication;

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
        mainThreadHandler = new Handler(Looper.getMainLooper());
        MVCHelper.setLoadViewFactory(new MyLoadViewFactory());
        Realm.init(this);
        RealmConfiguration configuration = new RealmConfiguration.Builder()
                .name("Mac.realm")
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(configuration);
        intSpeak();

    }
    private void intSpeak(){
        SpeechUtility.createUtility(this, SpeechConstant.APPID +"=5b3d9df5");//=号后面写自己应用的APPID
        Setting.setShowLog(false);

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
