package com.link.cloud.activity;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dinuscxj.progressbar.CircleProgressBar;
import com.link.cloud.MacApplication;
import com.link.cloud.R;
import com.link.cloud.adapter.LessonLeftAdapter;
import com.link.cloud.api.ApiFactory;
import com.link.cloud.api.BaseProgressSubscriber;
import com.link.cloud.api.request.LessonPred;
import com.link.cloud.api.response.CoachBean;
import com.link.cloud.base.AppBarActivity;
import com.link.cloud.bean.People;
import com.link.cloud.widget.PublicTitleView;
import com.zitech.framework.data.network.response.ApiResponse;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

@SuppressLint("Registered")
public class PrivateEducationConsumActivity extends AppBarActivity implements PublicTitleView.onItemClickListener {
    @BindView(R.id.bind_way)
    TextView bindWay;
    @BindView(R.id.venue_image)
    CircleImageView venueImage;
    @BindView(R.id.custom_progress)
    CircleProgressBar customProgress;
    @BindView(R.id.bind_venue_intro)
    TextView bindVenueIntro;
    @BindView(R.id.bind_venue_intro_below)
    TextView bindVenueIntroBelow;
    @BindView(R.id.lesson_consum_one)
    RelativeLayout lessonConsumOne;
    @BindView(R.id.multi_card)
    RecyclerView multi_card;
    @BindView(R.id.card_num)
    TextView cardNum;
    @BindView(R.id.confirm_consume)
    TextView confirmConsume;
    @BindView(R.id.lesson_consum_two)
    RelativeLayout lessonConsumTwo;
    @BindView(R.id.consume_lesson_today)
    TextView consumeLessonToday;
    @BindView(R.id.had_consume_lesson)
    TextView hadConsumeLesson;
    @BindView(R.id.lesson_name)
    TextView lessonName;
    @BindView(R.id.coach_name)
    TextView coachName;
    @BindView(R.id.lesson_time)
    TextView lessonTime;
    @BindView(R.id.lesson_consum_now_rl)
    RelativeLayout lessonConsumNowRl;
    @BindView(R.id.left_lesson_today)
    TextView leftLessonToday;
    @BindView(R.id.lesson_left_recyclerView)
    RecyclerView lessonLeftRecyclerView;
    @BindView(R.id.confirm_consume_finish)
    TextView confirmConsumeFinish;
    @BindView(R.id.lesson_consum_three)
    RelativeLayout lessonConsumThree;
    private PublicTitleView publicTitle;
    private ArrayList<LessonPred.NotbookBean> mList= new ArrayList();
    ValueAnimator animator;
    RealmResults<People> peoples ;
    ArrayList<People> nowPeople = new ArrayList<>();
    Realm realm;
    private LessonLeftAdapter listAdapter;
    private String memberCoursePkcode;
    private String userUid;

    @Override
    protected void initViews() {
        setTitle(R.drawable.handy_logo);
        publicTitle = (PublicTitleView) findViewById(R.id.publicTitle);
        publicTitle.setTags("1.学员确认", "2.选择课程", "3.教练确认", "4.消课成功");
        publicTitle.setTitleText(getString(R.string.lesson_consum));
        publicTitle.setFinshText(getResources().getString(R.string.back_home));
        publicTitle.setItemClickListener(this);
        customProgress.setProgressFormatter(null);
        mList = new ArrayList<>();
        realm= Realm.getDefaultInstance();
         RealmResults<People> peoples = realm.where(People.class).findAll();
         peoples.addChangeListener(new RealmChangeListener<RealmResults<People>>() {
             @Override
             public void onChange(RealmResults<People> people) {
                 nowPeople.clear();
                 nowPeople.addAll(realm.copyFromRealm(people));
             }
         });
         bindWay.setText("学员确认");
         nowPeople.addAll(realm.copyFromRealm(peoples));
//        setData();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        lessonLeftRecyclerView.setLayoutManager(layoutManager);
        listAdapter = new LessonLeftAdapter(mList);
        lessonLeftRecyclerView.setAdapter(listAdapter);
        animator = ValueAnimator.ofInt(0, 80);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int state = MacApplication.getVenueUtils().getState();
                int progress = (int) animation.getAnimatedValue();
                if(customProgress!=null){
                    customProgress.setProgress(progress);
                }
                if (state == 3) {
                    userUid = MacApplication.getVenueUtils().identifyNewImg(nowPeople);
                    com.orhanobut.logger.Logger.e(userUid);
                    ApiFactory.getPersonalClass(userUid).subscribe(new BaseProgressSubscriber<ApiResponse<LessonPred>>(PrivateEducationConsumActivity.this) {
                        @Override
                        public void onNext(ApiResponse<LessonPred> lessonPredApiResponse) {
                            super.onNext(lessonPredApiResponse);
                            publicTitle.nextPosition();
                            bindWay.setText("选择课程");
                            lessonConsumOne.setVisibility(View.GONE);
                            lessonConsumThree.setVisibility(View.VISIBLE);
                            memberCoursePkcode = lessonPredApiResponse.getData().getBook().get(0).getMemberCoursePkcode();
                            lessonName.setText(lessonPredApiResponse.getData().getBook().get(0).getFitnessCourseName()+"");
                            lessonTime.setText(lessonPredApiResponse.getData().getBook().get(0).getBegtime()+"");
                            coachName.setText(lessonPredApiResponse.getData().getBook().get(0).getCoachNikename()+"");
                            mList.clear();
                            mList.addAll(lessonPredApiResponse.getData().getNotbook());

                        }

                        @Override
                        public void onError(Throwable e) {
                            super.onError(e);
                        }
                    });
                }
                if(progress>=79){
                    animator.setCurrentPlayTime(0);
                }
            }
        });
        animator.setDuration(40000);
        animator.start();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_lesson_consum;
    }

    @Override
    public void itemClickListener() {
//        finish();
        ApiFactory.findcoach(userUid,memberCoursePkcode).subscribe(new BaseProgressSubscriber<ApiResponse<CoachBean>>(PrivateEducationConsumActivity.this) {
            @Override
            public void onNext(ApiResponse<CoachBean> coachBeanApiResponse) {
                super.onNext(coachBeanApiResponse);
                ApiFactory.consumePrivate(userUid,memberCoursePkcode,coachBeanApiResponse.getData().getCoachid()).subscribe(new BaseProgressSubscriber<ApiResponse>(PrivateEducationConsumActivity.this) {
                    @Override
                    public void onNext(ApiResponse apiResponse) {
                        super.onNext(apiResponse);
                    }
                });
            }
        });
   }



    @OnClick({R.id.bind_venue_intro, R.id.confirm_consume, R.id.confirm_consume_finish})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.bind_venue_intro:
                publicTitle.nextPosition();
                lessonConsumOne.setVisibility(View.GONE);
                lessonConsumTwo.setVisibility(View.VISIBLE);
                break;
            case R.id.confirm_consume:
                publicTitle.nextPosition();
                lessonConsumTwo.setVisibility(View.GONE);
                lessonConsumThree.setVisibility(View.VISIBLE);
                break;
            case R.id.confirm_consume_finish:
                publicTitle.nextPosition();
                lessonConsumOne.setVisibility(View.VISIBLE);
                lessonConsumThree.setVisibility(View.GONE);
                bindWay.setText("教练确认");
                ApiFactory.findcoach(userUid,memberCoursePkcode).subscribe(new BaseProgressSubscriber<ApiResponse>(PrivateEducationConsumActivity.this) {
                    @Override
                    public void onNext(ApiResponse apiResponse) {
                        super.onNext(apiResponse);

                    }
                });
                break;
        }
    }
//
//    private void setData() {
//        SwipeCardLayoutManager swmanamger = new SwipeCardLayoutManager(this);
//        multi_card.setLayoutManager(swmanamger);
//        Collections.reverse(mList);
//        LessonConsumeAdapter mAdatper = new LessonConsumeAdapter(mList, this);
//        multi_card.setAdapter(mAdatper);
//        CardConfig.initConfig(this);
//        ItemTouchHelper.Callback callback = new SwipeCardCallBack(mList, mAdatper, multi_card);
//        ItemTouchHelper helper = new ItemTouchHelper(callback);
//        helper.attachToRecyclerView(multi_card);
//
//    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }
}

