package Controller.ActionListionerController.Admin.Inventory;

import Controller.EntityControllers.ProductCategory;
import View.Panels.InventoryPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * ActionListener for handling the addition of a new product category in the InventoryPanel.
 */
public class AddCategoryListener implements ActionListener {
    private final InventoryPanel frame;
    private final ProductCategory pc= new ProductCategory();

    /**
     * Constructor for AddCategoryListener.
     *
     * @param ip InventoryPanel instance
     */
    public AddCategoryListener(InventoryPanel ip) {
        this.frame = ip;
    }

    /**
     * Invoked when an action occurs. Handles the addition of a new product category.
     *
     * @param actionEvent The event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        // Show a dialog to get the name of the new product category
        String name = JOptionPane.showInputDialog(frame, "Category Name:");

        // Get the list of existing category names
        ArrayList<String> categoryList = pc.getNames();

        // Check if the user cancelled or left the input empty
        if (name == null || name.isEmpty()) {
            return;
        }

        // Check if the category already exists
        if (!categoryList.contains(name)) {
            // Insert the new category and show a success message
            pc.insert(name);
            JOptionPane.showMessageDialog(frame, "Category Added");
        } else {
            // Show a message indicating that the category already exists
            JOptionPane.showMessageDialog(frame, "Category Already Exists");
        }
    }
}
