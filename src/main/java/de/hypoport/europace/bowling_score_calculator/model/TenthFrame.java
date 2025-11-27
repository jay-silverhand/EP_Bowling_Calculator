package de.hypoport.europace.bowling_score_calculator.model;

public class TenthFrame extends Frame {

    private BonusRoll firstBonusRoll;
    private BonusRoll secondBonusRoll;

    public TenthFrame(int roll1, int roll2, BonusRoll firstBonusRoll, BonusRoll secondBonusRoll) {
        super(roll1, roll2);
        validateTenthFrameRules(roll1, roll2, firstBonusRoll, secondBonusRoll);
        this.firstBonusRoll = firstBonusRoll;
        this.secondBonusRoll = secondBonusRoll;
    }


    private void validateTenthFrameRules(int roll1, int roll2,
                                         BonusRoll firstBonusRoll, BonusRoll secondBonusRoll) {

        if (!isStrike() && !isSpare()) {
            if (firstBonusRoll != null || secondBonusRoll != null) {
                throw new IllegalArgumentException("No bonus rolls allowed without strike or spare.");
            }
            return;
        }

        if (firstBonusRoll != null && (firstBonusRoll.getScore() < 0 || firstBonusRoll.getScore() > 10))
            throw new IllegalArgumentException("Only values between 0 and 10 are allowed for a roll.");

        if (secondBonusRoll != null && (secondBonusRoll.getScore() < 0 || secondBonusRoll.getScore() > 10))
            throw new IllegalArgumentException("Only values between 0 and 10 are allowed for a roll.");

        if (isSpare() && secondBonusRoll != null)
            throw new IllegalArgumentException("Only one bonus roll allowed if spare.");
    }


    public BonusRoll getFirstBonusRoll() {
        return firstBonusRoll;
    }

    public BonusRoll getSecondBonusRoll() {
        return secondBonusRoll;
    }
}
