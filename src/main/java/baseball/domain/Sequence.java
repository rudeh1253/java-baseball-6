package baseball.domain;

import baseball.config.CharacterSet;

import java.util.Collections;
import java.util.List;

public class Sequence {
    private final List<String> container;

    public Sequence(List<String> container, CharacterSet characterSet) {
        this.container = Collections.unmodifiableList(container);
        validate(characterSet);
    }

    private void validate(CharacterSet characterSet) {
        SequenceValidator.validateCharacterDomain(this.container, characterSet);
    }

    private static class SequenceValidator {

        public static <E> void validateCharacterDomain(List<String> container, CharacterSet characterSet) {
            for (String element : container) {
                if (!characterSet.contains(element)) {
                    throw new IllegalArgumentException(); // TODO: insert error message
                }
            }
        }
    }

    public boolean isStrike(String character, int position) {
        return this.container.get(position).equals(character);
    }

    public boolean isBall(String character, int position) {
        int size = this.container.size();
        for (int i = position + 1; i < position + size; i++) {
            if (this.container.get(i).equals(character)) {
                return true;
            }
        }
        return false;
    }
}
