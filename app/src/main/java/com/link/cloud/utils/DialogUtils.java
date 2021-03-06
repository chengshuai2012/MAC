package com.link.cloud.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.link.cloud.R;
import com.link.cloud.activity.RegisterActivity;
import com.link.cloud.activity.SettingActivity;
import com.link.cloud.adapter.Coach_Advantager;
import com.link.cloud.adapter.Lesson_Advantager;
import com.link.cloud.adapter.Pay_GridView_Adapter_Black;
import com.link.cloud.adapter.Pay_GridView_Adapter_Gray;
import com.link.cloud.adapter.TipsAdapter;
import com.link.cloud.api.ApiFactory;
import com.link.cloud.api.BaseProgressSubscriber;
import com.link.cloud.api.dataSourse.CoachInfo;
import com.link.cloud.base.BaseActivity;
import com.link.cloud.listener.DialogCancelListener;
import com.zitech.framework.data.network.response.ApiResponse;
import com.zitech.framework.data.network.subscribe.ProgressSubscriber;

import java.util.ArrayList;
import java.util.List;

import cn.iwgang.countdownview.CountdownView;

/**
 * Created by OFX002 on 2018/9/21.
 */

public class DialogUtils implements View.OnClickListener {
    private Lesson_Advantager adapter;
    private DialogCancelListener listener;
    private AlertDialog dialog;
    StringBuilder builder = new StringBuilder();
    private Pay_GridView_Adapter_Black pay_gridView_adapter_black;
    boolean isPay;
    private TextView inputTel;
    private TextView tv_desc_short;
    private TextView tv_desc_long;
    private ImageView ivMoreLine;
    private ListView advantage_lesson_detail;

    private DialogUtils(Activity context, DialogCancelListener listener) {
        this.listener = listener;
        this.context = context;
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        dialog = builder.create();
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
    }

    private static DialogUtils dialogUtils;

    public static synchronized DialogUtils getDialogUtils(Activity context, DialogCancelListener listener) {
        if (dialogUtils == null) {
            dialogUtils = new DialogUtils(context, listener);
        } else {
            dialogUtils = null;
            System.gc();
            dialogUtils = new DialogUtils(context, listener);
        }
        return dialogUtils;
    }

    public void showManagerDialog(View view) {
        dialog.show();
        Window window = dialog.getWindow();
        window.setBackgroundDrawableResource(android.R.color.transparent);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(770, 500);
        TextView cancel = view.findViewById(R.id.cancel);
        TextView psw_login = view.findViewById(R.id.psw_login);
        ImageView close = view.findViewById(R.id.close);
        cancel.setOnClickListener(this);
        psw_login.setOnClickListener(this);
        close.setOnClickListener(this);
        dialog.setCancelable(false);
        params.leftMargin = 30;
        window.setContentView(view, params);
    }

    public void showPayOkDialog(View view) {
        dialog.show();
        Window window = dialog.getWindow();
        window.setBackgroundDrawableResource(android.R.color.transparent);
        TextView back_home = view.findViewById(R.id.back_home);
        ImageView close_pay = view.findViewById(R.id.close_pay);
        close_pay.setOnClickListener(this);
        back_home.setOnClickListener(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(770, 540);
        params.leftMargin = 30;
        window.setContentView(view, params);
    }

    public void showPswPayDialog(View view) {
        isPay = true;
        dialog.show();
        Window window = dialog.getWindow();
        window.setBackgroundDrawableResource(android.R.color.transparent);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(770, 1040);
        GridView gray = view.findViewById(R.id.gray);
        GridView black = view.findViewById(R.id.black);
        gray.setAdapter(new Pay_GridView_Adapter_Gray(context));
        pay_gridView_adapter_black = new Pay_GridView_Adapter_Black(context);
        black.setAdapter(pay_gridView_adapter_black);
        TextView bind_keypad_0 = view.findViewById(R.id.bind_keypad_0);
        TextView bind_keypad_1 = view.findViewById(R.id.bind_keypad_1);
        TextView bind_keypad_2 = view.findViewById(R.id.bind_keypad_2);
        TextView bind_keypad_3 = view.findViewById(R.id.bind_keypad_3);
        TextView bind_keypad_4 = view.findViewById(R.id.bind_keypad_4);
        TextView bind_keypad_5 = view.findViewById(R.id.bind_keypad_5);
        TextView bind_keypad_6 = view.findViewById(R.id.bind_keypad_6);
        TextView bind_keypad_7 = view.findViewById(R.id.bind_keypad_7);
        TextView bind_keypad_8 = view.findViewById(R.id.bind_keypad_8);
        TextView bind_keypad_9 = view.findViewById(R.id.bind_keypad_9);
        TextView bind_keypad_ok = view.findViewById(R.id.clear_keyboard);
        TextView bind_keypad_delect = view.findViewById(R.id.delete_keyboard);
        ImageView close = view.findViewById(R.id.close);
        close.setOnClickListener(this);
        bind_keypad_0.setOnClickListener(this);
        bind_keypad_1.setOnClickListener(this);
        bind_keypad_2.setOnClickListener(this);
        bind_keypad_3.setOnClickListener(this);
        bind_keypad_4.setOnClickListener(this);
        bind_keypad_5.setOnClickListener(this);
        bind_keypad_6.setOnClickListener(this);
        bind_keypad_7.setOnClickListener(this);
        bind_keypad_8.setOnClickListener(this);
        bind_keypad_9.setOnClickListener(this);
        bind_keypad_ok.setOnClickListener(this);
        bind_keypad_delect.setOnClickListener(this);
        params.leftMargin = 30;
        window.setContentView(view, params);
    }

    public void showVeunePayDialog(View view) {
        dialog.show();
        Window window = dialog.getWindow();
        window.setBackgroundDrawableResource(android.R.color.transparent);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(770, 460);
        ImageView close_pay_veune = view.findViewById(R.id.close_pay_veune);
        TextView cancel_buy_group_lesson = view.findViewById(R.id.cancel_buy_group_lesson);
        TextView psw_login = view.findViewById(R.id.psw_pay);
        ImageView close = view.findViewById(R.id.close);
        close_pay_veune.setOnClickListener(this);
        cancel_buy_group_lesson.setOnClickListener(this);
        psw_login.setOnClickListener(this);
        params.leftMargin = 30;
        window.setContentView(view, params);
    }

    public void showPreDialog(View view) {
        dialog.show();
        Window window = dialog.getWindow();
        window.setBackgroundDrawableResource(android.R.color.transparent);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(770, 373);
        TextView cancel = view.findViewById(R.id.cancel_pre);
        TextView psw_login = view.findViewById(R.id.cancel_pre_pay);
        cancel.setOnClickListener(this);
        psw_login.setOnClickListener(this);
        params.leftMargin = 30;
        window.setContentView(view, params);
    }

    public void showPayDialog(View view, Bitmap bitmap, String payment, String payString, long time) {
        dialog.show();
        ImageView close_pay = view.findViewById(R.id.close_pay);
        ImageView qrcode = view.findViewById(R.id.qrcode);
        TextView money = view.findViewById(R.id.money);
        TextView payTyte = view.findViewById(R.id.work_introduce);
        CountdownView countdownView = view.findViewById(R.id.countDownView);
        countdownView.setTag("test1");
        countdownView.start(time);
        payTyte.setText(payString);
        qrcode.setImageBitmap(bitmap);
        close_pay.setOnClickListener(this);
        money.setText(payment + "元");
        Window window = dialog.getWindow();
        window.setBackgroundDrawableResource(android.R.color.transparent);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(770, 569);
        params.leftMargin = 30;
        window.setContentView(view, params);
        countdownView.setOnCountdownEndListener(new CountdownView.OnCountdownEndListener() {
            @Override
            public void onEnd(CountdownView cv) {
                dissMiss();
            }
        });

    }

    public void showVeuneOkDialog(View view) {
        dialog.show();
        Window window = dialog.getWindow();
        window.setBackgroundDrawableResource(android.R.color.transparent);
        ImageView close_pay_veune = view.findViewById(R.id.close_pay);
        TextView back_home = view.findViewById(R.id.back_home);
        dialog.setCancelable(true);
        back_home.setOnClickListener(this);
        close_pay_veune.setOnClickListener(this);
        dialog.setCanceledOnTouchOutside(true);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(770, 569);
        params.leftMargin = 30;
        window.setContentView(view, params);
    }
    public void showVeuneInOkDialog(View view) {
        dialog.show();
        Window window = dialog.getWindow();
        window.setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(770, 569);
        params.leftMargin = 30;
        window.setContentView(view, params);
    }
    public void showVeuneFailDialog(View view, String msg) {
        dialog.show();
        Window window = dialog.getWindow();
        window.setBackgroundDrawableResource(android.R.color.transparent);
        TextView no_bind = view.findViewById(R.id.no_bind);
        TextView verify_you_have_bind = view.findViewById(R.id.verify_you_have_bind);
        verify_you_have_bind.setText(msg);
        no_bind.setOnClickListener(this);
        TextView has_bind = view.findViewById(R.id.has_bind);
        has_bind.setOnClickListener(this);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(770, 630);
        params.leftMargin = 30;
        window.setContentView(view, params);
    }

    public void showVeunePayFailDialog(View view, String msg) {
        dialog.show();
        Window window = dialog.getWindow();
        window.setBackgroundDrawableResource(android.R.color.transparent);
        TextView no_bind = view.findViewById(R.id.pay_no_bind);
        TextView verify_you_have_bind = view.findViewById(R.id.verify_you_have_bind);
        verify_you_have_bind.setText(msg);
        no_bind.setOnClickListener(this);
        TextView has_bind = view.findViewById(R.id.pay_has_bind);
        has_bind.setOnClickListener(this);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(770, 630);
        params.leftMargin = 30;
        window.setContentView(view, params);
    }

    public void showHanyPayDialog(View view, String money) {
        dialog.show();
        ImageView close_pay = view.findViewById(R.id.close_pay);
        TextView confirm_pay = view.findViewById(R.id.confirm_pay);
        TextView lesson_price = view.findViewById(R.id.lesson_price);
        close_pay.setOnClickListener(this);
        confirm_pay.setOnClickListener(this);
        Window window = dialog.getWindow();
        lesson_price.setText(money);
        window.setBackgroundDrawableResource(android.R.color.transparent);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(770, 420);
        params.leftMargin = 30;
        window.setContentView(view, params);
    }

    public void showPsdDialog(View view) {
        isPay = false;
        dialog.show();
        Window window = dialog.getWindow();
        window.setBackgroundDrawableResource(android.R.color.transparent);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(770, 869);
        inputTel = view.findViewById(R.id.input_tel);
        TextView bind_keypad_0 = view.findViewById(R.id.bind_keypad_0);
        TextView bind_keypad_1 = view.findViewById(R.id.bind_keypad_1);
        TextView bind_keypad_2 = view.findViewById(R.id.bind_keypad_2);
        TextView bind_keypad_3 = view.findViewById(R.id.bind_keypad_3);
        TextView bind_keypad_4 = view.findViewById(R.id.bind_keypad_4);
        TextView bind_keypad_5 = view.findViewById(R.id.bind_keypad_5);
        TextView bind_keypad_6 = view.findViewById(R.id.bind_keypad_6);
        TextView bind_keypad_7 = view.findViewById(R.id.bind_keypad_7);
        TextView bind_keypad_8 = view.findViewById(R.id.bind_keypad_8);
        TextView bind_keypad_9 = view.findViewById(R.id.bind_keypad_9);
        TextView bind_keypad_ok = view.findViewById(R.id.bind_keypad_ok);
        TextView bind_keypad_delect = view.findViewById(R.id.bind_keypad_delect);
        TextView confirm = view.findViewById(R.id.confirm);
        TextView venue_login = view.findViewById(R.id.venue_login);
        ImageView close = view.findViewById(R.id.close);
        close.setOnClickListener(this);
        venue_login.setOnClickListener(this);
        confirm.setOnClickListener(this);
        bind_keypad_0.setOnClickListener(this);
        bind_keypad_1.setOnClickListener(this);
        bind_keypad_2.setOnClickListener(this);
        bind_keypad_3.setOnClickListener(this);
        bind_keypad_4.setOnClickListener(this);
        bind_keypad_5.setOnClickListener(this);
        bind_keypad_6.setOnClickListener(this);
        bind_keypad_7.setOnClickListener(this);
        bind_keypad_8.setOnClickListener(this);
        bind_keypad_9.setOnClickListener(this);
        bind_keypad_ok.setOnClickListener(this);
        bind_keypad_delect.setOnClickListener(this);
        confirm.setOnClickListener(this);
        inputTel.setText(context.getResources().getString(R.string.manager_pwd));
        params.leftMargin = 30;
        window.setContentView(view, params);
    }

    boolean isInit = false;
    boolean isShowShortText = false;
    Activity context;
    ArrayList<String> list;

    public void showIntroCoachDialog(View view, CoachInfo coachInfo) {
        dialog.show();
        FrameLayout fl_desc = (FrameLayout) view.findViewById(R.id.fl_desc);
        tv_desc_short = (TextView) view.findViewById(R.id.tv_desc_short);
        ScrollView scrollview = (ScrollView) view.findViewById(R.id.scrollview);
        tv_desc_long = (TextView) view.findViewById(R.id.tv_desc_long);
        ivMoreLine = (ImageView) view.findViewById(R.id.iv_more_line);
        ImageView btn_delete = (ImageView) view.findViewById(R.id.btn_delete);
        ImageView dialog_big_image = (ImageView) view.findViewById(R.id.dialog_big_image);
        ImageView icon_coach = (ImageView) view.findViewById(R.id.coach_image);
       // ImageView advantage_lesson_detail_more = (ImageView) view.findViewById(R.id.advantage_lesson_detail_more);
        advantage_lesson_detail = (ListView) view.findViewById(R.id.advantage_lesson_detail);
        ListView coach_verify_detail = (ListView) view.findViewById(R.id.coach_verify_detail);
        Glide.with(context).load(coachInfo.getStoreCoachTopimg()).placeholder(R.drawable.coach_main_img).error(R.drawable.coach_main_img).into(dialog_big_image);
        Glide.with(context).load(coachInfo.getStoreCoachHeadimg()).placeholder(R.drawable.icon_coach).error(R.drawable.icon_coach).into(icon_coach);
        List<CoachInfo.StoreCoachIntroduceBean.GoodCourseBean> goodCourse = coachInfo.getStoreCoachIntroduce().getGoodCourse();
        adapter = new Lesson_Advantager(context, goodCourse);
        Coach_Advantager advantager = new Coach_Advantager(context,coachInfo.getStoreCoachIntroduce().getCertificateList());
        coach_verify_detail.setAdapter(advantager);
        setListViewHeightCoach(advantage_lesson_detail, adapter, context, coachInfo);
        setListViewHeightCoachADV(coach_verify_detail, advantager, context, coachInfo);
        advantage_lesson_detail.setAdapter(adapter);
        isInit = false;
        isShowShortText = true;
      //  advantage_lesson_detail_more.setOnClickListener(this);
        ivMoreLine.setOnClickListener(this);
        tv_desc_short.setText(coachInfo.getStoreCoachSynopsis());
        tv_desc_long.setText(coachInfo.getStoreCoachSynopsis());
        ViewTreeObserver vto = fl_desc.getViewTreeObserver();
        vto.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                if (isInit)
                    return true;
                if (mesureDescription(tv_desc_short, tv_desc_long)) {
                    ivMoreLine.setVisibility(View.VISIBLE);
                }
                isInit = true;
                return true;
            }
        });
        Window window = dialog.getWindow();
        window.setBackgroundDrawableResource(android.R.color.transparent);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(850, 1750);
        window.setContentView(view, params);
        btn_delete.setOnClickListener(this);
        scrollview.scrollTo(0,0);
    }

    public void showIntroLessonDialog(View view, CoachInfo coachInfo) {
        dialog.show();
        ScrollView scrollView = view.findViewById(R.id.scrollView);
        ImageView dialog_big_image = (ImageView) view.findViewById(R.id.dialog_big_image);
        ImageView btn_delete = (ImageView) view.findViewById(R.id.btn_delete);
        TextView private_coach_name = (TextView) view.findViewById(R.id.private_coach_name);
        TextView had_pay_num = (TextView) view.findViewById(R.id.had_pay_num);
        TextView lesson_address = (TextView) view.findViewById(R.id.lesson_address);
        TextView lesson_detial = (TextView) view.findViewById(R.id.lesson_detial);
        ListView lvtips = view.findViewById(R.id.tips_lv);
        TipsAdapter adapter = new TipsAdapter(context, coachInfo.getFitnessCourseContext().getReminder());
        setListViewHeight(lvtips, adapter, context, coachInfo);
        lvtips.setAdapter(adapter);
        lvtips.setFocusable(false);
        Glide.with(context).load(coachInfo.getFitnessCourseCoverimg()).placeholder(R.drawable.coach_main_img).error(R.drawable.coach_main_img).into(dialog_big_image);
        private_coach_name.setText(coachInfo.getCoachName());
        had_pay_num.setText(context.getResources().getString(R.string.have_mun) + coachInfo.getNum() + context.getResources().getString(R.string.peoples_buy));
        lesson_address.setText(coachInfo.getAddress());
        lesson_detial.setText(coachInfo.getFitnessCourseContext().getFitnessCourseintroduce());
        Window window = dialog.getWindow();
        window.setBackgroundDrawableResource(android.R.color.transparent);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(850, 1750);
        scrollView.scrollTo(0,0);
        window.setContentView(view, params);
        btn_delete.setOnClickListener(this);

    }

    private boolean mesureDescription(TextView shortView, TextView longView) {
        final int shortHeight = shortView.getHeight();
        final int longHeight = longView.getHeight();
        if (longHeight > shortHeight) {
            shortView.setVisibility(View.VISIBLE);
            longView.setVisibility(View.GONE);
            return true;
        }
        shortView.setVisibility(View.GONE);
        longView.setVisibility(View.VISIBLE);
        return false;
    }

    private void setListViewHeight(ListView user_reviews_listview, BaseAdapter mListviewAdapter, Activity activity, CoachInfo coachInfo) {
        int totalHeight = 0;
        for (int i = 0, len = mListviewAdapter.getCount(); i < len; i++) {
            View listItem = mListviewAdapter.getView(i, null, user_reviews_listview);
            TextView tv = listItem.findViewById(R.id.message);
            tv.setText(coachInfo.getFitnessCourseContext().getReminder().get(i).getText());
            int textWidth = (int) Math.ceil(tv.getPaint().measureText(tv.getText().toString()));
            int tvHeight = tv.getLineHeight();
            int width = 700;
            int newTVHeight;
            listItem.measure(0, 0);
            if (textWidth > width) {
                if (textWidth % width > 0) {
                    newTVHeight = tvHeight * ((textWidth / width) + 1);
                } else {
                    newTVHeight = tvHeight * ((textWidth / width));
                }
            } else {
                newTVHeight = tvHeight;
            }
            totalHeight += listItem.getMeasuredHeight() + newTVHeight - tvHeight;
        }
        ViewGroup.LayoutParams params = user_reviews_listview.getLayoutParams();
        params.height = totalHeight + (user_reviews_listview.getDividerHeight() * (mListviewAdapter.getCount() - 1));
        user_reviews_listview.setLayoutParams(params);
    }
    private void setListViewHeightCoach(ListView user_reviews_listview, BaseAdapter mListviewAdapter, Activity activity, CoachInfo coachInfo) {
        int totalHeight = 0;
        for (int i = 0, len = mListviewAdapter.getCount(); i < len; i++) {
            View listItem = mListviewAdapter.getView(i, null, user_reviews_listview);
            TextView tv = listItem.findViewById(R.id.message);
            tv.setText(coachInfo.getStoreCoachIntroduce().getGoodCourse().get(i).getGoods());
            int textWidth = (int) Math.ceil(tv.getPaint().measureText(tv.getText().toString()));
            int tvHeight = tv.getLineHeight();
            int width = 700;
            int newTVHeight;
            listItem.measure(0, 0);
            if (textWidth > width) {
                if (textWidth % width > 0) {
                    newTVHeight = tvHeight * ((textWidth / width) + 1);
                } else {
                    newTVHeight = tvHeight * ((textWidth / width));
                }
            } else {
                newTVHeight = tvHeight;
            }
            totalHeight += listItem.getMeasuredHeight() + newTVHeight - tvHeight;
        }
        ViewGroup.LayoutParams params = user_reviews_listview.getLayoutParams();
        params.height = totalHeight + (user_reviews_listview.getDividerHeight() * (mListviewAdapter.getCount() - 1));
        user_reviews_listview.setLayoutParams(params);
    }
    private void setListViewHeightCoachADV(ListView user_reviews_listview, BaseAdapter mListviewAdapter, Activity activity, CoachInfo coachInfo) {
        int totalHeight = 0;
        for (int i = 0, len = mListviewAdapter.getCount(); i < len; i++) {
            View listItem = mListviewAdapter.getView(i, null, user_reviews_listview);
            TextView tv = listItem.findViewById(R.id.message);
            tv.setText(coachInfo.getStoreCoachIntroduce().getCertificateList().get(i).getText());
            int textWidth = (int) Math.ceil(tv.getPaint().measureText(tv.getText().toString()));
            int tvHeight = tv.getLineHeight();
            int width = 700;
            int newTVHeight;
            listItem.measure(0, 0);
            if (textWidth > width) {
                if (textWidth % width > 0) {
                    newTVHeight = tvHeight * ((textWidth / width) + 1);
                } else {
                    newTVHeight = tvHeight * ((textWidth / width));
                }
            } else {
                newTVHeight = tvHeight;
            }
            totalHeight += listItem.getMeasuredHeight() + newTVHeight - tvHeight;
        }
        ViewGroup.LayoutParams params = user_reviews_listview.getLayoutParams();
        params.height = totalHeight + (user_reviews_listview.getDividerHeight() * (mListviewAdapter.getCount() - 1));
        user_reviews_listview.setLayoutParams(params);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
//            case R.id.advantage_lesson_detail_more:
//
//                setListViewHeight(advantage_lesson_detail, adapter, context, null);
//                break;
            case R.id.iv_more_line:
                if (isShowShortText) {
                    tv_desc_short.setVisibility(View.GONE);
                    tv_desc_long.setVisibility(View.VISIBLE);
                    ivMoreLine.setImageDrawable(context.getResources().getDrawable(R.drawable.up));
                } else {
                    tv_desc_short.setVisibility(View.VISIBLE);
                    tv_desc_long.setVisibility(View.GONE);
                    ivMoreLine.setImageDrawable(context.getResources().getDrawable(R.drawable.down));
                }
                isShowShortText = !isShowShortText;
                break;
            case R.id.btn_delete:

            case R.id.cancel:

            case R.id.close:
                listener.dialogCancel();
                dialog.dismiss();
                break;
            case R.id.close_pay:
            case R.id.close_pay_veune:
            case R.id.cancel_buy_group_lesson:
            case R.id.cancel_pre:
                dialog.dismiss();
                break;
            case R.id.psw_login:
                dialog.dismiss();
                builder.delete(0,builder.length());
                View psw_dialog = View.inflate(context, R.layout.psw_dialog, null);
                showPsdDialog(psw_dialog);
                break;
            case R.id.confirm_pay:
                dialog.dismiss();
                listener.onVenuePay();
                View pay_veune_dialog = View.inflate(context, R.layout.pay_veune_dialog, null);
                showVeunePayDialog(pay_veune_dialog);
                break;
            case R.id.venue_login:

                dialog.dismiss();
                View veune_dialog = View.inflate(context, R.layout.veune_dialog, null);
                showManagerDialog(veune_dialog);
                break;
            case R.id.cancel_pre_pay:
                listener.dialogCancel();
                break;
            case R.id.psw_pay:
                View psw_pay_dialog = View.inflate(context, R.layout.psw_pay_dialog, null);
                showPswPayDialog(psw_pay_dialog);
                break;
            case R.id.bind_keypad_0:
            case R.id.bind_keypad_1:
            case R.id.bind_keypad_2:
            case R.id.bind_keypad_3:
            case R.id.bind_keypad_4:
            case R.id.bind_keypad_5:
            case R.id.bind_keypad_6:
            case R.id.bind_keypad_7:
            case R.id.bind_keypad_8:
            case R.id.bind_keypad_9:
                if (isPay) {
                    if (builder.length() >= 6) {
                        return;
                    }
                }
                builder.append(((TextView) view).getText());

                if (inputTel != null) {
                    inputTel.setText(builder.toString());
                }
                if (pay_gridView_adapter_black != null) {
                    pay_gridView_adapter_black.setLength(builder.length());
                }

                break;
            case R.id.bind_keypad_ok:
                builder.delete(0, builder.length());
                inputTel.setText(context.getResources().getString(R.string.manager_pwd));
                break;
            case R.id.bind_keypad_delect:
                if (builder.length() >= 1) {
                    builder.deleteCharAt(builder.length() - 1);
                    inputTel.setText(builder.toString());
                } else {
                    inputTel.setText(context.getResources().getString(R.string.manager_pwd));
                }

                break;
            case R.id.clear_keyboard:
                builder.delete(0, builder.length());
                pay_gridView_adapter_black.setLength(builder.length());
                View pay_ok = View.inflate(context, R.layout.pay_ok_dialog, null);
                showPayOkDialog(pay_ok);
                break;
            case R.id.delete_keyboard:
                if (builder.length() >= 1) {
                    builder.deleteCharAt(builder.length() - 1);
                    pay_gridView_adapter_black.setLength(builder.length());
                } else {
                    pay_gridView_adapter_black.setLength(builder.length());
                }

                break;
            case R.id.confirm:

                String fisrt = Utils.getMD5( builder.toString()).toUpperCase();
                String second = Utils.getMD5(fisrt).toUpperCase();
                ApiFactory.validatePassword(second).subscribe(new BaseProgressSubscriber<ApiResponse>(context) {
                    @Override
                    public void onNext(ApiResponse apiResponse) {
                        super.onNext(apiResponse);
                        ((BaseActivity) context).showActivity(SettingActivity.class);
                        listener.dialogCancel();
                        dialog.dismiss();
                    }
                });

                break;
            case R.id.back_home:
                ((BaseActivity) context).finish();
                break;
            case R.id.no_bind:
            case R.id.pay_no_bind:
                dialog.dismiss();
                ((BaseActivity) context).showActivity(RegisterActivity.class);
                break;
            case R.id.pay_has_bind:
                dialog.dismiss();
                listener.onVenuePay();
                break;
            case R.id.has_bind:
                listener.dialogCancel();
                dialog.dismiss();
                break;
        }
    }

    public void dissMiss() {
        if (dialog.isShowing()) {
            dialog.dismiss();
        }
    }
    public boolean isShowing() {
      return dialog.isShowing();
    }
}
