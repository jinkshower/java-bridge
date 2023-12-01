package bridge.domain;

public enum BridgeConstant {

    UPPER_TRAVERSAL("U"),
    LOWER_TRAVERSAL("D");

    private final String message;

    BridgeConstant(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
