package com.we2seek.mvndemo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class DateUtils_TimeComparationTest {
    private final String newLine = System.getProperty("line.separator");
    private String inputTime;
    private String startTime;
    private String endTime;
    private boolean expectedResult;

    public DateUtils_TimeComparationTest(String inputTime, String startTime, String endTime, boolean expectedResult) {
        this.inputTime = inputTime;
        this.startTime = startTime;
        this.endTime = endTime;
        this.expectedResult = expectedResult;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> generateData() {
        return Arrays.asList(new Object[][]{
                {"17:59", "18:00", "19:00", false},
                {"18:00", "18:00", "19:00", true},
                {"18:01", "18:00", "19:00", true},

                {"18:59", "18:00", "19:00", true},
                {"19:00", "18:00", "19:00", false},
                {"19:01", "18:00", "19:00", false},

                {"22:59", "23:00", "06:00", false},
                {"23:00", "23:00", "06:00", true},
                {"23:01", "23:00", "06:00", true},

                {"05:59", "23:00", "06:00", true},
                {"06:00", "23:00", "06:00", false},
                {"06:01", "23:00", "06:00", false},
        });
    }

    @Test
    public void timeBetween() {
        boolean result = DateUtils.isTimeBetween(inputTime, startTime, endTime);
        System.out.printf("%s %s %s %s %s",
                inputTime,
                startTime,
                endTime,
                expectedResult == result ? "PASSED" : "NOT PASSED",
                newLine
        );

        assertEquals(expectedResult, result);
    }

}