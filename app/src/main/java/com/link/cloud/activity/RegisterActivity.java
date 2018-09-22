package com.link.cloud.activity;

import android.content.Context;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.SpannedString;
import android.text.style.AbsoluteSizeSpan;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.link.cloud.R;
import com.link.cloud.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by OFX002 on 2018/9/21.
 */

public class RegisterActivity extends BaseActivity {
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

    protected void initViews() {
        SetEditTextHintFontSize(this,inputTel,36,R.string.please_input_tel);
        SetEditTextHintFontSize(this,verifyCode,30,R.string.please_input_tel);
    }

//设置EditText的hint字体大小

    public static void SetEditTextHintFontSize(Context context, EditText editText, int size, int contentID) {

        String content = context.getString(contentID);

        // 新建一个可以添加属性的文本对象

        SpannableString ss = new SpannableString(content);

        // 新建一个属性对象,设置文字的大小

        AbsoluteSizeSpan ass = new AbsoluteSizeSpan(size, false);

        // 附加属性到文本

        ss.setSpan(ass, 0, ss.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        // 设置hint

        editText.setHint(new SpannedString(ss)); // 一定要进行转换,否则属性会消失

    }
public class RegisterActivity extends BaseActivity {
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

    @Override
    protected void initViews() {
        SetEditTextHintFontSize(this,inputTel,36,R.string.please_input_tel);
        SetEditTextHintFontSize(this,verifyCode,30,R.string.please_input_tel);
    }

//设置EditText的hint字体大小

    public static void SetEditTextHintFontSize(Context context, EditText editText, int size, int contentID) {

        String content = context.getString(contentID);

        // 新建一个可以添加属性的文本对象

        SpannableString ss = new SpannableString(content);

        // 新建一个属性对象,设置文字的大小

        AbsoluteSizeSpan ass = new AbsoluteSizeSpan(size, false);

        // 附加属性到文本

        ss.setSpan(ass, 0, ss.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        // 设置hint

        editText.setHint(new SpannedString(ss)); // 一定要进行转换,否则属性会消失

    }

    @Override
    protected int setLayoutID() {
        return R.layout.activity_register;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }
}
