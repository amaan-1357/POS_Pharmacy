package Controller.ActionListionerController.Admin;

import View.AdminPortal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The UsersButtonListener class represents an ActionListener for the "Manage Users" button in the AdminPortal.
 */
public class UsersButtonListener implements ActionListener {
    private final JPanel panel;

    /**
     * Constructor for the UsersButtonListener.
     *
     * @param ap The AdminPortal associated with the listener.
     */
    public UsersButtonListener(AdminPortal ap) {
        panel = ap.getContentPanel();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        CardLayout cLayout = (CardLayout) panel.getLayout();
        cLayout.show(panel, "p3");
    }
}
