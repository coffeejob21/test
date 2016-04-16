package com.we2seek.mvndemo;

import java.util.regex.Pattern;

public class DateUtils {

    // 24 Hours time pattern
    private static final String REG = "^(([0-9])|([0-1][0-9])|([2][0-3])):([0-5][0-9])$";

    private static final Pattern timePattern = Pattern.compile(REG);

    public static boolean isTimeBetween(String time, String start, String end) {

        if (time == null || start == null || end == null) {
            throw new NullPointerException();
        }

        if (!isValid(time) || !isValid(start) || !isValid(end)) {
            throw new IllegalArgumentException("Time doesn\'t match the HH:mm format");
        }

        if (start.compareTo(end) == 0) {
            throw new IllegalArgumentException("Passed arguments doesn\'t form time interval");
        }

        // eg 18:00 - 23:00
        if (start.compareTo(end) < 0) {
            return time.compareTo(start) >= 0 && time.compareTo(end) < 0;
        }
        // eg 23:00 - 06:00
        else {
            return time.compareTo(start) >= 0 || time.compareTo(end) < 0;
        }

    }

    public static boolean isValid(final String time) {
        return timePattern.matcher(time).matches();
    }
}
