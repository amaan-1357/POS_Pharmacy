package Controller.TableModelController;

import Controller.EntityControllers.User;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

/**
 * The UserTableModel class extends DefaultTableModel and represents the table model
 * for displaying user information in a table.
 */
public class UserTableModel extends DefaultTableModel {
    private final User user = new User();
    private final Integer UID;

    /**
     * Constructor for UserTableModel.
     *
     * @param u The user ID for which the table is being created.
     */
    public UserTableModel(Integer u){
        UID = u;
        addColumn("UID");   // Column for User ID
        addColumn("Name");  // Column for Username
        addColumn("Role");  // Column for User Role
        setUsersInfo();     // Set the initial user information
    }

    /**
     * Sets the user information in the table model.
     */
    public void setUsersInfo(){
        ArrayList<User> users = user.getUsers(UID);
        setRowCount(0);

        // Iterate through the users and add each user to the table model
        for(User u: users){
            Object[] a= {u.getId(), u.getName(), u.getRole()};
            this.addRow(a);
        }
    }

    /**
     * Sets the non-manager users in the table model.
     */
    public void setNonManagers(){
        ArrayList<User> users = user.getUsers(UID);
        setRowCount(0);

        // Iterate through the users and add non-manager users to the table model
        for(User u: users){
            if(!u.getRole().equals("manager")){
                Object[] a= {u.getId(), u.getName(), u.getRole()};
                this.addRow(a);
            }
        }
    }
}
