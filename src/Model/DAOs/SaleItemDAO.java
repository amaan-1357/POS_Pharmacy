package Model.DAOs;

import Controller.EntityControllers.SaleItem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;

/**
 * The SaleItemDAO class provides data access methods for interacting with the sale_items table.
 */
public class SaleItemDAO implements IDAO {

    /**
     * Retrieves all sale items associated with a specific sale ID from the database.
     *
     * @param id Sale ID
     * @return ArrayList of SaleItem objects containing sale item information.
     */
    public ArrayList<SaleItem> loadById(Integer id) {
        ArrayList<SaleItem> list = new ArrayList<>();
        String query = "SELECT * FROM sale_items where sale_id = "+id+";";
        try{
            Connection conn = IDAO.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                list.add(new SaleItem(rs.getInt("sale_id"),rs.getInt("product_id"),rs.getInt("quantity"),rs.getDouble("item_total")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
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
     * Inserts a new sale item into the database using the provided data.
     *
     * @param data A Hashtable containing the sale item information.
     * @return true if the insertion is successful, false otherwise.
     */
    @SuppressWarnings("SqlSourceToSinkFlow")
    public boolean insert(Hashtable<String, String> data) {
        String query = "insert into sale_items(sale_id, product_id, quantity, item_total)  values ('" +
                data.get("sid") + "','" + data.get("pid") + "','" + data.get("qty") + "','" + data.get("total") + "');";
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
