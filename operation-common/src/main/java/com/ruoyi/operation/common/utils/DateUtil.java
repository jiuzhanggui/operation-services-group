package com.ruoyi.operation.common.utils;

import java.text.ParseException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.apache.commons.lang3.time.DateParser;
import org.apache.commons.lang3.time.FastDateFormat;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.http.util.Asserts;
import org.springframework.util.StringUtils;

/**
 * @author xujianhu
 * @date 2023-05-03 16:39
 * @Description: date 工具类
 */
public class DateUtil {
    private static final ZoneId ZONE_ID = ZoneId.systemDefault();

    public static final String SECOND_TIME_TYPE = "SECOND_TIME_TYPE";

    public static final String MILLISECOND_TIME_TYPE = "MILLISECOND_TIME_TYPE";

    public static final DateTimeFormatter FORMATTER_DATETIME = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static final DateTimeFormatter FORMATTER_DATE = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static final DateTimeFormatter FORMATTER_DATETIME_MILLSENCOND =
        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

    private static final DateParser DATE_PARSER = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss");

    public static Date parseDateString(String s) {
        try {
            return DATE_PARSER.parse(s);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 时间戳 转 Date
     *
     * @param timestamp
     * @return
     */
    public static Date parseDateTimestamp(Long timestamp) {
        if (null == timestamp || timestamp.equals(0L)) {
            return null;
        }
        return new Date(timestamp);
    }

    public static LocalDateTime parseDate(Date d) {
        if (null == d) {
            return null;
        }
        return LocalDateTime.ofInstant(d.toInstant(), ZONE_ID);
    }

    public static LocalDateTime parseString(String s) {
        if (StringUtils.isEmpty(s)) {
            return null;
        }
        if (s.contains(":")) {
            return LocalDateTime.parse(s, FORMATTER_DATETIME);
        } else {
            return LocalDate.parse(s, DateTimeFormatter.ISO_DATE).atStartOfDay();
        }
    }

    public static LocalDateTime parseLocalDate(LocalDate dt) {
        if (null == dt) {
            return null;
        }
        return dt.atStartOfDay();
    }

    public static String parseDateTimeFormat(LocalDateTime d, DateTimeFormatter dtf) {
        return d.format(dtf);
    }

    public static LocalDateTime toDateTimeZeroHourMinusSecond(LocalDateTime d) {
        return d.withHour(0).withMinute(0).withSecond(0).withNano(0);
    }

    public static Long parseLocalDateTime(LocalDateTime d, String timeType) {
        if (null == d) {
            return 0L;
        }
        switch (timeType) {
            case SECOND_TIME_TYPE:
                return d.toEpochSecond(ZoneOffset.of("+8"));
            case MILLISECOND_TIME_TYPE:
                return d.toInstant(ZoneOffset.of("+8")).toEpochMilli();
            default:
                return d.toEpochSecond(ZoneOffset.of("+8"));
        }
    }

    public static Long parseLocalDateTime(LocalDateTime d) {
        if (null == d) {
            return 0L;
        }
        return d.toInstant(ZoneOffset.of("+8")).toEpochMilli();
    }

    public static Boolean compareToTwoDateTimeByDateTime(LocalDateTime d,
        Pair<LocalDateTime, LocalDateTime> compareDateTime) {
        Asserts.notNull(d, "wait compare param time is not null");
        Asserts.notNull(compareDateTime, "compare date time is not null");
        Long dl = parseLocalDateTime(d);
        Long dLeft = parseLocalDateTime(compareDateTime.getLeft());
        Long dRight = parseLocalDateTime(compareDateTime.getRight());
        if (dl >= dLeft && dl <= dRight) {
            return true;
        }
        return false;
    }

    public static LocalDateTime currentDate(boolean isZero) {
        if (isZero) {
            return getNowMin();
        }
        return LocalDateTime.now();
    }

    public static LocalDateTime currentDate() {
        return LocalDateTime.now();
    }

    public static LocalDateTime getNowMin() {
        return LocalDateTime.of(LocalDate.now(), LocalTime.MIN);// 当天零点
    }

    public static LocalDateTime getNowMax() {
        return LocalDateTime.of(LocalDate.now(), LocalTime.MAX);// 当天结束
    }

    /**
     * 判断时间是否在当天内[00, 23]
     *
     * @param dt
     * @return
     */
    public static Boolean isWithInDay(LocalDateTime dt) {
        return dt.isAfter(getNowMin()) && dt.isBefore(getNowMax()) || dt.equals(getNowMax()) || dt.equals(getNowMin());
    }

    public static LocalDateTime calculateCurrentDate(Long d) {
        if (null == d) {
            return null;
        }
        return LocalDateTime.now().plusDays(d);
    }

    public static LocalDateTime calculateCustomDay(LocalDateTime dt, Long d) {
        if (null == dt || null == d) {
            return null;
        }
        return dt.plusDays(d);
    }

    public static LocalDateTime calculateCustomSecond(LocalDateTime dt, Long d) {
        if (null == dt || null == d) {
            return null;
        }
        return dt.plusSeconds(d);
    }

    public static Date convertLocalDateTimetoDate(LocalDateTime dt) {
        if (null == dt) {
            return null;
        }
        Instant instant = dt.atZone(ZoneId.systemDefault()).toInstant();
        return Date.from(instant);
    }

    public static String convertLocalDateTimetoString(LocalDateTime dt, DateTimeFormatter dtf) {
        if (dt == null) {
            return null;
        }
        return dt.format(dtf);
    }

    public static String convertLocalDateTimetoString(LocalDateTime dt) {
        if (dt == null) {
            return null;
        }
        return dt.format(FORMATTER_DATETIME);
    }

    public static String convertDatetoString(Date dt) {
        return convertLocalDateTimetoString(parseDate(dt));
    }

    public static String convertLocalDateTimetoString() {
        return LocalDateTime.now().format(FORMATTER_DATETIME);
    }

    public static String convertLocalDateTimetoStringMillSencond() {
        return LocalDateTime.now().format(FORMATTER_DATETIME_MILLSENCOND);
    }

    public static int compareTwoDatetimeOfDay(LocalDateTime leftDateTime, LocalDateTime rightDateTime) {
        return (int)ChronoUnit.DAYS.between(leftDateTime, rightDateTime);
    }

    public static Date earlierTime(Date first, Date second) {
        if (first == null) {
            return second;
        }
        if (second == null) {
            return first;
        }
        if (first.before(second)) {
            return first;
        } else {
            return second;
        }
    }

    public static Date laterTime(Date first, Date second) {
        if (first == null) {
            return second;
        }
        if (second == null) {
            return first;
        }
        if (first.after(second)) {
            return first;
        } else {
            return second;
        }
    }

    public static LocalDateTime earlierTime(LocalDateTime first, LocalDateTime second) {
        if (first == null) {
            return second;
        }
        if (second == null) {
            return first;
        }
        if (first.isBefore(second)) {
            return first;
        } else {
            return second;
        }
    }

    public static LocalDateTime laterTime(LocalDateTime first, LocalDateTime second) {
        if (first == null) {
            return second;
        }
        if (second == null) {
            return first;
        }
        if (first.isAfter(second)) {
            return first;
        } else {
            return second;
        }
    }
}
