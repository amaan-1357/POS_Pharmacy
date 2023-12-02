package Model.StoredProcedures;

import View.AdminPortal;
import View.SalesRepPortal;

import javax.swing.*;
import java.sql.*;

/**
 * The LoginAuthenticate class provides methods to authenticate users by checking credentials in the database.
 */
public class LoginAuthenticate {

    /**
     * Method to authenticate a user by checking credentials in the database.
     *
     * @param u_name The username of the user.
     * @param pass   The password of the user.
     * @return True if the authentication is successful, false otherwise.
     */
    public Boolean authenticate(String u_name, String pass) {
        try {
            // Establish a connection to the database
            Connection conn = getConnection();

            // Prepare a callable statement to call the stored procedure for authentication
            CallableStatement stmt = conn.prepareCall("{CALL AuthenticateUser(?,?)}");
            stmt.setString(1, u_name);
            stmt.setString(2, pass);

            // Execute the query to authenticate the user
            ResultSet rs = stmt.executeQuery();

            // If a result is returned, the user is authenticated
            if (rs.next()) {
                // Extract user information from the result set
                int userId = rs.getInt("user_id");
                String role = rs.getString("role");

                // Open the respective portal based on the user's role
                if (role.equals("manager")) {
                    SwingUtilities.invokeLater(() -> {
                        // Invoke the AdminPortal with the user's ID
                        AdminPortal ap = new AdminPortal(userId);
                    });
                } else {
                    SwingUtilities.invokeLater(() -> {
                        // Invoke the SalesRepPortal for non-manager roles
                        SalesRepPortal srp = new SalesRepPortal();
                    });
                }
                return true; // Authentication successful
            } else {
                // Authentication failed, print an error message
                System.out.println("Authentication failed. Invalid username or password.");
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Method to establish a connection to the database.
     *
     * @return The database connection.
     * @throws SQLException If a database access error occurs.
     */
    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost/pharmacy_pos", "root", "DragonBall1!");
    }
}
