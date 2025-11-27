package de.hypoport.europace.bowling_score_calculator.model;

public class Frame {

    private int score;
    private int roll1;
    private int roll2;
    private boolean isSpare;
    private boolean isStrike;

    public Frame(int roll1, int roll2) {
        validateRolls(roll1, roll2);
        this.roll1 = roll1;
        this.roll2 = roll2;
        this.isStrike = roll1 == 10;
        this.isSpare = !isStrike && (roll1 + roll2 == 10);
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getRoll1() {
        return roll1;
    }

    public void setRoll1(int roll1) {
        this.roll1 = roll1;
    }

    public int getRoll2() {
        return roll2;
    }

    public void setRoll2(int roll2) {
        this.roll2 = roll2;
    }

    public boolean isSpare() {
        return isSpare;
    }

    public void setSpare(boolean spare) {
        isSpare = spare;
    }

    public boolean isStrike() {
        return isStrike;
    }

    public void setStrike(boolean strike) {
        isStrike = strike;
    }

    private void validateRolls(int roll1, int roll2) {

        if ((roll1 < 0 || roll2 < 0) || (roll1 > 10 || roll2 > 10)) {
            throw new IllegalArgumentException("Only values between 0 and 10 are allowed for a roll.");
        }

        if (roll1 + roll2 > 10) {
            throw new IllegalArgumentException("The total frame score cannot be higher than 10.");
        }

        if (roll1 == 10 && roll2 != 0) {
            throw new IllegalArgumentException("On strike the second roll value must be 0.");
        }
    }
}
