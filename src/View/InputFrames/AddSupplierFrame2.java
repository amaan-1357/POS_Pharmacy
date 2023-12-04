package View.InputFrames;

import Controller.ActionListionerController.Admin.Inventory.UpdateProductFrame.AddSupplierFrame2.ConfirmButtonListener;
import Controller.ActionListionerController.CancelButtonListener;

import javax.swing.*;
import java.awt.*;

/**
 * AddSupplierFrame2 class represents a JFrame for adding supplier information in the context of updating a product.
 */
public class AddSupplierFrame2 extends JFrame {
    private final JTextField supplierNameField;
    private final JTextField emailField;
    private final JTextField phoneNoField;

    /**
     * Constructs the AddSupplierFrame2.
     *
     * @param af The UpdateProductFrame reference.
     */
    public AddSupplierFrame2(UpdateProductFrame af){
        setTitle("Supplier Information");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Create labels
        JLabel supplierNameLabel = new JLabel("Supplier Name:");
        JLabel emailLabel = new JLabel("Email:");
        JLabel phoneNoLabel = new JLabel("Phone No:");

        // Create text fields
        supplierNameField = new JTextField();
        emailField = new JTextField();
        phoneNoField = new JTextField();

        // Create buttons
        JButton cancelButton = new JButton("Cancel");
        JButton confirmButton = new JButton("Confirm");

        // Set layout manager
        setLayout(new GridLayout(4, 2, 10, 10)); // 4 rows, 2 columns, with gaps

        // Add components to the frame
        add(supplierNameLabel);
        add(supplierNameField);
        add(emailLabel);
        add(emailField);
        add(phoneNoLabel);
        add(phoneNoField);
        add(cancelButton);
        add(confirmButton);

        // Add action listener to the Confirm button
        confirmButton.addActionListener(new ConfirmButtonListener(this, af));

        // Add action listener to the Cancel button
        cancelButton.addActionListener(new CancelButtonListener(this));

        // Set default close operation
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set frame size
        setSize(400, 200);

        // Set frame visibility
        setVisible(true);
    }

    public JTextField getSupplierNameField() {
        return supplierNameField;
    }

    public JTextField getEmailField() {
        return emailField;
    }

    public JTextField getPhoneNoField() {
        return phoneNoField;
    }
}
