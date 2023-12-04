package Controller.ActionListionerController.Admin;

import View.AdminPortal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The SalesButtonListener class represents an ActionListener for the "Sales" button in the AdminPortal.
 */
public class SalesButtonListener implements ActionListener {
    private final JPanel panel;

    /**
     * Constructor for the SalesButtonListener.
     *
     * @param adminPortal The AdminPortal associated with the listener.
     */
    public SalesButtonListener(AdminPortal adminPortal) {
        panel = adminPortal.getContentPanel();  // Get the content panel from the AdminPortal
    }

    /**
     * Invoked when the "Sales" button is clicked. Switches the card layout to the "Sales" panel.
     *
     * @param actionEvent The action event triggered by the button click.
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        CardLayout cardLayout = (CardLayout) panel.getLayout();  // Get the CardLayout of the content panel
        cardLayout.show(panel, "p1");  // Show the "Sales" panel with the specified name ("p1")
    }
}
