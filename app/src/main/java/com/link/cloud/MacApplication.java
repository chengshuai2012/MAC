package com.link.cloud;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.iflytek.cloud.Setting;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechUtility;
import com.link.cloud.activity.SplashActivity;
import com.link.cloud.utils.TimeService;
import com.link.cloud.utils.Venueutils;
import com.link.cloud.widget.MyLoadViewFactory;
import com.orhanobut.logger.Logger;
import com.shizhefei.mvc.MVCHelper;
import com.zitech.framework.BaseApplication;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MacApplication extends BaseApplication {

    private User user;
    private Handler mainThreadHandler;
    public static Venueutils venueUtils;
    public  int count =0;
    public static final String COUNT_CHANGE = "change_count";
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
        Thread.setDefaultUncaughtExceptionHandler(restartHandler);
        Intent intent = new Intent(getApplicationContext(), TimeService.class);
        startService(intent);
        registerLifeCircle();
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

    private void registerLifeCircle(){
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle bundle) {

            }
            @Override
            public void onActivityStarted(Activity activity) {
                count++;
                Log.e("onActivityStarted: ",count+"" );
                Intent countIntent = new Intent(COUNT_CHANGE);
                countIntent.putExtra("count",count);
                sendBroadcast(countIntent);
            }
            @Override
            public void onActivityResumed(Activity activity) {

            }
            @Override
            public void onActivityPaused(Activity activity) {

            }
            @Override
            public void onActivityStopped(Activity activity) {
                count--;
                Log.e("onActivityStarted: ",count+"" );
                Intent countIntent = new Intent(COUNT_CHANGE);
                countIntent.putExtra("count",count);
                sendBroadcast(countIntent);
            }
            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
            }
            @Override
            public void onActivityDestroyed(Activity activity) {

            }
        });

    }
    private Thread.UncaughtExceptionHandler restartHandler = new Thread.UncaughtExceptionHandler() {
        public void uncaughtException(Thread thread, Throwable ex) {
            Throwable cause = ex.getCause();
            StringBuilder builder = new StringBuilder();
            builder.append(ex.getCause().toString()+"\r\n");
            for(int x=0;x<cause.getStackTrace().length;x++){
                builder.append("FileName:"+cause.getStackTrace()[x].getFileName()+">>>>Method:"+cause.getStackTrace()[x].getMethodName()+">>>>FileLine:"+cause.getStackTrace()[x].getLineNumber()+"\r\n");
            }

            Logger.e(builder.toString());
            restartApp();
        }
    };
    public void restartApp() {
        Intent intent = new Intent(this, SplashActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        this.startActivity(intent);
        android.os.Process.killProcess(android.os.Process.myPid());  //结束进程之前可以把你程序的注销或者退出代码放在这段代码之前
    }
    public static MacApplication getInstance() {
        return (MacApplication) BaseApplication.getInstance();
    }




}
