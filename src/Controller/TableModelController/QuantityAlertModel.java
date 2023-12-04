package Controller.TableModelController;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.Hashtable;

/**
 * The QuantityAlertModel class extends DefaultTableModel and represents the table model
 * for displaying products in the quantity alert scenario.
 */
public class QuantityAlertModel extends DefaultTableModel {

    /**
     * Constructor for QuantityAlertModel.
     *
     * @param data ArrayList of Hashtables containing product information.
     */
    public QuantityAlertModel(ArrayList<Hashtable<String, String>> data) {
        this.addColumn("Name");         // Column for Product Name
        this.addColumn("Supplier");     // Column for Supplier
        this.addColumn("Email");        // Column for Supplier Email
        this.addColumn("Phone Number"); // Column for Supplier Phone Number
        setRowCount(0);

        // Iterate through the data and add each product to the table model
        for (Hashtable<String, String> hs : data) {
            // Create an Object array for each product and add it to the table model
            Object[] a = {
                    hs.get("name"),
                    hs.get("supplier"),
                    hs.get("email"),
                    hs.get("number")
            };
            this.addRow(a);
        }
    }
}
