package com.link.cloud.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.link.cloud.R;
import com.link.cloud.api.dataSourse.CoachInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 49488 on 2018/10/23.
 */

public class TipsAdapter extends BaseAdapter{
    List<CoachInfo.FitnessCourseContextBean.ReminderBean > data = new ArrayList();
    Context mContext;
    public TipsAdapter(Context mContext, List <CoachInfo.FitnessCourseContextBean.ReminderBean >list){
        data.clear();
        data.addAll(list);
        this.mContext =mContext;
    }

    public void setData(List<CoachInfo.FitnessCourseContextBean.ReminderBean > data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View inflate = View.inflate(mContext, R.layout.tips_item, null);
        TextView tv = inflate.findViewById(R.id.message);
        tv.setText(data.get(position).getText());
        return inflate;
    }
}
