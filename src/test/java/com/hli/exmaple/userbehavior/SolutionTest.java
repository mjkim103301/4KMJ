package com.hli.exmaple.userbehavior;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;


class SolutionTest {
    static ArrayList<TestCase> testCases=new ArrayList<>();
    static Solution solution=new Solution();

    @BeforeAll
    static void setTestCase(){
        testCases.add(new TestCase("05/04 MON", "05/30"
                , new String[]{"05/26", "05/25", "05/27", "05/10", "05/11", "05/23", "05/22", "05/21", "05/06", "05/09", "05/07", "05/08"}
                , 5));
        testCases.add(new TestCase("05/27 SUN", "06/16"
                , new String[]{"05/31", "05/30", "06/01", "06/04", "06/07", "06/06", "06/09", "06/08", "06/13", "06/14", "06/10"}
                , 4));
    }

    @Test
    @DisplayName("테스트케이스로 답이 맞는지 테스트")
    void testSolution() {
        for(TestCase item: testCases){
            assertEquals(item.answer, solution.solution(item.startDate, item.endDate, item.loginDates));
        }
    }
}