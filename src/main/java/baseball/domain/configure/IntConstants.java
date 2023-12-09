package baseball.domain.configure;

public enum IntConstants {
    NUMBER_SET_SIZE(3),
    DIGIT_MIN(1),
    DIGIT_MAX(9);

    private int value;

    IntConstants(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
