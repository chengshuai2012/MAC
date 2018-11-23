package com.link.cloud.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.link.cloud.R;
import com.link.cloud.User;
import com.link.cloud.api.ApiFactory;
import com.link.cloud.api.BaseProgressSubscriber;
import com.link.cloud.base.AppBarActivity;
import com.link.cloud.widget.PublicTitleView;
import com.zitech.framework.data.network.response.ApiResponse;
import com.zitech.framework.utils.ViewUtils;

import pl.droidsonroids.gif.GifImageButton;
import pl.droidsonroids.gif.GifImageView;

@SuppressLint("Registered")
public class DemoActivity extends AppBarActivity {
    private LinearLayout rootView;
    private PublicTitleView publicTitle;
    private GifImageView fingerImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        GifImageButton gib = new GifImageButton(this);

        super.onCreate(savedInstanceState);
        initialize();
        setToolBarHelperHeight(ViewUtils.getDimenPx(R.dimen.h300));
        setTitle(R.drawable.handy_logo);
    }

    @Override
    protected void initViews() {
    }

    private void getCode() {

        ApiFactory.sendVCode("13631629110").subscribe(new BaseProgressSubscriber<ApiResponse>(this) {
            @Override
            public void onNext(ApiResponse response) {

            }
        });
    }




    @Override
    protected int getLayoutId() {
        return R.layout.activity_demo;
    }

    private void initialize() {
//        rootView = (LinearLayout) findViewById(R.id.rootView);
        publicTitle= (PublicTitleView) findViewById(R.id.publicTitle);
        fingerImage= (GifImageView) findViewById(R.id.fingerImage);
        //fingerImage.setImageResource(R.drawable.finger);
//        Glide.w ith(this).load(R.drawable.finger).asGif().into(fingerImage);
        publicTitle.setTags("haha","asdjfhasd","asdfads");

        publicTitle.setItemClickListener(new PublicTitleView.onItemClickListener() {
            @Override
            public void itemClickListener() {
                finish();
            }
        });

    }
}
