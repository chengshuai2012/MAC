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
 * Created by OFX002 on 2018/9/25.
 */

public class Lesson_Advantager extends BaseAdapter{
    List<CoachInfo.StoreCoachIntroduceBean.GoodCourseBean> data = new ArrayList();
    Context mContext;
    public Lesson_Advantager(Context mContext, List <CoachInfo.StoreCoachIntroduceBean.GoodCourseBean>list){
        data.clear();
        data.addAll(list);
        this.mContext =mContext;
    }

    public void setData(List<CoachInfo.StoreCoachIntroduceBean.GoodCourseBean> data) {
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
        View inflate = View.inflate(mContext, R.layout.advantage_lesson_item, null);
        TextView message = inflate.findViewById(R.id.message);
        message.setText(data.get(position).getGoods());
        return inflate;
    }
}
