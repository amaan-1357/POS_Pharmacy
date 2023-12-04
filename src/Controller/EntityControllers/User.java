package Controller.EntityControllers;

import Model.DAOs.UserDAO;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 * The User class represents a user entity with various attributes.
 */
public class User {
    private Integer id;        // User ID
    private String name;       // User name
    private String u_name;     // User username
    private String password;   // User password
    private String role;       // User role
    private static final UserDAO dao = new UserDAO(); // Data Access Object for user-related database operations

    /**
     * Default constructor.
     */
    public User() {
    }

    /**
     * Constructor to create a user with ID, name, and role.
     *
     * @param id   The ID of the user.
     * @param name The name of the user.
     * @param role The role of the user.
     */
    public User(Integer id, String name, String role) {
        this.id = id;
        this.name = name;
        this.role = role;
    }

    /**
     * Constructor to create a user with name, username, password, and role.
     *
     * @param name     The name of the user.
     * @param u_name   The username of the user.
     * @param password The password of the user.
     * @param role     The role of the user.
     */
    public User(String name, String u_name, String password, String role) {
        this.name = name;
        this.u_name = u_name;
        this.password = password;
        this.role = role;
    }

    /**
     * Get a list of users excluding the specified user ID.
     *
     * @param UID The user ID to exclude.
     * @return ArrayList of User objects.
     */
    public ArrayList<User> getUsers(Integer UID) {
        ArrayList<Hashtable<String, String>> data = dao.load(UID);
        ArrayList<User> users = new ArrayList<>();
        for (Hashtable<String, String> d : data) {
            users.add(new User(Integer.parseInt(d.get("id")),
                    d.get("name"),
                    d.get("role"))
            );
        }
        return users;
    }

    /**
     * Get a list of all usernames.
     *
     * @return ArrayList of usernames.
     */
    public ArrayList<String> getUName() {
        return dao.loadUNames();
    }

    /**
     * Insert a new user into the database.
     *
     * @param user The User object to insert.
     * @return True if insertion is successful, false otherwise.
     */
    public boolean insert(User user) {
        return dao.insert(new Hashtable<>() {{
            put("name", user.getName());
            put("u_name", user.getU_name());
            put("pass", user.getPassword());
            put("role", user.getRole());
        }});
    }

    /**
     * Get the name of the user based on the user ID.
     *
     * @param Id The user ID.
     * @return The name of the user.
     */
    public String getName(Integer Id) {
        return dao.loadName(Id);
    }

    /**
     * Update the role of a user based on the user ID.
     *
     * @param id The user ID.
     * @return True if the update is successful, false otherwise.
     */
    public boolean update(Integer id) {
        return dao.update(id);
    }

    /**
     * Delete a user based on the user ID.
     *
     * @param UID The user ID to delete.
     */
    public void delete(Integer UID) {
        dao.delete(UID);
    }

    // Getter methods for the attributes

    /**
     * Get the ID of the user.
     *
     * @return The user ID.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Get the name of the user.
     *
     * @return The name of the user.
     */
    public String getName() {
        return name;
    }

    /**
     * Get the password of the user.
     *
     * @return The password of the user.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Get the role of the user.
     *
     * @return The role of the user.
     */
    public String getRole() {
        return role;
    }

    /**
     * Get the username of the user.
     *
     * @return The username of the user.
     */
    public String getU_name() {
        return u_name;
    }
}
