package com.xu.nohotel.utils;

import java.util.Calendar;
import java.util.Date;

public class TimeDifferent {
    // 5分钟内有效
    public static boolean LessThanFive(Date date1,Date date2) {
        Calendar calendar1 = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        calendar1.setTime(date1);
        calendar2.setTime(date2);
        long timeOne=calendar1.getTimeInMillis();
        long timeTwo=calendar2.getTimeInMillis();
        long minute=(timeOne-timeTwo)/(1000*60);
        if(minute <= 5) {
            return true;
        }else {
            return false;
        }
    }
}
