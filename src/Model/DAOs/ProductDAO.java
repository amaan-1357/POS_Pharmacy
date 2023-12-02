package Model.DAOs;

import java.sql.*;
import java.util.ArrayList;
import java.util.Hashtable;

/**
 * The ProductDAO class provides data access methods for interacting with the products table.
 */
public class ProductDAO implements IDAO {

    /**
     * Retrieves all products from the database.
     *
     * @return ArrayList of Hashtables containing product information.
     */
    public ArrayList<Hashtable<String, String>> load() {
        ArrayList<Hashtable<String,String>> data = new ArrayList<>();

        // SQL query to select all rows from the products table
        String query = "SELECT * FROM products";

        try {
            Connection conn = IDAO.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            // Process the result set and populate the data ArrayList
            while (rs.next()) {
                Hashtable<String,String> productInfo = new Hashtable<>();
                productInfo.put("id", rs.getString(1));
                productInfo.put("name", rs.getString(2));
                productInfo.put("price", rs.getString(3));
                productInfo.put("category_id", rs.getString(4));
                productInfo.put("supplier_id", rs.getString(5));
                productInfo.put("quantity", rs.getString(6));
                productInfo.put("limit", rs.getString(7));
                productInfo.put("status", rs.getString(8));
                data.add(productInfo);
            }
        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
        }
        return data;
    }

    /**
     * Retrieves the product ID based on the given product name.
     *
     * @param name Product name
     * @return Product ID or 0 if not found.
     */
    public Integer getPID(String name){
        String query = "SELECT product_id FROM products WHERE product_name = '"+name+"'";
        try {
            Connection conn = IDAO.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if(rs.next()){
                return Integer.parseInt(rs.getString(1));
            }
        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
        }
        return 0;
    }

    /**
     * Retrieves multiple products based on the given product ID.
     *
     * @param id Product ID
     * @return ArrayList of Hashtables containing product information.
     */
    public ArrayList<Hashtable<String, String>> loadMultiple(Integer id) {
        ArrayList<Hashtable<String,String>> data = new ArrayList<>();
        String query = "SELECT * FROM products WHERE product_id = "+id.toString();
        try{
            Connection conn = IDAO.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()){
                Hashtable<String,String> productInfo = new Hashtable<>();
                productInfo.put("id", rs.getString(1));
                productInfo.put("name",rs.getString(2));
                productInfo.put("price", rs.getString(3));
                productInfo.put("category_id", rs.getString(4));
                productInfo.put("supplier_id", rs.getString(5));
                productInfo.put("quantity", rs.getString(6));
                productInfo.put("limit", rs.getString(7));
                productInfo.put("status", rs.getString(8));
                data.add(productInfo);
            }
        } catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return data;
    }

    /**
     * Updates product information based on the provided data Hashtable (not implemented).
     *
     * @param data Hashtable containing updated product information.
     * @return Always returns false (not implemented).
     */
    public boolean update(Hashtable<String, String> data) {
        return false; // Not implemented
    }

    /**
     * Updates the quantity of a product in the database.
     *
     * @param PID      Product ID
     * @param quantity New quantity value
     * @return True if the update is successful, false otherwise.
     */
    public boolean updateQuantity(Integer PID, Integer quantity){
        String query = "UPDATE products SET quantity = " + quantity.toString() + " WHERE product_id = " + PID + ";";
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
     * Inserts a new product into the database (not implemented).
     *
     * @param data Hashtable containing product information.
     * @return Always returns false (not implemented).
     */
    public boolean insert(Hashtable<String, String> data) {
        return false; // Not implemented
    }

    /**
     * Retrieves a single product based on the given product ID.
     *
     * @param id Product ID
     * @return Hashtable containing product information.
     */
    public Hashtable<String, String> loadSingle(Integer id) {
        Hashtable<String,String> data = new Hashtable<>();
        String query = "SELECT * FROM products WHERE product_id = "+id.toString();
        try{
            Connection conn = IDAO.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()){
                Hashtable<String,String> productInfo = new Hashtable<>();
                productInfo.put("id", rs.getString(1));
                productInfo.put("name",rs.getString(2));
                productInfo.put("price", rs.getString(3));
                productInfo.put("category_id", rs.getString(4));
                productInfo.put("supplier_id", rs.getString(5));
                productInfo.put("quantity", rs.getString(6));
                productInfo.put("limit", rs.getString(7));
                productInfo.put("status", rs.getString(8));
            }
        } catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return data;
    }

    /**
     * Retrieves products based on a searched keyword.
     *
     * @param keyword Searched keyword
     * @return ArrayList of Hashtables containing product information.
     */
    public ArrayList<Hashtable<String, String>> loadSearched(String keyword) {
        ArrayList<Hashtable<String,String>> data = new ArrayList<>();
        String query = "SELECT * FROM products WHERE product_name LIKE '%" + keyword + "%';";
        try{
            Connection conn = IDAO.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()){
                Hashtable<String,String> productInfo = new Hashtable<>();
                productInfo.put("id", rs.getString(1));
                productInfo.put("name",rs.getString(2));
                productInfo.put("price", rs.getString(3));
                productInfo.put("category_id", rs.getString(4));
                productInfo.put("supplier_id", rs.getString(5));
                productInfo.put("quantity", rs.getString(6));
                productInfo.put("limit", rs.getString(7));
                productInfo.put("status", rs.getString(8));
                data.add(productInfo);
            }
        } catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return data;
    }
}
