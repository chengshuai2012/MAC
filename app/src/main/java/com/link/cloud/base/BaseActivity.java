package com.link.cloud.base;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.google.gson.Gson;
import com.link.cloud.Constants;
import com.link.cloud.MacApplication;
import com.link.cloud.R;
import com.link.cloud.activity.PreGroupLessonActivity;
import com.link.cloud.api.bean.PayBean;
import com.link.cloud.listener.MessageListener;
import com.link.cloud.utils.Utils;
import com.link.cloud.utils.Venueutils;
import com.link.cloud.widget.SimpleStyleDialog;
import com.orhanobut.logger.Logger;
import com.tbruyelle.rxpermissions.RxPermissions;
import com.zitech.framework.utils.ViewUtils;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.realm.Realm;
import rx.functions.Action1;


/**
 * Created by OFX002 on 2018/9/20.
 */

public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener, Venueutils.VenueCallBack {

    private Unbinder bind;
    private SimpleStyleDialog denyDialog;
    public Realm realm;
    MessageListener messageListner;
    MesReceiver mesReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        this.setContentView(this.getLayoutId());
        realm= Realm.getDefaultInstance();
        bind = ButterKnife.bind(this);
        mesReceiver=new MesReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Constants.MSG);
        registerReceiver(mesReceiver, intentFilter);
        MacApplication.getVenueUtils().initVenue(this, this, false);
        initViews();

    }
    public class MesReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String msg = intent.getStringExtra("msg");
            String type  =null;
            try {
                JSONObject object = new JSONObject(msg);
                type = object.getString("msgType");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if(messageListner!=null){
                if("BUY_COURSE_NOTIFY".equals(type)){
                        messageListner.onMessageReciever(type,msg);
                }
            }

        }
    }
    protected abstract void initViews();

    protected abstract int getLayoutId();

    @Override
    protected void onStart() {
        super.onStart();

    }

    protected void setMessageListner(MessageListener messageListner){
        this.messageListner = messageListner;
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
        realm.close();
        unregisterReceiver(mesReceiver);
        try {
            MacApplication.getVenueUtils().unBindService();
        } catch (Exception e) {

        }

    }

    @Override
    protected void onStop() {
        super.onStop();
        Logger.i("AVChatRestart", this.getClass() + " onStop");
    }

    protected void onResume() {
        super.onResume();

    }

    protected void onPause() {
        super.onPause();

    }

    /**
     * @param cls 目标activity
     *            跳转并finish当前activity
     * @throws ActivityNotFoundException
     */
    public void skipActivity(Class<?> cls) {
        showActivity(cls);
        ViewUtils.anima(ViewUtils.RIGHT_IN, this);
        finish();
    }

    /**
     * @param cls
     * @param extras
     */
    public void skipActivity(Class<?> cls, Bundle extras) {
        Intent intent = new Intent();
        intent.putExtras(extras);
        intent.setClass(this, cls);
        startActivity(intent);
        ViewUtils.anima(ViewUtils.RIGHT_IN, this);
        finish();
    }

    public void showActivityForResult(Class<?> cls, int requestCode) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        super.startActivityForResult(intent, requestCode);
        ViewUtils.anima(ViewUtils.RIGHT_IN, this);
    }

    public void showActivityForResult(Class<?> cls, Bundle bundle, int requestCode) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        intent.putExtras(bundle);
        super.startActivityForResult(intent, requestCode);
        ViewUtils.anima(ViewUtils.RIGHT_IN, this);

    }

    public void showActivity(Class<?> cls) {
        Intent intent = new Intent();


        intent.setClass(this, cls);
        super.startActivity(intent);
        ViewUtils.anima(ViewUtils.RIGHT_IN, this);

    }

    public void showActivity(Class<?> cls, Bundle extras) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        intent.putExtras(extras);
        super.startActivity(intent);
        ViewUtils.anima(ViewUtils.RIGHT_IN, this);
    }

    public void showActivity(Class<?> cls, String name, String extras) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        intent.putExtra(name, extras);
        super.startActivity(intent);
        ViewUtils.anima(ViewUtils.RIGHT_IN, this);
    }

    @Override
    public void finish() {
        super.finish();
        applyCloseTransition();
    }

    protected void applyCloseTransition() {
        ViewUtils.anima(ViewUtils.RIGHT_IN, this);
    }

    @Override
    public void onClick(View v) {

    }

    //请求权限
    protected void requestRxPermissions(final String denyText, final String... permissions) {
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.request(permissions).subscribe(new Action1<Boolean>() {
            @Override
            public void call(Boolean granted) {
                if (granted) {
                    onPermissionGranted(permissions);
                } else {
                    showDeniedDialog(denyText, permissions);
                }
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {

            }
        });
    }

    protected void onPermissionGranted(String... permissions) {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {

            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    private void showDeniedDialog(final String denyText, final String... permissions) {
        if (denyDialog == null) {
            denyDialog = new SimpleStyleDialog(this, denyText);
            denyDialog.setCancelButtonText("取消");
            denyDialog.setOnPositiveButtonClickListener(new SimpleStyleDialog.OnPositiveButtonClickListener() {
                @Override
                public void onClick(Dialog dialog) {
                    requestRxPermissions(denyText, permissions);
                }
            });
            denyDialog.setPositiveButtonText("去开启");
        } else {
            denyDialog.setContent(denyText);
        }
        if (!denyDialog.isShowing()) {
            denyDialog.show();
        }
    }

    public Fragment showFragment(Class<? extends Fragment> fragmentClass) {
        return Utils.replace(getSupportFragmentManager(), R.id.content_frame, fragmentClass);
    }

    public Fragment showFragment(Class<? extends Fragment> fragmentClass, Bundle bundle) {
        return Utils.replace(getSupportFragmentManager(), fragmentClass, R.id.content_frame, bundle);
    }

    public Context getContext() {
        return this;
    }

    @TargetApi(19)
    protected void setTranslucentStatus(boolean on) {
        if (!(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)) return;
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    @Override
    public void modelMsg(int state, String msg) {
        Logger.e(state + msg);
    }

}
