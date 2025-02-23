import javax.swing.*;

public class CalcScreen {
    public static void main(String[] args) {
        String input = JOptionPane.showInputDialog(null, "Enter your expression");
        JFrame frame = new JFrame("Truth Table");
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setSize(2000, 1000);
        TableGUI tableGui = new TableGUI(input);
        //tableGui.setPreferredSize(new java.awt.Dimension(20000, 20000));
        JScrollPane scrollPane = new JScrollPane(tableGui, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        frame.add(scrollPane);

        frame.setVisible(true);
    }
}
