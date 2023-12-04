package View.Panels;

import Controller.ActionListionerController.Admin.Sales.BillCancelButtonListener;
import Controller.ActionListionerController.Admin.Sales.BillChargeButtonListener;
import Controller.KeyPressListenerController.Sales.DiscountBoxListener;
import Controller.KeyPressListenerController.Sales.SearchBoxSalesListener;
import Controller.SelectionListeners.BillSelectionListener;
import Controller.SelectionListeners.ProductTableSelection;
import Controller.TableModelController.BillTableModel;
import Controller.TableModelController.ProductTableModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * The SalesPanel class represents the panel for handling sales operations in the Sales Representative Portal.
 */
public class SalesPanel extends JPanel {
    // Variables for managing sales data
    Double s_Total = 0.0;
    private final DefaultTableModel productModel = new ProductTableModel();
    private final JTable products = new JTable(productModel);
    private final DefaultTableModel billModel = new BillTableModel();
    private final JTable
            bill = new JTable(billModel);

    // Components for search functionality
    private final JTextField searchBox = new JTextField();

    private final JLabel tax = new JLabel("5");
    private final JTextField discountField = new JTextField("");
    private final JLabel subTotal = new JLabel("0.0");
    private JLabel total = new JLabel("0.0");

    /**
     * Constructs the SalesPanel.
     *
     * @param UID The user ID of the sales representative.
     */
    public SalesPanel(Integer UID) {
        setVisible(true);
        setSize(1680, 1050);
        // Layout manager
        SpringLayout sLayout = new SpringLayout();
        setLayout(sLayout);


        // Add components to the panel

        // Bill label and search components
        JLabel billLabel = new JLabel("Bill");
        add(billLabel);
        String north = SpringLayout.NORTH;
        sLayout.putConstraint(north, billLabel, 5, north, this);
        String west = SpringLayout.WEST;
        sLayout.putConstraint(west, billLabel, 5, west, this);

        JLabel searchLabel = new JLabel("Search : ");
        add(searchLabel);
        sLayout.putConstraint(north, searchLabel, 5, north, this);
        sLayout.putConstraint(west, searchLabel, 300, west, this);

        add(searchBox);
        sLayout.putConstraint(north, searchBox, 5, north, this);
        String east = SpringLayout.EAST;
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

        // Tax, discount, and total
        // Components for handling taxes, discounts, and totals
        JLabel taxLabel = new JLabel("Tax(%): ");
        add(taxLabel);
        sLayout.putConstraint(north, taxLabel, 500, south, billLabel);
        sLayout.putConstraint(west, taxLabel, 5, west, this);

        add(tax);
        sLayout.putConstraint(north, tax, 500, south, billLabel);
        sLayout.putConstraint(east, tax, -5, west, productScrollPane);
        sLayout.putConstraint(west, tax, 220, west, this);

        JLabel discountLabel = new JLabel("Discount(%): ");
        add(discountLabel);
        sLayout.putConstraint(north, discountLabel, 5, south, taxLabel);
        sLayout.putConstraint(west, discountLabel, 5, west, this);

        add(discountField);
        sLayout.putConstraint(north, discountField, 5, south, taxLabel);
        sLayout.putConstraint(east, discountField, -5, west, productScrollPane);
        sLayout.putConstraint(west, discountField, 220, west, this);

        JLabel subTotalLabel = new JLabel("Sub Total: ");
        add(subTotalLabel);
        sLayout.putConstraint(north, subTotalLabel, 5, south, discountField);
        sLayout.putConstraint(west, subTotalLabel, 5, west, this);

        add(subTotal);
        sLayout.putConstraint(north, subTotal, 5, south, discountField);
        sLayout.putConstraint(east, subTotal, -5, west, productScrollPane);
        sLayout.putConstraint(west, subTotal, 250, west, this);

        JLabel totalLabel = new JLabel("Total: ");
        add(totalLabel);
        sLayout.putConstraint(north, totalLabel, 5, south, subTotalLabel);
        sLayout.putConstraint(west, totalLabel, 5, west, this);

        add(total);
        sLayout.putConstraint(north, total, 5, south, subTotalLabel);
        sLayout.putConstraint(east, total, -5, west, productScrollPane);
        sLayout.putConstraint(west, total, 250, west, this);

        // Charge and cancel buttons
        JButton charge = new JButton("Charge");
        add(charge);
        sLayout.putConstraint(north, charge, 10, south, totalLabel);
        sLayout.putConstraint(east, charge, -5, west, productScrollPane);

        // Buttons for canceling and charging a sale
        JButton cancel = new JButton("Cancel");
        add(cancel);
        sLayout.putConstraint(north, cancel, 10, south, totalLabel);
        sLayout.putConstraint(east, cancel, -5, west, charge);

        // Bill table
        bill.setGridColor(Color.BLACK);
        bill.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane billScrollPane = new JScrollPane(bill);
        add(billScrollPane);
        sLayout.putConstraint(north, billScrollPane, 5, south, billLabel);
        sLayout.putConstraint(west, billScrollPane, 5, west, this);
        sLayout.putConstraint(east, billScrollPane, -5, west, productScrollPane);
        sLayout.putConstraint(south, billScrollPane, -5, north, taxLabel);

        // Add listeners to components
        searchBox.getDocument().addDocumentListener(new SearchBoxSalesListener(this));
        discountField.getDocument().addDocumentListener(new DiscountBoxListener(this));

        products.getSelectionModel().addListSelectionListener(new ProductTableSelection(this));
        bill.getSelectionModel().addListSelectionListener(new BillSelectionListener(this));

        charge.addActionListener(new BillChargeButtonListener(this, UID));
        cancel.addActionListener(new BillCancelButtonListener(this));
    }

    // Getter and setter methods...

    public DefaultTableModel getProductModel() {
        return productModel;
    }

    public JTable getProducts() {
        return products;
    }

    public JTable getBill() {
        return bill;
    }

    public DefaultTableModel getBillModel() {
        return billModel;
    }

    public JTextField getSearchBox() {
        return searchBox;
    }

    public JLabel getTax() {
        return tax;
    }

    public JTextField getDiscountField() {
        return discountField;
    }

    public JLabel getSubTotal() {
        return subTotal;
    }

    public JLabel getTotal() {
        return total;
    }

    public Double getS_Total() {
        return s_Total;
    }

    public void setS_Total(Double s_Total) {
        this.s_Total = s_Total;
    }

    public void setTotal(JLabel total) {
        this.total = total;
    }
}
