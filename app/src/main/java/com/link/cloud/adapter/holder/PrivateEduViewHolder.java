package com.link.cloud.adapter.holder;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.link.cloud.R;
import com.link.cloud.User;
import com.link.cloud.adapter.TagAdapter;
import com.link.cloud.api.bean.PriceLevelBean;
import com.link.cloud.api.bean.PrivateEduBean;
import com.link.cloud.base.BaseViewHolder;
import com.zitech.framework.utils.ToastMaster;
import com.zitech.framework.utils.ViewUtils;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @author qianlu
 * @date 2018/9/26.
 * GitHub：qiandailu
 * email：zar.l@qq.com
 * description：
 */
public class PrivateEduViewHolder extends BaseViewHolder<PrivateEduBean> {
    private CircleImageView eduImage;
    private TextView calssName;
    private TextView coachName;
    private RecyclerView tagLayout;
    private LinearLayout rootViewLayout;


    private int selecterPosition=-1;



    public PrivateEduViewHolder(View itemView) {
        super(itemView);
        eduImage = (CircleImageView) itemView.findViewById(R.id.eduImage);
        calssName = (TextView) itemView.findViewById(R.id.calssName);
        coachName = (TextView) itemView.findViewById(R.id.coachName);
        tagLayout = (RecyclerView) itemView.findViewById(R.id.recycleView);
        rootViewLayout = (LinearLayout) itemView.findViewById(R.id.rootViewLayout);
    }


    @Override
    public void setData(Activity activity, PrivateEduBean data, int position) {

    }

    public void setDates(final Activity activity, final PrivateEduBean data, final int position, boolean isSeceter) {
        if (isSeceter) {
            rootViewLayout.setBackground(activity.getResources().getDrawable(R.drawable.private_edu_item_bg));
        } else {
            rootViewLayout.setBackground(activity.getResources().getDrawable(R.drawable.private_edu_member_bg));
        }
        List<String> tagsList = new ArrayList<>();


        if (data.getPriceLevel()!=null){
            for (PriceLevelBean priceLevelBean : data.getPriceLevel()) {
                tagsList.add(priceLevelBean.getCourseTotal());
            }
            LinearLayoutManager layoutManager = new LinearLayoutManager(activity);
            layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            tagLayout.setLayoutManager(layoutManager);
            final TagAdapter tagAdapter = new TagAdapter(activity, tagsList);
            tagAdapter.setSeceter(isSeceter);
            tagLayout.setAdapter(tagAdapter);
            tagAdapter.setOnItemClickListner(new TagAdapter.onItemClickLister() {
                @Override
                public void OnClickPre(int thisPosition) {
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClick(data.getPriceLevel().get(thisPosition), position);
                    }

                }
            });

        }

        Glide.with(activity).load(data.getImgurl()).into(eduImage);
        calssName.setText(data.getFitnessCourseName());
        coachName.setText(data.getCoachName());


    }

}
