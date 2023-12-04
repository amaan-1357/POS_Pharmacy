package Controller.KeyPressListenerController.Inventory;

import Controller.TableModelController.ProductTableModel;
import View.Panels.InventoryPanel;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * The SearchBoxInventoryListener class implements DocumentListener and handles events
 * when the text in the search box changes. It updates the product table model to display
 * either all products or searched products based on the entered text.
 */
public class SearchBoxInventoryListener implements DocumentListener {
    // Reference to the InventoryPanel frame
    private final InventoryPanel frame;

    // Reference to the product table model
    private final ProductTableModel model;

    /**
     * Constructor for SearchBoxInventoryListener.
     *
     * @param ip The InventoryPanel frame containing the search box and product table model.
     */
    public SearchBoxInventoryListener(InventoryPanel ip) {
        frame = ip;
        model = (ProductTableModel) frame.getProductModel();
    }

    /**
     * Invoked when text is inserted into the search box.
     *
     * @param e The event that characterizes the change.
     */
    @Override
    public void insertUpdate(DocumentEvent e) {
        // Update the product table model with searched products
        model.setSearchedInfo(frame.getSearchBox().getText());
    }

    /**
     * Invoked when text is removed from the search box.
     *
     * @param e The event that characterizes the change.
     */
    @Override
    public void removeUpdate(DocumentEvent e) {
        // Update the product table model based on whether the search box is empty
        if (frame.getSearchBox().getText().isEmpty())
            model.setProductInfo();
        else
            model.setSearchedInfo(frame.getSearchBox().getText());
    }

    /**
     * Invoked when text in the search box is changed.
     *
     * @param e The event that characterizes the change.
     */
    @Override
    public void changedUpdate(DocumentEvent e) {
        // Update the product table model based on whether the search box is empty
        if (frame.getSearchBox().getText().isEmpty())
            model.setProductInfo();
        else
            model.setSearchedInfo(frame.getSearchBox().getText());
    }
}
