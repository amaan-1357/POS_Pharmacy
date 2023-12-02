package Controller.ActionListionerController;

import View.LoginPage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The LogoutButtonListener class implements ActionListener for handling logout button events.
 */
public class LogoutButtonListener implements ActionListener {
    private JFrame frame;

    /**
     * Constructs a LogoutButtonListener with the specified JFrame.
     *
     * @param f The JFrame to be closed upon logout.
     */
    public LogoutButtonListener(JFrame f) {
        this.frame = f;
    }

    /**
     * Invoked when the logout button is clicked. Closes the current frame and opens the login page.
     *
     * @param actionEvent The action event.
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        frame.dispose();
        SwingUtilities.invokeLater(() -> {
            LoginPage lp = new LoginPage();
        });
    }
}
