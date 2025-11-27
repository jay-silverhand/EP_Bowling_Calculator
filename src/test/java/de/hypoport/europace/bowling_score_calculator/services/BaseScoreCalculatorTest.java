package de.hypoport.europace.bowling_score_calculator.services;

import de.hypoport.europace.bowling_score_calculator.model.Frame;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BaseScoreCalculatorTest {

    private static final BaseScoreCalculator calc = new BaseScoreCalculator();

    private  static final List<Frame> testFrames = new ArrayList();

    @BeforeAll
     static void initTestFrames(){
        testFrames.add(new Frame(3, 5));
        testFrames.add(new Frame(3, 7));
        testFrames.add(new Frame(10, 0));
        testFrames.add(new Frame(0, 10));
        calc.setBaseScores(testFrames);
    }

    @Test
    void testBaseScoreCalculatedCorrectly(){
    assertEquals(8, testFrames.get(0).getScore());
    }

    @Test
    void testBaseScoreCalculatedCorrectly2(){
        assertEquals(10, testFrames.get(1).getScore());
    }

    @Test
    void testBaseScoreCalculatedCorrectly3(){
        assertEquals(10, testFrames.get(2).getScore());
    }

    @Test
    void testBaseScoreCalculatedCorrectly4(){
        assertEquals(10, testFrames.get(3).getScore());
    }

}
