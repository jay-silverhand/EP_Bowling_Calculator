package de.hypoport.europace.bowling_score_calculator.services;

import de.hypoport.europace.bowling_score_calculator.model.BonusRoll;
import de.hypoport.europace.bowling_score_calculator.model.Frame;
import de.hypoport.europace.bowling_score_calculator.model.TenthFrame;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FrameValidatorTest {

    @Test
    void testScoresTooHigh(){

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Frame(12, 0));

        assertEquals("Only values between 0 and 10 are allowed for a roll.", exception.getMessage());
    }

    @Test
    void testScoresTooHigh2(){

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Frame(3, 11));

        assertEquals("Only values between 0 and 10 are allowed for a roll.", exception.getMessage());
    }

    @Test
    void testScoresTooHigh3(){

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Frame(12, 15));

        assertEquals("Only values between 0 and 10 are allowed for a roll.", exception.getMessage());
    }

    @Test
    void testScoresTooHigh4(){

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Frame(0, 15));

        assertEquals("Only values between 0 and 10 are allowed for a roll.", exception.getMessage());
    }

    @Test
    void testScoresNegative(){

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Frame(-4, 0));

        assertEquals("Only values between 0 and 10 are allowed for a roll.", exception.getMessage());
    }

    @Test
    void testScoresNegative2(){

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Frame(2, -1));

        assertEquals("Only values between 0 and 10 are allowed for a roll.", exception.getMessage());
    }

    @Test
    void testScoresNegative3(){

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Frame(-4, -6));

        assertEquals("Only values between 0 and 10 are allowed for a roll.", exception.getMessage());
    }

    @Test
    void testTotalScoreTooHigh(){

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Frame(10, 2));

        assertEquals("The total frame score cannot be higher than 10.", exception.getMessage());
    }

    @Test
    void testTotalScoreTooHigh2(){

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Frame(5, 8));

        assertEquals("The total frame score cannot be higher than 10.", exception.getMessage());
    }

    @Test
    void testBonusRollScoreTooHigh(){

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new TenthFrame(3, 7, new BonusRoll(12), null));

        assertEquals("Only values between 0 and 10 are allowed for a roll.", exception.getMessage());
    }

    @Test
    void testBonusRollScoreTooHigh2(){

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new TenthFrame(3, 7, new BonusRoll(5), new BonusRoll(12)));

        assertEquals("Only values between 0 and 10 are allowed for a roll.", exception.getMessage());
    }

    @Test
    void testBonusRollScoreTooHigh3(){

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new TenthFrame(3, 7, new BonusRoll(11), new BonusRoll(12)));

        assertEquals("Only values between 0 and 10 are allowed for a roll.", exception.getMessage());
    }

    @Test
    void testBonusRollScoreNegative(){

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new TenthFrame(3, 7, new BonusRoll(-3), null));

        assertEquals("Only values between 0 and 10 are allowed for a roll.", exception.getMessage());
    }

    @Test
    void testBonusRollScoreNegative2(){

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new TenthFrame(3, 7, null, new BonusRoll(-3)));

        assertEquals("Only values between 0 and 10 are allowed for a roll.", exception.getMessage());
    }

    @Test
    void testBonusRollScoreNegative3(){

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new TenthFrame(3, 7, new BonusRoll(-7), new BonusRoll(-3)));

        assertEquals("Only values between 0 and 10 are allowed for a roll.", exception.getMessage());
    }

    @Test
    void testCorrectInput(){
        Frame testFrame = new Frame(2,5);
        assertEquals(2, testFrame.getRoll1());
        assertEquals(5, testFrame.getRoll2());
    }

    @Test
    void testSpareFlagSetCorrectly(){
        Frame testFrame = new Frame(2,8);
        assertEquals(true, testFrame.isSpare());
    }

    @Test
    void testStrikeFlagSetCorrectly(){
        Frame testFrame = new Frame(10,0);
        assertEquals(true, testFrame.isStrike());
    }
}
