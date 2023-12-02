package Controller.SelectionListeners;

import Controller.TableModelController.BillTableModel;
import View.Panels.SalesPanel;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class BillSelectionListener implements ListSelectionListener {
    private SalesPanel frame;
    private JTable table;
    private BillTableModel model;

    public BillSelectionListener(SalesPanel frame) {
        this.frame = frame;
        this.model = (BillTableModel) frame.getBillModel();
        this.table = frame.getBill();
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
        if(JOptionPane.showConfirmDialog(frame,"Do You want to remove this item from the bill.",
                "Confirmation",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
            Double price = Double.parseDouble(model.getValueAt(selectedRow,3).toString());
            price = price + ((price/100)*Integer.parseInt(frame.getTax().getText().toString()));
            try {
                price = Double.parseDouble(String.format("%.0f", Double.parseDouble(price.toString())));
            } catch (NumberFormatException exception) {
                System.out.println("Invalid input format");
            }
            Double s = Double.parseDouble(frame.getSubTotal().getText().toString());
            s=s-price;
            try {
                s = Double.parseDouble(String.format("%.0f", Double.parseDouble(s.toString())));
            } catch (NumberFormatException exception) {
                System.out.println("Invalid input format");
            }
            if(frame.getDiscountField().getText().equals(""))
                frame.getTotal().setText(s.toString());
            else{
                Double t = s - ((s/100)*Double.parseDouble(frame.getDiscountField().getText()));
                try {
                    t = Double.parseDouble(String.format("%.0f", Double.parseDouble(t.toString())));
                } catch (NumberFormatException exception) {
                    System.out.println("Invalid input format");
                }
                frame.getTotal().setText(t.toString());
            }
            frame.getSubTotal().setText(s.toString());
            frame.setS_Total(s);
            model.removeRow(selectedRow);
            if(model.getRowCount()==0){
                frame.getSubTotal().setText("0.0");
                frame.getTotal().setText("0.0");
                frame.setS_Total(0.0);
            }
        }
        else
            table.clearSelection();
    }
}
