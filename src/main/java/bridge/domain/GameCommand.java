package bridge.domain;

public class GameCommand {

    private final String movingCommand;

    public GameCommand(String gameCommand) {
        validate(gameCommand);
        this.movingCommand = gameCommand;
    }

    private void validate(String gameCommand) {
        if (!isValidCommand(movingCommand)) {
            throw new IllegalArgumentException("[ERROR] 유효한 입력이 아닙니다.");
        }
    }

    private boolean isValidCommand(String gameCommand) {
        return movingCommand.equals("R") || movingCommand.equals("Q");
    }

    public boolean isRetry() {
        return movingCommand == "R";
    }

}
