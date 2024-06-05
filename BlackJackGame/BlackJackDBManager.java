/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BlackJackGame;

/**
 *
 * @author samh
 */



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BlackJackDBManager {
    private static final String URL = "jdbc:derby:BlackJackDB;create=true";
    private static final String USER_NAME = "pdc"; // your DB username
    private static final String PASSWORD = "pdc"; // your DB password

    private Connection conn;

    public BlackJackDBManager() {
        establishConnection();
//        setupDatabase();
    }

    public Connection getConnection() {
        return this.conn;
    }

    // Establish connection
    public void establishConnection() {
        if (this.conn == null) {
            try {
                conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
                System.out.println(URL + " Get Connected Successfully ....");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    // Setup the database
//    private void setupDatabase() {
//        try (Statement stmt = conn.createStatement()) {
//            stmt.executeUpdate("CREATE TABLE users (username VARCHAR(50) PRIMARY KEY, password VARCHAR(50))");
//        } catch (SQLException ex) {
//            if (!ex.getSQLState().equals("X0Y32")) {
//                System.out.println(ex.getMessage());
//            }
//        }
//    }

    public boolean checkCredentials(String username, String password) {
        try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?")) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean addUser(String username, String password) {
        try (PreparedStatement stmt = conn.prepareStatement("INSERT INTO users (username, password) VALUES (?, ?)")) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void closeConnections() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}