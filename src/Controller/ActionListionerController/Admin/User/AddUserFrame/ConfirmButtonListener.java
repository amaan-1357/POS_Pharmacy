package Controller.ActionListionerController.Admin.User.AddUserFrame;

import Controller.EntityControllers.User;
import Controller.TableModelController.UserTableModel;
import View.InputFrames.AddUserFrame;
import View.Panels.UsersPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * The ConfirmButtonListener class represents an ActionListener for the confirm button in the AddUserFrame.
 */
public class ConfirmButtonListener implements ActionListener {
    private User u = new User();
    private UsersPanel frame1;
    private AddUserFrame frame;
    private UserTableModel model;

    /**
     * Constructor for the ConfirmButtonListener.
     *
     * @param af The AddUserFrame associated with the listener.
     * @param up The UsersPanel associated with the listener.
     */
    public ConfirmButtonListener(AddUserFrame af, UsersPanel up) {
        frame1 = up;
        model = (UserTableModel) up.getUserModel();
        frame = af;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (frame.getNameField().getText().isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Name cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (frame.getPasswordField().getText().isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Password cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (frame.getUsernameField().getText().isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Username cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            ArrayList<String> uNames = u.getUName();
            if (uNames.contains(frame.getUsernameField().getText())) {
                JOptionPane.showMessageDialog(null, "Username already taken", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                u.insert(new User(frame.getNameField().getText().toString(),
                        frame.getUsernameField().getText().toString(),
                        frame.getPasswordField().getText().toString(),
                        frame.getRoleComboBox().getItemAt(frame.getRoleComboBox().getSelectedIndex()))
                );
                frame.dispose();
            }
        }
        model.setUsersInfo();
    }
}
