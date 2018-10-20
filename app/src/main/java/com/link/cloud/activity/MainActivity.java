package com.link.cloud.activity;

import android.animation.ValueAnimator;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.link.cloud.MacApplication;
import com.link.cloud.R;
import com.link.cloud.User;
import com.link.cloud.api.ApiFactory;
import com.link.cloud.api.bean.LessonBean;
import com.link.cloud.api.bean.PrivateEduBean;
import com.link.cloud.api.request.GetUserPages;
import com.link.cloud.api.response.PageResponse;
import com.link.cloud.base.AppBarActivity;
import com.link.cloud.bean.People;
import com.link.cloud.fragment.Group_Lesson_Fragment;
import com.link.cloud.fragment.LessonChoose_Fragment;
import com.link.cloud.listener.DialogCancelListener;
import com.link.cloud.utils.DialogUtils;
import com.link.cloud.utils.NettyClientBootstrap;
import com.zitech.framework.data.network.response.ApiResponse;
import com.zitech.framework.data.network.subscribe.ProgressSubscriber;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import butterknife.BindView;
import butterknife.OnClick;
import io.realm.Realm;
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
    @BindView(R.id.fg_container)
    FrameLayout fgContainer;
    private LessonChoose_Fragment lessonChoose_fragment;
    private Group_Lesson_Fragment group_lesson_fragment;
    private DialogUtils dialogUtils;
    Realm realm;
    ValueAnimator animator;
    RealmResults<People> userBeans;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    protected void initViews() {
        hideToolbar();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        lessonChoose_fragment = new LessonChoose_Fragment();
        fragmentTransaction.replace(R.id.fg_container, lessonChoose_fragment);
        fragmentTransaction.commit();
        dialogUtils = DialogUtils.getDialogUtils(this, this);
        realm = Realm.getDefaultInstance();
        userBeans = realm.where(People.class).findAll();
        animator = ValueAnimator.ofInt(0, 100);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int state = MacApplication.getVenueUtils().getState();
                if (state == 3) {
                    String uid = MacApplication.getVenueUtils().identifyNewImg(userBeans);
                    group_lesson_fragment.onVeuenMsg(uid);
                }
            }
        });
        animator.setDuration(40000);
        ExecutorService service = Executors.newFixedThreadPool(1);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                nettyClientBootstrap = new NettyClientBootstrap(MainActivity.this,MainActivity. this,TCP_PORT, TCP_URL,"{\"data\":{},\"msgType\":\"HEART_BEAT\",\"token\":\""+User.get().getToken()+"\"}");
                nettyClientBootstrap.start();
                SendMsgToTcp("{\"data\":{},\"msgType\":\"HEART_BEAT\",\"token\":\""+User.get().getToken()+"\"}");
            }
        };
        service.execute(runnable);


    }
    protected void  SendMsgToTcp(String msg){
        nettyClientBootstrap.startNetty(msg);
    }
    @OnClick({R.id.member, R.id.manager, R.id.lesson_in, R.id.choose_lesson, R.id.buy, R.id.lesson_consume, R.id.register})
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.member:
                member.setBackground(getResources().getDrawable(R.drawable.border_red));
                manager.setBackground(null);
                member.setTextColor(getResources().getColor(R.color.almost_white));
                manager.setTextColor(getResources().getColor(R.color.text_gray));
                break;
            case R.id.manager:
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
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = manager.beginTransaction();
                if (group_lesson_fragment == null) {
                    group_lesson_fragment = new Group_Lesson_Fragment();
                }
                fragmentTransaction.replace(R.id.fg_container, group_lesson_fragment);
                fragmentTransaction.commit();
                break;
            case R.id.choose_lesson:
                animator.cancel();
                chooseLesson.setBackground(getResources().getDrawable(R.drawable.border_red_half_left));
                lessonIn.setBackground(null);
                lessonIn.setTextColor(getResources().getColor(R.color.red));
                chooseLesson.setTextColor(getResources().getColor(R.color.almost_white));
                FragmentManager manager1 = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction1 = manager1.beginTransaction();
                fragmentTransaction1.replace(R.id.fg_container, lessonChoose_fragment);
                fragmentTransaction1.commit();
                break;

            case R.id.buy:
                showActivity(PrivateEducationActivity.class);
                break;
            case R.id.register:
                showActivity(RegisterActivity.class);
                break;
            case R.id.lesson_consume:
                showActivity(PrePrivateLessonActivity.class);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }

    @Override
    public void dialogCancel() {
        member.setBackground(getResources().getDrawable(R.drawable.border_red));
        manager.setBackground(null);
        member.setTextColor(getResources().getColor(R.color.almost_white));
        manager.setTextColor(getResources().getColor(R.color.text_gray));
    }
}
