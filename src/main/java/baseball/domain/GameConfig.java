package baseball.domain;

import baseball.config.GameMode;

public record GameConfig(GameMode gameMode, int length) {
}
