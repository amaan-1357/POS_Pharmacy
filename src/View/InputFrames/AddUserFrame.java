package View.InputFrames;

import Controller.ActionListionerController.Admin.User.AddUserFrame.ConfirmButtonListener;
import Controller.ActionListionerController.CancelButtonListener;
import View.Panels.UsersPanel;

import javax.swing.*;
import java.awt.*;

/**
 * The AddUserFrame class represents a JFrame for adding a new user.
 */
public class AddUserFrame extends JFrame {
    JLabel nameLabel = new JLabel("Name:");
    JLabel usernameLabel = new JLabel("Username:");
    JLabel passwordLabel = new JLabel("Password:");
    JLabel roleLabel = new JLabel("Role:");
    private final JTextField nameField = new JTextField();
    private final JTextField usernameField = new JTextField();
    private final JTextField passwordField = new JTextField();
    private final JComboBox<String> roleComboBox;
    JButton cancelButton = new JButton("Cancel");
    JButton confirmButton = new JButton("Confirm");

    /**
     * Constructor for the AddUserFrame.
     *
     * @param up The UsersPanel where the frame is displayed.
     */
    public AddUserFrame(UsersPanel up) {
        setTitle("User Registration");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridBagLayout());
        setLocationRelativeTo(null);
        setVisible(true);

        String[] roles = {"manager", "sales_assistant"};
        roleComboBox = new JComboBox<>(roles);

        nameField.setColumns(20);
        usernameField.setColumns(20);
        passwordField.setColumns(20);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Add components to the JFrame
        add(nameLabel, gbc);
        gbc.gridy++;
        add(usernameLabel, gbc);
        gbc.gridy++;
        add(passwordLabel, gbc);
        gbc.gridy++;
        add(roleLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(nameField, gbc);
        gbc.gridy++;
        add(usernameField, gbc);
        gbc.gridy++;
        add(passwordField, gbc);
        gbc.gridy++;
        add(roleComboBox, gbc);

        gbc.gridy++;
        add(cancelButton, gbc);
        gbc.gridx++;
        add(confirmButton, gbc);

        cancelButton.addActionListener(new CancelButtonListener(this));
        confirmButton.addActionListener(new ConfirmButtonListener(this, up));
    }

    public JTextField getNameField() {
        return nameField;
    }

    public JTextField getUsernameField() {
        return usernameField;
    }

    public JTextField getPasswordField() {
        return passwordField;
    }

    public JComboBox<String> getRoleComboBox() {
        return roleComboBox;
    }
}
