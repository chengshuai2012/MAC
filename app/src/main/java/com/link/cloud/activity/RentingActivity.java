package com.link.cloud.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.link.cloud.Constants;
import com.link.cloud.R;
import com.link.cloud.api.bean.RentTimeBean;
import com.link.cloud.base.BaseActivity;
import com.link.cloud.fragment.RentBuyFragment;
import com.link.cloud.fragment.RentCabinetFragment;
import com.link.cloud.fragment.RentInfoFragment;
import com.link.cloud.widget.PublicTitleView;

/**
 * @author qianlu
 * @date 2018/9/27.
 * GitHub：qiandailu
 * email：zar.l@qq.com
 * description：
 */
@SuppressLint("Registered")
public class RentingActivity extends BaseActivity {
    private PublicTitleView publicTitle;


    @Override
    protected void initViews() {
        setTitle(R.drawable.handy_logo);
        showFragment(RentCabinetFragment.class);
        publicTitle = (PublicTitleView) findViewById(R.id.publicTitle);
        publicTitle.setItemClickListener(new PublicTitleView.onItemClickListener() {
            @Override
            public void itemClickListener() {
                finish();
            }
        });
        publicTitle.setTags(getResources().getString(R.string.one_select_time), getResources().getString(R.string.input_user_info), getResources().getString(R.string.sure_pay), getResources().getString(R.string.pay_success));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_renting;
    }



    public void showDate(RentTimeBean rentTimeBean) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constants.FragmentExtra.BEAN, rentTimeBean);
        showFragment(RentInfoFragment.class, bundle);
        publicTitle.nextPosition();
    }

    public void showPosition() {
        publicTitle.lastPosition();
    }

    public void showBuyDate() {
        showFragment(RentBuyFragment.class);
        publicTitle.nextPosition();
    }


}
