package com.link.cloud.adapter.holder;

import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.link.cloud.R;
import com.link.cloud.api.bean.PrivateEduBean;
import com.link.cloud.base.BaseViewHolder;
import com.link.cloud.widget.TagLayout;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @author qianlu
 * @date 2018/9/26.
 * GitHub：qiandailu
 * email：zar.l@qq.com
 * description：
 */
public class SingleBuyViewHolder extends BaseViewHolder<PrivateEduBean> {

    private TextView cardName;
    private TextView cardMoney;
    private TextView validityPeriod;
    private Button payButton;

    public SingleBuyViewHolder(View itemView) {
        super(itemView);
        initView(itemView);
    }

    private void initView(View itemView) {
        cardName = (TextView) itemView.findViewById(R.id.cardName);
        cardMoney = (TextView) itemView.findViewById(R.id.cardMoney);
        validityPeriod = (TextView) itemView.findViewById(R.id.validityPeriod);
        payButton = (Button) itemView.findViewById(R.id.payButton);
    }

    @Override
    public void setData(Activity activity, PrivateEduBean data, int position) {

    }

    public void setDates(Activity activity, PrivateEduBean data, int position, boolean isSeceter) {

    }

}
