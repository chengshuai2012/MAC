package com.link.cloud.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.link.cloud.R;
import com.link.cloud.adapter.holder.SingleBuyViewHolder;
import com.link.cloud.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SingleBuyItemAdapter extends RecyclerView.Adapter {


    private List<String> items = new ArrayList<>();
    private Context activity;
    private int mLocation = 0;
    private BaseViewHolder.OnItemClickListener onItemClickListener;
    public static HashMap<Integer, Boolean> isSelected;



    public void setOnItemClickListener(BaseViewHolder.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


    public SingleBuyItemAdapter(Context activity) {
        this.activity = activity;
    }

    public void setDate(List<String> data) {
        this.items = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SingleBuyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        SingleBuyViewHolder privateEduViewHolder = new SingleBuyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.single_buy_item_layout, parent, false));
        if (onItemClickListener != null)
            privateEduViewHolder.setOnItemClickListener(onItemClickListener);
        return privateEduViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
//        final String contentText = items.get(position);
//        ((SingleBuyViewHolder) holder).setDates();
    }


    // 初始化 设置所有item都为未选择
    public void init(boolean isSelecter, boolean isFirst) {
        isSelected = new HashMap<Integer, Boolean>();
        for (int i = 0; i < items.size(); i++) {
            if (i == 0 && isFirst) {
                isSelected.put(i, true);
            } else {
                isSelected.put(i, isSelecter);
            }

        }
    }

    @Override
    public int getItemCount() {
        return 5;
    }



    public static class ContentViewHolder extends RecyclerView.ViewHolder {
        private TextView contentText;


        public ContentViewHolder(View itemView) {
            super(itemView);
            contentText = itemView.findViewById(R.id.contentText);
        }
    }
}

