package baseball.config;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class CharacterSetTest {

    @DisplayName("If CharacterSet is of number mode, when calling 'contains' method with the argument " +
            "which is not an integer or doesn't reside between 1 and 9, IllegalArgumentException is thrown.")
    @ValueSource(strings = { "0", "-1", "-12413853", "10", "125214681", "a", "b", "z", "ab", "de" })
    @ParameterizedTest
    void contains_numberMode_tryOutOfDomain(String value) {
        assertThat(CharacterSet.of(GameMode.MODE_NUMBER).contains(value)).isFalse();
    }

    @DisplayName("The mode of CharacterSet is set to number mode and 'contains' method is called with " +
            "the argument following the specification.")
    @ValueSource(strings = { "1", "2", "3", "4", "5", "6", "7", "8", "9" })
    @ParameterizedTest
    void contains_numberMode_tryInsideOfDomain(String value) {
        assertThat(CharacterSet.of(GameMode.MODE_NUMBER).contains(value)).isTrue();
    }

    @DisplayName("If CharacterSet is of alphabet mode, when calling 'contains' method with the argument " +
            "which is not an alphabet or doesn't reside between 'a' and 'z' or whose length is not 1, " +
            "IllegalArgumentException is thrown.")
    @ValueSource(strings = { "0", "-1", "123", "A", "Z", "ab", "cd", "za", "!" })
    @ParameterizedTest
    void contains_alphabetMode_tryOutOfDomain(String value) {
        assertThat(CharacterSet.of(GameMode.MODE_ALPHABET).contains(value)).isFalse();
    }

    @DisplayName("The mode of CharacterSet is set to alphabet mode and 'contains' method is called with " +
            "the argument following the specification.")
    @ValueSource(
            strings = {
                    "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p",
                    "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"
            }
    )
    @ParameterizedTest
    void contains_alphabetMode_tryInsideOfDomain(String value) {
        assertThat(CharacterSet.of(GameMode.MODE_ALPHABET).contains(value)).isTrue();
    }
}