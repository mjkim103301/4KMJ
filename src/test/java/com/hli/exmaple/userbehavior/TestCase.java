package com.hli.exmaple.userbehavior;

public class TestCase {
    String startDate;
    String endDate;
    String[] loginDates;
    int answer;
    public TestCase(){}
    public TestCase(String startDate, String endDate, String[] loginDates, int answer){
        this.startDate=startDate;
        this.endDate=endDate;
        this.loginDates=loginDates;
        this.answer=answer;
    }
}
