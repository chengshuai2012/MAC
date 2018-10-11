package com.link.cloud.adapter.holder;

import android.app.Activity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.TextView;

import com.link.cloud.R;
import com.link.cloud.api.bean.RentTimeBean;
import com.link.cloud.base.BaseViewHolder;
import com.zitech.framework.utils.ViewUtils;

/**
 * 作者：qianlu on 2018/10/9 10:26
 * 邮箱：zar.l@qq.com
 */
public class RentTimeViewHolder extends BaseViewHolder<RentTimeBean> {

    private CardView rootViewLayout;
    private TextView timeText;
    private TextView rentMoney;
    private TextView sureButton;


    public RentTimeViewHolder(View itemView) {
        super(itemView);
        initView(itemView);
    }

    private void initView(View itemView) {
        rootViewLayout = (CardView) itemView.findViewById(R.id.rootViewLayout);
        timeText = (TextView) itemView.findViewById(R.id.timeText);
        rentMoney = (TextView) itemView.findViewById(R.id.rentMoney);
        sureButton = (TextView) itemView.findViewById(R.id.sureButton);
    }

    @Override
    public void setData(Activity activity, RentTimeBean data, int position) {

    }

    public void setData(Activity activity, RentTimeBean data, final int position, boolean isSelecter) {
        if (isSelecter) {
            rootViewLayout.setBackground(activity.getResources().getDrawable(R.drawable.border_lesson_r56));
        } else {
            rootViewLayout.setBackground(activity.getResources().getDrawable(R.drawable.border_member_r56));
        }

        ViewUtils.setOnClickListener(rootViewLayout
                , new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (onItemClickListener != null) {
                                    onItemClickListener.onCheackClick(position);
                        }
                    }
                });


    }
}
