package com.link.cloud.activity;

import android.util.Log;

import com.link.cloud.R;
import com.link.cloud.base.AppBarActivity;
import com.link.cloud.bean.Days;
import com.link.cloud.utils.LunarCalendar;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by 49488 on 2018/10/6.
 */

public class PrePrivateLessonActivity extends AppBarActivity{
    ArrayList<Days> days =new ArrayList<>();
    String [] week = new String []{"六","日","一","二","三","四","五"};
    private static String[] lunarDay = {"初一", "初二", "初三", "初四", "初五", "初六", "初七", "初八", "初九", "初十",
            "十一", "十二", "十三", "十四", "十五", "十六", "十七", "十八", "十九", "二十",
            "廿一", "廿二", "廿三", "廿四", "廿五", "廿六", "廿七", "廿八", "廿九", "三十"};
    int maxDay;
    @Override
    protected void initViews() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month =  calendar.get(Calendar.MONTH)+1;
        int day =  calendar.get(Calendar.DAY_OF_MONTH);
        int dayweek = calendar.get(Calendar.DAY_OF_WEEK);
        if(month==2){
            if(year%4==0){
                maxDay=29;
            }else {
                maxDay=28;
            }
        }else if(month==4||month==6||month==9||month==11){
            maxDay=30;
        }else {
            maxDay=31;
        }
        if(day+10>maxDay){
            for(int x = day;x<=maxDay;x++){
                Days dayss = new Days();
                String lunar = lunarDay[LunarCalendar. solarToLunar(year , month, x)[2]-1];;
                dayss.setDay(x+"");
                dayss.setLauar(lunar);
                dayss.setWeek(week[dayweek%7]);
                dayweek++;
                days.add(dayss);
                if(x==maxDay){
                    month++;
                    for(int y=1;y<(10-(maxDay-day));y++){
                        if(month>12){
                            month=1;
                            year++;
                            dayss = new Days();
                            String lunars= lunarDay[LunarCalendar. solarToLunar(year , month, y)[2]-1];
                            dayss.setDay(y+"");
                            dayss.setLauar(lunars);
                            dayss.setWeek(week[dayweek%7]);
                            dayweek++;
                            days.add(dayss);
                        }else {
                            dayss = new Days();
                            String lunars= lunarDay[LunarCalendar. solarToLunar(year , month, y)[2]-1];;
                            dayss.setDay(y+"");
                            dayss.setLauar(lunars);
                            dayss.setWeek(week[dayweek%7]);
                            dayweek++;
                            days.add(dayss);
                        }
                    }
                }
            }
        }else {
            for(int x =0;x<10;x++){
                Log.e("initViews: ",dayweek+"<<<"+"day" );
                Days dayss = new Days();
                String lunar = lunarDay[LunarCalendar. solarToLunar(year , month, day)[2]-1];
                dayss.setDay(day+"");
                dayss.setLauar(lunar);
                dayss.setWeek(week[dayweek%7]);
                dayweek++;
                day++;
                days.add(dayss);
            }
        }
        for(int i=0;i<days.size();i++){
            Log.e("initViews: ", days.get(i).toString());
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_demo;
    }
}
