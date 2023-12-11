package baseball.domain;

import baseball.config.CharacterSet;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class Sequence implements Iterable<String> {
    private final List<String> container;

    public Sequence(List<String> container, GameConfig gameConfig) {
        this.container = Collections.unmodifiableList(container);
        validate(gameConfig);
    }

    private void validate(GameConfig gameConfig) {
        SequenceValidator.validateCharacterDomain(this.container, gameConfig);
        SequenceValidator.validateLengthOfSequence(this.container, gameConfig);
    }

    private static class SequenceValidator {

        public static void validateCharacterDomain(List<String> container, GameConfig gameConfig) {
            CharacterSet characterSet = CharacterSet.of(gameConfig.gameMode());
            for (String element : container) {
                if (!characterSet.contains(element)) {
                    throw new IllegalArgumentException(); // TODO: insert error message
                }
            }
        }

        public static void validateLengthOfSequence(List<String> container, GameConfig gameConfig) {
            if (container.size() != gameConfig.length()) {
                throw new IllegalArgumentException(); // TODO: insert error message
            }
        }
    }

    public boolean isStrike(String character, int position) {
        return this.container.get(position).equals(character);
    }

    public boolean isBall(String character, int position) {
        int size = this.container.size();
        for (int i = position + 1; i < position + size; i++) {
            if (this.container.get(i % size).equals(character)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<String> iterator() {
        return new SequenceIterator<>(this.container);
    }

    private static class SequenceIterator<E> implements Iterator<E> {
        private final List<E> toIterate;
        private int currentIndex = 0;

        public SequenceIterator(List<E> sequence) {
            this.toIterate = sequence;
        }

        @Override
        public boolean hasNext() {
            return toIterate.size() > currentIndex;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException(); // TODO: add error message
            }
            return this.toIterate.get(currentIndex++);
        }
    }
}
