package baseball.config;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.HashSet;
import java.util.Set;

public class CharacterSet {
    private final Set<String> container;

    private CharacterSet(Set<String> container) {
        this.container = container;
    }

    private static final CharacterSet NUMBER_SET;
    private static final CharacterSet ALPHABET_SET;

    static {
        NUMBER_SET = new CharacterSet(getNumberSet());
        ALPHABET_SET = new CharacterSet(getAlphabetSet());
    }

    public static CharacterSet of(GameMode gameMode) {
        if (gameMode == GameMode.MODE_NUMBER) {
            return NUMBER_SET;
        }
        return ALPHABET_SET;
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

    public String getRandomCharacter() {
        int randomIndex = Randoms.pickNumberInRange(0, this.container.size() - 1);
        return this.container.stream().skip(randomIndex).findFirst().orElseThrow();
    }
}
