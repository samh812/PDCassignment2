package BlackJackGame;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class userLogin 
{

    String usersFilePath = "resources/Login.txt"; //Relative path from project
    private final Map<String, Integer> userBalances = new HashMap<>();

    public userLogin() 
    {
        loadUserBalances();
    }

    private void loadUserBalances() 
    {

        try {
            File file = new File(usersFilePath);
            file.getParentFile().mkdirs();

            //Check if the file exists and if not try to create it
            if (!file.exists()) 
            {
                if (file.createNewFile()) {
                    System.out.println("User file created: " + file.getName());
                } else {
                    System.out.println("Program failed to create new file.");
                }
            }

            //If the file exists or was created, load balances
            Scanner fileReader = new Scanner(file);
            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    userBalances.put(parts[0], Integer.parseInt(parts[1]));
                }
            }
            fileReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred while reading the users file.");
        } catch (IOException e) {
            System.out.println("An error occurred while creating or opening the users file.");
        }
    }
    
    private void saveUserBalances() {
    try (FileWriter fileWriter = new FileWriter(usersFilePath, false)) { //auto resource management
        for (Map.Entry<String, Integer> entry : userBalances.entrySet()) {
            fileWriter.write(entry.getKey() + "," + entry.getValue() + "\n");
        }
    } catch (IOException e) {
        System.out.println("An error occurred while writing to the users file.");
    }
}


public String login() //function for user to login
{
    System.out.println("Please enter your name:");
    Scanner scan = new Scanner(System.in);
    String userName = scan.nextLine();
    if(userName.toUpperCase().equals("N"))
    {
        System.out.println("Closing BlackJack");
        System.exit(0);
    }

    if (userBalances.containsKey(userName)) 
    {
        int currentBalance = userBalances.get(userName);
        if (currentBalance <= 0) 
        {
            //If the user's balance is negative, reset it to $100
            System.out.println("Your balance was negative. We've reset it to $100 for a fresh start!");
            userBalances.put(userName, 100); //Reset balance to $100
        } 
        else
        {
            System.out.println("Welcome back, " + userName + "! Your current balance is: $" + currentBalance);
        }
        saveUserBalances(); //save changes to the file
    } 
    else
    {
        System.out.println("User not found. Creating a new account with a default balance of $1000.");
        userBalances.put(userName, 1000); //Create a new user with $1000 balance
        saveUserBalances(); //Save the new user to the file
        System.out.println("Account created. Your current balance is: $1000");
    }
    return userName;
}

    
    public int getBalance(String userName) 
    {
    return userBalances.getOrDefault(userName, 0);
    }

    public void updateBalance(String userName, int newBalance) 
    {
    userBalances.put(userName, newBalance);
    saveUserBalances();
    }
}
