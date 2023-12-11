package baseball.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameRecorder {
    private int numOfTry;
    private List<String> tryHistory;

    public GameRecorder() {
        this.numOfTry = 0;
        this.tryHistory = new ArrayList<>();
    }

    public void incrementTry() {
        this.numOfTry++;
    }

    public void addTry(String oneTry) {
        this.tryHistory.add(oneTry);
    }

    public int getNumOfTry() {
        return this.numOfTry;
    }

    public List<String> getTryHistory() {
        return Collections.unmodifiableList(this.tryHistory);
    }
}
