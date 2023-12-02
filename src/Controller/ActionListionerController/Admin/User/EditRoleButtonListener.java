package Controller.ActionListionerController.Admin.User;

import Controller.EntityControllers.User;
import Controller.TableModelController.UserTableModel;
import View.Panels.UsersPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The EditRoleButtonListener class handles the action when the "Edit Role" button is clicked.
 */
public class EditRoleButtonListener implements ActionListener {
    private User u = new User();
    private UsersPanel frame;
    private JTable table;
    private UserTableModel model;
    private Integer UID;

    /**
     * Constructor for the EditRoleButtonListener.
     *
     * @param usersPanel The UsersPanel frame where the action is performed.
     * @param UID        The User ID of the currently logged-in user.
     */
    public EditRoleButtonListener(UsersPanel usersPanel, Integer UID) {
        this.UID = UID;
        frame = usersPanel;
        table = frame.getUserTable();
        model = (UserTableModel) frame.getUserModel();
    }

    /**
     * Invoked when the "Edit Role" button is clicked, prompts the user to enter the User ID for role editing.
     *
     * @param actionEvent The action event triggered by the button click.
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        int selection = JOptionPane.showConfirmDialog(frame, "This action cannot be reversed. Are you sure?", "Confirmation", JOptionPane.YES_NO_OPTION);
        if (selection == JOptionPane.YES_OPTION) {
            int id = Integer.parseInt(JOptionPane.showInputDialog(frame, "Enter User Id "));
            if (id == UID) {
                JOptionPane.showMessageDialog(frame, "You cannot edit your role!!");
            } else {
                int rows = model.getRowCount();
                for (int i = 0; i < rows; i++) {
                    if (model.getValueAt(i, 0).equals(id)) {
                        String role = model.getValueAt(i, 2).toString();
                        if (role.equals("manager")) {
                            JOptionPane.showMessageDialog(frame, "You cannot edit a manager's role!!");
                        } else if (role.equals("")) {
                            JOptionPane.showMessageDialog(frame, "No User with the given ID exists!!");
                        } else {
                            u.update(id);
                            model.setUsersInfo();
                            JOptionPane.showMessageDialog(frame, "Role Updated");
                        }
                    }
                }
            }
        }
    }
}
