package bridge;

import bridge.domain.MovingCommand;
import java.util.ArrayList;
import java.util.List;

/**
 * 다리 건너기 게임을 관리하는 클래스
 */
public class BridgeGame {

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
        if (movingCommand.getMovingCommand().equals("U")) {
            roundResult.add("U-O");
        }
        if (movingCommand.getMovingCommand().equals("D")) {
            roundResult.add("D-O");
        }
    }

    private void recordFailure(MovingCommand movingCommand) {
        if (movingCommand.getMovingCommand().equals("U")) {
            roundResult.add("U-X");
        }
        if (movingCommand.getMovingCommand().equals("D")) {
            roundResult.add("D-X");
        }
    }

    private boolean isMovable(MovingCommand movingCommand) {
        return bridges.get(bridgeIndex).equals(movingCommand.getMovingCommand());
    }

    public boolean isGameOver() {
        return bridgeIndex == bridges.size() || roundResult.contains("U-X") || roundResult.contains("D-X");
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
        return result.equals("U-O") || result.equals("D-O");
    }

    public String result() {
        if (isJourneyEnd()) {
            return "성공";
        }
        return "실패";
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
