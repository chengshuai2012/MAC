package com.link.cloud.fragment;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.link.cloud.R;
import com.link.cloud.base.BaseFragment;

/**
 * @author qianlu
 * @date 2018/9/27.
 * GitHub：qiandailu
 * email：zar.l@qq.com
 * description：
 */
public class RentCabinetFragment extends BaseFragment {
    private RecyclerView recycleView;
    private Button lastButton;
    private Button nextButton;

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_rentcabinet;
    }

    @Override
    public void onInflateView(View contentView) {
        super.onInflateView(contentView);
        initialize(contentView);
    }

    private void initialize(View contentView) {
        recycleView = (RecyclerView) contentView.findViewById(R.id.recycleView);
        lastButton = (Button) contentView.findViewById(R.id.lastButton);
        nextButton = (Button) contentView.findViewById(R.id.nextButton);
    }
}
