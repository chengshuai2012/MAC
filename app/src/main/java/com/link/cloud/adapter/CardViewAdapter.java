package com.link.cloud.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.link.cloud.R;
import com.link.cloud.api.bean.MemberCardInfoBean;
import com.link.cloud.base.ListAdapter;


/**
 * 作者：qianlu on 2018/9/30 15:48
 * 邮箱：zar.l@qq.com
 */
public class CardViewAdapter extends ListAdapter<MemberCardInfoBean> {

    public CardViewAdapter(Context context) {
        super(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card_layout, parent, false);
        }
        TextView textView=ViewHolder.get(convertView,R.id.text);
        textView.setText(String.valueOf(position));
        return convertView;
    }
}
