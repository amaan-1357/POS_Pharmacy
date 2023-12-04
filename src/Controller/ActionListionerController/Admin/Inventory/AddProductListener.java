package Controller.ActionListionerController.Admin.Inventory;

import View.InputFrames.AddProductFrame;
import View.Panels.InventoryPanel;
import View.Panels.SalesPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * ActionListener for handling the addition of a new product in the InventoryPanel.
 */
public class AddProductListener implements ActionListener {
    private final InventoryPanel frame;
    private final SalesPanel frame1;

    /**
     * Constructor for AddProductListener.
     *
     * @param ip InventoryPanel instance
     * @param sp SalesPanel instance
     */
    public AddProductListener(InventoryPanel ip, SalesPanel sp){
        this.frame = ip;
        this.frame1 = sp;
    }

    /**
     * Invoked when an action occurs. Opens the AddProductFrame for adding a new product.
     *
     * @param actionEvent The event to be processed
     */
    @SuppressWarnings("unused")
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        // Open the AddProductFrame using SwingUtilities.invokeLater to ensure proper thread handling
        SwingUtilities.invokeLater(() -> {
            AddProductFrame f = new AddProductFrame(frame, frame1);
            f.setVisible(true);
        });
    }
}
