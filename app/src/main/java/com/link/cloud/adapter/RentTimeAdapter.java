package com.link.cloud.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.link.cloud.R;
import com.link.cloud.adapter.holder.RentTimeViewHolder;
import com.link.cloud.adapter.holder.SingleBuyViewHolder;
import com.link.cloud.api.bean.RentTimeBean;
import com.link.cloud.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RentTimeAdapter extends RecyclerView.Adapter {


    private List<RentTimeBean> items = new ArrayList<>();
    private Context activity;
    private int mLocation = 0;
    private BaseViewHolder.OnItemClickListener onItemClickListener;
    public static HashMap<Integer, Boolean> isSelected = new HashMap<Integer, Boolean>();


    public void setOnItemClickListener(BaseViewHolder.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


    public RentTimeAdapter(Context activity) {
        this.activity = activity;
    }

    public void setDate(List<RentTimeBean> data) {
        this.items = data;
        init(false, -1);
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public RentTimeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RentTimeViewHolder rentTimeViewHolder = new RentTimeViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.rent_time_item_layout, parent, false));
        if (onItemClickListener != null)
            rentTimeViewHolder.setOnItemClickListener(onItemClickListener);
        return rentTimeViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final RentTimeBean contentText = items.get(position);
        ((RentTimeViewHolder) holder).setData((Activity) activity, contentText, position, isSelected.get(position));
    }


    // 初始化 设置所有item都为未选择
    public void init(boolean isSelecter, int position) {
        for (int i = 0; i < items.size(); i++) {
            if (i == position) {
                isSelected.put(i, true);
            } else {
                isSelected.put(i, isSelecter);
            }
        }
    }


    @Override
    public int getItemCount() {
        return items.size();
    }


}

