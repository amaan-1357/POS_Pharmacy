package Controller.ActionListionerController.Admin;

import Controller.EntityControllers.Batch;
import Controller.EntityControllers.Product;
import Controller.EntityControllers.ProductCategory;
import Controller.EntityControllers.Supplier;
import View.AdminPortal;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * The InventoryReportListener class represents an ActionListener for generating an inventory report.
 */
public class InventoryReportListener implements ActionListener {
    private final Product p = new Product();            // Entity controller for Product
    private final Batch b = new Batch();                // Entity controller for Batch
    private ProductCategory pc = new ProductCategory();  // Entity controller for ProductCategory
    private Supplier s = new Supplier();          // Entity controller for Supplier
    private final AdminPortal frame;                    // Reference to the AdminPortal frame

    /**
     * Constructor for the InventoryReportListener.
     *
     * @param ap The AdminPortal associated with the listener.
     */
    public InventoryReportListener(AdminPortal ap) {
        frame = ap;  // Set the AdminPortal reference
    }

    @SuppressWarnings("CallToPrintStackTrace")
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String filePath = "inventory_report.txt";   // File path for the inventory report
        ArrayList<Product> productList = p.getProducts();  // Get the list of products from the database
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, false))) {
            // Loop through each product in the list
            for (Product product : productList) {
                // Get the batches associated with the product
                ArrayList<Batch> batchList = b.getBatchesByPID(product.getId());

                // Write product information to the file
                writer.write("Product Id: " + product.getId() + ": \n");
                writer.write("\t\tName: " + product.getName());
                writer.write("\n\t\tPrice: " + product.getPrice());
                pc = pc.loadSingle(product.getCategoryID());
                writer.write("\n\t\tCategory: " + pc.getName());
                s = s.loadSingle(product.getSupplierID());
                writer.write("\n\t\tSupplier: " + s.getName());
                writer.write("\n\t\tQuantity: " + product.getQuantity());
                writer.write("\n\t\tLimit: " + product.getLimit());
                writer.write("\n\t\tStatus: " + product.getStatus());
                writer.write("\n\t\tBatches: ");

                // Loop through each batch associated with the product
                for (int i = 0; i < batchList.size(); i++) {
                    writer.write("\n\t\t\tBatch " + i + " Expiry: " + batchList.get(i).getDate() +
                            " Quantity: " + batchList.get(i).getQuantity());
                }

                writer.write("\n");
            }

            // Display a message indicating successful report generation
            JOptionPane.showMessageDialog(frame, "Inventory report has been stored in inventory_report.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
