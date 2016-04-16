package com.we2seek.mvndemo;

import java.util.Date;

/**
 * Created by Vitaliy Timchenko on 15.04.16 14:18.
 */
public class DateUtils {

    /**
     * Tests if current date belongs specified interval.
     *
     * @param from start date
     * @param to end date
     * @return <code>true</code> if current date belongs to specified interval exclusively.
     * @exception NullPointerException if <code>from</code> or <code>to</code> is null.
     */
    public static boolean isInInterval(Date now, Date from, Date to){
        if (from == null || to == null) {
            throw new NullPointerException();
        }
        // If dates are the same or difference between two dates so small
        if (from.equals(to) || Math.abs(from.getTime() - to.getTime()) < 2 ) {
            throw new IllegalArgumentException("Can not create interval from " + from + " and " + to);
        }

        String newLine = System.getProperty("line.separator");

        /*if (now == null) {
            now = new Date();
        }*/
        boolean isAfterFrom = now.after(from);
        boolean isBeforeTo = now.before(to);
        System.out.printf("now: \t\t%d%s", now.getTime(), newLine);
        System.out.printf("from: \t\t%d%s", from.getTime(), newLine);
        System.out.printf("to: \t\t%d%s", to.getTime(), newLine);
        System.out.printf("before: \t%s%s", isBeforeTo, newLine);
        System.out.printf("after: \t%s%s", isAfterFrom, newLine);

        return isAfterFrom && isBeforeTo;
    }
}
