import javax.swing.*;

public class Tester {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Test");
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(2000, 1000);
        // "p AND NOTq OR s AND e BI l IMP r OR NOTs AND a AND b AND c"
        TableGUI tableGui = new TableGUI("p AND ( q OR r )");
        //tableGui.setPreferredSize(new java.awt.Dimension(20000, 20000));
        JScrollPane scrollPane = new JScrollPane(tableGui, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        frame.add(scrollPane);

        frame.setVisible(true);
    }
}
