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

        assertEquals(70, score, "Alle offenen Frames korrekt berechnet");
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

        assertEquals(20, score, "Spare Bonus korrekt berechnet");
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

        assertEquals(26, score, "Strike Bonus korrekt berechnet");
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

        assertEquals(40, score, "Strike & Spare Mix Boni korrekt berechnet");
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

        assertEquals(20, score, "Letzten Frame mit Bonus korrekt berechnet");
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

        assertEquals(25, score, "Letzten Frame mit zwei Boni korrekt berechnet");
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

        assertEquals(6, score, "Letzten Frame ohne Bonus korrekt berechnet");
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

        assertEquals(300, score, "Perfektes Spiel mit Bonus-Frame & Bonus-Roll korrekt berechnet");
    }

}
