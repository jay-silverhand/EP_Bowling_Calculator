package de.hypoport.europace.bowling_score_calculator.model;

public class BonusRoll {

    private boolean isStrike;
    private int score;

    public BonusRoll(int score) {
        this.isStrike = score == 10;
        this.score = score;
    }

    public boolean isStrike() {
        return isStrike;
    }

    public int getScore() {
        return score;
    }
}
