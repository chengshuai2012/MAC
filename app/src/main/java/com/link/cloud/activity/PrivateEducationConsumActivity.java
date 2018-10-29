package com.link.cloud.activity;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
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
import com.link.cloud.bean.AllUser;
import com.link.cloud.widget.PublicTitleView;
import com.zitech.framework.data.network.response.ApiResponse;
import java.util.ArrayList;
import java.util.List;
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
    @BindView(R.id.lesson_consum_now_ok)
    RelativeLayout lessonConsumOK;
    @BindView(R.id.user_type)
    TextView userType;
    @BindView(R.id.name_multi)
    TextView nameMulti;
    @BindView(R.id.tel_multi)
    TextView telMulti;
    @BindView(R.id.card_info_re)
    RelativeLayout cardInfoRe;
    private PublicTitleView publicTitle;
    private ArrayList<LessonPred.NotbookBean> mList = new ArrayList();
    ValueAnimator animator;
    RealmResults<AllUser> peoples;
    ArrayList<AllUser> nowPeople = new ArrayList<>();
    private LessonLeftAdapter listAdapter;
    private String memberCoursePkcode;
    private String userUid,coachUid,uid,ResponseCoachid;

    @Override
    protected void initViews() {
        setTitle(R.drawable.handy_logo);
        publicTitle = (PublicTitleView) findViewById(R.id.publicTitle);
        publicTitle.setTags("1."+getResources().getString(R.string.member_confirm), "2."+getResources().getString(R.string.choose_lesson), "3."+getResources().getString(R.string.coach_confirm), "4."+getResources().getString(R.string.consume_lesson_ok));
        publicTitle.setTitleText(getString(R.string.lesson_consum));
        publicTitle.setFinshText(getResources().getString(R.string.back_home));
        publicTitle.setItemClickListener(this);
        customProgress.setProgressFormatter(null);
        mList = new ArrayList<>();
        final RealmResults<AllUser> peoples = realm.where(AllUser.class).findAll();
        peoples.addChangeListener(new RealmChangeListener<RealmResults<AllUser>>() {
            @Override
            public void onChange(RealmResults<AllUser> people) {
                nowPeople.clear();
                nowPeople.addAll(realm.copyFromRealm(people));
            }
        });
        bindWay.setText(R.string.member_confirm);
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
                if (customProgress != null) {
                    customProgress.setProgress(progress);
                }
                if (state == 3) {
                    uid = MacApplication.getVenueUtils().identifyNewImg(nowPeople);
                    AllUser first = realm.where(AllUser.class).equalTo("uuid", uid).findFirst();
                    int userType=0;
                    Log.e("onAnimationUpdate: ",uid +"");
                    if(first!=null){
                        userType = first.getUserType();
                    }
                    if(userType==1){
                        userUid=uid;
                        ApiFactory.getPersonalClass(userUid).subscribe(new BaseProgressSubscriber<ApiResponse<LessonPred>>(PrivateEducationConsumActivity.this) {
                            @Override
                            public void onNext(ApiResponse<LessonPred> lessonPredApiResponse) {
                                super.onNext(lessonPredApiResponse);
                                userMessage(lessonPredApiResponse);
                            }

                            @Override
                            public void onError(Throwable e) {
                                super.onError(e);
                            }
                        });
                    }else {
                        if(ResponseCoachid!=null) {
                            if (ResponseCoachid.equals(uid) ) {
                                coachUid = uid;
                                bindWay.setText(R.string.choose_lesson);
                                lessonConsumTwo.setVisibility(View.VISIBLE);
                                lessonConsumOne.setVisibility(View.GONE);
                                lessonConsumThree.setVisibility(View.GONE);
                            }
                        }
                    }

                }
                if (progress >= 79) {
                    animator.setCurrentPlayTime(0);
                }
            }
        });
        animator.setDuration(40000);
        animator.start();
    }

    private void userMessage(ApiResponse<LessonPred> lessonPredApiResponse) {
        List<LessonPred.BookBean> book = lessonPredApiResponse.getData().getBook();
        bindWay.setText(getResources().getString(R.string.member_confirm));
        publicTitle.firstPosition();
        if(book.size()>0){
            memberCoursePkcode = book.get(0).getMemberCoursePkcode();
            lessonName.setText(book.get(0).getFitnessCourseName() + "");
            lessonTime.setText(book.get(0).getBegtime() + "");
            coachName.setText(book.get(0).getCoachNikename() + "");
        }
        telMulti.setText(lessonPredApiResponse.getData().getUser().getPhone());
        nameMulti.setText(lessonPredApiResponse.getData().getUser().getName());
        userType.setText(getResources().getString(R.string.member));
        mList.clear();
        mList.addAll(lessonPredApiResponse.getData().getNotbook());
        lessonConsumOne.setVisibility(View.GONE);
        lessonConsumThree.setVisibility(View.GONE);
        lessonConsumTwo.setVisibility(View.VISIBLE);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_lesson_consum;
    }

    @Override
    public void itemClickListener() {
        finish();

    }


    @OnClick({R.id.bind_venue_intro, R.id.confirm_consume, R.id.confirm_consume_finish})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.bind_venue_intro:
//                publicTitle.nextPosition();
//                lessonConsumOne.setVisibility(View.GONE);
//                lessonConsumTwo.setVisibility(View.VISIBLE);
                break;
            case R.id.confirm_consume:
                if(!TextUtils.isEmpty(userUid)){
                    if(TextUtils.isEmpty(coachUid)){
                        publicTitle.nextPosition();
                        bindWay.setText(R.string.choose_lesson);
                        lessonConsumTwo.setVisibility(View.GONE);
                        lessonConsumOne.setVisibility(View.GONE);
                        lessonConsumThree.setVisibility(View.VISIBLE);
                    }else {
                        ApiFactory.consumePrivate(userUid, memberCoursePkcode, ResponseCoachid).subscribe(new BaseProgressSubscriber<ApiResponse>(PrivateEducationConsumActivity.this) {
                            @Override
                            public void onNext(ApiResponse apiResponse) {
                                super.onNext(apiResponse);
                                lessonConsumNowRl.setVisibility(View.GONE);
                                consumeLessonToday.setVisibility(View.GONE);
                                lessonConsumOK.setVisibility(View.VISIBLE);
                                lessonConsumThree.setVisibility(View.VISIBLE);
                                lessonConsumOne.setVisibility(View.GONE);
                                lessonConsumTwo.setVisibility(View.GONE);
                                confirmConsumeFinish.setVisibility(View.GONE);
                                bindWay.setText(getResources().getString(R.string.consume_lesson_ok));
                                publicTitle.nextPosition();
                            }
                        });
                    }

                }


                break;
            case R.id.confirm_consume_finish:
                publicTitle.nextPosition();
                lessonConsumOne.setVisibility(View.VISIBLE);
                lessonConsumThree.setVisibility(View.GONE);
                lessonConsumTwo.setVisibility(View.GONE);
                bindWay.setText(R.string.coach_confirm);
                ApiFactory.findcoach(userUid, memberCoursePkcode).subscribe(new BaseProgressSubscriber<ApiResponse<CoachBean>>(PrivateEducationConsumActivity.this) {
                    @Override
                    public void onNext(ApiResponse<CoachBean> coachBeanApiResponse) {
                        super.onNext(coachBeanApiResponse);
                        ResponseCoachid =coachBeanApiResponse.getData().getUuid();
                        Log.e("onNext: ", ResponseCoachid);
                        lessonConsumOne.setVisibility(View.VISIBLE);
                        lessonConsumTwo.setVisibility(View.GONE);
                        lessonConsumThree.setVisibility(View.GONE);
                        telMulti.setText(coachBeanApiResponse.getData().getPhone());
                        nameMulti.setText(coachBeanApiResponse.getData().getName());
                        userType.setText(getResources().getString(R.string.coach));
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
        animator.end();
    }



}

