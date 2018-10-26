package com.link.cloud.adapter;

import android.app.Activity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.link.cloud.R;
import com.link.cloud.api.dataSourse.GroupLessonInResource;
import com.link.cloud.api.dataSourse.GroupLessonUser;
import com.link.cloud.listener.DialogCancelListener;
import com.link.cloud.utils.DialogUtils;

import java.util.ArrayList;
import java.util.List;


public class GroupLesson_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private ArrayList<GroupLessonUser.UserInfosBean> dataList;

    private final int TYPE_ITEM = 1;

    private final int TYPE_FOOTER = 2;

    private int loadState = 2;

    public final int LOADING = 1;

    public final int LOADING_COMPLETE = 2;

    public final int LOADING_END = 3;
    Activity activity;
    public GroupLesson_Adapter(ArrayList<GroupLessonUser.UserInfosBean> dataList, Activity activity) {
        this.dataList = dataList;
        this.activity=activity;
    }

    @Override
    public int getItemViewType(int position) {
            return TYPE_ITEM;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.group_lesson_item, parent, false);
            return new RecyclerViewHolder(view);

//        } else if (viewType == TYPE_FOOTER) {
//            View view = LayoutInflater.from(parent.getContext())
//                    .inflate(R.layout.layout_refresh_footer, parent, false);
//            return new FootViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof RecyclerViewHolder) {
            RecyclerViewHolder recyclerViewHolder = (RecyclerViewHolder) holder;
            recyclerViewHolder.memeber_name.setText(dataList.get(position).getNickname());
            recyclerViewHolder.tel_member.setText(new StringBuffer(dataList.get(position).getPhone()).replace(3,7,"****"));
            if(dataList.get(position).getStatus()==0){
                recyclerViewHolder.status_not_in.setText(activity.getResources().getString(R.string.status_not_in));
                recyclerViewHolder.status_not_in.setBackground(activity.getResources().getDrawable(R.drawable.border_red_gradient));
            }else {
                recyclerViewHolder.status_not_in.setText(activity.getResources().getString(R.string.status_in));
                recyclerViewHolder.status_not_in.setBackground(activity.getResources().getDrawable(R.drawable.border_gray_gradient));
            }
        }
//        } else if (holder instanceof FootViewHolder) {
//            FootViewHolder footViewHolder = (FootViewHolder) holder;
//            switch (loadState) {
//                case LOADING: // 正在加载
//                    footViewHolder.pbLoading.setVisibility(View.VISIBLE);
//                    footViewHolder.tvLoading.setVisibility(View.VISIBLE);
//                    footViewHolder.llEnd.setVisibility(View.GONE);
//                    break;
//
//                case LOADING_COMPLETE: // 加载完成
//                    footViewHolder.pbLoading.setVisibility(View.INVISIBLE);
//                    footViewHolder.tvLoading.setVisibility(View.INVISIBLE);
//                    footViewHolder.llEnd.setVisibility(View.GONE);
//                    break;
//
//                case LOADING_END: // 加载到底
//                    footViewHolder.pbLoading.setVisibility(View.GONE);
//                    footViewHolder.tvLoading.setVisibility(View.GONE);
//                    footViewHolder.llEnd.setVisibility(View.VISIBLE);
//                    break;
//
//                default:
//                    break;
//            }

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if (manager instanceof GridLayoutManager) {
            final GridLayoutManager gridManager = ((GridLayoutManager) manager);
            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return getItemViewType(position) == TYPE_FOOTER ? gridManager.getSpanCount() : 1;
                }
            });
        }
    }


    private class RecyclerViewHolder extends RecyclerView.ViewHolder {

        LinearLayout lesson_in;
        TextView status_not_in;
        TextView memeber_name;
        TextView tel_member;

        RecyclerViewHolder(View itemView) {
            super(itemView);
            //tvItem = (TextView) itemView.findViewById(R.id.tv_item);
            lesson_in = (LinearLayout) itemView.findViewById(R.id.lesson_in);
            status_not_in = (TextView) itemView.findViewById(R.id.status_not_in);
            memeber_name = (TextView) itemView.findViewById(R.id.memeber_name);
            tel_member = (TextView) itemView.findViewById(R.id.tel_member);
        }
    }

//    private class FootViewHolder extends RecyclerView.ViewHolder {
//
//        ProgressBar pbLoading;
//        TextView tvLoading;
//        LinearLayout llEnd;
//
//        FootViewHolder(View itemView) {
//            super(itemView);
//            pbLoading = (ProgressBar) itemView.findViewById(R.id.pb_loading);
//            tvLoading = (TextView) itemView.findViewById(R.id.tv_loading);
//            llEnd = (LinearLayout) itemView.findViewById(R.id.ll_end);
//        }
//    }

    public void setLoadState(int loadState) {
        this.loadState = loadState;
        notifyDataSetChanged();
    }
}
