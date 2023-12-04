package Controller.ActionListionerController.Admin.Sales;

import Controller.TableModelController.BillTableModel;
import View.Panels.SalesPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * ActionListener for handling the cancellation of a bill in the SalesPanel.
 */
public class BillCancelButtonListener implements ActionListener {
    private final SalesPanel frame;
    private final BillTableModel model;

    /**
     * Constructor for BillCancelButtonListener.
     *
     * @param sp SalesPanel instance
     */
    public BillCancelButtonListener(SalesPanel sp) {
        this.frame = sp;
        this.model = (BillTableModel) frame.getBillModel();
    }

    /**
     * Invoked when an action occurs. Cancels the current bill by resetting the table and total amounts.
     *
     * @param actionEvent The event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        // Clear the table and reset total amounts to zero
        model.setRowCount(0);
        frame.getTotal().setText("0.0");
        frame.getSubTotal().setText("0.0");
        frame.getDiscountField().setText("0");
    }
}
