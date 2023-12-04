package Controller.ActionListionerController.Admin.Inventory.AddProductFrame.AddSupplierFrame;

import Controller.EntityControllers.Supplier;
import View.InputFrames.AddProductFrame;
import View.InputFrames.AddSupplierFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// ActionListener to handle confirmation of adding a new supplier
public class ConfirmSupplierListener implements ActionListener {
    private final AddSupplierFrame frame;
    private final AddProductFrame frame1;

    // Constructor to initialize the listener with the relevant frames
    public ConfirmSupplierListener(AddSupplierFrame af, AddProductFrame apf) {
        frame = af;
        frame1 = apf;
    }

    // Method called when the "Confirm" button is clicked
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        // Check if the supplier name is empty
        if (frame.getSupplierNameField().getText().isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please enter a supplier name");
            return;
        } else if (frame.getPhoneNoField().getText().isEmpty()) {
            // Check if the phone number is empty
            JOptionPane.showMessageDialog(frame, "Please enter a phone number");
            return;
        } else {
            // Validate the phone number format
            String phoneNumberRegex = "^03\\d{9}$";
            Pattern pattern = Pattern.compile(phoneNumberRegex);
            Matcher matcher = pattern.matcher(frame.getPhoneNoField().getText());
            if (!matcher.matches()) {
                JOptionPane.showMessageDialog(frame, "Please enter a valid phone number");
                return;
            }
        }

        // Check if the email is provided
        Supplier s;
        if (frame.getEmailField().getText().isEmpty()) {
            // Create a new supplier without email
            s = new Supplier(frame.getSupplierNameField().getText(), frame.getPhoneNoField().getText());
            // Check if the supplier already exists
            ArrayList<Supplier> list = s.getSuppliers();
            for (Supplier supplier : list) {
                if (supplier.getName().equals(frame.getSupplierNameField().getText())) {
                    JOptionPane.showMessageDialog(frame, "Supplier already exists");
                    return;
                }
            }
            // Insert the supplier without email
            s.insertWithoutEmail();
            frame.dispose();
        } else {
            // Validate the email format
            String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
            Pattern pattern = Pattern.compile(emailRegex);
            Matcher matcher = pattern.matcher(frame.getEmailField().getText());
            if (!matcher.matches()) {
                JOptionPane.showMessageDialog(frame, "Please enter a valid email");
                return;
            }
            // Create a new supplier with email
            s = new Supplier(frame.getSupplierNameField().getText(), frame.getEmailField().getText(), frame.getPhoneNoField().getText());
            // Check if the supplier already exists
            ArrayList<Supplier> list = s.getSuppliers();
            for (Supplier supplier : list) {
                if (supplier.getName().equals(frame.getSupplierNameField().getText())) {
                    JOptionPane.showMessageDialog(frame, "Supplier already exists");
                    return;
                }
            }
            // Insert the supplier with email
            s.insert();
            frame.dispose();
        }

        // Update the supplier combo box in the AddProductFrame
        frame1.getSupplierComboBox().addItem(frame.getSupplierNameField().getText());
    }
}