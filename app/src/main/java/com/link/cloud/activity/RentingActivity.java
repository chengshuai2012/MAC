package com.link.cloud.activity;

import android.annotation.SuppressLint;

import com.link.cloud.R;
import com.link.cloud.base.AppBarActivity;
import com.link.cloud.fragment.RentCabinetFragment;

/**
 * @author qianlu
 * @date 2018/9/27.
 * GitHub：qiandailu
 * email：zar.l@qq.com
 * description：
 */
@SuppressLint("Registered")
public class RentingActivity extends AppBarActivity {
    @Override
    protected void initViews() {
        setTitle(R.drawable.handy_logo);
        showFragment(RentCabinetFragment.class);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_renting;
    }
}
