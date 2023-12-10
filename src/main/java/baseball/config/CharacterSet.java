package baseball.config;

import java.util.HashSet;
import java.util.Set;

public class CharacterSet {
    private final Set<String> container;

    private CharacterSet(Set<String> container) {
        this.container = container;
    }

    public static CharacterSet of(GameMode gameMode) {
        if (gameMode == GameMode.MODE_NUMBER) {
            return new CharacterSet(getNumberSet());
        }
        return new CharacterSet(getAlphabetSet());
    }

    private static Set<String> getNumberSet() {
        return generateContainer('1', '9');
    }

    private static Set<String> getAlphabetSet() {
        return generateContainer('a', 'z');
    }

    private static Set<String> generateContainer(char from, int to) {
        Set<String> container = new HashSet<>();
        for (char element = from; element <= to; element++) {
            container.add(String.valueOf(element));
        }
        return container;
    }

    public boolean contains(String element) {
        return this.container.contains(element);
    }
}
