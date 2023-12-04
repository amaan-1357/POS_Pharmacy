package Controller.ActionListionerController.Admin.Inventory.UpdateProductFrame;

import View.InputFrames.AddSupplierFrame2;
import View.InputFrames.UpdateProductFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * ActionListener for opening the Add Supplier frame in the Update Product frame.
 */
public class AddSupplierListener implements ActionListener {
    private final UpdateProductFrame frame;

    /**
     * Constructor for AddSupplierListener.
     *
     * @param f UpdateProductFrame instance
     */
    public AddSupplierListener(UpdateProductFrame f) {
        frame = f;
    }

    /**
     * Invoked when an action occurs. Opens the Add Supplier frame when the action is triggered.
     *
     * @param actionEvent The event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        SwingUtilities.invokeLater(() -> {
            // Create and display the Add Supplier frame
            AddSupplierFrame2 addSupplierFrame = new AddSupplierFrame2(frame);
            addSupplierFrame.setVisible(true);
        });
    }
}
