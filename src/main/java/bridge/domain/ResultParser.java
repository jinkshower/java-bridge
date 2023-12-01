package bridge.domain;

import java.util.List;

public class ResultParser {

    public static String convertResultToString(List<String> roundResult) {
        String upperBridge = "";
        String lowerBridge = "";
        for (String string : roundResult) {
            String[] split = analyzeBridge(string);
            upperBridge = addBridge(upperBridge, split[0]);
            lowerBridge = addBridge(lowerBridge, split[1]);
        }
        return "[" + upperBridge + "]" + "%n" + "[" + lowerBridge + "]";
    }

    private static String[] analyzeBridge(String string) {
        String bridge = ResultConstant.of(string).getBridge();
        return bridge.split("");
    }

    private static String addBridge(String bridge, String split) {
        String blankIncluded = " " + split + " ";
        if (!bridge.isEmpty()) {
            return bridge + "|" + blankIncluded;
        }
        return bridge + blankIncluded;
    }
}
