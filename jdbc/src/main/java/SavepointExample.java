import java.sql.*;

public class SavepointExample {
    public static void main(String[] args) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", "username", "password");
            
            connection.setAutoCommit(false); // Disable auto-commit mode
            
            // Perform some operations within the transaction
            Statement statement = connection.createStatement();
            statement.executeUpdate("UPDATE account SET balance = balance - 100 WHERE account_number = 'A1'"); // Deduct $100 from Account A
            
            Savepoint transferSavepoint = connection.setSavepoint("transfer_savepoint"); // Set a savepoint
            
            statement.executeUpdate("UPDATE account SET balance = balance + 100 WHERE account_number = 'B1'"); // Add $100 to Account B
            
            // Check if the transfer is successful
            boolean transferSuccessful = true; // Assume transfer is successful for this example
            
            if (transferSuccessful) {
                connection.commit(); // Commit the transaction if successful
                System.out.println("Transfer successful. Transaction committed.");
            } else {
                connection.rollback(transferSavepoint); // Roll back to the savepoint if transfer fails
                System.out.println("Transfer failed. Rolled back to savepoint.");
                // Additional error handling or logging can be done here
            }
            
            connection.setAutoCommit(true); // Re-enable auto-commit mode
            
            // Close the connection and resources
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
