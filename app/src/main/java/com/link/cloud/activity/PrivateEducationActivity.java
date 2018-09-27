package com.link.cloud.activity;

import android.annotation.SuppressLint;
import android.app.Dialog;

import com.link.cloud.R;
import com.link.cloud.base.AppBarActivity;
import com.link.cloud.fragment.BuyPrivateEduFragment;
import com.link.cloud.fragment.PrivateEduListFragment;
import com.link.cloud.widget.BottomBuyDialog;

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

        BottomBuyDialog dialog = new BottomBuyDialog(this);
        dialog.setOnCancelButtonClickListener(new BottomBuyDialog.OnCancelButtonClickListener() {
            @Override
            public void onClick(Dialog dialog) {

            }
        });
        dialog.setOnPositiveButtonClickListener(new BottomBuyDialog.OnPositiveButtonClickListener() {
            @Override
            public void onClick(Dialog dialog) {
                showFragment(BuyPrivateEduFragment.class);
            }
        });
        dialog.show();


        showActivity(RentingActivity.class);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_privateeducation;
    }




}
