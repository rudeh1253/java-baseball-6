package baseball.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatNoException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

class NumberSetTest {

    @DisplayName("If the argument for constructor contains a digit 0, throws IllegalArgumentException")
    @ValueSource(ints = { 0, 980, 800, 702 })
    @ParameterizedTest
    void constructor_withException_rangeOfEachDigit(int testCase) {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new NumberSet(testCase));
    }

    @DisplayName("If the argument for constructor don't have only 4 digits, throws IllegalArgumentException")
    @ValueSource(ints = { 0, 1, 2, 33, 9123, 5416, Integer.MAX_VALUE })
    @ParameterizedTest
    void constructor_withException_lengthOfNumber(int testCase) {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new NumberSet(testCase));
    }

    @DisplayName("If the argument contains any duplicate, IllegalArgumentException is thrown.")
    @ValueSource(ints = { 111, 112, 335, 998, 999 })
    @ParameterizedTest
    void constructor_withException_duplicateOfDigit(int testCase) {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new NumberSet(testCase));
    }

    @DisplayName("If the argument follows the rule completely, no exception is thrown.")
    @ValueSource(ints = { 123, 124, 456, 489, 789 })
    @ParameterizedTest
    void constructor_withoutException(int testCase) {
        assertThatNoException().isThrownBy(() -> new NumberSet(testCase));
    }

    private static class TestCasePair<C, E> {
        final C testCase;
        final E expected;

        TestCasePair(C testCase, E expected) {
            this.testCase = testCase;
            this.expected = expected;
        }

        C getTestCase() {
            return this.testCase;
        }

        E getExpected() {
            return this.expected;
        }
    }

    static Stream<TestCasePair<Integer, String>> toString_equals() {
        return Stream.of(
                new TestCasePair<>(123, "123"),
                new TestCasePair<>(124, "124"),
                new TestCasePair<>(125, "125"),
                new TestCasePair<>(456, "456"),
                new TestCasePair<>(789, "789")
        );
    }

    @DisplayName("toString test")
    @MethodSource
    @ParameterizedTest
    void toString_equals(TestCasePair<Integer, String> testCasePair) {
        Integer testCase = testCasePair.getTestCase();
        String expected = testCasePair.getExpected();
        assertThat(new NumberSet(testCase).toString()).isEqualTo(expected);
    }
}