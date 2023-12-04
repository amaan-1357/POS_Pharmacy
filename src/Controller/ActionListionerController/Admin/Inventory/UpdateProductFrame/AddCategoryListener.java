package Controller.ActionListionerController.Admin.Inventory.UpdateProductFrame;

import Controller.EntityControllers.ProductCategory;
import View.InputFrames.UpdateProductFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * ActionListener for adding a new category in the Update Product frame.
 */
public class AddCategoryListener implements ActionListener {
    private final UpdateProductFrame frame;
    private final ProductCategory pc = new ProductCategory();

    /**
     * Constructor for AddCategoryListener.
     *
     * @param ip UpdateProductFrame instance
     */
    public AddCategoryListener(UpdateProductFrame ip) {
        this.frame = ip;
    }

    /**
     * Invoked when an action occurs. Prompts the user to enter a new category name and adds it to the database.
     *
     * @param actionEvent The event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        // Prompt user to enter a new category name
        String name = JOptionPane.showInputDialog(frame, "Category Name:");

        // Get the list of existing category names
        ArrayList<String> categoryList = pc.getNames();

        // Validation and insertion logic
        if ((name == null)) {
            JOptionPane.showMessageDialog(frame, "Please enter a category name");
        } else if (name.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please enter a category name");
        } else if (!categoryList.contains(name)) {
            // If category doesn't exist, insert it into the database
            pc.insert(name);
            JOptionPane.showMessageDialog(frame, "Category Added");
            //noinspection unchecked
            frame.getnCategory().addItem(name); // Add the new category to the ComboBox in UpdateProductFrame
        } else {
            JOptionPane.showMessageDialog(frame, "Category Already Exists");
        }
    }
}
