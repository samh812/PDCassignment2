
package GUILogin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class GUI implements ActionListener
{
    //Declare outside constructor for scope
    private static JPanel panel;
    private static JFrame frame;
    private static JLabel userLabel;
    private static JTextField userText;
    private static JLabel passwordLabel;
    private static JPasswordField passwordText;
    private static JButton loginButton;
    private static JButton createButton;
    private static JLabel success;
    
    public GUI()
    {
        //new panel instance
        panel = new JPanel();
        
        //new frame instance
        frame = new JFrame();
        frame.setTitle("Login");
        frame.setSize(350, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //add the panel to the frame
        frame.add(panel);
        
        panel.setLayout(null);
        
        //"User" Label text shown on screen
        userLabel = new JLabel("User");
        userLabel.setBounds(10, 20, 80, 25); //position of where text is shown
        panel.add(userLabel);
        
        //space for user to type username into
        userText = new JTextField();
        userText.setBounds(100, 20, 165, 25); //position of where text box is shown
        panel.add(userText);
        
        //"Password" label text shown on screen
        passwordLabel  = new JLabel("Password"); //position of where text is shown
        passwordLabel.setBounds(10, 50, 80, 25);
        panel.add(passwordLabel);
        
        //space for user to type password into, password text is hidden 
        passwordText = new JPasswordField();
        passwordText.setBounds(100, 50, 165, 25); //position of where text box is shown
        panel.add(passwordText);
        
        //login button
        loginButton = new JButton("Login");
        loginButton.setBounds(10, 80, 80, 25); //button layout
        loginButton.addActionListener(this);
        panel.add(loginButton);
        
        //create user button
        createButton = new JButton("Create User");
        createButton.setBounds(100, 80, 150, 25);
        createButton.addActionListener(this);
        panel.add(createButton);
        
        //message that shows up when button is pressed, currently blank and text will be set when button is pressed by action listener
        success = new JLabel("");
        success.setBounds(10, 110, 300, 25); //position of where text is shown
        panel.add(success);
       
        
        frame.setVisible(true);
        
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        String user = userText.getText();
        String password = passwordText.getText();

        if(user.equals("Seb") && password.equals("123"))
        {
            success.setText("Login Successful!");
        }
        else
        {
            success.setText("Login Incorrect");
        }
    }
    
        
    public static void main(String[] args) 
    {
        GUI gui = new GUI();

    }

    
}
