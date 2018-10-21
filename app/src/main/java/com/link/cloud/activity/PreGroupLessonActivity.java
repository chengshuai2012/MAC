package com.link.cloud.activity;

import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.CardView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.SpannedString;
import android.text.style.AbsoluteSizeSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.link.cloud.R;
import com.link.cloud.api.ApiFactory;
import com.link.cloud.api.bean.LessonItemBean;
import com.link.cloud.api.request.EdituserRequest;
import com.link.cloud.base.AppBarActivity;
import com.link.cloud.listener.DialogCancelListener;
import com.link.cloud.utils.DialogUtils;
import com.link.cloud.utils.Utils;
import com.link.cloud.widget.PublicTitleView;
import com.zitech.framework.data.network.response.ApiResponse;
import com.zitech.framework.data.network.subscribe.ProgressSubscriber;
import com.zitech.framework.utils.ToastMaster;

import java.util.Hashtable;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
    EditText inputTel;
    @BindView(R.id.verify_code)
    EditText verifyCode;
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
    private PublicTitleView publicTitle;
    private DialogUtils dialogUtils;
    private String courseReleasePkcode;
    private LessonItemBean lessonItemBean;

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
        setHintSize(inputTel, 36, getResources().getString(R.string.please_input_tel));
        setHintSize(verifyCode, 30, getResources().getString(R.string.please_input_verify));
        verifyCode.setShowSoftInputOnFocus(false);
        inputTel.setShowSoftInputOnFocus(false);
        wechatPrice.setText(lessonItemBean.getCourseReleaseMoney()+"元");
        zhifubaoPrice.setText(lessonItemBean.getCourseReleaseMoney()+"元");
        handyPrice.setText(lessonItemBean.getCourseReleaseMoney()+"元");
        lessonName.setText(lessonItemBean.getFitnessCourseName()+"");
        address.setText(getResources().getString(R.string.address)+lessonItemBean.getAddress());
        lessonTime.setText(getResources().getString(R.string.time)+lessonItemBean.getCoursePlanBegtime());
        peopleCount.setText(lessonItemBean.getNum()+"人");
    }

    public static Bitmap createQRCode(String text, int size) {
        try {
            Hashtable<EncodeHintType, String> hints = new Hashtable<>();
            hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
            BitMatrix bitMatrix = new QRCodeWriter().encode(text,
                    BarcodeFormat.QR_CODE, size, size, hints);
            int[] pixels = new int[size * size];
            for (int y = 0; y < size; y++) {
                for (int x = 0; x < size; x++) {
                    if (bitMatrix.get(x, y)) {
                        pixels[y * size + x] = 0xff000000;
                    } else {
                        pixels[y * size + x] = 0xffffffff;
                    }
                }
            }
            Bitmap bitmap = Bitmap.createBitmap(size, size,
                    Bitmap.Config.ARGB_8888);
            bitmap.setPixels(pixels, 0, size, 0, 0, size, size);
            return bitmap;
        } catch (WriterException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void setHintSize(EditText editText, int size, String hint) {
        String hintStr = hint;
        SpannableString ss = new SpannableString(hintStr);
        AbsoluteSizeSpan ass = new AbsoluteSizeSpan(size, true);
        editText.setHintTextColor(getResources().getColor(R.color.dark_black));
        ss.setSpan(ass, 0, ss.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        editText.setHint(new SpannedString(ss));

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_pre_lesson;
    }

    StringBuilder verify = new StringBuilder();
    StringBuilder builder = new StringBuilder();
    boolean isSendVerify = false;
    String tel_num;
    EdituserRequest edituserRequest;

    @OnClick({R.id.bind_keypad_0, R.id.bind_keypad_1, R.id.bind_keypad_2, R.id.bind_keypad_3, R.id.bind_keypad_4, R.id.bind_keypad_5, R.id.bind_keypad_6, R.id.bind_keypad_7, R.id.bind_keypad_8,
            R.id.bind_keypad_9, R.id.bind_keypad_ok, R.id.bind_keypad_delect, R.id.confirm_bind, R.id.back, R.id.pre, R.id.pay_zhifubao, R.id.pay_wechat, R.id.handy_pay, R.id.send})
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
                if (inputTel.isFocused()) {
                    if (builder.length() <= 11) {
                        builder.append(((TextView) v).getText());
                        inputTel.setText(builder.toString());
                        inputTel.setSelection(builder.length());
                    }

                } else {
                    if (verify.length() <= 11) {
                        verify.append(((TextView) v).getText());
                        verifyCode.setText(verify.toString());
                        verifyCode.setSelection(verify.length());
                    }
                }
                break;
            case R.id.bind_keypad_ok:
                if (inputTel.isFocused()) {
                    builder.delete(0, builder.length());
                    inputTel.setText(builder.toString());
                    inputTel.setSelection(builder.length());
                    setHintSize(inputTel, 36, getResources().getString(R.string.please_input_tel));
                } else {
                    verify.delete(0, verify.length());
                    verifyCode.setText(verify.toString());
                    verifyCode.setSelection(verify.length());
                    setHintSize(verifyCode, 30, getResources().getString(R.string.please_input_verify));
                }
                break;
            case R.id.bind_keypad_delect:
                if (inputTel.isFocused()) {
                    if (builder.length() >= 1) {
                        builder.deleteCharAt(builder.length() - 1);
                        inputTel.setText(builder.toString());
                        inputTel.setSelection(builder.length());
                    }
                } else {
                    if (verify.length() >= 1) {
                        verify.deleteCharAt(verify.length() - 1);
                        verifyCode.setText(verify.toString());
                        verifyCode.setSelection(verify.length());
                    }
                }
                break;
            case R.id.confirm_bind:
                if (isSendVerify) {
                    String code = verifyCode.getText().toString();
                    ApiFactory.binduser(tel_num, code).subscribe(new ProgressSubscriber<ApiResponse<EdituserRequest>>(this) {
                        @Override
                        public void onNext(ApiResponse<EdituserRequest> apiResponse) {
                            loginMiddleOne.setVisibility(View.INVISIBLE);
                            loginMiddleTwo.setVisibility(View.VISIBLE);
                            registerIntroduceFive.setTextColor(getResources().getColor(R.color.red));
                            registerIntroduceTwo.setTextColor(getResources().getColor(R.color.text_register));
                            loginWay.setText(getResources().getString(R.string.choose_pay));
                            edituserRequest = apiResponse.getData();
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
                ApiFactory.getBuyQrcode(courseReleasePkcode).subscribe(new ProgressSubscriber<ApiResponse>(this) {
                    @Override
                    public void onNext(ApiResponse apiResponse) {
                        super.onNext(apiResponse);
                        View pay_wechat = View.inflate(PreGroupLessonActivity.this, R.layout.pay_dialog, null);
                        dialogUtils.showPayDialog(pay_wechat, createQRCode((String) apiResponse.getData(), 220));
                    }
                });

                break;
            case R.id.pay_zhifubao:
                View pay_zhifubao = View.inflate(this, R.layout.pay_dialog, null);
                dialogUtils.showPayDialog(pay_zhifubao, null);
                break;
            case R.id.handy_pay:
                View handy_pay = View.inflate(this, R.layout.pay_conform_dialog, null);
                dialogUtils.showHanyPayDialog(handy_pay);
                break;
            case R.id.send:
                tel_num = inputTel.getText().toString();
                if (Utils.isPhoneNum(tel_num)) {
                    ApiFactory.sendVCode(tel_num).subscribe(new ProgressSubscriber<ApiResponse>(this) {
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

        }
    }

    @Override
    public void dialogCancel() {
        finish();
    }


}
