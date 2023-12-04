package Controller.ActionListionerController.Admin.Inventory.AddProductFrame;

import View.InputFrames.AddProductFrame;
import View.InputFrames.AddSupplierFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * ActionListener for opening the Add Supplier frame from the Add Product frame.
 */
public class AddSupplierListener implements ActionListener {
    private final AddProductFrame frame;

    /**
     * Constructor for AddSupplierListener.
     *
     * @param f AddProductFrame instance
     */
    public AddSupplierListener(AddProductFrame f) {
        frame = f;
    }

    /**
     * Invoked when an action occurs. Opens the Add Supplier frame.
     *
     * @param actionEvent The event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        SwingUtilities.invokeLater(() -> {
            // Create and show the Add Supplier frame
            AddSupplierFrame addSupplierFrame = new AddSupplierFrame(frame);
            addSupplierFrame.setVisible(true);
        });
    }
}
