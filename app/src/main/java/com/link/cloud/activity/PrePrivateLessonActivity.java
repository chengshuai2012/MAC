package com.link.cloud.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.link.cloud.R;
import com.link.cloud.adapter.LessonDayTimeAdapter;
import com.link.cloud.adapter.LessonHourTimeAdapter;
import com.link.cloud.base.AppBarActivity;
import com.link.cloud.bean.Days;
import com.link.cloud.utils.LunarCalendar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 49488 on 2018/10/6.
 */

public class PrePrivateLessonActivity extends AppBarActivity implements LessonDayTimeAdapter.OnItemClickListener, LessonHourTimeAdapter.OnItemClickListener {
    ArrayList<Days> days = new ArrayList<>();
    String[] week = new String[]{"六", "日", "一", "二", "三", "四", "五"};
    private static String[] lunarDay = {"初一", "初二", "初三", "初四", "初五", "初六", "初七", "初八", "初九", "初十",
            "十一", "十二", "十三", "十四", "十五", "十六", "十七", "十八", "十九", "二十",
            "廿一", "廿二", "廿三", "廿四", "廿五", "廿六", "廿七", "廿八", "廿九", "三十"};
    private static String[] lunarMonth = {"一", "二", "三", "四", "五", "六", "七", "八", "九", "十",
            "一", "十二"};
    int maxDay;
    @BindView(R.id.lesson_pre_day)
    RecyclerView lessonPreDay;
    @BindView(R.id.lesson_pre_hour)
    RecyclerView lessonPreHour;
    @BindView(R.id.day)
    TextView dayTv;
    ArrayList<String> hours = new ArrayList<>();
    private LessonHourTimeAdapter hourTimeAdapter;
    private int hour;

    @Override
    protected void initViews() {
        setTitle(R.drawable.handy_logo);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        lessonPreDay.setLayoutManager(layoutManager);
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int dayweek = calendar.get(Calendar.DAY_OF_WEEK);
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        if (month == 2) {
            if (year % 4 == 0) {
                maxDay = 29;
            } else {
                maxDay = 28;
            }
        } else if (month == 4 || month == 6 || month == 9 || month == 11) {
            maxDay = 30;
        } else {
            maxDay = 31;
        }
        if (day + 10 > maxDay) {
            for (int x = day; x <= maxDay; x++) {
                Days dayss = new Days();
                String lunar = lunarDay[LunarCalendar.solarToLunar(year, month, x)[2] - 1];
                dayss.setDay(x + "");
                dayss.setLauar(lunar);
                dayss.setWeek(week[dayweek % 7]);
                dayss.setYear(year +"");
                dayss.setMonth(month+"");
                dayss.setLauarMonth( lunarMonth[LunarCalendar.solarToLunar(year, month, x)[1] -1]+"");
                dayweek++;
                days.add(dayss);

                if (x == maxDay) {
                    month++;
                    for (int y = 1; y < (10 - (maxDay - day)); y++) {
                        if (month > 12) {
                            month = 1;
                            year++;
                            dayss = new Days();
                            String lunars = lunarDay[LunarCalendar.solarToLunar(year, month, y)[2] - 1];
                            dayss.setDay(y + "");
                            dayss.setYear(year +"");
                            dayss.setMonth(month+"");
                            dayss.setLauarMonth( lunarMonth[LunarCalendar.solarToLunar(year, month, x)[1]-1 ]+"");
                            dayss.setLauar(lunars);
                            dayss.setWeek(week[dayweek % 7]);
                            dayweek++;
                            days.add(dayss);
                        } else {
                            dayss = new Days();
                            dayss.setYear(year +"");
                            dayss.setMonth(month+"");
                            dayss.setLauarMonth( lunarMonth[LunarCalendar.solarToLunar(year, month, x)[1]-1 ]+"");
                            String lunars = lunarDay[LunarCalendar.solarToLunar(year, month, y)[2] - 1];;
                            dayss.setDay(y + "");
                            dayss.setLauar(lunars);
                            dayss.setWeek(week[dayweek % 7]);
                            dayweek++;
                            days.add(dayss);
                        }
                    }
                }
            }
        } else {
            for (int x = 0; x < 10; x++) {
                Log.e("initViews: ", dayweek + "<<<" + "day");
                Days dayss = new Days();
                String lunar = lunarDay[LunarCalendar.solarToLunar(year, month, day)[2] - 1];
                dayss.setDay(day + "");
                dayss.setYear(year +"");
                dayss.setMonth(month+"");
                dayss.setLauarMonth( lunarMonth[LunarCalendar.solarToLunar(year, month, x)[1]-1]+"");
                dayss.setLauar(lunar);
                dayss.setWeek(week[dayweek % 7]);
                dayweek++;
                day++;
                days.add(dayss);
            }
        }
        for (int i = hour; i <=24; i++) {
           if(i>12){
               hours.add("下午"+(i-12)+"时");
           }else {
               hours.add("上午"+i+"时");
           }
        }

        LessonDayTimeAdapter listAdapter = new LessonDayTimeAdapter(days);
        listAdapter.setOnItemClickListener(this);
        lessonPreDay.setAdapter(listAdapter);
        dayTv.setText(days.get(0).toString()+"周"+days.get(0).getWeek()+days.get(0).Lauar());
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(this);
        lessonPreHour.setLayoutManager(layoutManager2);
        hourTimeAdapter = new LessonHourTimeAdapter(hours);
        hourTimeAdapter.setOnItemClickListener(this);
        lessonPreHour.setAdapter(hourTimeAdapter);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_pre_lesson_time;
    }


    int dayPosition = 0;
    @Override
    public void onItemClick(View view, int postion) {
        dayTv.setText(days.get(postion).toString()+"周"+days.get(postion).getWeek()+days.get(postion).Lauar());
        dayPosition = postion;
        hours.clear();
        if(postion!=0){
            for (int i = 1; i <=24; i++) {
                if(i>12){
                    hours.add("下午"+(i-12)+"时");
                }else {
                    hours.add("上午"+i+"时");
                }
            }
        }else {
            for (int i = hour; i <=24; i++) {
                if(i>12){
                    hours.add("下午"+(i-12)+"时");
                }else {
                    hours.add("上午"+i+"时");
                }
            }
        }
        hourTimeAdapter.notifyDataSetChanged();
    }

    @OnClick({R.id.previous,R.id.confirm_pre})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.previous:
                finish();
                break;

                case R.id.confirm_pre:
                    Log.e("onLessonHourItemClick: ",days.get(dayPosition)+">>>"+hours.get(HourPosition)+MinPosition );

                break;
        }
    }



    int HourPosition= 0;
int MinPosition= 0;
    @Override
    public void onLessonHourItemClick(int i, int postion) {
        HourPosition = postion;
        MinPosition=i;
        Log.e("onLessonHourItemClick: ",days.get(dayPosition)+">>>"+hours.get(postion)+i );
    }
}
