package bridge.domain;

public class GameCommand {

    private final String gameCommand;

    public GameCommand(String gameCommand) {
        validate(gameCommand);
        this.gameCommand = gameCommand;
    }

    private void validate(String gameCommand) {
        if (!isValidCommand(gameCommand)) {
            throw new IllegalArgumentException("[ERROR] 유효한 입력이 아닙니다.");
        }
    }

    private boolean isValidCommand(String gameCommand) {
        return gameCommand.equals("R") || gameCommand.equals("Q");
    }

    public boolean isRetry() {
        return gameCommand.equals("R");
    }

}
