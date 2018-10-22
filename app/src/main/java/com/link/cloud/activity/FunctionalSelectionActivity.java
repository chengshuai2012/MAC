package com.link.cloud.activity;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.link.cloud.R;
import com.link.cloud.base.BaseActivity;
import com.zitech.framework.utils.ViewUtils;

/**
 * 作者：qianlu on 2018/10/22 13:59
 * 邮箱：zar.l@qq.com
 */
@SuppressLint("Registered")
public class FunctionalSelectionActivity extends BaseActivity {


    private android.widget.TextView privateEduLayout;
    private android.widget.TextView openLayout;
    private android.widget.TextView rentingCabinetLayout;
    private android.widget.Button backLayout;


    @Override
    protected void initViews() {
        initView();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_functionalselection;
    }

    private void initView() {
        privateEduLayout = (TextView) findViewById(R.id.privateEduLayout);
        openLayout = (TextView) findViewById(R.id.openLayout);
        rentingCabinetLayout = (TextView) findViewById(R.id.rentingCabinetLayout);
        backLayout = (Button) findViewById(R.id.backLayout);


        ViewUtils.setOnClickListener(privateEduLayout, this);
        ViewUtils.setOnClickListener(openLayout, this);
        ViewUtils.setOnClickListener(rentingCabinetLayout, this);
        ViewUtils.setOnClickListener(backLayout, this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);

        switch (v.getId()) {
            case R.id.privateEduLayout:
                showActivity(PrivateEducationActivity.class);
                break;
            case R.id.openLayout:
                showActivity(SinglePurchaseActivity.class);

                break;
            case R.id.rentingCabinetLayout:
                showActivity(RentingActivity.class);
                break;

            case R.id.backLayout:
                finish();
                break;


        }
    }


}
