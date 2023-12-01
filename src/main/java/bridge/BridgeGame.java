package bridge;

import bridge.domain.MovingCommand;
import java.util.ArrayList;
import java.util.List;

/**
 * 다리 건너기 게임을 관리하는 클래스
 */
public class BridgeGame {

    private final List<String> bridges;
    private int bridgeIndex = 0;

    public BridgeGame(List<String> bridges) {
        this.bridges = new ArrayList<>(bridges);
    }

    public void move(MovingCommand movingCommand) {
        if (isMovable(movingCommand)) {
            bridges.set(bridgeIndex, "O");
            bridgeIndex++;
            return;
        }
        bridges.set(bridgeIndex, "X");
        bridgeIndex++;
    }

    private boolean isMovable(MovingCommand movingCommand) {
        return bridges.get(bridgeIndex).equals(movingCommand.getMovingCommand());
    }

    public boolean isGameOver() {
        return bridgeIndex == bridges.size() || bridges.contains("X");
    }

    public List<String> currentBridge() {
        return new ArrayList<>(bridges);
    }

    public void retry() {
    }
}
