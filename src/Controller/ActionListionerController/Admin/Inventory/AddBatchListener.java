package Controller.ActionListionerController.Admin.Inventory;

import Controller.EntityControllers.Batch;
import Controller.EntityControllers.Product;
import Controller.TableModelController.ProductTableModel;
import View.InputFrames.AddProductFrame;
import View.Panels.InventoryPanel;
import View.Panels.SalesPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ActionListener for handling the addition of a batch in the InventoryPanel.
 */
public class AddBatchListener implements ActionListener {
    private final InventoryPanel frame;
    private final SalesPanel frame1;

    /**
     * Constructor for AddBatchListener.
     *
     * @param ip InventoryPanel instance
     * @param sp SalesPanel instance
     */
    public AddBatchListener(InventoryPanel ip, SalesPanel sp) {
        this.frame = ip;
        this.frame1 = sp;
    }

    /**
     * Invoked when an action occurs. Handles the addition of a batch to existing products or the creation of a new product.
     *
     * @param actionEvent The event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        // Ask the user if it is a batch of a new product
        int selection = JOptionPane.showConfirmDialog(frame, "Is it a batch of new product?", "Confirmation", JOptionPane.YES_NO_OPTION);

        // If the user selects 'Yes', open the AddProductFrame
        if (selection == JOptionPane.YES_OPTION) {
            SwingUtilities.invokeLater(() -> {
                AddProductFrame ap = new AddProductFrame(frame,frame1);
                ap.setVisible(true);
            });
        } else if (selection == JOptionPane.NO_OPTION) {
            // Show a dialog to block execution until the user selects a row
            Integer Id = getSelectedProductId();
            if (Id == null) {
                return;
            }

            // Validate and get the expiry date
            String pattern = "^\\d{4}-\\d{2}-\\d{2}$";
            Pattern regex = Pattern.compile(pattern);
            String input;
            while (true) {
                input = JOptionPane.showInputDialog("Enter a date (YYYY-MM-DD):");
                Matcher matcher = regex.matcher(input);
                if (!matcher.matches()) {
                    JOptionPane.showMessageDialog(frame, "Invalid date format. Please use YYYY-MM-DD.");
                } else {
                    String[] c = input.split("-");
                    LocalDate dd;
                    try {
                        dd = LocalDate.of(Integer.parseInt(c[0]), Integer.parseInt(c[1]), Integer.parseInt(c[2]));
                    } catch (DateTimeException ex) {
                        JOptionPane.showMessageDialog(frame, "Invalid date.");
                        continue;
                    }
                    Date d = Date.valueOf(dd);
                    Date da = Date.valueOf(LocalDate.now());
                    if (d.compareTo(da) < 0) {
                        JOptionPane.showMessageDialog(frame, "Expiry Date cannot be in the past");
                    } else if (d.compareTo(da) == 0) {
                        JOptionPane.showMessageDialog(frame, "Expiry Date cannot be today");
                    } else {
                        // Validate and get the quantity
                        String p = "\\d*";
                        regex = Pattern.compile(p);
                        while (true) {
                            input = JOptionPane.showInputDialog("Enter the quantity:");
                            Matcher matcher1 = regex.matcher(input);
                            if (!matcher1.matches()) {
                                JOptionPane.showMessageDialog(frame, "Invalid quantity format. Please use numbers only.");
                            } else {
                                // Insert the batch into the database and update the product quantity
                                Batch batch = new Batch(Id, d, Integer.parseInt(input));
                                batch.insert();
                                Product pa = new Product();
                                pa = pa.loadSingle(Id);
                                Integer q = pa.getQuantity() + Integer.parseInt(input);
                                pa.updateQty(Id, q);
                                JOptionPane.showMessageDialog(frame, "Batch Added");
                                frame.getProducts().clearSelection();
                                ProductTableModel df = (ProductTableModel) frame.getProductModel();
                                df.setProductInfo();
                                df = (ProductTableModel) frame1.getProductModel();
                                df.setProductInfo();
                                break;
                            }
                        }
                        break;
                    }
                }
            }
        }
    }

    /**
     * Opens a dialog with a table of products and waits for the user to select a product.
     *
     * @return The selected product ID
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