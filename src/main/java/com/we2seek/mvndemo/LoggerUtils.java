package com.we2seek.mvndemo;

/**
 * Different useful things for logging
 */
public class LoggerUtils {

    /**
     * Get current class name from exception stack trace
     *
     * @return Class name
     */
    public static String getCurrentClassName() {
        try {
            throw new RuntimeException();
        } catch (RuntimeException e) {
            return e.getStackTrace()[1].getClassName();
        }
    }
}
