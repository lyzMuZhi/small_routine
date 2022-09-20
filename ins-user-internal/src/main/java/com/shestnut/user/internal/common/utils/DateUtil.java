package com.shestnut.user.internal.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

//    private static final ThreadLocalDateFormat yyyyMMdd = new ThreadLocalDateFormat("yyyyMMdd");
//    private static final ThreadLocalDateFormat yyyy_MM_dd = new ThreadLocalDateFormat("yyyy-MM-dd");

    /**
     * 解析时间戳
     *
     * @param timestamp 时间戳
     * @return
     */
    public static String parsingTimestamp(String timestamp){
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        long time = new Long(timestamp);
        return format.format(time);
    }

    public static String parsingTime2yyyyMMdd(String timestamp){
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        long time = new Long(timestamp);
        return format.format(time);
    }

    /**
     * yyyyMMdd  -> yyyy-MM-dd
     *
     * @param date  yyyyMMdd
     * @return      yyyy-MM-dd
     */
    public static String yyyyMMdd2yyyy_MM_dd(String date){
        try {
            Date time = new SimpleDateFormat("yyyyMMdd").parse(date);
            return new SimpleDateFormat("yyyy-MM-dd").format(time);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * yyyyMMdd  -> yyyy-MM-dd
     *
     * @param date  yyyyMMdd
     * @return      yyyy-MM-dd
     */
    public static String yyyy_MM_dd2yyyyMMdd(String date){
        try {
            Date time = new SimpleDateFormat("yyyy-MM-dd").parse(date);
            return new SimpleDateFormat("yyyyMMdd").format(time);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
