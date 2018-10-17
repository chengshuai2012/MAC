package com.link.cloud.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.link.cloud.R;
import com.link.cloud.adapter.GroupLesson_Adapter;
import com.link.cloud.listener.DialogCancelListener;
import com.link.cloud.listener.EndlessRecyclerOnScrollListener;
import com.link.cloud.utils.DialogUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by OFX002 on 2018/9/21.
 */

public class Group_Lesson_Fragment extends Fragment implements DialogCancelListener {
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private GroupLesson_Adapter loadMoreAdapter;
    private List<String> dataList = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View Group_Lesson_Fragment = View.inflate(getContext(), R.layout.group_lesson_fragment,null);
        swipeRefreshLayout = (SwipeRefreshLayout)Group_Lesson_Fragment.findViewById(R.id.refresh_layout);
        recyclerView = (RecyclerView) Group_Lesson_Fragment.findViewById(R.id.recycler_view);
        init();
        return Group_Lesson_Fragment;
    }
    private void init() {

        // 设置刷新控件颜色
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.red));

        // 模拟获取数据
        getData();
        loadMoreAdapter = new GroupLesson_Adapter(dataList,getActivity());
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
//
//        // 设置加载更多监听
//        recyclerView.addOnScrollListener(new EndlessRecyclerOnScrollListener() {
//            @Override
//            public void onLoadMore() {
//                loadMoreAdapter.setLoadState(loadMoreAdapter.LOADING);
//
//                if (dataList.size() < 52) {
//                    // 模拟获取网络数据，延时1s
//                    new Timer().schedule(new TimerTask() {
//                        @Override
//                        public void run() {
//                            getActivity().runOnUiThread(new Runnable() {
//                                @Override
//                                public void run() {
//                                    getData();
//                                    loadMoreAdapter.setLoadState(loadMoreAdapter.LOADING_COMPLETE);
//                                }
//                            });
//                        }
//                    }, 1000);
//                } else {
//                    // 显示加载到底的提示
//                    loadMoreAdapter.setLoadState(loadMoreAdapter.LOADING_END);
//                }
//            }
//        });
    }

    private void getData() {
        char letter = 'A';
        for (int i = 0; i < 26; i++) {
            dataList.add(String.valueOf(letter));
            letter++;
        }
    }
    public void onVeuenMsg(String uid){
        if(TextUtils.isEmpty(uid)){
            View view =View.inflate(getActivity(),R.layout.verify_fail,null);
            DialogUtils.getDialogUtils(getActivity(),this).showVeuneFailDialog(view);
        }else {
            View view =View.inflate(getActivity(),R.layout.verify_success,null);
            DialogUtils.getDialogUtils(getActivity(),this).showVeuneOkDialog(view);
        }
    }

    @Override
    public void dialogCancel() {

    }
}
