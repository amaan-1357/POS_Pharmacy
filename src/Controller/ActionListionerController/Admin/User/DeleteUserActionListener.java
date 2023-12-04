package Controller.ActionListionerController.Admin.User;

import Controller.EntityControllers.User;
import Controller.TableModelController.UserTableModel;
import View.Panels.UsersPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The DeleteUserActionListener class handles the action when the "Delete User" button is clicked.
 */
public class DeleteUserActionListener implements ActionListener {

    private final UsersPanel frame;
    private final User u = new User();
    private final UserTableModel model;
    Integer UID;

    /**
     * Constructor for the DeleteUserActionListener.
     *
     * @param up  The UsersPanel frame where the action is performed.
     * @param UID The User ID of the currently logged-in user.
     */
    public DeleteUserActionListener(UsersPanel up, Integer UID) {
        frame = up;
        JTable table = frame.getUserTable();
        model = (UserTableModel) table.getModel();
        this.UID = UID;
    }

    /**
     * Invoked when the "Delete User" button is clicked, prompts the user to enter the User ID for deletion.
     *
     * @param actionEvent The action event triggered by the button click.
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Integer id = Integer.parseInt(JOptionPane.showInputDialog(frame, "Enter User Id"));
        if (id.equals(UID)) {
            JOptionPane.showMessageDialog(frame, "You cannot delete yourself!!");
        } else {
            String role = "";
            int rows = model.getRowCount();
            for (int i = 0; i < rows; i++) {
                if (model.getValueAt(i, 0).equals(id)) {
                    role = model.getValueAt(i, 2).toString();
                }
            }
            if (role.equals("manager")) {
                JOptionPane.showMessageDialog(frame, "You cannot delete a manager!!");
            } else if (role.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "No User with the given ID exists!!");
            } else {
                u.delete(id);
                model.setUsersInfo();
                JOptionPane.showMessageDialog(frame, "User deleted successfully!!");
            }
        }
    }
}
