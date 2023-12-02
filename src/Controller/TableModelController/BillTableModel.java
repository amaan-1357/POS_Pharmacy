package Controller.TableModelController;

import Controller.EntityControllers.Product;
import View.Panels.SalesPanel;

import javax.swing.table.DefaultTableModel;

public class BillTableModel extends DefaultTableModel {
    private SalesPanel frame;

    private Product product = new Product();
    public BillTableModel(SalesPanel sp){
        this.addColumn("PID");
        this.addColumn("Name");
        this.addColumn("Qty");
        this.addColumn("Price");
        frame = sp;
    }

    public Double addProduct(Integer PID, Integer qty){
        product = product.loadSingle(PID);
        Double s = (Double) ((product.getPrice()*qty));
        try {
            s = Double.parseDouble(String.format("%.2f", Double.parseDouble(s.toString())));
        } catch (NumberFormatException exception) {
            System.out.println("Invalid input format");
        }
        Object[] values = {product.getId(), product.getName(), qty, s};
        this.addRow(values);
        return s;
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }

}
