package bridge.controller;

import bridge.Application;
import bridge.InputView;
import bridge.OutputView;
import bridge.domain.ApplicationStatus;
import java.util.EnumMap;
import java.util.Map;
import java.util.function.Supplier;

public class GameController {

    private final InputView inputView;
    private final OutputView outputView;
    private final Map<ApplicationStatus, Supplier<ApplicationStatus>> gameGuide;

    public GameController(InputView inputView, OutputView outputVIew) {
        this.inputView = inputView;
        this.outputView = outputVIew;
        this.gameGuide = initializeGameGuide();
    }

    private Map<ApplicationStatus, Supplier<ApplicationStatus>> initializeGameGuide() {
        Map<ApplicationStatus, Supplier<ApplicationStatus>> gameGuide = new EnumMap<>(ApplicationStatus.class);
        gameGuide.put(ApplicationStatus.SET_UP, this::setUp);
        gameGuide.put(ApplicationStatus.START_GAME, this::startGame);
        gameGuide.put(ApplicationStatus.PLAYING, this::playRound);
        gameGuide.put(ApplicationStatus.RETRY_OR_EXIT, this::retryOrExit);
        return gameGuide;
    }

    private ApplicationStatus setUp() {
        outputView.printBeginNotice();
    }

    public void run() {
        ApplicationStatus applicationStatus = process(ApplicationStatus.SET_UP_GAME);
        while (applicationStatus.playable()) {
            applicationStatus = process(applicationStatus);
        }
    }

    private ApplicationStatus process(ApplicationStatus applicationStatus) {
        while (true) {
            try {
                return gameGuide.get(applicationStatus).get();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
