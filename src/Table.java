import java.util.*;
public class Table {
    ArrayList<String[]> table;


    public Table() {
        table = new ArrayList<>();
    }


    public void addCol(String[] col) {
        table.add(col);
    }


    public String getTruth(int row, int col) {
        return table.get(col)[row];
    }


    public String getHeader(int col) {
        return table.get(col)[0];
    }


    public int getRows() {
        return table.get(0).length;
    }


    public int getCols() {
        return table.size();
    }


    public String toString() {
        String output = "";
        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getCols(); j++) {
                output += table.get(j)[i] + "\t";
            }
            output += "\n";
        }
        return output;
    }
}

