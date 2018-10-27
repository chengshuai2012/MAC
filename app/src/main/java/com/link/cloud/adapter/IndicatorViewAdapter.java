package com.link.cloud.adapter;


import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.link.cloud.R;
import com.link.cloud.activity.MainActivity;
import com.link.cloud.activity.PreGroupLessonActivity;
import com.link.cloud.api.ApiFactory;
import com.link.cloud.api.bean.LessonItemBean;
import com.link.cloud.api.dataSourse.CoachInfo;
import com.link.cloud.base.BaseActivity;
import com.link.cloud.bean.MainFragment;
import com.link.cloud.listener.DialogCancelListener;
import com.link.cloud.listener.FragmentListener;
import com.link.cloud.utils.DialogUtils;
import com.shizhefei.view.indicator.IndicatorViewPager;
import com.zitech.framework.data.network.response.ApiResponse;
import com.zitech.framework.data.network.subscribe.ProgressSubscriber;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by luqian on 2018/1/29.
 */

public class IndicatorViewAdapter extends IndicatorViewPager.IndicatorViewPagerAdapter implements DialogCancelListener {

    public ArrayList<MainFragment> fragmentList = new ArrayList<>();
    private Context context;
    private DialogUtils dialogUtils;
    private FragmentListener fragmentListener;
    public IndicatorViewAdapter(Context context,FragmentListener listener,ArrayList<MainFragment>data) {
        this.context = context;
        fragmentListener=listener;
        fragmentList.addAll(data);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public View getViewForTab(int position, View convertView, ViewGroup container) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.tab_top, container, false);
        }
        TextView textView = (TextView) convertView;
        textView.setText(fragmentList.get(position).getTip());
        return convertView;
    }

    @Override
    public View getViewForPage(final int position, View convertView, ViewGroup container) {
        View view= null;
        final List<LessonItemBean> courses = fragmentList.get(position).getCourses();
        if(convertView==null){
            view =View.inflate(context,R.layout.fragment_lessonlist,null);
        }else {
            view=convertView;
        }
        final SwipeRefreshLayout swipe = (SwipeRefreshLayout) view.findViewById(R.id.swipe);
        RecyclerView recycle = (RecyclerView) view.findViewById(R.id.recycle);
        swipe.setColorSchemeColors(context.getResources().getColor(R.color.red));
        recycle.setLayoutManager(new LinearLayoutManager(context));
        ChooseLesson_Adapter loadMoreAdapter = new ChooseLesson_Adapter(courses,(MainActivity)context);
        recycle.setAdapter(loadMoreAdapter);
        loadMoreAdapter.setOnItemClickListner(new ChooseLesson_Adapter.onItemClickLister() {
            @Override
            public void OnClickCoachImage(int postion) {
                com.orhanobut.logger.Logger.e(postion+"");
                String courseReleasePkcode = courses.get(postion).getCourseReleasePkcode();
                ApiFactory.coursedetail(courseReleasePkcode).subscribe(new ProgressSubscriber<ApiResponse<CoachInfo>>(context) {
                    @Override
                    public void onNext(ApiResponse<CoachInfo> coachInfoApiResponse) {
                        super.onNext(coachInfoApiResponse);
                        View view = View.inflate(context,R.layout.coach_dialog,null);
                        dialogUtils = DialogUtils.getDialogUtils((MainActivity)context, IndicatorViewAdapter.this);
                        dialogUtils.showIntroCoachDialog(view,coachInfoApiResponse.getData());
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        dialogUtils.dissMiss();
                    }
                });

            }

            @Override
            public void OnClickPre(int postion) {
                Gson gson= new Gson();
                String json = gson.toJson(courses.get(postion));
                ((BaseActivity)context).showActivity(PreGroupLessonActivity.class,"courseReleasePkcode",json);
            }

            @Override
            public void OnClickLesson(int postion) {
                String courseReleasePkcode = courses.get(postion).getCourseReleasePkcode();
                ApiFactory.coursedetail(courseReleasePkcode).subscribe(new ProgressSubscriber<ApiResponse<CoachInfo>>(context) {
                    @Override
                    public void onNext(ApiResponse<CoachInfo> coachInfoApiResponse) {
                        super.onNext(coachInfoApiResponse);
                        View view = View.inflate(context,R.layout.lesson_dialog,null);
                        dialogUtils.showIntroLessonDialog(view,coachInfoApiResponse.getData());
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        dialogUtils.dissMiss();
                    }
                });
            }
        });
        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (null != fragmentListener) {
                    fragmentListener.OnRefreshListener(position);
                }
            }
        });
        return view;
    }
    @Override
    public void dialogCancel() {

    }

    @Override
    public void onVenuePay() {

    }
}
