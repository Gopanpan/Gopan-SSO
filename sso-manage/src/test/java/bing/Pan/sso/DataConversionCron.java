package Gopan.sso;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @crea : Created by intelliJ IDEA 16.1.3
 * @auth : Gopan
 * @mail : 15923508369@163.com
 * @date : 2017/6/27 16:01
 * @desc :
 */
public class DataConversionCron {


    public static void main(String[] args){
        String cron = getCron(new Date());
        System.out.println(cron);
    }



    public static String formatDateByPattern(Date date, String dateFormat){
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        String formatTimeStr = null;
        if (date != null) {
            formatTimeStr = sdf.format(date);
        }
        return formatTimeStr;
    }

    public static String getCron(java.util.Date  date){
        String dateFormat="ss mm HH dd MM ? yyyy";
        return formatDateByPattern(date, dateFormat);
    }



}
