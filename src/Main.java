import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JComponent {
    private static JButton yes;
    private static JButton no;
    private static JDialog dialog;
    public static void main(String[] args) {
        UIManager.put("OptionPane.minimumSize", new Dimension(500,500));
        UIManager.put("OptionPane.background", Color.WHITE);
        UIManager.put("Panel.background", Color.WHITE);
        JTextArea rules = new JTextArea("Welcome to Truth Tabler! Before we get started, " +
                "make sure you understand the formatting rules.\n1. Parentheses are not allowed. (sorry.)\n2. Variables " +
                "and operators must be seperated by a space with the exception of negation.\n3. Operators must be typed" +
                " in all caps. Valid operators are: NOT, AND, OR, IMP, BI\n4. Variables must be in lowercase.\nExample: " +
                "NOTp AND r BI NOTq OR s IMP p\nDo you understand?");
        rules.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        JOptionPane pane = new JOptionPane(rules, JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION, null,
                new Object[]{}, null);
        yes = new JButton(" YES ");
        no = new JButton(" NO ");
        yes.setFont(new Font("Times New Roman", Font.BOLD, 60));
        no.setFont(new Font("Times New Roman", Font.PLAIN, 60));
        yes.setPreferredSize(new Dimension(250, 125));
        no.setPreferredSize(new Dimension(250, 125));
        yes.addActionListener(actionListener);
        no.addActionListener(actionListener);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(yes);
        buttonPanel.add(no);

        pane.add(buttonPanel, JOptionPane.BOTTOM_ALIGNMENT);
        pane.setBackground(Color.WHITE);
        dialog = pane.createDialog(null, "Do you understand?");
        dialog.setBackground(Color.WHITE);
        dialog.setVisible(true);
        dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }
    private static ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == no) {
                System.exit(0);
            }
            if (e.getSource() == yes) {
                dialog.setVisible(false);

                JTextArea enter = new JTextArea("Enter your expression:");
                enter.setFont(new Font("Times New Roman", Font.PLAIN, 30));
                String input = JOptionPane.showInputDialog(null, enter);
                /*
                boolean formatted = verifyString(input);
                while (!formatted) {
                    JOptionPane.showMessageDialog(null, "String incorrectly formatted. Please try " +
                            "again.", "Error!", JOptionPane.ERROR_MESSAGE);
                    input = JOptionPane.showInputDialog(null, enter);
                    //formatted = verifyString(input);

                }

                 */

                TableGUI tableGui = new TableGUI(input);
                JFrame frame = new JFrame("Truth Table");
                frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                JScrollPane scrollPane = new JScrollPane(tableGui, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                        JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

                frame.add(scrollPane);

                frame.setVisible(true);
            }
        }
    };
    /*
    public static boolean verifyString(String input) {
        Calculator calc = new Calculator(input);
        calc.setup();

        Table table = calc.getTable();
        for (int i = 0; i < table.getCols(); i++) {
            String header = table.getHeader(i);
            if (!header.contains("NOT") && !header.contains("AND") && !header.contains("OR") && !header.contains("BI")
                    && !header.contains("IMP") && !(header.matches("^[a-z]+$") && header.length() == 1) &&  !header.matches(".*NOT[a-z].*")) {
                return false;
            }
        }
        return true;
    }
    */
}

