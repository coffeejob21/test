package com.we2seek.mvndemo;

public class Main {
    public static void main(String[] args) {
        String now = "18:05";
        String start = "19:00";
        String end = "18:00";

        System.out.println(now.compareTo(start));
        System.out.println(now.compareTo(end));
    }
}
