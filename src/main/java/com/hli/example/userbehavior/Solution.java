package com.hli.example.userbehavior;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {

    public int solution(String start_date, String end_date, String[] login_dates) {
        return checkLogin(
                buildPeriod(start_date, end_date),
                login_dates
        );
    }

    private static List<Date> buildPeriod(String start_date, String end_date) {
        List<Date> period = new ArrayList<>();
        Date endDate = new Date(end_date);

        Date now = new Date(start_date);
        period.add(now);
        while (!now.equals(endDate)) {
            Date nextDate = now.getNextDate();
            period.add(nextDate);
            now = nextDate;
        }

        return period;
    }

    private static int checkLogin(List<Date> period, String[] login_dates) {
        Arrays.sort(login_dates);

        int maxNumberOfWeekdays = 0;
        int tempNumberOfWeekdays = 0;
        int loginIndex = 0;

        for (int i = 0; i < period.size(); i++) {
            if (loginIndex >= login_dates.length) {
                break;
            }
            Date now = period.get(i);
            Date loginDate = new Date(login_dates[loginIndex]);
            if (now.equals(loginDate)) {
                if (now.isWeekdays()) {
                    tempNumberOfWeekdays++;
                }
                loginIndex++;
            } else if (now.isWeekdays()) {
                maxNumberOfWeekdays = Math.max(maxNumberOfWeekdays, tempNumberOfWeekdays);
                tempNumberOfWeekdays = 0;
            }
        }

        return Math.max(maxNumberOfWeekdays, tempNumberOfWeekdays);
    }

}
