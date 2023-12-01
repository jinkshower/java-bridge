package bridge.domain;

public enum ApplicationStatus {

    SET_UP,
    START_GAME,
    PLAYING,
    RETRY_OR_EXIT,
    APPLICATION_EXIT;

    public boolean playable() {
        return this != APPLICATION_EXIT;
    }
}
