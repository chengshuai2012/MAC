package com.link.cloud.adapter;


import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.link.cloud.R;
import com.shizhefei.view.indicator.IndicatorViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by luqian on 2018/1/29.
 */

public class IndicatorViewAdapter extends IndicatorViewPager.IndicatorFragmentPagerAdapter {

    List<Fragment> fragmentList = new ArrayList<Fragment>();
    private Context context;
    private List<String> title;

    public IndicatorViewAdapter(FragmentManager fragmentManager, List<Fragment> fragmentList, List<String> title, Context context) {
        super(fragmentManager);
        this.context = context;
        this.fragmentList = fragmentList;
        this.title = title;
    }


    @Override
    public int getCount() {
        return title.size();
    }

    @Override
    public View getViewForTab(int position, View convertView, ViewGroup container) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.tab_top, container, false);
        }
        TextView textView = (TextView) convertView;
        textView.setText(title.get(position));
        return convertView;
    }

    @Override
    public Fragment getFragmentForPage(int position) {
        return fragmentList.get(position);
    }
}
