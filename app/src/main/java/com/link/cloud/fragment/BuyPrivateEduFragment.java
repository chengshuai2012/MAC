package com.link.cloud.fragment;

import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.link.cloud.Constants;
import com.link.cloud.R;
import com.link.cloud.activity.PrivateEducationActivity;
import com.link.cloud.activity.RentingActivity;
import com.link.cloud.api.bean.PriceLevelBean;
import com.link.cloud.api.bean.PrivateEduBean;
import com.link.cloud.base.BaseFragment;
import com.zitech.framework.utils.ToastMaster;
import com.zitech.framework.utils.ViewUtils;

import cn.iwgang.countdownview.CountdownView;

/**
 * @author qianlu
 * @date 2018/9/27.
 * GitHub：qiandailu
 * email：zar.l@qq.com
 * description：购买私教课
 */
public class BuyPrivateEduFragment extends BaseFragment {


    private android.widget.TextView className;
    private android.widget.TextView costText;
    private android.widget.TextView unitpriceText;
    private android.widget.TextView coachText;
    private android.widget.TextView surePayWechatButton;
    private android.widget.TextView surePayalipayButton;
    private android.widget.TextView placeAddressText;
    private android.widget.TextView amountText;
    private cn.iwgang.countdownview.CountdownView countDownView;
    private android.widget.Button lastButton;

    private PriceLevelBean priceLevelBean;
    private PrivateEduBean rentTimeBean;
    private android.support.v7.widget.CardView handyPayLayout;
    private TextView handyPayButton;
    private android.widget.LinearLayout wechatPayButton;
    private android.widget.LinearLayout aliPayButton;

    @Override
    public void onInflateView(View contentView) {
        super.onInflateView(contentView);
        rentTimeBean = (PrivateEduBean) getArguments().getSerializable(Constants.FragmentExtra.BEAN);
        priceLevelBean = (PriceLevelBean) getArguments().getSerializable(Constants.FragmentExtra.PRICELEVELBEAN);
        initView(contentView);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_buyprivateedu;
    }

    private void initView(View contentView) {
        ToastMaster.shortToast(rentTimeBean.getAddress());
        className = (TextView) contentView.findViewById(R.id.className);
        costText = (TextView) contentView.findViewById(R.id.costText);
        unitpriceText = (TextView) contentView.findViewById(R.id.unitpriceText);
        coachText = (TextView) contentView.findViewById(R.id.coachText);
        placeAddressText = (TextView) contentView.findViewById(R.id.placeAddressText);
        amountText = (TextView) contentView.findViewById(R.id.amountText);
        countDownView = (CountdownView) contentView.findViewById(R.id.countDownView);
        lastButton = (Button) contentView.findViewById(R.id.lastButton);
        surePayWechatButton = (TextView) contentView.findViewById(R.id.surePayWechatButton);
        surePayalipayButton = (TextView) contentView.findViewById(R.id.surePayalipayButton);
        handyPayLayout = (CardView) contentView.findViewById(R.id.handyPayLayout);
        handyPayButton = (TextView) contentView.findViewById(R.id.handyPayButton);
        wechatPayButton = (LinearLayout) contentView.findViewById(R.id.wechatPayButton);
        aliPayButton = (LinearLayout) contentView.findViewById(R.id.aliPayButton);

        coachText.setText(rentTimeBean.getCoachName());
        className.setText(rentTimeBean.getFitnessCourseName());
        costText.setText(getString(R.string.sure_pay_money) + priceLevelBean.getCourseUnit());
        unitpriceText.setText("x" + priceLevelBean.getCourseTotal() + getString(R.string.jie_string));
        placeAddressText.setText(rentTimeBean.getAddress());
        amountText.setText(getString(R.string.yuan) + priceLevelBean.getTotalPrice());
        surePayWechatButton.setText(getString(R.string.sure_other_pay) + getString(R.string.yuan) + priceLevelBean.getTotalPrice());
        handyPayButton.setText(getString(R.string.sure_other_pay) + getString(R.string.yuan) + priceLevelBean.getTotalPrice());
        surePayalipayButton.setText(getString(R.string.sure_other_pay) + getString(R.string.yuan) + priceLevelBean.getTotalPrice());
        countDownView.setOnCountdownEndListener(new CountdownView.OnCountdownEndListener() {
            @Override
            public void onEnd(CountdownView cv) {
                ToastMaster.shortToast("倒计时完了");
            }
        });
        countDownView.setTag("test1");
        long time1 = (long) 10 * 60 * 1000;
        countDownView.start(time1);


        ViewUtils.setOnClickListener(lastButton, this);
        ViewUtils.setOnClickListener(wechatPayButton, this);
        ViewUtils.setOnClickListener(aliPayButton, this);
        ViewUtils.setOnClickListener(handyPayLayout, this);

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.lastButton:
                ((PrivateEducationActivity) getActivity()).showPosition();
                getActivity().onBackPressed();
                break;
            case R.id.wechatPayButton:
                break;
            case R.id.aliPayButton:
                break;
            case R.id.handyPayLayout:
                break;
        }
    }
}
