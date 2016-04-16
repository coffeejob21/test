package com.we2seek.mvndemo;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class DateUtils_TimeValidationTest {
    private String inputTime;
    private boolean expectedResult;

    public DateUtils_TimeValidationTest(String inputTime, boolean expectedResult) {
        this.inputTime = inputTime;
        this.expectedResult = expectedResult;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> generateData() {
        return Arrays.asList(new Object[][]{
                {"00:00", true},
                {"01:00", true},
                {"23:00", true},
                {"0:00", true},
                {"1:00", true},
                {"9:59", true},
                {"23:59", true},
                {"24:00", false},
                {"25:00", false},
                {"12:60", false},
                {"0:0", false},
                {"12:0", false},
                {"100:00", false},
        });
    }

    @Test
    public void timeValidatorChecker() {
        boolean result = DateUtils.isValid(inputTime);
        System.out.println("For " + inputTime + " expected: " + expectedResult + " result: " + result);
        assertEquals(expectedResult, result);
    }
}
