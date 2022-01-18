package com.hli.example.userbehavior;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
        Date nowDate = new Date(start_date);
        period.add(nowDate);
        while (!nowDate.equals(endDate)) {
            Date nextDate = nowDate.getNextDate();
            period.add(nextDate);
            nowDate = nextDate;
        }

        return period;
    }

    private static int checkLogin(List<Date> period, String[] login_dates) {
        int maxNumberOfWeekdays = 0;
        int tempNumberOfWeekdays = 0;
        int loginIndex = 0;

        List<String> sortedLoginDates = Arrays.stream(login_dates)
                .sorted()
                .collect(Collectors.toList());

        for (Date now : period) {
            if (loginIndex >= sortedLoginDates.size()) {
                break;
            }
            Date loginDate = new Date(sortedLoginDates.get(loginIndex));
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
