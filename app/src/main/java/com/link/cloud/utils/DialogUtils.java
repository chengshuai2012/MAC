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
import com.link.cloud.adapter.Lesson_Advantager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by OFX002 on 2018/9/21.
 */

public class DialogUtils {
    public static void showManagerDialog(View view, Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(true);
        alertDialog.setCanceledOnTouchOutside(true);
        alertDialog.show();
        Window window = alertDialog.getWindow();
        window.setBackgroundDrawableResource(android.R.color.transparent);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(770, 500);
        params.leftMargin = 133;
        window.setContentView(view, params);
    }

    static boolean isInit = false;
    static boolean isShowShortText = false;

    public static void showIntroCoachDialog(View view, final Activity context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        FrameLayout fl_desc = (FrameLayout) view.findViewById(R.id.fl_desc);
        final TextView tv_desc_short = (TextView) view.findViewById(R.id.tv_desc_short);
        final TextView tv_desc_long = (TextView) view.findViewById(R.id.tv_desc_long);
        final ImageView ivMoreLine = (ImageView) view.findViewById(R.id.iv_more_line);
        final ImageView advantage_lesson_detail_more = (ImageView) view.findViewById(R.id.advantage_lesson_detail_more);
        final ListView advantage_lesson_detail = (ListView) view.findViewById(R.id.advantage_lesson_detail);
        final List<String> list = new ArrayList();
        for (int x = 0; x < 3; x++) {
            list.add("111");
        }
        final Lesson_Advantager adapter = new Lesson_Advantager(context, list);
        setListViewHeight(advantage_lesson_detail, adapter, context);
        advantage_lesson_detail.setAdapter(adapter);
        isInit = false;
        isShowShortText = true;
        advantage_lesson_detail_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int x = 0; x < 3; x++) {
                    list.add("111");
                }
                adapter.setData(list);
                setListViewHeight(advantage_lesson_detail, adapter, context);
            }
        });
        ivMoreLine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isShowShortText) {
                    tv_desc_short.setVisibility(View.GONE);
                    tv_desc_long.setVisibility(View.VISIBLE);
                } else {
                    tv_desc_short.setVisibility(View.VISIBLE);
                    tv_desc_long.setVisibility(View.GONE);
                }
                isShowShortText = !isShowShortText;
            }
        });
        String content = "新浪科技讯 北京时间7月25日凌晨消息，在今天举行的新产品发布会上，谷歌发布Android 4.3版本，代号仍为\"果冻豆(Jelly Bean)\"。今天发布的新一代Nexus 7将搭载该操作系统，Nexus系列设备今日可收到OTA推送更新。\r\nAndroid 4.3操作系统新增一系列功能。首先是多用户设置功能，包括针对保护儿童的“受限文件(restricted profiles)”特性。用户可以对应用内容进行限制，防止儿童在使用应用时看到不适宜内容，或接触不合适的应用内购买广告。这项功能与微软Windows Phone的\"儿童乐园(Microsoft's Kid's Corner)\"功能类似。\r\n第二项升级是智能蓝牙(Bluetooth Smart)功能，即\"低功耗蓝牙(Bluetooth Low Energy)\"。";
        tv_desc_short.setText(content);
        tv_desc_long.setText(content);
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
        AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(true);
        alertDialog.setCanceledOnTouchOutside(true);
        alertDialog.show();
        Window window = alertDialog.getWindow();
        window.setBackgroundDrawableResource(android.R.color.transparent);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(850, 1750);
        params.leftMargin = 80;
        window.setContentView(view, params);
    }

    /**
     * 计算描述信息是否过长
     */
    private static boolean mesureDescription(TextView shortView, TextView longView) {
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

    private static void setListViewHeight(ListView user_reviews_listview, BaseAdapter mListviewAdapter,Activity activity) {
        int totalHeight = 0;
        for (int i = 0, len = mListviewAdapter.getCount(); i < len; i++) { // listAdapter.getCount()返回数据项的数目
            View listItem = mListviewAdapter.getView(i, null, user_reviews_listview);
            TextView tv = (TextView) listItem.findViewById(R.id.message);
            tv.setText(activity.getResources().getString(R.string.message));
            int textWidth = (int) Math.ceil(tv.getPaint().measureText(tv.getText().toString()));
            int tvHeight = tv.getLineHeight()-6;
            int width = 750; // 屏幕宽度（像素）
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
        params.height = totalHeight + (user_reviews_listview.getDividerHeight() * (mListviewAdapter.getCount()-1));
        user_reviews_listview.setLayoutParams(params);
    }
}
