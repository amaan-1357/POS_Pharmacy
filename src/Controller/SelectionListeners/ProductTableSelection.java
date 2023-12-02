package Controller.SelectionListeners;

import Controller.TableModelController.BillTableModel;
import View.Panels.SalesPanel;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.text.DecimalFormat;

public class ProductTableSelection implements ListSelectionListener {
    private SalesPanel frame;
    private JTable table;
    private BillTableModel model;

    public ProductTableSelection(SalesPanel sp){
        frame = sp;
        table = frame.getProducts();
        model = (BillTableModel) frame.getBillModel();
    }
    @Override
    public void valueChanged(ListSelectionEvent e) {
        if(e.getValueIsAdjusting()){
            return;
        }
        int selectedRow = table.getSelectedRow();
        if(selectedRow == -1){
            return;
        }
        Integer quantity = -1;
        Integer qty = Integer.parseInt(table.getValueAt(selectedRow,5).toString());
        String status = table.getValueAt(selectedRow,6).toString();
        if(status.equals("discontinued")){
            JOptionPane.showMessageDialog(frame, "This product is discontinued.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            table.clearSelection();
            return;
        }
        if(qty == 0){
            JOptionPane.showMessageDialog(frame, "This product is out of stock.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            table.clearSelection();
            return;
        }
        Integer PID = Integer.parseInt(table.getValueAt(selectedRow,0).toString());
        Integer rows = model.getRowCount();
        for(int i=0;i<rows;i++){
            Integer p = Integer.parseInt(model.getValueAt(i,0).toString());
            if(p==PID){
                JOptionPane.showMessageDialog(frame, "Product already added to bill.",
                        "Error", JOptionPane.ERROR_MESSAGE);
                table.clearSelection();
                return;
            }
        }
        String q = JOptionPane.showInputDialog("Enter Quantity: ");
        if(q == null){
            JOptionPane.showMessageDialog(frame, "Quantity cannot be empty.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            table.clearSelection();
            return;
        }
        if(q.matches("\\d+")){
            quantity = Integer.parseInt(q);
        }
        else{
            JOptionPane.showMessageDialog(frame, "Invalid quantity.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            table.clearSelection();
            return;
        }
        if(quantity>qty) {
            JOptionPane.showMessageDialog(frame, "Not enough quantity in stock.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            table.clearSelection();
            return;
        }
        if(quantity==0){
            JOptionPane.showMessageDialog(frame, "Quantity cannot be zero.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            table.clearSelection();
            return;
        }
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        Double s = model.addProduct(PID,quantity);
        JOptionPane.showMessageDialog(frame, "Product added to bill.",
                "Success", JOptionPane.INFORMATION_MESSAGE);
        frame.setS_Total(frame.getS_Total()+s);
        frame.getSubTotal().setText(frame.getS_Total().toString());
        s = Double.parseDouble(frame.getSubTotal().getText());
        s=s+((s/100)*Integer.parseInt(frame.getTax().getText().toString()));
        try {
            s = Double.parseDouble(String.format("%.2f", Double.parseDouble(s.toString())));
        } catch (NumberFormatException exception) {
            System.out.println("Invalid input format");
        }
        frame.getSubTotal().setText(s.toString());
        if(frame.getDiscountField().getText().equals("")) {
            Double t = s;
            try {
                t = Double.parseDouble(String.format("%.0f", Double.parseDouble(t.toString())));
            } catch (NumberFormatException exception) {
                System.out.println("Invalid input format");
            }
            frame.getTotal().setText(t.toString());
        }
        else{
            Double t = s - ((s/100)*Double.parseDouble(frame.getDiscountField().getText()));
            try {
                t = Double.parseDouble(String.format("%.0f", Double.parseDouble(t.toString())));
            } catch (NumberFormatException exception) {
                System.out.println("Invalid input format");
            }
            frame.getTotal().setText(t.toString());
        }
        table.clearSelection();
    }
}
