package baseball.view;

import static baseball.view.config.OutputConstants.BALL;
import static baseball.view.config.OutputConstants.END_OF_GAME;
import static baseball.view.config.OutputConstants.INIT_MESSAGE;
import static baseball.view.config.OutputConstants.NOTHING;
import static baseball.view.config.OutputConstants.PROMPT_INPUT;
import static baseball.view.config.OutputConstants.PROMPT_RESTART;
import static baseball.view.config.OutputConstants.SPACE;
import static baseball.view.config.OutputConstants.STRIKE;

import baseball.dto.InningResultDTO;

public class OutputView {
    private static final OutputView SINGLETON = new OutputView();

    private OutputView() {
    }

    public static OutputView getInstance() {
        return SINGLETON;
    }

    public void printInitMessage() {
        System.out.println(INIT_MESSAGE.get());
    }

    public void promptInput() {
        System.out.print(PROMPT_INPUT.get());
    }

    public void printInningResult(InningResultDTO dto) {
        int strike = dto.getStrike();
        int ball = dto.getBall();
        if (strike == 0 && ball == 0) {
            System.out.println(NOTHING.get());
            return;
        }
        System.out.println(ball + BALL.get() + SPACE.get() + strike + STRIKE.get());
    }

    public void printEndMessage() {
        System.out.println(END_OF_GAME.get());
    }

    public void promptRestart() {
        System.out.println(PROMPT_RESTART.get());
    }
}
