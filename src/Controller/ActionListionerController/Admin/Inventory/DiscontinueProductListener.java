package Controller.ActionListionerController.Admin.Inventory;

import Controller.EntityControllers.Batch;
import Controller.EntityControllers.Product;
import Controller.TableModelController.ProductTableModel;
import View.Panels.InventoryPanel;
import View.Panels.SalesPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * ActionListener for handling the discontinuation of a product in the InventoryPanel.
 */
public class DiscontinueProductListener implements ActionListener {
    private final InventoryPanel frame;
    private final SalesPanel frame1;
    private final Product p = new Product();
    private final Batch b = new Batch();

    /**
     * Constructor for DiscontinueProductListener.
     *
     * @param frame  InventoryPanel instance
     * @param frame1 SalesPanel instance
     */
    public DiscontinueProductListener(InventoryPanel frame, SalesPanel frame1) {
        this.frame = frame;
        this.frame1 = frame1;
    }

    /**
     * Invoked when an action occurs. Discontinues the selected product and updates the UI.
     *
     * @param actionEvent The event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        // Display a warning and confirm dialog
        int selected = JOptionPane.showConfirmDialog(frame, "This action cannot be undone", "Warning", JOptionPane.YES_NO_OPTION);

        // Check if the user confirmed the action
        if (selected == JOptionPane.YES_OPTION) {
            Integer Id = getSelectedProductId();
            if (Id == null) {
                return;
            }

            // Update product status and delete associated batches
            p.updateStatus(Id);
            b.deleteByPID(Id);

            // Update product information in both InventoryPanel and SalesPanel
            ProductTableModel df = (ProductTableModel) frame.getProductModel();
            df.setProductInfo();
            df = (ProductTableModel) frame1.getProductModel();
            df.setProductInfo();
        }
    }

    /**
     * Displays a dialog to select a product and returns the selected product ID.
     *
     * @return The selected product ID or null if none selected
     */
    private Integer getSelectedProductId() {
        Frame parentFrame = (Frame) SwingUtilities.getWindowAncestor(frame);
        JDialog dialog = new JDialog(parentFrame, "Select a Product", true);

        // Create a panel to hold the table and buttons
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Create a table model and table
        ProductTableModel model = (ProductTableModel) frame.getProductModel();
        JTable table = new JTable(model);

        // Add a list selection listener to the table
        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                dialog.dispose();  // Close the dialog when a row is selected
            }
        });

        // Add the table to a scroll pane and add the scroll pane to the panel
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane);

        // Add the panel to the dialog and set properties
        dialog.add(panel);
        dialog.setSize(400, 300);
        dialog.setLocationRelativeTo(frame);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        // Show the dialog (blocking)
        dialog.setVisible(true);

        // Return the selected product ID
        try {
            return Integer.parseInt(frame.getProducts().getValueAt(table.getSelectedRow(), 0).toString());
        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }
}