package baseball;

import baseball.controller.BaseballController;
import baseball.domain.InningResult;
import baseball.domain.NumberSet;
import baseball.view.InputView;
import baseball.view.OutputView;

public class Application {
    public static void main(String[] args) {
        BaseballController baseballController =
                new BaseballController(InputView.getInstance(), OutputView.getInstance());
        baseballController.initGame();
        run(baseballController);
    }

    private static void run(BaseballController baseballController) {
        NumberSet target = baseballController.generateRandomTarget();
        while (true) {
            NumberSet input = baseballController.inputDigits();
            InningResult inningResult = baseballController.getInningResult(input, target);
            boolean end = baseballController.processInningResult(inningResult);
            if (end) {
                break;
            }
        }
        boolean restart = baseballController.willGameBeRestarted();
        if (restart) {
            run(baseballController);
        }
    }
}
