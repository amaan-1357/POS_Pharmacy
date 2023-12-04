package Model.DAOs;

import java.sql.*;
import java.util.ArrayList;
import java.util.Hashtable;

/**
 * The SupplierDAO class provides data access methods for interacting with the suppliers table.
 */
public class SupplierDAO implements IDAO {

    /**
     * Retrieves all suppliers from the database.
     *
     * @return ArrayList of Hashtables containing supplier information.
     */
    public ArrayList<Hashtable<String, String>> load() {
        ArrayList<Hashtable<String,String>> data = new ArrayList<>();

        // SQL query to select supplier_id and supplier_name from the suppliers table
        String query = "SELECT supplier_id, supplier_name FROM suppliers;";

        try {
            Connection conn = IDAO.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            // Process the result set and populate the data ArrayList
            while (rs.next()) {
                Hashtable<String,String> supplierInfo = new Hashtable<>();
                supplierInfo.put("id", rs.getString(1));
                supplierInfo.put("name", rs.getString(2));
                data.add(supplierInfo);
            }
        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
        }
        return data;
    }

    /**
     * Retrieves multiple suppliers based on the given supplier ID.
     *
     * @param id Supplier ID
     * @return ArrayList of Hashtables containing supplier information.
     */
    public ArrayList<Hashtable<String, String>> loadMultiple(Integer id) {
        ArrayList<Hashtable<String,String>> data = new ArrayList<>();
        String query = "SELECT * FROM suppliers WHERE supplier_id = "+id.toString();
        try{
            Connection conn = IDAO.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            // Process the result set and populate the data ArrayList
            while (rs.next()){
                Hashtable<String,String> supplierInfo = new Hashtable<>();
                supplierInfo.put("id", rs.getString(1));
                supplierInfo.put("name", rs.getString(2));
                if(rs.getString(3)!=null)
                    supplierInfo.put("email", rs.getString(3));
                supplierInfo.put("phone_no", rs.getString(4));
                data.add(supplierInfo);
            }
        } catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return data;
    }

    /**
     * Updates supplier information based on the provided data Hashtable (not implemented).
     *
     * @return Always returns false (not implemented).
     */
    public boolean update() {
        return false; // Not implemented
    }

    /**
     * Inserts a new supplier into the database.
     *
     * @param data Hashtable containing supplier information.
     * @return True if the insertion is successful, false otherwise.
     */
    @SuppressWarnings("SqlSourceToSinkFlow")
    public boolean insert(Hashtable<String, String> data) {
        String query = "INSERT INTO suppliers(supplier_name,email,phone_number) VALUES ('" +
                data.get("name") + "','" + data.get("email") + "','" + data.get("number") + "');";
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
     * Inserts a new supplier without an email into the database.
     *
     * @param data Hashtable containing supplier information without email.
     */
    @SuppressWarnings("SqlSourceToSinkFlow")
    public void insertWithoutEmail(Hashtable<String, String> data) {
        String query = "INSERT INTO suppliers(supplier_name,phone_number) VALUES ('" +
                data.get("name") + "','" + data.get("number") + "');";
        try{
            Connection conn = IDAO.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.executeUpdate();
        } catch(SQLException ignored){
        }
    }

    /**
     * Retrieves a single supplier based on the given supplier ID.
     *
     * @param id Supplier ID
     * @return Hashtable containing supplier information.
     */
    public Hashtable<String, String> loadSingle(Integer id) {
        Hashtable<String,String> data = new Hashtable<>();
        String query = "SELECT * FROM suppliers WHERE supplier_id = "+id.toString();
        try{
            Connection conn = IDAO.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            // Process the result set and populate the data Hashtable
            while (rs.next()){
                data.put("id", rs.getString(1));
                data.put("name", rs.getString(2));
                if(rs.getString(3)!=null)
                    data.put("email", rs.getString(3));
                data.put("phone_no", rs.getString(4));
            }
        } catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return data;
    }

    /**
     * Retrieves a single supplier based on the given supplier name.
     *
     * @param name Supplier name
     * @return Hashtable containing supplier information.
     */
    @SuppressWarnings("SqlSourceToSinkFlow")
    public Hashtable<String, String> loadByName(String name) {
        Hashtable<String,String> data = new Hashtable<>();
        String query = "SELECT * FROM suppliers WHERE supplier_name = '"+name+"';";
        try{
            Connection conn = IDAO.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            // Process the result set and populate the data Hashtable
            while (rs.next()){
                data.put("id", rs.getString(1));
                data.put("name", rs.getString(2));
                if(rs.getString(3)!=null)
                    data.put("email", rs.getString(3));
                data.put("phone_no", rs.getString(4));
            }
        } catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return data;
    }
}
