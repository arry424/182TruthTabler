import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Calculator {
    String expression;
    StringTokenizer arrayCounter;
    String[] sepExpression;
    ArrayList<String> displayString;
    Table table;

    public Calculator(String expression) {
        this.expression = expression;
        arrayCounter = new StringTokenizer(expression, " ");
        sepExpression = new String[arrayCounter.countTokens()];
        Arrays.fill(sepExpression, "");
        displayString = new ArrayList<String>();
        table = new Table();
    }

    public void createArray() {
        int count = 0;
        for (int i = 0; i < expression.length(); i++) {
            if (expression.substring(i, i+1).equals(" ")) {
                count++;
                continue;
            }
            sepExpression[count] += expression.substring(i, i + 1);
        }
    }

    public String[] createDisplayArray() {
        for (int i = 0; i < sepExpression.length; i++) {
            for (int j = 0; j < sepExpression[i].length(); j++) {
                if ((int)(sepExpression[i].charAt(j)) >= 97 && ((int)sepExpression[i].charAt(j)) <= 122 &&
                    !displayString.contains(Character.toString(sepExpression[i].charAt(j)))) {
                    displayString.add(Character.toString(sepExpression[i].charAt(j)));
                }
            }
        }

        for (int i = 0; i < sepExpression.length; i++) {
            if (sepExpression[i].contains("NOT") &&
                !displayString.contains(sepExpression[i])) {
                displayString.add(sepExpression[i]);
            }
        }

        for (int i = 0; i < sepExpression.length; i++) {
            if (sepExpression[i].contains("AND") &&
                    !displayString.contains(sepExpression[i - 1] + " AND " + sepExpression[i + 1])) {
                displayString.add(sepExpression[i - 1] + " AND " + sepExpression[i + 1]);
            }
        }

        for (int i = 0; i < sepExpression.length; i++) {
            String before = "";
            String after = "";
            if (sepExpression[i].contains("OR")) {
                for (int j = i - 1; j >= 0; j--) {
                    if (!sepExpression[j].contains("BI") && !sepExpression[j].contains("IMP")) {
                        before = sepExpression[j] + " " + before;
                    }
                    else {
                        break;
                    }
                }
                for (int j = i + 1; j < sepExpression.length; j++) {
                    if (!sepExpression[j].contains("BI") && !sepExpression[j].contains("IMP") && !sepExpression[j].contains("OR")) {
                        after = after + " " + sepExpression[j];
                    }
                    else {
                        break;
                    }
                }
                displayString.add(before + "OR" + after);
            }

        }

        for (int i = 0; i < sepExpression.length; i++) {
            String before = "";
            String after = "";
            if (sepExpression[i].contains("IMP")) {
                for (int j = i - 1; j >= 0; j--) {
                    if (!sepExpression[j].contains("BI")) {
                        before = sepExpression[j] + " " + before;
                    }
                    else {
                        break;
                    }
                }

                for (int j = i + 1; j < sepExpression.length; j++) {
                    if (!sepExpression[j].contains("BI") && !sepExpression[j].contains("IMP")) {
                        after = after + " " + sepExpression[j];
                    }
                    else {
                        break;
                    }
                }
                displayString.add(before + "IMP" + after);
            }

        }

        for (int i = 0; i < sepExpression.length; i++) {
            String before = "";
            String after = "";
            if (sepExpression[i].contains("BI")) {
                for (int j = i - 1; j >= 0; j--) {
                    before = sepExpression[j] + " " +before;
                }
                for (int j = i + 1; j < sepExpression.length; j++) {
                    if (!sepExpression[j].contains("BI")) {
                        after = after + " " + sepExpression[j];
                    }
                    else {
                        break;
                    }
                }
                displayString.add(before + "BI" + after);
            }

        }

        String[] displayArray = new String[displayString.size()];
        for (int i = 0; i < displayString.size(); i++) {
            displayArray[i] = displayString.get(i);
            System.out.print(displayArray[i] + "\n");
        }

        return displayArray;
    }
}
