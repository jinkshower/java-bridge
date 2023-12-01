package bridge.domain;

public enum ApplicationStatus {

    SET_UP,
    START_GAME,
    RETRY,
    OUTCOME,
    APPLICATION_EXIT;

    public boolean playable() {
        return this != APPLICATION_EXIT;
    }
}
