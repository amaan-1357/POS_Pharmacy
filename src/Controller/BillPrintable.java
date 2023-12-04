package Controller;

import View.Panels.SalesPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.util.ArrayList;

/**
 * The BillPrintable class implements the Printable interface and defines the print format
 * for generating a bill to be printed.
 */
public class BillPrintable implements Printable {
    private final ArrayList<String> names;
    private final ArrayList<String> prices;
    private final ArrayList<String> quantities;
    private final String subTotal;
    private final String totalAmount;
    private final SalesPanel frame;
    private final String number;
    private final String userName;

    /**
     * Constructor for BillPrintable.
     *
     * @param names        List of item names in the bill.
     * @param prices       List of item prices in the bill.
     * @param quantities   List of item quantities in the bill.
     * @param subTotal     Subtotal of the bill.
     * @param totalAmount  Total amount of the bill.
     * @param billNumber   Bill number.
     * @param sp           SalesPanel reference.
     * @param userName     Name of the salesperson.
     */
    public BillPrintable(ArrayList<String> names, ArrayList<String> prices, ArrayList<String> quantities,
                         String subTotal, String totalAmount, String billNumber, SalesPanel sp, String userName) {
        this.names = names;
        this.prices = prices;
        this.quantities = quantities;
        this.subTotal = subTotal;
        this.totalAmount = totalAmount;
        this.frame = sp;
        this.number = billNumber;
        this.userName = userName;
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) {

        int r = names.size();
        ImageIcon icon = new ImageIcon("/Users/amaan/Course work/SCD/POS_Pharmacy/logo.jpeg");
        int result = NO_SUCH_PAGE;
        if (pageIndex == 0) {

            Graphics2D g2d = (Graphics2D) graphics;
            g2d.translate((int) pageFormat.getImageableX(), (int) pageFormat.getImageableY());

            try {
                int y = 20;
                int yShift = 10;
                int headerRectHeight = 15;

                g2d.setFont(new Font("Monospaced", Font.PLAIN, 9));
                g2d.drawImage(icon.getImage(), 50, 20, 90, 30, frame);
                y += yShift + 30;
                g2d.drawString("-------------------------------------", 12, y);
                y += yShift;
                g2d.drawString("       Bill no: " + number + "       ", 12, y);
                y += yShift;
                g2d.drawString("   Sales Person: " + userName + "   ", 12, y);
                y += yShift;
                g2d.drawString("         AA Pharmacy        ", 12, y);
                y += yShift;
                g2d.drawString("            Fast            ", 12, y);
                y += yShift;
                g2d.drawString("           Lahore            ", 12, y);
                y += yShift;
                g2d.drawString("        +923218450365      ", 12, y);
                y += yShift;
                g2d.drawString("-------------------------------------", 12, y);
                y += headerRectHeight;

                g2d.drawString(" Item Name        Qty       Price   ", 10, y);
                y += yShift;
                g2d.drawString("-------------------------------------", 10, y);
                y += headerRectHeight;

                for (int s = 0; s < r; s++) {
                    g2d.drawString(" " + names.get(s) + "                            ", 10, y);
                    y += yShift;
                    g2d.drawString("      " + quantities.get(s) + "  " + prices.get(s), 10, y);
                    y += yShift;
                }

                g2d.drawString("-------------------------------------", 10, y);
                y += yShift;
                g2d.drawString(" Sub Total:               " + subTotal + "   ", 10, y);
                y += yShift;
                g2d.drawString(" Total amount:               " + totalAmount + "   ", 10, y);
                y += yShift;
                g2d.drawString("-------------------------------------", 10, y);
                y += yShift;
                g2d.drawString("*************************************", 10, y);
                y += yShift;
                g2d.drawString("       THANK YOU COME AGAIN            ", 10, y);
                y += yShift;
                g2d.drawString("*************************************", 10, y);
                y += yShift;
                g2d.drawString("      SOFTWARE BY:AA Developers        ", 10, y);
                y += yShift;
                g2d.drawString("  CONTACT: amaanshokat2468@gmail.com     ", 10, y);

            } catch (Exception e) {
                e.printStackTrace();
            }

            result = PAGE_EXISTS;
        }
        return result;
    }
}
