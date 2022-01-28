package com.hli.example.userbehavior;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Date {
    private static final List<Integer> LAST_DAY_OF_THE_MONTH = Collections.unmodifiableList(Arrays.asList(0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31));
    private final int month;
    private final int day;
    private final DayOfTheWeek dayOfTheWeek;

    public Date(String date) {
        month = splitMonth(date);
        day = splitDay(date);
        if (date.length() > 6) {
            dayOfTheWeek = DayOfTheWeek.valueOf(date.substring(6));
        } else {
            dayOfTheWeek = null;
        }
    }

    public Date(int month, int day, DayOfTheWeek dayOfTheWeek) {
        this.month = month;
        this.day = day;
        this.dayOfTheWeek = dayOfTheWeek;
    }

    public boolean isWeekday() {
        return dayOfTheWeek.isWeekday();
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

    public Date getNextDate() {
        int nextDay = day + 1;
        int nextMonth = month;
        if (nextDay > LAST_DAY_OF_THE_MONTH.get(nextMonth)) {
            nextMonth += 1;
            nextDay = 1;
        }
        DayOfTheWeek nextDayOfTheWeek = dayOfTheWeek.getNextDay();
        return new Date(nextMonth, nextDay, nextDayOfTheWeek);
    }
}
