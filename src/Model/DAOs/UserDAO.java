package Model.DAOs;

import java.sql.*;
import java.util.ArrayList;
import java.util.Hashtable;

/**
 * UserDAO class represents a Data Access Object for managing user-related data in the database.
 */
public class UserDAO implements IDAO {

    /**
     * Load user data excluding the specified user ID.
     *
     * @param uid The user ID to exclude.
     * @return ArrayList of Hashtables containing user data.
     */
    public ArrayList<Hashtable<String, String>> load(Integer uid) {
        ArrayList<Hashtable<String,String>> data = new ArrayList<>();

        // SQL query to select user data excluding the specified user ID
        String query = "SELECT * FROM users WHERE user_id NOT IN (" + uid + ");";
        try {
            Connection conn = IDAO.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            // Process the result set and populate the data ArrayList
            while (rs.next()) {
                Hashtable<String,String> user = new Hashtable<>();
                user.put("id", rs.getString(1));
                user.put("name", rs.getString(2));
                user.put("u_name", rs.getString(3));
                user.put("role", rs.getString(5));
                data.add(user);
            }
        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
        }
        return data;
    }

    /**
     * Load all usernames from the users table.
     *
     * @return ArrayList of usernames.
     */
    public ArrayList<String> loadUNames() {
        ArrayList<String> data = new ArrayList<>();

        // SQL query to select all usernames from the users table
        String query = "SELECT username FROM users;";
        try {
            Connection conn = IDAO.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            // Process the result set and populate the data ArrayList
            while (rs.next()) {
                data.add(rs.getString(1));
            }
        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
        }
        return data;
    }

    /**
     * Delete a user based on the user ID.
     *
     * @param UID The user ID to delete.
     */
    public void delete(Integer UID){
        // SQL query to delete a user based on the user ID
        String query = "DELETE FROM users WHERE user_id = " + UID + ";";
        try {
            Connection conn = IDAO.getConnection();
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
        }
    }

    /**
     * Load all user data.
     *
     * @return ArrayList of Hashtables containing user data.
     */
    public ArrayList<Hashtable<String, String>> load() {
        return null; // Not implemented
    }

    /**
     * Load multiple instances of user data based on the user ID.
     *
     * @return ArrayList of Hashtables containing user data.
     */
    public ArrayList<Hashtable<String, String>> loadMultiple() {
        return null; // Not implemented
    }

    /**
     * Update the role of a user based on the user ID.
     *
     * @param id The user ID.
     * @return True if the update is successful, false otherwise.
     */
    public boolean update(Integer id) {
        // SQL query to update the role of a user based on the user ID
        String query = "UPDATE users SET role = 'manager' WHERE user_id = " + id.toString() + ";";
        try {
            Connection conn = IDAO.getConnection();
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(query);
            return true;
        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
        }
        return false;
    }

    /**
     * Insert a new user into the users table.
     *
     * @param data Hashtable containing user data.
     * @return True if insertion is successful, false otherwise.
     */
    @SuppressWarnings("SqlSourceToSinkFlow")
    public boolean insert(Hashtable<String, String> data) {
        // SQL query to insert a new user into the users table
        String query = "INSERT INTO users(name, username, password, role) VALUES ('" +
                data.get("name") + "','" + data.get("u_name") + "','" + data.get("pass") +"','" + data.get("role") + "');";
        try{
            Connection conn = IDAO.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.executeUpdate();
        } catch(SQLException ex){
            return false;
        }
        return true;
    }

    /**
     * Load a single instance of user data based on the user ID.
     *
     * @return Hashtable containing user data.
     */
    public Hashtable<String, String> loadSingle() {
        return null; // Not implemented
    }
}
