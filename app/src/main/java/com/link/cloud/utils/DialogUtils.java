package com.link.cloud.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.link.cloud.R;
import com.link.cloud.activity.DemoActivity;
import com.link.cloud.adapter.Lesson_Advantager;
import com.link.cloud.base.BaseActivity;
import com.link.cloud.listener.DialogCancelListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by OFX002 on 2018/9/21.
 */

public class DialogUtils implements View.OnClickListener {

    private TextView tv_desc_short;
    private TextView tv_desc_long;
    private TextView inputTel;
    private ImageView ivMoreLine;
    private ImageView btn_delete;
    private ImageView advantage_lesson_detail_more;
    private ListView advantage_lesson_detail;
    private List<String> list;
    private Lesson_Advantager adapter;
    private AlertDialog IntroCoachDialog;
    private DialogCancelListener listener;
    private AlertDialog dialogVeune;
    private AlertDialog dialogPsd;
    private ImageView close;
    StringBuilder builder = new StringBuilder();

    public void showManagerDialog(View view, Activity context, DialogCancelListener listener) {
        this.listener = listener;
        this.context = context;
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        dialogVeune = builder.create();
        dialogVeune.setCancelable(false);
        dialogVeune.setCanceledOnTouchOutside(false);
        dialogVeune.show();
        Window window = dialogVeune.getWindow();
        window.setBackgroundDrawableResource(android.R.color.transparent);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(770, 500);
        TextView cancel = view.findViewById(R.id.cancel);
        TextView psw_login = view.findViewById(R.id.psw_login);
        close = view.findViewById(R.id.close);
        cancel.setOnClickListener(this);
        psw_login.setOnClickListener(this);
        close.setOnClickListener(this);
        params.leftMargin = 133;
        window.setContentView(view, params);
    }

    public void showPsdDialog(View view, Activity context, DialogCancelListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        dialogPsd = builder.create();
        dialogPsd.setCancelable(false);
        dialogPsd.setCanceledOnTouchOutside(false);
        dialogPsd.show();
        Window window = dialogPsd.getWindow();
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
        close = view.findViewById(R.id.close);
        close.setOnClickListener(this);
        venue_login.setOnClickListener(this);
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
        params.leftMargin = 133;
        window.setContentView(view, params);
    }

    boolean isInit = false;
    boolean isShowShortText = false;
    Activity context;

    public void showIntroCoachDialog(View view, final Activity context) {
        this.context = context;
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        FrameLayout fl_desc = (FrameLayout) view.findViewById(R.id.fl_desc);
        tv_desc_short = (TextView) view.findViewById(R.id.tv_desc_short);
        tv_desc_long = (TextView) view.findViewById(R.id.tv_desc_long);
        ivMoreLine = (ImageView) view.findViewById(R.id.iv_more_line);
        btn_delete = (ImageView) view.findViewById(R.id.btn_delete);
        advantage_lesson_detail_more = (ImageView) view.findViewById(R.id.advantage_lesson_detail_more);
        advantage_lesson_detail = (ListView) view.findViewById(R.id.advantage_lesson_detail);
        list = new ArrayList();
        for (int x = 0; x < 3; x++) {
            list.add("111");
        }
        adapter = new Lesson_Advantager(context, list);
        setListViewHeight(advantage_lesson_detail, adapter, context);
        advantage_lesson_detail.setAdapter(adapter);
        isInit = false;
        isShowShortText = true;
        advantage_lesson_detail_more.setOnClickListener(this);
        ivMoreLine.setOnClickListener(this);
        tv_desc_short.setText(context.getResources().getString(R.string.message));
        tv_desc_long.setText(context.getResources().getString(R.string.message));
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
        IntroCoachDialog = builder.create();
        IntroCoachDialog.setCancelable(false);
        IntroCoachDialog.setCanceledOnTouchOutside(false);
        IntroCoachDialog.show();
        Window window = IntroCoachDialog.getWindow();
        window.setBackgroundDrawableResource(android.R.color.transparent);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(850, 1750);
        params.leftMargin = 80;
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

    private void setListViewHeight(ListView user_reviews_listview, BaseAdapter mListviewAdapter, Activity activity) {
        int totalHeight = 0;
        for (int i = 0, len = mListviewAdapter.getCount(); i < len; i++) {
            View listItem = mListviewAdapter.getView(i, null, user_reviews_listview);
            TextView tv = (TextView) listItem.findViewById(R.id.message);
            tv.setText(activity.getResources().getString(R.string.message));
            int textWidth = (int) Math.ceil(tv.getPaint().measureText(tv.getText().toString()));
            int tvHeight = tv.getLineHeight() - 6;
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
            case R.id.advantage_lesson_detail_more:
                for (int x = 0; x < 3; x++) {
                    list.add("111");
                }
                adapter.setData(list);
                setListViewHeight(advantage_lesson_detail, adapter, context);
                break;
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
                IntroCoachDialog.dismiss();
                break;
            case R.id.cancel:
                listener.dialogCancel();
                dialogVeune.dismiss();
                break;
            case R.id.close:
                listener.dialogCancel();
                if (dialogVeune.isShowing()) {
                    dialogVeune.dismiss();
                } else {
                    if (dialogPsd != null) {
                        dialogPsd.dismiss();
                    }
                }

                break;
            case R.id.psw_login:
                dialogVeune.dismiss();
                View psw_dialog = View.inflate(context, R.layout.psw_dialog, null);
                showPsdDialog(psw_dialog, context, listener);
                break;
            case R.id.venue_login:
                dialogPsd.dismiss();
                dialogVeune.show();
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
                builder.append(((TextView) view).getText());
                inputTel.setText(builder.toString());
                break;
            case R.id.bind_keypad_ok:
                builder.delete(0, builder.length());
                inputTel.setText( context.getResources().getString(R.string.manager_pwd));
                break;
            case R.id.bind_keypad_delect:
                if(builder.length()>=1){
                    builder.deleteCharAt(builder.length() - 1);
                    inputTel.setText(builder.toString());
                }else {
                    inputTel.setText(context.getResources().getString(R.string.manager_pwd));
                }

                break;
            case R.id.confirm:
                dialogPsd.dismiss();
                ((BaseActivity) context).showActivity(DemoActivity.class);
                listener.dialogCancel();
                break;
        }
    }
}
