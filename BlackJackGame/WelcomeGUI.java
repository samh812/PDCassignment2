package BlackJackGame;

/**
 *
 * @author samh
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomeGUI extends JFrame {
    private JLabel greetingLabel;
    private JLabel rulesLabel;
    private JButton loginButton;
    private JButton quitButton;

    public WelcomeGUI() {
        setTitle("Welcome");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a custom panel with a background image
        BackgroundPanel backgroundPanel = new BackgroundPanel(new ImageIcon("./resources/cards/table.jpg").getImage());
        backgroundPanel.setLayout(new GridBagLayout());
        setContentPane(backgroundPanel);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;

        // Greeting label
        greetingLabel = new JLabel("Welcome to BlackJack!");
        greetingLabel.setFont(new Font("Arial", Font.BOLD, 24));
        greetingLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        backgroundPanel.add(greetingLabel, gbc);

        // Rules label
        rulesLabel = new JLabel("<html>Press the login button to log in to your account<br>" +
                                "You can press the quit button at any time to quit the game <br>"+
                                "Once you have logged in, you must place a bet for the game to start<br>"+
                                "Have fun!</html>");
        rulesLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        rulesLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        backgroundPanel.add(rulesLabel, gbc);

        // Login button
        loginButton = new JButton("Login");
        loginButton.setFont(new Font("Arial", Font.PLAIN, 18));
        Dimension buttonSize = new Dimension(220, 40);
        loginButton.setPreferredSize(buttonSize);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        backgroundPanel.add(loginButton, gbc);

        // Quit button
        quitButton = new JButton("Quit");
        quitButton.setFont(new Font("Arial", Font.PLAIN, 18));
        quitButton.setPreferredSize(buttonSize);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        backgroundPanel.add(quitButton, gbc);

        // Login button action listener
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // close the WelcomeGUI
                new LoginGUI().setVisible(true); // open the LoginGUI
            }
        });

        // Quit button action listener
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // close the application
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new WelcomeGUI().setVisible(true);
            }
        });
    }

    // Custom JPanel to paint the background image
    class BackgroundPanel extends JPanel {
        private Image backgroundImage;

        public BackgroundPanel(Image backgroundImage) {
            this.backgroundImage = backgroundImage;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
