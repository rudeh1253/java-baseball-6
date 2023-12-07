package baseball.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    private static final InputView singleton = new InputView();

    private InputView() {
    }

    public static InputView getInstance() {
        return singleton;
    }

    private static class InputValidator {

        public static void validateInteger(String expectedToInteger) {
            try {
                Integer.parseInt(expectedToInteger);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException();
            }
        }
    }

    public int inputInteger() throws IllegalArgumentException {
        String input = Console.readLine();
        InputValidator.validateInteger(input);
        return Integer.parseInt(input);
    }
}
