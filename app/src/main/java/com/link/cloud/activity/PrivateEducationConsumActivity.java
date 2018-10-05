package com.link.cloud.activity;

import android.annotation.SuppressLint;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dinuscxj.progressbar.CircleProgressBar;
import com.link.cloud.R;
import com.link.cloud.adapter.LessonConsumeAdapter;
import com.link.cloud.adapter.LessonLeftAdapter;
import com.link.cloud.base.AppBarActivity;
import com.link.cloud.widget.CardConfig;
import com.link.cloud.widget.PublicTitleView;
import com.link.cloud.widget.SwipeCardCallBack;
import com.link.cloud.widget.SwipeCardLayoutManager;

import java.util.ArrayList;
import java.util.Collections;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

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
    private ArrayList mList;

    @Override
    protected void initViews() {
        setTitle(R.drawable.handy_logo);
        publicTitle = (PublicTitleView) findViewById(R.id.publicTitle);
        publicTitle.setTags("1.教练确认", "2.学员确认", "3.选择课程", "4.消课成功");
        publicTitle.setTitleText(getString(R.string.lesson_consum));
        publicTitle.setFinshText(getResources().getString(R.string.back_home));
        publicTitle.setItemClickListener(this);
        customProgress.setProgressFormatter(null);
        mList = new ArrayList<>();
        initData();
        setData();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        lessonLeftRecyclerView.setLayoutManager(layoutManager);
        LessonLeftAdapter listAdapter = new LessonLeftAdapter(mList);
        lessonLeftRecyclerView.setAdapter(listAdapter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_lesson_consum;
    }

    @Override
    public void itemClickListener() {
        finish();
    }


    private void initData() {
        for (int i = 0; i < 8; i++) {
            mList.add("11111111111" + i);
        }
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
                finish();
                break;
        }
    }

    private void setData() {
        SwipeCardLayoutManager swmanamger = new SwipeCardLayoutManager(this);
        multi_card.setLayoutManager(swmanamger);
        Collections.reverse(mList);
        LessonConsumeAdapter mAdatper = new LessonConsumeAdapter(mList, this);
        multi_card.setAdapter(mAdatper);
        CardConfig.initConfig(this);
        ItemTouchHelper.Callback callback = new SwipeCardCallBack(mList, mAdatper, multi_card);
        ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(multi_card);

    }


}

