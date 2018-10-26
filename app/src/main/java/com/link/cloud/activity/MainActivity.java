package com.link.cloud.activity;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.link.cloud.MacApplication;
import com.link.cloud.R;
import com.link.cloud.User;
import com.link.cloud.adapter.GroupLesson_Adapter;
import com.link.cloud.adapter.IndicatorViewAdapter;
import com.link.cloud.api.ApiFactory;
import com.link.cloud.api.BaseProgressSubscriber;
import com.link.cloud.api.bean.LessonBean;
import com.link.cloud.api.dataSourse.GroupLessonInResource;
import com.link.cloud.api.dataSourse.GroupLessonUser;
import com.link.cloud.base.AppBarActivity;
import com.link.cloud.bean.FingerprintsBean;
import com.link.cloud.bean.People;
import com.link.cloud.fragment.LessonListFragment;
import com.link.cloud.listener.DialogCancelListener;
import com.link.cloud.listener.FragmentListener;
import com.link.cloud.utils.DialogUtils;
import com.link.cloud.utils.NettyClientBootstrap;
import com.link.cloud.utils.Utils;
import com.shizhefei.view.indicator.IndicatorViewPager;
import com.shizhefei.view.indicator.RecyclerIndicatorView;
import com.shizhefei.view.indicator.slidebar.SpringBar;
import com.shizhefei.view.indicator.transition.OnTransitionTextListener;
import com.zitech.framework.data.network.response.ApiResponse;
import com.zitech.framework.data.network.subscribe.ProgressSubscriber;
import com.zitech.framework.utils.ToastMaster;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import butterknife.BindView;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

import static com.link.cloud.Constants.TCP_PORT;
import static com.link.cloud.Constants.TCP_URL;


public class MainActivity extends AppBarActivity implements DialogCancelListener {

    @BindView(R.id.member)
    TextView member;
    @BindView(R.id.manager)
    TextView manager;
    @BindView(R.id.choose_lesson)
    TextView chooseLesson;
    @BindView(R.id.lesson_in)
    TextView lessonIn;
    @BindView(R.id.choose_lesson_container)
    LinearLayout chooseLessonContainer;
    @BindView(R.id.group_lesson_fragment)
    RelativeLayout group_lesson_fragment;
    @BindView(R.id.choose_lesson_fragment)
    RelativeLayout choose_lesson_fragment;
    @BindView(R.id.lesson_name)
    TextView lessonName;
    @BindView(R.id.coach_name)
    TextView coachName;
    private DialogUtils dialogUtils;
    Realm realm;
    ValueAnimator animator;
    private List<String> date = new ArrayList<>();
    private ViewPager viewPager;
    private RecyclerIndicatorView scrollIndicatorView;
    private IndicatorViewPager indicatorViewPager;
    private List<Fragment> fragmentList = new ArrayList<>();
    private FragmentManager fragmentManager;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private GroupLesson_Adapter loadMoreAdapter;
    private ArrayList<GroupLessonInResource> groupInList = new ArrayList<>();
    private ArrayList<FingerprintsBean> groupUsers = new ArrayList<>();
    private ArrayList<GroupLessonUser.UserInfosBean> groupInUserList = new ArrayList<>();
    RealmResults<FingerprintsBean> userBeans;
    private String courseReleasePkcode;
    private IndicatorViewAdapter indicatorViewAdapter;

    private void getListDate(final boolean isRefresh) {
        ApiFactory.courseList().subscribe(new BaseProgressSubscriber<ApiResponse<List<LessonBean>>>(MainActivity.this) {
            @Override
            public void onNext(ApiResponse<List<LessonBean>> response) {
                if (isRefresh) {
                    ((LessonListFragment) indicatorViewAdapter.getCurrentFragment()).swipe.setRefreshing(false);
                }
                if (!date.isEmpty()) {
                    date.clear();
                    fragmentList.clear();
                    indicatorViewAdapter.title.clear();
                    indicatorViewAdapter.fragmentList.clear();
                    indicatorViewAdapter.notifyDataSetChanged();
                }
                for (LessonBean lessonBean : response.getData()) {
                    date.add("    " + lessonBean.getDate().substring(5) + "    ");
                    LessonListFragment lessonListFragment = new LessonListFragment();
                    lessonListFragment.setCourses(lessonBean.getCourses());
                    lessonListFragment.setFragmentListener(new FragmentListener() {
                        @Override
                        public void OnRefreshListener() {
                            getListDate(true);
                        }
                    });
                    fragmentList.add(lessonListFragment);
                }
                if (indicatorViewAdapter == null) {
                    indicatorViewAdapter = new IndicatorViewAdapter(fragmentManager, fragmentList, date, MainActivity.this);
                    indicatorViewPager.setAdapter(indicatorViewAdapter);
                } else {
                    indicatorViewAdapter.title.addAll(date);
                    indicatorViewAdapter.fragmentList.addAll(fragmentList);
                    indicatorViewAdapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                if (isRefresh) {
                    ((LessonListFragment) indicatorViewAdapter.getCurrentFragment()).swipe.setRefreshing(false);
                }
            }
        });

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    protected void initViews() {
        hideToolbar();
        dialogUtils = DialogUtils.getDialogUtils(this, this);
        realm = Realm.getDefaultInstance();
        userBeans = realm.where(FingerprintsBean.class).findAll();
        userBeans.addChangeListener(new RealmChangeListener<RealmResults<FingerprintsBean>>() {
            @Override
            public void onChange(RealmResults<FingerprintsBean> fingerprintsBeans) {
                groupUsers.clear();
                groupUsers.addAll(realm.copyFromRealm(fingerprintsBeans));
            }
        });
        animator = ValueAnimator.ofInt(0, 80);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int state = MacApplication.getVenueUtils().getState();
                if (state == 3) {
                    final String uid = MacApplication.getVenueUtils().identifyNewImgUser(groupUsers);
                    if (uid != null) {
                        ApiFactory.admissionCourse(uid, courseReleasePkcode).subscribe(new BaseProgressSubscriber<ApiResponse>(MainActivity.this) {
                            @Override
                            public void onNext(ApiResponse apiResponse) {
                                super.onNext(apiResponse);
                                onVeuenMsg(uid, "");
                                loadMoreAdapter.notifyDataSetChanged();
                            }

                            @Override
                            public void onError(Throwable e) {
                                super.onError(e);
                                onVeuenMsg(null, e.getMessage());
                            }
                        });
                    } else {
                        View view = View.inflate(MainActivity.this, R.layout.verify_fail, null);
                        dialogUtils.showVeuneFailDialog(view, getResources().getString(R.string.please_confirm_bind));
                    }

                }
                if ((int) (animation.getAnimatedValue()) >= 79) {
                    animator.setCurrentPlayTime(0);
                }
            }
        });
        animator.setDuration(4000);
        ExecutorService service = Executors.newFixedThreadPool(1);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                nettyClientBootstrap = new NettyClientBootstrap(MainActivity.this, MainActivity.this, TCP_PORT, TCP_URL, "{\"data\":{},\"msgType\":\"HEART_BEAT\",\"token\":\"" + User.get().getToken() + "\"}");
                nettyClientBootstrap.start();
                SendMsgToTcp("{\"data\":{},\"msgType\":\"HEART_BEAT\",\"token\":\"" + User.get().getToken() + "\"}");
            }
        };
        service.execute(runnable);
        viewPager = (ViewPager) findViewById(R.id.viewPageView);
        scrollIndicatorView = (RecyclerIndicatorView) findViewById(R.id.titleIndicator);
        int selectColorId = getResources().getColor(R.color.almost_white);
        int unSelectColorId = getResources().getColor(R.color.dark_black);
        scrollIndicatorView.setOnTransitionListener(new OnTransitionTextListener().setColor(selectColorId, unSelectColorId));
        scrollIndicatorView.setScrollBar(new SpringBar(this, getResources().getColor(R.color.red)));
        indicatorViewPager = new IndicatorViewPager(scrollIndicatorView, viewPager);
        fragmentManager = getSupportFragmentManager();
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.refresh_layout);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        getListDate(false);
    }


    private void initGroup() {
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.red));
        groupInList.clear();
        getGroupData();
        loadMoreAdapter = new GroupLesson_Adapter(groupInUserList, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(loadMoreAdapter);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getGroupData();
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
    }

    private void getGroupData() {
        ApiFactory.getRecentLesson().subscribe(new BaseProgressSubscriber<ApiResponse<ArrayList<GroupLessonInResource>>>(this) {
            @Override
            public void onNext(ApiResponse<ArrayList<GroupLessonInResource>> arrayListApiResponse) {
                super.onNext(arrayListApiResponse);
                groupInList.addAll(arrayListApiResponse.getData());
                if (arrayListApiResponse.getData().size() > 0) {
                    coachName.setText(arrayListApiResponse.getData().get(0).getStoreCoachName());
                    lessonName.setText(arrayListApiResponse.getData().get(0).getFitnessCourseName());
                    courseReleasePkcode = arrayListApiResponse.getData().get(0).getCourseReleasePkcode();
                    ApiFactory.getGroupUsers(courseReleasePkcode).subscribe(new BaseProgressSubscriber<ApiResponse<GroupLessonUser>>(MainActivity.this) {
                        @Override
                        public void onNext(final ApiResponse<GroupLessonUser> groupLessonUserApiResponse) {
                            super.onNext(groupLessonUserApiResponse);
                            groupInUserList.clear();
                            groupInUserList.addAll(groupLessonUserApiResponse.getData().getUserInfos());
                            loadMoreAdapter.notifyDataSetChanged();
                            realm.executeTransaction(new Realm.Transaction() {
                                @Override
                                public void execute(Realm realm) {
                                    realm.copyToRealm(groupLessonUserApiResponse.getData().getFingerprints());
                                }
                            });
                        }
                    });
                }

            }
        });
    }

    public void onVeuenMsg(String uid, String msg) {
        if (TextUtils.isEmpty(uid)) {
            View view = View.inflate(this, R.layout.verify_fail, null);
            dialogUtils.showVeuneFailDialog(view, msg);
        } else {
            View view = View.inflate(this, R.layout.verify_success, null);
            dialogUtils.showVeuneInOkDialog(view);
        }
        handler.sendEmptyMessageDelayed(0, 3000);
    }

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    dialogUtils.dissMiss();
                    animator.start();
                    break;
                case 1:
                    int state = MacApplication.getVenueUtils().getState();
                    if (state == 3) {
                        RealmResults<People> all = realm.where(People.class).findAll();
                        final String uid = MacApplication.getVenueUtils().identifyNewImg(realm.copyFromRealm(all));
                        ApiFactory.validateAdmin(uid).subscribe(new ProgressSubscriber<ApiResponse>(MainActivity.this) {
                            @Override
                            public void onNext(ApiResponse apiResponse) {
                                super.onNext(apiResponse);
                            }
                        });
                    }
                    handler.sendEmptyMessageDelayed(1, 1000);
                    break;
            }

        }
    };

    protected void SendMsgToTcp(String msg) {
        nettyClientBootstrap.startNetty(msg);
    }

    @OnClick({R.id.member, R.id.manager, R.id.lesson_in, R.id.choose_lesson, R.id.buy, R.id.lesson_consume, R.id.register})
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.member:
                handler.removeMessages(1);
                member.setBackground(getResources().getDrawable(R.drawable.border_red));
                manager.setBackground(null);
                member.setTextColor(getResources().getColor(R.color.almost_white));
                manager.setTextColor(getResources().getColor(R.color.text_gray));
                break;
            case R.id.manager:
                handler.sendEmptyMessageDelayed(1, 1000);
                View view = View.inflate(MainActivity.this, R.layout.veune_dialog, null);
                dialogUtils.showManagerDialog(view);
                manager.setBackground(getResources().getDrawable(R.drawable.border_red));
                member.setBackground(null);
                member.setTextColor(getResources().getColor(R.color.text_gray));
                manager.setTextColor(getResources().getColor(R.color.almost_white));
                break;
            case R.id.lesson_in:
                animator.start();
                lessonIn.setBackground(getResources().getDrawable(R.drawable.border_red_half_right));
                chooseLesson.setBackground(null);
                lessonIn.setTextColor(getResources().getColor(R.color.almost_white));
                chooseLesson.setTextColor(getResources().getColor(R.color.red));
                group_lesson_fragment.setVisibility(View.VISIBLE);
                choose_lesson_fragment.setVisibility(View.GONE);
                initGroup();
                break;
            case R.id.choose_lesson:
                animator.end();
                getListDate(false);
                chooseLesson.setBackground(getResources().getDrawable(R.drawable.border_red_half_left));
                lessonIn.setBackground(null);
                lessonIn.setTextColor(getResources().getColor(R.color.red));
                chooseLesson.setTextColor(getResources().getColor(R.color.almost_white));
                choose_lesson_fragment.setVisibility(View.VISIBLE);
                group_lesson_fragment.setVisibility(View.GONE);
                break;

            case R.id.buy:
                showActivity(FunctionalSelectionActivity.class);
                break;
            case R.id.register:
                showActivity(RegisterActivity.class);
                break;
            case R.id.lesson_consume:
                showActivity(PrivateEducationConsumActivity.class);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
        handler.removeCallbacksAndMessages(null);
    }

    @Override
    public void dialogCancel() {
        member.setBackground(getResources().getDrawable(R.drawable.border_red));
        manager.setBackground(null);
        member.setTextColor(getResources().getColor(R.color.almost_white));
        manager.setTextColor(getResources().getColor(R.color.text_gray));
        handler.removeMessages(1);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (group_lesson_fragment.isFocusable()) {
            animator.start();
        }
    }

    @Override
    public void onVenuePay() {

    }

}
