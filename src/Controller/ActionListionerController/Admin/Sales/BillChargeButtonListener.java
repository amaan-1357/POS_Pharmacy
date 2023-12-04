package Controller.ActionListionerController.Admin.Sales;

import Controller.BillPrintable;
import Controller.EntityControllers.*;
import Controller.TableModelController.BillTableModel;
import Controller.TableModelController.ProductTableModel;
import Controller.TableModelController.QuantityAlertModel;
import View.Panels.SalesPanel;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.FileOutputStream;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BillChargeButtonListener implements ActionListener {
    private final SalesPanel frame;
    private final BillTableModel model;
    private final ProductTableModel pModel;
    private Product p = new Product();
    private final Sale s = new Sale();
    private final SaleItem si = new SaleItem();
    private Batch b = new Batch();
    private Supplier sup = new Supplier();
    private Customer c = new Customer();
    private final User u = new User();
    private Double bHeight=0.0;
    private final Integer UID;

    // Constructor for BillChargeButtonListener
    public BillChargeButtonListener(SalesPanel sp, Integer UID) {
        this.frame = sp;
        this.model = (BillTableModel) frame.getBillModel();
        this.pModel = (ProductTableModel) frame.getProductModel();
        this.UID = UID;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        // Check if the bill is empty
        if(model.getRowCount() == 0) {
            JOptionPane.showMessageDialog(frame, "Please add a product to the bill");
            return;
        }

        Integer CID = null;
        Integer PID;
        Integer SID;
        String name;

        // Ask whether it's a new customer
        int selection = JOptionPane.showConfirmDialog(frame,"Is it a new customer?","Customer",
                JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);

        // If yes, gather information for a new customer
        if(selection == JOptionPane.YES_OPTION){
            name = JOptionPane.showInputDialog(frame,"Enter New Customer Name");

            // Ask whether email is provided for the new customer
            int ns = JOptionPane.showConfirmDialog(frame,"Is email provided?","Email",JOptionPane.YES_NO_OPTION);

            // If yes, validate and insert email
            if(ns == JOptionPane.YES_OPTION){
                while(true) {
                    String email = JOptionPane.showInputDialog(frame, "Enter Customer Email");
                    String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
                    Pattern pattern = Pattern.compile(emailRegex);
                    Matcher matcher = pattern.matcher(email);
                    if(!matcher.matches()){
                        JOptionPane.showMessageDialog(frame, "Please enter a valid email");
                        continue;
                    }
                    c = new Customer(name, email);
                    c.insert();
                    break;
                }
            }
            // If no email is provided, insert without email
            else{
                c = new Customer(name);
                c.insertWithoutEmail();
            }
            CID = c.getMaxCID();
        }
        // If not a new customer, gather information for an existing customer
        else{
            name = JOptionPane.showInputDialog(frame,"Enter Customer Name");

            // Check if the customer exists
            if(c.getCID(name) == 0){
                // If the customer doesn't exist, prompt to add a new customer or try again
                while(c.getCID(name)==0) {
                    int n = JOptionPane.showConfirmDialog(frame, "Customer doesn't exist! Do you want to add a new customer?", "Error", JOptionPane.YES_NO_OPTION);
                    if (n == JOptionPane.YES_OPTION) {
                        name = JOptionPane.showInputDialog(frame,"Enter New Customer Name");
                        int ns = JOptionPane.showConfirmDialog(frame,"Is email provided?","Email",JOptionPane.YES_NO_OPTION);
                        if(ns == JOptionPane.YES_OPTION){
                            String email = JOptionPane.showInputDialog(frame,"Enter Customer Email");
                            c = new Customer(name,email);
                            c.insert();
                        }
                        else{
                            c = new Customer(name);
                            c.insertWithoutEmail();
                        }
                        CID = c.getMaxCID();
                    }
                    else{
                        name = JOptionPane.showInputDialog(frame, "Enter Customer Name");
                    }
                }
            }
            // If the customer exists, retrieve the customer ID
            else{
                CID = c.getCID(name);
            }
        }

        // Get the current date
        Date d = Date.valueOf(LocalDate.now());
        // Insert a new sale
        s.insert(new Sale(CID, d, Double.parseDouble(frame.getTotal().getText())));
        // Get the sale ID
        SID = s.getSID();

        // Get the number of rows in the bill
        int rows = model.getRowCount();
        ArrayList<String> names = new ArrayList<>();
        ArrayList<String> quantities = new ArrayList<>();
        ArrayList<String> prices = new ArrayList<>();
        ArrayList<Hashtable<String,String>> data = new ArrayList<>();

        // Process each row in the bill
        for(int i = 0; i < rows; i++){
            // Get product information
            PID = Integer.parseInt(model.getValueAt(i,0).toString());
            names.add(model.getValueAt(i,1).toString());
            quantities.add(model.getValueAt(i,2).toString());
            prices.add(model.getValueAt(i,3).toString());
            p = p.loadSingle(PID);
            Integer quantity = Integer.parseInt(model.getValueAt(i,2).toString());
            Double price = Double.parseDouble(model.getValueAt(i,3).toString());

            // Update product quantity
            int q = p.getQuantity()-quantity;
            p.setQuantity(q);

            // Check for quantity alerts
            if(q<p.getLimit()){
                Hashtable<String,String> hs = new Hashtable<>();
                hs.put("name",p.getName());
                sup = sup.loadSingle(p.getSupplierID());
                hs.put("supplier",sup.getName());
                if(sup.getEmail() != null){
                    hs.put("email",sup.getEmail());
                }else{
                    hs.put("email","");
                }
                hs.put("number",sup.getPhoneNo());
                data.add(hs);
            }

            // Get the batch for the product
            b = b.getByPID(PID);

            // Process the batch quantity
            while(quantity!=0) {
                if (b.getQuantity() < quantity) {
                    quantity = quantity - b.getQuantity();
                    b.deleteById(b.getId());
                    b = b.getByPID(PID);
                } else {
                    q = b.getQuantity() - quantity;
                    if(q>0)
                        b.updateQtyById(q,b.getId());
                    else
                        b.deleteById(b.getId());
                    break;
                }
            }

            // Update product quantity in the database
            p.updateQty(PID,p.getQuantity());
            pModel.setProductInfo();

            // Insert sale item
            si.insert(new SaleItem(SID, PID, quantity, price));
        }

        // Set the bill height for printing
        bHeight = (double) names.size();
        PrinterJob pj = PrinterJob.getPrinterJob();
        // Show print dialog
        if (pj.printDialog()) {
            // Set printable content
            pj.setPrintable(new BillPrintable(names, prices, quantities, frame.getSubTotal().getText(), frame.getTotal().getText(), SID.toString(), frame, u.getName(UID)), getPageFormat(pj));
            try {
                // Print the bill
                pj.print();
            } catch (PrinterException ex) {
                //noinspection CallToPrintStackTrace
                ex.printStackTrace();
            }
        } else {
            // If no printer is connected, prompt the user to save the print as a PDF
            createPDF(names, prices, quantities, frame.getSubTotal().getText(), frame.getTotal().getText(), SID.toString(), frame, u.getName(UID));
        }

        // Show quantity alerts
        if(!data.isEmpty()){
            showAlerts(data);
        }

        // Clear the bill and reset total amounts
        model.setRowCount(0);
        frame.getSubTotal().setText("0.0");
        frame.getTotal().setText("0.0");
        frame.getDiscountField().setText("0");
    }

    // Show quantity alerts in a dialog
    private void showAlerts(ArrayList<Hashtable<String,String>> data) {
        Frame parentFrame = (Frame) SwingUtilities.getWindowAncestor(frame);
        JDialog dialog = new JDialog(parentFrame, "Quantity Alert", true);

        // Create a panel to hold the table and buttons
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        dialog.setLayout(new BorderLayout());

        // Create a table model and table
        JTable table = new JTable(new QuantityAlertModel(data));

        // Add a scroll pane with the table to the panel
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane);

        // Create an "Ok" button to close the dialog
        JButton button = new JButton("Ok");
        button.addActionListener(actionEvent -> dialog.dispose());

        // Add the panel to the dialog
        dialog.add(panel, BorderLayout.CENTER);

        // Add the "Ok" button to the bottom of the dialog
        dialog.add(button, BorderLayout.SOUTH);
        dialog.setSize(400, 300);
        dialog.setLocationRelativeTo(frame);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setVisible(true);
    }

    // Get page format for printing
    public PageFormat getPageFormat(PrinterJob pj)
    {
        PageFormat pf = pj.defaultPage();
        Paper paper = pf.getPaper();

        // Calculate paper size based on bill height
        double bodyHeight = bHeight;
        double headerHeight = 5.0;
        double footerHeight = 5.0;
        double width = cm_to_pp(8);
        double height = cm_to_pp(headerHeight+bodyHeight+footerHeight);
        paper.setSize(width, height);
        paper.setImageableArea(0,10,width,height - cm_to_pp(1));

        // Set orientation and paper
        pf.setOrientation(PageFormat.PORTRAIT);
        pf.setPaper(paper);

        return pf;
    }

    // Create a PDF of the bill
    private void createPDF(ArrayList<String> names, ArrayList<String> prices, ArrayList<String> quantities,
                           String subTotal, String totalAmount, String billNumber, SalesPanel frame, String userName) {
        try {
            // Create a Document
            Document document = new Document();

            // Provide the path where you want to save the PDF
            String pdfPath = "/Users/amaan/Course work/SCD/POS_Pharmacy/Bills/bill"+billNumber+".pdf";

            // Create a PdfWriter instance to write to the document
            PdfWriter.getInstance(document, new FileOutputStream(pdfPath));

            // Open the document for writing
            document.open();

            // Add content to the PDF
            addContentToPDF(document, names, prices, quantities, subTotal, totalAmount, billNumber, userName);

            // Close the document
            document.close();

            // Display a message indicating successful PDF creation
            JOptionPane.showMessageDialog(frame, "PDF saved successfully: " + pdfPath);
        } catch (Exception e) {
            //noinspection CallToPrintStackTrace
            e.printStackTrace();
        }
    }

    // Add content to the PDF
    @SuppressWarnings("CallToPrintStackTrace")
    private void addContentToPDF(Document document, ArrayList<String> names, ArrayList<String> prices,
                                 ArrayList<String> quantities, String subTotal, String totalAmount,
                                 String billNumber, String userName) {
        try {
            // Add content similar to the print method
            document.add(new Paragraph("Bill Number: " + billNumber));
            document.add(new Paragraph("Sales Person: " + userName));
            document.add(new Paragraph("AA Pharmacy"));
            document.add(new Paragraph("Fast"));
            document.add(new Paragraph("Lahore"));
            document.add(new Paragraph("+923218450365"));
            document.add(new Paragraph("-------------------------------------"));

            // Add items to the PDF
            for (int s = 0; s < names.size(); s++) {
                document.add(new Paragraph(names.get(s)));
                document.add(new Paragraph("      " + quantities.get(s) + "  " + prices.get(s)));
            }

            document.add(new Paragraph("-------------------------------------"));
            document.add(new Paragraph("Sub Total: " + subTotal));
            document.add(new Paragraph("Total amount: " + totalAmount));
            document.add(new Paragraph("-------------------------------------"));
            document.add(new Paragraph("*************************************"));
            document.add(new Paragraph("THANK YOU COME AGAIN"));
            document.add(new Paragraph("*************************************"));
            document.add(new Paragraph("SOFTWARE BY:AA Developers"));
            document.add(new Paragraph("CONTACT: amaanshokat2468@gmail.com"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Convert centimeters to pixels
    protected static double cm_to_pp(double cm)
    {
        return toPPI(cm * 0.393600787);
    }

    // Convert inches to pixels
    protected static double toPPI(double inch)
    {
        return inch * 72d;
    }
}