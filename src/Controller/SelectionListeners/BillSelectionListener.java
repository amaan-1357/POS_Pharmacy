package Controller.SelectionListeners;

import Controller.TableModelController.BillTableModel;
import View.Panels.SalesPanel;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * The BillSelectionListener class implements ListSelectionListener and handles events when
 * selecting items in the bill table. It provides functionality to remove selected items from
 * the bill and update the total and subtotal amounts.
 */
public class BillSelectionListener implements ListSelectionListener {
    // Reference to the SalesPanel frame
    private final SalesPanel frame;
    // Reference to the bill table
    private final JTable table;
    // Reference to the bill table model
    private final BillTableModel model;

    /**
     * Constructor for BillSelectionListener.
     *
     * @param frame The SalesPanel frame containing the bill.
     */
    public BillSelectionListener(SalesPanel frame) {
        this.frame = frame;
        this.model = (BillTableModel) frame.getBillModel();
        this.table = frame.getBill();
    }

    /**
     * Invoked when the selection value of the bill table changes.
     *
     * @param e The ListSelectionEvent that triggered the event.
     */
    @Override
    public void valueChanged(ListSelectionEvent e) {
        // Ignore events during adjustment
        if (e.getValueIsAdjusting()) {
            return;
        }

        int selectedRow = table.getSelectedRow();

        // Return if no row is selected
        if (selectedRow == -1) {
            return;
        }

        // Ask for confirmation to remove the selected item from the bill
        if (JOptionPane.showConfirmDialog(frame, "Do You want to remove this item from the bill.",
                "Confirmation", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {

            // Calculate the price of the selected item and update subtotal and total amounts
            double price = Double.parseDouble(model.getValueAt(selectedRow, 3).toString());
            price = price + ((price / 100) * Integer.parseInt(frame.getTax().getText()));

            // Format the price to remove decimal points
            try {
                price = Double.parseDouble(String.format("%.0f", Double.parseDouble(Double.toString(price))));
            } catch (NumberFormatException exception) {
                System.out.println("Invalid input format");
            }

            double s = Double.parseDouble(frame.getSubTotal().getText());
            s = s - price;

            // Format the subtotal amount to remove decimal points
            try {
                s = Double.parseDouble(String.format("%.0f", Double.parseDouble(Double.toString(s))));
            } catch (NumberFormatException exception) {
                System.out.println("Invalid input format");
            }

            // Update total and subtotal amounts
            if (frame.getDiscountField().getText().isEmpty())
                frame.getTotal().setText(Double.toString(s));
            else {
                double t = s - ((s / 100) * Double.parseDouble(frame.getDiscountField().getText()));

                // Format the total amount to remove decimal points
                try {
                    t = Double.parseDouble(String.format("%.0f", Double.parseDouble(Double.toString(t))));
                } catch (NumberFormatException exception) {
                    System.out.println("Invalid input format");
                }

                frame.getTotal().setText(Double.toString(t));
            }

            frame.getSubTotal().setText(Double.toString(s));
            frame.setS_Total(s);

            // Remove the selected row from the bill table model
            model.removeRow(selectedRow);

            // Reset amounts if there are no items in the bill
            if (model.getRowCount() == 0) {
                frame.getSubTotal().setText("0.0");
                frame.getTotal().setText("0.0");
                frame.setS_Total(0.0);
            }
        } else {
            // Clear the selection if removal is canceled
            table.clearSelection();
        }
    }
}
