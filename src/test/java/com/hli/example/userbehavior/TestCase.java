package com.hli.example.userbehavior;

public class TestCase {
    private String startDate;
    private String endDate;
    private String[] loginDates;
    private int answer;


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
