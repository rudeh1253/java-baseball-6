package baseball.view.config;

public enum OutputConstants {
    INIT_MESSAGE("숫자 야구 게임을 시작합니다."),
    PROMPT_INPUT("숫자를 입력해 주세요 : "),
    BALL("볼"),
    STRIKE("스트라이크"),
    NOTHING("낫싱"),
    SPACE(" "),
    END_OF_GAME("3개의 숫자를 모두 맞히셨습니다! 게임 종료"),
    PROMPT_RESTART("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");

    private String message;

    OutputConstants(String message) {
        this.message = message;
    }

    public String get() {
        return this.message;
    }
}
