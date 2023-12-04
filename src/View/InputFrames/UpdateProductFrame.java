package View.InputFrames;

import Controller.ActionListionerController.Admin.Inventory.UpdateProductFrame.AddCategoryListener;
import Controller.ActionListionerController.Admin.Inventory.UpdateProductFrame.AddSupplierListener;
import Controller.ActionListionerController.Admin.Inventory.UpdateProductFrame.ConfirmButtonListener;
import Controller.ActionListionerController.CancelButtonListener;
import Controller.EntityControllers.Product;
import Controller.EntityControllers.ProductCategory;
import Controller.EntityControllers.Supplier;
import View.Panels.InventoryPanel;
import View.Panels.SalesPanel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

@SuppressWarnings("rawtypes")
public class UpdateProductFrame extends JFrame {
    private final JTextField nName = new JTextField();
    private final JTextField nPrice = new JTextField(4);
    @SuppressWarnings("rawtypes")
    private final JComboBox nCategory = new JComboBox();
    @SuppressWarnings("rawtypes")
    private final JComboBox nSupplier = new JComboBox();
    @SuppressWarnings("FieldCanBeLocal")
    private ProductCategory pc = new ProductCategory();
    @SuppressWarnings("FieldCanBeLocal")
    private Supplier s = new Supplier();

    @SuppressWarnings("unchecked")
    public UpdateProductFrame(InventoryPanel ip, SalesPanel sp, Product p) {
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JTextField oName = new JTextField(p.getName());
        JTextField oPrice = new JTextField(p.getPrice().toString());
        pc = pc.loadSingle(p.getCategoryID());
        JTextField oCategory = new JTextField(pc.getName());
        s = s.loadSingle(p.getSupplierID());
        JTextField oSupplier = new JTextField(s.getName());

        ArrayList<Supplier> sup = s.getSuppliers();
        ArrayList<ProductCategory> cat = pc.getProductCategories();

        //noinspection unchecked
        nSupplier.addItem("");
        for (Supplier supplier : sup) {
            nSupplier.addItem(supplier.getName());
        }
        nCategory.addItem("");
        for (ProductCategory productCategory : cat) {
            nCategory.addItem(productCategory.getName());
        }

        Container contentPane = getContentPane();
        oName.setEditable(false);
        oPrice.setEditable(false);
        oCategory.setEditable(false);
        oSupplier.setEditable(false);

        JButton addCategory = new JButton("Add Category");
        addCategory.addActionListener(new AddCategoryListener(this));
        JButton addSupplier = new JButton("Add Supplier");
        addSupplier.addActionListener(new AddSupplierListener(this));
        JButton cancel = new JButton("Cancel");
        cancel.addActionListener(new CancelButtonListener(this));
        JButton confirm = new JButton("Confirm");
        confirm.addActionListener(new ConfirmButtonListener(this, ip, sp, p));

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        JLabel oldLabel = new JLabel("Old Information");
        JLabel onLabel = new JLabel("Name");
        JLabel newLabel = new JLabel("New Information (Only Fill Information that has to be updated!!!");
        JLabel nnLabel = new JLabel("Name");
        JLabel opLabel = new JLabel("Price");
        JLabel npLabel = new JLabel("Price");
        JLabel ocLabel = new JLabel("Category");
        JLabel ncLabel = new JLabel("Category");
        JLabel osLabel = new JLabel("Supplier");
        JLabel nsLabel = new JLabel("Supplier");
        contentPaneLayout.setHorizontalGroup(
                contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(contentPaneLayout.createParallelGroup()
                                        .addComponent(oldLabel)
                                        .addComponent(newLabel)
                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                                .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                                                .addComponent(onLabel)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(oName))
                                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                                                .addComponent(nnLabel)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(nName, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)))
                                                .addGap(18, 18, 18)
                                                .addGroup(contentPaneLayout.createParallelGroup()
                                                        .addComponent(npLabel, GroupLayout.Alignment.TRAILING)
                                                        .addComponent(opLabel, GroupLayout.Alignment.TRAILING))))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(contentPaneLayout.createParallelGroup()
                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                                .addComponent(cancel)
                                                .addGap(81, 81, 81)
                                                .addComponent(confirm))
                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                                .addGroup(contentPaneLayout.createParallelGroup()
                                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                                                .addComponent(oPrice, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(ocLabel)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(oCategory, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                                                .addComponent(nPrice, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(ncLabel)
                                                                .addGap(18, 18, 18)
                                                                .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                                        .addComponent(addCategory, GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
                                                                        .addComponent(nCategory, GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE))))
                                                .addGap(18, 18, 18)
                                                .addGroup(contentPaneLayout.createParallelGroup()
                                                        .addComponent(osLabel)
                                                        .addComponent(nsLabel))
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(contentPaneLayout.createParallelGroup()
                                                        .addComponent(oSupplier, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(nSupplier, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(addSupplier))))
                                .addContainerGap(122, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
                contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(oldLabel)
                                .addGap(26, 26, 26)
                                .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(onLabel)
                                        .addComponent(opLabel)
                                        .addComponent(oPrice, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(ocLabel)
                                        .addComponent(oCategory, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(oName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(osLabel)
                                        .addComponent(oSupplier, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(39, 39, 39)
                                .addComponent(newLabel)
                                .addGap(30, 30, 30)
                                .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(nnLabel)
                                        .addComponent(nName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(npLabel)
                                        .addComponent(nPrice, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(ncLabel)
                                        .addComponent(nCategory, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(nsLabel)
                                        .addComponent(nSupplier, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(addCategory)
                                        .addComponent(addSupplier))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                                .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(cancel)
                                        .addComponent(confirm))
                                .addGap(29, 29, 29))
        );
        pack();
    }

    @SuppressWarnings("rawtypes")
    public JComboBox getnCategory() {
        return nCategory;
    }

    public
    JComboBox getnSupplier() {
        return nSupplier;
    }

    public JTextField getnName() {
        return nName;
    }

    public JTextField getnPrice() {
        return nPrice;
    }

}
