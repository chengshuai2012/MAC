package com.link.cloud.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.link.cloud.R;

/**
 * Created by OFX002 on 2018/9/27.
 */

public class Pay_GridView_Adapter_Black extends BaseAdapter{
    Context context;
    int length;
    @Override
    public int getCount() {
        return length;
    }
    public Pay_GridView_Adapter_Black(Context context){
        this.context=context;
    }
    public void setLength(int length) {
        this.length = length;
        notifyDataSetChanged();
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
        return View.inflate(context, R.layout.dark_item,null);
    }
}
