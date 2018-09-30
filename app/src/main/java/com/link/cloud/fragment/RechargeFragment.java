package com.link.cloud.fragment;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.link.cloud.R;
import com.link.cloud.base.BaseFragment;

public class RechargeFragment extends BaseFragment {
    private android.support.v7.widget.RecyclerView recycleView;
    private android.widget.Button payButton;
    private android.widget.TextView goLoginText;

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_recharge;
    }

    @Override
    public void onInflateView(View contentView) {
        super.onInflateView(contentView);
        initView(contentView);

    }

    private void initView(View contentView) {
        recycleView = (RecyclerView) contentView.findViewById(R.id.recycleView);
        payButton = (Button) contentView.findViewById(R.id.payButton);
        goLoginText = (TextView) contentView.findViewById(R.id.goLoginText);
    }
}
