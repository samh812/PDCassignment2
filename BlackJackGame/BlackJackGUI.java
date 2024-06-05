/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package BlackJackGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author samh
 */
public class BlackJackGUI extends JFrame {

    private JButton hitButton;
    private JButton standButton;
    private JButton newGameButton;
    private JLabel playerLabel;
    private JLabel dealerLabel;
    private JTextArea gameTextArea;
    
    private BlackJackGame game;

    public BlackJackGUI() {
        game = new BlackJackGame();

        setTitle("BlackJack Game");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create components
        hitButton = new JButton("Hit");
        standButton = new JButton("Stand");
        newGameButton = new JButton("New Game");
        playerLabel = new JLabel("Player:");
        dealerLabel = new JLabel("Dealer:");
        gameTextArea = new JTextArea();
        gameTextArea.setEditable(false);

        // Add components to frame
        JPanel topPanel = new JPanel();
        topPanel.add(playerLabel);
        topPanel.add(dealerLabel);

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(hitButton);
        bottomPanel.add(standButton);
        bottomPanel.add(newGameButton);

        add(topPanel, BorderLayout.NORTH);
        add(new JScrollPane(gameTextArea), BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        // Add action listeners
        hitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.hit();
                updateGameText();
            }
        });

        standButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.stand();
                updateGameText();
            }
        });

        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.newGame();
                updateGameText();
            }
        });

        updateGameText();
    }

    private void updateGameText() {
        // Update the game text area with the current game state
        gameTextArea.setText(game.getGameState());
        playerLabel.setText("Player: " + game.getPlayerScore());
        dealerLabel.setText("Dealer: " + game.getDealerScore());
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
