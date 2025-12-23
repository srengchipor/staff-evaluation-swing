package view;

import components.PopMessage;
import service.AuthService;
import service.impl.AuthServiceImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

public class LoginForm {

    public static AuthService authService =  new AuthServiceImpl();

    public static void getLoginForm(){
        JFrame frame = new JFrame("Authentication");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(720, 450);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titleLabel = new JLabel("Login");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(titleLabel, gbc);

        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;

        JLabel userLabel = new JLabel("Username:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(userLabel, gbc);

        JTextField userField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(userField, gbc);

        JLabel passLabel = new JLabel("Password:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(passLabel, gbc);

        JPasswordField passField = new JPasswordField(20);
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(passField, gbc);

        JButton loginButton = new JButton("Login");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(loginButton, gbc);

        JButton backButton = new JButton("Back");
        gbc.gridx = 1;                     // Right column
        gbc.gridy = 6;                     // Bottom row
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.SOUTHEAST; // Bottom-right
        gbc.fill = GridBagConstraints.NONE;
        panel.add(backButton, gbc);

        JLabel messageLabel = new JLabel("");
        messageLabel.setForeground(Color.RED);
        gbc.gridy = 4;
        panel.add(messageLabel, gbc);

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = userField.getText();
                String password = new String(passField.getPassword());

                try {
                    String result = authService.login(username, password);
                    if (username.isEmpty() || password.isEmpty()) {
                        messageLabel.setText("Please fill in all fields");
                    } else if (result.equals("SUPER_ADMIN")) {
                        messageLabel.setForeground(Color.GREEN);
                        messageLabel.setText("Login successful!");
                        PopMessage.showSuccess(frame, username);

                        frame.dispose();
                        AdminDashboard dashboard = new AdminDashboard();
                        dashboard.setVisible(true);

                    } else if (result.equals("ADMIN")) {
                        messageLabel.setForeground(Color.GREEN);
                        messageLabel.setText("Login successful!");
                        PopMessage.showSuccess(frame, username);

                        frame.dispose();
                        CompanyAdminDashboard dashboard = new CompanyAdminDashboard();
                        dashboard.setVisible(true);

                    } else if (result.equals("STAFF")) {
                        messageLabel.setForeground(Color.GREEN);
                        messageLabel.setText("Login successful!");
                        PopMessage.showSuccess(frame, username);

                        frame.dispose();
                        StaffDashboard dashboard = new StaffDashboard();
                        dashboard.setVisible(true);

                    } else {
                        messageLabel.setForeground(Color.RED);
                        messageLabel.setText("Invalid username or password");
                    }
                } catch (SQLException ex) {
                    System.out.println("SQL Error: " + ex.getMessage());
                }
            }
        });

        passField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loginButton.doClick();
            }
        });

        backButton.addActionListener(e -> {
            frame.dispose();
            new FirstUI();
        });

        frame.add(panel);
        frame.setVisible(true);
    }

}