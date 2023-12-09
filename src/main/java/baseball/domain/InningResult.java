package baseball.domain;

public class InningResult {
    private final int strike;
    private final int ball;

    private InningResult(int strike, int ball) {
        this.strike = strike;
        this.ball = ball;
    }

    public static InningResult of(NumberSet set1, NumberSet set2) {
        int strike = 0;
        int ball = 0;
        for (int i = 0; i < set1.size(); i++) {
            int digit = set1.getDigit(i);
            if (set2.isStrike(digit, i)) {
                strike++;
            }
            if (set2.isBall(digit, i)) {
                ball++;
            }
        }
        return new InningResult(strike, ball);
    }

    public int getStrike() {
        return this.strike;
    }

    public int getBall() {
        return this.ball;
    }
}
