package View;

import Controller.ActionListionerController.Admin.*;
import Controller.ActionListionerController.DeleteExpired;
import Controller.ActionListionerController.LogoutButtonListener;
import View.Panels.GradientPanel;
import View.Panels.InventoryPanel;
import View.Panels.SalesPanel;
import View.Panels.UsersPanel;

import javax.swing.*;
import java.awt.*;

public class AdminPortal extends JFrame {
    private final JPanel contentPanel = new JPanel();

    /**
     * Constructor for AdminPortal.
     * @param UID The user ID of the admin.
     */
    public AdminPortal(Integer UID){
        setVisible(true);
        setExtendedState(MAXIMIZED_BOTH);
        setLayout(new GridBagLayout());
        setSize(1680,1050);
        setTitle("AdminPortal");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel sp = new SalesPanel(UID);
        InventoryPanel ip = new InventoryPanel(sp);
        JPanel up = new UsersPanel(UID);

        // Create header panel
        GridBagConstraints gbcHeader = new GridBagConstraints();
        gbcHeader.gridx = 0;
        gbcHeader.gridy = 0;
        gbcHeader.weightx = 1.0;
        gbcHeader.weighty = 0.1; // 10% of the height
        gbcHeader.fill = GridBagConstraints.BOTH;
        JPanel headerPane = new GradientPanel();
        add(headerPane, gbcHeader);

        SpringLayout sLayout = new SpringLayout();
        headerPane.setLayout(sLayout);
        JLabel headerLabel = new JLabel("Pharmacy POS | Admin");
        headerPane.add(headerLabel);
        String north = SpringLayout.NORTH;
        sLayout.putConstraint(north, headerLabel,13, north,this);
        String west = SpringLayout.WEST;
        sLayout.putConstraint(west, headerLabel,10, west,this);
        Font font = new Font("Arial", Font.BOLD, 30);
        headerLabel.setFont(font);

        JButton sales = new JButton("Sales");
        headerPane.add(sales);
        sLayout.putConstraint(north, sales,13, north,this);
        String east = SpringLayout.EAST;
        sLayout.putConstraint(west, sales,450, east, headerLabel);

        JButton manageInventory = new JButton("Manage Inventory");
        headerPane.add(manageInventory);
        sLayout.putConstraint(north, manageInventory,13, north,this);
        sLayout.putConstraint(west, manageInventory,10, east, sales);

        JButton manageUsers = new JButton("Manage Users");
        headerPane.add(manageUsers);
        sLayout.putConstraint(north, manageUsers,13, north,this);
        sLayout.putConstraint(west, manageUsers,10, east, manageInventory);

        JButton inventoryReport = new JButton("Generate Inventory Report");
        headerPane.add(inventoryReport);
        sLayout.putConstraint(north, inventoryReport,13, north,this);
        sLayout.putConstraint(west, inventoryReport,10, east, manageUsers);

        JButton salesReport = new JButton("Generate Sales Report");
        headerPane.add(salesReport);
        sLayout.putConstraint(north, salesReport,13, north,this);
        sLayout.putConstraint(west, salesReport,10, east, inventoryReport);

        JButton logout = new JButton("Logout");
        headerPane.add(logout);
        sLayout.putConstraint(north, logout,13, north,this);
        sLayout.putConstraint(west, logout,10, east, salesReport);

        // Create content panel
        GridBagConstraints gbcContent = new GridBagConstraints();
        gbcContent.gridx = 0;
        gbcContent.gridy = 1;
        gbcContent.weightx = 1.0;
        gbcContent.weighty = 0.9; // 90% of the height
        gbcContent.fill = GridBagConstraints.BOTH;
        add(contentPanel, gbcContent);

        contentPanel.setLayout(new CardLayout(5,5));
        contentPanel.add(sp,"p1");
        contentPanel.add(ip,"p2");
        contentPanel.add(up,"p3");
        CardLayout cLayout = (CardLayout) contentPanel.getLayout();
        cLayout.show(contentPanel,"p1");

        logout.addActionListener(new LogoutButtonListener(this));
        sales.addActionListener(new SalesButtonListener(this));
        manageInventory.addActionListener(new InventoryButtonListener(this, ip));
        manageUsers.addActionListener(new UsersButtonListener(this));
        inventoryReport.addActionListener(new InventoryReportListener(this));
        salesReport.addActionListener(new SalesReportListener(this));

        DeleteExpired de = new DeleteExpired((SalesPanel) sp);

        pack();
        setExtendedState(MAXIMIZED_BOTH);
        setVisible(true);
    }

    /**
     * Gets the content panel of the admin portal.
     * @return The content panel.
     */
    public JPanel getContentPanel() {
        return contentPanel;
    }
}
