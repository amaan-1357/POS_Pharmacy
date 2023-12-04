package Controller.ActionListionerController.Admin.User;

import View.InputFrames.AddUserFrame;
import View.Panels.UsersPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The AddUserButtonListener class handles the action when the "Add User" button is clicked.
 */
public class AddUserButtonListener implements ActionListener {
    private final UsersPanel frame;

    /**
     * Constructor for the AddUserButtonListener.
     *
     * @param frame The UsersPanel frame where the action is performed.
     */
    public AddUserButtonListener(UsersPanel frame) {
        this.frame = frame;
    }

    /**
     * Invoked when the "Add User" button is clicked, opens the AddUserFrame.
     *
     * @param actionEvent The action event triggered by the button click.
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        SwingUtilities.invokeLater(() -> {
            AddUserFrame af = new AddUserFrame(frame);
            af.setVisible(true);
        });
    }
}
