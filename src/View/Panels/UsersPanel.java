package View.Panels;

import Controller.ActionListionerController.Admin.Users.AddUserButtonListener;
import Controller.ActionListionerController.Admin.Users.DeleteUserActionListener;
import Controller.ActionListionerController.Admin.Users.EditRoleButtonListener;
import Controller.TableModelController.UserTableModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * The UsersPanel class represents the panel for managing user-related operations in the admin portal.
 */
public class UsersPanel extends JPanel {
    private JScrollPane userScrollPane = new JScrollPane();
    private DefaultTableModel userModel;
    private JTable userTable;
    private JButton addUser = new JButton("Add");
    private JButton editRole = new JButton("Edit Role");
    private JButton deleteButton = new JButton("Delete");
    private SpringLayout sLayout = new SpringLayout();
    private String north = SpringLayout.NORTH;
    private String south = SpringLayout.SOUTH;
    private String west = SpringLayout.WEST;
    private String east = SpringLayout.EAST;

    /**
     * Constructs the UsersPanel.
     *
     * @param UID The user ID for authentication purposes.
     */
    public UsersPanel(Integer UID) {
        setVisible(true);
        setSize(1680, 1050);
        setLayout(sLayout);

        userModel = new UserTableModel(UID);
        userTable = new JTable(userModel);

        userTable.setGridColor(Color.BLACK);
        userTable.setRowSelectionAllowed(false);
        userScrollPane = new JScrollPane(userTable);
        add(userScrollPane);
        sLayout.putConstraint(north, userScrollPane, 5, north, this);
        sLayout.putConstraint(west, userScrollPane, 300, west, this);
        sLayout.putConstraint(east, userScrollPane, -5, east, this);
        sLayout.putConstraint(south, userScrollPane, -5, south, this);

        add(addUser);
        sLayout.putConstraint(north, addUser, 50, north, this);
        sLayout.putConstraint(west, addUser, 100, west, this);

        add(editRole);
        sLayout.putConstraint(north, editRole, 5, south, addUser);
        sLayout.putConstraint(west, editRole, 100, west, this);

        add(deleteButton);
        sLayout.putConstraint(north, deleteButton, 5, south, editRole);
        sLayout.putConstraint(west, deleteButton, 100, west, this);

        addUser.addActionListener(new AddUserButtonListener(this));
        deleteButton.addActionListener(new DeleteUserActionListener(this, UID));
        editRole.addActionListener(new EditRoleButtonListener(this, UID));
    }

    /**
     * Gets the user model associated with the panel.
     *
     * @return The user model.
     */
    public DefaultTableModel getUserModel() {
        return userModel;
    }

    /**
     * Gets the user table associated with the panel.
     *
     * @return The user table.
     */
    public JTable getUserTable() {
        return userTable;
    }
}
