package com.link.cloud.activity;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.CardView;
import android.text.Editable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.SpannedString;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.AbsoluteSizeSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.link.cloud.Constants;
import com.link.cloud.MacApplication;
import com.link.cloud.R;
import com.link.cloud.api.ApiFactory;
import com.link.cloud.api.BaseProgressSubscriber;
import com.link.cloud.api.bean.LessonItemBean;
import com.link.cloud.api.bean.PayBean;
import com.link.cloud.api.bean.SingleUser;
import com.link.cloud.api.request.EdituserRequest;
import com.link.cloud.base.AppBarActivity;
import com.link.cloud.base.BaseActivity;
import com.link.cloud.bean.AllUser;
import com.link.cloud.listener.DialogCancelListener;
import com.link.cloud.listener.MessageListener;
import com.link.cloud.utils.DialogUtils;
import com.link.cloud.utils.Utils;
import com.link.cloud.widget.PublicTitleView;
import com.orhanobut.logger.Logger;
import com.zitech.framework.data.network.response.ApiResponse;
import com.zitech.framework.data.network.subscribe.NoProgressSubscriber;
import com.zitech.framework.data.network.subscribe.ProgressSubscriber;
import com.zitech.framework.utils.ToastMaster;

import org.json.JSONException;
import org.json.JSONObject;
import butterknife.BindView;
import butterknife.OnClick;
import cn.iwgang.countdownview.CountdownView;
import io.realm.Realm;
import io.realm.RealmResults;
import okhttp3.RequestBody;

/**
 * Created by OFX002 on 2018/9/26.
 */

public class PreGroupLessonActivity extends AppBarActivity implements DialogCancelListener {

    @BindView(R.id.binding)
    TextView binding;
    @BindView(R.id.register_introduce_one)
    TextView registerIntroduceOne;
    @BindView(R.id.register_introduce_two)
    TextView registerIntroduceTwo;
    @BindView(R.id.register_introduce_three)
    TextView registerIntroduceThree;
    @BindView(R.id.register_introduce_five)
    TextView registerIntroduceFive;
    @BindView(R.id.back)
    TextView back;
    @BindView(R.id.login_way)
    TextView loginWay;
    @BindView(R.id.login_veune)
    TextView loginVeune;
    @BindView(R.id.login_face)
    TextView loginFace;
    @BindView(R.id.choose_login_way)
    LinearLayout chooseLoginWay;
    @BindView(R.id.login_way_1)
    TextView loginWay1;
    @BindView(R.id.login_way_one)
    LinearLayout loginWayOne;
    @BindView(R.id.bind_way_two)
    LinearLayout bindWayTwo;
    @BindView(R.id.code_intro)
    TextView codeIntro;
    @BindView(R.id.tel_intro)
    TextView telIntro;
    @BindView(R.id.input_tel)
    TextView inputTel;
    @BindView(R.id.verify_code)
    TextView verifyCode;
    @BindView(R.id.send)
    TextView send;
    @BindView(R.id.bind_keypad_1)
    Button bindKeypad1;
    @BindView(R.id.bind_keypad_2)
    Button bindKeypad2;
    @BindView(R.id.bind_keypad_3)
    Button bindKeypad3;
    @BindView(R.id.bind_keypad_4)
    Button bindKeypad4;
    @BindView(R.id.bind_keypad_5)
    Button bindKeypad5;
    @BindView(R.id.bind_keypad_6)
    Button bindKeypad6;
    @BindView(R.id.bind_keypad_7)
    Button bindKeypad7;
    @BindView(R.id.bind_keypad_8)
    Button bindKeypad8;
    @BindView(R.id.bind_keypad_9)
    Button bindKeypad9;
    @BindView(R.id.bind_keypad_delect)
    Button bindKeypadDelect;
    @BindView(R.id.bind_keypad_0)
    Button bindKeypad0;
    @BindView(R.id.bind_keypad_ok)
    Button bindKeypadOk;
    @BindView(R.id.confirm_bind)
    TextView confirmBind;
    @BindView(R.id.login_middle_one)
    RelativeLayout loginMiddleOne;
    @BindView(R.id.login_middle_two)
    LinearLayout loginMiddleTwo;
    @BindView(R.id.lesson_name)
    TextView lessonName;
    @BindView(R.id.should_pay)
    TextView shouldPay;
    @BindView(R.id.lesson_time)
    TextView lessonTime;
    @BindView(R.id.address)
    TextView address;
    @BindView(R.id.people_count)
    TextView peopleCount;
    @BindView(R.id.pay_way)
    TextView payWay;
    @BindView(R.id.pay_time)
    TextView payTime;
    @BindView(R.id.handy_price)
    TextView handyPrice;
    @BindView(R.id.handy_pay)
    CardView handyPay;
    @BindView(R.id.wechat_price)
    TextView wechatPrice;
    @BindView(R.id.pay_wechat)
    LinearLayout payWechat;
    @BindView(R.id.zhifubao_price)
    TextView zhifubaoPrice;
    @BindView(R.id.pay_zhifubao)
    LinearLayout payZhifubao;
    @BindView(R.id.pre)
    TextView pre;
    @BindView(R.id.pay_restTime)
    CountdownView payRestTime;
    @BindView(R.id.code_number)
    EditText code_mumber;
    private PublicTitleView publicTitle;
    private DialogUtils dialogUtils;
    private String courseReleasePkcode;
    private LessonItemBean lessonItemBean;
    boolean isTel=true;
    String uuid;
    private int minute;
    private int second;
    private long payRest;
    MesReceiver mesReceiver;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void initViews() {
        setTitle(R.drawable.handy_logo);
        registerIntroduceTwo.setTextColor(getResources().getColor(R.color.red));
        registerIntroduceTwo.setText(getResources().getString(R.string.choose_login_1));
        registerIntroduceThree.setVisibility(View.GONE);
        registerIntroduceFive.setText(getResources().getString(R.string.choose_pay_2));
        binding.setText(getResources().getString(R.string.pay_ticket));
        String lessonItemBeanJson = getIntent().getStringExtra("courseReleasePkcode");
        Gson gson = new Gson();
        lessonItemBean = gson.fromJson(lessonItemBeanJson, LessonItemBean.class);
        courseReleasePkcode = lessonItemBean.getCourseReleasePkcode();
        dialogUtils = DialogUtils.getDialogUtils(this, this);
        wechatPrice.setText(lessonItemBean.getCourseReleaseMoney() + "元");
        zhifubaoPrice.setText(lessonItemBean.getCourseReleaseMoney() + "元");
        handyPrice.setText(lessonItemBean.getCourseReleaseMoney() + "元");
        lessonName.setText(lessonItemBean.getFitnessCourseName() + "");
        address.setText(getResources().getString(R.string.address) + lessonItemBean.getAddress());
        lessonTime.setText(getResources().getString(R.string.time) + lessonItemBean.getCoursePlanBegtime());
        peopleCount.setText(lessonItemBean.getNum() + "人");
        mesReceiver=new MesReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Constants.MSG);
        registerReceiver(mesReceiver, intentFilter);
        initData();
    }
    public class MesReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String msg = intent.getStringExtra("msg");
            String type  =null;
            JSONObject object=null;
            try {
                object = new JSONObject(msg);
                type = object.getString("msgType");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if("BUY_COURSE_NOTIFY".equals(type)){
                Gson gson  = new Gson();
                PayBean payBean = gson.fromJson(msg, PayBean.class);
                String Payuuid = payBean.getData().getUserInfo().getUuid();
                Logger.e(uuid);
                Logger.e(Payuuid);
                dialogUtils.dissMiss();
                View view = View.inflate(PreGroupLessonActivity.this,R.layout.pay_ok_dialog,null);
                dialogUtils.showVeuneOkDialog(view);
                if(uuid.equals(Payuuid)){
                    handler.sendEmptyMessageDelayed(4,2000);
                }
            }
        }

    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_pre_lesson;
    }

    StringBuilder verify = new StringBuilder();
    StringBuilder tel = new StringBuilder();
    boolean isSendVerify = false;
    String tel_num;
    EdituserRequest edituserRequest;

    @OnClick({R.id.bind_keypad_0, R.id.bind_keypad_1, R.id.bind_keypad_2, R.id.bind_keypad_3, R.id.bind_keypad_4, R.id.bind_keypad_5, R.id.bind_keypad_6, R.id.bind_keypad_7, R.id.bind_keypad_8,
            R.id.bind_keypad_9, R.id.bind_keypad_ok, R.id.bind_keypad_delect, R.id.confirm_bind, R.id.back, R.id.pre, R.id.pay_zhifubao, R.id.pay_wechat, R.id.handy_pay, R.id.send,R.id.input_tel,R.id.verify_code})
    public void OnClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.bind_keypad_0:
            case R.id.bind_keypad_1:
            case R.id.bind_keypad_2:
            case R.id.bind_keypad_3:
            case R.id.bind_keypad_4:
            case R.id.bind_keypad_5:
            case R.id.bind_keypad_6:
            case R.id.bind_keypad_7:
            case R.id.bind_keypad_8:
            case R.id.bind_keypad_9:
                if (isTel) {
                    if (tel.length() < 11) {
                        try {
                            tel.append(((TextView) v).getText());
                            inputTel.setText(tel.toString());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    if (verify.length() < 4) {
                        try {
                            verify.append(((TextView) v).getText());
                            verifyCode.setText(verify.toString());
                        }  catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                break;
            case R.id.bind_keypad_ok:
                if (isTel) {
                    try {
                        tel.delete(0, tel.length());
                        inputTel.setText(tel.toString());
                        inputTel.setText(getResources().getString(R.string.please_input_tel));
                    } catch (Resources.NotFoundException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        verify.delete(0, verify.length());
                        verifyCode.setText(verify.toString());
                        verifyCode.setText(getResources().getString(R.string.please_input_verify));
                    } catch (Resources.NotFoundException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case R.id.bind_keypad_delect:
                if (isTel) {
                    if (tel.length() >=1) {
                        try {
                            tel.deleteCharAt(tel.length() - 1);
                            if(tel.length()>=1){

                                inputTel.setText(tel.toString());
                            }else {
                                inputTel.setText(getResources().getString(R.string.please_input_tel));
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    if (verify.length() >= 1) {
                        try {
                            verify.deleteCharAt(verify.length() - 1);
                            if(verify.length()>=1){

                                verifyCode.setText(verify.toString());
                            }else {
                                verifyCode.setText(getResources().getString(R.string.please_input_verify));
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                break;
            case R.id.confirm_bind:
                if (isSendVerify) {
                    String code = verifyCode.getText().toString();
                    if(TextUtils.isEmpty(code)){
                        ToastMaster.shortToast(getResources().getString(R.string.verify_input));
                        return;
                    }
                    ApiFactory.binduser(tel_num, code).subscribe(new BaseProgressSubscriber<ApiResponse<EdituserRequest>>(this) {
                        @Override
                        public void onNext(ApiResponse<EdituserRequest> apiResponse) {
                            loginMiddleOne.setVisibility(View.INVISIBLE);
                            loginMiddleTwo.setVisibility(View.VISIBLE);
                            registerIntroduceFive.setTextColor(getResources().getColor(R.color.red));
                            registerIntroduceTwo.setTextColor(getResources().getColor(R.color.text_register));
                            loginWay.setText(getResources().getString(R.string.choose_pay));
                            uuid = apiResponse.getData().getUuid();
                            edituserRequest = apiResponse.getData();
                            long time = (long) 4 * 60 * 1000;
                            payRestTime.start(time);
                            payRestTime.setOnCountdownEndListener(new CountdownView.OnCountdownEndListener() {
                                @Override
                                public void onEnd(CountdownView cv) {
                                    finish();
                                }
                            });
                        }
                    });

                } else {
                    ToastMaster.shortToast(getResources().getString(R.string.verify_first));
                }

                break;
            case R.id.pre:
                View pre = View.inflate(this, R.layout.pre_dialog, null);
                dialogUtils.showPreDialog(pre);
                break;
            case R.id.pay_wechat:
                minute = payRestTime.getMinute();
                second = payRestTime.getSecond();
                payRest = (minute *60+ second)*1000;
                Logger.e(payRest+"");
                ApiFactory.getBuyQrcode(courseReleasePkcode).subscribe(new BaseProgressSubscriber<ApiResponse>(this) {
                    @Override
                    public void onNext(ApiResponse apiResponse) {
                        super.onNext(apiResponse);
                        View pay_wechat = View.inflate(PreGroupLessonActivity.this, R.layout.pay_dialog, null);
                        dialogUtils.showPayDialog(pay_wechat, Utils.createQRCode((String) apiResponse.getData(), 220), lessonItemBean.getCourseReleaseMoney(), getString(R.string.work_intro_wechat), payRest);
                    }
                });

                break;
            case R.id.pay_zhifubao:
                minute = payRestTime.getMinute();
                second = payRestTime.getSecond();
                payRest =  (minute *60+ second)*1000;
                View pay_zhifubao = View.inflate(this, R.layout.pay_dialog, null);
                dialogUtils.showPayDialog(pay_zhifubao, null, "12", getString(R.string.work_intro), payRest);
                break;
            case R.id.handy_pay:
                minute = payRestTime.getMinute();
                second = payRestTime.getSecond();
                payRest = minute *60+ second;
                View handy_pay = View.inflate(this, R.layout.pay_conform_dialog, null);
                dialogUtils.showHanyPayDialog(handy_pay, "￥" + lessonItemBean.getCourseReleaseMoney());
                break;
            case R.id.send:
                tel_num = inputTel.getText().toString();
                if (Utils.isPhoneNum(tel_num)) {
                    ApiFactory.sendVCode(tel_num).subscribe(new BaseProgressSubscriber<ApiResponse>(this) {
                        @Override
                        public void onNext(ApiResponse apiResponse) {
                            isSendVerify = true;
                            ToastMaster.shortToast(getResources().getString(R.string.verify_has_send));
                        }
                    });
                } else {
                    ToastMaster.shortToast(getResources().getString(R.string.error_tel));
                }

                break;
            case  R.id.input_tel:
                isTel=true;
                break;
            case  R.id.verify_code:
                isTel =false;
                break;

        }
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    protected void initData() {
        code_mumber.setFocusable(true);
        code_mumber.setCursorVisible(true);
        code_mumber.setFocusableInTouchMode(true);
        code_mumber.requestFocus();
        code_mumber.setShowSoftInputOnFocus(false);
        /**
         * EditText编辑框内容发生变化时的监听回调
         */
        code_mumber.addTextChangedListener(new EditTextChangeListener());
    }
    public class EditTextChangeListener implements TextWatcher {
        long lastTime;
        /**
         * 编辑框的内容发生改变之前的回调方法
         */
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }
        /**
         * 编辑框的内容正在发生改变时的回调方法 >>用户正在输入
         * 我们可以在这里实时地 通过搜索匹配用户的输入
         */
        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }
        /**
         * 编辑框的内容改变以后,用户没有继续输入时 的回调方法
         */
        @Override
        public void afterTextChanged(Editable editable) {
            String str=code_mumber.getText().toString();
            if (str.contains("\n")) {
                if(System.currentTimeMillis()-lastTime<1500){
                    code_mumber.setText("");
                    return;
                }
                lastTime=System.currentTimeMillis();
                JSONObject object =null;
                try {
                    object= new JSONObject(code_mumber.getText().toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if (object==null){
                    return;
                }
                RequestBody requestBody=RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),object.toString());
                ApiFactory.BindByQrcode(requestBody).subscribe(new BaseProgressSubscriber<ApiResponse<EdituserRequest>>(PreGroupLessonActivity.this) {
                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }

                    @Override
                    public void onNext(ApiResponse<EdituserRequest> edituserRequestApiResponse) {
                        super.onNext(edituserRequestApiResponse);
                        loginMiddleOne.setVisibility(View.INVISIBLE);
                        loginMiddleTwo.setVisibility(View.VISIBLE);
                        registerIntroduceFive.setTextColor(getResources().getColor(R.color.red));
                        registerIntroduceTwo.setTextColor(getResources().getColor(R.color.text_register));
                        loginWay.setText(getResources().getString(R.string.choose_pay));
                        uuid = edituserRequestApiResponse.getData().getUuid();
                        edituserRequest = edituserRequestApiResponse.getData();
                        long time = (long) 4 * 60 * 1000;
                        payRestTime.start(time);
                        payRestTime.setOnCountdownEndListener(new CountdownView.OnCountdownEndListener() {
                            @Override
                            public void onEnd(CountdownView cv) {
                                finish();
                            }
                        });
                    }

                    @Override
                    public void onCompleted() {
                        super.onCompleted();
                    }
                });
                code_mumber.setText("");
            }
        }
    }

    @Override
    public void dialogCancel() {
        finish();
    }

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            handler.removeMessages(3);
            switch (msg.what) {
                case 3:
                    if(dialogUtils.isShowing()) {
                        int state = MacApplication.getVenueUtils().getState();
                        if (state == 3) {
                            RealmResults<AllUser> all = realm.where(AllUser.class).equalTo("uuid", uuid).findAll();
                            Logger.e(all.size() + "");
                            Logger.e(uuid + "");
                            final String uid = MacApplication.getVenueUtils().identifyNewImg(realm.copyFromRealm(all));
                            if (uuid.equals(uid)) {
                                ApiFactory.payByRest(courseReleasePkcode, uid).subscribe(new ProgressSubscriber<ApiResponse>(PreGroupLessonActivity.this) {
                                    @Override
                                    public void onNext(ApiResponse apiResponse) {
                                        super.onNext(apiResponse);
                                        View view = View.inflate(PreGroupLessonActivity.this, R.layout.pay_ok_dialog, null);
                                        dialogUtils.showVeuneOkDialog(view);
                                        handler.sendEmptyMessageDelayed(4, 2000);
                                    }

                                    @Override
                                    public void onError(Throwable e) {
                                        super.onError(e);
                                        View view = View.inflate(PreGroupLessonActivity.this, R.layout.pay_fail, null);
                                        dialogUtils.showVeunePayFailDialog(view, getResources().getString(R.string.please_confirm_bind));
                                    }
                                });
                            } else {
                                View view = View.inflate(PreGroupLessonActivity.this, R.layout.pay_fail, null);
                                dialogUtils.showVeunePayFailDialog(view, getResources().getString(R.string.please_confirm_bind));
//                            View view = View.inflate(PreGroupLessonActivity.this, R.layout.verify_fail, null);
//                            dialogUtils.showVeuneFailDialog(view, getString(R.string.verify_fail));
                            }
                        }
                        handler.sendEmptyMessageDelayed(3, 1000);
                    }
                    break;
                case 4:
                    dialogUtils.dissMiss();
                    finish();
                    break;
            }

        }
    };

    @Override
    public void onVenuePay() {
        handler.sendEmptyMessage(3);
        View pay_veune_dialog = View.inflate(this, R.layout.pay_veune_dialog, null);
        dialogUtils.showVeunePayDialog(pay_veune_dialog);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mesReceiver);
        handler.removeMessages(3);
    }

}
