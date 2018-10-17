package com.link.cloud.activity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;

import com.link.cloud.Constants;
import com.link.cloud.R;
import com.link.cloud.api.bean.PrivateEduBean;
import com.link.cloud.api.bean.RentTimeBean;
import com.link.cloud.base.AppBarActivity;
import com.link.cloud.fragment.BuyPrivateEduFragment;
import com.link.cloud.fragment.PrivateEduListFragment;
import com.link.cloud.fragment.RentInfoFragment;
import com.link.cloud.widget.BottomBuyDialog;
import com.link.cloud.widget.PublicTitleView;

/**
 * @author qianlu
 * @date 2018/9/26.
 * GitHub：qiandailu
 * email：zar.l@qq.com
 * description：
 */
@SuppressLint("Registered")
public class PrivateEducationActivity extends AppBarActivity {

    private PublicTitleView publicTitle;


    @Override
    protected void initViews() {

        setTitle(R.drawable.handy_logo);
        showFragment(PrivateEduListFragment.class);
        publicTitle = (PublicTitleView) findViewById(R.id.publicTitle);
        publicTitle.setItemClickListener(new PublicTitleView.onItemClickListener() {
            @Override
            public void itemClickListener() {
                finish();
            }
        });

        publicTitle.setTags("1.选择课程","2.选择时间","3.确认支付","4.支付成功");
//        showActivity(RentingActivity.class);
    }


    public void showDate(PrivateEduBean rentTimeBean) {
        final Bundle bundle = new Bundle();
        bundle.putSerializable(Constants.FragmentExtra.BEAN, rentTimeBean);
        publicTitle.nextPosition();

        BottomBuyDialog dialog = new BottomBuyDialog(this);
        dialog.setOnCancelButtonClickListener(new BottomBuyDialog.OnCancelButtonClickListener() {
            @Override
            public void onClick(Dialog dialog) {
                dialog.dismiss();
            }
        });
        dialog.setOnPositiveButtonClickListener(new BottomBuyDialog.OnPositiveButtonClickListener() {
            @Override
            public void onClick(Dialog dialog) {
                showFragment(BuyPrivateEduFragment.class, bundle);
                dialog.dismiss();
            }
        });
        dialog.show();
        publicTitle.nextPosition();
    }


    public void showPosition() {
        publicTitle.lastPosition();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_privateeducation;
    }


}
