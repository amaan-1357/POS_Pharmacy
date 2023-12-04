package Controller.EntityControllers;

import Model.DAOs.SupplierDAO;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 * The Supplier class serves as an entity controller for managing suppliers.
 */
public class Supplier {
    private Integer id;         // Unique identifier of the supplier
    private String name;        // Name of the supplier
    private String email = null; // Email of the supplier (nullable)
    private String phoneNo;      // Phone number of the supplier
    public static SupplierDAO dao = new SupplierDAO(); // Data Access Object for supplier-related database operations

    /**
     * Default constructor for the Supplier class.
     */
    public Supplier() {
    }

    /**
     * Parameterized constructor for creating a Supplier instance with specified id, name, email, and phone number.
     *
     * @param id      The unique identifier of the supplier.
     * @param name    The name of the supplier.
     * @param email   The email of the supplier.
     * @param phoneNo The phone number of the supplier.
     */
    public Supplier(Integer id, String name, String email, String phoneNo) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNo = phoneNo;
    }

    /**
     * Parameterized constructor for creating a Supplier instance with specified id, name, and phone number.
     *
     * @param id      The unique identifier of the supplier.
     * @param name    The name of the supplier.
     * @param phoneNo The phone number of the supplier.
     */
    public Supplier(Integer id, String name, String phoneNo) {
        this.id = id;
        this.name = name;
        this.phoneNo = phoneNo;
    }

    /**
     * Parameterized constructor for creating a Supplier instance with specified name and phone number.
     *
     * @param name    The name of the supplier.
     * @param phoneNo The phone number of the supplier.
     */
    public Supplier(String name, String phoneNo) {
        this.name = name;
        this.phoneNo = phoneNo;
    }

    /**
     * Parameterized constructor for creating a Supplier instance with specified name, email, and phone number.
     *
     * @param name    The name of the supplier.
     * @param email   The email of the supplier.
     * @param phoneNo The phone number of the supplier.
     */
    public Supplier(String name, String email, String phoneNo) {
        this.name = name;
        this.email = email;
        this.phoneNo = phoneNo;
    }

    /**
     * Retrieves information about all suppliers.
     *
     * @return An ArrayList of Supplier instances containing supplier information.
     */
    public ArrayList<Supplier> getSuppliers() {
        ArrayList<Hashtable<String, String>> data = dao.load();
        ArrayList<Supplier> suppliers = new ArrayList<>();
        for (Hashtable<String, String> d : data) {
            if (d.get("email") == null) {
                suppliers.add(new Supplier(Integer.parseInt(d.get("id")),
                        d.get("name"),
                        d.get("phone_no"))
                );
            } else {
                suppliers.add(new Supplier(Integer.parseInt(d.get("id")),
                        d.get("name"),
                        d.get("email"),
                        d.get("phone_no"))
                );
            }
        }
        return suppliers;
    }

    /**
     * Retrieves information about multiple suppliers based on the specified limit.
     *
     * @param limit The maximum number of suppliers to retrieve.
     * @return An ArrayList of Supplier instances containing supplier information.
     */
    public ArrayList<Supplier> loadMultiple(Integer limit) {
        ArrayList<Hashtable<String, String>> data = dao.loadMultiple(limit);
        ArrayList<Supplier> suppliers = new ArrayList<>();
        for (Hashtable<String, String> d : data) {
            if (d.get("email") == null) {
                suppliers.add(new Supplier(Integer.parseInt(d.get("id")),
                        d.get("name"),
                        d.get("phone_no"))
                );
            } else {
                suppliers.add(new Supplier(Integer.parseInt(d.get("id")),
                        d.get("name"),
                        d.get("email"),
                        d.get("phone_no"))
                );
            }
        }
        return suppliers;
    }

    /**
     * Inserts a new supplier with the specified information.
     *
     * @return A boolean indicating the success of the insertion operation.
     */
    public boolean insert() {
        return dao.insert(new Hashtable<>() {{
            put("name", name);
            put("email", email);
            put("number", phoneNo);
        }});
    }

    /**
     * Inserts a new supplier without email information.
     */
    public void insertWithoutEmail() {
        dao.insertWithoutEmail(new Hashtable<>() {{
            put("name", name);
            put("number", phoneNo);
        }});
    }

    /**
     * Retrieves information about a single supplier based on the specified id.
     *
     * @param i The unique identifier of the supplier.
     * @return A Supplier instance containing supplier information.
     */
    public Supplier loadSingle(Integer i) {
        Hashtable<String, String> d = dao.loadSingle(i);
        if (d.get("email") == null) {
            return new Supplier(Integer.parseInt(d.get("id")),
                    d.get("name"),
                    d.get("phone_no"));
        } else {
            return new Supplier(Integer.parseInt(d.get("id")),
                    d.get("name"),
                    d.get("email"),
                    d.get("phone_no"));
        }
    }

    /**
     * Retrieves information about a single supplier based on the specified name.
     *
     * @param name The name of the supplier.
     * @return A Supplier instance containing supplier information.
     */
    public Supplier loadByName(String name) {
        Hashtable<String, String> d = dao.loadByName(name);
        if (d.get("email") == null) {
            return new Supplier(Integer.parseInt(d.get("id")),
                    d.get("name"),
                    d.get("phone_no"));
        } else {
            return new Supplier(Integer.parseInt(d.get("id")),
                    d.get("name"),
                    d.get("email"),
                    d.get("phone_no"));
        }
    }

    // Getter and setter methods for the attributes

    /**
     * Gets the unique identifier of the supplier.
     *
     * @return The id of the supplier.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the supplier.
     *
     * @param id The id to set for the supplier.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Gets the name of the supplier.
     *
     * @return The name of the supplier.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the supplier.
     *
     * @param name The name to set for the supplier.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the email of the supplier.
     *
     * @return The email of the supplier.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email of the supplier.
     *
     * @param email The email to set for the supplier.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the phone number of the supplier.
     *
     * @return The phone number of the supplier.
     */
    public String getPhoneNo() {
        return phoneNo;
    }
}
