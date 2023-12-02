package Controller.KeyPressListenerController;

import View.Panels.SalesPanel;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class DiscountBoxListener implements DocumentListener {

    private SalesPanel frame;

    public DiscountBoxListener(SalesPanel frame) {
        this.frame = frame;
    }

    @Override
    public void insertUpdate(DocumentEvent documentEvent) {
        String q = frame.getDiscountField().getText();
        if(!q.matches("\\d*")){
            JOptionPane.showMessageDialog(frame, "Invalid value",
                    "Error", JOptionPane.ERROR_MESSAGE);
            setText(frame.getDiscountField());
        }
        else if(q.isEmpty()) {
            setText(frame.getDiscountField());
            Double s = Double.parseDouble(frame.getSubTotal().getText());
            try {
                s = Double.parseDouble(String.format("%.0f", Double.parseDouble(s.toString())));
            } catch (NumberFormatException exception) {
                System.out.println("Invalid input format");
            }
            frame.getTotal().setText(s.toString());
        }
        else if(Integer.parseInt(q) >100){
            JOptionPane.showMessageDialog(frame, "Discount cannot be greater than 100",
                    "Error", JOptionPane.ERROR_MESSAGE);
            setText(frame.getDiscountField());
        }
        else{
            Double s = Double.parseDouble(frame.getSubTotal().getText());
            Double t = s - ((s/100)*Double.parseDouble(frame.getDiscountField().getText()));
            try {
                t = Double.parseDouble(String.format("%.0f", Double.parseDouble(t.toString())));
            } catch (NumberFormatException exception) {
                System.out.println("Invalid input format");
            }
            frame.getTotal().setText(t.toString());
        }
    }

    @Override
    public void removeUpdate(DocumentEvent documentEvent) {
        String q = frame.getDiscountField().getText();
        if(!q.matches("\\d*")){
            JOptionPane.showMessageDialog(frame, "Invalid value",
                    "Error", JOptionPane.ERROR_MESSAGE);
            setText(frame.getDiscountField());
        }
        else if(q.isEmpty()) {
            setText(frame.getDiscountField());
            Double s = Double.parseDouble(frame.getSubTotal().getText());
            try {
                s = Double.parseDouble(String.format("%.0f", Double.parseDouble(s.toString())));
            } catch (NumberFormatException exception) {
                System.out.println("Invalid input format");
            }
            frame.getTotal().setText(s.toString());
        }
        else if(Integer.parseInt(q) >100){
            JOptionPane.showMessageDialog(frame, "Discount cannot be greater than 100",
                    "Error", JOptionPane.ERROR_MESSAGE);
            setText(frame.getDiscountField());
        }
        else{
                Double s = Double.parseDouble(frame.getSubTotal().getText());
                Double t = s - ((s/100)*Double.parseDouble(frame.getDiscountField().getText()));
                try {
                    t = Double.parseDouble(String.format("%.0f", Double.parseDouble(t.toString())));
                } catch (NumberFormatException exception) {
                    System.out.println("Invalid input format");
                }
                frame.getTotal().setText(t.toString());
            }
    }

    @Override
    public void changedUpdate(DocumentEvent documentEvent) {
        String q = frame.getDiscountField().getText();
        if(!q.matches("\\d*")){
            JOptionPane.showMessageDialog(frame, "Invalid value",
                    "Error", JOptionPane.ERROR_MESSAGE);
            setText(frame.getDiscountField());
        }
        else if(q.isEmpty()){
            setText(frame.getDiscountField());
            Double s = Double.parseDouble(frame.getSubTotal().getText());
            try {
                s = Double.parseDouble(String.format("%.0f", Double.parseDouble(s.toString())));
            } catch (NumberFormatException exception) {
                System.out.println("Invalid input format");
            }
            frame.getTotal().setText(s.toString());
        }
        else if(Integer.parseInt(q) >100){
            JOptionPane.showMessageDialog(frame, "Discount cannot be greater than 100",
                    "Error", JOptionPane.ERROR_MESSAGE);
            setText(frame.getDiscountField());
        }
        else{
            Double s = Double.parseDouble(frame.getSubTotal().getText());
            Double t = s - ((s/100)*Double.parseDouble(frame.getDiscountField().getText()));
            try {
                t = Double.parseDouble(String.format("%.0f", Double.parseDouble(t.toString())));
            } catch (NumberFormatException exception) {
                System.out.println("Invalid input format");
            }
            frame.getTotal().setText(t.toString());
        }
    }
    public void setText(JTextField t){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // This will be executed outside the event-handling code
                t.setText("");
            }
        });
    }
}
