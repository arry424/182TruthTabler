import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Calculator {
    String expression;
    StringTokenizer arrayCounter;
    String[] sepExpression;
    ArrayList<String> displayString;
    String[] displayArray;
    Table table;
    int varCount = 0;

    public Calculator(String expression) {
        this.expression = expression;
        arrayCounter = new StringTokenizer(expression, " ");
        sepExpression = new String[arrayCounter.countTokens()];
        Arrays.fill(sepExpression, "");
        displayString = new ArrayList<String>();
        table = new Table();
    }

    public void setup() {
        createArray();
        createDisplayArray();
        addVarCols();
        addNotCols();
        addAndCols();
        addOrCols();
        addImpCols();
        addBiCols();
        System.out.println("\n" + table);
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
                    varCount++;
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
            String before = "";
            String after = "";
            if (sepExpression[i].contains("AND")) {
                for (int j = i - 1; j >= 0; j--) {
                    if (!sepExpression[j].contains("OR") && !sepExpression[j].contains("BI") && !sepExpression[j].contains("IMP")) {
                        before = sepExpression[j] + " " + before;
                    }
                    else {
                        break;
                    }
                }
                for (int j = i + 1; j < sepExpression.length; j++) {
                    if (!sepExpression[j].contains("BI") && !sepExpression[j].contains("IMP") && !sepExpression[j].contains("OR")
                        && !sepExpression[j].contains("AND")) {
                        after = after + " " + sepExpression[j];
                    }
                    else {
                        break;
                    }
                }
                displayString.add(before + "AND" + after);
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

        displayArray = new String[displayString.size()];
        for (int i = 0; i < displayString.size(); i++) {
            displayArray[i] = displayString.get(i);
            System.out.print(displayArray[i] + "\n");
        }

        return displayArray;
    }

    public void addVarCols() {
        for (int i = 0; i < varCount; i++) {
            String[] col = new String[(int)Math.pow(2, varCount) + 1];
            col[0] = displayArray[i];
            int step = 0;
            int interval = (int) Math.pow(2, i);
            boolean change = true;
            for (int j = 1; j < col.length; j++) {
                col[j] = change ? "T" : "F";
                step++;
                if (step == interval) {
                    step = 0;
                    change = !change;
                }
            }

            table.addCol(col);
        }
    }

    public void addNotCols() {
        for (int i = varCount; i < displayArray.length; i++) {
            if (displayArray[i].contains("NOT") && displayArray[i].length() == 4) {
                String var = displayArray[i].substring(3);
                int column = findCol(var);
                String[] col = new String[(int)Math.pow(2, varCount) + 1];
                col[0] = displayArray[i];
                for (int j = 1; j < col.length; j++) {
                    col[j] = table.getTruth(j, column).equals("T") ? "F" : "T";
                }
                table.addCol(col);
            }
        }
    }


    //TODO add the AND OR IMP and BI cols
    /*
        Loop through the display array until one is found with the predicate operator
        if (also contains a lower precedence operator)
            ignore'
        else
            use the operator for AND or OR directly with whatever is on the left and right
            special stuff for implies and bi
                will probably be annoying
     */

    public void addAndCols() {
        for (int i = varCount; i < displayArray.length; i++) {
            if (displayArray[i].contains("AND") && (!displayArray[i].contains("OR") && !displayArray[i].contains("IMP") &&
                    !displayArray[i].contains("BI"))) {
                String before = displayArray[i].substring(0, displayArray[i].lastIndexOf("AND") - 1);
                String after = displayArray[i].substring(displayArray[i].lastIndexOf("AND") + 4);
                int columnBefore = findCol(before);
                int columnAfter = findCol(after);
                String[] col = new String[(int)Math.pow(2, varCount) + 1];
                col[0] = displayArray[i];
                for (int j = 1; j < col.length; j++) {
                    col[j] = table.getTruth(j, columnBefore).equals("T") && table.getTruth(j, columnAfter).equals("T")? "T" : "F";
                }
                table.addCol(col);
            }
        }
    }

    public void addOrCols() {
        for (int i = varCount; i < displayArray.length; i++) {
            if (displayArray[i].contains("OR") && (!displayArray[i].contains("IMP") &&
                    !displayArray[i].contains("BI"))) {
                String before = displayArray[i].substring(0, displayArray[i].lastIndexOf("OR") - 1);
                String after = displayArray[i].substring(displayArray[i].lastIndexOf("OR") + 3);
                int columnBefore = findCol(before);
                int columnAfter = findCol(after);
                String[] col = new String[(int)Math.pow(2, varCount) + 1];
                col[0] = displayArray[i];
                for (int j = 1; j < col.length; j++) {
                    col[j] = table.getTruth(j, columnBefore).equals("T") || table.getTruth(j, columnAfter).equals("T")? "T" : "F";
                }
                table.addCol(col);
            }
        }
    }

    public void addImpCols() {
        for (int i = varCount; i < displayArray.length; i++) {
            if (displayArray[i].contains("IMP") && (!displayArray[i].contains("BI"))) {
                String before = displayArray[i].substring(0, displayArray[i].lastIndexOf("IMP") - 1);
                String after = displayArray[i].substring(displayArray[i].lastIndexOf("IMP") + 4);
                int columnBefore = findCol(before);
                int columnAfter = findCol(after);
                String[] col = new String[(int)Math.pow(2, varCount) + 1];
                col[0] = displayArray[i];
                for (int j = 1; j < col.length; j++) {
                    col[j] = table.getTruth(j, columnBefore).equals("T") && table.getTruth(j, columnAfter).equals("F")? "F" : "T";
                }
                table.addCol(col);
            }
        }
    }

    public void addBiCols() {
        for (int i = varCount; i < displayArray.length; i++) {
            if (displayArray[i].contains("BI")) {
                String before = displayArray[i].substring(0, displayArray[i].lastIndexOf("BI") - 1);
                String after = displayArray[i].substring(displayArray[i].lastIndexOf("BI") + 3);
                int columnBefore = findCol(before);
                int columnAfter = findCol(after);
                String[] col = new String[(int)Math.pow(2, varCount) + 1];
                col[0] = displayArray[i];
                for (int j = 1; j < col.length; j++) {
                    col[j] = ((table.getTruth(j, columnBefore).equals("T") && table.getTruth(j, columnAfter).equals("T")) ||
                              (table.getTruth(j, columnBefore).equals("F") && table.getTruth(j, columnAfter).equals("F")))  ? "T" : "F";
                }
                table.addCol(col);
            }
        }
    }

    public int findCol(String s) {
        for (int i = 0; i < displayArray.length; i++) {
            if (displayArray[i].equals(s)) {
                return i;
            }
        }
        return -1;
    }

}
