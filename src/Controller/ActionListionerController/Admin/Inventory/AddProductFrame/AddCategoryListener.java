package Controller.ActionListionerController.Admin.Inventory.AddProductFrame;

import Controller.EntityControllers.ProductCategory;
import View.InputFrames.AddProductFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * ActionListener for adding a new product category.
 */
public class AddCategoryListener implements ActionListener {
    private final AddProductFrame frame;
    private final ProductCategory pc = new ProductCategory();

    /**
     * Constructor for AddCategoryListener.
     *
     * @param ip AddProductFrame instance
     */
    public AddCategoryListener(AddProductFrame ip) {
        this.frame = ip;
    }

    /**
     * Invoked when an action occurs. Adds a new product category based on user input.
     *
     * @param actionEvent The event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        // Prompt user for category name
        String name = JOptionPane.showInputDialog(frame, "Category Name:");

        // Get existing category names
        ArrayList<String> categoryList = pc.getNames();

        // Check if user canceled the input or entered an empty string
        if ((name == null)) {
            JOptionPane.showMessageDialog(frame, "Please enter a category name");
        } else if (name.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please enter a category name");
        } else if (!categoryList.contains(name)) {  // Check if category already exists
            // Insert new category
            pc.insert(name);
            JOptionPane.showMessageDialog(frame, "Category Added");
            // Add the new category to the combo box
            frame.getCategoryComboBox().addItem(name);
        } else {
            JOptionPane.showMessageDialog(frame, "Category Already Exists");
        }
    }
}
