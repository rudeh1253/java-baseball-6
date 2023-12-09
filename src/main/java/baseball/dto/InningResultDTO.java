package baseball.dto;

import baseball.domain.InningResult;

public class InningResultDTO {
    private final int strike;
    private final int ball;

    private InningResultDTO(int strike, int ball) {
        this.strike = strike;
        this.ball = ball;
    }

    public static InningResultDTO from(InningResult inningResult) {
        return new InningResultDTO(inningResult.getStrike(), inningResult.getBall());
    }

    public int getStrike() {
        return this.strike;
    }

    public int getBall() {
        return this.ball;
    }
}
