package View;

import Controller.ActionListionerController.Admin.InventoryButtonListener;
import Controller.ActionListionerController.Admin.SalesButtonListener;
import Controller.ActionListionerController.Admin.UsersButtonListener;
import Controller.ActionListionerController.LogoutButtonListener;
import View.Panels.GradientPanel;
import View.Panels.InventoryPanel;
import View.Panels.SalesPanel;
import View.Panels.UsersPanel;

import javax.swing.*;
import java.awt.*;

public class AdminPortal extends JFrame {
    private JLabel headerLabel = new JLabel("Pharmacy POS | Admin");
    private JButton sales = new JButton("Sales");
    private JButton manageInventory = new JButton("Manage Inventory");
    private JButton manageUsers = new JButton("Manage Users");
    private JButton inventoryReport = new JButton("Generate Inventory Report");
    private JButton salesReport = new JButton("Generate Sales Report");
    private JButton logout = new JButton("Logout");
    private JPanel headerPane = new GradientPanel();
    private JPanel contentPanel = new JPanel();
    private JPanel sp;
    private JPanel ip;
    private JPanel up;
    private SpringLayout sLayout = new SpringLayout();
    private CardLayout cLayout = new CardLayout();
    private String north = SpringLayout.NORTH;
    private String south = SpringLayout.SOUTH;
    private String west = SpringLayout.WEST;
    private String east = SpringLayout.EAST;

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

        sp = new SalesPanel(UID);
        ip = new InventoryPanel();
        up = new UsersPanel(UID);

        // Create header panel
        GridBagConstraints gbcHeader = new GridBagConstraints();
        gbcHeader.gridx = 0;
        gbcHeader.gridy = 0;
        gbcHeader.weightx = 1.0;
        gbcHeader.weighty = 0.1; // 10% of the height
        gbcHeader.fill = GridBagConstraints.BOTH;
        add(headerPane, gbcHeader);

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

        headerPane.add(manageUsers);
        sLayout.putConstraint(north,manageUsers,13, north,this);
        sLayout.putConstraint(west,manageUsers,10,east,manageInventory);

        headerPane.add(inventoryReport);
        sLayout.putConstraint(north,inventoryReport,13, north,this);
        sLayout.putConstraint(west,inventoryReport,10,east,manageUsers);

        headerPane.add(salesReport);
        sLayout.putConstraint(north,salesReport,13, north,this);
        sLayout.putConstraint(west,salesReport,10,east,inventoryReport);

        headerPane.add(logout);
        sLayout.putConstraint(north,logout,13, north,this);
        sLayout.putConstraint(west,logout,10,east,salesReport);

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
        cLayout = (CardLayout) contentPanel.getLayout();
        cLayout.show(contentPanel,"p1");

        logout.addActionListener(new LogoutButtonListener(this));
        sales.addActionListener(new SalesButtonListener(this));
        manageInventory.addActionListener(new InventoryButtonListener(this));
        manageUsers.addActionListener(new UsersButtonListener(this));

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