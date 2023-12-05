package Controller.TableModelController;

import Controller.DataFetchers.ProductDataFetchers;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.Hashtable;

/**
 * The ProductTableModel class extends DefaultTableModel and represents the table model for displaying product information.
 */
public class ProductTableModel extends DefaultTableModel {
    private final ProductDataFetchers pdf = new ProductDataFetchers(); // Data fetcher for product information

    /**
     * Constructor for ProductTableModel.
     * Initializes the columns and sets the initial product information.
     */
    public ProductTableModel() {
        this.addColumn("PID");       // Column for Product ID
        this.addColumn("Name");      // Column for Product Name
        this.addColumn("Price");     // Column for Product Price
        this.addColumn("Category");  // Column for Product Category
        this.addColumn("Supplier");  // Column for Product Supplier
        this.addColumn("Qty");       // Column for Product Quantity
        this.addColumn("Status");    // Column for Product Status
        setProductInfo();            // Set initial product information
    }

    /**
     * Sets the product information in the table model.
     * Retrieves product data from the data fetcher and populates the table.
     */
    public void setProductInfo() {
        ArrayList<Hashtable<String, String>> productInfo = pdf.getProductInfo();
        setRowCount(0); // Clear existing rows
        for (Hashtable<String, String> hs : productInfo) {
            // Create an Object array for each product and add it to the table model
            Object[] a = {hs.get("id"),
                    hs.get("name"),
                    hs.get("price"),
                    hs.get("category"),
                    hs.get("supplier"),
                    hs.get("quantity"),
                    hs.get("status")
            };
            this.addRow(a);
        }
    }

    /**
     * Sets the product information in the table model based on a search keyword.
     * Retrieves searched product data from the data fetcher and populates the table.
     *
     * @param keyword The keyword used for searching products.
     */
    public void setSearchedInfo(String keyword) {
        ArrayList<Hashtable<String, String>> productInfo = pdf.getSearchedProducts(keyword);
        setRowCount(0); // Clear existing rows
        for (Hashtable<String, String> hs : productInfo) {
            // Create an Object array for each searched product and add it to the table model
            Object[] a = {hs.get("id"),
                    hs.get("name"),
                    hs.get("price"),
                    hs.get("category"),
                    hs.get("supplier"),
                    hs.get("quantity"),
                    hs.get("status")
            };
            this.addRow(a);
        }
    }
    /**
     * Sets the product information in the table model of active products.
     * Retrieves searched product data from the data fetcher and populates the table.
     */
    public void setActiveInfo() {
        ArrayList<Hashtable<String, String>> productInfo = pdf.getActiveInfo();
        setRowCount(0); // Clear existing rows
        for (Hashtable<String, String> hs : productInfo) {
            // Create an Object array for each searched product and add it to the table model
            Object[] a = {hs.get("id"),
                    hs.get("name"),
                    hs.get("price"),
                    hs.get("category"),
                    hs.get("supplier"),
                    hs.get("quantity"),
                    hs.get("status")
            };
            this.addRow(a);
        }
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
