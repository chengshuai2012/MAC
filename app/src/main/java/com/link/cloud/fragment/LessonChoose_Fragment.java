package com.link.cloud.fragment;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.link.cloud.R;
import com.link.cloud.adapter.IndicatorViewAdapter;
import com.link.cloud.api.ApiFactory;
import com.link.cloud.api.bean.LessonBean;
import com.link.cloud.base.BaseFragment;
import com.shizhefei.view.indicator.IndicatorViewPager;
import com.shizhefei.view.indicator.RecyclerIndicatorView;
import com.shizhefei.view.indicator.slidebar.SpringBar;
import com.shizhefei.view.indicator.transition.OnTransitionTextListener;
import com.zitech.framework.data.network.response.ApiResponse;
import com.zitech.framework.data.network.subscribe.ProgressSubscriber;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 49488 on 2018/9/20.
 */

public class LessonChoose_Fragment extends BaseFragment {
    private List<String> date = new ArrayList<>();
    private ViewPager viewPager;
    private RecyclerIndicatorView scrollIndicatorView;
    private IndicatorViewPager indicatorViewPager;


    private List<Fragment> fragmentList = new ArrayList<>();
    private FragmentManager fragmentManager;

    @Override
    public void onInflateView(View contentView) {
        super.onInflateView(contentView);
        initView(contentView);
    }


    private void initView(View contentView) {
        viewPager = contentView.findViewById(R.id.viewPageView);
        scrollIndicatorView = contentView.findViewById(R.id.titleIndicator);

        int selectColorId = getResources().getColor(R.color.almost_white);
        int unSelectColorId =  getResources().getColor(R.color.dark_black);

        scrollIndicatorView.setOnTransitionListener(new OnTransitionTextListener().setColor(selectColorId, unSelectColorId));
        scrollIndicatorView.setScrollBar(new SpringBar(getActivity(),getResources().getColor(R.color.red)));


        indicatorViewPager = new IndicatorViewPager(scrollIndicatorView, viewPager);

        fragmentManager = getChildFragmentManager();
        getListDate();
    }


    @Override
    protected int getContentViewId() {
        return R.layout.choose_lesson_fragment;
    }

    public void getListDate() {

        ApiFactory.courseList("2018-10-17").subscribe(new ProgressSubscriber<ApiResponse<List<LessonBean>>>(getActivity()) {

            @Override
            public void onNext(ApiResponse<List<LessonBean>> response) {
                if (!date.isEmpty()) {
                    date.clear();
                    fragmentList.clear();
                }
                for (LessonBean lessonBean : response.getData()) {
                    date.add(lessonBean.getDate());
                    LessonListFragment lessonListFragment = new LessonListFragment();
                    lessonListFragment.setCourses(lessonBean.getCourses());
                    fragmentList.add(lessonListFragment);
                }
                IndicatorViewAdapter indicatorViewAdapter = new IndicatorViewAdapter(fragmentManager, fragmentList, date, getActivity());
                indicatorViewPager.setAdapter(indicatorViewAdapter);
            }
        });

    }


}
