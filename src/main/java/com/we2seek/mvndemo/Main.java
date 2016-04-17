package com.we2seek.mvndemo;


/**
 * This app shows greeting message depends on current time by rules below:
 * <pre>
 *     Good morning, World!   at 06:00 - 09:00
 *     Good day, World!       at 09:00 - 19:00
 *     Good evening, World!   at 19:00 - 23:00
 *     Good night, World!     at 23:00 - 06:00
 * </pre>
 *
 * @author Vitaliy Timchenko (vitaliy.timchenko@gmail.com)
 * @version 1.0
 */
public class Main {

    public static void main(String[] args) {
        System.out.println(Messenger.getMessage());
    }
}
