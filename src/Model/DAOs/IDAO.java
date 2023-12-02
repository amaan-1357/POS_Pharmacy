package Model.DAOs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * The IDAO interface provides a static method for obtaining a database connection.
 */
public interface IDAO {

    /**
     * Gets a database connection.
     *
     * @return A Connection object representing a connection to the database.
     * @throws SQLException If a database access error occurs.
     */
    static Connection getConnection() throws SQLException {
        // Establish a connection to the MySQL database using JDBC
        return DriverManager.getConnection("jdbc:mysql://localhost/pharmacy_pos", "root", "DragonBall1!");
    }
}
