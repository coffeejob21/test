package com.we2seek.mvndemo;

import org.apache.log4j.Logger;

import java.util.regex.Pattern;

/**
 * This class works with time to test given time is between time interval
 */
public class DateUtils {

    final static Logger logger = Logger.getLogger(LoggerUtils.getCurrentClassName());

    private static final String newLine = System.getProperty("line.separator");

    // 24 Hours time pattern
    private static final String REG = "^(([0-1][0-9])|([2][0-3])):([0-5][0-9])$";

    private static final Pattern timePattern = Pattern.compile(REG);

    /**
     * Checks is given time between specified time interval
     *
     * @param time  Tested time in HH:mm format
     * @param start Start of time interval in HH:mm format (inclusive)
     * @param end   End of time interval in HH:mm format (exclusive)
     * @return <code>true</code> if <code>time</code> between <code>start</code> inclusively
     * and <code>end</code> exclusively
     * @throws NullPointerException     When passed params are nulls
     * @throws IllegalArgumentException When passed params doesn't match HH:mm format,
     *                                  or if <code>start</code> equals to <code>end</code>
     */
    public static boolean isTimeBetween(String time, String start, String end)
            throws NullPointerException, IllegalArgumentException {

        String errorMessage;

        if (time == null || start == null || end == null) {
            errorMessage = String.format(
                    "Passed null arguments:%stime: %s, start: %s, end: %s",
                    newLine,
                    time == null ? "null" : time,
                    start == null ? "null" : start,
                    end == null ? "null" : end
            );
            throw new NullPointerException(errorMessage);
        }

        if (!isValid(time) || !isValid(start) || !isValid(end)) {
            errorMessage = String.format(
                    "Time doesn't match the HH:mm format:%stime: %s, start: %s, end: %s",
                    newLine,
                    time,
                    start,
                    end
            );
            throw new IllegalArgumentException("Time doesn\'t match the HH:mm format");
        }

        if (start.compareTo(end) == 0) {
            throw new IllegalArgumentException("Passed arguments doesn\'t form time interval");
        }

        // Normalize hours to 2 digits format to be correctly compared

        // This will return
        boolean result;

        // eg 18:00 - 23:00
        if (start.compareTo(end) < 0) {
            result = time.compareTo(start) >= 0 && time.compareTo(end) < 0;
        }
        // eg 23:00 - 06:00
        else {
            result = time.compareTo(start) >= 0 || time.compareTo(end) < 0;
        }

        logger.debug(time + " [" + start + " - " + end + "] " + result);

        return result;
    }

    /**
     * Tests if given time matches HH:mm format
     *
     * @param time
     * @return
     */
    public static boolean isValid(final String time) {
        return timePattern.matcher(time).matches();
    }
}
