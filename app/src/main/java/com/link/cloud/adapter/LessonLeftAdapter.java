package com.link.cloud.adapter;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;

import android.view.View;

import android.view.ViewGroup;

import android.widget.ImageView;

import android.widget.TextView;

import com.link.cloud.R;
import com.link.cloud.api.request.LessonPred;

import java.util.ArrayList;


/**
 * Created by 49488 on 2018/10/5.
 */



public class LessonLeftAdapter extends RecyclerView.Adapter<LessonLeftAdapter.ViewHolder> {

    private ArrayList<LessonPred.NotbookBean> mDataList;

    static class ViewHolder extends RecyclerView.ViewHolder{

        TextView coach;
        TextView nameText;


        public ViewHolder(View itemView) {

            super(itemView);
            nameText = itemView.findViewById(R.id.lesson_name);
            coach = itemView.findViewById(R.id.coach_name);

        }

    }

    public  LessonLeftAdapter(ArrayList<LessonPred.NotbookBean> listDatas){

        mDataList = listDatas;

    }





    @Override

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lesson_left_item,

                parent,false);

        ViewHolder holder = new ViewHolder(view);

        return holder;

    }



    @Override

    public void onBindViewHolder(ViewHolder holder, int position) {

        LessonPred.NotbookBean listData = mDataList.get(position);
        holder.coach.setText(listData.getCoachName());
        holder.nameText.setText(listData.getFitnessCourseName());

    }



    @Override

    public int getItemCount() {

        return mDataList.size();

    }

}

