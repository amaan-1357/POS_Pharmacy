package View;

import View.Panels.SalesPanel;

import javax.swing.*;
import java.awt.*;

public class PharmacyAdminPortal extends JFrame {

    private JLabel headerLabel = new JLabel("Pharmacy POS | Admin");
    private JButton sales = new JButton("Sales");
    private JButton manageInventory = new JButton("Manage Inventory");
    private JButton manageStaff = new JButton("Manage Staff");
    private JButton inventoryReport = new JButton("Generate Inventory Report");
    private JButton salesReport = new JButton("Generate Sales Report");
    private JButton logout = new JButton("Logout");
    private JPanel headerPane = new JPanel();
    private JPanel contentPane = new JPanel();
    private JPanel sp;
    private SpringLayout sLayout = new SpringLayout();
    private CardLayout cLayout = new CardLayout();
    private String north = SpringLayout.NORTH;
    private String south = SpringLayout.SOUTH;
    private String west = SpringLayout.WEST;
    private String east = SpringLayout.EAST;

    public PharmacyAdminPortal() {
        setVisible(true);
        setExtendedState(MAXIMIZED_BOTH);
        getContentPane().setLayout(new BoxLayout(getContentPane(),BoxLayout.Y_AXIS));
        setSize(1680,1050);
        setTitle("AdminPortal");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        headerPane.setLayout(sLayout);
        headerPane.add(headerLabel);
        sLayout.putConstraint(north, headerLabel,13, north,this);
        sLayout.putConstraint(west, headerLabel,10, west,this);
        Font font = new Font("Arial", Font.BOLD, 30);
        headerLabel.setFont(font);

        headerPane.add(sales);
        sLayout.putConstraint(north,sales,13, north,this);
        sLayout.putConstraint(west,sales,450,east,headerLabel);

        headerPane.add(manageInventory);
        sLayout.putConstraint(north,manageInventory,13, north,this);
        sLayout.putConstraint(west,manageInventory,10,east,sales);

        headerPane.add(manageStaff);
        sLayout.putConstraint(north,manageStaff,13, north,this);
        sLayout.putConstraint(west,manageStaff,10,east,manageInventory);

        headerPane.add(inventoryReport);
        sLayout.putConstraint(north,inventoryReport,13, north,this);
        sLayout.putConstraint(west,inventoryReport,10,east,manageStaff);

        headerPane.add(salesReport);
        sLayout.putConstraint(north,salesReport,13, north,this);
        sLayout.putConstraint(west,salesReport,10,east,inventoryReport);

        headerPane.add(logout);
        sLayout.putConstraint(north,logout,13, north,this);
        sLayout.putConstraint(west,logout,10,east,salesReport);
        getContentPane().add(headerPane);

        contentPane.setLayout(new CardLayout(5,5));
        contentPane.add(sp,"p1");
        cLayout = (CardLayout) contentPane.getLayout();
        cLayout.show(contentPane,"p1");
        getContentPane().add(contentPane);
    }



}

