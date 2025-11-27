package de.hypoport.europace.bowling_score_calculator.services;

import de.hypoport.europace.bowling_score_calculator.model.BonusRoll;
import de.hypoport.europace.bowling_score_calculator.model.Frame;
import de.hypoport.europace.bowling_score_calculator.model.Game;
import de.hypoport.europace.bowling_score_calculator.model.TenthFrame;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ScoreCalculatorServiceTest {

    private final BaseScoreCalculator baseScoreCalculator = new BaseScoreCalculator();
    private final ScoreCalculatorService calculator = new ScoreCalculatorService(baseScoreCalculator);

    @Test
    void testAllRegularFrames() {
        List<Frame> frames = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            frames.add(new Frame(3, 4));
        }
        Game game = new Game(frames);

        int score = calculator.calculateGameScore(game);

        assertEquals(70, score);
    }

    @Test
    void testSpareBonus() {
        List<Frame> frames = new ArrayList<>();
        frames.add(new Frame(7, 3));
        frames.add(new Frame(4, 2));

        for (int i = 2; i < 10; i++) {
            frames.add(new Frame(0, 0));
        }
        Game game = new Game(frames);

        int score = calculator.calculateGameScore(game);

        assertEquals(20, score);
    }

    @Test
    void testStrikeBonus() {
        List<Frame> frames = new ArrayList<>();
        frames.add(new Frame(10, 0));
        frames.add(new Frame(3, 5));

        for (int i = 2; i < 10; i++) {
            frames.add(new Frame(0, 0));
        }
        Game game = new Game(frames);

        int score = calculator.calculateGameScore(game);

        assertEquals(26, score);
    }

    @Test
    void testStrikeSpareMix() {
        List<Frame> frames = new ArrayList<>();
        frames.add(new Frame(10,0));
        frames.add(new Frame(7, 3));
        frames.add(new Frame(4, 2));

        for (int i = 3; i < 10; i++) {
            frames.add(new Frame(0, 0));
        }
        Game game = new Game(frames);

        int score = calculator.calculateGameScore(game);

        assertEquals(40, score);
    }

    @Test
    void testLastFrameWithOneBonusThrow() {
        List<Frame> frames = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            frames.add(new Frame(0, 0));
        }
        TenthFrame tenthFrame = new TenthFrame(10, 0, new BonusRoll(10), null);
        frames.add(tenthFrame);

        Game game = new Game(frames);

        int score = calculator.calculateGameScore(game);

        assertEquals(20, score);
    }

    @Test
    void testLastFrameWithTwoBonusThrows() {
        List<Frame> frames = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            frames.add(new Frame(0, 0));
        }
        TenthFrame tenthFrame = new TenthFrame(10, 0, new BonusRoll(10), new BonusRoll(5));
        frames.add(tenthFrame);

        Game game = new Game(frames);

        int score = calculator.calculateGameScore(game);

        assertEquals(25, score );
    }

    @Test
    void testLastFrameWithoutBonus() {
        List<Frame> frames = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            frames.add(new Frame(0, 0));
        }
        TenthFrame tenthFrame = new TenthFrame(6, 0, null, null);
        frames.add(tenthFrame);

        Game game = new Game(frames);

        int score = calculator.calculateGameScore(game);

        assertEquals(6, score);
    }

    @Test
    void testPerfectGame() {
        List<Frame> frames = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            frames.add(new Frame(10, 0));
        }
        TenthFrame tenthFrame = new TenthFrame(10, 0, new BonusRoll(10), new BonusRoll(10));
        frames.add(tenthFrame);

        Game game = new Game(frames);

        int score = calculator.calculateGameScore(game);

        assertEquals(300, score);
    }

}
