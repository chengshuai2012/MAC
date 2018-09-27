package com.link.cloud.fragment;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.link.cloud.R;
import com.link.cloud.base.BaseFragment;

/**
 * @author qianlu
 * @date 2018/9/27.
 * GitHub：qiandailu
 * email：zar.l@qq.com
 * description：
 */
public class RentInfoFragment extends BaseFragment {


    private TextView rentStore;
    private TextView rentTime;
    private TextView returnTimeText;
    private TextView cabinetPositionText;
    private Button lastButton;
    private Button nextButton;

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_rentinfo;
    }

    @Override
    public void onInflateView(View contentView) {
        super.onInflateView(contentView);
        initialize(contentView);
    }

    private void initialize(View contentView) {

        rentStore = (TextView) contentView.findViewById(R.id.rentStore);
        rentTime = (TextView) contentView.findViewById(R.id.rentTime);
        returnTimeText = (TextView) contentView.findViewById(R.id.returnTimeText);
        cabinetPositionText = (TextView) contentView.findViewById(R.id.cabinetPositionText);
        lastButton = (Button) contentView.findViewById(R.id.lastButton);
        nextButton = (Button) contentView.findViewById(R.id.nextButton);
    }

}
