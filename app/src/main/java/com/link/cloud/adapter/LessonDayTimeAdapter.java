package com.link.cloud.adapter;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.link.cloud.R;
import com.link.cloud.bean.Days;

import java.util.ArrayList;


/**
 * Created by 49488 on 2018/10/5.
 */



public class LessonDayTimeAdapter extends RecyclerView.Adapter<LessonDayTimeAdapter.ViewHolder> {

    private ArrayList<Days> mDataList;
    private OnItemClickListener mClickListener;
    int clickPosition=0;
    static class ViewHolder extends RecyclerView.ViewHolder{

        TextView dayWeek;

        TextView dayMonth;

        TextView dayLaunch;

        LinearLayout day_of_month_ll;

        public ViewHolder(View itemView,OnItemClickListener mListener) {

            super(itemView);
            dayWeek = itemView.findViewById(R.id.day_of_week);
            dayLaunch = itemView.findViewById(R.id.day_of_launch);
            dayMonth = itemView.findViewById(R.id.day_of_month);
            day_of_month_ll = itemView.findViewById(R.id.day_of_month_ll);

        }


    }

    public LessonDayTimeAdapter(ArrayList<Days> listDatas){

        mDataList = listDatas;

    }





    @Override

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pre_lesson_day_item,

                parent,false);

        ViewHolder holder = new ViewHolder(view,mClickListener);

        return holder;

    }

    public void setOnItemClickListener(OnItemClickListener listener) {

        this.mClickListener = listener;

    }

    @Override

    public void onBindViewHolder(final ViewHolder holder, final int position) {

        Days listData = mDataList.get(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mClickListener!=null){
                    mClickListener.onItemClick(view,position);
                    clickPosition=position;
                    notifyDataSetChanged();
                }
            }
        });
        holder.dayLaunch.setText(listData.getLauar());
        holder.dayMonth.setText(listData.getDay());
        holder.dayWeek.setText(listData.getWeek());
        if(clickPosition==position){
            holder.dayMonth.setTextColor(Color.parseColor("#F3F3F3"));
            holder.dayLaunch.setTextColor(Color.parseColor("#F3F3F3"));
            holder.day_of_month_ll.setBackgroundResource(R.drawable.border_red_gradient_round);
        }else {
            holder.dayMonth.setTextColor(Color.parseColor("#333333"));
            holder.dayLaunch.setTextColor(Color.parseColor("#333333"));
            holder.day_of_month_ll.setBackground(null);
        }
    }



    public interface OnItemClickListener {
        void onItemClick(View view, int postion);
    }

    @Override

    public int getItemCount() {

        return mDataList.size();

    }

}

