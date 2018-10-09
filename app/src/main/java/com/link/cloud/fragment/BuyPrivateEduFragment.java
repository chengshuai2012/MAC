package com.link.cloud.fragment;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.link.cloud.R;
import com.link.cloud.activity.PrivateEducationActivity;
import com.link.cloud.activity.RentingActivity;
import com.link.cloud.base.BaseFragment;
import com.zitech.framework.utils.ViewUtils;

import cn.iwgang.countdownview.CountdownView;

/**
 * @author qianlu
 * @date 2018/9/27.
 * GitHub：qiandailu
 * email：zar.l@qq.com
 * description：购买私教课
 */
public class BuyPrivateEduFragment  extends BaseFragment{


    private android.widget.TextView className;
    private android.widget.TextView costText;
    private android.widget.TextView unitpriceText;
    private android.widget.TextView coachText;
    private android.widget.TextView placeAddressText;
    private android.widget.TextView amountText;
    private cn.iwgang.countdownview.CountdownView countDownView;
    private android.widget.Button lastButton;

    @Override
    public void onInflateView(View contentView) {
        super.onInflateView(contentView);

    }

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_buyprivateedu;
    }

    private void initView(View contentView) {
        className = (TextView) contentView.findViewById(R.id.className);
        costText = (TextView) contentView.findViewById(R.id.costText);
        unitpriceText = (TextView) contentView.findViewById(R.id.unitpriceText);
        coachText = (TextView) contentView.findViewById(R.id.coachText);
        placeAddressText = (TextView) contentView.findViewById(R.id.placeAddressText);
        amountText = (TextView) contentView.findViewById(R.id.amountText);
        countDownView = (CountdownView) contentView.findViewById(R.id.countDownView);
        lastButton = (Button) contentView.findViewById(R.id.lastButton);

        ViewUtils.setOnClickListener(lastButton,this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.lastButton:
                ((PrivateEducationActivity) getActivity()).showPosition();
                getActivity().onBackPressed();
                break;
        }
    }
}
