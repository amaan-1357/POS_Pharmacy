package View;

import Controller.ActionListionerController.LogoutButtonListener;
import View.Panels.GradientPanel;
import View.Panels.SalesPanel;

import javax.swing.*;
import java.awt.*;

/**
 * The SalesRepPortal class represents the main frame for the Sales Representative Portal.
 */
public class SalesRepPortal extends JFrame {
    // Components for the header
    private JLabel headerLabel = new JLabel("Pharmacy POS | Sales Rep");
    private JButton logout = new JButton("Logout");
    private JPanel headerPane = new GradientPanel();

    // Components for the content area
    private JPanel contentPane = new JPanel();
    private JPanel sp;
    private SpringLayout sLayout = new SpringLayout();
    private CardLayout cLayout = new CardLayout();
    private String north = SpringLayout.NORTH;
    private String south = SpringLayout.SOUTH;
    private String west = SpringLayout.WEST;
    private String east = SpringLayout.EAST;

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
        add(headerPane, gbcHeader);

        headerPane.setLayout(sLayout);
        headerPane.add(headerLabel);
        sLayout.putConstraint(north, headerLabel, 13, north, this);
        sLayout.putConstraint(west, headerLabel, 10, west, this);
        Font font = new Font("Arial", Font.BOLD, 30);
        headerLabel.setFont(font);

        headerPane.add(logout);
        sLayout.putConstraint(north, logout, 13, north, this);
        sLayout.putConstraint(west, logout, 1190, east, headerLabel);

        // Create content panel
        GridBagConstraints gbcContent = new GridBagConstraints();
        gbcContent.gridx = 0;
        gbcContent.gridy = 1;
        gbcContent.weightx = 1.0;
        gbcContent.weighty = 0.9; // 90% of the height
        gbcContent.fill = GridBagConstraints.BOTH;
        add(contentPane, gbcContent);

        sp = new SalesPanel(UID);

        contentPane.setLayout(new CardLayout(5, 5));
        contentPane.add(sp, "p1");
        cLayout = (CardLayout) contentPane.getLayout();
        cLayout.show(contentPane, "p1");

        logout.addActionListener(new LogoutButtonListener(this));

        pack();
        setExtendedState(MAXIMIZED_BOTH);
        setVisible(true);
    }
}
