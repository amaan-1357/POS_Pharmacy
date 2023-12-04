package Controller.ActionListionerController.Admin;

import Controller.EntityControllers.*;
import View.AdminPortal;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * The SalesReportListener class represents an ActionListener for generating a sales report in the AdminPortal.
 */
public class SalesReportListener implements ActionListener {
    private final Sale s = new Sale();
    private final SaleItem si = new SaleItem();
    private Product p = new Product();
    private final Customer c = new Customer();
    private final AdminPortal frame;

    /**
     * Constructor for the SalesReportListener.
     *
     * @param adminPortal The AdminPortal associated with the listener.
     */
    public SalesReportListener(AdminPortal adminPortal) {
        frame = adminPortal;
    }

    /**
     * Invoked when the "Generate Sales Report" action is performed. Writes sales data to a text file.
     *
     * @param actionEvent The action event triggered by the button click.
     */
    @SuppressWarnings("CallToPrintStackTrace")
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String filePath = "sales_report.txt";
        ArrayList<Sale> salesList = s.loadAll();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, false))) {
            for (Sale sale : salesList) {
                ArrayList<SaleItem> saleItemsList = si.loadById(sale.getId());
                writer.write("Sale Id: " + sale.getId() + ": \n");
                writer.write("\t\tCustomer Name : " + c.loadName(sale.getCustomerId()));
                writer.write("\n\t\tDate: " + sale.getDate());
                writer.write("\n\t\tTotal Amount: " + sale.getAmount());
                writer.write("\n\t\tItems: ");
                for (int i = 0; i < saleItemsList.size(); i++) {
                    p = p.loadSingle(saleItemsList.get(i).getItemId());
                    writer.write("\n\t\t\tItem " + i + " Name: " + p.getName() +
                            " Quantity: " + saleItemsList.get(i).getQuantity() +
                            " Item Total: " + saleItemsList.get(i).getPrice());
                }
                writer.write("\n");
            }
            JOptionPane.showMessageDialog(frame, "Sales report has been stored in sales_report.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
