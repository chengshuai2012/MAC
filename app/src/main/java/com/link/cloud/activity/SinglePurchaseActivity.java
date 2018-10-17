package com.link.cloud.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.link.cloud.R;
import com.link.cloud.base.AppBarActivity;
import com.link.cloud.fragment.OpenMembershipFragment;
import com.link.cloud.fragment.RechargeFragment;
import com.link.cloud.fragment.SingleBuyFragment;
import com.link.cloud.widget.PublicTitleView;

/**
 * @author qianlu
 * @date 2018/9/27.
 * GitHub：qiandailu
 * email：zar.l@qq.com
 * description：
 */
@SuppressLint("Registered")
public class SinglePurchaseActivity extends AppBarActivity {

    private RadioButton singBuy;
    private RadioButton activiteCard;
    private RadioButton rechargeCard;
    private RadioGroup radioGroup;
    private FrameLayout contentframe;
    private PublicTitleView publicTitleView;

    @Override
    protected void initViews() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_singlepurchase;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.drawable.handy_logo);
        initialize();
    }

    private void initialize() {
        publicTitleView = (PublicTitleView) findViewById(R.id.publicTitle);
        singBuy = (RadioButton) findViewById(R.id.singBuy);
        activiteCard = (RadioButton) findViewById(R.id.activiteCard);
        rechargeCard = (RadioButton) findViewById(R.id.rechargeCard);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        contentframe = (FrameLayout) findViewById(R.id.content_frame);
        showFragment(SingleBuyFragment.class);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                intFragment(checkedId);
            }
        });
        publicTitleView.setItemClickListener(new PublicTitleView.onItemClickListener() {
            @Override
            public void itemClickListener() {
                finish();
            }
        });


    }

    private void intFragment(int checkedId) {
        switch (checkedId) {
            case R.id.singBuy:
                showFragment(SingleBuyFragment.class);
                break;
            case R.id.activiteCard:
                showFragment(OpenMembershipFragment.class);
                break;
            case R.id.rechargeCard:
                showFragment(RechargeFragment.class);
                break;
        }

    }
}
