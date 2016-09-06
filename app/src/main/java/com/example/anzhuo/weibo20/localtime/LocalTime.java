package com.example.anzhuo.weibo20.localtime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by anzhuo on 2016/8/29.
 */
public class LocalTime {
    public static String getdata(String data) throws ParseException {
        String time = null;
        Date date=new Date(data);
        Calendar calendar=Calendar.getInstance();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd  HH-mm-ss");
        String st =simpleDateFormat.format(date);
        long t= calendar.getTime().getTime()-date.getTime();
        if (t<1000*60*60){
            time=String.valueOf((int)t/6000)+"分钟前";
        }else if(t<(1000*60*60*24)&&t>=1000*60*60){
            time=String.valueOf((int)t/3600000)+"小时前";
        }else if(t<1000*60*60*24*15&&t>=1000*60*60*24){
            time=String.valueOf((int)(t/3600000/24))+"天前";
        }else {
            time=st;
        }

        return time;
    }

}
