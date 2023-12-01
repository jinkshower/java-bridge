package bridge.controller;

import bridge.Application;
import bridge.BridgeGame;
import bridge.BridgeMaker;
import bridge.BridgeRandomNumberGenerator;
import bridge.InputView;
import bridge.OutputView;
import bridge.domain.ApplicationStatus;
import bridge.domain.MovingCommand;
import bridge.util.ExceptionHandler;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class GameController {

    private final InputView inputView;
    private final OutputView outputView;
    private final Map<ApplicationStatus, Supplier<ApplicationStatus>> gameGuide;
    private BridgeGame bridgeGame;

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
        List<String> bridges = ExceptionHandler.repeatUntilValid(this::handleBridgeSize);
        bridgeGame = new BridgeGame(bridges);
        return ApplicationStatus.START_GAME;
    }

    private List<String> handleBridgeSize() {
        BridgeMaker bridgeMaker = new BridgeMaker(new BridgeRandomNumberGenerator());
        return bridgeMaker.makeBridge(inputView.readBridgeSize());
    }

    private ApplicationStatus startGame() {
        while (!bridgeGame.isGameOver()) {
            MovingCommand movingCommand = ExceptionHandler.repeatUntilValid(this::handleMoveCommand);
            bridgeGame.move(movingCommand);
            outputView.printMap(bridgeGame.currentBridge());
        }
        return ApplicationStatus.RETRY_OR_EXIT;
    }


    private MovingCommand handleMoveCommand() {
        return new MovingCommand(inputView.readGameCommand());
    }

    public void run() {
        ApplicationStatus applicationStatus = process(ApplicationStatus.SET_UP);
        while (applicationStatus.playable()) {
            applicationStatus = process(applicationStatus);
        }
    }

    private ApplicationStatus process(ApplicationStatus applicationStatus) {
        try {
            return gameGuide.get(applicationStatus).get();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
