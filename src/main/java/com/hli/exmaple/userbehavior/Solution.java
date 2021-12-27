package com.hli.exmaple.userbehavior;

import java.util.*;

class Solution {
    class Node{
        int month;
        int day;
        public Node(){}
        public Node(int month, int day){
            this.month=month;
            this.day=day;
        }
    }
    Node[][]calendar;
    boolean[][]visit;
    String []day={"SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT"};
    int[]lastDay={0,31,28,31,30,31,30,31,31,30,31,30,31};
    int max, startMonth, startDay, endMonth, endDay;
    int startDayOfWeek;
    int size;

    public int solution(String start_date, String end_date, String[] login_dates) {
        int answer = -1;
        max=0;
        startMonth=Integer.parseInt(start_date.substring(0,2));
        startDay=Integer.parseInt(start_date.substring(3,5));
        String temp=start_date.substring(6);
        for(int i=0;i<7;i++){
            if(day[i].equals(temp)){
                startDayOfWeek=i;
                break;
            }
        }
        //System.out.println(startMonth+" "+startDay+" "+temp+" "+startDayOfWeek);
        endMonth=Integer.parseInt(end_date.substring(0,2));
        endDay=Integer.parseInt(end_date.substring(3,5));
        //1달에 넉넉잡아 5주이므로 5를 곱함
        size=(endMonth-startMonth+1)*5;
        calendar=new Node[size][7];
        visit=new boolean[size][7];

        for(int y=0;y<size;y++){
            for(int x=0;x<7;x++){
                calendar[y][x]=new Node();
            }
        }
        // System.out.println(endMonth+" "+endDay);
        setCalendar();
        // printCalendar();
        checkLogin(login_dates);
        //  printVisit();
        answer=max;
        return answer;
    }

    public void setCalendar(){
        int month=startMonth;
        int day=startDay;
        for(int x=startDayOfWeek;x<7;x++){
            calendar[0][x]=new Node(month, day);

            if(month==endMonth && day==endDay){
                return;
            }
            day++;
            if(day>lastDay[month]){
                month++;
                day=1;
            }

        }

        for(int y=1;y<size;y++){
            for(int x=0;x<7;x++){
                calendar[y][x]=new Node(month, day);

                if(month==endMonth && day==endDay){
                    return;
                }
                day++;
                if(day>lastDay[month]){
                    month++;
                    day=1;
                }

            }
        }
    }

    public void checkLogin(String[] login_dates){
        Arrays.sort(login_dates);
        //  System.out.println(Arrays.toString(login_dates));
        int tempCnt=0;
        int loginIndex=0;
        int loginMonth=Integer.parseInt(login_dates[loginIndex].substring(0,2));
        int loginDay=Integer.parseInt(login_dates[loginIndex++].substring(3,5));
        int loginSize=login_dates.length;
        // System.out.println("loginMonth: "+loginMonth+" loginDay: "+loginDay);
        for(int y=0;y<size;y++){
            for(int x=0;x<7;x++){
                Node now=calendar[y][x];
                if(now.month==loginMonth && now.day==loginDay){
                    visit[y][x]=true;
                    if(x>=1 && x<=5){
                        tempCnt++;
                        if(max<tempCnt){
                            max=tempCnt;
                        }
                        // System.out.println("temp++ "+ loginMonth+" "+loginDay+" "+tempCnt);
                    }
                    if(loginIndex>=loginSize){
                        return;
                    }
                    loginMonth=Integer.parseInt(login_dates[loginIndex].substring(0,2));
                    loginDay=Integer.parseInt(login_dates[loginIndex++].substring(3,5));

                }else if(x>=1 && x<=5){
                    tempCnt=0;
                }
            }
        }
    }

    public void printCalendar(){
        for(int y=0;y<size;y++){
            for(int x=0;x<7;x++){
                System.out.print(calendar[y][x].month+"/"+calendar[y][x].day+" ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public void printVisit(){
        for(int y=0;y<size;y++){
            for(int x=0;x<7;x++){
                System.out.print(visit[y][x]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
