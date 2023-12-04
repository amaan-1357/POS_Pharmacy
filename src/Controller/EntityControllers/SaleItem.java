package Controller.EntityControllers;

import Model.DAOs.SaleItemDAO;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 * The SaleItem class represents a sale item entity with various attributes.
 * It provides methods to interact with the SaleItemDAO to load and manipulate sale item data.
 */
public class SaleItem {
    private Integer saleId;    // Sale ID associated with the sale item
    private Integer itemId;    // Product ID associated with the sale item
    private Integer quantity;   // Quantity of the product in the sale item
    private Double price;       // Total price of the sale item
    private final SaleItemDAO dao = new SaleItemDAO(); // Data Access Object for sale item-related database operations

    /**
     * Default constructor for the SaleItem class.
     */
    public SaleItem() {
    }

    /**
     * Parameterized constructor for the SaleItem class.
     *
     * @param saleId   Sale ID associated with the sale item.
     * @param itemId   Product ID associated with the sale item.
     * @param quantity Quantity of the product in the sale item.
     * @param price    Total price of the sale item.
     */
    public SaleItem(Integer saleId, Integer itemId, Integer quantity, Double price) {
        this.saleId = saleId;
        this.itemId = itemId;
        this.quantity = quantity;
        this.price = price;
    }

    /**
     * Inserts a new SaleItem into the database.
     *
     * @param si The SaleItem object to be inserted.
     * @return true if the insertion is successful, false otherwise.
     */
    public boolean insert(SaleItem si) {
        return dao.insert(new Hashtable<>() {{
            put("sid", si.getSaleId().toString());
            put("pid", si.getItemId().toString());
            put("qty", si.getQuantity().toString());
            put("total", si.getPrice().toString());
        }});
    }

    /**
     * Loads sale items by sale ID from the database.
     *
     * @param id The sale ID.
     * @return An ArrayList of SaleItem objects representing sale items associated with the specified sale ID.
     */
    public ArrayList<SaleItem> loadById(Integer id) {
        return dao.loadById(id);
    }

    // Getter methods for the attributes

    public Integer getSaleId() {
        return saleId;
    }

    public Integer getItemId() {
        return itemId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Double getPrice() {
        return price;
    }
}
