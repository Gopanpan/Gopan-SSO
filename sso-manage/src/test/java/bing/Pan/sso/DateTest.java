package bing.Pan.sso;

import bing.Pan.sso.common.enums.DateEnums;
import bing.Pan.sso.common.utils.DateUtilsBaseOnLessThanJDK8;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @crea : Created by intelliJ IDEA 17.2
 * @auth : bing.Pan
 * @mail : 15923508369@163.com
 * @date : 10 31 2017 14:59
 * @desc :
 */
public class DateTest {

     public static void main(String[] args){


         Map<Integer, Map<String, String>> monthWithWeek = DateUtilsBaseOnLessThanJDK8.getMonthWithWeek(null);

         Set<Integer> keySet = monthWithWeek.keySet();

         for (int key: keySet) {
             Map<String, String> stringStringMap = monthWithWeek.get(key);
             System.out.println(stringStringMap.get("startDate")+"  || "+ stringStringMap.get("endDate"));

         }

         //printWeeks();
     }
     public static void printWeeks() {
         Map<Integer,Map<String,String>> weekMap = new HashMap<>();
         SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
         Calendar calendar = Calendar.getInstance();
         calendar.setTime(DateUtilsBaseOnLessThanJDK8.parse("2017-04","yyyy-MM"));
         calendar.set(Calendar.DATE, 1);
         int month = calendar.get(Calendar.MONTH);
         int count = 0;
         while (calendar.get(Calendar.MONTH) == month) {
             if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
                 ++ count;
                 Map<String,String> singleWeek = new HashMap<>();
                 singleWeek.put("startDate",format.format(calendar.getTime()));
                 calendar.add(Calendar.DATE, 6);
                 singleWeek.put("endDate",format.format(calendar.getTime()));
                 weekMap.put(count,singleWeek);
             }
             calendar.add(Calendar.DATE, 1);
         }



         Set<Integer> keySet = weekMap.keySet();

         for (int key: keySet) {
             Map<String, String> stringStringMap = weekMap.get(key);
             System.out.println(stringStringMap.get("startDate")+"  || "+ stringStringMap.get("endDate"));

         }
     }


}
