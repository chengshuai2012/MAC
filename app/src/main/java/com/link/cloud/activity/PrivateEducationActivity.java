package com.link.cloud.activity;

import android.annotation.SuppressLint;
import android.support.v4.app.Fragment;

import com.link.cloud.R;
import com.link.cloud.base.AppBarActivity;
import com.link.cloud.fragment.PrivateEduListFragment;
import com.link.cloud.utils.Utils;

/**
 * @author qianlu
 * @date 2018/9/26.
 * GitHub：qiandailu
 * email：zar.l@qq.com
 * description：
 */
@SuppressLint("Registered")
public class PrivateEducationActivity extends AppBarActivity {
    @Override
    protected void initViews() {
        showFragment(PrivateEduListFragment.class);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_privateeducation;
    }

    public Fragment showFragment(Class<? extends Fragment> fragmentClass) {
        return Utils.replace(getSupportFragmentManager(), R.id.content_frame, fragmentClass);
    }



}
