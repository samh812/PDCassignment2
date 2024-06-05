package BlackJackGame;

/**
 *
 * @author samh
 */


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginGUI extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton createAccountButton;
    private JLabel messageLabel;

    private BlackJackDBManager dbManager;

    public LoginGUI() {
        dbManager = new BlackJackDBManager();

        setTitle("Login");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 2));

        usernameField = new JTextField();
        passwordField = new JPasswordField();
        loginButton = new JButton("Login");
        createAccountButton = new JButton("Create Account");
        messageLabel = new JLabel();

        add(new JLabel("Username:"));
        add(usernameField);
        add(new JLabel("Password:"));
        add(passwordField);
        add(loginButton);
        add(createAccountButton);
        add(messageLabel);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                if (dbManager.checkCredentials(username, password)) {
                    messageLabel.setText("Login successful");
                    new BlackJackGUI().setVisible(true);
                    dispose();
                } else {
                    messageLabel.setText("Invalid credentials");
                }
            }
        });

        createAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                if (dbManager.addUser(username, password)) {
                    messageLabel.setText("Account created successfully");
                } else {
                    messageLabel.setText("Failed to create account");
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LoginGUI().setVisible(true);
            }
        });
    }
}
