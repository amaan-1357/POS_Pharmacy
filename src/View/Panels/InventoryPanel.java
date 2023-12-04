package View.Panels;

import Controller.ActionListionerController.Admin.Inventory.*;
import Controller.KeyPressListenerController.Inventory.SearchBoxInventoryListener;
import Controller.TableModelController.ProductTableModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * The InventoryPanel class represents a JPanel that displays inventory information.
 */
public class InventoryPanel extends JPanel {
    private final DefaultTableModel productModel = new ProductTableModel();
    private final JTable products = new JTable(productModel);
    private final JTextField searchBox = new JTextField();

    /**
     * Constructor for the InventoryPanel.
     *
     * @param sp The SalesPanel where the InventoryPanel is displayed.
     */
    public InventoryPanel(JPanel sp) {
        setVisible(true);
        setSize(1680, 1050);
        SpringLayout sLayout = new SpringLayout();
        setLayout(sLayout);

        SalesPanel frame = (SalesPanel) sp;

        JLabel searchLabel = new JLabel("Search : ");
        add(searchLabel);
        String north = SpringLayout.NORTH;
        sLayout.putConstraint(north, searchLabel, 5, north, this);
        String west = SpringLayout.WEST;
        sLayout.putConstraint(west, searchLabel, 300, west, this);

        add(searchBox);
        String east = SpringLayout.EAST;
        sLayout.putConstraint(north, searchBox, 5, north, this);
        sLayout.putConstraint(west, searchBox, 5, east, searchLabel);
        sLayout.putConstraint(east, searchBox, -5, east, this);

        // Product table
        products.setGridColor(Color.BLACK);
        products.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane productScrollPane = new JScrollPane(products);
        add(productScrollPane);
        String south = SpringLayout.SOUTH;
        sLayout.putConstraint(north, productScrollPane, 5, south, searchBox);
        sLayout.putConstraint(west, productScrollPane, 300, west, this);
        sLayout.putConstraint(east, productScrollPane, -5, east, this);
        sLayout.putConstraint(south, productScrollPane, -5, south, this);

        JButton addBatch = new JButton("Add New Batch");
        add(addBatch);
        sLayout.putConstraint(north, addBatch, 50, north, this);
        sLayout.putConstraint(west, addBatch, 100, west, this);

        JButton addProduct = new JButton("Add Product");
        add(addProduct);
        sLayout.putConstraint(north, addProduct, 5, south, addBatch);
        sLayout.putConstraint(west, addProduct, 100, west, this);

        JButton addCategory = new JButton("Add Category");
        add(addCategory);
        sLayout.putConstraint(north, addCategory, 5, south, addProduct);
        sLayout.putConstraint(west, addCategory, 100, west, this);

        JButton discontinueProduct = new JButton("Discontinue Product");
        add(discontinueProduct);
        sLayout.putConstraint(north, discontinueProduct, 5, south, addCategory);
        sLayout.putConstraint(west, discontinueProduct, 100, west, this);

        JButton update = new JButton("Update Product");
        add(update);
        sLayout.putConstraint(north, update, 5, south, discontinueProduct);
        sLayout.putConstraint(west, update, 100, west, this);

        addCategory.addActionListener(new AddCategoryListener(this));
        searchBox.getDocument().addDocumentListener(new SearchBoxInventoryListener(this));
        addBatch.addActionListener(new AddBatchListener(this, frame));
        addProduct.addActionListener(new AddProductListener(this, frame));
        update.addActionListener(new UpdateProductListener(this, frame));
        discontinueProduct.addActionListener(new DiscontinueProductListener(this, frame));
    }

    /**
     * Gets the product model.
     *
     * @return The DefaultTableModel representing product information.
     */
    public DefaultTableModel getProductModel() {
        return productModel;
    }

    /**
     * Gets the products table.
     *
     * @return The JTable representing the products.
     */
    public JTable getProducts() {
        return products;
    }

    /**
     * Gets the search box.
     *
     * @return The JTextField representing the search box.
     */
    public JTextField getSearchBox() {
        return searchBox;
    }
}
