package com.hli.example.userbehavior;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Date {
    private static final List<String> WEEK = Collections.unmodifiableList(Arrays.asList("SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT"));
    private static final Map<String, Integer> DAY_MAP = Collections.unmodifiableMap(new HashMap<String, Integer>() {{
        for (int i = 0; i < WEEK.size(); i++) {
            put(WEEK.get(i), i);
        }
    }});
    private static final List<Integer> LAST_DAY_OF_THE_MONTH = Collections.unmodifiableList(Arrays.asList(0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31));
    private final int month;
    private final int day;
    private final String dayOfTheWeek;

    public Date(String date) {
        month = splitMonth(date);
        day = splitDay(date);
        if (date.length() > 6) {
            dayOfTheWeek = date.substring(6);
        } else {
            dayOfTheWeek = "";
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
        return WEEK.get((++index) % 7);
    }

    public Date getNextDate() {
        int nextDay = day + 1;
        int nextMonth = month;
        if (nextDay > LAST_DAY_OF_THE_MONTH.get(nextMonth)) {
            nextMonth += 1;
            nextDay = 1;
        }
        String nextDayOfTheWeek = getNextDayOfTheWeek(dayOfTheWeek);
        return new Date(nextMonth, nextDay, nextDayOfTheWeek);
    }
}
