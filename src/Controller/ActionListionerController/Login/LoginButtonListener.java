package Controller.ActionListionerController.Login;

import Model.StoredProcedures.LoginAuthenticate;
import View.LoginPage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The LoginButtonListener class handles the action when the login button is clicked on the login page.
 */
public class LoginButtonListener implements ActionListener {
    private final LoginPage frame; // Reference to the LoginPage frame
    private final LoginAuthenticate authenticator = new LoginAuthenticate(); // Authenticator for user login

    /**
     * Constructor for the LoginButtonListener.
     *
     * @param lp The LoginPage frame to which this listener is attached.
     */
    public LoginButtonListener(LoginPage lp) {
        frame = lp;
    }

    /**
     * The actionPerformed method is invoked when the login button is clicked.
     *
     * @param e The ActionEvent representing the button click.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // Check if either username or password fields are empty
        if (frame.getUsername().getText().isEmpty() || frame.getPassword().getText().isEmpty()) {
            // Display an error message if fields are empty
            JOptionPane.showMessageDialog(frame, "Username and/or Password fields cannot be left empty.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            // Attempt to authenticate the user with the provided username and password
            if (authenticator.authenticate(frame.getUsername().getText(), frame.getPassword().getText())) {
                // If authentication is successful, close the login frame
                frame.dispose();
            } else {
                // If authentication fails, display an error message
                JOptionPane.showMessageDialog(frame, "Username or Password is Incorrect!!",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
