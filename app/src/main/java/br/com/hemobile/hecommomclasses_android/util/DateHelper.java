package br.com.hemobile.hecommomclasses_android.util;

import android.support.annotation.NonNull;

import java.util.Calendar;
import java.util.Date;

public abstract class DateHelper {

    @NonNull
    public static Calendar asCalendar(int day, int month, int year) {
        Calendar calendar = Calendar.getInstance();

        calendar.clear();
        calendar.set(year, month - 1, day);

        return calendar;
    }

    @NonNull
    public static Calendar endOfDay(int day, int month, int year) {
        return endOfDay(asCalendar(day, month, year));
    }

    @NonNull
    public static Calendar endOfDay(Calendar calendar) {
        calendar = clearTime(calendar);

        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);

        return calendar;
    }

    @NonNull
    public static Date asDayMonthDate(@NonNull Date date) {
        Calendar cal = Calendar.getInstance();
        Calendar dateCal = Calendar.getInstance();

        dateCal.setTime(date);

        cal.clear();

        cal.set(Calendar.YEAR,  dateCal.get(Calendar.YEAR));
        cal.set(Calendar.MONTH, dateCal.get(Calendar.MONTH));
        cal.set(Calendar.DAY_OF_MONTH, dateCal.get(Calendar.DAY_OF_MONTH));

        return cal.getTime();
    }

    @NonNull
    public static Date asHourDate(@NonNull Date date) {
        Calendar cal = Calendar.getInstance();
        Calendar dateCal = Calendar.getInstance();

        dateCal.setTime(date);

        cal.clear();
        cal.set(Calendar.HOUR_OF_DAY, dateCal.get(Calendar.HOUR_OF_DAY));

        return cal.getTime();
    }

    public static boolean isBetween(Date date, Date start, Date end) {
        Calendar cal = clearSecondsAndMilliseconds(wrap(date));
        Calendar startCal = clearSecondsAndMilliseconds(wrap(start));
        Calendar endCal = clearSecondsAndMilliseconds(wrap(end));

        if(startCal.after(endCal)) {
            return false; // Interval mismatch
        }

        return isBetween(cal, startCal, endCal);
    }

    public static boolean isBetween(Calendar calendar, Calendar start, Calendar end) {
        return (start.before(calendar) || start.equals(calendar)) && (end.after(calendar) || end.equals(calendar));
    }

    public static Calendar wrap(Date date) {
        Calendar calendar = Calendar.getInstance();

        calendar.setTime(date);

        return calendar;
    }

    private static Calendar clearTime(Calendar calendar) {
        calendar = clearSecondsAndMilliseconds(calendar);

        calendar.clear(Calendar.MINUTE);
        calendar.clear(Calendar.HOUR);

        return calendar;
    }

    private static Calendar clearSecondsAndMilliseconds(Calendar calendar) {
        calendar.clear(Calendar.SECOND);
        calendar.clear(Calendar.MILLISECOND);

        return calendar;
    }
}
