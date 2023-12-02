package View;

import Controller.ActionListionerController.Login.LoginButtonListener;
import Controller.ComponentListenerController.LoginPageComponent;

import javax.swing.*;
import java.awt.*;

/**
 * The LoginPage class represents the login page of the Pharmacy POS application.
 */
public class LoginPage extends JFrame {
    private final JLabel u_name = new JLabel("Username: "); // Label for username
    private final JLabel pass = new JLabel("Password: "); // Label for password
    private final JTextField username = new JTextField(); // Text field for entering username
    private final JTextField password = new JPasswordField(); // Text field for entering password
    private final SpringLayout sLayout = new SpringLayout(); // Spring layout for positioning components

    /**
     * Constructor for the LoginPage class.
     */
    public LoginPage() {
        setVisible(true);
        setLocation(500, 300);
        setSize(700, 500);
        setTitle("Login");
        setLayout(sLayout);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        addComponentListener(new LoginPageComponent(this));

        // Header label for the application
        JLabel headerLabel = new JLabel("Pharmacy POS");
        add(headerLabel);
        String north = SpringLayout.NORTH;
        sLayout.putConstraint(north, headerLabel, 5, north, this);
        String west = SpringLayout.WEST;
        sLayout.putConstraint(west, headerLabel, 5, west, this);
        Font font = new Font("Arial", Font.BOLD, 24);
        headerLabel.setFont(font);

        // Username label and text field
        add(u_name);
        sLayout.putConstraint(north, u_name, ((getHeight() / 2) - (getHeight() / 10)), north, this);
        sLayout.putConstraint(west, u_name, ((getWidth() / 2) - (getWidth() / 10)), west, this);
        add(username);
        sLayout.putConstraint(north, username, ((getHeight() / 2) - (getHeight() / 10)), north, this);
        String east = SpringLayout.EAST;
        sLayout.putConstraint(west, username, 10, east, u_name);
        sLayout.putConstraint(east, username, (getWidth() / 4), west, u_name);

        // Password label and text field
        add(pass);
        String south = SpringLayout.SOUTH;
        sLayout.putConstraint(north, pass, 10, south, u_name);
        sLayout.putConstraint(west, pass, ((getWidth() / 2) - (getWidth() / 10)), west, this);
        add(password);
        sLayout.putConstraint(north, password, 10, south, u_name);
        sLayout.putConstraint(west, password, 10, east, u_name);
        sLayout.putConstraint(east, password, (getWidth() / 4), west, u_name);

        // Login button
        JButton login = new JButton("Login");
        add(login);
        sLayout.putConstraint(north, login, 10, south, pass);
        sLayout.putConstraint(west, login, 35, west, pass);
        login.addActionListener(new LoginButtonListener(this));
    }

    // Entry point for the application
    public static void main(String... args) {
        SwingUtilities.invokeLater(LoginPage::new);
    }

    // Getter methods for accessing components from other classes
    public JLabel getU_name() {
        return u_name;
    }

    public JLabel getPass() {
        return pass;
    }

    public JTextField getUsername() {
        return username;
    }

    public JTextField getPassword() {
        return password;
    }

    public SpringLayout getsLayout() {
        return sLayout;
    }
}
