package Controller.ActionListionerController.Admin.Inventory;

import Controller.EntityControllers.Product;
import Controller.TableModelController.ProductTableModel;
import View.InputFrames.UpdateProductFrame;
import View.Panels.InventoryPanel;
import View.Panels.SalesPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * ActionListener for handling the update of a product in the InventoryPanel.
 */
public class UpdateProductListener implements ActionListener {
    private final InventoryPanel frame;
    private final SalesPanel frame1;
    private Product p = new Product();

    /**
     * Constructor for UpdateProductListener.
     *
     * @param ip InventoryPanel instance
     * @param sp SalesPanel instance
     */
    public UpdateProductListener(InventoryPanel ip, SalesPanel sp) {
        this.frame = ip;
        this.frame1 = sp;
    }

    /**
     * Invoked when an action occurs. Opens the UpdateProductFrame with the selected product for editing.
     *
     * @param actionEvent The event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        // Get the selected product ID
        Integer Id = getSelectedProductId();
        if (Id == null) {
            return;
        }

        // Load the selected product
        p = p.loadSingle(Id);

        // Open the UpdateProductFrame for editing
        SwingUtilities.invokeLater(() -> {
            UpdateProductFrame uf = new UpdateProductFrame(frame, frame1, p);
            uf.setVisible(true);
        });
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
