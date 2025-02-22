import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JComponent {
    private static JButton yes;
    private static JButton no;
    private static JDialog dialog;
    public static void main(String[] args) {
        UIManager.put("OptionPane.minimumSize", new Dimension(2000,1000));
        UIManager.put("OptionPane.background", Color.WHITE);
        UIManager.put("Panel.background", Color.WHITE);
        JTextArea rules = new JTextArea("Welcome to Truth Tabler! Before we get started, " +
                "make sure you understand the formatting rules.\n1. Parentheses are not allowed. (sorry.)\n2. Variables " +
                "and operators must be seperated by a space with the exception of negation.\n3. Operators must be typed" +
                " in all caps. Valid operators are: NOT, AND, OR, IMP, BI\n4. Variables must be in lowercase.\nExample: " +
                "NOTp AND r BI NOTq OR s IMP p\nDo you understand?");
        rules.setFont(new Font("Comic Sans MS", Font.BOLD, 45));
        JOptionPane pane = new JOptionPane(rules, JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION, null,
                new Object[]{}, null);
        yes = new JButton(" YES ");
        no = new JButton(" NO ");
        yes.setFont(new Font("Comic Sans MS", Font.BOLD, 60));
        no.setFont(new Font("Comic Sans MS", Font.BOLD, 60));
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


        /*int understood = JOptionPane.showConfirmDialog(null, rules, "Do you understand?",
                JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);*/

    }
    private static ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == no) {
                System.exit(0);
            }
            if (e.getSource() == yes) {
                dialog.setVisible(false);
                JOptionPane.showInputDialog(null, "Enter your expression");
            }
        }
    };

}
