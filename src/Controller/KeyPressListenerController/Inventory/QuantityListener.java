package Controller.KeyPressListenerController.Inventory;

import View.InputFrames.AddProductFrame;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * The QuantityListener class implements the DocumentListener interface to validate
 * and handle input in a text field for quantity values.
 */
public class QuantityListener implements DocumentListener {
    // Reference to the frame containing the text field
    AddProductFrame frame;

    /**
     * Constructor for QuantityListener.
     *
     * @param ap The AddProductFrame instance containing the text field.
     */
    public QuantityListener(AddProductFrame ap) {
        frame = ap;
    }

    /**
     * Called when text is inserted into the document.
     *
     * @param documentEvent The DocumentEvent describing the insertion.
     */
    @Override
    public void insertUpdate(DocumentEvent documentEvent) {
        validateAndHandleInput();
    }

    /**
     * Called when text is removed from the document.
     *
     * @param documentEvent The DocumentEvent describing the removal.
     */
    @Override
    public void removeUpdate(DocumentEvent documentEvent) {
        validateAndHandleInput();
    }

    /**
     * Called when attributes of the document are changed.
     *
     * @param documentEvent The DocumentEvent describing the change.
     */
    @Override
    public void changedUpdate(DocumentEvent documentEvent) {
        validateAndHandleInput();
    }

    /**
     * Validates the input in the text field and handles any invalid input.
     */
    private void validateAndHandleInput() {
        String input = frame.getQuantityField().getText();
        if (!input.matches("\\d*")) {
            // Show an error message for invalid input
            JOptionPane.showMessageDialog(frame, "Invalid value",
                    "Error", JOptionPane.ERROR_MESSAGE);
            // Clear the text field
            clearTextField(frame.getQuantityField());
        }
    }

    /**
     * Clears the text in the given text field using SwingUtilities.invokeLater.
     *
     * @param textField The JTextField to clear.
     */
    public void clearTextField(JTextField textField) {
        SwingUtilities.invokeLater(() -> {
            // This will be executed outside the event-handling code
            textField.setText("");
        });
    }
}
