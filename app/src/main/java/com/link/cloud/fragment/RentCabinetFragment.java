package com.link.cloud.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.link.cloud.R;
import com.link.cloud.activity.RentingActivity;
import com.link.cloud.adapter.RentTimeAdapter;
import com.link.cloud.api.bean.RentTimeBean;
import com.link.cloud.base.BaseFragment;
import com.link.cloud.base.BaseViewHolder;
import com.zitech.framework.utils.ViewUtils;

import java.util.ArrayList;
import java.util.List;

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
    private RentTimeAdapter rentTimeAdapter;
    private RentTimeBean selecterRentTimeBean;

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
        recycleView = contentView.findViewById(R.id.recycleView);
        lastButton = contentView.findViewById(R.id.lastButton);
        nextButton = contentView.findViewById(R.id.nextButton);
        recycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        final List<RentTimeBean> data = new ArrayList<>();
        data.add(new RentTimeBean());
        data.add(new RentTimeBean());
        data.add(new RentTimeBean());
        data.add(new RentTimeBean());
        rentTimeAdapter = new RentTimeAdapter(getActivity());
        rentTimeAdapter.setOnItemClickListener(new BaseViewHolder.OnItemClickListener() {
            @Override
            public void onItemClick(Object data, int position) {
            }

            @Override
            public void onCheackClick(int position) {
                rentTimeAdapter.init(false, position);
                rentTimeAdapter.notifyDataSetChanged();
                selecterRentTimeBean = data.get(position);
            }
        });

        recycleView.setAdapter(rentTimeAdapter);
        rentTimeAdapter.setDate(data);

        ViewUtils.setOnClickListener(nextButton
                , new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (selecterRentTimeBean != null)
                            ((RentingActivity) getActivity()).showDate(selecterRentTimeBean);
                    }
                });

        ViewUtils.setOnClickListener(lastButton, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
    }


}
