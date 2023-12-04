package Controller.ActionListionerController.Admin.Inventory.UpdateProductFrame.AddSupplierFrame2;

import Controller.EntityControllers.Supplier;
import View.InputFrames.UpdateProductFrame;
import View.InputFrames.AddSupplierFrame2;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ActionListener for confirming the addition of a new supplier in the Add Supplier frame.
 */
public class ConfirmButtonListener implements ActionListener {
    private final AddSupplierFrame2 frame;
    private final UpdateProductFrame frame1;

    /**
     * Constructor for ConfirmButtonListener.
     *
     * @param af  AddSupplierFrame2 instance
     * @param apf UpdateProductFrame instance
     */
    public ConfirmButtonListener(AddSupplierFrame2 af, UpdateProductFrame apf) {
        frame = af;
        frame1 = apf;
    }

    /**
     * Invoked when an action occurs. Performs validation and inserts the new supplier into the database.
     *
     * @param actionEvent The event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        // Validation checks for supplier information
        if (frame.getSupplierNameField().getText().isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please enter a supplier name");
            return;
        } else if (frame.getPhoneNoField().getText().isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please enter a phone number");
            return;
        } else {
            String phoneNumberRegex = "^03\\d{9}$";
            Pattern pattern = Pattern.compile(phoneNumberRegex);
            Matcher matcher = pattern.matcher(frame.getPhoneNoField().getText());
            if (!matcher.matches()) {
                JOptionPane.showMessageDialog(frame, "Please enter a valid phone number");
                return;
            }
        }

        // If email field is empty, create a new supplier without email
        Supplier s;
        if (frame.getEmailField().getText().isEmpty()) {
            s = new Supplier(frame.getSupplierNameField().getText(), frame.getPhoneNoField().getText());
            ArrayList<Supplier> list = s.getSuppliers();
            for (Supplier supplier : list) {
                if (supplier.getName().equals(frame.getSupplierNameField().getText())) {
                    JOptionPane.showMessageDialog(frame, "Supplier already exists");
                    return;
                }
            }
            s.insertWithoutEmail();
            frame.dispose();
        } else {
            // If email field is not empty, validate and create a new supplier with email
            String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
            Pattern pattern = Pattern.compile(emailRegex);
            Matcher matcher = pattern.matcher(frame.getEmailField().getText());
            if (!matcher.matches()) {
                JOptionPane.showMessageDialog(frame, "Please enter a valid email");
                return;
            }
            s = new Supplier(frame.getSupplierNameField().getText(), frame.getEmailField().getText(), frame.getPhoneNoField().getText());
            ArrayList<Supplier> list = s.getSuppliers();
            for (Supplier supplier : list) {
                if (supplier.getName().equals(frame.getSupplierNameField().getText())) {
                    JOptionPane.showMessageDialog(frame, "Supplier already exists");
                    return;
                }
            }
            s.insert();
            frame.dispose();
        }

        // Add the new supplier to the ComboBox in UpdateProductFrame
        //noinspection unchecked
        frame1.getnSupplier().addItem(frame.getSupplierNameField().getText());
    }
}
