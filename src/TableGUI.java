import javax.swing.*;
import java.awt.*;

public class TableGUI extends JPanel {
    public TableGUI(String input) {
        this.setLayout(new GridBagLayout());
        this.setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 50, 5, 50);
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;


        Calculator calc = new Calculator(input);
        calc.setup();

        Table table = calc.getTable();
        // Dynamically adjust panel's preferred size based on table dimensions
        int rowHeight = 50; // Height per row in pixels
        int colWidth = 100; // Width per column in pixels

        // Set preferred size based on table dimensions
        this.setMaximumSize(new java.awt.Dimension(table.getCols() * table.getLast().length(), table.getRows() * rowHeight));
        this.setMinimumSize(new java.awt.Dimension(table.getCols() * colWidth, table.getRows() * rowHeight));
        /*
        int[] widths = new int[table.getCols()];

        for (int j = 0; j < table.getCols(); j++) {
            int maxWidth = table.getHeader(j).length();
            for (int i = 0; i < table.getRows(); i++) {
                maxWidth = Math.max(maxWidth, table.getTruth(i, j).length());
            }
        }

         */

        int curGridXLoc = 0;
        int curGridYLoc = 0;

        for (int i = 0; i < table.getRows(); i++) {
            for (int j = 0; j < table.getCols(); j++) {
                if (i == 0) {
                    gbc.gridx = curGridXLoc;
                    gbc.gridy = curGridYLoc;
                    JLabel text = new JLabel(table.getHeader(j));
                    text.setFont(new Font("Arial", Font.PLAIN, 20));
                    text.setHorizontalAlignment(JLabel.CENTER);
                    text.setVerticalAlignment(JLabel.CENTER);
                    if (j == table.getCols() - 1) {
                        text.setFont(new Font("Arial", Font.BOLD, 20));
                    }

                    text.setMinimumSize(new Dimension(colWidth, rowHeight * 2));

                    this.add(text, gbc);


                    curGridXLoc++;
                } else {
                    gbc.gridx = curGridXLoc;
                    gbc.gridy = curGridYLoc;
                    JLabel text = new JLabel(table.getTruth(i, j));
                    text.setFont(new Font("Arial", Font.PLAIN, 15));
                    if (j == table.getCols() - 1) {
                        text.setFont(new Font("Arial", Font.BOLD, 15));
                    }
                    text.setHorizontalAlignment(JLabel.CENTER);
                    this.add(text, gbc);
                    curGridXLoc++;
                }
            }
            curGridXLoc = 0;
            curGridYLoc++;
        }
    }

    public boolean verifyString(String input) {
        Calculator calc = new Calculator(input);
        calc.setup();

        Table table = calc.getTable();
        for (int i = 0; i < table.getCols(); i++) {
            String header = table.getHeader(i);
            if (!header.contains("NOT") && !header.contains("AND") && !header.contains("OR") && !header.contains("BI")
                && !header.contains("IMP") && !header.contains("^[a-z]+$")) {
                return false;
            }
        }
        return true;
    }
}
