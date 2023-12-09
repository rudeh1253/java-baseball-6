package baseball.controller;

import baseball.domain.InningResult;
import baseball.domain.NumberSet;
import baseball.domain.configure.IntConstants;
import baseball.dto.InningResultDTO;
import baseball.view.InputView;
import baseball.view.OutputView;
import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.List;

public class BaseballController {
    private final InputView inputView;
    private final OutputView outputView;

    public BaseballController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void initGame() {
        outputView.printInitMessage();
    }

    public NumberSet generateRandomTarget() {
        List<Integer> computer = new ArrayList<>();
        while (computer.size() < IntConstants.NUMBER_SET_SIZE.getValue()) {
            int randomNumber = Randoms.pickNumberInRange(1, 9);
            if (!computer.contains(randomNumber)) {
                computer.add(randomNumber);
            }
        }
        return new NumberSet(computer);
    }

    public NumberSet inputDigits() {
        outputView.promptInput();
        return new NumberSet(this.inputView.inputInteger());
    }

    public InningResult getInningResult(NumberSet set1, NumberSet set2) {
        InningResult inningResult = InningResult.of(set1, set2);
        outputView.printInningResult(InningResultDTO.from(inningResult));
        return inningResult;
    }

    public boolean processInningResult(InningResult inningResult) {
        if (inningResult.getStrike() >= IntConstants.NUMBER_SET_SIZE.getValue()) {
            outputView.printEndMessage();
            return true;
        }
        return false;
    }

    public boolean willGameBeRestarted() {
        outputView.promptRestart();
        return inputView.inputInteger() == IntConstants.RESTART_GAME.getValue();
    }
}
