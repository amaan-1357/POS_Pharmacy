package Controller.ActionListionerController.Admin.User.AddUserFrame;

import View.InputFrames.AddUserFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The CancelButtonListener class represents an ActionListener for the cancel button in the AddUserFrame.
 */
public class CancelButtonListener implements ActionListener {
    private AddUserFrame frame;

    /**
     * Constructor for the CancelButtonListener.
     *
     * @param af The AddUserFrame associated with the listener.
     */
    public CancelButtonListener(AddUserFrame af) {
        frame = af;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        frame.dispose();
    }
}
