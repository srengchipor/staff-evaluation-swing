package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FirstUI {

    private JFrame frame;

    public FirstUI() {
        frame = new JFrame("Staff Evaluation System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(new Color(240, 240, 240));

        JLabel titleLabel = new JLabel("Staff Evaluation System");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(30, 0, 30, 0));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridBagLayout());
        centerPanel.setBackground(new Color(240, 240, 240));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);

        JPanel registerPanel = createOptionPanel("Register a new\nCompany", new Color(70, 130, 180));
        gbc.gridx = 0;
        gbc.gridy = 0;
        centerPanel.add(registerPanel, gbc);

        JPanel loginPanel = createOptionPanel("Login", new Color(60, 179, 113));
        gbc.gridx = 1;
        gbc.gridy = 0;
        centerPanel.add(loginPanel, gbc);

        registerPanel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                frame.dispose();
                new CompanyRegistrationForm();
            }

            public void mouseEntered(MouseEvent e) {
                registerPanel.setBackground(new Color(90, 150, 200));
                registerPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            public void mouseExited(MouseEvent e) {
                registerPanel.setBackground(new Color(70, 130, 180));
            }
        });

        loginPanel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                frame.dispose();
                LoginForm.getLoginForm();
            }

            public void mouseEntered(MouseEvent e) {
                loginPanel.setBackground(new Color(80, 199, 133));
                loginPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            public void mouseExited(MouseEvent e) {
                loginPanel.setBackground(new Color(60, 179, 113));
            }
        });

        mainPanel.add(centerPanel, BorderLayout.CENTER);

        frame.add(mainPanel);
        frame.setVisible(true);
    }

    private JPanel createOptionPanel(String text, Color bgColor) {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(200, 150));
        panel.setBackground(bgColor);
        panel.setLayout(new GridBagLayout());
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY, 1),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        JLabel label = new JLabel("<html><div style='text-align: center;'>" +
                text.replace("\n", "<br>") + "</div></html>");
        label.setFont(new Font("Arial", Font.BOLD, 18));
        label.setForeground(Color.WHITE);
        label.setHorizontalAlignment(SwingConstants.CENTER);

        panel.add(label);

        return panel;
    }

}