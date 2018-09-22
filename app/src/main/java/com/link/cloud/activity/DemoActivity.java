package com.link.cloud.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.link.cloud.R;
import com.link.cloud.base.AppBarActivity;

@SuppressLint("Registered")
public class DemoActivity extends AppBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.mipmap.ic_no_data);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_demo;
    }
}
