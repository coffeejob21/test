package com.we2seek.mvndemo;


import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class DateUtils_TimeParsingTest {

    //    final static Logger logger = Logger.getLogger(LoggerUtils.getCurrentClassName());
    final static Logger logger = Logger.getLogger("tests.timeparsing");

    private String inputTime;
    private boolean expectedResult;

    public DateUtils_TimeParsingTest(String inputTime, boolean expectedResult) {
        this.inputTime = inputTime;
        this.expectedResult = expectedResult;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> generateData() {
        return Arrays.asList(new Object[][]{
                {"00:00", true},
                {"01:00", true},
                {"23:00", true},
                {"23:59", true},
                {"0:00", false},
                {"1:00", false},
                {"9:59", false},
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
        String outputText = String.format("Time: %6s expected: %5s, actual: %5s [%s]",
                inputTime,
                expectedResult,
                result,
                expectedResult == result ? "PASS" : "FAIL"
        );
        logger.debug(outputText);
        assertEquals(expectedResult, result);
    }
}