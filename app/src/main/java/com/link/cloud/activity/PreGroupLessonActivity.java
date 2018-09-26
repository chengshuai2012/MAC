package com.link.cloud.activity;

import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.link.cloud.R;
import com.link.cloud.base.AppBarActivity;
import com.link.cloud.listener.DialogCancelListener;
import com.link.cloud.utils.DialogUtils;
import com.link.cloud.widget.PublicTitleView;

import butterknife.BindView;
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
    private PublicTitleView publicTitle;
    private DialogUtils dialogUtils;

    @Override
    protected void initViews() {
        setTitle(R.drawable.handy_logo);
        registerIntroduceTwo.setTextColor(getResources().getColor(R.color.red));
        registerIntroduceTwo.setText(getResources().getString(R.string.choose_login_1));
        registerIntroduceThree.setVisibility(View.GONE);
        registerIntroduceFive.setText(getResources().getString(R.string.choose_pay_2));
        binding.setText(getResources().getString(R.string.pay_ticket));
        dialogUtils = new DialogUtils(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_pre_lesson;
    }
    StringBuilder builder = new StringBuilder();
    @OnClick ({R.id.bind_keypad_0,R.id.bind_keypad_1,R.id.bind_keypad_2,R.id.bind_keypad_3,R.id.bind_keypad_4,R.id.bind_keypad_5,R.id.bind_keypad_6,R.id.bind_keypad_7,R.id.bind_keypad_8,
            R.id.bind_keypad_9,R.id.bind_keypad_ok,R.id.bind_keypad_delect,R.id.confirm_bind,R.id.back,R.id.pre,R.id.pay_zhifubao,R.id.pay_wechat})
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
                loginMiddleOne.setVisibility(View.INVISIBLE);
                loginMiddleTwo.setVisibility(View.VISIBLE);
                String tel = inputTel.getText().toString();
                registerIntroduceFive.setTextColor(getResources().getColor(R.color.red));
                registerIntroduceTwo.setTextColor(getResources().getColor(R.color.text_register));
                loginWay.setText(getResources().getString(R.string.choose_pay));
                break;
            case R.id.pre:
                View pre = View.inflate(this,R.layout.pre_dialog,null);
                dialogUtils.showPreDialog(pre,this);
                break;
                case R.id.pay_wechat:
                View pay_wechat = View.inflate(this,R.layout.pay_dialog,null);
                dialogUtils.showPayDialog(pay_wechat,this);
                break;
                case R.id.pay_zhifubao:
                View pay_zhifubao = View.inflate(this,R.layout.pay_dialog,null);
                dialogUtils.showPayDialog(pay_zhifubao,this);
                break;
        }
    }

    @Override
    public void dialogCancel() {
        finish();
    }
}
