package Controller.EntityControllers;

import Model.DAOs.ProductCategoryDAO;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 * The ProductCategory class serves as an entity controller for managing product categories.
 */
public class ProductCategory {
    private Integer id;
    private String name;
    public static ProductCategoryDAO dao = new ProductCategoryDAO();

    /**
     * Default constructor for the ProductCategory class.
     */
    public ProductCategory() {
    }

    /**
     * Parameterized constructor for creating a ProductCategory instance with specified id and name.
     *
     * @param id   The unique identifier of the product category.
     * @param name The name of the product category.
     */
    public ProductCategory(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Retrieves information about all product categories.
     *
     * @return An ArrayList of ProductCategory instances containing category information.
     */
    public ArrayList<ProductCategory> getProductCategories() {
        ArrayList<Hashtable<String, String>> data = dao.load();
        ArrayList<ProductCategory> productCategories = new ArrayList<>();
        for (Hashtable<String, String> d : data) {
            productCategories.add(new ProductCategory(Integer.parseInt(d.get("id")),
                    d.get("name"))
            );
        }
        return productCategories;
    }

    /**
     * Retrieves the names of all product categories.
     *
     * @return An ArrayList of String containing the names of product categories.
     */
    public ArrayList<String> getNames() {
        return dao.loadNames();
    }

    /**
     * Retrieves information about multiple product categories based on the specified limit.
     *
     * @param limit The maximum number of product categories to retrieve.
     * @return An ArrayList of ProductCategory instances containing category information.
     */
    public ArrayList<ProductCategory> loadMultiple(Integer limit) {
        ArrayList<Hashtable<String, String>> data = dao.loadMultiple(limit);
        ArrayList<ProductCategory> productCategories = new ArrayList<>();
        for (Hashtable<String, String> d : data) {
            productCategories.add(new ProductCategory(Integer.parseInt(d.get("id")),
                    d.get("name"))
            );
        }
        return productCategories;
    }

    /**
     * Retrieves information about a single product category based on the specified id.
     *
     * @param id The unique identifier of the product category.
     * @return A ProductCategory instance containing category information.
     */
    public ProductCategory loadSingle(Integer id) {
        Hashtable<String, String> data = dao.loadSingle(id);
        return new ProductCategory(Integer.parseInt(data.get("id")),
                data.get("name"));
    }

    /**
     * Inserts a new product category with the specified name.
     *
     * @param name The name of the new product category to be inserted.
     * @return A boolean indicating the success of the insertion operation.
     */
    public boolean insert(String name) {
        return dao.insert(name);
    }

    /**
     * Gets the unique identifier of the product category.
     *
     * @return The id of the product category.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the product category.
     *
     * @param id The id to set for the product category.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Gets the name of the product category.
     *
     * @return The name of the product category.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the product category.
     *
     * @param name The name to set for the product category.
     */
    public void setName(String name) {
        this.name = name;
    }
}
