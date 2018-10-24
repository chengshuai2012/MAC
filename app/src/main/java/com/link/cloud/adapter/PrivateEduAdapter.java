package com.link.cloud.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.link.cloud.R;
import com.link.cloud.adapter.holder.PrivateEduViewHolder;
import com.link.cloud.api.bean.PrivateEduBean;
import com.link.cloud.base.BaseViewHolder;
import com.shizhefei.mvc.IDataAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by qianlu on 2018/5/16.
 * 邮箱：zar.l@qq.com
 */
public class PrivateEduAdapter extends RecyclerView.Adapter implements IDataAdapter<List<PrivateEduBean>> {

    private List<PrivateEduBean> items = new ArrayList<>();
    private BaseViewHolder.OnItemClickListener onItemClickListener;
    private Context context;
    public static HashMap<Integer, Boolean> isSelected= new HashMap<Integer, Boolean>();


    public PrivateEduAdapter(Context context) {
        this.context = context;
        init(false,-1);
    }

    public void setOnItemClickListener(BaseViewHolder.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public PrivateEduViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        PrivateEduViewHolder privateEduViewHolder = new PrivateEduViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.private_edu_item_layout, parent, false));
        if (onItemClickListener != null)
            privateEduViewHolder.setOnItemClickListener(onItemClickListener);
        return privateEduViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final PrivateEduBean privateEduBean = items.get(position);
        ((PrivateEduViewHolder) holder).setDates((Activity) context, privateEduBean, position, isSelected.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public void notifyDataChanged(List<PrivateEduBean> items, boolean isRefresh) {
        boolean empty = this.items.isEmpty();
        int sizeBeforeChange = this.items.size();
        if (isRefresh) {
            this.items.clear();
        }
        int size = this.items.size();
        this.items.addAll(items);
        if (isRefresh || empty) {
            if (items.size() >= sizeBeforeChange) {
                notifyItemRangeChanged(0, items.size());
            } else {
                notifyDataSetChanged();
            }
        } else {
            notifyItemRangeInserted(size, items.size());
        }
        init(false,-1);
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
    public List<PrivateEduBean> getData() {
        return items;
    }

    @Override
    public boolean isEmpty() {
        return items.isEmpty() ;
    }


}
