package baseball.config;

import java.util.HashSet;
import java.util.Set;

public class CharacterSet {
    private final Set<Object> container;

    private CharacterSet(Set<Object> container) {
        this.container = container;
    }

    public static CharacterSet of(GameMode gameMode) {
        if (gameMode == GameMode.MODE_NUMBER) {
            return new CharacterSet(getNumberSet());
        }
        return new CharacterSet(getAlphabetSet());
    }

    private static Set<Object> getNumberSet() {
        return generateContainer(1, 9);
    }

    private static Set<Object> getAlphabetSet() {
        return generateContainer('a', 'z');
    }

    private static Set<Object> generateContainer(int from, int to) {
        Set<Object> container = new HashSet<>();
        for (int element = from; element <= to; element++) {
            container.add(element);
        }
        return container;
    }

    public boolean contains(Object element) {
        return this.container.contains(element);
    }
}
