package baseball.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class InningResultTest {

    record TestCaseWrapper(NumberSet set1, NumberSet set2, int expectedStrike, int expectedBall) {
    }

    static Stream<TestCaseWrapper> of_test() {
        return Stream.of(
                new TestCaseWrapper(new NumberSet(123), new NumberSet(123), 3, 0),
                new TestCaseWrapper(new NumberSet(123), new NumberSet(124), 2, 0),
                new TestCaseWrapper(new NumberSet(123), new NumberSet(145), 1, 0),
                new TestCaseWrapper(new NumberSet(123), new NumberSet(456), 0, 0),
                new TestCaseWrapper(new NumberSet(123), new NumberSet(321), 1, 2),
                new TestCaseWrapper(new NumberSet(123), new NumberSet(312), 0, 3),
                new TestCaseWrapper(new NumberSet(123), new NumberSet(412), 0, 2)
        );
    }

    @DisplayName("Test instantiating")
    @MethodSource
    @ParameterizedTest
    void of_test(TestCaseWrapper testCase) {
        InningResult inningResult = InningResult.of(testCase.set1(), testCase.set2());
        assertThat(inningResult.getStrike()).isEqualTo(testCase.expectedStrike());
        assertThat(inningResult.getBall()).isEqualTo(testCase.expectedBall());
    }
}