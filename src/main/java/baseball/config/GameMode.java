package baseball.config;

public enum GameMode {
    MODE_NUMBER(1),
    MODE_ALPHABET(2);

    private final int value;

    GameMode(int value) {
        this.value = value;
    }

    public int get() {
        return this.value;
    }
}
