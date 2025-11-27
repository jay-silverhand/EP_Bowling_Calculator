package de.hypoport.europace.bowling_score_calculator.services;

import de.hypoport.europace.bowling_score_calculator.model.Frame;
import de.hypoport.europace.bowling_score_calculator.model.Game;
import de.hypoport.europace.bowling_score_calculator.model.TenthFrame;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ScoreCalculatorService {

    private BaseScoreCalculator baseScoreCalc;

    public ScoreCalculatorService(BaseScoreCalculator baseScoreCalc) {
        this.baseScoreCalc = baseScoreCalc;
    }

    public int calculateGameScore(Game game) {

        List<Frame> frames = game.getFrames();

        baseScoreCalc.setBaseScores(frames);

        for (int index = 0; index < frames.size(); index++) {
            Frame frame = frames.get(index);

            int bonus = 0;

            bonus = handleSpare(frame, index, frames, bonus);
            bonus = handleStrike(frame, index, frames, bonus);
            bonus = handleTenthFrame(frame, bonus);
            frame.setScore(frame.getScore() + bonus);
        }

        return frames.stream().mapToInt(Frame::getScore).sum();
    }

    private int handleTenthFrame(Frame frame, int bonus) {
        if (frame instanceof TenthFrame tenthFrame) {
            bonus += tenthFrame.getFirstBonusRoll() != null ? tenthFrame.getFirstBonusRoll().getScore() : 0;
            bonus += tenthFrame.getSecondBonusRoll() != null ? tenthFrame.getSecondBonusRoll().getScore() : 0;
        }
        return bonus;
    }

    private int handleStrike(Frame frame, int index, List<Frame> frames, int bonus) {
        if (frame.isStrike()) {
            if (index + 1 < frames.size()) {
                Frame next = frames.get(index + 1);

                if (next.isStrike()) {
                    bonus += next.getRoll1();

                    if (index + 2 < frames.size()) {
                        bonus += frames.get(index + 2).getRoll1();
                    } else if (next instanceof TenthFrame tenthFrame) {
                        bonus += tenthFrame.getFirstBonusRoll().getScore();
                    }
                } else {
                    bonus += next.getRoll1() + next.getRoll2();
                }
            }
        }
        return bonus;
    }

    private int handleSpare(Frame frame, int index, List<Frame> frames, int bonus) {
        if (frame.isSpare()) {
            if (index + 1 < frames.size()) {
                bonus = frames.get(index + 1).getRoll1();
            }
        }
        return bonus;
    }
}

