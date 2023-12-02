package Model.DAOs;

import java.sql.*;
import java.util.ArrayList;
import java.util.Hashtable;

/**
 * The ProductCategoryDAO class provides data access methods for interacting with the product_categories table.
 */
public class ProductCategoryDAO implements IDAO {

    /**
     * Retrieves all product categories from the database.
     *
     * @return ArrayList of Hashtables containing category information.
     */
    public ArrayList<Hashtable<String, String>> load() {
        ArrayList<Hashtable<String, String>> data = new ArrayList<>();

        // SQL query to select all rows from the product_categories table
        String query = "SELECT * FROM product_categories";

        try {
            Connection conn = IDAO.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            // Process the result set and populate the data ArrayList
            while (rs.next()) {
                Hashtable<String, String> categoryInfo = new Hashtable<>();
                categoryInfo.put("id", rs.getString(1));
                categoryInfo.put("name", rs.getString(2));
                data.add(categoryInfo);
            }
        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
        }
        return data;
    }

    /**
     * Retrieves names of all product categories from the database.
     *
     * @return ArrayList of category names.
     */
    public ArrayList<String> loadNames() {
        ArrayList<String> data = new ArrayList<>();

        // SQL query to select category_name column from the product_categories table
        String query = "SELECT category_name FROM product_categories";

        try {
            Connection conn = IDAO.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            // Process the result set and populate the data ArrayList with category names
            while (rs.next()) {
                data.add(rs.getString(1));
            }
        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
        }
        return data;
    }

    /**
     * Retrieves multiple product categories based on the given category ID.
     *
     * @param id Category ID
     * @return ArrayList of Hashtables containing category information.
     */
    public ArrayList<Hashtable<String, String>> loadMultiple(Integer id) {
        ArrayList<Hashtable<String, String>> data = new ArrayList<>();

        // SQL query to select rows from the product_categories table where category_id matches the given id
        String query = "SELECT * FROM product_categories WHERE category_id = " + id.toString();

        try {
            Connection conn = IDAO.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            // Process the result set and populate the data ArrayList
            while (rs.next()) {
                Hashtable<String, String> categoryInfo = new Hashtable<>();
                categoryInfo.put("id", rs.getString(1));
                categoryInfo.put("name", rs.getString(2));
                data.add(categoryInfo);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return data;
    }

    /**
     * Updates product category information based on the provided data Hashtable.
     *
     * @param data Hashtable containing updated category information.
     * @return True if the update is successful, false otherwise.
     */
    public boolean update(Hashtable<String, String> data) {
        return false; // Not implemented
    }

    /**
     * Inserts a new product category with the given name into the database.
     *
     * @param name Category name to be inserted.
     * @return True if the insertion is successful, false otherwise.
     */
    public boolean insert(String name) {
        String query = "INSERT INTO product_categories(category_name) VALUES ('" + name + "')";
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
     * Retrieves a single product category based on the given category ID.
     *
     * @param id Category ID
     * @return Hashtable containing category information.
     */
    public Hashtable<String, String> loadSingle(Integer id) {
        Hashtable<String, String> data = new Hashtable<>();

        // SQL query to select a row from the product_categories table where category_id matches the given id
        String query = "SELECT * FROM product_categories WHERE category_id = " + id.toString();

        try {
            Connection conn = IDAO.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            // Process the result set and populate the data Hashtable
            while (rs.next()) {
                data.put("id", rs.getString(1));
                data.put("name", rs.getString(2));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return data;
    }

    /**
     * Retrieves product categories based on a searched keyword (not implemented).
     *
     * @param keyword Searched keyword
     * @return ArrayList of Hashtables containing category information.
     */
    public ArrayList<Hashtable<String, String>> loadSearched(String keyword) {
        return null; // Not implemented
    }
}
