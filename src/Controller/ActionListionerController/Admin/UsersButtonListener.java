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
    private AdminPortal frame;
    private JPanel panel;
    private CardLayout cLayout;

    /**
     * Constructor for the UsersButtonListener.
     *
     * @param ap The AdminPortal associated with the listener.
     */
    public UsersButtonListener(AdminPortal ap) {
        this.frame = ap;
        panel = frame.getContentPanel();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        cLayout = (CardLayout) panel.getLayout();
        cLayout.show(panel, "p3");
    }
}
