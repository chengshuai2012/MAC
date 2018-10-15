package com.link.cloud.activity;

import android.animation.ValueAnimator;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dinuscxj.progressbar.CircleProgressBar;
import com.link.cloud.MacApplication;
import com.link.cloud.R;
import com.link.cloud.base.AppBarActivity;

import javax.crypto.Mac;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

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

    protected void initViews() {
        customProgress.setProgressFormatter(null);
        customProgress.setMax(100);
        registerIntroduceTwo.setTextColor(getResources().getColor(R.color.red));
        hideToolbar();

    }

    private void simulateProgress() {
        ValueAnimator animator = ValueAnimator.ofInt(0, 100);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int progress = (int) animation.getAnimatedValue();
                if(customProgress!=null){
                    customProgress.setProgress(progress);
                    int state = MacApplication.getVenueUtils().getState();
                    if(state==3){
                        MacApplication.getVenueUtils().workModel();
                    }
                    if(progress==99){
                        finish();
                    }
                }
            }
        });
        animator.setDuration(40000);
        animator.start();
    }
    StringBuilder builder = new StringBuilder();
    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @OnClick({R.id.bind_keypad_0,R.id.bind_keypad_1,R.id.bind_keypad_2,R.id.bind_keypad_3,R.id.bind_keypad_4,R.id.bind_keypad_5,R.id.bind_keypad_6,R.id.bind_keypad_7,R.id.bind_keypad_8,
            R.id.bind_keypad_9,R.id.bind_keypad_ok,R.id.bind_keypad_delect,R.id.confirm_bind,R.id.bind_venue_intro,R.id.back})
    public void OnClick(View v){
        switch (v.getId()){
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
                builder.append(((TextView)v).getText());
                inputTel.setText(builder.toString());
            break;
            case R.id.bind_keypad_ok:
                builder.delete(0,builder.length());
                inputTel.setText(builder.toString());

                break;
            case R.id.bind_keypad_delect:
                if(builder.length()>=1){
                    builder.deleteCharAt(builder.length() - 1);
                    inputTel.setText(builder.toString());
                }
                    break;
            case R.id.confirm_bind:
                bindMiddleOne.setVisibility(View.INVISIBLE);
                bindMiddleTwo.setVisibility(View.VISIBLE);
                String tel = inputTel.getText().toString();
                registerIntroduceThree.setTextColor(getResources().getColor(R.color.red));
                registerIntroduceTwo.setTextColor(getResources().getColor(R.color.text_register));
                bindWay.setText(getResources().getString(R.string.bind_veune));
                simulateProgress();
                break;
            case R.id.bind_venue_intro:
                bindMiddleTwo.setVisibility(View.INVISIBLE);
                bindMiddleThree.setVisibility(View.VISIBLE);
                registerIntroduceFive.setTextColor(getResources().getColor(R.color.red));
                registerIntroduceThree.setTextColor(getResources().getColor(R.color.text_register));
                bindWay.setText(getResources().getString(R.string.bind_finish));
                break;


        }
    }
}
