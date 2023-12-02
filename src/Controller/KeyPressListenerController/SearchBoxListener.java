package Controller.KeyPressListenerController;

import Controller.TableModelController.ProductTableModel;
import View.Panels.SalesPanel;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * The SearchBoxListener class implements DocumentListener and handles events when the text in the search box changes.
 * It updates the product table model to display either all products or searched products based on the entered text.
 */
public class SearchBoxListener implements DocumentListener {
    private SalesPanel frame;        // Reference to the SalesPanel frame
    private ProductTableModel model; // Reference to the product table model

    /**
     * Constructor for SearchBoxListener.
     *
     * @param sp The SalesPanel frame containing the search box and product table model.
     */
    public SearchBoxListener(SalesPanel sp) {
        frame = sp;
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
