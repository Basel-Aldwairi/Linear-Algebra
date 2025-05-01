import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LinearGUI extends JFrame{
    private JButton button1;
    private JPanel panelMain;
    private JLabel label1;
    private JTextField nameField;
   // private JLabel lab;

    public LinearGUI() {


        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(button1,"Hello " + nameField.getText());
            }
        });
    }


    public static void main(String[] args) {
        LinearGUI ln = new LinearGUI();
        ln.setContentPane(ln.panelMain);
        ln.nameField.setSize(50,10);
        ln.setTitle("Linear Algebra GUI");
        ln.setSize(300,200);
        ln.setVisible(true);
        ln.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ln.setBounds(600,200,200,100);

    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
