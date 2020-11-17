package com.horse.sso.common.utils;

import com.horse.sso.common.enums.DateEnums;
import org.springframework.util.StringUtils;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @crea : Created by intelliJ IDEA 16.1.3
 * @auth : Gopan
 * @mail : 15923508369@163.com
 * @date : 2017/5/31 9:04
 * @desc : 时间工具类，基于jdk1.8
 */
public class DateUtilsBaseOnJDK8 {

    /**
     * 时间转字符串形式,缺省格式为：yyyy-MM-dd HH:mm:ss
     * @param date  java LocalDateTime
     * @param patterns 格式
     * @return
     */
    public static String date2String(LocalDateTime date,String patterns){

        if(StringUtils.isEmpty(date))
            return null;

        if(StringUtils.isEmpty(patterns)){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DateEnums.HYPHEN_YYYYMMddHHmmss.getPatterns());
            return date.format(formatter);
        } else{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(patterns);
            return date.format(formatter);
        }
    }


    /**
     * 获取当前时间的指定字符串格式,缺省格式为：yyyy-MM-dd HH:mm:ss
     * @param patterns
     * @return
     */
    public static String getCurrentDate(String patterns){
        LocalDateTime localDateTime = LocalDateTime.now();
        if(StringUtils.isEmpty(patterns))
            return localDateTime.format(DateTimeFormatter.ofPattern(DateEnums.HYPHEN_YYYYMMddHHmmss.getPatterns()));
        else
            return localDateTime.format(DateTimeFormatter.ofPattern(patterns));


    }


    /**
     * 对比两个时间差
     * @param contrast          对比时间
     * @param byContrast        被对比时间
     * @param dateEnums         对比类型（毫秒、秒、分...）
     * @return
     */
    public static Long durationTime(LocalDateTime contrast,LocalDateTime byContrast,DateEnums dateEnums){
        if(StringUtils.isEmpty(byContrast) || StringUtils.isEmpty(contrast))
            return null;
        Duration duration = Duration.between(byContrast, contrast);
        String patterns = dateEnums.getPatterns();
        switch (patterns){
            case "millis":
                return duration.toMillis();
            case "abs_millis":
                return duration.abs().toMillis();

            case "seconds":
                return duration.getSeconds();
            case "abs_seconds":
                return duration.abs().getSeconds();

            case "minutes":
                return duration.toMinutes();
            case "abs_minutes":
                return duration.abs().toMinutes();

            case "hours":
                return duration.toHours();
            case "abs_hours":
                return duration.abs().toHours();

            case "days":
                return duration.toDays();
            case "abs_days":
                return duration.abs().toDays();

            default:
                return null;
        }
    }

}
