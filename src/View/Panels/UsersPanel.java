package View.Panels;

import Controller.ActionListionerController.Admin.User.AddUserButtonListener;
import Controller.ActionListionerController.Admin.User.DeleteUserActionListener;
import Controller.ActionListionerController.Admin.User.EditRoleButtonListener;
import Controller.TableModelController.UserTableModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * The UsersPanel class represents the panel for managing user-related operations in the admin portal.
 */
public class UsersPanel extends JPanel {
    private final DefaultTableModel userModel;
    private final JTable userTable;

    /**
     * Constructs the UsersPanel.
     *
     * @param UID The user ID for authentication purposes.
     */
    public UsersPanel(Integer UID) {
        setVisible(true);
        setSize(1680, 1050);
        SpringLayout sLayout = new SpringLayout();
        setLayout(sLayout);

        userModel = new UserTableModel(UID);
        userTable = new JTable(userModel);

        userTable.setGridColor(Color.BLACK);
        userTable.setRowSelectionAllowed(false);
        JScrollPane userScrollPane = new JScrollPane(userTable);
        add(userScrollPane);
        String north = SpringLayout.NORTH;
        sLayout.putConstraint(north, userScrollPane, 5, north, this);
        String west = SpringLayout.WEST;
        sLayout.putConstraint(west, userScrollPane, 300, west, this);
        String east = SpringLayout.EAST;
        sLayout.putConstraint(east, userScrollPane, -5, east, this);
        String south = SpringLayout.SOUTH;
        sLayout.putConstraint(south, userScrollPane, -5, south, this);

        JButton addUser = new JButton("Add");
        add(addUser);
        sLayout.putConstraint(north, addUser, 50, north, this);
        sLayout.putConstraint(west, addUser, 100, west, this);

        JButton editRole = new JButton("Edit Role");
        add(editRole);
        sLayout.putConstraint(north, editRole, 5, south, addUser);
        sLayout.putConstraint(west, editRole, 100, west, this);

        JButton deleteButton = new JButton("Delete");
        add(deleteButton);
        sLayout.putConstraint(north, deleteButton, 5, south, editRole);
        sLayout.putConstraint(west, deleteButton, 100, west, this);

        addUser.addActionListener(new AddUserButtonListener(this));
        deleteButton.addActionListener(new DeleteUserActionListener(this, UID));
        editRole.addActionListener(new EditRoleButtonListener(this));
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
