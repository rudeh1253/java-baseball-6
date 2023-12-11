package baseball.domain;

import static org.assertj.core.api.Assertions.assertThat;

import baseball.config.GameMode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;

class GameManagerTest {
    static final int LENGTH = 5;
    static final GameConfig NUMBER_MODE_CONFIG = new GameConfig(GameMode.MODE_NUMBER, LENGTH);
    static final GameConfig ALPHABET_MODE_CONFIG = new GameConfig(GameMode.MODE_ALPHABET, LENGTH);
    static final Sequence<String> CORRECT_ANSWER_FOR_NUMBER_MODE =
            new Sequence<>(List.of("1", "2", "3", "4", "5"), NUMBER_MODE_CONFIG);
    static final Sequence<String> CORRECT_ANSWER_FOR_ALPHABET_MODE =
            new Sequence<>(List.of("a", "b", "c", "d", "e"), ALPHABET_MODE_CONFIG);
    GameManager numberGameManager;
    GameManager alphabetGameManger;

    @BeforeEach
    void init() {
        numberGameManager =
                new GameManager(new GameRecorder(), NUMBER_MODE_CONFIG, CORRECT_ANSWER_FOR_NUMBER_MODE);
        alphabetGameManger =
                new GameManager(new GameRecorder(), ALPHABET_MODE_CONFIG, CORRECT_ANSWER_FOR_ALPHABET_MODE);
    }

    @DisplayName("Test incrementNumOfTry for numberGameManager")
    @ValueSource(ints = { 0, 1, 2, 3, 4, 5, 6 })
    @ParameterizedTest
    void incrementNumOfTry_numberGameManager(int numOfTry) {
        executeIncrementNumOfTryTest(numberGameManager, numOfTry);
    }

    @DisplayName("Test incrementNumOfTry for alphabetGameManger")
    @ValueSource(ints = { 0, 1, 2, 3, 4, 5, 6 })
    @ParameterizedTest
    void incrementNumOfTry_alphabetGameManager(int numOfTry) {
        executeIncrementNumOfTryTest(alphabetGameManger, numOfTry);
    }

    void executeIncrementNumOfTryTest(GameManager gameManager, int numOfTry) {
        for (int i = 0; i < numOfTry; i++) {
            gameManager.incrementNumOfTry();
        }
        assertThat(gameManager.getNumOfTry()).isEqualTo(numOfTry);
    }

    @DisplayName("Test recordRecentTry for numberGameManager")
    @ValueSource(
            strings = {
                    "12345",
                    "12345,12346",
                    "12345,12345",
                    "12345,23456,34567,45678,56789"
            }
    )
    @ParameterizedTest
    void recordRecentTry_numberGameManager(String testCase) {
        executeRecordTryTest(numberGameManager, testCase, NUMBER_MODE_CONFIG);
    }

    @DisplayName("Test recordRecentTry for numberGameManager")
    @ValueSource(
            strings = {
                    "abcde",
                    "abcde,abcdf",
                    "abcde,abcde",
                    "abcde,bcdef,cdefg,defgh,efghi"
            }
    )
    @ParameterizedTest
    void recordRecentTry_alphabetGameManager(String testCase) {
        executeRecordTryTest(alphabetGameManger, testCase, ALPHABET_MODE_CONFIG);
    }

    void executeRecordTryTest(GameManager gameManager, String testCase, GameConfig gameConfig) {
        String[] testCases = testCase.split(",");
        List<String> testList = Arrays.asList(testCases);
        testList.forEach(testCaseElement ->
                gameManager.recordRecentTry(new Sequence<>(Arrays.asList(testCaseElement.split("")),
                        gameConfig)));
        List<List<String>> history = gameManager.getTryHistory();
        for (int i = 0; i < testList.size(); i++) {
            assertThat(history.get(i)).isEqualTo(Arrays.asList(testList.get(i).split("")));
        }
    }

    @DisplayName("Test compareToCorrectAnswer for numberGameManager")
    @CsvSource({
            "12345,5,0",
            "12346,4,0",
            "54321,1,4",
            "51234,0,5",
            "56789,0,1"
    })
    @ParameterizedTest
    void compareToCorrectAnswer_numberGameManager(String guess, int expectedStrike, int expectedBall) {
        executeCompareToCorrectAnswerTest(
                numberGameManager,
                NUMBER_MODE_CONFIG,
                Arrays.asList(guess.split("")),
                expectedStrike,
                expectedBall
        );
    }

    @DisplayName("Test compareToCorrectAnswer for alphabetGameManager")
    @CsvSource({
            "abcde,5,0",
            "abcdf,4,0",
            "edcba,1,4",
            "eabcd,0,5",
            "efghi,0,1"
    })
    @ParameterizedTest
    void compareToCorrectAnswer_alphabetGameManager(String guess, int expectedStrike, int expectedBall) {
        executeCompareToCorrectAnswerTest(
                alphabetGameManger,
                ALPHABET_MODE_CONFIG,
                Arrays.asList(guess.split("")),
                expectedStrike,
                expectedBall
        );
    }

    void executeCompareToCorrectAnswerTest(GameManager gameManager,
                                           GameConfig gameConfig,
                                           List<String> guess,
                                           int expectedStrike,
                                           int expectedBall) {
        InningResult inningResult = gameManager.compareToCorrectAnswer(new Sequence<>(guess, gameConfig));
        assertThat(inningResult.strike()).isEqualTo(expectedStrike);
        assertThat(inningResult.ball()).isEqualTo(expectedBall);
    }
}