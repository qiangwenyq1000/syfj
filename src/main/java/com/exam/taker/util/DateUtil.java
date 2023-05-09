package com.exam.taker.util;

import org.joda.time.DateTime;
import org.joda.time.Seconds;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Date;

public class DateUtil {

    public static final String FORMAT_DATE_COMPACT = "yyyyMMdd";
    public static final String FORMAT_DATE_NORMAL = "yyyy-MM-dd";
    public static final String FORMAT_DATETIME_COMPACT = "yyyyMMddHHmmss";
    public static final String FORMAT_DATETIME_COMPACT_DAY = "yyyyMMdd";
    public static final String FORMAT_DATETIME_NORMAL = "yyyy-MM-dd HH:mm:ss";
    public static final String FORMAT_TIMESTAMP_NORMAL = "yyyy-MM-dd HH:mm:ss.SSS";

    public static long getCurrentTimeMillis() {
        return System.currentTimeMillis();
    }

    public static Integer secondsBetween(Date start, Date end) {
        DateTime startDt = new DateTime(start);
        DateTime endDt = new DateTime(end);
        int seconds = Seconds.secondsBetween(startDt, endDt).getSeconds();
        return seconds;
    }

    public static String formatNow(String formatPattern) {
        return formatDate(getNow(), formatPattern);
    }

    public static String formatDate(Date dt, String formatPattern) {
        DateTime dateTime = new DateTime(dt);
        return dateTime.toString(formatPattern);
    }

    public static Date parseDate(String dateStr, String formatPattern) {
        DateTimeFormatter fmt = DateTimeFormat.forPattern(formatPattern);
        return fmt.parseDateTime(dateStr).toDate();
    }

    public static Date getNow() {
        return new Date();
    }

    public static Date getYesterday() {
        Date now = new Date();
        DateTime dateTime = new DateTime(now);
        return dateTime.minusDays(1).toDate();
    }

    public static Date plusDays(Date date, int days) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusDays(days).toDate();
    }

    public static Date minusDays(Date date, int days) {
        DateTime dateTime = new DateTime(date);
        return dateTime.minusDays(days).toDate();
    }

    public static Date plusHours(Date date, int hours) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusHours(hours).toDate();
    }

    public static Date plusMinutes(Date date, int minutes) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusMinutes(minutes).toDate();
    }

    public static Date minusMinutes(Date date, int minutes) {
        DateTime dateTime = new DateTime(date);
        return dateTime.minusMinutes(minutes).toDate();
    }

    public static Date plusSeconds(Date date, int seconds) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusSeconds(seconds).toDate();
    }

    public static Date minusSeconds(Date date, int seconds) {
        DateTime dateTime = new DateTime(date);
        return dateTime.minusSeconds(seconds).toDate();
    }

    /**
     * + 年
     */
    public static Date plusYears(Date date, int years) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusYears(years).toDate();
    }

    /**
     * - 年
     */
    public static Date minusYears(Date date, int years) {
        DateTime dateTime = new DateTime(date);
        return dateTime.minusYears(years).toDate();
    }
}
