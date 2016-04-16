package com.we2seek.mvndemo;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static com.we2seek.mvndemo.DateUtils.isInInterval;
import static org.junit.Assert.*;

/**
 * Created by Vitaliy Timchenko on 15.04.16 15:39.
 */
public class DateUtilsTest {
    private Date now;

    @Before
    public void setUp() {
        this.now = new Date();
    }

    @Test
    public void nowInInterval() {
        String newLine = System.getProperty("line.separator");

        Date start = new Date(now.getTime() - 5_000);
        Date end = new Date(now.getTime() + 5_000);

        System.out.printf("NOW IN INTERVAL%sNow: \t%s%s", newLine, now, newLine);
        System.out.printf("Start: \t%s%s", start, newLine);
        System.out.printf("End: \t%s%s%s", end, newLine, newLine);

        assertTrue(isInInterval(now, start, end));
    }

    @Test
    public void nowBeforeInterval() {
        String newLine = System.getProperty("line.separator");

        Date start = new Date(now.getTime() + 5_000);
        Date end = new Date(now.getTime() + 10_000);

        System.out.printf("NOW BEFORE%sNow: \t%s%s", newLine, now, newLine);
        System.out.printf("Start: \t%s%s", start, newLine);
        System.out.printf("End: \t%s%s%s", end, newLine, newLine);

        assertFalse(isInInterval(now, start, end));
    }

    @Test
    public void nowAfterInterval() {
        String newLine = System.getProperty("line.separator");

        Date start = new Date(now.getTime() - 10_000);
        Date end = new Date(now.getTime() - 5_000);

        System.out.printf("NOW AFTER%sNow: \t%s%s", newLine, now, newLine);
        System.out.printf("Start: \t%s%s", start, newLine);
        System.out.printf("End: \t%s%s%s", end, newLine, newLine);

        assertFalse(isInInterval(now, start, end));
    }

    @Test
    public void nowEqualsStartDate() {
        String newLine = System.getProperty("line.separator");

        Date start = now;
        Date end = new Date(now.getTime() + 5_000);

        System.out.printf("NOW == START%sNow: \t%s%s", newLine, now, newLine);
        System.out.printf("Start: \t%s%s", start, newLine);
        System.out.printf("End: \t%s%s%s", end, newLine, newLine);

        assertFalse(isInInterval(now, start, end));
    }

    @Test
    public void nowEqualsEndDate() {
        String newLine = System.getProperty("line.separator");

        Date start = new Date(now.getTime() - 5_000);
        Date end = now;

        System.out.printf("NOW == END%sNow: \t%s%s", newLine, now, newLine);
        System.out.printf("Start: \t%s%s", start, newLine);
        System.out.printf("End: \t%s%s%s", end, newLine, newLine);

        assertFalse(isInInterval(now, start, end));
    }

    @Test(expected = NullPointerException.class)
    public void dateFromEqualsNull() {
        isInInterval(now, null, new Date());
    }

    @Test(expected = NullPointerException.class)
    public void dateToEqualsNull() {
        isInInterval(now, new Date(), null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void datesAreEquals() {
        isInInterval(now, now, now);
    }

}