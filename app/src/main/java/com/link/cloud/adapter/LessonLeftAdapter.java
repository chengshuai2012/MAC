package com.link.cloud.adapter;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;

import android.view.View;

import android.view.ViewGroup;

import android.widget.ImageView;

import android.widget.TextView;

import com.link.cloud.R;

import java.util.ArrayList;


/**
 * Created by 49488 on 2018/10/5.
 */



public class LessonLeftAdapter extends RecyclerView.Adapter<LessonLeftAdapter.ViewHolder> {

    private ArrayList<String> mDataList;

    static class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imageAvatar;

        TextView nameText;

        TextView contentsText;

        public ViewHolder(View itemView) {

            super(itemView);

        }

    }

    public  LessonLeftAdapter(ArrayList<String> listDatas){

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

        String listData = mDataList.get(position);


    }



    @Override

    public int getItemCount() {

        return mDataList.size();

    }

}

