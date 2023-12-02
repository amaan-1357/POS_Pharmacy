package Controller.ComponentListenerController;

import View.LoginPage;

import javax.swing.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

/**
 * The LoginPageComponent class is a component listener controller for managing component resizing in the LoginPage view.
 */
public class LoginPageComponent extends ComponentAdapter {
    private LoginPage frame;
    private String north = SpringLayout.NORTH;
    private String west = SpringLayout.WEST;

    /**
     * Constructs a LoginPageComponent with the specified LoginPage frame.
     *
     * @param lp The LoginPage frame to be managed by this component listener.
     */
    public LoginPageComponent(LoginPage lp) {
        frame = lp;
    }

    /**
     * Overrides the componentResized method to dynamically adjust the position of UI components when the login page is resized.
     *
     * @param e The ComponentEvent representing the component resize event.
     */
    @Override
    public void componentResized(ComponentEvent e) {
        int height = frame.getHeight();
        int width = frame.getWidth();

        // Adjust the position of U_name component
        frame.getsLayout().putConstraint(north, frame.getU_name(), ((height / 2) - (height / 10)), north, frame.getRootPane());
        frame.getsLayout().putConstraint(west, frame.getU_name(), ((width / 2) - (width / 10)), west, frame.getRootPane());

        // Adjust the position of Username and Pass components
        frame.getsLayout().putConstraint(north, frame.getUsername(), ((height / 2) - (height / 10)), north, frame.getRootPane());
        frame.getsLayout().putConstraint(west, frame.getPass(), ((width / 2) - (width / 10)), west, frame.getRootPane());
    }
}
