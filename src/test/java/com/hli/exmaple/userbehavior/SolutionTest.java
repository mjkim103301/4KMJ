package com.hli.exmaple.userbehavior;

import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SolutionTest {
    private ArrayList<TestCase> testCases=new ArrayList<>();
    private Solution solution=new Solution();
    private final int NUMBER_OF_DAYS_IN_A_YEAR=365;
    private final int[] LAST_DAY_OF_THE_MONTH={0,31,28,31,30,31,30,31,31,30,31,30,31};

    @BeforeEach
    void setTestCasesClear(){
        testCases.clear();
    }

    @AfterEach
    void testRun(){
        for(TestCase item: testCases){
            assertEquals(item.answer, solution.solution(item.startDate, item.endDate, item.loginDates));
        }
    }

    @Test
    @DisplayName("케이스 1: 주말까지 합쳐서 연속으로 방문한 날이 가장 값이 높지만 답은 주말에 가지 않은 연속된 날일 때")
    void testCase1() {
        testCases.add(new TestCase("05/04 MON", "05/30"
                , new String[]{"05/26", "05/25", "05/27", "05/10", "05/11", "05/23", "05/22", "05/21", "05/06", "05/09", "05/07", "05/08"}
                , 5));
    }

    @Test
    @DisplayName("케이스 2: 달이 넘어가면서 주말은 가지 않은 연속된 날")
    void testCase2() {
        testCases.add(new TestCase("05/27 SUN", "06/16"
                , new String[]{"05/31", "05/30", "06/01", "06/04", "06/07", "06/06", "06/09", "06/08", "06/13", "06/14", "06/10"}
                , 4));
    }

    @Test
    @DisplayName("케이스 3: 접속기록이 1개인데 그게 주말일 때")
    void testCase3() {
        testCases.add(new TestCase("05/27 SUN", "06/16"
                , new String[]{"06/02"}
                , 0));
        testCases.add(new TestCase("05/27 SUN", "06/16"
                , new String[]{"06/03"}
                , 0));
    }

    @Test
    @DisplayName("케이스 4: 접속기록이 1개인데 평일일 때")
    void testCase4() {
        testCases.add(new TestCase("05/27 SUN", "06/16"
                , new String[]{"05/28"}
                , 1));
        testCases.add(new TestCase("05/27 SUN", "06/16"
                , new String[]{"05/29"}
                , 1));
        testCases.add(new TestCase("05/27 SUN", "06/16"
                , new String[]{"05/30"}
                , 1));
        testCases.add(new TestCase("05/27 SUN", "06/16"
                , new String[]{"05/31"}
                , 1));
        testCases.add(new TestCase("05/27 SUN", "06/16"
                , new String[]{"06/01"}
                , 1));
    }

    @Test
    @DisplayName("케이스 5: 주말만 접속했을 때")
    void testCase5() {
        testCases.add(new TestCase("05/26 SAT", "06/17"
                , new String[]{"06/17","06/02", "06/03","05/26","05/27",  "06/16",  "06/09", "06/10"}
                , 0));
    }

    @Test
    @DisplayName("케이스 6: 연속으로 방문한 날이 모두 달을 넘어가는 날들일 때")
    void testCase6() {
        testCases.add(new TestCase("01/01 FRI", "12/31"
                , new String[]{"01/31", "02/01", "02/02", "05/31", "06/01", "09/30", "10/01", "10/02", "10/04"}
                , 3));
    }

    @Test
    @DisplayName("케이스 7: 2021년 365일 모두 방문했을 때")
    void testCase7() {
        String []loginDates=new String[NUMBER_OF_DAYS_IN_A_YEAR];

        int index=0;
        for(int month=1;month<=12;month++){

            String date="";
            if(month<10){
                date+="0"+month;
            }else{
                date+=month;
            }
            date+="/";

            for(int day=1;day<=LAST_DAY_OF_THE_MONTH[month];day++){
                if(day<10){
                    date+="0"+day;
                }else{
                    date+=day;
                }
                loginDates[index++]=date;
                date=date.substring(0,3);
            }
        }

        System.out.println(Arrays.toString(loginDates));
        testCases.add(new TestCase("01/01 FRI", "12/31"
                ,loginDates
                , 261));
    }
}