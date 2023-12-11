package baseball.domain;

import java.util.List;

public class GameManager {
    private final GameRecorder gameRecorder;
    private final GameConfig gameConfig;
    private Sequence<String> correctAnswer;

    public GameManager(GameRecorder gameRecorder, GameConfig gameConfig, Sequence<String> correctAnswer) {
        this.gameRecorder = gameRecorder;
        this.gameConfig = gameConfig;
        this.correctAnswer = correctAnswer;
    }

    public void incrementNumOfTry() {
        this.gameRecorder.incrementTry();;
    }

    public int getNumOfTry() {
        return this.gameRecorder.getNumOfTry();
    }

    public void recordRecentTry(Sequence<String> guess) {
        guess.recordCurrentSequence(this.gameRecorder);
    }

    public List<List<String>> getTryHistory() {
        return this.gameRecorder.getTryHistory();
    }

    public InningResult compareToCorrectAnswer(Sequence<String> guess) {
        int strike = 0;
        int ball = 0;
        int index = 0;
        for (String expectElement : guess) {
            if (this.correctAnswer.isStrike(expectElement, index++)) {
                strike++;
            }
        }
        index = 0;
        for (String expectElement : guess) {
            if (this.correctAnswer.isBall(expectElement, index++)) {
                ball++;
            }
        }
        return new InningResult(strike, ball);
    }
}
