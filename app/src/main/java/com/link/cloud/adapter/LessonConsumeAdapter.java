package com.link.cloud.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.link.cloud.R;
import com.link.cloud.base.CardBaseAdapter;

import java.util.ArrayList;


/**
 * Created by ls on 2017/11/25.
 */

public class LessonConsumeAdapter extends CardBaseAdapter<LessonConsumeAdapter.LessonConsumeHolder> {
    public ArrayList<String > mData;
    public Context context;

    public LessonConsumeAdapter(ArrayList<String > mData, Context context) {
        this.mData = mData;
        this.context = context;
    }

    @Override
    public LessonConsumeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.lesson_consum_card, null);
        LessonConsumeHolder holder = new LessonConsumeHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        LessonConsumeHolder lessonConsumeHolder=(LessonConsumeHolder) holder;
        lessonConsumeHolder.tel_multi.setText(mData.get(position));
    }
    
    
    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public class LessonConsumeHolder extends RecyclerView.ViewHolder {
        public TextView tel_multi;

        public LessonConsumeHolder(View itemView) {
            super(itemView);
            tel_multi= itemView.findViewById(R.id.tel_multi);
        }
    }
}