package Controller.EntityControllers;

import Model.DAOs.ProductDAO;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 * The Product class represents a product entity with various attributes.
 * It provides methods to interact with the ProductDAO to load and manipulate product data.
 */
public class Product {
    private Integer id;          // Product ID
    private String name;         // Product name
    private Double price;        // Product price
    private Integer categoryID;  // ID of the category to which the product belongs
    private Integer supplierID;  // ID of the supplier providing the product
    private Integer quantity;    // Available quantity of the product
    private Integer limit;       // Quantity limit for the product
    private String status;       // Product status (e.g., active, discontinued)
    public static ProductDAO dao = new ProductDAO(); // Data Access Object for product-related database operations

    /**
     * Default constructor for the Product class.
     */
    public Product() {
    }

    /**
     * Parameterized constructor for the Product class.
     *
     * @param id         The product ID.
     * @param name       The product name.
     * @param price      The product price.
     * @param categoryID The ID of the category to which the product belongs.
     * @param supplierID The ID of the supplier providing the product.
     * @param quantity   The available quantity of the product.
     * @param limit      The quantity limit for the product.
     * @param status     The status of the product (e.g., active, discontinued).
     */
    public Product(Integer id, String name, Double price, Integer categoryID, Integer supplierID, Integer quantity, Integer limit, String status) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.categoryID = categoryID;
        this.supplierID = supplierID;
        this.quantity = quantity;
        this.limit = limit;
        this.status = status;
    }

    public Product(String text, double v, Integer id, Integer id1, int i, int i1) {
    }

    /**
     * Retrieves a list of all products from the database.
     *
     * @return An ArrayList of Product objects representing all products.
     */
    public ArrayList<Product> getProducts() {
        ArrayList<Hashtable<String, String>> data = dao.load();
        ArrayList<Product> products = new ArrayList<>();
        for (Hashtable<String, String> d : data) {
            products.add(new Product(
                    Integer.parseInt(d.get("id")),
                    d.get("name"),
                    Double.valueOf(d.get("price")),
                    Integer.parseInt(d.get("category_id")),
                    Integer.parseInt(d.get("supplier_id")),
                    Integer.parseInt(d.get("quantity")),
                    Integer.parseInt(d.get("limit")),
                    d.get("status"))
            );
        }
        return products;
    }

    /**
     * Loads multiple products from the database based on a specified parameter.
     *
     * @param i The parameter used for loading multiple products.
     * @return An ArrayList of Product objects representing the loaded products.
     */
    public ArrayList<Product> loadMultiple(Integer i) {
        ArrayList<Hashtable<String, String>> data = dao.loadMultiple(i);
        ArrayList<Product> products = new ArrayList<>();
        for (Hashtable<String, String> d : data) {
            products.add(new Product(
                    Integer.parseInt(d.get("id")),
                    d.get("name"),
                    Double.valueOf(d.get("price")),
                    Integer.parseInt(d.get("category_id")),
                    Integer.parseInt(d.get("supplier_id")),
                    Integer.parseInt(d.get("quantity")),
                    Integer.parseInt(d.get("limit")),
                    d.get("status"))
            );
        }
        return products;
    }

    /**
     * Loads products from the database based on a search keyword.
     *
     * @param keyword The search keyword.
     * @return An ArrayList of Product objects representing the searched products.
     */
    public ArrayList<Product> loadSearched(String keyword) {
        ArrayList<Hashtable<String, String>> data = dao.loadSearched(keyword);
        ArrayList<Product> products = new ArrayList<>();
        for (Hashtable<String, String> d : data) {
            products.add(new Product(
                    Integer.parseInt(d.get("id")),
                    d.get("name"),
                    Double.valueOf(d.get("price")),
                    Integer.parseInt(d.get("category_id")),
                    Integer.parseInt(d.get("supplier_id")),
                    Integer.parseInt(d.get("quantity")),
                    Integer.parseInt(d.get("limit")),
                    d.get("status"))
            );
        }
        return products;
    }

    /**
     * Inserts a new Product into the database.
     *
     * @return true if the insertion is successful, false otherwise.
     */
    public boolean insert(){
        return dao.insert(new Hashtable<>() {{
            put("name", name);
            put("price", price.toString());
            put("category_id", categoryID.toString());
            put("supplier_id", supplierID.toString());
            put("quantity", quantity.toString());
            put("limit", limit.toString());
        }});
    }

    /**
     * Retrieves the maximum product ID using the associated DAO's getMaxId method.
     *
     * @return The maximum product ID as an Integer, or 0 if there is an issue or no records.
     */
    public Integer getMaxId() {
        // Delegating the task to the DAO's getMaxId method
        return dao.getMaxId();
    }

    /**
     * Updates the quantity of a product in the database.
     *
     * @param PID      The product ID.
     * @param quantity The new quantity value.
     */
    public void updateQty(Integer PID, Integer quantity) {
        dao.updateQuantity(PID, quantity);
    }

    /**
     * Updates the status of a product in the database.
     *
     * @param PID The product ID.
     */
    public void updateStatus(Integer PID){
        dao.updateStatus(PID);
    }

    /**
     * Updates the information of the current product in the database.
     *
     * @return true if the update is successful, false otherwise.
     */
    public boolean update(){
        return dao.update(this);
    }

    /**
     * Loads a single product from the database based on the product ID.
     *
     * @param i The product ID.
     * @return A Product object representing the loaded product.
     */
    public Product loadSingle(Integer i) {
        Hashtable<String, String> d = dao.loadSingle(i);
        return new Product(
                Integer.parseInt(d.get("id")),
                d.get("name"),
                Double.valueOf(d.get("price")),
                Integer.parseInt(d.get("category_id")),
                Integer.parseInt(d.get("supplier_id")),
                Integer.parseInt(d.get("quantity")),
                Integer.parseInt(d.get("limit")),
                d.get("status")
        );
    }

    // Getter and setter methods for the attributes

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(Integer categoryID) {
        this.categoryID = categoryID;
    }

    public Integer getSupplierID() {
        return supplierID;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getLimit() {
        return limit;
    }

    public String getStatus() {
        return status;
    }
}
