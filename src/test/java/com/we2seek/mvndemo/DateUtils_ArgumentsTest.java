package com.we2seek.mvndemo;

import org.junit.Test;

public class DateUtils_ArgumentsTest {

    @Test(expected = NullPointerException.class)
    public void nullPointeInitialTime() {
        DateUtils.isTimeBetween(null, "", "");
    }

    @Test(expected = NullPointerException.class)
    public void nullPointerStartTime() {
        DateUtils.isTimeBetween("", null, "");
    }

    @Test(expected = NullPointerException.class)
    public void nullPointerEndTime() {
        DateUtils.isTimeBetween("", "", null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void emptyArguments() {
        DateUtils.isTimeBetween("", "", "");
    }

    @Test(expected = IllegalArgumentException.class)
    public void invalidArguments() {
        DateUtils.isTimeBetween("17:60", "00:00", "00:00");
    }

    @Test(expected = IllegalArgumentException.class)
    public void startEqualsEnd() {
        DateUtils.isTimeBetween("17:00", "00:00", "00:00");
    }

}
