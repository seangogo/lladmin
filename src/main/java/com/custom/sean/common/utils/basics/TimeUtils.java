package com.custom.sean.common.utils.basics;

import java.time.*;
import java.util.Date;

/**
 * Created by sean on 2017/11/2.
 */
public class TimeUtils {

    public static LocalDateTime UDateToLocalDateTime(Date date) {
         return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    public static LocalDate UDateToLocalDate(Date date) {
         return UDateToLocalDateTime(date).toLocalDate();
    }

    public static LocalTime UDateToLocalTime(Date date) {
         return UDateToLocalDateTime(date).toLocalTime();
    }


    public static Date LocalDateTimeToUdate(LocalDateTime localDateTime) {
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDateTime.atZone(zone).toInstant();
        return  Date.from(instant);
    }

}
