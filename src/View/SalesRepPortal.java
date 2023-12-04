package View;

import Controller.ActionListionerController.DeleteExpired;
import Controller.ActionListionerController.LogoutButtonListener;
import View.Panels.GradientPanel;
import View.Panels.SalesPanel;

import javax.swing.*;
import java.awt.*;

/**
 * The SalesRepPortal class represents the main frame for the Sales Representative Portal.
 */
public class SalesRepPortal extends JFrame {

    /**
     * Constructs the SalesRepPortal frame.
     */
    public SalesRepPortal(Integer UID) {
        setVisible(true);
        setExtendedState(MAXIMIZED_BOTH);
        setLayout(new GridBagLayout());
        setSize(1680, 1050);
        setTitle("Sales Rep Portal");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

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
        // Components for the header
        JLabel headerLabel = new JLabel("Pharmacy POS | Sales Rep");
        headerPane.add(headerLabel);
        String north = SpringLayout.NORTH;
        sLayout.putConstraint(north, headerLabel, 13, north, this);
        String west = SpringLayout.WEST;
        sLayout.putConstraint(west, headerLabel, 10, west, this);
        Font font = new Font("Arial", Font.BOLD, 30);
        headerLabel.setFont(font);

        JButton logout = new JButton("Logout");
        headerPane.add(logout);
        sLayout.putConstraint(north, logout, 13, north, this);
        String east = SpringLayout.EAST;
        sLayout.putConstraint(west, logout, 1190, east, headerLabel);

        // Create content panel
        GridBagConstraints gbcContent = new GridBagConstraints();
        gbcContent.gridx = 0;
        gbcContent.gridy = 1;
        gbcContent.weightx = 1.0;
        gbcContent.weighty = 0.9; // 90% of the height
        gbcContent.fill = GridBagConstraints.BOTH;
        // Components for the content area
        JPanel contentPane = new JPanel();
        add(contentPane, gbcContent);

        JPanel sp = new SalesPanel(UID);

        contentPane.setLayout(new CardLayout(5, 5));
        contentPane.add(sp, "p1");
        CardLayout cLayout = (CardLayout) contentPane.getLayout();
        cLayout.show(contentPane, "p1");

        logout.addActionListener(new LogoutButtonListener(this));

        DeleteExpired de = new DeleteExpired((SalesPanel) sp);

        pack();
        setExtendedState(MAXIMIZED_BOTH);
        setVisible(true);
    }
}
