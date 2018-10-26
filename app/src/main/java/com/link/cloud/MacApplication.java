package com.link.cloud;

import android.os.Handler;
import android.os.Looper;

import com.link.cloud.utils.NettyClientBootstrap;
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
    private static NettyClientBootstrap nettyClientBootstrap;

    public static Venueutils getVenueUtils() {
        synchronized (Venueutils.class) {
            if (venueUtils == null) {
                venueUtils = new Venueutils();
            }
            return venueUtils;
        }
    }

    public static NettyClientBootstrap getNettyClientBootstrap() {
       return nettyClientBootstrap;
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
        nettyClientBootstrap = NettyClientBootstrap.getNetty(getApplicationContext(), Constants.TCP_PORT,Constants.TCP_URL,"");

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
