package com.hli.example.userbehavior;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Date {
    private static final String[] WEEK = {"SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT"};
    private static final Map<String, Integer> DAY_MAP = new HashMap() {{
        for (int i = 0; i < WEEK.length; i++) {
            put(WEEK[i], i);
        }
    }};
    private static final int[] LAST_DAY_OF_THE_MONTH = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    private int month;
    private int day;
    private String dayOfTheWeek;

    public Date(String date) {
        month = splitMonth(date);
        day = splitDay(date);
        if (date.length() > 6) {
            dayOfTheWeek = date.substring(6);
        }
    }

    public Date(int month, int day, String dayOfTheWeek) {
        this.month = month;
        this.day = day;
        this.dayOfTheWeek = dayOfTheWeek;
    }

    public boolean isWeekdays() {
        return (!dayOfTheWeek.equals("SAT") && !dayOfTheWeek.equals("SUN"));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Date date = (Date) o;
        return (month == date.month && day == date.day);
    }

    @Override
    public int hashCode() {
        return Objects.hash(month, day);
    }

    private static int splitMonth(String date) {
        return Integer.parseInt(date.substring(0, 2));
    }

    private static int splitDay(String date) {
        return Integer.parseInt(date.substring(3, 5));
    }

    private static String getNextDayOfTheWeek(String dayOfTheWeek) {
        int index = DAY_MAP.get(dayOfTheWeek);
        return WEEK[(++index) % 7];
    }

    public Date getNextDate() {
        int nextDay = day + 1;
        int nextMonth = month;
        if (nextDay > LAST_DAY_OF_THE_MONTH[nextMonth]) {
            nextMonth += 1;
            nextDay = 1;
        }
        String nextDayOfTheWeek = getNextDayOfTheWeek(dayOfTheWeek);
        return new Date(nextMonth, nextDay, nextDayOfTheWeek);
    }
}
