package com.link.cloud.activity;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageFormat;
import android.graphics.Rect;
import android.graphics.YuvImage;
import android.hardware.Camera;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.aip.entity.ARGBImg;
import com.baidu.aip.manager.FaceDetector;
import com.baidu.aip.manager.FaceSDKManager;
import com.baidu.aip.utils.FeatureUtils;
import com.dinuscxj.progressbar.CircleProgressBar;
import com.guo.android_extend.java.ExtByteArrayOutputStream;
import com.link.cloud.Constants;
import com.link.cloud.MacApplication;
import com.link.cloud.R;
import com.link.cloud.api.ApiFactory;
import com.link.cloud.api.BaseProgressSubscriber;
import com.link.cloud.api.bean.BindFaceRequest;
import com.link.cloud.api.bean.BindUserFace;
import com.link.cloud.api.request.BindFinger;
import com.link.cloud.api.request.EdituserRequest;
import com.link.cloud.base.BaseActivity;
import com.link.cloud.bean.AllUser;
import com.link.cloud.bean.UserFace;
import com.link.cloud.listener.DialogCancelListener;
import com.link.cloud.utils.DialogUtils;
import com.link.cloud.utils.HexUtil;
import com.link.cloud.utils.TTSUtils;
import com.link.cloud.utils.Utils;
import com.link.cloud.widget.CameraFrameData;
import com.link.cloud.widget.CameraGLSurfaceView;
import com.link.cloud.widget.CameraSurfaceView;
import com.zitech.framework.data.network.response.ApiResponse;
import com.zitech.framework.utils.ToastMaster;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import io.realm.Realm;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by OFX002 on 2018/9/21.
 */

public class RegisterActivity extends BaseActivity implements View.OnTouchListener, CameraSurfaceView.OnCameraListener, DialogCancelListener {
    @BindView(R.id.member)
    TextView member;
    @BindView(R.id.manager)
    TextView manager;
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
    @BindView(R.id.bind_veune_container)
    RelativeLayout bindVeuneContainer;
    @BindView(R.id.custom_progress_face)
    CircleProgressBar customProgressFace;
    @BindView(R.id.surfaceView)
    CameraSurfaceView surfaceView;
    @BindView(R.id.sv_camera_surfaceview)
    CameraGLSurfaceView svCameraSurfaceview;
    @BindView(R.id.bind_face_container)
    RelativeLayout bindFaceContainer;
    @BindView(R.id.bind_face_intro)
    TextView bindFaceIntro;
    @BindView(R.id.bind_face_intro_below)
    TextView bindFaceIntroBelow;
    private ValueAnimator animator;
    boolean isSendVerify = false;
    private String tel_num;
    private EdituserRequest edituserRequest;
    @BindView(R.id.code_number)
    EditText code_mumber;
    boolean isTel = true;
    int bindType = 1;
    private Camera mCamera;
    private String TAG ="RegisterActivity";
    private int mWidth;
    private int mHeight;
    private int mFormat;
    DialogUtils dialogUtils;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    protected void initViews() {
        customProgress.setProgressFormatter(null);
        customProgress.setMax(100);
        customProgressFace.setProgressFormatter(null);
        customProgressFace.setMax(100);
        registerIntroduceTwo.setTextColor(getResources().getColor(R.color.red));
        dialogUtils=DialogUtils.getDialogUtils(this,this);
        initData();
        setCameraView();
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return false;
    }
    @Override
    public Camera setupCamera() {
        // TODO Auto-generated method stub
        mCamera = Camera.open(Camera.CameraInfo.CAMERA_FACING_BACK);
        try {
            Camera.Parameters parameters = mCamera.getParameters();
            parameters.setPreviewSize(mWidth, mHeight);
            parameters.setPreviewFormat(mFormat);
            mCamera.setDisplayOrientation(90);
            for (Camera.Size size : parameters.getSupportedPreviewSizes()) {
                Log.d(TAG, "SIZE:" + size.width + "x" + size.height);
            }
            for (Integer format : parameters.getSupportedPreviewFormats()) {
                Log.d(TAG, "FORMAT:" + format);
            }

            List<int[]> fps = parameters.getSupportedPreviewFpsRange();
            for (int[] count : fps) {
                Log.d(TAG, "T:");
                for (int data : count) {
                    Log.d(TAG, "V=" + data);
                }
            }
            mCamera.setParameters(parameters);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (mCamera != null) {
            mWidth = mCamera.getParameters().getPreviewSize().width;
            mHeight = mCamera.getParameters().getPreviewSize().height;
        }
        return mCamera;
    }

    @Override
    public void setupChanged(int format, int width, int height) {

    }

    @Override
    public boolean startPreviewImmediately() {
        return true;
    }

    @Override
    public Object onPreview(byte[] data, int width, int height, int format, long timestamp) {
        clone = data.clone();
        return null;
    }

    @Override
    public void onAfterRender(CameraFrameData data) {

    }

    @Override
    public void onBeforeRender(CameraFrameData data) {

    }

    @Override
    public void dialogCancel() {
        member.setBackground(getResources().getDrawable(R.drawable.border_red));
        manager.setBackground(null);
        member.setTextColor(getResources().getColor(R.color.almost_white));
        manager.setTextColor(getResources().getColor(R.color.text_gray));
    }

    @Override
    public void onVenuePay() {

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
            String str = code_mumber.getText().toString();
            if (str.contains("\n")) {
                if (System.currentTimeMillis() - lastTime < 1500) {
                    code_mumber.setText("");
                    return;
                }
                lastTime = System.currentTimeMillis();
                JSONObject object = null;
                try {
                    object = new JSONObject(code_mumber.getText().toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if (object == null) {
                    return;
                }
                RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), object.toString());
                ApiFactory.BindByQrcode(requestBody).subscribe(new BaseProgressSubscriber<ApiResponse<EdituserRequest>>(RegisterActivity.this) {
                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }

                    @Override
                    public void onNext(ApiResponse<EdituserRequest> edituserRequestApiResponse) {
                        super.onNext(edituserRequestApiResponse);
                        edituserRequest = edituserRequestApiResponse.getData();
                        setBindNext();
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
                    if (state != 4 && state != 3) {
                        bindVenueIntro.setText(getResources().getString(R.string.right_finger));
                    }
                    if (progress == 99) {
                        isSendVerify =false;
                        edituserRequest=null;
                        bindMiddleTwo.setVisibility(View.INVISIBLE);
                        bindMiddleThree.setVisibility(View.INVISIBLE);
                        bindMiddleOne.setVisibility(View.VISIBLE);
                        verify.delete(0, verify.length());
                        tel.delete(0,tel.length());
                        verifyCode.setText(getResources().getString(R.string.please_input_verify));
                        inputTel.setText(getResources().getString(R.string.please_input_tel));
                        animator.cancel();
                    }
                }
            }
        });
        animator.setDuration(40000);
        animator.start();
    }

    private void simulateProgressFace() {
        animator = ValueAnimator.ofInt(0, 100);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int progress = (int) animation.getAnimatedValue();
                if (customProgressFace != null) {
                    customProgressFace.setProgress(progress);
                    if (progress == 99) {
                        isSendVerify =false;
                        verify.delete(0, verify.length());
                        tel.delete(0,tel.length());
                        verifyCode.setText(getResources().getString(R.string.please_input_verify));
                        inputTel.setText(getResources().getString(R.string.please_input_tel));
                        edituserRequest=null;
                        bindMiddleTwo.setVisibility(View.INVISIBLE);
                        bindMiddleThree.setVisibility(View.INVISIBLE);
                        bindMiddleOne.setVisibility(View.VISIBLE);
                        animator.cancel();
                    }
                }
            }
        });
        animator.setDuration(40000);
        animator.start();
    }
    private void register(final String filePath) {

        Executors.newSingleThreadExecutor().submit(new Runnable() {

            @Override
            public void run() {
                ARGBImg argbImg = FeatureUtils.getARGBImgFromPath(filePath);
                byte[] bytes = new byte[2048];
                int ret = 0;
                Log.e(TAG, "run: "+ret);
                ret = FaceSDKManager.getInstance().getFaceFeature().faceFeature(argbImg, bytes, 50);
                Log.e(TAG, "run: "+ret);
                if (ret == FaceDetector.NO_FACE_DETECTED) {
                    Log.e("aaaa","人脸太小（必须打于最小检测人脸minFaceSize），或者人脸角度太大，人脸不是朝上");
                } else if (ret != -1) {
                    final BindFaceRequest bindFaceRequest = new BindFaceRequest() ;
                    long l = System.currentTimeMillis()+8*60*60*1000;
                    SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Long time1=new Long(l);
                    String d = format.format(time1);
                    bindFaceRequest.setCreateTime(d);
                    bindFaceRequest.setFace(HexUtil.bytesToHexString(bytes));
                    bindFaceRequest.setFaceBase64(Utils.imageToBase64(filePath));
                    bindFaceRequest.setId(edituserRequest.getId());
                    bindFaceRequest.setMerchantId(edituserRequest.getMerchantId());
                    bindFaceRequest.setPhone(edituserRequest.getPhone());
                    bindFaceRequest.setUserType(edituserRequest.getUserType());
                    bindFaceRequest.setUuid(edituserRequest.getUuid());
                    Log.e(TAG, "run: "+">>>>>>>>>>>");
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ApiFactory.bindUserFace(bindFaceRequest).subscribe(new BaseProgressSubscriber<ApiResponse<BindUserFace>>(RegisterActivity.this) {
                                @Override
                                public void onError(Throwable e) {
                                    Log.e(TAG, "onError: "+e.getMessage());
                                }

                                @Override
                                public void onStart() {
                                    Log.e(TAG, "onStart: ");
                                }

                                @Override
                                public void onNext(ApiResponse<BindUserFace> bindUserFaceApiResponse) {
                                    bindMiddleTwo.setVisibility(View.INVISIBLE);
                                    bindMiddleThree.setVisibility(View.VISIBLE);
                                    registerIntroduceFive.setTextColor(getResources().getColor(R.color.red));
                                    registerIntroduceThree.setTextColor(getResources().getColor(R.color.text_register));
                                    cardNum.setText(getResources().getString(R.string.now_card) + edituserRequest.getPhone());
                                    TTSUtils.getInstance().speak(getString(R.string.bind_ok));
                                    int userType = edituserRequest.getUserType();
                                    if (userType == 1) {
                                        cardType.setText(getString(R.string.member_card));
                                    } else if (userType == 2) {
                                        cardType.setText(getString(R.string.coach_card));
                                    } else if (userType == 3) {
                                        cardType.setText(getString(R.string.worker_card));
                                    }
                                }

                                @Override
                                public void onCompleted() {
                                    Log.e(TAG, "onCompleted: ");
                                }
                            });
                        }
                    });

                } else {
                    Log.e("aaaa","抽取特征失败");
                }
            }
        });
    }
    StringBuilder verify = new StringBuilder();
    StringBuilder tel = new StringBuilder();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @OnClick({R.id.bind_keypad_0, R.id.bind_keypad_1, R.id.bind_keypad_2, R.id.bind_keypad_3, R.id.bind_keypad_4, R.id.bind_keypad_5, R.id.bind_keypad_6, R.id.bind_keypad_7, R.id.bind_keypad_8,
            R.id.bind_keypad_9, R.id.bind_keypad_ok, R.id.bind_keypad_delect, R.id.confirm_bind, R.id.bind_venue_intro, R.id.back, R.id.input_tel, R.id.verify_code, R.id.send, R.id.bind_face, R.id.bind_veune,R.id.bind_face_intro_below
    ,R.id.member,R.id.manager})
    public void OnClick(View v) {
        switch (v.getId()) {
            case R.id.member:
                member.setBackground(getResources().getDrawable(R.drawable.border_red));
                manager.setBackground(null);
                member.setTextColor(getResources().getColor(R.color.almost_white));
                manager.setTextColor(getResources().getColor(R.color.text_gray));
                break;
            case R.id.manager:
                View view = View.inflate(RegisterActivity.this, R.layout.psw_dialog, null);
                dialogUtils.showPsdDialog(view);
                manager.setBackground(getResources().getDrawable(R.drawable.border_red));
                member.setBackground(null);
                member.setTextColor(getResources().getColor(R.color.text_gray));
                manager.setTextColor(getResources().getColor(R.color.almost_white));
                break;
            case R.id.back:
                isSendVerify =false;
                edituserRequest=null;
                bindMiddleTwo.setVisibility(View.INVISIBLE);
                bindMiddleThree.setVisibility(View.INVISIBLE);
                bindMiddleOne.setVisibility(View.VISIBLE);
                verify.delete(0, verify.length());
                tel.delete(0,tel.length());
                verifyCode.setText(getResources().getString(R.string.please_input_verify));
                inputTel.setText(getResources().getString(R.string.please_input_tel));
                if(animator!=null){
                    animator.cancel();
                }
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
                    if (verify.length() < 6) {
                        try {
                            verify.append(((TextView) v).getText());
                            verifyCode.setText(verify.toString());
                        } catch (Exception e) {
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
                    if (tel.length() >= 1) {
                        try {
                            tel.deleteCharAt(tel.length() - 1);
                            if (tel.length() >= 1) {

                                inputTel.setText(tel.toString());
                            } else {
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
                            if (verify.length() >= 1) {

                                verifyCode.setText(verify.toString());
                            } else {
                                verifyCode.setText(getResources().getString(R.string.please_input_verify));
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                break;
            case R.id.confirm_bind:
                setBindNext();
                if (isSendVerify) {
                    String code = verifyCode.getText().toString();
                    if (TextUtils.isEmpty(code)) {
                        ToastMaster.shortToast(getResources().getString(R.string.verify_input));
                        return;
                    }
                    ApiFactory.binduser(tel_num, code).subscribe(new BaseProgressSubscriber<ApiResponse<EdituserRequest>>(this) {
                        @Override
                        public void onNext(ApiResponse<EdituserRequest> apiResponse) {
                            edituserRequest = apiResponse.getData();
                            setBindNext();
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
            case R.id.input_tel:
                isTel = true;
                break;
            case R.id.verify_code:
                isTel = false;
                break;
            case R.id.bind_face:
                if(TextUtils.isEmpty(Constants.baiduKey)){
                    return;
                }
                bindType = 2;
                if(edituserRequest!=null){
                    setBindNext();
                }
                registerIntroduceThree.setText("2."+getResources().getString(R.string.bind_face));
                bindFace.setBackgroundResource(R.drawable.border_red_half_right);
                bindFace.setTextColor(getResources().getColor(R.color.white));
                bindVeune.setBackground(null);
                bindVeune.setTextColor(getResources().getColor(R.color.red));

                break;
            case R.id.bind_veune:
                bindType = 1;
                registerIntroduceThree.setText("2."+getResources().getString(R.string.bind_veune));
                if(edituserRequest!=null){
                    setBindNext();
                }
                bindVeune.setBackgroundResource(R.drawable.border_red_half_left);
                bindVeune.setTextColor(getResources().getColor(R.color.white));
                bindFace.setBackground(null);
                bindFace.setTextColor(getResources().getColor(R.color.red));

                break;
            case R.id.bind_face_intro_below:
                    ExtByteArrayOutputStream ops = new ExtByteArrayOutputStream();
                    YuvImage yuv = new YuvImage(clone, ImageFormat.NV21, 640, 480, null);
                    yuv.compressToJpeg(new Rect(0, 0, 640, 480), 85, ops);
                    final Bitmap bitmap = BitmapFactory.decodeByteArray(ops.getByteArray(), 0, ops.getByteArray().length);
                    try {
                        File file= new File(Environment.getExternalStorageDirectory()+"/register.jpg");
                        if(file.exists()){
                            file.delete();
                        }
                        FileOutputStream fileOutputStream=new FileOutputStream(file.getAbsolutePath());
                        bitmap.compress(Bitmap.CompressFormat.JPEG,85,fileOutputStream);
                        register(file.getAbsolutePath());
                        // saveData(bitmap);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                        Toast.makeText(RegisterActivity.this,e.getMessage(),Toast.LENGTH_SHORT);
                    }
                break;

        }
    }
    private byte[] clone;
    private void  setBindNext(){
        bindMiddleOne.setVisibility(View.INVISIBLE);
        bindMiddleTwo.setVisibility(View.VISIBLE);
        registerIntroduceThree.setTextColor(getResources().getColor(R.color.red));
        registerIntroduceTwo.setTextColor(getResources().getColor(R.color.text_register));
        Log.e(TAG, "setBindNext: "+bindType);
        if (bindType == 1) {
            bindVeuneContainer.setVisibility(View.VISIBLE);
            bindFaceContainer.setVisibility(View.INVISIBLE);
            simulateProgress();
            bindWay.setText("2."+getResources().getString(R.string.bind_veune));
        } else {
            bindVeuneContainer.setVisibility(View.INVISIBLE);
            bindFaceContainer.setVisibility(View.VISIBLE);
            bindWay.setText("2."+getResources().getString(R.string.bind_face));
            simulateProgressFace();
        }

    }
    @Override
    protected void onDestroy() {
        handler.removeMessages(5);
        if(mCamera!=null){
            mCamera.release();
        }
        super.onDestroy();
    }
private void setCameraView(){
    int mCameraRotate = 0;
    boolean mCameraMirror = false;
    mWidth = 640;
    mHeight = 480;
    mFormat = ImageFormat.NV21;
    svCameraSurfaceview .setOnTouchListener(this);
    surfaceView.setOnCameraListener(this);
    surfaceView.setupGLSurafceView(svCameraSurfaceview, true, mCameraMirror, mCameraRotate);
    surfaceView.debug_print_fps(false, false);
}
    @Override
    protected void onResume() {
        super.onResume();
        isSendVerify = false;
    }


    @Override
    public void modelMsg(int state, String msg,Bitmap bitmap) {
        Log.e("modelMsg: ", state + ">>>>>>>>>");
        TTSUtils.getInstance().speak(msg);
        if (state == 3) {
            if(bitmap!=null){
                venueImage.setImageBitmap(bitmap);
            }
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
                    if (userType == 1) {
                        cardType.setText(getString(R.string.member_card));
                    } else if (userType == 2) {
                        cardType.setText(getString(R.string.coach_card));
                    } else if (userType == 3) {
                        cardType.setText(getString(R.string.worker_card));
                    }
                    handler.sendEmptyMessageDelayed(5, 3000);
                }
            });

        }
        if (state == 2) {
            if(bitmap!=null){
                venueImage.setImageBitmap(bitmap);
            }
            bindVenueIntro.setText(getResources().getString(R.string.same_finger));
        }
        if (state == 1) {
            if(bitmap!=null){
                venueImage.setImageBitmap(bitmap);
            }
            bindVenueIntro.setText(getResources().getString(R.string.again_finger));
        }
    }

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            isSendVerify =false;
            edituserRequest=null;
            bindMiddleTwo.setVisibility(View.INVISIBLE);
            bindMiddleThree.setVisibility(View.INVISIBLE);
            bindMiddleOne.setVisibility(View.VISIBLE);
            verify.delete(0, verify.length());
            tel.delete(0,tel.length());
            verifyCode.setText(getResources().getString(R.string.please_input_verify));
            inputTel.setText(getResources().getString(R.string.please_input_tel));
            animator.cancel();
        }
    };
}
