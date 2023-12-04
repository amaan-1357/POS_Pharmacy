package Controller.ActionListionerController.Admin;

import Controller.TableModelController.ProductTableModel;
import View.AdminPortal;
import View.Panels.InventoryPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The InventoryButtonListener class represents an ActionListener for the "Manage Inventory" button in the AdminPortal.
 */
public class InventoryButtonListener implements ActionListener {
    private final InventoryPanel ip;       // Reference to the InventoryPanel
    private final JPanel panel;             // Reference to the main content panel in AdminPortal

    /**
     * Constructor for the InventoryButtonListener.
     *
     * @param ap The AdminPortal associated with the listener.
     * @param ip The InventoryPanel associated with the listener.
     */
    public InventoryButtonListener(AdminPortal ap, InventoryPanel ip) {
        // Reference to the AdminPortal frame
        panel = ap.getContentPanel();   // Get the main content panel from AdminPortal
        this.ip = ip;           // Set the InventoryPanel reference
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        // Get the ProductTableModel associated with the InventoryPanel and set product information
        ProductTableModel pd = (ProductTableModel) ip.getProductModel();
        pd.setProductInfo();

        // Get the CardLayout from the content panel and show the InventoryPanel
        // CardLayout for switching between panels
        CardLayout cLayout = (CardLayout) panel.getLayout();
        cLayout.show(panel, "p2");   // Show the "p2" panel (Assuming "p2" is the identifier for InventoryPanel)
    }
}
