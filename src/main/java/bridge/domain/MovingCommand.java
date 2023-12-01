package bridge.domain;

public class MovingCommand {

    private final String movingCommand;

    public MovingCommand(String movingCommand) {
        validate(movingCommand);
        this.movingCommand = movingCommand;
    }

    private void validate(String movingCommand) {
        if (!isValidCommand(movingCommand)) {
            throw new IllegalArgumentException("[ERROR] 유효한 입력이 아닙니다.");
        }
    }

    private boolean isValidCommand(String movingCommand) {
        return movingCommand.equals("U") || movingCommand.equals("D");
    }

    public String getMovingCommand() {
        return movingCommand;
    }
}
