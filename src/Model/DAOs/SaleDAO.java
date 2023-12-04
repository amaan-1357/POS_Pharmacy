package Model.DAOs;

import Controller.EntityControllers.Sale;

import java.sql.*;
import java.util.ArrayList;
import java.util.Hashtable;

/**
 * The SaleDAO class provides data access methods for interacting with the sales table.
 */
public class SaleDAO implements IDAO{

    /**
     * Retrieves all sales from the database.
     *
     * @return ArrayList of Sale objects containing sale information.
     */
    public ArrayList<Sale> load() {
        ArrayList<Sale> list = new ArrayList<>();
        String query = "SELECT * FROM sales;";
        try{
            Connection conn = IDAO.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){
                Sale sale = new Sale();
                sale.setId(rs.getInt("sale_id"));
                sale.setCustomerId(rs.getInt("customer_id"));
                sale.setDate(rs.getDate("sale_date"));
                sale.setAmount(rs.getDouble("total_amount"));
                list.add(sale);
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
     * Update method not implemented for this class.
     *
     * @return false
     */
    public boolean update() {
        return false;
    }

    /**
     * Retrieves the maximum sale ID from the "sales" table.
     *
     * @return The maximum sale ID as an Integer, or 0 if there is an issue or no records.
     */
    public Integer getSID(){
        String query = "SELECT MAX(sale_id) AS max_sales_id FROM sales;";
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
     * Inserts a new sale into the database using the provided data.
     *
     * @param data A Hashtable containing the sale information.
     * @return true if the insertion is successful, false otherwise.
     */
    @SuppressWarnings("SqlSourceToSinkFlow")
    public boolean insert(Hashtable<String, String> data) {
        String query = "insert into sales(customer_id, sale_date, total_amount) values ('" +
                data.get("cid") + "','" + data.get("date") + "','" + data.get("total_amount") + "');";
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
     * LoadSingle method not implemented for this class.
     *
     * @return null
     */
    public Hashtable<String, String> loadSingle() {
        return null;
    }
}
