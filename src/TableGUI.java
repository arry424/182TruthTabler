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
                    text.setFont(new Font("Comic Sans MS", Font.PLAIN, 40));
                    text.setHorizontalAlignment(JLabel.CENTER);
                    text.setVerticalAlignment(JLabel.CENTER);

                    text.setMinimumSize(new Dimension(colWidth, rowHeight * 2));

                    this.add(text, gbc);


                    curGridXLoc++;
                } else {
                    gbc.gridx = curGridXLoc;
                    gbc.gridy = curGridYLoc;
                    JLabel text = new JLabel(table.getTruth(i, j));
                    text.setFont(new Font("Comic Sans MS", Font.PLAIN, 40));
                    text.setHorizontalAlignment(JLabel.CENTER);
                    this.add(text, gbc);
                    curGridXLoc++;
                }
            }
            curGridXLoc = 0;
            curGridYLoc++;
        }
    }
}
