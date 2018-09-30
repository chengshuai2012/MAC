package com.link.cloud.activity;

import android.annotation.SuppressLint;

import com.link.cloud.R;
import com.link.cloud.base.AppBarActivity;
import com.link.cloud.widget.PublicTitleView;

/**
 * @author qianlu
 * @date 2018/9/26.
 * GitHub：qiandailu
 * email：zar.l@qq.com
 * description：
 */
@SuppressLint("Registered")
public class PrivateEducationConsumActivity extends AppBarActivity implements PublicTitleView.onItemClickListener {
    private PublicTitleView publicTitle;
    @Override
    protected void initViews() {
        setTitle(R.drawable.handy_logo);
        publicTitle= (PublicTitleView) findViewById(R.id.publicTitle);
        publicTitle.setTags("1.教练确认","2.学员确认","3.选择课程","4.消课成功");
        publicTitle.setTitleText(getString(R.string.lesson_consum));
        publicTitle.setFinshText(getResources().getString(R.string.back_home));
        publicTitle.setItemClickListener(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_lesson_consum;
    }

    @Override
    public void itemClickListener() {
        finish();
    }
}
