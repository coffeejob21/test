package com.we2seek.mvndemo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        System.out.println(new Date());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date start = null;
        Date end = null;
        Date now = null;
        try {
            now = new Date();
            start = new Date(sdf.parse("2016-04-15 14:54:00").getTime());
            end = new Date(sdf.parse("2016-04-15 14:59:00").getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.out.println(DateUtils.isInInterval(start, end));
    }
}
