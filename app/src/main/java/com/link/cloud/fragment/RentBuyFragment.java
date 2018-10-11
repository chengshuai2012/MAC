package com.link.cloud.fragment;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.link.cloud.Constants;
import com.link.cloud.R;
import com.link.cloud.activity.RentingActivity;
import com.link.cloud.api.bean.RentTimeBean;
import com.link.cloud.base.BaseFragment;
import com.zitech.framework.utils.ViewUtils;

import cn.iwgang.countdownview.CountdownView;

/**
 * 作者：qianlu on 2018/10/9 14:32
 * 邮箱：zar.l@qq.com
 */
public class RentBuyFragment extends BaseFragment {


    private android.widget.TextView className;
    private android.widget.TextView costText;
    private android.widget.TextView coachText;
    private android.widget.TextView placeAddressText;
    private android.widget.TextView rentAddress;
    private cn.iwgang.countdownview.CountdownView countDownView;
    private android.widget.Button lastButton;

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_rentbuy;
    }

    @Override
    public void onInflateView(View contentView) {
        super.onInflateView(contentView);
        initView(contentView);

    }

    private void initView(View contentView) {
        className = (TextView) contentView.findViewById(R.id.className);
        costText = (TextView) contentView.findViewById(R.id.costText);
        coachText = (TextView) contentView.findViewById(R.id.coachText);
        placeAddressText = (TextView) contentView.findViewById(R.id.placeAddressText);
        rentAddress = (TextView) contentView.findViewById(R.id.rentAddress);
        countDownView = (CountdownView) contentView.findViewById(R.id.countDownView);
        lastButton = (Button) contentView.findViewById(R.id.lastButton);


        ViewUtils.setOnClickListener(lastButton,this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.lastButton:
                ((RentingActivity) getActivity()).showPosition();
                getActivity().onBackPressed();
                break;


        }
    }
}
