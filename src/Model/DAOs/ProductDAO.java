package Model.DAOs;

import Controller.EntityControllers.Product;

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
     * Updates product information based on the provided Product p .
     *
     * @param p Product containing updated product information.
     * @return True if the update is successful, false otherwise.
     */
    @SuppressWarnings("SqlSourceToSinkFlow")
    public boolean update(Product p) {
        String query = "UPDATE products SET product_name='" + p.getName() +
                "', unit_price=" + p.getPrice() +
                ", category_id=" +  p.getCategoryID() +
                ", supplier_id=" +  p.getSupplierID() + " WHERE product_id=" + p.getId()+";";
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
     * Updates the quantity of a product in the database.
     *
     * @param PID      Product ID
     * @param quantity New quantity value
     */
    public void updateQuantity(Integer PID, Integer quantity){
        String query = "UPDATE products SET quantity = " + quantity.toString() + " WHERE product_id = " + PID + ";";
        try{
            Connection conn = IDAO.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.executeUpdate();
        } catch(SQLException ignored){
        }
    }

    /**
     * Updates the status of a product to 'discontinued' and sets quantity to 0.
     *
     * @param PID Product ID
     */
    public void updateStatus(Integer PID){
        String query = "UPDATE products SET status = 'discontinued', quantity = 0 WHERE product_id = " + PID + ";";
        try{
            Connection conn = IDAO.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.executeUpdate();
        } catch(SQLException ignored){
        }
    }

    /**
     * Inserts a new product into the database using the provided data.
     *
     * @param data A Hashtable containing the product information.
     * @return true if the insertion is successful, false otherwise.
     */
    @SuppressWarnings("SqlSourceToSinkFlow")
    public boolean insert(Hashtable<String, String> data) {
        String query = "INSERT INTO products (product_name, unit_price, category_id, supplier_id, quantity, lower_limit) " +
                "VALUES('" + data.get("name") + "'," + data.get("price") + "," + data.get("category_id") + "," +
                data.get("supplier_id") + "," + data.get("quantity") + "," + data.get("limit") + ");";
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
                data.put("id", rs.getString(1));
                data.put("name",rs.getString(2));
                data.put("price", rs.getString(3));
                data.put("category_id", rs.getString(4));
                data.put("supplier_id", rs.getString(5));
                data.put("quantity", rs.getString(6));
                data.put("limit", rs.getString(7));
                data.put("status", rs.getString(8));
            }
        } catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return data;
    }

    /**
     * Retrieves the maximum product ID from the "products" table.
     *
     * @return The maximum product ID as an Integer, or 0 if there is an issue or no records.
     */
    public Integer getMaxId() {
        // SQL query to get the maximum product ID
        String query = "SELECT MAX(product_id) FROM products;";

        try {
            // Establishing a database connection
            Connection conn = IDAO.getConnection();

            // Creating a statement for executing the SQL query
            Statement stmt = conn.createStatement();

            // Executing the query and getting the result set
            ResultSet rs = stmt.executeQuery(query);

            // Checking if the result set has at least one row
            if (rs.next()) {
                // Parsing and returning the value of the first column (product ID) as an Integer
                return Integer.parseInt(rs.getString(1));
            }
        } catch (SQLException ex) {
            // Printing the error message to the console in case of a SQL exception
            System.out.println(ex.getMessage());
        }

        // Returning 0 if there is an issue or no records
        return 0;
    }

    /**
     * Retrieves products based on a searched keyword.
     *
     * @param keyword Searched keyword
     * @return ArrayList of Hashtables containing product information.
     */
    @SuppressWarnings("SqlSourceToSinkFlow")
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
