package com.we2seek.mvndemo;

import org.apache.log4j.Logger;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Return message to user depending on current time and user's locale
 */
public class Messenger {

    final static Logger logger = Logger.getLogger(LoggerUtils.getCurrentClassName());

    private static Map<String, String[]> timeIntervals;
    // Current user's JVM locale
    private static Locale currentLocale = Locale.getDefault();

    static {
        timeIntervals = new HashMap<>();
        timeIntervals.put("morning", new String[]{"06:00", "09:00"});
        timeIntervals.put("day", new String[]{"09:00", "19:00"});
        timeIntervals.put("evening", new String[]{"19:00", "23:00"});
        timeIntervals.put("night", new String[]{"23:00", "06:00"});
    }

    /**
     * Get message depends on current part of day: morning, day, evening, night.
     * Use user's default locale to retrieve messages from properties files.
     *
     * @return
     */
    public static String getMessage() {
        /* For test purposes uncomment one of below: */
        // currentLocale = new Locale("uk", "UA");
        // currentLocale = new Locale("ru", "RU");

        return getMessage(currentLocale);
    }

    /**
     * Get message depends on current part of day: morning, day, evening, night and passed <code>Locale</code>.
     *
     * @param locale Pass <code>Locale</code> to retrieve locale-based message.
     * @return
     */
    public static String getMessage(Locale locale) {
        logger.debug("======== Get Message ========");
        String currentTime = getCurrentTime();
        String result = null;
        logger.debug("Current time: " + currentTime);

        try {
            for (Map.Entry<String, String[]> entry : timeIntervals.entrySet()) {
                String key = entry.getKey();
                String startTime = entry.getValue()[0];
                String endTime = entry.getValue()[1];

                if (DateUtils.isTimeBetween(currentTime, startTime, endTime)) {
                    logger.debug("It is " + key);
                    result = getMessage(key);
                    break;
                }
            }
        } catch (Exception e) {
            logger.warn(e);
        }

        // Normally we must not be here
        if (result == null) {
            logger.warn("Something wrong with time intervals set. Maybe its empty.");
        }
        return result;
    }

    /**
     * Retrieve value by key from "messages*.properties" file based on current locale.
     *
     * @param key
     * @return Localized message
     */
    private static String getMessage(final String key) {
        return getMessage(key, currentLocale);
    }

    /**
     * Retrieve value by key from "messages*.properties" file based on passed locale.
     * If file with certain locale not found the default message will be loaded.
     *
     * @param key    key name in properties file
     * @param locale locale
     * @return Localized message
     */
    private static String getMessage(final String key, final Locale locale) {
        final String fileName = "messages";

        logger.debug("Trying to load message for " + currentLocale.getDisplayName());
        logger.debug("From: \"" + fileName + "_" +
                currentLocale.getLanguage() + "_" +
                currentLocale.getCountry() + ".properties\"");

        ResourceBundle messagesBundle = null;
        String message = null;
        String messageInUnicode = "";

        try {
            messagesBundle = ResourceBundle.getBundle(fileName, locale);
            message = messagesBundle.getString(key);
            messageInUnicode = new String(message.getBytes("ISO-8859-1"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            logger.warn(e);
            System.err.println("Wrong encoding: " + e.getMessage());
        } catch (MissingResourceException e) {
            logger.warn(e);
            System.err.println(e.getMessage());
        } catch (NullPointerException e) {
            logger.warn(e);
            System.err.println(e.getMessage());
        }

        logger.debug("The message is: " + messageInUnicode);
        return messageInUnicode;
    }

    private static String getCurrentTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        Date now = new Date();
        return formatter.format(now);
    }
}
