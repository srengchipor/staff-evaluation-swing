package view;

import service.AdminService;
import service.impl.AdminServiceImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

public class CompanyRegistrationForm {

    public static AdminService adminService = new AdminServiceImpl();


    private JFrame frame;
    private JTextField nameField;
    private JTextField addressField;
    private JTextField phoneField;
    private JTextField emailField;
    private JTextField adminUserField;
    private JPasswordField adminPasswordField;
    private JPasswordField confirmPasswordField;

    public CompanyRegistrationForm() {
        frame = new JFrame("Company Registration");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 600);
        frame.setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);

        JLabel titleLabel = new JLabel("Register a New Company");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(8, 5, 8, 5);

        int row = 0;

        addFormField(formPanel, gbc, row++, "Company Name:", nameField = new JTextField(20));
        addFormField(formPanel, gbc, row++, "Address:", addressField = new JTextField(20));
        addFormField(formPanel, gbc, row++, "Phone:", phoneField = new JTextField(20));
        addFormField(formPanel, gbc, row++, "Email:", emailField = new JTextField(20));

        JSeparator separator = new JSeparator();
        gbc.gridx = 0;
        gbc.gridy = row++;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(15, 5, 15, 5);
        formPanel.add(separator, gbc);

        JLabel adminLabel = new JLabel("Administrator Account");
        adminLabel.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridy = row++;
        gbc.insets = new Insets(5, 5, 10, 5);
        formPanel.add(adminLabel, gbc);

        gbc.gridwidth = 1;
        gbc.insets = new Insets(8, 5, 8, 5);

        addFormField(formPanel, gbc, row++, "Admin Username:", adminUserField = new JTextField(20));
        addFormField(formPanel, gbc, row++, "Admin Password:", adminPasswordField = new JPasswordField(20));
        addFormField(formPanel, gbc, row++, "Confirm Password:", confirmPasswordField = new JPasswordField(20));

        mainPanel.add(formPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 15));
        buttonPanel.setBackground(Color.WHITE);

        JButton registerButton = new JButton("Register");
        registerButton.setPreferredSize(new Dimension(120, 35));
        registerButton.setBackground(new Color(60, 179, 113));
        registerButton.setForeground(Color.WHITE);
        registerButton.setFocusPainted(false);
        registerButton.setFont(new Font("Arial", Font.BOLD, 14));

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setPreferredSize(new Dimension(120, 35));
        cancelButton.setBackground(new Color(220, 53, 69));
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setFocusPainted(false);
        cancelButton.setFont(new Font("Arial", Font.BOLD, 14));

        buttonPanel.add(registerButton);
        buttonPanel.add(cancelButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                registerCompany();
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new FirstUI();
            }
        });

        frame.add(mainPanel);
        frame.setVisible(true);
    }

    private void addFormField(JPanel panel, GridBagConstraints gbc, int row, String label, JTextField field) {
        JLabel jLabel = new JLabel(label);
        jLabel.setFont(new Font("Arial", Font.PLAIN, 13));
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(jLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = row;
        panel.add(field, gbc);
    }

    private void registerCompany() {
        String name = nameField.getText().trim();
        String address = addressField.getText().trim();
        String phone = phoneField.getText().trim();
        String email = emailField.getText().trim();
        String adminUser = adminUserField.getText().trim();
        String adminPassword = new String(adminPasswordField.getPassword());
        String confirmPassword = new String(confirmPasswordField.getPassword());

        if (name.isEmpty() || address.isEmpty() || phone.isEmpty() ||
                email.isEmpty() || adminUser.isEmpty() || adminPassword.isEmpty()) {
            JOptionPane.showMessageDialog(frame,
                    "Please fill in all fields!",
                    "Validation Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!email.contains("@") || !email.contains(".")) {
            JOptionPane.showMessageDialog(frame,
                    "Please enter a valid email address!",
                    "Validation Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!adminPassword.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(frame,
                    "Passwords do not match!",
                    "Validation Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (adminPassword.length() < 6) {
            JOptionPane.showMessageDialog(frame,
                    "Password must be at least 6 characters long!",
                    "Validation Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Here you would insert into database
        // String sql = "INSERT INTO companies (name, address, phone, email, admin_user, admin_password) VALUES (?, ?, ?, ?, ?, ?)";

        int result = JOptionPane.showConfirmDialog(frame,
                "Company Name: " + name + "\nAddress: " + address +
                        "\nPhone: " + phone + "\nEmail: " + email +
                        "\nAdmin User: " + adminUser +
                        "\n\nAre you sure you want to register this company?",
                "Confirm Registration",
                JOptionPane.YES_NO_OPTION);

        if (result == JOptionPane.YES_OPTION) {
            try {

                AdminService adminService = new AdminServiceImpl();


                JOptionPane.showMessageDialog(frame,
                        // Call the registerCompany method
                        adminService.registerCompany(
                                name, address, phone, email, adminUser, adminPassword
                        ),
//                        "Company registered successfully!\n\nYou can now login with your admin credentials.",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE);

                frame.dispose();
                new FirstUI();

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(frame,
                        "Database error: " + ex.getMessage(),
                        "Registration Failed",
                        JOptionPane.ERROR_MESSAGE);
                System.out.println("Error: " + ex.getMessage());;
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame,
                        "An error occurred: " + ex.getMessage(),
                        "Registration Failed",
                        JOptionPane.ERROR_MESSAGE);
                System.out.println("Error: " + ex.getMessage());
            }
        }
    }
}