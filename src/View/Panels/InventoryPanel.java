package View.Panels;

import Controller.ActionListionerController.Admin.Inventory.AddCategoryListener;
import Controller.TableModelController.ProductTableModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class InventoryPanel extends JPanel {
    private DefaultTableModel productModel = new ProductTableModel();
    private JTable products = new JTable(productModel);
    private JScrollPane productScrollPane = new JScrollPane();
    private JButton update = new JButton("Update Product");
    private JButton addBatch = new JButton("Add New Batch");
    private JButton addProduct = new JButton("Add Product");
    private JButton deleteProduct = new JButton("Discontinue Product");
    private JButton addCategory = new JButton("Add Category");
    private SpringLayout sLayout = new SpringLayout();
    private String north = SpringLayout.NORTH;
    private String south = SpringLayout.SOUTH;
    private String west = SpringLayout.WEST;
    private String east = SpringLayout.EAST;
    public InventoryPanel(){
        setVisible(true);
        setSize(1680,1050);
        setLayout(sLayout);

        products.setGridColor(Color.BLACK);
        productScrollPane = new JScrollPane(products);
        add(productScrollPane);
        sLayout.putConstraint(north,productScrollPane,5,north,this);
        sLayout.putConstraint(west,productScrollPane,300,west,this);
        sLayout.putConstraint(east,productScrollPane,-5,east,this);
        sLayout.putConstraint(south,productScrollPane,-5,south,this);

        add(addBatch);
        sLayout.putConstraint(north,addBatch,50,north,this);
        sLayout.putConstraint(west,addBatch,100,west,this);

        add(addProduct);
        sLayout.putConstraint(north,addProduct,5,south,addBatch);
        sLayout.putConstraint(west,addProduct,100,west,this);

        add(addCategory);
        sLayout.putConstraint(north,addCategory,5,south,addProduct);
        sLayout.putConstraint(west,addCategory,100,west,this);

        add(deleteProduct);
        sLayout.putConstraint(north,deleteProduct,5,south,addCategory);
        sLayout.putConstraint(west,deleteProduct,100,west,this);

        add(update);
        sLayout.putConstraint(north,update,5,south,deleteProduct);
        sLayout.putConstraint(west,update,100,west,this);

        addCategory.addActionListener(new AddCategoryListener(this));
    }
}
