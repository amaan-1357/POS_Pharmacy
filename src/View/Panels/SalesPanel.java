package View.Panels;

import Controller.ActionListionerController.Admin.Sales.CancelButtonListener;
import Controller.ActionListionerController.Admin.Sales.ChargeButtonListener;
import Controller.KeyPressListenerController.DiscountBoxListener;
import Controller.KeyPressListenerController.SearchBoxListener;
import Controller.SelectionListeners.BillSelectionListener;
import Controller.SelectionListeners.ProductTableSelection;
import Controller.TableModelController.BillTableModel;
import Controller.TableModelController.ProductTableModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class SalesPanel extends JPanel {
    Double s_Total = 0.0;
    private DefaultTableModel productModel = new ProductTableModel();
    private JTable products = new JTable(productModel);
    private JLabel billLabel = new JLabel("Bill");
    private DefaultTableModel billModel = new BillTableModel(this);
    private JTable bill = new JTable(billModel);
    private JTextField searchBox = new JTextField();
    private JLabel searchLabel = new JLabel("Search : ");
    private JScrollPane productScrollPane = new JScrollPane();
    private JScrollPane billScrollPane = new JScrollPane();
    private JLabel taxLabel = new JLabel("Tax(%): ");
    private JLabel tax = new JLabel("5");
    private JLabel discountLabel = new JLabel("Discount(%): ");
    private JTextField discountField = new JTextField("");
    private JLabel subTotalLabel = new JLabel("Sub Total: ");
    private JLabel totalLabel = new JLabel("Total: ");
    private JLabel subTotal = new JLabel("0.0");
    private JLabel total = new JLabel("0.0");
    private JButton cancel = new JButton("Cancel");
    private JButton charge = new JButton("Charge");
    private SpringLayout sLayout = new SpringLayout();
    private String north = SpringLayout.NORTH;
    private String south = SpringLayout.SOUTH;
    private String west = SpringLayout.WEST;
    private String east = SpringLayout.EAST;
    public SalesPanel(){
        setVisible(true);
        setSize(1680,1050);
        setLayout(sLayout);

        add(billLabel);
        sLayout.putConstraint(north,billLabel,5,north,this);
        sLayout.putConstraint(west,billLabel,5,west,this);

        add(searchLabel);
        sLayout.putConstraint(north,searchLabel,5,north,this);
        sLayout.putConstraint(west,searchLabel,300,west,this);

        add(searchBox);
        sLayout.putConstraint(north,searchBox,5,north,this);
        sLayout.putConstraint(west,searchBox,5,east,searchLabel);
        sLayout.putConstraint(east,searchBox,-5,east,this);

        products.setGridColor(Color.BLACK);
        products.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        productScrollPane = new JScrollPane(products);
        add(productScrollPane);
        sLayout.putConstraint(north,productScrollPane,5,south,searchBox);
        sLayout.putConstraint(west,productScrollPane,300,west,this);
        sLayout.putConstraint(east,productScrollPane,-5,east,this);
        sLayout.putConstraint(south,productScrollPane,-5,south,this);

        add(taxLabel);
        sLayout.putConstraint(north,taxLabel,500,south,billLabel);
        sLayout.putConstraint(west,taxLabel,5,west,this);

        add(tax);
        sLayout.putConstraint(north,tax,500,south,billLabel);
        sLayout.putConstraint(east,tax,-5,west,productScrollPane);
        sLayout.putConstraint(west,tax,220,west,this);

        add(discountLabel);
        sLayout.putConstraint(north,discountLabel,5,south,taxLabel);
        sLayout.putConstraint(west,discountLabel,5,west,this);

        add(discountField);
        sLayout.putConstraint(north,discountField,5,south,taxLabel);
        sLayout.putConstraint(east,discountField,-5,west,productScrollPane);
        sLayout.putConstraint(west,discountField,220,west,this);

        add(subTotalLabel);
        sLayout.putConstraint(north,subTotalLabel,5,south,discountField);
        sLayout.putConstraint(west,subTotalLabel,5,west,this);

        add(subTotal);
        sLayout.putConstraint(north,subTotal,5,south,discountField);
        sLayout.putConstraint(east,subTotal,-5,west,productScrollPane);
        sLayout.putConstraint(west,subTotal,250,west,this);

        add(totalLabel);
        sLayout.putConstraint(north,totalLabel,5,south,subTotalLabel);
        sLayout.putConstraint(west,totalLabel,5,west,this);

        add(total);
        sLayout.putConstraint(north,total,5,south,subTotalLabel);
        sLayout.putConstraint(east,total,-5,west,productScrollPane);
        sLayout.putConstraint(west,total,250,west,this);

        add(charge);
        sLayout.putConstraint(north,charge,10,south,totalLabel);
        sLayout.putConstraint(east,charge,-5,west,productScrollPane);

        add(cancel);
        sLayout.putConstraint(north,cancel,10,south,totalLabel);
        sLayout.putConstraint(east,cancel,-5,west,charge);

        bill.setGridColor(Color.BLACK);
        bill.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        billScrollPane = new JScrollPane(bill);
        add(billScrollPane);
        sLayout.putConstraint(north,billScrollPane,5,south,billLabel);
        sLayout.putConstraint(west,billScrollPane,5,west,this);
        sLayout.putConstraint(east,billScrollPane,-5,west,productScrollPane);
        sLayout.putConstraint(south,billScrollPane,-5,north,taxLabel);

        searchBox.getDocument().addDocumentListener(new SearchBoxListener(this));
        discountField.getDocument().addDocumentListener(new DiscountBoxListener(this));

        products.getSelectionModel().addListSelectionListener(new ProductTableSelection(this));
        bill.getSelectionModel().addListSelectionListener(new BillSelectionListener(this));

        charge.addActionListener(new ChargeButtonListener(this));
        cancel.addActionListener(new CancelButtonListener(this));
    }

    public DefaultTableModel getProductModel() {
        return productModel;
    }

    public JTable getProducts() {
        return products;
    }

    public JLabel getBillLabel() {
        return billLabel;
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

    public JLabel getSearchLabel() {
        return searchLabel;
    }

    public JScrollPane getProductScrollPane() {
        return productScrollPane;
    }

    public JLabel getTaxLabel() {
        return taxLabel;
    }

    public JLabel getTax() {
        return tax;
    }

    public JLabel getDiscountLabel() {
        return discountLabel;
    }

    public JTextField getDiscountField() {
        return discountField;
    }

    public JLabel getSubTotalLabel() {
        return subTotalLabel;
    }

    public JLabel getTotalLabel() {
        return totalLabel;
    }

    public JLabel getSubTotal() {
        return subTotal;
    }

    public JLabel getTotal() {
        return total;
    }

    public JButton getCancel() {
        return cancel;
    }

    public JButton getCharge() {
        return charge;
    }

    public SpringLayout getsLayout() {
        return sLayout;
    }

    public Double getS_Total(){
        return s_Total;
    }

    public void setS_Total(Double s_Total) {
        this.s_Total = s_Total;
    }

    public void setSubTotal(JLabel subTotal) {
        this.subTotal = subTotal;
    }

    public void setTotal(JLabel total) {
        this.total = total;
    }

    public void setDiscountField(JTextField discountField) {
        this.discountField = discountField;
    }
}
