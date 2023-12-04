package Controller.ActionListionerController.Admin.Inventory.AddProductFrame;

import Controller.EntityControllers.Batch;
import Controller.EntityControllers.Product;
import Controller.EntityControllers.ProductCategory;
import Controller.EntityControllers.Supplier;
import Controller.TableModelController.ProductTableModel;
import View.InputFrames.AddProductFrame;
import View.Panels.InventoryPanel;
import View.Panels.SalesPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ActionListener for confirming the addition of a new product in the Add Product frame.
 */
public class ConfirmProductListener implements ActionListener {
    private final AddProductFrame frame;
    private final InventoryPanel frame1;
    private final SalesPanel frame2;
    private Product p = new Product();
    private final ProductCategory pc = new ProductCategory();
    private final Supplier s = new Supplier();

    /**
     * Constructor for ConfirmProductListener.
     *
     * @param frame AddProductFrame instance
     * @param ip    InventoryPanel instance
     * @param sp    SalesPanel instance
     */
    public ConfirmProductListener(AddProductFrame frame, InventoryPanel ip, SalesPanel sp) {
        this.frame = frame;
        frame1 = ip;
        frame2 = sp;
    }

    /**
     * Invoked when an action occurs. Performs validation and inserts the new product into the database.
     *
     * @param actionEvent The event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        // Validation checks for product information
        if (frame.getProductNameField().getText().isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please enter a product name");
        } else if (frame.getPriceField().getText().isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please enter a price");
        } else if (frame.getQuantityField().getText().isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please enter a quantity");
        } else if (frame.getLowerLimitField().getText().isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please enter a lower limit");
        } else if (frame.getCategoryComboBox().getItemAt(frame.getCategoryComboBox().getSelectedIndex()).isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please select a category");
        } else if (frame.getSupplierComboBox().getItemAt(frame.getSupplierComboBox().getSelectedIndex()).isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please select a supplier");
        } else {
            // Check if a product with the same name already exists
            List<Product> productList = p.getProducts();
            for (Product pr : productList) {
                if (pr.getName().equals(frame.getProductNameField().getText())) {
                    JOptionPane.showMessageDialog(frame, "A product with that name already exists");
                    return;
                }
            }

            // Create a new Product instance with the provided information
            p = new Product(
                    frame.getProductNameField().getText(),
                    Double.parseDouble(frame.getPriceField().getText()),
                    pc.loadByName(frame.getCategoryComboBox().getItemAt(frame.getCategoryComboBox().getSelectedIndex())).getId(),
                    s.loadByName(frame.getSupplierComboBox().getItemAt(frame.getSupplierComboBox().getSelectedIndex())).getId(),
                    Integer.parseInt(frame.getQuantityField().getText()),
                    Integer.parseInt(frame.getLowerLimitField().getText())
            );

            // Insert the new product into the database
            p.insert();

            // Refresh ProductTableModel in both InventoryPanel and SalesPanel
            ProductTableModel productTableModel = (ProductTableModel) frame1.getProductModel();
            productTableModel.setProductInfo();
            productTableModel = (ProductTableModel) frame2.getProductModel();
            productTableModel.setProductInfo();

            // Prompt user for batch details
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
                    @SuppressWarnings("deprecation") Date d = new Date(Integer.parseInt(c[0]), Integer.parseInt(c[1]) - 1, Integer.parseInt(c[2]));
                    Date da = Date.valueOf(LocalDate.now());
                    if (d.compareTo(da) < 0) {
                        JOptionPane.showMessageDialog(frame, "Expiry Date cannot be in the past");
                    } else if (d.compareTo(da) == 0) {
                        JOptionPane.showMessageDialog(frame, "Expiry Date cannot be today");
                    } else {
                        // Create a new Batch instance and insert it into the database
                        Batch b = new Batch(p.getMaxId(), d, Integer.parseInt(frame.getQuantityField().getText()));
                        b.insert();
                        frame.dispose();
                        break;
                    }
                }
            }
        }
    }
}
