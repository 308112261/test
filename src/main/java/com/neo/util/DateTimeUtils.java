package com.neo.util;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author chi  2018-08-09 18:06
 **/
public class DateTimeUtils {

    private DateTimeUtils() {}

    public static final DateTimeFormatter yyyy_M_d = DateTimeFormatter.ofPattern("yyyy-M-d");
    public static final DateTimeFormatter yyyy_MM_dd = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static final DateTimeFormatter yyyy_M_d_H_m = DateTimeFormatter.ofPattern("yyyy-M-d H:m");
    public static final DateTimeFormatter yyyy_MM_dd_H_m_s = DateTimeFormatter.ofPattern("yyyy-MM-dd H:m:s");
    public static final DateTimeFormatter yyyy_MM_dd_HH_mm_ss = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static final DateTimeFormatter HH_mm = DateTimeFormatter.ofPattern("HH:mm");
    public static final DateTimeFormatter HH_mm_ss = DateTimeFormatter.ofPattern("HH:mm:ss");
    public static final DateTimeFormatter yyyy_MM_dd_HH_mm = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
    public static final DateTimeFormatter yyyyMMdd_HH_mm_ss = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    public static final DateTimeFormatter yyyy_MM_dd_HH_mma = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static final DateTimeFormatter yyyyMMdd= DateTimeFormatter.ofPattern("yyyy/MM/dd");

    public static LocalDate yyyy_MM_dd(String localDateTime) {
        return LocalDate.parse(localDateTime, yyyy_MM_dd);
    }

    public static LocalDateTime yyyy_MM_dd_H_m_s(String localDateTime) {
        return LocalDateTime.parse(localDateTime, yyyy_MM_dd_H_m_s);
    }

    public static LocalDateTime yyyy_MM_dd_HH_mm_ss(String localDateTime) {
        return LocalDateTime.parse(localDateTime, yyyy_MM_dd_HH_mm_ss);
    }

    public static LocalDateTime yyyy_MM_dd_H_m(String localDateTime) {
        return LocalDateTime.parse(localDateTime, yyyy_M_d_H_m);
    }

    public static LocalDateTime startOfDay(LocalDateTime startTime){
        return startTime.toLocalDate().atStartOfDay();
    }
    public static LocalDateTime endOfDay(LocalDateTime startTime){
        return startTime.plusDays(1L).toLocalDate().atStartOfDay().minusSeconds(1L);
    }
    public static LocalDateTime startOfWeek(LocalDateTime startTime){
        return startTime.with(DayOfWeek.MONDAY).toLocalDate().atStartOfDay();
    }
    public static LocalDateTime endOfWeek(LocalDateTime startTime){
        return startTime.plusWeeks(1L).with(DayOfWeek.MONDAY).toLocalDate().atStartOfDay().minusSeconds(1L);
    }
    public static LocalDateTime startOfMonth(LocalDateTime startTime){
        return startTime.withDayOfMonth(1).toLocalDate().atStartOfDay();
    }
    public static LocalDateTime endOfMonth(LocalDateTime startTime){
        return startTime.plusMonths(1L).withDayOfMonth(1).toLocalDate().atStartOfDay().minusSeconds(1);
    }

    public static LocalDateTime startOfYear(LocalDateTime startTime){
        return startTime.withDayOfYear(1).toLocalDate().atStartOfDay();
    }
    public static LocalDateTime endOfYear(LocalDateTime startTime){
        return startTime.plusYears(1L).withDayOfYear(1).toLocalDate().atStartOfDay().minusSeconds(1L);
    }


    public static String minuteAndSecond(LocalDateTime start, LocalDateTime end){
        Duration between = Duration.between(start, end);
        int seconds = (int)between.getSeconds();
        int h = seconds / 3600;
        int m = seconds % 3600 / 60;
        int s = seconds % 60; //不足60的就是秒，够60就是分

        StringBuilder sb = new StringBuilder();
        if(h!=0){
            sb.append(h+"小时");
        }
        if(h!=0 || m!=0){
            sb.append(m+"分钟");
        }

        return sb.append(s+"秒").toString();
    }

    public static String yyyy_MM_dd_HH_mm(LocalDateTime time){
        return yyyy_MM_dd_HH_mm.format(time);
    }


    public static String yyyyMMdd_HH_mm_ss(LocalDateTime time){
        if(time!=null){
            return yyyyMMdd_HH_mm_ss.format(time);
        }
       return "";
    }

    public static String yyyy_MM_dd_HH_mm_ss(LocalDateTime time){
        if(time!=null){
        return yyyy_MM_dd_HH_mm_ss.format(time);
        }
        return "";
    }


    public static String yyyy_MM_dd_HH_mma(LocalDateTime time){
        return yyyy_MM_dd_HH_mma.format(time);
    }

    public static String yyyy_MM_dd(LocalDateTime time){
        return yyyy_MM_dd.format(time);
    }


    public static String HH_mm(LocalDateTime time){
        return HH_mm.format(time);
    }


    public static String yyyyMMdd(LocalDateTime time){
        return yyyyMMdd.format(time);
    }


}
