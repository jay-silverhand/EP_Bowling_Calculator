package de.hypoport.europace.bowling_score_calculator.services;

import de.hypoport.europace.bowling_score_calculator.model.Frame;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BaseScoreCalculator {

    public void setBaseScores(List<Frame> frames) {
        frames.forEach(frame -> {
            frame.setScore(frame.getRoll1() + frame.getRoll2());
        });
    }
}
