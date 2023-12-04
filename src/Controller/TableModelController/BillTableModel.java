package Controller.TableModelController;

import Controller.EntityControllers.Product;

import javax.swing.table.DefaultTableModel;

/**
 * The BillTableModel class extends DefaultTableModel and represents the table model for displaying items in a bill.
 */
public class BillTableModel extends DefaultTableModel {

    private Product product = new Product(); // Instance of the Product class for loading product information

    /**
     * Constructor for BillTableModel.
     */
    public BillTableModel() {
        this.addColumn("PID");   // Column for Product ID
        this.addColumn("Name");  // Column for Product Name
        this.addColumn("Qty");   // Column for Quantity
        this.addColumn("Price"); // Column for Total Price
        // Reference to the SalesPanel
    }

    /**
     * Adds a product to the bill table with the specified quantity.
     *
     * @param PID The Product ID.
     * @param qty The quantity of the product.
     * @return The total price of the added product.
     */
    public Double addProduct(Integer PID, Integer qty) {
        product = product.loadSingle(PID); // Load product information based on the given Product ID
        double s = (product.getPrice() * qty);

        // Format the total price to two decimal places
        try {
            s = Double.parseDouble(String.format("%.2f", Double.parseDouble(Double.toString(s))));
        } catch (NumberFormatException exception) {
            System.out.println("Invalid input format");
        }

        // Add the product to the bill table
        Object[] values = {product.getId(), product.getName(), qty, s};
        this.addRow(values);

        return s; // Return the total price of the added product
    }

    /**
     * Override to make all cells in the table non-editable.
     *
     * @param row    The row index of the cell.
     * @param column The column index of the cell.
     * @return Always returns false to make cells non-editable.
     */
    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}
