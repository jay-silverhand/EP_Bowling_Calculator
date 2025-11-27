package de.hypoport.europace.bowling_score_calculator.model;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private List<Frame> frames = new ArrayList<>(10);

    private int totalScore;

    public Game(List<Frame> frames) {
        this.frames = frames;
    }

    public void addFrame(Frame frame){
        frames.add(frame);
    }

    public List<Frame> getFrames() {
        return frames;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }
}
