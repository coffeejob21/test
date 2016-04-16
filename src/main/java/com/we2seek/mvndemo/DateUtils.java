package com.we2seek.mvndemo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Vitaliy Timchenko on 15.04.16 14:18.
 */
public class DateUtils {

    // If this field equals null - compare with Date = now.
    private static Date dateToCompare;

    /**
     * Tests if current date belongs specified interval.
     *
     * @param from time in format "HH:mm"
     * @param to   time in format "HH:mm"
     * @return <code>true</code> if current time belongs to specified interval exclusively
     * @throws ParseException if passed arguments is not in "HH:mm" format.
     */
    public static boolean isInInterval(String from, String to) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

        Date dateFrom = null;
        Date dateTo = null;

        try {
            dateFrom = sdf.parse(from);
            dateTo = sdf.parse(to);
        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println("Can not parse time value. Please use HH:mm format");
        }

        return isInInterval(dateFrom, dateTo);
    }

    /**
     * Tests if current date belongs specified interval. If setup currentTime to any value will compare with this value.
     *
     * @param from start date
     * @param to   end date
     * @return <code>true</code> if current date belongs to specified interval exclusively.
     * @throws NullPointerException     if <code>from</code> or <code>to</code> is null.
     * @throws IllegalArgumentException if <code>from</code> equals to <code>to</code>,
     *                                  or if their difference so small to be an interval
     */
    public static boolean isInInterval(Date from, Date to) {
        if (from == null || to == null) {
            throw new NullPointerException();
        }
        // If dates are the same or difference between two dates so small
        if (from.equals(to) || Math.abs(from.getTime() - to.getTime()) < 2) {
            throw new IllegalArgumentException("Can not create interval from " + from + " and " + to);
        }

        String newLine = System.getProperty("line.separator");

        if (getDateToCompare() == null) {
            setDateToCompare(new Date());
        }

        Date now = getDateToCompare();

        // We do not pay attention to milliseconds
        now = resetMillis(now);
        from = resetMillis(from);
        to = resetMillis(to);

        // If end of interval < start then we add 1 day to the end
        // like 23:00 and 06:00
        if (from.after(to)) {
            to.setTime(to.getTime() + 86400_000);
        }

        boolean isAfterFrom = now.after(from);
        boolean isBeforeTo = now.before(to);
        System.out.printf("now: \t\t%d \t%s%s", now.getTime(), now, newLine);
        System.out.printf("from: \t\t%d \t%s%s", from.getTime(), from, newLine);
        System.out.printf("to: \t\t%d \t%s%s", to.getTime(), to, newLine);
        System.out.printf("before: \t%s%s", isBeforeTo, newLine);
        System.out.printf("after: \t%s%s", isAfterFrom, newLine);

        return isAfterFrom && isBeforeTo;
    }

    public static Date getDateToCompare() {
        return dateToCompare;
    }

    public static void setDateToCompare(Date dateToCompare) {
        DateUtils.dateToCompare = dateToCompare;
    }

    private static Date resetMillis(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTime();
    }
}
