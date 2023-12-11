package baseball.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatNoException;

import baseball.config.GameMode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

class SequenceTest {

    record TestCaseWrapperForConstructor(List<String> container, GameConfig gameConfig) {
    }

    static Stream<TestCaseWrapperForConstructor> constructor_withException_containerElementDoesNotMatchGameMode() {
        return Stream.of(
                new TestCaseWrapperForConstructor(
                        List.of("1", "2", "a", "3"), new GameConfig(GameMode.MODE_NUMBER, 4)
                ),
                new TestCaseWrapperForConstructor(
                        List.of("1", "b", "a", "3"), new GameConfig(GameMode.MODE_NUMBER, 4)
                ),
                new TestCaseWrapperForConstructor(
                        List.of("c", "b", "a", "3"), new GameConfig(GameMode.MODE_NUMBER, 4)
                ),
                new TestCaseWrapperForConstructor(
                        List.of("c", "b", "a", "d"), new GameConfig(GameMode.MODE_NUMBER, 4)
                ),
                new TestCaseWrapperForConstructor(
                        List.of("1", "2", "a", "3"), new GameConfig(GameMode.MODE_ALPHABET, 4)
                ),
                new TestCaseWrapperForConstructor(
                        List.of("1", "b", "a", "3"), new GameConfig(GameMode.MODE_ALPHABET, 4)
                ),
                new TestCaseWrapperForConstructor(
                        List.of("c", "b", "a", "3"), new GameConfig(GameMode.MODE_ALPHABET, 4)
                ),
                new TestCaseWrapperForConstructor(
                        List.of("1", "2", "3", "4"), new GameConfig(GameMode.MODE_ALPHABET, 4)
                )
        );
    }

    @DisplayName("If the container contains a character which doesn't fit to the GameMode," +
            "IllegalArgumentException is thrown.")
    @MethodSource
    @ParameterizedTest
    void constructor_withException_containerElementDoesNotMatchGameMode(TestCaseWrapperForConstructor testCase) {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new Sequence(testCase.container(), testCase.gameConfig()));
    }

    static Stream<TestCaseWrapperForConstructor> constructor_withoutException() {
        return Stream.of(
                new TestCaseWrapperForConstructor(List.of("c", "b", "a", "d"), new GameConfig(GameMode.MODE_ALPHABET, 4)),
                new TestCaseWrapperForConstructor(List.of("1", "2", "3", "4"), new GameConfig(GameMode.MODE_NUMBER, 4))
        );
    }

    @DisplayName("Follows the specification. No exception is thrown.")
    @MethodSource
    @ParameterizedTest
    void constructor_withoutException(TestCaseWrapperForConstructor testCase) {
        assertThatNoException().isThrownBy(() -> new Sequence(testCase.container(), testCase.gameConfig()));
    }

    @DisplayName("Test if iterator is correctly implemented.")
    @Test
    void iterator() {
        List<String> list = List.of("a", "b", "c", "d", "e");
        Sequence testObject = new Sequence(list, new GameConfig(GameMode.MODE_ALPHABET, list.size()));
        int index = 0;
        for (String innerElement : testObject) {
            assertThat(innerElement).isEqualTo(list.get(index++));
        }
    }
}