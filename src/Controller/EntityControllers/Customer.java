package Controller.EntityControllers;

import Model.DAOs.CustomerDAO;

import java.util.Hashtable;

/**
 * The Customer class represents a customer with specific attributes.
 */
public class Customer {
    private Integer id;
    private String name;
    private String email;
    private final CustomerDAO dao = new CustomerDAO();

    /**
     * Default constructor for creating a Customer object.
     */
    public Customer() {
    }

    /**
     * Constructor for creating a Customer object with a specified name.
     *
     * @param name The name of the customer.
     */
    public Customer(String name) {
        this.name = name;
    }

    /**
     * Constructor for creating a Customer object with specified name and email.
     *
     * @param name  The name of the customer.
     * @param email The email of the customer.
     */
    public Customer(String name, String email) {
        this.name = name;
        this.email = email;
    }

    /**
     * Retrieves the customer ID based on the provided name.
     *
     * @param name The name of the customer.
     * @return The customer ID corresponding to the provided name.
     */
    public Integer getCID(String name) {
        return dao.getCID(name);
    }

    /**
     * Retrieves the maximum customer ID from the database.
     *
     * @return The maximum customer ID.
     */
    public Integer getMaxCID() {
        return dao.getMaxCID();
    }

    /**
     * Inserts a new customer into the database.
     *
     * @return True if the insertion is successful, false otherwise.
     */
    public boolean insert() {
        return dao.insert(new Hashtable<>() {{
            put("name", name);
            put("email", email);
        }});
    }

    /**
     * Inserts a new customer into the database without specifying an email.
     */
    public void insertWithoutEmail() {
        dao.insertWithoutEmail(new Hashtable<>() {{
            put("name", name);
        }});
    }

    /**
     * Retrieves the name of the customer based on the provided ID.
     *
     * @param id The ID of the customer.
     * @return The name of the customer corresponding to the provided ID.
     */
    public String loadName(Integer id) {
        return dao.loadName(id);
    }

    /**
     * Setter for the customer ID.
     *
     * @param id The new customer ID.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Setter for the name of the customer.
     *
     * @param name The new name of the customer.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Setter for the email of the customer.
     *
     * @param email The new email of the customer.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Getter for the customer ID.
     *
     * @return The customer ID.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Getter for the name of the customer.
     *
     * @return The name of the customer.
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for the email of the customer.
     *
     * @return The email of the customer.
     */
    public String getEmail() {
        return email;
    }
}
