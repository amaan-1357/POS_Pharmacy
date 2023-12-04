package Controller.KeyPressListenerController.Inventory;

import View.InputFrames.AddProductFrame;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class LimitListener implements DocumentListener {
    AddProductFrame frame;
    public LimitListener(AddProductFrame frame) {
        this.frame = frame;
    }
    @Override
    public void insertUpdate(DocumentEvent documentEvent) {
        String q = frame.getLowerLimitField().getText();
        if (!q.matches("\\d*")) {
            JOptionPane.showMessageDialog(frame, "Invalid value",
                    "Error", JOptionPane.ERROR_MESSAGE);
            setText(frame.getLowerLimitField());
        }
    }

    @Override
    public void removeUpdate(DocumentEvent documentEvent) {
        String q = frame.getLowerLimitField().getText();
        if (!q.matches("\\d*")) {
            JOptionPane.showMessageDialog(frame, "Invalid value",
                    "Error", JOptionPane.ERROR_MESSAGE);
            setText(frame.getLowerLimitField());
        }
    }

    @Override
    public void changedUpdate(DocumentEvent documentEvent) {
        String q = frame.getLowerLimitField().getText();
        if (!q.matches("\\d*")) {
            JOptionPane.showMessageDialog(frame, "Invalid value",
                    "Error", JOptionPane.ERROR_MESSAGE);
            setText(frame.getLowerLimitField());
        }
    }
    public void setText(JTextField t) {
        SwingUtilities.invokeLater(() -> {
            // This will be executed outside the event-handling code
            t.setText("");
        });
    }
}
