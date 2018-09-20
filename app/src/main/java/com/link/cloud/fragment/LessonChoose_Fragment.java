package com.link.cloud.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.link.cloud.R;
import com.link.cloud.utils.DisplayUtil;
import com.shizhefei.view.indicator.Indicator;
import com.shizhefei.view.indicator.IndicatorViewPager;
import com.shizhefei.view.indicator.RecyclerIndicatorView;
import com.shizhefei.view.indicator.slidebar.SpringBar;
import com.shizhefei.view.indicator.transition.OnTransitionTextListener;

/**
 * Created by 49488 on 2018/9/20.
 */

public class LessonChoose_Fragment extends Fragment {
    private IndicatorViewPager indicatorViewPager;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View lesson_choose = View.inflate(getContext(), R.layout.choose_lesson_fragment,null);
        ViewPager viewPager = (ViewPager)lesson_choose. findViewById(R.id.year_viewPager);
        Indicator indicator = (RecyclerIndicatorView) lesson_choose.findViewById(R.id.year_indicator);
        int selectColorId = getResources().getColor(R.color.almost_white);
        int unSelectColorId =  getResources().getColor(R.color.red);
        indicator.setOnTransitionListener(new OnTransitionTextListener().setColor(selectColorId, unSelectColorId));
        indicator.setScrollBar(new SpringBar(getActivity(), Color.RED));
        viewPager.setOffscreenPageLimit(4);
        indicatorViewPager = new IndicatorViewPager(indicator, viewPager);
        indicatorViewPager.setAdapter(new YearAdapter(1800, 10000000));
        indicatorViewPager.setCurrentItem(2016-1800, false);
        return lesson_choose;
    }
    private class YearAdapter extends IndicatorViewPager.IndicatorFragmentPagerAdapter {
        private int startYear;
        private int endYear;

        public YearAdapter(int startYear, int endYear) {
            super(getActivity().getSupportFragmentManager());
            this.startYear = startYear;
            this.endYear = endYear;
        }

        @Override
        public View getViewForTab(int position, View convertView, ViewGroup container) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.tab_top, container, false);
            }
            TextView textView = (TextView) convertView;
            int padding = DisplayUtil.dipToPix(getActivity(), 12);
            textView.setPadding(padding, 0, padding, 0);
            textView.setText(String.valueOf(startYear + position));
            return convertView;
        }

        @Override
        public Fragment getFragmentForPage(int position) {
            YearFragment yearFragment = new YearFragment();
            Bundle a = new Bundle();
            a.putInt(YearFragment.INTENT_INT_POSITION, position);
            yearFragment.setArguments(a);
            return yearFragment;
        }

        @Override
        public int getCount() {
            return endYear - startYear;
        }
    }

}
