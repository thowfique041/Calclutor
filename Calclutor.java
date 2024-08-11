
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calclutor extends JFrame implements ActionListener {
    JButton[] numButton = new JButton[10];
    JButton[] operButton = new JButton[6];  // Adjusted to 6 for '+', '-', '*', '/', '.', '='
    JButton ac, dl;
    JTextField inputField;
    Font myFont = new Font("Arial", Font.BOLD, 20);
    double num1 = 0, num2 = 0, r = 0;
    char op = ' ';
    ImageIcon img = new ImageIcon("Icon.gif");

    Calclutor() {
        super("Calculator");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(450, 600);
        this.setLayout(null);
        this.getContentPane().setBackground(Color.gray);
        this.setLocationRelativeTo(null);
        this.setIconImage(img.getImage());

        // Input field
        inputField = new JTextField();
        inputField.setBounds(10, 10, 420, 90);
        inputField.setFont(new Font("Arial", Font.BOLD, 30));
        inputField.setForeground(Color.white);
        inputField.setBackground(new Color(0x12345));
        inputField.setEditable(false);
        this.add(inputField);

        // AC button
        ac = new JButton("AC");
        ac.setBounds(120, 500, 200, 60);
        ac.setFont(myFont);
        ac.setBackground(Color.black);
        ac.setForeground(Color.white);
        ac.addActionListener(this);
        ac.setFocusable(false);
        this.add(ac);

        // Delete button
        dl = new JButton("Del");
        dl.setBounds(350, 500, 80, 60);
        dl.setFont(myFont);
        dl.setBackground(Color.black);
        dl.setForeground(Color.white);
        dl.addActionListener(this);
        dl.setFocusable(false);
        this.add(dl);

        // Operator buttons
        operButton[0] = new JButton("+");
        operButton[1] = new JButton("-");
        operButton[2] = new JButton("*");
        operButton[3] = new JButton("/");
        operButton[4] = new JButton(".");
        operButton[5] = new JButton("=");

        for (int i = 0; i < 6; i++) {
            operButton[i].setForeground(Color.white);
            operButton[i].setBackground(Color.black);
            operButton[i].setFocusable(false);
            operButton[i].setFont(myFont);
            operButton[i].addActionListener(this);
        }

        // Number buttons
        for (int i = 0; i < 10; i++) {
            numButton[i] = new JButton(String.valueOf(i));
            numButton[i].setForeground(Color.white);
            numButton[i].setBackground(Color.black);
            numButton[i].setFocusable(false);
            numButton[i].setFont(myFont);
            numButton[i].addActionListener(this);
        }

        // Panel to hold buttons
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new GridLayout(4, 4, 10, 10));
        jPanel.setBounds(10, 100, 420, 380);
        jPanel.setBackground(Color.gray);
        jPanel.setOpaque(true);

        // Add number and operator buttons to the panel in the correct order
        jPanel.add(numButton[1]);
        jPanel.add(numButton[2]);
        jPanel.add(numButton[3]);
        jPanel.add(operButton[3]);  // /

        jPanel.add(numButton[4]);
        jPanel.add(numButton[5]);
        jPanel.add(numButton[6]);
        jPanel.add(operButton[2]);  // *

        jPanel.add(numButton[7]);
        jPanel.add(numButton[8]);
        jPanel.add(numButton[9]);
        jPanel.add(operButton[1]);  // -

        jPanel.add(operButton[4]);  // .
        jPanel.add(numButton[0]);
        jPanel.add(operButton[0]);  // +
        jPanel.add(operButton[5]);  // =

        this.add(jPanel);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 10; i++) {
            if (e.getSource() == numButton[i]) {
                inputField.setText(inputField.getText().concat(String.valueOf(i)));
            }
        }
        if (e.getSource() == operButton[4]) {  // .
            inputField.setText(inputField.getText().concat("."));
        }
        if (e.getSource() == ac) {  // AC
            r = 0;
            num1 = 0;
            num2 = 0;
            inputField.setText(null);
        }
        if (e.getSource() == dl && inputField.getText().length() > 0) {  // Delete last char
            inputField.setText(inputField.getText().substring(0, inputField.getText().length() - 1));
        }
        if (e.getSource() == operButton[0]) {  // +
            num1 = Double.parseDouble(inputField.getText());
            inputField.setText(null);
            op = '+';
        }
        if (e.getSource() == operButton[1]) {  // -
            num1 = Double.parseDouble(inputField.getText());
            inputField.setText(null);
            op = '-';
        }
        if (e.getSource() == operButton[2]) {  // *
            num1 = Double.parseDouble(inputField.getText());
            inputField.setText(null);
            op = '*';
        }
        if (e.getSource() == operButton[3]) {  // /
            num1 = Double.parseDouble(inputField.getText());
            inputField.setText(null);
            op = '/';
        }
        if (e.getSource() == operButton[5]) {  // =
            num2 = Double.parseDouble(inputField.getText());
            boolean f = true;
            switch (op) {
                case '+':
                    r = num1 + num2;
                    break;
                case '-':
                    r = num1 - num2;
                    break;
                case '*':
                    r = num1 * num2;
                    break;
                case '/':
                    if (num1==0) {
                        f=!f;
                    }
                    else r = num1 / num2;
                    break;
                default:
                    r = num2;
                    break;
            }
            if(f) inputField.setText(String.valueOf(r));
            else inputField.setText("Syntax error");
            num1 = r;
            op=' ';
            
        }
    }

    public static void main(String[] args) {
        new Calclutor();
    }
}
