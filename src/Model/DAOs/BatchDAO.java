package Model.DAOs;

import Controller.EntityControllers.Batch;

import java.sql.*;
import java.util.ArrayList;
import java.util.Hashtable;

/**
 * The BatchDAO class implements the IDAO interface and provides data access methods
 * for the 'batches' table in the database.
 */
public class BatchDAO implements IDAO {

    /**
     * Load method not implemented for this class.
     * @return null
     */
    public ArrayList<Hashtable<String, String>> load() {
        return null;
    }

    /**
     * LoadMultiple method not implemented for this class.
     * @return null
     */
    public ArrayList<Hashtable<String, String>> loadMultiple() {
        return null;
    }

    /**
     * Update method not implemented for this class.
     * @return false
     */
    public boolean update() {
        return false;
    }

    /**
     * Inserts a new batch into the 'batches' table.
     *
     * @param data A Hashtable containing data for the new batch.
     * @return true if the insertion is successful, false otherwise.
     */
    @SuppressWarnings("SqlSourceToSinkFlow")
    public boolean insert(Hashtable<String, String> data) {
        String query = "INSERT INTO batches (product_id, expiry_date, quantity) VALUES(" +
                data.get("Id") + ",'" + data.get("date") + "'," + data.get("quantity") + ");";
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
     * Deletes batches associated with a specific product ID.
     *
     * @param id The product ID.
     */
    public void deleteByPID(Integer id) {
        String query = "DELETE FROM batches WHERE product_id=" + id + ";";
        try {
            Connection conn = IDAO.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.executeUpdate();
        } catch (SQLException ignored) {
        }
    }

    /**
     * Updates the quantity of a batch with a specific ID.
     *
     * @param qty The new quantity.
     * @param id  The batch ID.
     */
    public void updateQtyById(Integer qty, Integer id) {
        String query = "UPDATE batches SET quantity=" + qty + " WHERE batch_id=" + id + ";";
        try {
            Connection conn = IDAO.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.executeUpdate();
        } catch (SQLException ignored) {
        }
    }

    /**
     * Deletes a batch with a specific ID.
     *
     * @param id The batch ID.
     */
    public void deleteById(Integer id) {
        String query = "DELETE FROM batches WHERE batch_id=" + id + ";";
        try {
            Connection conn = IDAO.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.executeUpdate();
        } catch (SQLException ignored) {
        }
    }

    /**
     * Retrieves batch information associated with a specific product ID.
     *
     * @param id The product ID.
     * @return A Hashtable containing batch information.
     */
    public Hashtable<String, String> getByPID(Integer id) {
        Hashtable<String, String> data = new Hashtable<>();
        String query = "SELECT * FROM batches WHERE product_id = " + id +
                " AND expiry_date >= CURDATE() ORDER BY expiry_date LIMIT 1;";
        try {
            Connection conn = IDAO.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                data.put("id", rs.getString(1));
                data.put("product_id", rs.getString(2));
                data.put("expiry", rs.getString(3));
                data.put("qty", rs.getString(4));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return data;
    }

    /**
     * Retrieves all batches associated with a specific product ID.
     *
     * @param id The product ID.
     * @return An ArrayList of Batch objects.
     */
    public ArrayList<Batch> getBatchesByPID(Integer id) {
        ArrayList<Batch> batches = new ArrayList<>();
        String query = "SELECT * FROM batches WHERE product_id = " + id + ";";
        try {
            Connection conn = IDAO.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                batches.add(new Batch(rs.getInt(1), rs.getInt(2), Date.valueOf(rs.getString(3)), rs.getInt(4)));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return batches;
    }

    /**
     * LoadSingle method not implemented for this class.
     * @return null
     */
    public Hashtable<String, String> loadSingle() {
        return null;
    }
}
