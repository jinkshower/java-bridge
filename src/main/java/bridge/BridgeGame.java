package bridge;

import bridge.domain.MovingCommand;
import java.util.ArrayList;
import java.util.List;

/**
 * 다리 건너기 게임을 관리하는 클래스
 */
public class BridgeGame {

    private List<String> bridges;
    private List<String> roundResult;
    private int bridgeIndex = 0;
    private int tryCount = 1;
    private final List<String> initialBridges;

    public BridgeGame(List<String> bridges) {
        this.bridges = new ArrayList<>(bridges);
        this.roundResult = new ArrayList<>();
        this.initialBridges = new ArrayList<>(bridges);
    }

    public void move(MovingCommand movingCommand) {
        if (isMovable(movingCommand)) {
            bridges.set(bridgeIndex, "O");
            recordMoving(movingCommand);
            bridgeIndex++;
            return;
        }
        bridges.set(bridgeIndex, "X");
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
        return bridgeIndex == bridges.size() || bridges.contains("X");
    }

    public List<String> currentBridge() {
        return new ArrayList<>(roundResult);
    }

    public boolean isJourneyEnd() {
        return roundResult.size() == bridges.stream().filter(s -> s.equals("O")).count();
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
        bridges = initialBridges;
        bridgeIndex = 0;
        tryCount++;
    }
}
