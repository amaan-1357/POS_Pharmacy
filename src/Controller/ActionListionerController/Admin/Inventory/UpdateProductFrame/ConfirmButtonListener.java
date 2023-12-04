package Controller.ActionListionerController.Admin.Inventory.UpdateProductFrame;

import Controller.EntityControllers.Product;
import Controller.EntityControllers.ProductCategory;
import Controller.EntityControllers.Supplier;
import Controller.TableModelController.ProductTableModel;
import View.InputFrames.UpdateProductFrame;
import View.Panels.InventoryPanel;
import View.Panels.SalesPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * ActionListener for handling the confirmation of product updates in the Update Product frame.
 */
public class ConfirmButtonListener implements ActionListener {
    private final UpdateProductFrame frame;
    private final InventoryPanel frame1;
    private final SalesPanel frame2;
    private final Product p;
    private final ProductCategory pc = new ProductCategory();
    private final Supplier s = new Supplier();

    /**
     * Constructor for ConfirmButtonListener.
     *
     * @param frame UpdateProductFrame instance
     * @param ip    InventoryPanel instance
     * @param sp    SalesPanel instance
     * @param p     Product instance
     */
    public ConfirmButtonListener(UpdateProductFrame frame, InventoryPanel ip, SalesPanel sp, Product p) {
        this.frame = frame;
        frame1 = ip;
        frame2 = sp;
        this.p = p;
    }

    /**
     * Invoked when an action occurs. Handles the confirmation of product updates and updates related components.
     *
     * @param actionEvent The event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        // Check and update the product information based on the user's input
        if (!frame.getnName().getText().isEmpty()) {
            p.setName(frame.getnName().getText());
        }
        if (!frame.getnPrice().getText().isEmpty()) {
            p.setPrice(Double.parseDouble(frame.getnPrice().getText()));
        }
        if (!frame.getnCategory().getItemAt(frame.getnCategory().getSelectedIndex()).equals("")) {
            pc.loadByName(frame.getnCategory().getItemAt(frame.getnCategory().getSelectedIndex()).toString());
            p.setCategoryID(pc.getId());
        }
        if (!frame.getnSupplier().getItemAt(frame.getnSupplier().getSelectedIndex()).equals("")) {
            s.loadByName(frame.getnSupplier().getItemAt(frame.getnSupplier().getSelectedIndex()).toString());
        }

        // Update the product in the database
        p.update();

        // Update the ProductTableModel for InventoryPanel
        ProductTableModel df = (ProductTableModel) frame1.getProductModel();
        df.setProductInfo();

        // Update the ProductTableModel for SalesPanel
        df = (ProductTableModel) frame2.getProductModel();
        df.setProductInfo();

        // Close the Update Product frame
        frame.dispose();
    }
}
