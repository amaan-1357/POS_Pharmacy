package Controller.ActionListionerController.Admin.User;

import Controller.EntityControllers.User;
import Controller.TableModelController.UserTableModel;
import View.Panels.UsersPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The EditRoleButtonListener class handles the action when the "Edit Role" button is clicked.
 */
public class EditRoleButtonListener implements ActionListener {
    private final User u = new User();
    private final UsersPanel frame;
    private UserTableModel model;
    private Integer UID;

    /**
     * Constructor for the EditRoleButtonListener.
     *
     * @param usersPanel The UsersPanel frame where the action is performed.
     */
    public EditRoleButtonListener(UsersPanel usersPanel, Integer UID) {
        frame = usersPanel;
        model = (UserTableModel) frame.getUserModel();
        this.UID = UID;
    }

    /**
     * Invoked when the "Edit Role" button is clicked, prompts the user to enter the User ID for role editing.
     *
     * @param actionEvent The action event triggered by the button click.
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        // Confirm the action with the user
        int selection = JOptionPane.showConfirmDialog(frame, "This action cannot be reversed. Are you sure?", "Confirmation", JOptionPane.YES_NO_OPTION);

        // If the user confirms, proceed with role editing
        if (selection == JOptionPane.YES_OPTION) {
            // Get the selected user's ID
            int id = getSelectedUserId();

            // Update the user's role
            u.update(id);

            // Refresh the user table model
            model = (UserTableModel) frame.getUserModel();
            model.setUsersInfo();

            // Display a confirmation message
            JOptionPane.showMessageDialog(frame, "Role Updated");
        }
    }

    // Helper method to get the selected user's ID
    private Integer getSelectedUserId() {
        // Get the parent frame
        Frame parentFrame = (Frame) SwingUtilities.getWindowAncestor(frame);

        // Create a dialog for user selection
        JDialog dialog = new JDialog(parentFrame, "Select the User", true);

        // Create a panel to hold the table and buttons
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Create a table model and table
        UserTableModel mod = new UserTableModel(UID);
        mod.setNonManagers();
        JTable table = new JTable(mod);

        // Add a list selection listener to the table
        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                dialog.dispose();  // Close the dialog when a row is selected
            }
        });

        // Add the table to a scroll pane and add the scroll pane to the panel
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane);

        // Add the panel to the dialog and set properties
        dialog.add(panel);
        dialog.setSize(400, 300);
        dialog.setLocationRelativeTo(frame);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        // Show the dialog (blocking)
        dialog.setVisible(true);

        // Return the selected user's ID
        try {

            return Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString());
        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }
}
