package Controller.EntityControllers;

import Model.DAOs.BatchDAO;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Hashtable;

/**
 * The Batch class represents a batch of products with specific attributes.
 */
public class Batch {
    private Integer id;
    private Integer productId;
    private Date date;
    private Integer quantity;
    public static BatchDAO dao = new BatchDAO();

    /**
     * Constructor for creating a new Batch object.
     *
     * @param productId The ID of the product associated with the batch.
     * @param data      The expiry date of the batch.
     * @param quantity  The quantity of products in the batch.
     */
    public Batch(Integer productId, Date data, Integer quantity) {
        this.productId = productId;
        this.date = data;
        this.quantity = quantity;
    }

    /**
     * Empty constructor for creating Batch objects without specifying attributes.
     */
    public Batch() {
    }

    /**
     * Constructor for creating a Batch object with all attributes specified.
     *
     * @param id        The ID of the batch.
     * @param productId The ID of the product associated with the batch.
     * @param date      The expiry date of the batch.
     * @param quantity  The quantity of products in the batch.
     */
    public Batch(Integer id, Integer productId, Date date, Integer quantity) {
        this.id = id;
        this.productId = productId;
        this.date = date;
        this.quantity = quantity;
    }

    /**
     * Inserts a new batch into the database.
     *
     * @return True if the insertion is successful, false otherwise.
     */
    public boolean insert() {
        return dao.insert(new Hashtable<>() {
            {
                put("Id", String.valueOf(productId));
                put("date", String.valueOf(date));
                put("quantity", String.valueOf(quantity));
            }
        });
    }

    /**
     * Retrieves a batch by the specified product ID.
     *
     * @param PID The product ID associated with the batch.
     * @return A Batch object corresponding to the specified product ID.
     */
    public Batch getByPID(Integer PID) {
        Hashtable<String, String> hs = dao.getByPID(PID);
        return new Batch(Integer.parseInt(hs.get("id")),
                Integer.parseInt(hs.get("product_id")),
                Date.valueOf(hs.get("expiry")),
                Integer.parseInt(hs.get("qty")));
    }

    /**
     * Retrieves all batches associated with a specific product ID.
     *
     * @param id The product ID for which batches are to be retrieved.
     * @return ArrayList of Batch objects associated with the specified product ID.
     */
    public ArrayList<Batch> getBatchesByPID(Integer id) {
        return dao.getBatchesByPID(id);
    }

    /**
     * Deletes all batches associated with a specific product ID.
     *
     * @param id The product ID for which batches are to be deleted.
     */
    public void deleteByPID(Integer id) {
        dao.deleteByPID(id);
    }

    /**
     * Updates the quantity of a batch by its ID.
     *
     * @param qty The new quantity value.
     * @param id  The ID of the batch to be updated.
     */
    public void updateQtyById(Integer qty, Integer id) {
        dao.updateQtyById(qty, id);
    }

    /**
     * Deletes a batch by its ID.
     *
     * @param id The ID of the batch to be deleted.
     */
    public void deleteById(Integer id) {
        dao.deleteById(id);
    }

    /**
     * Getter for the ID of the batch.
     *
     * @return The ID of the batch.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Getter for the expiry date of the batch.
     *
     * @return The expiry date of the batch.
     */
    public Date getDate() {
        return date;
    }

    /**
     * Getter for the quantity of products in the batch.
     *
     * @return The quantity of products in the batch.
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * Setter for the quantity of products in the batch.
     *
     * @param quantity The new quantity value.
     */
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
