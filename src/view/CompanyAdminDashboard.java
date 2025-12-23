package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

/**
 * Company Admin Dashboard - Manages company-specific staff, evaluations, and reports
 */
public class CompanyAdminDashboard extends JFrame {

    private JPanel contentPanel;
    private CardLayout cardLayout;
    private String companyName = "Agent 404"; // This should be passed from login

    public CompanyAdminDashboard() {
        setTitle("Staff Evaluation System - Company Admin Dashboard");
        setSize(1200, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        JPanel sidebar = createSidebar();
        add(sidebar, BorderLayout.WEST);

        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);

        contentPanel.add(createDashboardPanel(), "dashboard");
        contentPanel.add(createStaffManagementPanel(), "staff");
        contentPanel.add(createOrganizationPanel(), "organization");
        contentPanel.add(createPeriodsPanel(), "periods");
        contentPanel.add(createAssignmentsPanel(), "assignments");
        contentPanel.add(createReportsPanel(), "reports");

        add(contentPanel, BorderLayout.CENTER);

        cardLayout.show(contentPanel, "dashboard");
    }

    private JPanel createSidebar() {
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBackground(new Color(30, 41, 59));
        sidebar.setPreferredSize(new Dimension(250, 0));
        sidebar.setBorder(BorderFactory.createEmptyBorder(20, 15, 20, 15));

        JLabel titleLabel = new JLabel("COMPANY ADMIN");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        sidebar.add(titleLabel);

        JLabel subtitleLabel = new JLabel(companyName);
        subtitleLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        subtitleLabel.setForeground(new Color(148, 163, 184));
        subtitleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        sidebar.add(subtitleLabel);

        sidebar.add(Box.createRigidArea(new Dimension(0, 30)));

        sidebar.add(createMenuButton("📊 Dashboard", "dashboard"));
        sidebar.add(Box.createRigidArea(new Dimension(0, 10)));
        sidebar.add(createMenuButton("👥 Staff", "staff"));
        sidebar.add(Box.createRigidArea(new Dimension(0, 10)));
        sidebar.add(createMenuButton("🏢 Organization", "organization"));
        sidebar.add(Box.createRigidArea(new Dimension(0, 10)));
        sidebar.add(createMenuButton("📅 Periods", "periods"));
        sidebar.add(Box.createRigidArea(new Dimension(0, 10)));
        sidebar.add(createMenuButton("📋 Assignments", "assignments"));
        sidebar.add(Box.createRigidArea(new Dimension(0, 10)));
        sidebar.add(createMenuButton("📈 Reports", "reports"));

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
        button.setBackground(new Color(51, 65, 85));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setFont(new Font("Arial", Font.PLAIN, 14));

        button.addActionListener(e -> cardLayout.show(contentPanel, panelName));

        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                button.setBackground(new Color(71, 85, 105));
            }

            public void mouseExited(MouseEvent e) {
                button.setBackground(new Color(51, 65, 85));
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
        JLabel headerLabel = new JLabel("Dashboard Overview");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 28));
        headerPanel.add(headerLabel, BorderLayout.WEST);
        panel.add(headerPanel, BorderLayout.NORTH);

        JPanel statsPanel = new JPanel(new GridLayout(1, 4, 15, 0));
        statsPanel.setBackground(new Color(241, 245, 249));
        statsPanel.add(createStatCard("Total Staff", "48", new Color(59, 130, 246)));
        statsPanel.add(createStatCard("Departments", "5", new Color(34, 197, 94)));
        statsPanel.add(createStatCard("Active Periods", "2", new Color(168, 85, 247)));
        statsPanel.add(createStatCard("Evaluations", "156", new Color(249, 115, 22)));

        panel.add(statsPanel, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createStaffManagementPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(new Color(241, 245, 249));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(241, 245, 249));
        JLabel headerLabel = new JLabel("Staff Management");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 28));
        headerPanel.add(headerLabel, BorderLayout.WEST);

        JButton addStaffBtn = new JButton("➕ Add Staff");
        addStaffBtn.setBackground(new Color(59, 130, 246));
        addStaffBtn.setForeground(Color.WHITE);
        addStaffBtn.setFocusPainted(false);
        addStaffBtn.setFont(new Font("Arial", Font.BOLD, 14));
        addStaffBtn.addActionListener(e -> showAddStaffDialog());
        headerPanel.add(addStaffBtn, BorderLayout.EAST);

        panel.add(headerPanel, BorderLayout.NORTH);

        String[] columns = {"ID", "Name", "Department", "Office", "Position", "Actions"};
        Object[][] data = {
                {1, "Sok Dara", "IT Department", "Head Office", "Developer", "Actions"},
                {2, "Chan Sophea", "HR Department", "Head Office", "HR Manager", "Actions"},
                {3, "Lim Pisey", "Sales Department", "Branch 1", "Sales Lead", "Actions"},
                {4, "Rath Vanna", "IT Department", "Head Office", "Team Leader", "Actions"},
                {5, "Mao Sreypov", "Sales Department", "Branch 2", "Sales Rep", "Actions"}
        };

        DefaultTableModel model = new DefaultTableModel(data, columns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 5;
            }
        };

        JTable table = new JTable(model);
        table.setRowHeight(40);
        table.setFont(new Font("Arial", Font.PLAIN, 13));
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 13));
        table.getTableHeader().setBackground(new Color(241, 245, 249));

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(226, 232, 240)));
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createOrganizationPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(new Color(241, 245, 249));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel headerLabel = new JLabel("Organization Structure");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 28));
        panel.add(headerLabel, BorderLayout.NORTH);

        JPanel mainPanel = new JPanel(new GridLayout(1, 3, 15, 0));
        mainPanel.setBackground(new Color(241, 245, 249));

        // Departments
        mainPanel.add(createOrgSection("Departments",
                new String[]{"IT Department", "HR Department", "Sales Department", "Finance", "Marketing"}));

        // Offices
        mainPanel.add(createOrgSection("Offices",
                new String[]{"Head Office", "Branch Office 1", "Branch Office 2"}));

        // Positions
        mainPanel.add(createOrgSection("Positions",
                new String[]{"Manager", "Team Leader", "Senior Staff", "Staff", "Intern"}));

        panel.add(mainPanel, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createOrgSection(String title, String[] items) {
        JPanel section = new JPanel(new BorderLayout());
        section.setBackground(Color.WHITE);
        section.setBorder(BorderFactory.createLineBorder(new Color(226, 232, 240), 1));

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(248, 250, 252));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        headerPanel.add(titleLabel, BorderLayout.WEST);

        JButton addBtn = new JButton("➕");
        addBtn.setBackground(new Color(59, 130, 246));
        addBtn.setForeground(Color.WHITE);
        addBtn.setFocusPainted(false);
        addBtn.setPreferredSize(new Dimension(40, 30));
        addBtn.addActionListener(e -> {
            String newItem = JOptionPane.showInputDialog(this, "Enter new " + title.toLowerCase() + " name:");
            if (newItem != null && !newItem.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, newItem + " added successfully!");
            }
        });
        headerPanel.add(addBtn, BorderLayout.EAST);

        section.add(headerPanel, BorderLayout.NORTH);

        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (String item : items) {
            listModel.addElement(item);
        }

        JList<String> list = new JList<>(listModel);
        list.setFont(new Font("Arial", Font.PLAIN, 13));
        list.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

        JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setBorder(null);
        section.add(scrollPane, BorderLayout.CENTER);

        return section;
    }

    private JPanel createPeriodsPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(new Color(241, 245, 249));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(241, 245, 249));
        JLabel headerLabel = new JLabel("Evaluation Periods");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 28));
        headerPanel.add(headerLabel, BorderLayout.WEST);

        JButton addPeriodBtn = new JButton("➕ New Period");
        addPeriodBtn.setBackground(new Color(168, 85, 247));
        addPeriodBtn.setForeground(Color.WHITE);
        addPeriodBtn.setFocusPainted(false);
        addPeriodBtn.setFont(new Font("Arial", Font.BOLD, 14));
        addPeriodBtn.addActionListener(e -> showAddPeriodDialog());
        headerPanel.add(addPeriodBtn, BorderLayout.EAST);

        panel.add(headerPanel, BorderLayout.NORTH);

        JPanel periodsPanel = new JPanel();
        periodsPanel.setLayout(new BoxLayout(periodsPanel, BoxLayout.Y_AXIS));
        periodsPanel.setBackground(new Color(241, 245, 249));

        periodsPanel.add(createPeriodCard("PER0001", "2024-12-01", "2024-12-31", "Active"));
        periodsPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        periodsPanel.add(createPeriodCard("PER0002", "2025-01-01", "2025-01-31", "Upcoming"));
        periodsPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        periodsPanel.add(createPeriodCard("PER0003", "2024-11-01", "2024-11-30", "Closed"));

        JScrollPane scrollPane = new JScrollPane(periodsPanel);
        scrollPane.setBorder(null);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createPeriodCard(String code, String fromDate, String toDate, String status) {
        JPanel card = new JPanel(new BorderLayout(15, 10));
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(226, 232, 240), 1),
                BorderFactory.createEmptyBorder(15, 20, 15, 20)
        ));
        card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBackground(Color.WHITE);

        JLabel codeLabel = new JLabel(code);
        codeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        JLabel dateLabel = new JLabel(fromDate + " to " + toDate);
        dateLabel.setFont(new Font("Arial", Font.PLAIN, 13));
        dateLabel.setForeground(new Color(100, 116, 139));

        infoPanel.add(codeLabel);
        infoPanel.add(dateLabel);

        JLabel statusLabel = new JLabel(status);
        statusLabel.setFont(new Font("Arial", Font.BOLD, 12));
        statusLabel.setOpaque(true);
        statusLabel.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));

        if (status.equals("Active")) {
            statusLabel.setBackground(new Color(220, 252, 231));
            statusLabel.setForeground(new Color(22, 163, 74));
        } else if (status.equals("Upcoming")) {
            statusLabel.setBackground(new Color(219, 234, 254));
            statusLabel.setForeground(new Color(37, 99, 235));
        } else {
            statusLabel.setBackground(new Color(243, 244, 246));
            statusLabel.setForeground(new Color(107, 114, 128));
        }

        card.add(infoPanel, BorderLayout.WEST);
        card.add(statusLabel, BorderLayout.EAST);

        return card;
    }

    private JPanel createAssignmentsPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(new Color(241, 245, 249));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(241, 245, 249));
        JLabel headerLabel = new JLabel("Evaluation Assignments");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 28));
        headerPanel.add(headerLabel, BorderLayout.WEST);

        JButton createBtn = new JButton("➕ Create Assignment");
        createBtn.setBackground(new Color(249, 115, 22));
        createBtn.setForeground(Color.WHITE);
        createBtn.setFocusPainted(false);
        createBtn.setFont(new Font("Arial", Font.BOLD, 14));
        createBtn.addActionListener(e -> showCreateAssignmentDialog());
        headerPanel.add(createBtn, BorderLayout.EAST);

        panel.add(headerPanel, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(226, 232, 240), 1),
                BorderFactory.createEmptyBorder(30, 30, 30, 30)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(new JLabel("Select Period:"), gbc);
        gbc.gridx = 1;
        JComboBox<String> periodCombo = new JComboBox<>(new String[]{"PER0001 (Dec 2024)", "PER0002 (Jan 2025)"});
        periodCombo.setPreferredSize(new Dimension(250, 30));
        formPanel.add(periodCombo, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(new JLabel("Staff to Evaluate:"), gbc);
        gbc.gridx = 1;
        JComboBox<String> staffCombo = new JComboBox<>(new String[]{"Sok Dara", "Chan Sophea", "Lim Pisey"});
        staffCombo.setPreferredSize(new Dimension(250, 30));
        formPanel.add(staffCombo, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(new JLabel("Add Evaluators:"), gbc);
        gbc.gridx = 1;
        JButton addEvaluatorBtn = new JButton("Select Evaluators");
        addEvaluatorBtn.setBackground(new Color(34, 197, 94));
        addEvaluatorBtn.setForeground(Color.WHITE);
        addEvaluatorBtn.setFocusPainted(false);
        addEvaluatorBtn.setPreferredSize(new Dimension(250, 35));
        addEvaluatorBtn.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Evaluator selection dialog would open here");
        });
        formPanel.add(addEvaluatorBtn, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        JButton saveAssignmentBtn = new JButton("Save Assignment");
        saveAssignmentBtn.setBackground(new Color(59, 130, 246));
        saveAssignmentBtn.setForeground(Color.WHITE);
        saveAssignmentBtn.setFocusPainted(false);
        saveAssignmentBtn.setFont(new Font("Arial", Font.BOLD, 14));
        saveAssignmentBtn.setPreferredSize(new Dimension(200, 40));
        saveAssignmentBtn.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Assignment saved successfully!");
        });
        formPanel.add(saveAssignmentBtn, gbc);

        panel.add(formPanel, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createReportsPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(new Color(241, 245, 249));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel headerLabel = new JLabel("Evaluation Reports");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 28));
        panel.add(headerLabel, BorderLayout.NORTH);

        JPanel reportPanel = new JPanel(new GridLayout(2, 2, 15, 15));
        reportPanel.setBackground(new Color(241, 245, 249));

        reportPanel.add(createReportCard("Best Staff by Department", "View top performers by department"));
        reportPanel.add(createReportCard("Best Staff by Office", "View top performers by office"));
        reportPanel.add(createReportCard("Overall Rankings", "Company-wide rankings"));
        reportPanel.add(createReportCard("Evaluation Statistics", "Detailed statistics"));

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
        viewBtn.addActionListener(e -> JOptionPane.showMessageDialog(this, "Report: " + title));

        card.add(titleLabel);
        card.add(Box.createRigidArea(new Dimension(0, 10)));
        card.add(descLabel);
        card.add(Box.createRigidArea(new Dimension(0, 15)));
        card.add(viewBtn);

        return card;
    }

    private void showAddStaffDialog() {
        JDialog dialog = new JDialog(this, "Add New Staff", true);
        dialog.setSize(400, 500);
        dialog.setLocationRelativeTo(this);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        String[] labels = {"Name:", "Sex:", "Date of Birth:", "Department:", "Office:", "Position:"};
        JComponent[] fields = {
                new JTextField(20),
                new JComboBox<>(new String[]{"Male", "Female"}),
                new JTextField(20),
                new JComboBox<>(new String[]{"IT", "HR", "Sales"}),
                new JComboBox<>(new String[]{"Head Office", "Branch 1", "Branch 2"}),
                new JComboBox<>(new String[]{"Manager", "Team Leader", "Staff"})
        };

        for (int i = 0; i < labels.length; i++) {
            gbc.gridx = 0;
            gbc.gridy = i;
            panel.add(new JLabel(labels[i]), gbc);
            gbc.gridx = 1;
            panel.add(fields[i], gbc);
        }

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton saveBtn = new JButton("Save");
        saveBtn.setBackground(new Color(34, 197, 94));
        saveBtn.setForeground(Color.WHITE);
        saveBtn.setFocusPainted(false);
        saveBtn.addActionListener(e -> {
            JOptionPane.showMessageDialog(dialog, "Staff added successfully!");
            dialog.dispose();
        });

        JButton cancelBtn = new JButton("Cancel");
        cancelBtn.addActionListener(e -> dialog.dispose());

        buttonPanel.add(saveBtn);
        buttonPanel.add(cancelBtn);

        gbc.gridx = 0;
        gbc.gridy = labels.length;
        gbc.gridwidth = 2;
        panel.add(buttonPanel, gbc);

        dialog.add(panel);
        dialog.setVisible(true);
    }

    private void showAddPeriodDialog() {
        JDialog dialog = new JDialog(this, "Create New Period", true);
        dialog.setSize(400, 300);
        dialog.setLocationRelativeTo(this);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        String[] labels = {"Period Code:", "From Date:", "To Date:"};
        JComponent[] fields = {
                new JTextField(20),
                new JTextField(20),
                new JTextField(20)
        };

        for (int i = 0; i < labels.length; i++) {
            gbc.gridx = 0;
            gbc.gridy = i;
            panel.add(new JLabel(labels[i]), gbc);
            gbc.gridx = 1;
            panel.add(fields[i], gbc);
        }

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton saveBtn = new JButton("Create");
        saveBtn.setBackground(new Color(168, 85, 247));
        saveBtn.setForeground(Color.WHITE);
        saveBtn.setFocusPainted(false);
        saveBtn.addActionListener(e -> {
            JOptionPane.showMessageDialog(dialog, "Period created successfully!");
            dialog.dispose();
        });

        JButton cancelBtn = new JButton("Cancel");
        cancelBtn.addActionListener(e -> dialog.dispose());

        buttonPanel.add(saveBtn);
        buttonPanel.add(cancelBtn);

        gbc.gridx = 0;
        gbc.gridy = labels.length;
        gbc.gridwidth = 2;
        panel.add(buttonPanel, gbc);

        dialog.add(panel);
        dialog.setVisible(true);
    }

    private void showCreateAssignmentDialog() {
        JOptionPane.showMessageDialog(this,
                "Assignment creation wizard would open here.\nSelect period, target staff, and evaluators.",
                "Create Assignment",
                JOptionPane.INFORMATION_MESSAGE);
    }
}