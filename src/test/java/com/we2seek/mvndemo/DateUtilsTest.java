package com.we2seek.mvndemo;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static com.we2seek.mvndemo.DateUtils.isInInterval;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DateUtilsTest {
    private Date now;

    @Before
    public void setUp() {
        this.now = new Date();
        DateUtils.setDateToCompare(this.now);
    }

    @Test
    public void nowInInterval() {
        String newLine = System.getProperty("line.separator");

        Date start = new Date(now.getTime() - 5_000);
        Date end = new Date(now.getTime() + 5_000);

        System.out.printf("NOW IN INTERVAL%sNow: \t%s%s", newLine, now, newLine);
        System.out.printf("Start: \t%s%s", start, newLine);
        System.out.printf("End: \t%s%s%s", end, newLine, newLine);

        assertTrue(isInInterval(start, end));
    }

    @Test
    public void nowBeforeInterval() {
        String newLine = System.getProperty("line.separator");

        Date start = new Date(now.getTime() + 5_000);
        Date end = new Date(now.getTime() + 10_000);

        System.out.printf("NOW BEFORE%sNow: \t%s%s", newLine, now, newLine);
        System.out.printf("Start: \t%s%s", start, newLine);
        System.out.printf("End: \t%s%s%s", end, newLine, newLine);

        assertFalse(isInInterval(start, end));
    }

    @Test
    public void nowAfterInterval() {
        String newLine = System.getProperty("line.separator");

        Date start = new Date(now.getTime() - 10_000);
        Date end = new Date(now.getTime() - 5_000);

        System.out.printf("NOW AFTER%sNow: \t%s%s", newLine, now, newLine);
        System.out.printf("Start: \t%s%s", start, newLine);
        System.out.printf("End: \t%s%s%s", end, newLine, newLine);

        assertFalse(isInInterval(start, end));
    }

    @Test
    public void nowEqualsStartDate() {
        String newLine = System.getProperty("line.separator");

        Date start = now;
        Date end = new Date(now.getTime() + 5_000);

        System.out.printf("NOW == START%sNow: \t%s%s", newLine, now, newLine);
        System.out.printf("Start: \t%s%s", start, newLine);
        System.out.printf("End: \t%s%s%s", end, newLine, newLine);

        assertFalse(isInInterval(start, end));
    }

    @Test
    public void nowEqualsEndDate() {
        String newLine = System.getProperty("line.separator");

        Date start = new Date(now.getTime() - 5_000);
        Date end = now;

        System.out.printf("NOW == END%sNow: \t%s%s", newLine, now, newLine);
        System.out.printf("Start: \t%s%s", start, newLine);
        System.out.printf("End: \t%s%s%s", end, newLine, newLine);

        assertFalse(isInInterval(start, end));
    }

    @Test(expected = NullPointerException.class)
    public void dateFromEqualsNull() {
        isInInterval(null, new Date());
    }

    @Test(expected = NullPointerException.class)
    public void dateToEqualsNull() {
        isInInterval(now, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void datesAreEquals() {
        isInInterval(now, now);
    }

    // start = "23:00", end = "06:00"
    @Test
    public void startDateGreaterThanEndDate() {
        Date start = new Date(now.getTime() - 5_000);
        Date end = new Date(now.getTime() - 3600_000 * 5);

        assertTrue(isInInterval(start, end));
    }

    @Test
    public void nowInIntervalOfTime() {
        assertTrue(isInInterval("14:00", "15:00"));
    }

}