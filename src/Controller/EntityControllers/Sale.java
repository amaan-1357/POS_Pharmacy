package Controller.EntityControllers;

import Model.DAOs.SaleDAO;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Hashtable;

/**
 * The Sale class represents a sale entity with various attributes.
 * It provides methods to interact with the SaleDAO to load and manipulate sale data.
 */
public class Sale {
    private Integer id;           // Sale ID
    private Integer customerId;   // ID of the customer associated with the sale
    private Date date;            // Sale date
    private Double amount;        // Total sale amount
    public static SaleDAO dao = new SaleDAO(); // Data Access Object for sale-related database operations

    /**
     * Default constructor for the Sale class.
     */
    public Sale() {
    }

    /**
     * Parameterized constructor for the Sale class.
     *
     * @param customerId ID of the customer associated with the sale.
     * @param date       Sale date.
     * @param amount     Total sale amount.
     */
    public Sale(Integer customerId, Date date, Double amount) {
        this.customerId = customerId;
        this.date = date;
        this.amount = amount;
    }

    /**
     * Inserts a new Sale into the database.
     *
     * @param sale The Sale object to be inserted.
     * @return true if the insertion is successful, false otherwise.
     */
    public boolean insert(Sale sale) {
        return dao.insert(new Hashtable<>() {{
            put("cid", sale.getCustomerId().toString());
            put("date", sale.getDate().toString());
            put("total_amount", sale.getAmount().toString());
        }});
    }

    /**
     * Loads all sales from the database.
     *
     * @return An ArrayList of Sale objects representing all sales.
     */
    public ArrayList<Sale> loadAll() {
        return dao.load();
    }

    /**
     * Retrieves the latest Sale ID from the database.
     *
     * @return The latest Sale ID as an Integer.
     */
    public Integer getSID() {
        return dao.getSID();
    }

    // Getter and setter methods for the attributes

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
