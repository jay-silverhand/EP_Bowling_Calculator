package de.hypoport.europace.bowling_score_calculator.model;

public class TenthFrame extends Frame {

    private BonusRoll firstBonusRoll;
    private BonusRoll secondBonusRoll;

    public TenthFrame(int roll1, int roll2, BonusRoll firstBonusRoll, BonusRoll secondBonusRoll) {
        super(roll1, roll2);
        this.firstBonusRoll = validateBonusRoll(firstBonusRoll);
        this.secondBonusRoll = validateBonusRoll(secondBonusRoll);
    }

    private BonusRoll validateBonusRoll(BonusRoll bonusRoll) {

        if(bonusRoll != null && (bonusRoll.getScore() < 0 || bonusRoll.getScore() > 10)){
            throw new IllegalArgumentException("Only values between 0 and 10 are allowed for a roll.");
        }

        return bonusRoll;
    }

    public BonusRoll getFirstBonusRoll() {
        return firstBonusRoll;
    }

    public BonusRoll getSecondBonusRoll() {
        return secondBonusRoll;
    }
}
