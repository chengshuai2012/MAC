package com.link.cloud.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.link.cloud.R;
import com.link.cloud.base.AppBarActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by OFX002 on 2018/9/28.
 */

public class SettingActivity extends AppBarActivity {
    @BindView(R.id.member)
    TextView member;
    @BindView(R.id.manager)
    TextView manager;
    @BindView(R.id.open)
    TextView open;
    @BindView(R.id.close)
    TextView close;
    @BindView(R.id.open_or_close)
    LinearLayout openOrClose;
    @BindView(R.id.back_system_setting)
    TextView backSystemSetting;
    @BindView(R.id.back_app)
    TextView backApp;
    @BindView(R.id.back_system_main)
    TextView backSystemMain;

    @Override
    protected void initViews() {
        hideToolbar();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_setting;
    }

  @OnClick({R.id.back_app,R.id.member})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.back_app:
            case R.id.member:
                finish();
                break;
        }
  }
}
