package bing.Pan.sso.common.utils;

import bing.Pan.sso.common.enums.DateEnums;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @crea : Created by intelliJ IDEA 16.1.3
 * @auth : bing.Pan
 * @mail : 15923508369@163.com
 * @date : 2017/7/25 11:04
 * @desc : 时间工具类，基于jdk版本小于1.8
 */
public class DateUtilsBaseOnLessThanJDK8 {

    static Calendar calendar = Calendar.getInstance();

    /**
     * 获取系统当前时间的字符串形式（默认返回格式：yyyy-MM-dd HH:mm:ss）
     * @param patterns      返回格式,可为空，为空时返回默认格式
     * @return
     */
    public static String getCurrentTime4String(String patterns){
        SimpleDateFormat format;
        if(StringUtils.isEmpty(patterns))  format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        else format = new SimpleDateFormat(patterns);

        return format.format(calendar.getTime());
    }


    /**
     * 获取当前日为周几，以周一为每周的第一天
     * @return
     */
    public static int getDayIsToday(){
        return  calendar.get(Calendar.DAY_OF_WEEK) - 1;

    }

    /**
     * 获取当前月的第一天日期字符串格式 (yyyy-MM-dd)
     * @return
     */
    public static String getCurrentMonthFastDay(){

        int mainDay=calendar.getActualMinimum(Calendar.DAY_OF_MONTH);
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), mainDay, 23, 59, 59);
        SimpleDateFormat sdf = new SimpleDateFormat(DateEnums.HYPHEN_YYYYMMdd.getPatterns());
        return sdf.format(calendar.getTime());
    }

    /**
     *  获取当前月的最后一天日期字符串格式 (yyyy-MM-dd)
     * @return
     */
    public static String getCurrentMonthLastDay(){
        int MaxDay=calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), MaxDay, 23, 59, 59);
        SimpleDateFormat sdf = new SimpleDateFormat(DateEnums.HYPHEN_YYYYMMdd.getPatterns());
        return sdf.format(calendar.getTime());

    }


    /**
     * 获取当前月的上月月份
     * @return
     */
    public static String getLastMonth(){
        calendar.add(Calendar.MONTH, -1);
        SimpleDateFormat beforeFormat =  new SimpleDateFormat(DateEnums.HYPHEN_YYYYMM.getPatterns());
        return beforeFormat.format(calendar.getTime());
    }



    public static String timeDifference(){
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date solvetime = sdf.parse("2017-07-26 17:00:00");

            long solveL = solvetime.getTime();
            long ms = solveL - new Date ().getTime ();
            int day = (int) ( ms / 1000 / 60 / 60 / 24 );
            int hour = (int) ( ms / 1000 / 60 / 60 % 24 );
            int minute = (int) ( ms / 1000 / 60 % 60 );
            int second = (int) ( ms / 1000 % 60 );

            System.out.println(day+"天"+hour+"小时"+ minute +"分钟"+ second +"秒");
        } catch (ParseException e) {
            e.printStackTrace();
        }
       return null;

    }



}
