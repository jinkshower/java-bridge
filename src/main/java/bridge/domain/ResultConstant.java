package bridge.domain;

import java.util.Arrays;

public enum ResultConstant {

    UPPER_TRAVEL("U-O", "O "),
    LOWER_TRAVEL("D-O", " O"),
    UPPER_BROKEN("U-X", "X "),
    LOWER_BROKEN("D-X", " X");

    private final String message;
    private final String bridge;

    ResultConstant(String message, String bridge) {
        this.message = message;
        this.bridge = bridge;
    }

    public static ResultConstant of(String result) {
        return Arrays.stream(ResultConstant.values())
                .filter(resultConstant -> resultConstant.message.equals(result))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 유효하지 않습니다"));
    }

    public String getMessage() {
        return message;
    }

    public String getBridge() {
        return bridge;
    }
}
