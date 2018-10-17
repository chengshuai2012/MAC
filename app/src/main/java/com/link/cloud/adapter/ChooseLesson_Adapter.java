package com.link.cloud.adapter;

import android.app.Activity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.link.cloud.R;

import java.util.List;


public class ChooseLesson_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {

    private List<String> dataList;

    private final int TYPE_ITEM = 1;

    private final int TYPE_FOOTER = 2;

    private int loadState = 2;

    public final int LOADING = 1;

    public final int LOADING_COMPLETE = 2;

    public final int LOADING_END = 3;
    Activity mContext;
    public ChooseLesson_Adapter(List<String> dataList, Activity mContext) {
        this.dataList = dataList;
        this.mContext=mContext;
    }
    public interface onItemClickLister{
        void OnClickCoachImage(int postion);
        void OnClickPre(int postion);
        void OnClickLesson(int postion);
    }
    onItemClickLister mListner;
    public void setOnItemClickListner(onItemClickLister mListner){
        this.mListner=mListner;
    }
    @Override
    public int getItemViewType(int position) {

//        if (position + 1 == getItemCount()) {
//            return TYPE_FOOTER;
//        } else {
//
//        }
        return TYPE_ITEM;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.choose_lesson_item, parent, false);
            return new RecyclerViewHolder(view);
        }
//        } else if (viewType == TYPE_FOOTER) {
//            View view = LayoutInflater.from(parent.getContext())
//                    .inflate(R.layout.layout_refresh_footer, parent, false);
//            return new FootViewHolder(view);
//        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof RecyclerViewHolder) {
            RecyclerViewHolder recyclerViewHolder = (RecyclerViewHolder) holder;
            recyclerViewHolder.coach_image.setTag(position);
            recyclerViewHolder.lesson_pre_check.setTag(position);
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

        }
    }

    @Override
    public int getItemCount() {
        return dataList.size() ;
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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.coach_image:
                mListner.OnClickCoachImage((int)v.getTag());
                break;
                case R.id.lesson_pre_check:
                mListner.OnClickPre((int)v.getTag());
                break;

        }
    }

    private class RecyclerViewHolder extends RecyclerView.ViewHolder {
        ImageView  coach_image;
        TextView lesson_pre_check;

        RecyclerViewHolder(View itemView) {
            super(itemView);
            coach_image = (ImageView) itemView.findViewById(R.id.coach_image);
            lesson_pre_check = (TextView) itemView.findViewById(R.id.lesson_pre_check);
            coach_image.setOnClickListener(ChooseLesson_Adapter.this);
            lesson_pre_check.setOnClickListener(ChooseLesson_Adapter.this);
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
