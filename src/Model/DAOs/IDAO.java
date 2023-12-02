package Model.DAOs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public interface IDAO {

    static Connection getConnection()
            throws SQLException
    {
        return DriverManager.getConnection("jdbc:mysql://localhost/pharmacy_pos","root","DragonBall1!");
    }

}
