package BlackJackGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class BlackJackGUI extends JFrame {
    private JButton hitButton;
    private JButton standButton;
    private JButton newGameButton;
    private BackgroundPanel backgroundPanel;
    private JPanel playerCardPanel;
    private JPanel dealerCardPanel;
    private JPanel buttonPanel;
    private JPanel scorePanel;
    private JLabel playerScoreLabel;
    private JLabel dealerScoreLabel;
    private BlackJackGame game;
    private JLabel dealerFaceDownCardLabel;

    public BlackJackGUI() {
        game = new BlackJackGame();

        setTitle("BlackJack Game");
        setSize(800, 600); // Increased size to accommodate more cards
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create the background panel
        backgroundPanel = new BackgroundPanel("./resources/cards/table.jpg"); // Update with your background image path
        backgroundPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Create components
        hitButton = new JButton("Hit");
        standButton = new JButton("Stand");
        newGameButton = new JButton("New Game");
        playerCardPanel = new JPanel();
        playerCardPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10)); // Use FlowLayout for arranging cards horizontally
        playerCardPanel.setOpaque(false); // Make playerCardPanel transparent
        dealerCardPanel = new JPanel();
        dealerCardPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10)); // Use FlowLayout for arranging cards horizontally
        dealerCardPanel.setOpaque(false); // Make dealerCardPanel transparent
        playerScoreLabel = new JLabel("Player Score: 0");
        playerScoreLabel.setForeground(Color.WHITE);
        dealerScoreLabel = new JLabel("Dealer Score: 0");
        dealerScoreLabel.setForeground(Color.WHITE);

        // Create the button panel
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(hitButton);
        buttonPanel.add(standButton);
        buttonPanel.add(newGameButton);
        buttonPanel.setOpaque(false); // Make buttonPanel transparent

        // Create the score panel
        scorePanel = new JPanel();
        scorePanel.setLayout(new FlowLayout());
        scorePanel.add(playerScoreLabel);
        scorePanel.add(dealerScoreLabel);
        scorePanel.setOpaque(false); // Make scorePanel transparent

        // Add dealerCardPanel to the top
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        backgroundPanel.add(dealerCardPanel, gbc);

        // Add playerCardPanel to the bottom center
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        backgroundPanel.add(playerCardPanel, gbc);

        // Add buttonPanel below the playerCardPanel
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        backgroundPanel.add(buttonPanel, gbc);

        // Add scorePanel below the buttonPanel
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.CENTER;
        backgroundPanel.add(scorePanel, gbc);

        // Add background panel to the frame
        add(backgroundPanel, BorderLayout.CENTER);

        // Add action listeners
        hitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Card card = game.hit();
                if (card == null) {
                    JOptionPane.showMessageDialog(null, "You cannot draw any more cards!");
                } else {
                    displayPlayerCard(card.getIndex());
                    updateScores();
                    if (game.hasPlayerBusted()) {
                        hitButton.setEnabled(false); // Disable hit button
                        standButton.setEnabled(false); // Disable stand button
                        dealerTurn();
                    }
                }
            }
        });

        standButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hitButton.setEnabled(false);
                standButton.setEnabled(false);
                dealerTurn();
            }
        });

        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.newGame();
                playerCardPanel.removeAll();
                dealerCardPanel.removeAll();
                playerCardPanel.revalidate();
                playerCardPanel.repaint();
                dealerCardPanel.revalidate();
                dealerCardPanel.repaint();
                displayInitialCards();
                updateScores();
                hitButton.setEnabled(true); // Enable hit button for new game
                standButton.setEnabled(true); // Enable stand button for new game
            }
        });

        // Display initial cards at the start
        displayInitialCards();
        updateScores();
    }

    private void displayPlayerCard(int cardValue) {
        String imagePath = "./resources/cards/" + cardValue + ".png"; // Update with your image path
        ImageIcon cardIcon = new ImageIcon(imagePath);
        Image image = cardIcon.getImage(); // Transform it
        Image scaledImage = image.getScaledInstance(100, 150, java.awt.Image.SCALE_SMOOTH); // Scale it to desired size
        cardIcon = new ImageIcon(scaledImage);  // Transform it back

        JLabel cardLabel = new JLabel(cardIcon); // Create a new JLabel for the card
        playerCardPanel.add(cardLabel); // Add the new card to the panel
        playerCardPanel.revalidate(); // Refresh the panel to display the new card
        playerCardPanel.repaint();
    }

    private void displayDealerCard(int cardValue) {
        String imagePath = "./resources/cards/" + cardValue + ".png"; // Update with your image path
        ImageIcon cardIcon = new ImageIcon(imagePath);
        Image image = cardIcon.getImage(); // Transform it
        Image scaledImage = image.getScaledInstance(100, 150, java.awt.Image.SCALE_SMOOTH); // Scale it to desired size
        cardIcon = new ImageIcon(scaledImage);  // Transform it back

        JLabel cardLabel = new JLabel(cardIcon); // Create a new JLabel for the card
        dealerCardPanel.add(cardLabel); // Add the new card to the panel
        dealerCardPanel.revalidate(); // Refresh the panel to display the new card
        dealerCardPanel.repaint();
    }

    private void displayDealerCards() {
        dealerCardPanel.removeAll();
        List<Card> dealerHand = game.getDealerHand();
        for (int i = 0; i < dealerHand.size(); i++) {
            if (i == 1 && !game.isDealerCardRevealed()) {
                displayFaceDownCard();
            } else {
                displayDealerCard(dealerHand.get(i).getIndex());
            }
        }
    }

    private void displayFaceDownCard() {
        String imagePath = "./resources/cards/facedown.jpg"; // Update with your face-down card image path
        ImageIcon cardIcon = new ImageIcon(imagePath);
        Image image = cardIcon.getImage(); // Transform it
        Image scaledImage = image.getScaledInstance(100, 150, java.awt.Image.SCALE_SMOOTH); // Scale it to desired size
        cardIcon = new ImageIcon(scaledImage);  // Transform it back

        dealerFaceDownCardLabel = new JLabel(cardIcon); // Create a new JLabel for the face-down card
        dealerCardPanel.add(dealerFaceDownCardLabel); // Add the face-down card to the panel
        dealerCardPanel.revalidate(); // Refresh the panel to display the new card
        dealerCardPanel.repaint();
    }

    private void displayInitialCards() {
        List<Card> playerHand = game.getPlayerHand();
        List<Card> dealerHand = game.getDealerHand();
        for (Card card : playerHand) {
            displayPlayerCard(card.getIndex());
        }
        for (int i = 0; i < dealerHand.size(); i++) {
            if (i == 1) {
                displayFaceDownCard();
            } else {
                displayDealerCard(dealerHand.get(i).getIndex());
            }
        }
    }

    private void dealerTurn() {
        game.revealDealerCard();
        displayDealerCards();
        while (game.getDealerScore() < 17) {
            Card card = game.dealerHit();
            displayDealerCard(card.getIndex());
            updateScores();
        }

        String resultMessage;

        if (game.getPlayerScore() > 21) {
            resultMessage = "You went over 21! Dealer wins!";
        } else if (game.hasDealerBusted()) {
            resultMessage = "Dealer went over 21! You win!";
        } else if (game.getDealerScore() > game.getPlayerScore()) {
            resultMessage = "Dealer wins!";
        } else if (game.getDealerScore() == game.getPlayerScore()) {
            resultMessage = "It's a tie!";
        } else {
            resultMessage = "You win!";
        }

        JOptionPane.showMessageDialog(null, resultMessage);
    }

    private void updateScores() {
        int playerScore = game.getPlayerScore();
        int dealerScore = game.getDealerScore();
        playerScoreLabel.setText("Player Score: " + playerScore);
        dealerScoreLabel.setText("Dealer Score: " + dealerScore);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new BlackJackGUI().setVisible(true);
            }
        });
    }
}
