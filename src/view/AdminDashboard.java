package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

/**
 * Super Admin Dashboard - Manages all companies and system-wide settings
 */
public class AdminDashboard extends JFrame {

    private JPanel contentPanel;
    private CardLayout cardLayout;

    public AdminDashboard() {
        setTitle("Staff Evaluation System - Super Admin Dashboard");
        setSize(1200, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        // Sidebar
        JPanel sidebar = createSidebar();
        add(sidebar, BorderLayout.WEST);

        // Content Area with CardLayout
        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);

        contentPanel.add(createDashboardPanel(), "dashboard");
        contentPanel.add(createCompanyManagementPanel(), "companies");
        contentPanel.add(createSystemUsersPanel(), "users");
        contentPanel.add(createSystemReportsPanel(), "reports");

        add(contentPanel, BorderLayout.CENTER);

        cardLayout.show(contentPanel, "dashboard");
    }

    private JPanel createSidebar() {
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBackground(new Color(15, 23, 42));
        sidebar.setPreferredSize(new Dimension(250, 0));
        sidebar.setBorder(BorderFactory.createEmptyBorder(20, 15, 20, 15));

        JLabel titleLabel = new JLabel("SUPER ADMIN");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        sidebar.add(titleLabel);

        JLabel subtitleLabel = new JLabel("System Management");
        subtitleLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        subtitleLabel.setForeground(new Color(148, 163, 184));
        subtitleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        sidebar.add(subtitleLabel);

        sidebar.add(Box.createRigidArea(new Dimension(0, 30)));

        sidebar.add(createMenuButton("📊 Dashboard", "dashboard"));
        sidebar.add(Box.createRigidArea(new Dimension(0, 10)));
        sidebar.add(createMenuButton("🏢 Companies", "companies"));
        sidebar.add(Box.createRigidArea(new Dimension(0, 10)));
        sidebar.add(createMenuButton("👤 System Users", "users"));
        sidebar.add(Box.createRigidArea(new Dimension(0, 10)));
        sidebar.add(createMenuButton("📈 System Reports", "reports"));

        sidebar.add(Box.createVerticalGlue());

        JButton logoutBtn = new JButton("🚪 Logout");
        logoutBtn.setAlignmentX(Component.LEFT_ALIGNMENT);
        logoutBtn.setMaximumSize(new Dimension(220, 40));
        logoutBtn.setBackground(new Color(185, 28, 28));
        logoutBtn.setForeground(Color.WHITE);
        logoutBtn.setFocusPainted(false);
        logoutBtn.setBorderPainted(false);
        logoutBtn.addActionListener(e -> System.exit(0));
        sidebar.add(logoutBtn);

        return sidebar;
    }

    private JButton createMenuButton(String text, String panelName) {
        JButton button = new JButton(text);
        button.setAlignmentX(Component.LEFT_ALIGNMENT);
        button.setMaximumSize(new Dimension(220, 40));
        button.setBackground(new Color(30, 41, 59));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setFont(new Font("Arial", Font.PLAIN, 14));

        button.addActionListener(e -> cardLayout.show(contentPanel, panelName));

        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                button.setBackground(new Color(51, 65, 85));
            }
            public void mouseExited(MouseEvent e) {
                button.setBackground(new Color(30, 41, 59));
            }
        });

        return button;
    }

    private JPanel createDashboardPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(new Color(241, 245, 249));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(241, 245, 249));
        JLabel headerLabel = new JLabel("System Overview");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 28));
        headerPanel.add(headerLabel, BorderLayout.WEST);
        panel.add(headerPanel, BorderLayout.NORTH);

        JPanel statsPanel = new JPanel(new GridLayout(1, 4, 15, 0));
        statsPanel.setBackground(new Color(241, 245, 249));
        statsPanel.add(createStatCard("Total Companies", "12", new Color(59, 130, 246)));
        statsPanel.add(createStatCard("Active Users", "248", new Color(34, 197, 94)));
        statsPanel.add(createStatCard("Total Staff", "856", new Color(168, 85, 247)));
        statsPanel.add(createStatCard("System Health", "98%", new Color(249, 115, 22)));

        panel.add(statsPanel, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createCompanyManagementPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(new Color(241, 245, 249));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(241, 245, 249));
        JLabel headerLabel = new JLabel("Company Management");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 28));
        headerPanel.add(headerLabel, BorderLayout.WEST);

        JButton addCompanyBtn = new JButton("➕ Register Company");
        addCompanyBtn.setBackground(new Color(59, 130, 246));
        addCompanyBtn.setForeground(Color.WHITE);
        addCompanyBtn.setFocusPainted(false);
        addCompanyBtn.setFont(new Font("Arial", Font.BOLD, 14));
        addCompanyBtn.addActionListener(e -> showRegisterCompanyDialog());
        headerPanel.add(addCompanyBtn, BorderLayout.EAST);

        panel.add(headerPanel, BorderLayout.NORTH);

        String[] columns = {"ID", "Company Name", "Email", "Phone", "Admin User", "Status", "Actions"};
        Object[][] data = {
                {1, "Agent 404", "agent404@gmail.com", "099876543", "admin", "Active", "Actions"},
                {2, "Tech Solutions Ltd", "info@techsol.com", "023456789", "techAdmin", "Active", "Actions"},
                {3, "Global Trading Co", "contact@global.com", "012345678", "globalAdmin", "Inactive", "Actions"}
        };

        DefaultTableModel model = new DefaultTableModel(data, columns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 6;
            }
        };

        JTable table = new JTable(model);
        table.setRowHeight(40);
        table.setFont(new Font("Arial", Font.PLAIN, 13));
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 13));

        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createSystemUsersPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(new Color(241, 245, 249));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel headerLabel = new JLabel("System Users");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 28));
        panel.add(headerLabel, BorderLayout.NORTH);

        String[] columns = {"ID", "Username", "Company", "Role", "Status"};
        Object[][] data = {
                {1, "admin", "System", "Super Admin", "Active"},
                {2, "agent404Admin", "Agent 404", "Company Admin", "Active"},
                {3, "techAdmin", "Tech Solutions", "Company Admin", "Active"}
        };

        JTable table = new JTable(data, columns);
        table.setRowHeight(40);
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createSystemReportsPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(new Color(241, 245, 249));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel headerLabel = new JLabel("System Reports");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 28));
        panel.add(headerLabel, BorderLayout.NORTH);

        JPanel reportPanel = new JPanel(new GridLayout(2, 2, 15, 15));
        reportPanel.setBackground(new Color(241, 245, 249));

        reportPanel.add(createReportCard("Companies Overview", "View all companies statistics"));
        reportPanel.add(createReportCard("User Analytics", "System-wide user activity"));
        reportPanel.add(createReportCard("Performance Metrics", "Overall system performance"));
        reportPanel.add(createReportCard("Usage Statistics", "System usage reports"));

        panel.add(reportPanel, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createStatCard(String label, String value, Color color) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(226, 232, 240), 1),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));

        JLabel valueLabel = new JLabel(value);
        valueLabel.setFont(new Font("Arial", Font.BOLD, 36));
        valueLabel.setForeground(color);
        valueLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel labelText = new JLabel(label);
        labelText.setFont(new Font("Arial", Font.PLAIN, 14));
        labelText.setForeground(new Color(100, 116, 139));
        labelText.setAlignmentX(Component.LEFT_ALIGNMENT);

        card.add(valueLabel);
        card.add(Box.createRigidArea(new Dimension(0, 5)));
        card.add(labelText);

        return card;
    }

    private JPanel createReportCard(String title, String description) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(226, 232, 240), 1),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel descLabel = new JLabel(description);
        descLabel.setFont(new Font("Arial", Font.PLAIN, 13));
        descLabel.setForeground(new Color(100, 116, 139));
        descLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JButton viewBtn = new JButton("View Report");
        viewBtn.setBackground(new Color(59, 130, 246));
        viewBtn.setForeground(Color.WHITE);
        viewBtn.setFocusPainted(false);
        viewBtn.setAlignmentX(Component.LEFT_ALIGNMENT);

        card.add(titleLabel);
        card.add(Box.createRigidArea(new Dimension(0, 10)));
        card.add(descLabel);
        card.add(Box.createRigidArea(new Dimension(0, 15)));
        card.add(viewBtn);

        return card;
    }

    private void showRegisterCompanyDialog() {
        JDialog dialog = new JDialog(this, "Register New Company", true);
        dialog.setSize(450, 400);
        dialog.setLocationRelativeTo(this);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        String[] labels = {"Company Name:", "Address:", "Phone:", "Email:", "Admin Username:", "Admin Password:"};
        JComponent[] fields = {
                new JTextField(20),
                new JTextField(20),
                new JTextField(20),
                new JTextField(20),
                new JTextField(20),
                new JPasswordField(20)
        };

        for (int i = 0; i < labels.length; i++) {
            gbc.gridx = 0; gbc.gridy = i;
            panel.add(new JLabel(labels[i]), gbc);
            gbc.gridx = 1;
            panel.add(fields[i], gbc);
        }

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton saveBtn = new JButton("Register");
        saveBtn.setBackground(new Color(34, 197, 94));
        saveBtn.setForeground(Color.WHITE);
        saveBtn.setFocusPainted(false);
        saveBtn.addActionListener(e -> {
            JOptionPane.showMessageDialog(dialog, "Company registered successfully!");
            dialog.dispose();
        });

        JButton cancelBtn = new JButton("Cancel");
        cancelBtn.addActionListener(e -> dialog.dispose());

        buttonPanel.add(saveBtn);
        buttonPanel.add(cancelBtn);

        gbc.gridx = 0; gbc.gridy = labels.length; gbc.gridwidth = 2;
        panel.add(buttonPanel, gbc);

        dialog.add(panel);
        dialog.setVisible(true);
    }

}