package com.link.cloud.activity;

import android.os.Bundle;

import com.link.cloud.R;
import com.link.cloud.base.BaseActivity;
import com.link.cloud.fragment.MemberCardFragment;

/**
 * 作者：qianlu on 2018/9/30 15:31
 * 邮箱：zar.l@qq.com
 * 会员开卡
 */
public class MemberCardActivity extends BaseActivity {
    @Override
    protected void initViews() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.drawable.handy_logo);
        showFragment(MemberCardFragment.class);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_membercard;
    }




}
