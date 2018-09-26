package com.link.cloud.activity;

import android.widget.LinearLayout;

import com.link.cloud.R;
import com.link.cloud.base.AppBarActivity;
import com.link.cloud.widget.PublicTitleView;

/**
 * Created by OFX002 on 2018/9/25.
 */

public class GroupInActivity extends AppBarActivity {
    private LinearLayout rootView;
    private PublicTitleView publicTitle;
    @Override
    protected void initViews() {
        setTitle(R.mipmap.ic_no_data);
        initialize();
    }
    private void initialize() {
        rootView = (LinearLayout) findViewById(R.id.rootView);
        publicTitle= (PublicTitleView) findViewById(R.id.publicTitle);
        publicTitle.setTags("haha","asdjfhasd","asdfads");
        publicTitle.nextPosition();

    }
    @Override
    protected int getLayoutId() {
        return R.layout.activity_group_in;
    }

}
