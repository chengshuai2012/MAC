package com.link.cloud.activity;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
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

import com.dinuscxj.progressbar.CircleProgressBar;
import com.link.cloud.MacApplication;
import com.link.cloud.R;
import com.link.cloud.api.ApiFactory;
import com.link.cloud.api.BaseProgressSubscriber;
import com.link.cloud.api.request.BindFinger;
import com.link.cloud.api.request.EdituserRequest;
import com.link.cloud.base.AppBarActivity;
import com.link.cloud.utils.TTSUtils;
import com.link.cloud.utils.Utils;
import com.zitech.framework.data.network.response.ApiResponse;
import com.zitech.framework.utils.ToastMaster;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import io.realm.Realm;
import okhttp3.RequestBody;

/**
 * Created by OFX002 on 2018/9/21.
 */

public class RegisterActivity extends AppBarActivity {
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
    @BindView(R.id.bind_way)
    TextView bindWay;
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
    @BindView(R.id.bind_way_one)
    LinearLayout bindWayOne;
    @BindView(R.id.bind_way_two)
    LinearLayout bindWayTwo;
    @BindView(R.id.code_intro)
    TextView codeIntro;
    @BindView(R.id.tel_intro)
    TextView telIntro;
    @BindView(R.id.custom_progress)
    CircleProgressBar customProgress;
    @BindView(R.id.bind_veune)
    TextView bindVeune;
    @BindView(R.id.bind_face)
    TextView bindFace;
    @BindView(R.id.choose_bind_way)
    LinearLayout chooseBindWay;
    @BindView(R.id.confirm_bind)
    TextView confirmBind;
    @BindView(R.id.bind_middle_one)
    RelativeLayout bindMiddleOne;
    @BindView(R.id.venue_image)
    CircleImageView venueImage;
    @BindView(R.id.bind_venue_intro)
    TextView bindVenueIntro;
    @BindView(R.id.card_type)
    TextView cardType;
    @BindView(R.id.bind_venue_intro_below)
    TextView bindVenueIntroBelow;
    @BindView(R.id.bind_middle_two)
    RelativeLayout bindMiddleTwo;
    @BindView(R.id.card_info_re)
    RelativeLayout cardInfoRe;
    @BindView(R.id.card_num)
    TextView cardNum;
    @BindView(R.id.bind_finish_info)
    TextView bindFinishInfo;
    @BindView(R.id.bind_middle_three)
    RelativeLayout bindMiddleThree;
    private ValueAnimator animator;
    boolean isSendVerify = false;
    private String tel_num;
    private EdituserRequest edituserRequest;
    @BindView(R.id.code_number)
    EditText code_mumber;
    boolean isTel=true;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    protected void initViews() {
        customProgress.setProgressFormatter(null);
        customProgress.setMax(100);
        registerIntroduceTwo.setTextColor(getResources().getColor(R.color.red));
        hideToolbar();
        initData();
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
                ApiFactory.BindByQrcode(requestBody).subscribe(new BaseProgressSubscriber<ApiResponse<EdituserRequest>>(RegisterActivity.this) {
                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }

                    @Override
                    public void onNext(ApiResponse<EdituserRequest> edituserRequestApiResponse) {
                        super.onNext(edituserRequestApiResponse);
                        bindMiddleOne.setVisibility(View.INVISIBLE);
                        bindMiddleTwo.setVisibility(View.VISIBLE);
                        registerIntroduceThree.setTextColor(getResources().getColor(R.color.red));
                        registerIntroduceTwo.setTextColor(getResources().getColor(R.color.text_register));
                        bindWay.setText(getResources().getString(R.string.bind_veune));
                        simulateProgress();
                        edituserRequest = edituserRequestApiResponse.getData();
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

    private void simulateProgress() {
        animator = ValueAnimator.ofInt(0, 100);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int progress = (int) animation.getAnimatedValue();
                if (customProgress != null) {
                    customProgress.setProgress(progress);
                    int state = MacApplication.getVenueUtils().getState();
                    if (state == 3) {
                        MacApplication.getVenueUtils().workModel();
                        animator.setCurrentPlayTime(0);
                    }
                    if (state == 4) {

                    }
                    if (state != 4 && state != 3) {
                        bindVenueIntro.setText(getResources().getString(R.string.right_finger));
                    }
                    if (progress == 99) {
                        finish();
                    }
                }
            }
        });
        animator.setDuration(40000);
        animator.start();
    }

    StringBuilder verify = new StringBuilder();
    StringBuilder tel = new StringBuilder();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @OnClick({R.id.bind_keypad_0, R.id.bind_keypad_1, R.id.bind_keypad_2, R.id.bind_keypad_3, R.id.bind_keypad_4, R.id.bind_keypad_5, R.id.bind_keypad_6, R.id.bind_keypad_7, R.id.bind_keypad_8,
            R.id.bind_keypad_9, R.id.bind_keypad_ok, R.id.bind_keypad_delect, R.id.confirm_bind, R.id.bind_venue_intro, R.id.back, R.id.input_tel, R.id.verify_code, R.id.send})
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
                            bindMiddleOne.setVisibility(View.INVISIBLE);
                            bindMiddleTwo.setVisibility(View.VISIBLE);
                            registerIntroduceThree.setTextColor(getResources().getColor(R.color.red));
                            registerIntroduceTwo.setTextColor(getResources().getColor(R.color.text_register));
                            bindWay.setText(getResources().getString(R.string.bind_veune));
                            simulateProgress();
                            edituserRequest = apiResponse.getData();
                        }
                    });

                } else {
                    ToastMaster.shortToast(getResources().getString(R.string.verify_first));
                }

                break;
            case R.id.bind_venue_intro:
                bindMiddleTwo.setVisibility(View.INVISIBLE);
                bindMiddleThree.setVisibility(View.VISIBLE);
                registerIntroduceFive.setTextColor(getResources().getColor(R.color.red));
                registerIntroduceThree.setTextColor(getResources().getColor(R.color.text_register));
                bindWay.setText(getResources().getString(R.string.bind_finish));
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

    @Override
    protected void onDestroy() {
        handler.removeMessages(5);
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        isSendVerify = false;
    }


    @Override
    public void modelMsg(int state, String msg) {
        Log.e( "modelMsg: ", state+">>>>>>>>>");
        TTSUtils.getInstance().speak(msg);
        if (state == 3) {
            animator.cancel();
            bindVenueIntro.setText(getResources().getString(R.string.wait_amoment));
            BindFinger bindFinger = new BindFinger();
            bindFinger.setFingerprint(msg);
            bindFinger.setId(edituserRequest.getId());
            bindFinger.setMerchantId(edituserRequest.getMerchantId());
            bindFinger.setUserType(edituserRequest.getUserType());
            bindFinger.setUuid(edituserRequest.getUuid());
            ApiFactory.edituser(bindFinger).subscribe(new BaseProgressSubscriber<ApiResponse>(this) {
                @Override
                public void onNext(ApiResponse apiResponse) {
                    bindMiddleTwo.setVisibility(View.INVISIBLE);
                    bindMiddleThree.setVisibility(View.VISIBLE);
                    registerIntroduceFive.setTextColor(getResources().getColor(R.color.red));
                    registerIntroduceThree.setTextColor(getResources().getColor(R.color.text_register));
                    cardNum.setText(getResources().getString(R.string.now_card) + edituserRequest.getPhone());
                    TTSUtils.getInstance().speak(getString(R.string.bind_ok));
                    int userType = edituserRequest.getUserType();
                    if(userType==1){
                        cardType.setText(getString(R.string.member_card));
                    }else if(userType==2){
                        cardType.setText(getString(R.string.coach_card));
                    }else if(userType==3){
                        cardType.setText(getString(R.string.worker_card));
                    }
                    handler.sendEmptyMessageDelayed(5, 3000);
                }
            });

        }
        if (state == 2) {
            bindVenueIntro.setText(getResources().getString(R.string.same_finger));
        }
        if (state == 1) {
            bindVenueIntro.setText(getResources().getString(R.string.again_finger));
        }
    }

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            finish();
        }
    };
}
