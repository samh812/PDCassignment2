package BlackJackGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class LoginGUI extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton createAccountButton;
    private JButton quitButton;
    private JLabel messageLabel;

    private BlackJackDBManager dbManager;

    public LoginGUI() {
        dbManager = new BlackJackDBManager();

        setTitle("Login");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Background panel
        BackgroundPanel backgroundPanel = new BackgroundPanel("./resources/cards/table.jpg");
        backgroundPanel.setLayout(new GridBagLayout());

        usernameField = new JTextField();
        passwordField = new JPasswordField();
        loginButton = new JButton("Login");
        createAccountButton = new JButton("Create Account");
        quitButton = new JButton("Quit");
        messageLabel = new JLabel();

        // Change text color to white
        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");
        usernameLabel.setForeground(Color.WHITE);
        passwordLabel.setForeground(Color.WHITE);
        usernameField.setForeground(Color.BLACK);
        passwordField.setForeground(Color.BLACK);
        loginButton.setForeground(Color.BLACK);
        createAccountButton.setForeground(Color.BLACK);
        quitButton.setForeground(Color.BLACK);
        messageLabel.setForeground(Color.WHITE);

        // Set size for text fields and buttons
        Dimension fieldSize = new Dimension(200, 30);
        usernameLabel.setPreferredSize(new Dimension(120, 30));
        passwordLabel.setPreferredSize(new Dimension(120, 30));
        usernameField.setPreferredSize(fieldSize);
        passwordField.setPreferredSize(fieldSize);
        loginButton.setPreferredSize(new Dimension(120, 30)); // Size for buttons
        createAccountButton.setPreferredSize(new Dimension(140, 30)); // Size for buttons
        quitButton.setPreferredSize(new Dimension(120, 30)); // Size for buttons

        // Add components to background panel using GridBagLayout
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Padding

        gbc.gridx = 0;
        gbc.gridy = 0;
        backgroundPanel.add(usernameLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        backgroundPanel.add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        backgroundPanel.add(passwordLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        backgroundPanel.add(passwordField, gbc);

        // Panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false); // Make button panel transparent
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0)); // Center align buttons with some horizontal gap

        buttonPanel.add(loginButton);
        buttonPanel.add(createAccountButton);
        buttonPanel.add(quitButton);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        backgroundPanel.add(buttonPanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        backgroundPanel.add(messageLabel, gbc);

        add(backgroundPanel, BorderLayout.CENTER);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                if (dbManager.checkCredentials(username, password)) {
                    dispose();
                     new BlackJackGUI(username).setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid login credentials");
                }
            }
        });

        createAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                if (username.isEmpty() || password.isEmpty()) {
                    messageLabel.setForeground(Color.RED);
                    messageLabel.setText("Username and password fields cannot be empty.");
                } else {
                    if (dbManager.addUser(username, password)) {
                        messageLabel.setForeground(Color.WHITE);
                        messageLabel.setText("Account created successfully! You can log in now.");
                        usernameField.setText(""); // Clear username
                        passwordField.setText(""); // Clear password
                    } else {
                        messageLabel.setForeground(Color.RED); // Set color to red for failure
                        messageLabel.setText("Username already exists.");
                    }
                }
            }
        });

        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // Exit the application
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

    // Custom panel for background image
    class BackgroundPanel extends JPanel {
        private Image backgroundImage;

        public BackgroundPanel(String imagePath) {
            try {
                backgroundImage = new ImageIcon(imagePath).getImage();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (backgroundImage != null) {
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }
}
