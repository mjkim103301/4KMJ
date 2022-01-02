package com.hli.exmaple.userbehavior;

import java.util.*;

class Solution {
    Calendar[][] calendar;
    Map<String, Integer> dayMap = new HashMap() {{
        put("SUN", 0);
        put("MON", 1);
        put("TUE", 2);
        put("WED", 3);
        put("THU", 4);
        put("FRI", 5);
        put("SAT", 6);
    }};
    final int[] LAST_DAY_OF_THE_MONTH = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    Calendar startDate, endDate;
    int startDayOfWeek;
    int calendarSize;
    int maxNumberOfWeekdays;

    public int solution(String start_date, String end_date, String[] login_dates) {
        maxNumberOfWeekdays = 0;
        startDate = new Calendar(splitMonth(start_date), splitDay(start_date));
        endDate = new Calendar(splitMonth(end_date), splitDay(end_date));
        String dayOfStartDate = start_date.substring(6);

        startDayOfWeek = dayMap.get(dayOfStartDate);

        //1달에 넉넉잡아 5주이므로 5를 곱함
        calendarSize = (endDate.month - startDate.month + 1) * 5;
        calendar = new Calendar[calendarSize][7];

        initCalendar();
        setCalendar();
        checkLogin(login_dates);
        return maxNumberOfWeekdays;
    }


    public int splitMonth(String target) {
        return Integer.parseInt(target.substring(0, 2));
    }

    public int splitDay(String target) {
        return Integer.parseInt(target.substring(3, 5));
    }

    public void initCalendar() {
        for (int week = 0; week < calendarSize; week++) {
            for (int day = 0; day < 7; day++) {
                calendar[week][day] = new Calendar();
            }
        }
    }

    public void setCalendar() {
        Calendar nextDate = new Calendar(startDate.month, startDate.day);

        //첫주 달력세팅
        for (int day = startDayOfWeek; day < 7; day++) {
            if (!makeCalendar(0, day, nextDate)) {
                return;
            }
        }

        //둘째주부터 달력 세팅
        for (int week = 1; week < calendarSize; week++) {
            for (int day = 0; day < 7; day++) {
                if (!makeCalendar(week, day, nextDate)) {
                    return;
                }
            }
        }
    }

    public boolean makeCalendar(int y, int x, Calendar date) {
        calendar[y][x] = new Calendar(date.month, date.day);
        if (date.month == endDate.month && date.day == endDate.day) {
            return false;
        }
        date.day += 1;
        if (date.day > LAST_DAY_OF_THE_MONTH[date.month]) {
            date.month += 1;
            date.day = 1;
        }
        return true;
    }

    public void checkLogin(String[] login_dates) {
        Arrays.sort(login_dates);

        int tempNumberOfWeekdays = 0;
        int loginIndex = 0;

        for (int week = 0; week < calendarSize; week++) {
            for (int day = 0; day < 7; day++) {
                Calendar now = calendar[week][day];
                if (now.month == 0) {
                    continue;
                }
                if (loginIndex >= login_dates.length) {
                    return;
                }
                Calendar loginDate = new Calendar(splitMonth(login_dates[loginIndex]), splitDay(login_dates[loginIndex]));
                if (now.month == loginDate.month && now.day == loginDate.day) {
                    if (isWeekdays(day)) {
                        tempNumberOfWeekdays++;
                        maxNumberOfWeekdays = Math.max(maxNumberOfWeekdays, tempNumberOfWeekdays);
                    }
                    loginIndex++;
                } else if (isWeekdays(day)) {
                    tempNumberOfWeekdays = 0;
                }
            }
        }
    }

    public boolean isWeekdays(int day) {
        return (day >= 1 && day <= 5);
    }
}
