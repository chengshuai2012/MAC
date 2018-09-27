package com.link.cloud.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.link.cloud.R;
import com.link.cloud.adapter.PrivateEduAdapter;
import com.link.cloud.api.bean.PrivateEduBean;
import com.link.cloud.api.dataSourse.PrivateEduListDataSource;
import com.link.cloud.base.BaseFragment;
import com.link.cloud.widget.MVCSwipeRefreshHelper;

import java.util.List;

/**
 * @author qianlu
 * @date 2018/9/26.
 * GitHub：qiandailu
 * email：zar.l@qq.com
 * description：可购买的私教课程列表
 */
public class PrivateEduListFragment extends BaseFragment {
    private RecyclerView recycleView;
    private SwipeRefreshLayout swipeLayout;

    private MVCSwipeRefreshHelper<List<PrivateEduBean>> mvcHelper;
    private PrivateEduAdapter privateEduAdapter;

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_privateedulist;
    }


    @Override
    public void onInflateView(View contentView) {
        super.onInflateView(contentView);
        initialize(contentView);
    }

    private void initialize(View contentView) {

        recycleView = (RecyclerView) contentView.findViewById(R.id.recycleView);
        swipeLayout = (SwipeRefreshLayout) contentView.findViewById(R.id.swipeLayout);
        initDate();
    }

    private void initDate() {
        recycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        privateEduAdapter = new PrivateEduAdapter(getActivity());
        recycleView.setAdapter(privateEduAdapter);
        mvcHelper = new MVCSwipeRefreshHelper<>(swipeLayout);
        mvcHelper.setDataSource(new PrivateEduListDataSource());
        // 设置适配器
        mvcHelper.setAdapter(privateEduAdapter);
        // 加载数据
        mvcHelper.refresh();
    }

}
