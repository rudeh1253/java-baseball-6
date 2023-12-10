package baseball.domain;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatNoException;

import baseball.config.CharacterSet;
import baseball.config.GameMode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

class SequenceTest {

    record TestCaseWrapper(List<String> container, CharacterSet characterSet) {
    }

    static Stream<TestCaseWrapper> constructor_withException_containerElementDoesNotMatchGameMode() {
        return Stream.of(
                new TestCaseWrapper(List.of("1", "2", "a", "3"), CharacterSet.of(GameMode.MODE_NUMBER)),
                new TestCaseWrapper(List.of("1", "b", "a", "3"), CharacterSet.of(GameMode.MODE_NUMBER)),
                new TestCaseWrapper(List.of("c", "b", "a", "3"), CharacterSet.of(GameMode.MODE_NUMBER)),
                new TestCaseWrapper(List.of("c", "b", "a", "d"), CharacterSet.of(GameMode.MODE_NUMBER)),
                new TestCaseWrapper(List.of("1", "2", "a", "3"), CharacterSet.of(GameMode.MODE_ALPHABET)),
                new TestCaseWrapper(List.of("1", "b", "a", "3"), CharacterSet.of(GameMode.MODE_ALPHABET)),
                new TestCaseWrapper(List.of("c", "b", "a", "3"), CharacterSet.of(GameMode.MODE_ALPHABET)),
                new TestCaseWrapper(List.of("1", "2", "3", "4"), CharacterSet.of(GameMode.MODE_ALPHABET))
        );
    }

    @DisplayName("If the container contains a character which doesn't fit to the GameMode," +
            "IllegalArgumentException is thrown.")
    @MethodSource
    @ParameterizedTest
    void constructor_withException_containerElementDoesNotMatchGameMode(TestCaseWrapper testCase) {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new Sequence(testCase.container(), testCase.characterSet()));
    }

    static Stream<TestCaseWrapper> constructor_withoutException() {
        return Stream.of(
                new TestCaseWrapper(List.of("c", "b", "a", "d"), CharacterSet.of(GameMode.MODE_ALPHABET)),
                new TestCaseWrapper(List.of("1", "2", "3", "4"), CharacterSet.of(GameMode.MODE_NUMBER))
        );
    }

    @DisplayName("Follows the specification. No exception is thrown.")
    @MethodSource
    @ParameterizedTest
    void constructor_withoutException(TestCaseWrapper testCase) {
        assertThatNoException().isThrownBy(() -> new Sequence(testCase.container(), testCase.characterSet()));
    }
}