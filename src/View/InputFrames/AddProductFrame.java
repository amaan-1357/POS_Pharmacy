package View.InputFrames;

import Controller.ActionListionerController.Admin.Inventory.AddProductFrame.AddCategoryListener;
import Controller.ActionListionerController.Admin.Inventory.AddProductFrame.AddSupplierListener;
import Controller.ActionListionerController.CancelButtonListener;
import Controller.ActionListionerController.Admin.Inventory.AddProductFrame.ConfirmProductListener;
import Controller.EntityControllers.ProductCategory;
import Controller.EntityControllers.Supplier;
import Controller.KeyPressListenerController.Inventory.LimitListener;
import Controller.KeyPressListenerController.Inventory.PriceListener;
import Controller.KeyPressListenerController.Inventory.QuantityListener;
import View.Panels.InventoryPanel;
import View.Panels.SalesPanel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * AddProductFrame class represents a JFrame for adding product information.
 */
public class AddProductFrame extends JFrame {
    private JTextField productNameField;
    private JTextField priceField;
    private JComboBox<String> categoryComboBox;
    private JComboBox<String> supplierComboBox;
    private JTextField quantityField;
    private JTextField lowerLimitField;

    /**
     * Constructs the AddProductFrame.
     *
     * @param ip The InventoryPanel reference.
     * @param sp The SalesPanel reference.
     */
    public AddProductFrame(InventoryPanel ip, SalesPanel sp){
        // Set the title of the frame
        setTitle("Product Information");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Create labels
        JLabel productNameLabel = new JLabel("Product Name:");
        JLabel priceLabel = new JLabel("Price:");
        JLabel categoryLabel = new JLabel("Category:");
        JLabel supplierLabel = new JLabel("Supplier:");
        JLabel quantityLabel = new JLabel("Quantity:");
        JLabel lowerLimitLabel = new JLabel("Lower Limit:");

        // Create text fields
        productNameField = new JTextField();
        priceField = new JTextField();
        quantityField = new JTextField();
        lowerLimitField = new JTextField();

        // Create combo boxes
        categoryComboBox = new JComboBox<>();
        supplierComboBox = new JComboBox<>();

        Supplier s = new Supplier();
        ArrayList<Supplier> sup = s.getSuppliers();
        ProductCategory c = new ProductCategory();
        ArrayList<ProductCategory> cat = c.getProductCategories();

        priceField.getDocument().addDocumentListener(new PriceListener(this));
        quantityField.getDocument().addDocumentListener(new QuantityListener(this));
        lowerLimitField.getDocument().addDocumentListener(new LimitListener(this));

        supplierComboBox.addItem("");
        for (Supplier supplier : sup) {
            supplierComboBox.addItem(supplier.getName());
        }
        categoryComboBox.addItem("");
        for (ProductCategory productCategory : cat) {
            categoryComboBox.addItem(productCategory.getName());
        }

        // Create buttons
        JButton addCategoryButton = new JButton("Add Category");
        JButton addSupplierButton = new JButton("Add Supplier");
        JButton cancelButton = new JButton("Cancel");
        JButton confirmButton = new JButton("Confirm");

        // Set layout manager
        setLayout(new GridLayout(7, 3, 10, 10)); // 7 rows, 3 columns, with gaps

        // Add components to the frame
        add(productNameLabel);
        add(productNameField);
        add(new JLabel()); // Empty label for spacing
        add(priceLabel);
        add(priceField);
        add(new JLabel());
        add(categoryLabel);
        add(categoryComboBox);
        add(addCategoryButton);
        add(supplierLabel);
        add(supplierComboBox);
        add(addSupplierButton);
        add(quantityLabel);
        add(quantityField);
        add(new JLabel());
        add(lowerLimitLabel);
        add(lowerLimitField);
        add(new JLabel());
        add(cancelButton);
        add(confirmButton);

        // Add action listener to the Add Category button
        addCategoryButton.addActionListener(new AddCategoryListener(this));

        // Add action listener to the Add Supplier button
        addSupplierButton.addActionListener(new AddSupplierListener(this));

        // Add action listener to the Confirm button
        confirmButton.addActionListener(new ConfirmProductListener(this,ip,sp));

        // Add action listener to the Cancel button
        cancelButton.addActionListener(new CancelButtonListener(this));

        // Set frame size
        setSize(400, 300);

        // Set frame visibility
        setVisible(true);
    }

    public JComboBox<String> getCategoryComboBox() {
        return categoryComboBox;
    }

    public JComboBox<String> getSupplierComboBox() {
        return supplierComboBox;
    }

    public JTextField getPriceField() {
        return priceField;
    }

    public JTextField getQuantityField() {
        return quantityField;
    }

    public JTextField getLowerLimitField() {
        return lowerLimitField;
    }

    public JTextField getProductNameField() {
        return productNameField;
    }
}
