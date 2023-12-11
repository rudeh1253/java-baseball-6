package baseball.domain;

import baseball.config.CharacterSet;

import java.util.ArrayList;
import java.util.List;

public class RandomSequence<E extends String> extends Sequence<E> {

    private RandomSequence(List<E> container, GameConfig gameConfig) {
        super(container, gameConfig);
    }

    public static Sequence<String> genereateRandomSequence(GameConfig gameConfig) {
        CharacterSet characterSet = CharacterSet.of(gameConfig.gameMode());
        int length = gameConfig.length();
        List<String> container = new ArrayList<>();
        while (container.size() <= length) {
            String randomCharacter = characterSet.getRandomCharacter();
            if (!container.contains(randomCharacter)) {
                container.add(randomCharacter);
            }
        }
        return new Sequence<>(container, gameConfig);
    }
}
