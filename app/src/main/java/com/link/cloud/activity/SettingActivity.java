package com.link.cloud.activity;

import com.link.cloud.R;
import com.link.cloud.base.AppBarActivity;

/**
 * Created by OFX002 on 2018/9/28.
 */

public class SettingActivity extends AppBarActivity{
    @Override
    protected void initViews() {
        hideToolbar();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_setting;
    }
}
