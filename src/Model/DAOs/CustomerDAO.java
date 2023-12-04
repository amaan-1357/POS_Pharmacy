package Model.DAOs;

import java.sql.*;
import java.util.ArrayList;
import java.util.Hashtable;

/**
 * The CustomerDAO class implements the IDAO interface and provides data access methods
 * for the 'customers' table in the database.
 */
public class CustomerDAO implements IDAO {

    /**
     * Load method not implemented for this class.
     *
     * @return null
     */
    public ArrayList<Hashtable<String, String>> load() {
        return null;
    }

    /**
     * LoadMultiple method not implemented for this class.
     *
     * @return null
     */
    public ArrayList<Hashtable<String, String>> loadMultiple() {
        return null;
    }

    /**
     * Retrieves the customer ID based on the customer name.
     *
     * @param name The customer name.
     * @return The customer ID.
     */
    @SuppressWarnings("SqlSourceToSinkFlow")
    public Integer getCID(String name) {
        String query = "select customer_id from customers where customer_name = '" + name + "'";
        try {
            Connection conn = IDAO.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                return Integer.parseInt(rs.getString(1));
            }
        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
        }
        return 0;
    }

    /**
     * Retrieves the customer name based on the customer ID.
     *
     * @param id The customer ID.
     * @return The customer name.
     */
    public String loadName(Integer id) {
        String query = "select customer_name from customers where customer_id = " + id;
        try {
            Connection conn = IDAO.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
        }
        return null;
    }

    /**
     * Retrieves the maximum customer ID.
     *
     * @return The maximum customer ID.
     */
    public Integer getMaxCID() {
        String query = "select max(customer_id) from customers";
        try {
            Connection conn = IDAO.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                return Integer.parseInt(rs.getString(1));
            }
        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
        }
        return 0;
    }

    /**
     * Update method not implemented for this class.
     *
     * @return false
     */
    public boolean update() {
        return false;
    }

    /**
     * Inserts a new customer into the 'customers' table.
     *
     * @param data A Hashtable containing data for the new customer.
     * @return true if the insertion is successful, false otherwise.
     */
    @SuppressWarnings("SqlSourceToSinkFlow")
    public boolean insert(Hashtable<String, String> data) {
        String query = "insert into customers(customer_name,customer_email) values ('" +
                data.get("name") + "','" + data.get("email") + "');";
        try {
            Connection conn = IDAO.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            return false;
        }
        return true;
    }

    /**
     * Inserts a new customer without an email into the 'customers' table.
     *
     * @param data A Hashtable containing data for the new customer.
     */
    @SuppressWarnings("SqlSourceToSinkFlow")
    public void insertWithoutEmail(Hashtable<String, String> data) {
        String query = "insert into customers(customer_name) values ('" +
                data.get("name") + "');";
        try {
            Connection conn = IDAO.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.executeUpdate();
        } catch (SQLException ignored) {
        }
    }

    /**
     * LoadSingle method not implemented for this class.
     *
     * @return null
     */
    public Hashtable<String, String> loadSingle() {
        return null;
    }
}
