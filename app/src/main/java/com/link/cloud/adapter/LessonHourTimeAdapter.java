package com.link.cloud.adapter;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.link.cloud.R;
import com.link.cloud.bean.Days;

import java.util.ArrayList;


/**
 * Created by 49488 on 2018/10/5.
 */



public class LessonHourTimeAdapter extends RecyclerView.Adapter<LessonHourTimeAdapter.ViewHolder> {

    private ArrayList<String> mDataList;
    private OnItemClickListener mClickListener;
    int clickPosition=0;
    static class ViewHolder extends RecyclerView.ViewHolder{

        SeekBar hourSeekBar;

        TextView hour;

        public ViewHolder(View itemView,OnItemClickListener mListener) {

            super(itemView);
            hour = itemView.findViewById(R.id.hour_of_day);
            hourSeekBar = itemView.findViewById(R.id.hour_seekbar);

        }


    }

    public LessonHourTimeAdapter(ArrayList<String> listDatas){

        mDataList = listDatas;

    }





    @Override

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pre_lesson_time_item,

                parent,false);

        ViewHolder holder = new ViewHolder(view,mClickListener);

        return holder;

    }

    public void setOnItemClickListener(OnItemClickListener listener) {

        this.mClickListener = listener;

    }

    @Override

    public void onBindViewHolder(final ViewHolder holder, final int position) {

        String listData = mDataList.get(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    clickPosition=position;
                    notifyDataSetChanged();
            }
        });
        holder.hourSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                mClickListener.onLessonHourItemClick(i,position);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
       holder.hour.setText(listData);

        if(clickPosition==position){
            holder.hourSeekBar.setVisibility(View.VISIBLE);
        }else {
            holder.hourSeekBar.setVisibility(View.GONE);
        }
    }



    public interface OnItemClickListener {
        void onLessonHourItemClick(int i, int postion);
    }

    @Override

    public int getItemCount() {

        return mDataList.size();

    }

}

