package com.we2seek.mvndemo;

import org.apache.log4j.Logger;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

public class DateUtils_ArgumentsTest {

    final static Logger logger = Logger.getLogger("tests.arguments");

    @Rule
    public TestWatcher watchman = new TestWatcher() {
        @Override
        protected void succeeded(Description description) {
            logger.debug(description + " - PASS");
        }

        @Override
        protected void failed(Throwable e, Description description) {
            logger.debug(description + ", " + e.getCause() + " - FAIL");
            super.failed(e, description);
        }
    };

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
