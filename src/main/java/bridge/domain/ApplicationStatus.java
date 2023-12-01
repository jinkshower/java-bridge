package bridge.domain;

public enum ApplicationStatus {

    SET_UP,
    START_GAME,
    RETRY_OR_EXIT,
    APPLICATION_EXIT;

    public boolean playable() {
        return this != APPLICATION_EXIT;
    }
}
