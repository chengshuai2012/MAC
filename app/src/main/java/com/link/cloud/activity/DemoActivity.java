package com.link.cloud.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.link.cloud.R;
import com.link.cloud.base.AppBarActivity;
import com.link.cloud.widget.BottomBuyDialog;
import com.link.cloud.widget.PublicTitleView;

@SuppressLint("Registered")
public class DemoActivity extends AppBarActivity {
    private LinearLayout rootView;
    private PublicTitleView publicTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initialize();
        setTitle(R.mipmap.ic_no_data);
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_demo;
    }

    private void initialize() {
        rootView = (LinearLayout) findViewById(R.id.rootView);
        publicTitle= (PublicTitleView) findViewById(R.id.publicTitle);
        publicTitle.setTags("haha","asdjfhasd","asdfads");
        publicTitle.nextPosition();
        BottomBuyDialog dialog=new BottomBuyDialog(this);
        dialog.show();
    }
}
