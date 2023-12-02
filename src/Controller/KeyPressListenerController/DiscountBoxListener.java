package Controller.KeyPressListenerController;

import View.Panels.SalesPanel;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * The DiscountBoxListener class implements DocumentListener and handles events for changes in the discount text field.
 * It ensures valid input for the discount and updates the total amount accordingly.
 */
public class DiscountBoxListener implements DocumentListener {

    private SalesPanel frame;  // Reference to the SalesPanel frame

    /**
     * Constructor for DiscountBoxListener.
     *
     * @param frame The SalesPanel frame containing the discount text field.
     */
    public DiscountBoxListener(SalesPanel frame) {
        this.frame = frame;
    }

    /**
     * Invoked when text is inserted into the discount text field.
     *
     * @param documentEvent The DocumentEvent that triggered the event.
     */
    @Override
    public void insertUpdate(DocumentEvent documentEvent) {
        // Check if the entered value is a valid integer
        String q = frame.getDiscountField().getText();
        if (!q.matches("\\d*")) {
            JOptionPane.showMessageDialog(frame, "Invalid value",
                    "Error", JOptionPane.ERROR_MESSAGE);
            setText(frame.getDiscountField());
        } else if (q.isEmpty()) {
            // If the discount field is empty, reset the total amount
            setText(frame.getDiscountField());
            Double s = Double.parseDouble(frame.getSubTotal().getText());
            try {
                s = Double.parseDouble(String.format("%.0f", Double.parseDouble(s.toString())));
            } catch (NumberFormatException exception) {
                System.out.println("Invalid input format");
            }
            frame.getTotal().setText(s.toString());
        } else if (Integer.parseInt(q) > 100) {
            // Display an error message for discounts greater than 100%
            JOptionPane.showMessageDialog(frame, "Discount cannot be greater than 100",
                    "Error", JOptionPane.ERROR_MESSAGE);
            setText(frame.getDiscountField());
        } else {
            // Update the total amount based on the entered discount
            Double s = Double.parseDouble(frame.getSubTotal().getText());
            Double t = s - ((s / 100) * Double.parseDouble(frame.getDiscountField().getText()));
            try {
                t = Double.parseDouble(String.format("%.0f", Double.parseDouble(t.toString())));
            } catch (NumberFormatException exception) {
                System.out.println("Invalid input format");
            }
            frame.getTotal().setText(t.toString());
        }
    }

    /**
     * Invoked when text is removed from the discount text field.
     *
     * @param documentEvent The DocumentEvent that triggered the event.
     */
    @Override
    public void removeUpdate(DocumentEvent documentEvent) {
        // Check if the entered value is a valid integer
        String q = frame.getDiscountField().getText();
        if (!q.matches("\\d*")) {
            JOptionPane.showMessageDialog(frame, "Invalid value",
                    "Error", JOptionPane.ERROR_MESSAGE);
            setText(frame.getDiscountField());
        } else if (q.isEmpty()) {
            // If the discount field is empty, reset the total amount
            setText(frame.getDiscountField());
            Double s = Double.parseDouble(frame.getSubTotal().getText());
            try {
                s = Double.parseDouble(String.format("%.0f", Double.parseDouble(s.toString())));
            } catch (NumberFormatException exception) {
                System.out.println("Invalid input format");
            }
            frame.getTotal().setText(s.toString());
        } else if (Integer.parseInt(q) > 100) {
            // Display an error message for discounts greater than 100%
            JOptionPane.showMessageDialog(frame, "Discount cannot be greater than 100",
                    "Error", JOptionPane.ERROR_MESSAGE);
            setText(frame.getDiscountField());
        } else {
            // Update the total amount based on the entered discount
            Double s = Double.parseDouble(frame.getSubTotal().getText());
            Double t = s - ((s / 100) * Double.parseDouble(frame.getDiscountField().getText()));
            try {
                t = Double.parseDouble(String.format("%.0f", Double.parseDouble(t.toString())));
            } catch (NumberFormatException exception) {
                System.out.println("Invalid input format");
            }
            frame.getTotal().setText(t.toString());
        }
    }

    /**
     * Invoked when the text in the discount text field is changed.
     *
     * @param documentEvent The DocumentEvent that triggered the event.
     */
    @Override
    public void changedUpdate(DocumentEvent documentEvent) {
        // Check if the entered value is a valid integer
        String q = frame.getDiscountField().getText();
        if (!q.matches("\\d*")) {
            JOptionPane.showMessageDialog(frame, "Invalid value",
                    "Error", JOptionPane.ERROR_MESSAGE);
            setText(frame.getDiscountField());
        } else if (q.isEmpty()) {
            // If the discount field is empty, reset the total amount
            setText(frame.getDiscountField());
            Double s = Double.parseDouble(frame.getSubTotal().getText());
            try {
                s = Double.parseDouble(String.format("%.0f", Double.parseDouble(s.toString())));
            } catch (NumberFormatException exception) {
                System.out.println("Invalid input format");
            }
            frame.getTotal().setText(s.toString());
        } else if (Integer.parseInt(q) > 100) {
            // Display an error message for discounts greater than 100%
            JOptionPane.showMessageDialog(frame, "Discount cannot be greater than 100",
                    "Error", JOptionPane.ERROR_MESSAGE);
            setText(frame.getDiscountField());
        } else {
            // Update the total amount based on the entered discount
            Double s = Double.parseDouble(frame.getSubTotal().getText());
            Double t = s - ((s / 100) * Double.parseDouble(frame.getDiscountField().getText()));
            try {
                t = Double.parseDouble(String.format("%.0f", Double.parseDouble(t.toString())));
            } catch (NumberFormatException exception) {
                System.out.println("Invalid input format");
            }
            frame.getTotal().setText(t.toString());
        }
    }

    /**
     * Helper method to set the text of a JTextField asynchronously.
     *
     * @param t The JTextField to set the text for.
     */
    public void setText(JTextField t) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // This will be executed outside the event-handling code
                t.setText("");
            }
        });
    }
}
