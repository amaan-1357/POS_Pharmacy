package Controller.SelectionListeners;

import Controller.TableModelController.BillTableModel;
import View.Panels.SalesPanel;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * The ProductTableSelection class implements ListSelectionListener and handles events when a row is selected in the
 * product table. It allows users to add a selected product to the sales bill with a specified quantity.
 */
public class ProductTableSelection implements ListSelectionListener {
    // Reference to the SalesPanel frame
    private final SalesPanel frame;
    // Reference to the product table
    private final JTable table;
    // Reference to the bill table model
    private final BillTableModel model;

    /**
     * Constructor for ProductTableSelection.
     *
     * @param sp The SalesPanel frame containing the product table and bill table model.
     */
    public ProductTableSelection(SalesPanel sp) {
        frame = sp;
        table = frame.getProducts();
        model = (BillTableModel) frame.getBillModel();
    }

    /**
     * Invoked when the value of the selection changes.
     *
     * @param e The event that characterizes the change.
     */
    @Override
    public void valueChanged(ListSelectionEvent e) {
        // Check if the event is still in adjusting state
        if (e.getValueIsAdjusting()) {
            return;
        }

        // Get the selected row index
        int selectedRow = table.getSelectedRow();

        // Check if a row is selected
        if (selectedRow == -1) {
            return;
        }

        int quantity;
        int qty = Integer.parseInt(table.getValueAt(selectedRow, 5).toString());
        String status = table.getValueAt(selectedRow, 6).toString();

        // Display error messages for discontinued or out-of-stock products
        if (status.equals("discontinued")) {
            JOptionPane.showMessageDialog(frame, "This product is discontinued.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            table.clearSelection();
            return;
        }

        if (qty == 0) {
            JOptionPane.showMessageDialog(frame, "This product is out of stock.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            table.clearSelection();
            return;
        }

        int PID = Integer.parseInt(table.getValueAt(selectedRow, 0).toString());
        int rows = model.getRowCount();

        // Check if the selected product is already in the bill
        for (int i = 0; i < rows; i++) {
            int p = Integer.parseInt(model.getValueAt(i, 0).toString());
            if (p == PID) {
                JOptionPane.showMessageDialog(frame, "Product already added to bill.",
                        "Error", JOptionPane.ERROR_MESSAGE);
                table.clearSelection();
                return;
            }
        }

        // Prompt the user to enter the quantity
        String q = JOptionPane.showInputDialog("Enter Quantity: ");

        // Check if the quantity input is canceled
        if (q == null) {
            JOptionPane.showMessageDialog(frame, "Quantity cannot be empty.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            table.clearSelection();
            return;
        }

        // Check if the quantity input is a valid integer
        if (q.matches("\\d+")) {
            quantity = Integer.parseInt(q);
        } else {
            JOptionPane.showMessageDialog(frame, "Invalid quantity.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            table.clearSelection();
            return;
        }

        // Check if there is enough quantity in stock
        if (quantity > qty) {
            JOptionPane.showMessageDialog(frame, "Not enough quantity in stock.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            table.clearSelection();
            return;
        }

        // Check if the quantity is zero
        if (quantity == 0) {
            JOptionPane.showMessageDialog(frame, "Quantity cannot be zero.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            table.clearSelection();
            return;
        }

        // Add the selected product to the bill table and update totals
        Double s = model.addProduct(PID, quantity);
        JOptionPane.showMessageDialog(frame, "Product added to bill.",
                "Success", JOptionPane.INFORMATION_MESSAGE);

        frame.setS_Total(frame.getS_Total() + s);
        frame.getSubTotal().setText(frame.getS_Total().toString());
        s = Double.parseDouble(frame.getSubTotal().getText());
        s = s + ((s / 100) * Integer.parseInt(frame.getTax().getText()));

        try {
            s = Double.parseDouble(String.format("%.2f", Double.parseDouble(s.toString())));
        } catch (NumberFormatException exception) {
            System.out.println("Invalid input format");
        }

        frame.getSubTotal().setText(s.toString());

        // Update the total amount based on discounts
        if (frame.getDiscountField().getText().isEmpty()) {
            double t = s;
            try {
                t = Double.parseDouble(String.format("%.0f", Double.parseDouble(Double.toString(t))));
            } catch (NumberFormatException exception) {
                System.out.println("Invalid input format");
            }
            frame.getTotal().setText(Double.toString(t));
        } else {
            double t = s - ((s / 100) * Double.parseDouble(frame.getDiscountField().getText()));
            try {
                t = Double.parseDouble(String.format("%.0f", Double.parseDouble(Double.toString(t))));
            } catch (NumberFormatException exception) {
                System.out.println("Invalid input format");
            }
            frame.getTotal().setText(Double.toString(t));
        }

        table.clearSelection();  // Clear the selection after processing
    }
}
