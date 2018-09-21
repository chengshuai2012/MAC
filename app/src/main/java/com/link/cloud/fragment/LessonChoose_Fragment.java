package com.link.cloud.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.link.cloud.R;
import com.link.cloud.adapter.ChooseLesson_Adapter;
import com.link.cloud.listener.EndlessRecyclerOnScrollListener;
import com.link.cloud.utils.DisplayUtil;
import com.shizhefei.view.indicator.Indicator;
import com.shizhefei.view.indicator.IndicatorViewPager;
import com.shizhefei.view.indicator.RecyclerIndicatorView;
import com.shizhefei.view.indicator.slidebar.SpringBar;
import com.shizhefei.view.indicator.transition.OnTransitionTextListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by 49488 on 2018/9/20.
 */

public class LessonChoose_Fragment extends Fragment {
    private IndicatorViewPager indicatorViewPager;
    private LessonAdapter adapter;
    private String [] date = new String[]{"12月34日","9月5日","9月6日","9月7日","9月8日","9月10日"};
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View lesson_choose = View.inflate(getContext(), R.layout.choose_lesson_fragment,null);
        ViewPager viewPager =lesson_choose. findViewById(R.id.lesson_viewPager);
        Indicator indicator = (RecyclerIndicatorView) lesson_choose.findViewById(R.id.lesson_indicator);
        int selectColorId = getResources().getColor(R.color.almost_white);
        int unSelectColorId =  getResources().getColor(R.color.red);
        indicator.setOnTransitionListener(new OnTransitionTextListener().setColor(selectColorId, unSelectColorId));
        indicator.setScrollBar(new SpringBar(getActivity(), Color.RED));
        viewPager.setOffscreenPageLimit(0);
        indicatorViewPager = new IndicatorViewPager(indicator, viewPager);
        adapter = new LessonAdapter();
        indicatorViewPager.setAdapter(adapter);
        indicatorViewPager.setCurrentItem(0, false);
        return lesson_choose;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private class LessonAdapter extends IndicatorViewPager.IndicatorViewPagerAdapter {
        @Override
        public View getViewForTab(int position, View convertView, ViewGroup container) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.tab_top, container, false);
            }
            TextView textView = (TextView) convertView;
            int padding = DisplayUtil.dipToPix(getActivity(), 12);
            textView.setPadding(padding, 0, padding, 0);
            textView.setText(date[position]);
            return convertView;
        }

        @Override
        public View getViewForPage(int position, View convertView, ViewGroup container) {
            View Group_Lesson_Fragment = View.inflate(getContext(), R.layout.fragment_choose_lesson,null);
            final SwipeRefreshLayout  swipeRefreshLayout = (SwipeRefreshLayout)Group_Lesson_Fragment.findViewById(R.id.swipe_refresh_layout);
            RecyclerView recyclerView = (RecyclerView) Group_Lesson_Fragment.findViewById(R.id.recycler_view);
            // 设置刷新控件颜色
            swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.red));
            // 模拟获取数据
            final ChooseLesson_Adapter loadMoreAdapter = new ChooseLesson_Adapter(dataList);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerView.setAdapter(loadMoreAdapter);
            // 设置下拉刷新

            swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    // 刷新数据
                    dataList.clear();
                    getData();
                    loadMoreAdapter.notifyDataSetChanged();

                    // 延时1s关闭下拉刷新
                    swipeRefreshLayout.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (swipeRefreshLayout != null && swipeRefreshLayout.isRefreshing()) {
                                swipeRefreshLayout.setRefreshing(false);
                            }
                        }
                    }, 1000);
                }
            });
            // 设置加载更多监听
            recyclerView.addOnScrollListener(new EndlessRecyclerOnScrollListener() {
                @Override
                public void onLoadMore() {
                    loadMoreAdapter.setLoadState(loadMoreAdapter.LOADING);

                    if (dataList.size() < 52) {
                        // 模拟获取网络数据，延时1s
                        new Timer().schedule(new TimerTask() {
                            @Override
                            public void run() {
                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        getData();
                                        loadMoreAdapter.setLoadState(loadMoreAdapter.LOADING_COMPLETE);
                                    }
                                });
                            }
                        }, 1000);
                    } else {
                        // 显示加载到底的提示
                        loadMoreAdapter.setLoadState(loadMoreAdapter.LOADING_END);
                    }
                }
            });
            return Group_Lesson_Fragment;
        }


        @Override
        public int getCount() {
            return date.length;
        }
    }
    private List<String> dataList = new ArrayList<>();
    private void getData() {
        char letter = 'A';
        for (int i = 0; i < 26; i++) {
            dataList.add(String.valueOf(letter));
            letter++;
        }
    }
}
