package BlackJackGame;

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
        setupDatabase();
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

    // Setup database tables
    private void setupDatabase() {
        try ( Statement stmt = conn.createStatement()) {
            // Check if table exists
            ResultSet rs = conn.getMetaData().getTables(null, null, "USERS", null);
            if (!rs.next()) {
                // Table does not exist, create it
                stmt.executeUpdate("CREATE TABLE users (username VARCHAR(50) PRIMARY KEY, password VARCHAR(50), balance DECIMAL(20, 2) DEFAULT 1000.00)");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    // Check user credentials
    public boolean checkCredentials(String username, String password) {
        try ( PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?")) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Add a new user
    public boolean addUser(String username, String password) {
        try {
            PreparedStatement insertStmt = conn.prepareStatement("INSERT INTO users (username, password) VALUES (?, ?)");
            insertStmt.setString(1, username);
            insertStmt.setString(2, password);
            int rowsAffected = insertStmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            // Failed to insert due to primary key violation
            return false;
        }
    }

    // Close database connection
    public void closeConnections() {
        if (conn != null) {
            try {
                conn.close();
                System.out.println("Connection closed successfully");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public double getUserBalance(String username) {
        double balance = 0.0;
        try ( PreparedStatement stmt = getConnection().prepareStatement("SELECT balance FROM users WHERE username = ?")) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                balance = rs.getDouble("balance");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return balance;
    }

    public void placeBet(Betting bet) throws SQLException {
        try ( PreparedStatement stmt = getConnection().prepareStatement("UPDATE users SET balance = balance - ? WHERE username = ? AND balance >= ?")) {
            stmt.setDouble(1, bet.getBetAmount());
            stmt.setString(2, bet.getUsername());
            stmt.setDouble(3, bet.getBetAmount());
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated == 0) {
                throw new SQLException("Insufficient balance or user not found.");
            }
        }
    }

    public void updateUserBalance(String username, double amount) throws SQLException {
        try ( PreparedStatement stmt = getConnection().prepareStatement("UPDATE users SET balance = balance + ? WHERE username = ?")) {
            stmt.setDouble(1, amount);
            stmt.setString(2, username);
            stmt.executeUpdate();
        }
    }

    public void deleteUser(String username) throws SQLException {
        try ( PreparedStatement stmt = getConnection().prepareStatement("DELETE FROM users WHERE username = ?")) {
            stmt.setString(1, username);
            stmt.executeUpdate();
        }
    }
}
