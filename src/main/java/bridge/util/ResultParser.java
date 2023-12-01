package bridge.util;

import java.util.List;
import java.util.StringJoiner;

public class ResultParser {

    public static String convertResultToString(List<String> roundResult) {
        String upperBridge = "";
        String lowerBridge = "";
        for (String string: roundResult) {
            String[] split = analyzeBridge(string);
            upperBridge = addBridge(upperBridge, split[0]);
            lowerBridge = addBridge(lowerBridge, split[1]);
        }
        return "[" + upperBridge + "]" + "%n" + "[" + lowerBridge + "]";
    }

    private static String[] analyzeBridge(String string) {
        String bridge = ResultConstant.of(string).getBridge();
        String[] split = bridge.split("");
        return split;
    }

    private static String addBridge(String bridge, String split) {
        if (!bridge.isEmpty()) {
            return bridge + "|" + split;
        }
        return bridge + split;
    }
}
