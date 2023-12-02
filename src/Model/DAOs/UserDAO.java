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

        String query = "select * from users where user_id NOT IN (" + uid + ");";
        try {
            Connection conn = IDAO.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            System.out.println("rs " + rs);

            while (rs.next()) {
                Hashtable<String,String> o = new Hashtable<>();
                o.put("id", rs.getString(1));
                o.put("name", rs.getString(2));
                o.put("u_name", rs.getString(3));
                o.put("role", rs.getString(5));
                data.add(o);
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

        String query = "select username from users;";
        try {
            Connection conn = IDAO.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            System.out.println("rs " + rs);

            while (rs.next()) {
                data.add(rs.getString(1));
            }
        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
        }
        return data;
    }

    /**
     * Load the role of a user based on the user ID.
     *
     * @param UID The user ID.
     * @return The role of the user.
     */
    public String loadRole(Integer UID) {
        String query = "SELECT role FROM users WHERE user_id = ?";
        try (Connection conn = IDAO.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, UID);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("role");
                } else {
                    // Handle the case when no rows are returned (user not found)
                    System.out.println("User not found with ID: " + UID);
                }
            }
        } catch (SQLException e) {
            // Handle SQLException
            System.out.println("SQL Exception: " + e.getErrorCode());
        }
        return "";
    }

    /**
     * Delete a user based on the user ID.
     *
     * @param UID The user ID to delete.
     * @return True if deletion is successful, false otherwise.
     */
    public boolean delete(Integer UID){
        String query = "delete from users where user_id = " + UID + ";";
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
     * Get the user ID based on the username.
     *
     * @param u The username.
     * @return The user ID.
     */
    public Integer getID(String u) {
        String query = "select user_id from users where username = '" + u + "';";
        try {
            Connection conn = IDAO.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            return Integer.parseInt(rs.getString(1));
        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
        }
        return 0;
    }

    /**
     * Get the role of a user based on the user ID.
     *
     * @param id The user ID.
     * @return The role of the user.
     */
    public String getRole(Integer id) {
        String query = "select role from users where username = " + id + ";";
        try {
            Connection conn = IDAO.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            return rs.getString(1);
        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
        }
        return "";
    }

    /**
     * Load all user data.
     *
     * @return ArrayList of Hashtables containing user data.
     */
    public ArrayList<Hashtable<String, String>> load() {
        return null;
    }

    /**
     * Load multiple instances of user data based on the user ID.
     *
     * @param id The user ID.
     * @return ArrayList of Hashtables containing user data.
     */
    public ArrayList<Hashtable<String, String>> loadMultiple(Integer id) {
        return null;
    }

    /**
     * Update the role of a user based on the user ID.
     *
     * @param id The user ID.
     * @return True if the update is successful, false otherwise.
     */
    public boolean update(Integer id) {
        String query = "UPDATE users set role= 'manager' WHERE user_id="+id.toString()+";";
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
    public boolean insert(Hashtable<String, String> data) {
        String query = "insert into users(name, username, password, role) values ('" +
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
     * @param id The user ID.
     * @return Hashtable containing user data.
     */
    public Hashtable<String, String> loadSingle(Integer id) {
        return null;
    }

    /**
     * Load user data based on a keyword search.
     *
     * @param keyword The search keyword.
     * @return ArrayList of Hashtables containing user data.
     */
    public ArrayList<Hashtable<String, String>> loadSearched(String keyword) {
        return null;
    }
}
