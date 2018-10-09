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
    private RentTimeBean rentTimeBean;

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_rentinfo;
    }

    @Override
    public void onInflateView(View contentView) {
        super.onInflateView(contentView);
        initialize(contentView);
        rentTimeBean = (RentTimeBean) getArguments().getSerializable(Constants.FragmentExtra.BEAN);

    }

    private void initialize(View contentView) {

        rentStore = (TextView) contentView.findViewById(R.id.rentStore);
        rentTime = (TextView) contentView.findViewById(R.id.rentTime);
        returnTimeText = (TextView) contentView.findViewById(R.id.returnTimeText);
        cabinetPositionText = (TextView) contentView.findViewById(R.id.cabinetPositionText);
        lastButton = (Button) contentView.findViewById(R.id.lastButton);
        nextButton = (Button) contentView.findViewById(R.id.nextButton);


        ViewUtils.setOnClickListener(lastButton, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((RentingActivity) getActivity()).showPosition();
                getActivity().onBackPressed();
            }
        });
        ViewUtils.setOnClickListener(nextButton, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((RentingActivity) getActivity()).showBuyDate();
            }
        });
    }

}
