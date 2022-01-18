package com.hli.example.userbehavior;

public class TestCase {
    private final String startDate;
    private final String endDate;
    private final String[] loginDates;
    private final int answer;


    public TestCase(String startDate, String endDate, String[] loginDates, int answer) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.loginDates = loginDates;
        this.answer = answer;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String[] getLoginDates() {
        return loginDates;
    }

    public int getAnswer() {
        return answer;
    }
}
