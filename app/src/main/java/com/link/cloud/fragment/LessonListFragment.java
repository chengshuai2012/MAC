package com.link.cloud.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.link.cloud.R;
import com.link.cloud.adapter.ChooseLesson_Adapter;
import com.link.cloud.api.bean.LessonItemBean;
import com.link.cloud.base.BaseFragment;
import com.link.cloud.listener.DialogCancelListener;
import com.link.cloud.listener.FragmentListener;

import java.util.List;

/**
 * 作者：qianlu on 2018/10/17 17:17
 * 邮箱：zar.l@qq.com
 */
public class LessonListFragment extends BaseFragment implements DialogCancelListener {

    private List<LessonItemBean> courses;
    private android.support.v4.widget.SwipeRefreshLayout swipe;
    private android.support.v7.widget.RecyclerView recycle;
    private FragmentListener fragmentListener;


    public void setFragmentListener(FragmentListener fragmentListener) {
        this.fragmentListener = fragmentListener;
    }

    public void setCourses(List<LessonItemBean> courses) {
        this.courses = courses;
    }

    @Override
    public void onInflateView(View contentView) {
        super.onInflateView(contentView);
        initView(contentView);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_lessonlist;
    }


    private void initView(View contentView) {
        swipe = (SwipeRefreshLayout) contentView.findViewById(R.id.swipe);
        recycle = (RecyclerView) contentView.findViewById(R.id.recycle);
        recycle.setLayoutManager(new LinearLayoutManager(getActivity()));
        ChooseLesson_Adapter loadMoreAdapter = new ChooseLesson_Adapter(courses, getActivity());
        recycle.setAdapter(loadMoreAdapter);
        loadMoreAdapter.setOnItemClickListner(new ChooseLesson_Adapter.onItemClickLister() {
            @Override
            public void OnClickCoachImage(int postion) {

            }

            @Override
            public void OnClickPre(int postion) {

            }

            @Override
            public void OnClickLesson(int postion) {

            }
        });
        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (null != fragmentListener) {
                    fragmentListener.OnRefreshListener();
                }
            }
        });

    }


    @Override
    public void dialogCancel() {

    }
}