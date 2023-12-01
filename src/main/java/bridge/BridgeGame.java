package bridge;

import bridge.domain.MovingCommand;
import bridge.domain.ResultConstant;
import java.util.ArrayList;
import java.util.List;

/**
 * 다리 건너기 게임을 관리하는 클래스
 */
public class BridgeGame {

    private static final String UPPER_SAFE_BRIDGE = "U";
    private static final String LOWER_SAFE_BRIDGE = "D";
    private static final String UPPER_TRAVEL = ResultConstant.UPPER_TRAVEL.getMessage();
    private static final String LOWER_TRAVEL = ResultConstant.LOWER_TRAVEL.getMessage();
    private static final String UPPER_BROKEN = ResultConstant.UPPER_BROKEN.getMessage();
    private static final String LOWER_BROKEN = ResultConstant.LOWER_BROKEN.getMessage();
    private static final String SUCCESS_MESSAGE = "성공";
    private static final String FAILURE_MESSAGE = "실패";

    private final List<String> bridges;
    private List<String> roundResult;
    private int bridgeIndex = 0;
    private int tryCount = 1;

    public BridgeGame(List<String> bridges) {
        this.bridges = new ArrayList<>(bridges);
        this.roundResult = new ArrayList<>();
    }

    public void move(MovingCommand movingCommand) {
        if (isMovable(movingCommand)) {
            recordMoving(movingCommand);
            bridgeIndex++;
            return;
        }
        recordFailure(movingCommand);
        bridgeIndex++;
    }

    private void recordMoving(MovingCommand movingCommand) {
        if (movingCommand.getMovingCommand().equals(UPPER_SAFE_BRIDGE)) {
            roundResult.add(UPPER_TRAVEL);
        }
        if (movingCommand.getMovingCommand().equals(LOWER_SAFE_BRIDGE)) {
            roundResult.add(LOWER_TRAVEL);
        }
    }

    private void recordFailure(MovingCommand movingCommand) {
        if (movingCommand.getMovingCommand().equals(UPPER_SAFE_BRIDGE)) {
            roundResult.add(UPPER_BROKEN);
        }
        if (movingCommand.getMovingCommand().equals(LOWER_SAFE_BRIDGE)) {
            roundResult.add(LOWER_BROKEN);
        }
    }

    private boolean isMovable(MovingCommand movingCommand) {
        return bridges.get(bridgeIndex).equals(movingCommand.getMovingCommand());
    }

    public boolean isGameOver() {
        return bridgeIndex == bridges.size() ||
                roundResult.contains(UPPER_BROKEN) || roundResult.contains(LOWER_BROKEN);
    }

    public List<String> currentBridge() {
        return new ArrayList<>(roundResult);
    }

    public boolean isJourneyEnd() {
        return bridges.size() == roundResult.stream()
                .filter(this::isNotBroken)
                .count();
    }

    private boolean isNotBroken(String result) {
        return result.equals(UPPER_TRAVEL) || result.equals(LOWER_TRAVEL);
    }

    public String result() {
        if (isJourneyEnd()) {
            return SUCCESS_MESSAGE;
        }
        return FAILURE_MESSAGE;
    }

    public int getTryCount() {
        return tryCount;
    }

    public void retry() {
        roundResult = new ArrayList<>();
        bridgeIndex = 0;
        tryCount++;
    }
}
