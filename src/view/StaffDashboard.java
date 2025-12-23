package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Staff Dashboard - View and complete evaluations, check personal results
 */
public class StaffDashboard extends JFrame {

    private JPanel contentPanel;
    private CardLayout cardLayout;

    public StaffDashboard() {
        setTitle("Staff Evaluation System - Staff Portal");
        setSize(1200, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        JPanel sidebar = createSidebar();
        add(sidebar, BorderLayout.WEST);

        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);

        contentPanel.add(createDashboardPanel(), "dashboard");
        contentPanel.add(createMyEvaluationsPanel(), "evaluations");
        contentPanel.add(createEvaluationFormPanel(), "form");
        contentPanel.add(createMyResultsPanel(), "results");

        add(contentPanel, BorderLayout.CENTER);

        cardLayout.show(contentPanel, "dashboard");
    }

    private JPanel createSidebar() {
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBackground(new Color(22, 101, 52));
        sidebar.setPreferredSize(new Dimension(250, 0));
        sidebar.setBorder(BorderFactory.createEmptyBorder(20, 15, 20, 15));

        JLabel titleLabel = new JLabel("STAFF PORTAL");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        sidebar.add(titleLabel);

        JLabel subtitleLabel = new JLabel("Evaluation System");
        subtitleLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        subtitleLabel.setForeground(new Color(187, 247, 208));
        subtitleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        sidebar.add(subtitleLabel);

        sidebar.add(Box.createRigidArea(new Dimension(0, 30)));

        sidebar.add(createMenuButton("📊 Dashboard", "dashboard"));
        sidebar.add(Box.createRigidArea(new Dimension(0, 10)));
        sidebar.add(createMenuButton("📋 My Evaluations", "evaluations"));
        sidebar.add(Box.createRigidArea(new Dimension(0, 10)));
        sidebar.add(createMenuButton("🏆 My Results", "results"));

        sidebar.add(Box.createVerticalGlue());

        JPanel profilePanel = new JPanel();
        profilePanel.setLayout(new BoxLayout(profilePanel, BoxLayout.Y_AXIS));
        profilePanel.setBackground(new Color(34, 197, 94));
        profilePanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        profilePanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        profilePanel.setMaximumSize(new Dimension(220, 100));

        JLabel nameLabel = new JLabel("Sok Dara");
        nameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel posLabel = new JLabel("Senior Developer");
        posLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        posLabel.setForeground(new Color(220, 252, 231));
        posLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        profilePanel.add(nameLabel);
        profilePanel.add(posLabel);
        sidebar.add(profilePanel);

        sidebar.add(Box.createRigidArea(new Dimension(0, 15)));

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
        button.setBackground(new Color(34, 139, 34));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setFont(new Font("Arial", Font.PLAIN, 14));

        button.addActionListener(e -> cardLayout.show(contentPanel, panelName));

        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                button.setBackground(new Color(21, 128, 61));
            }
            public void mouseExited(MouseEvent e) {
                button.setBackground(new Color(34, 139, 34));
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

        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        titlePanel.setBackground(new Color(241, 245, 249));

        JLabel headerLabel = new JLabel("Welcome, Sok Dara");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 28));
        headerLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel subLabel = new JLabel("Senior Developer - IT Department");
        subLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        subLabel.setForeground(new Color(100, 116, 139));
        subLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        titlePanel.add(headerLabel);
        titlePanel.add(subLabel);
        headerPanel.add(titlePanel, BorderLayout.WEST);

        panel.add(headerPanel, BorderLayout.NORTH);

        JPanel mainContent = new JPanel(new BorderLayout(0, 20));
        mainContent.setBackground(new Color(241, 245, 249));

        JPanel statsPanel = new JPanel(new GridLayout(1, 4, 15, 0));
        statsPanel.setBackground(new Color(241, 245, 249));
        statsPanel.add(createStatCard("My Average Score", "8.5", new Color(34, 197, 94)));
        statsPanel.add(createStatCard("Pending Evaluations", "2", new Color(249, 115, 22)));
        statsPanel.add(createStatCard("Completed", "3", new Color(59, 130, 246)));
        statsPanel.add(createStatCard("Rank in Dept.", "#3", new Color(168, 85, 247)));

        mainContent.add(statsPanel, BorderLayout.NORTH);

        JPanel actionsPanel = new JPanel(new GridLayout(1, 2, 15, 0));
        actionsPanel.setBackground(new Color(241, 245, 249));

        actionsPanel.add(createQuickActionCard(
                "⏳ Pending Evaluations",
                "You have 2 evaluations to complete",
                "Start Evaluation",
                new Color(249, 115, 22),
                e -> cardLayout.show(contentPanel, "evaluations")
        ));

        actionsPanel.add(createQuickActionCard(
                "📊 View My Results",
                "Check your performance and rankings",
                "View Results",
                new Color(59, 130, 246),
                e -> cardLayout.show(contentPanel, "results")
        ));

        mainContent.add(actionsPanel, BorderLayout.CENTER);

        panel.add(mainContent, BorderLayout.CENTER);

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

    private JPanel createQuickActionCard(String title, String description, String buttonText,
                                         Color buttonColor, ActionListener action) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(226, 232, 240), 1),
                BorderFactory.createEmptyBorder(30, 30, 30, 30)
        ));

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel descLabel = new JLabel(description);
        descLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        descLabel.setForeground(new Color(100, 116, 139));
        descLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JButton actionBtn = new JButton(buttonText);
        actionBtn.setBackground(buttonColor);
        actionBtn.setForeground(Color.WHITE);
        actionBtn.setFocusPainted(false);
        actionBtn.setFont(new Font("Arial", Font.BOLD, 14));
        actionBtn.setAlignmentX(Component.LEFT_ALIGNMENT);
        actionBtn.addActionListener(action);

        card.add(titleLabel);
        card.add(Box.createRigidArea(new Dimension(0, 10)));
        card.add(descLabel);
        card.add(Box.createRigidArea(new Dimension(0, 20)));
        card.add(actionBtn);

        return card;
    }

    private JPanel createMyEvaluationsPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(new Color(241, 245, 249));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel headerLabel = new JLabel("My Evaluation Tasks");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 28));
        panel.add(headerLabel, BorderLayout.NORTH);

        JPanel evaluationsPanel = new JPanel();
        evaluationsPanel.setLayout(new BoxLayout(evaluationsPanel, BoxLayout.Y_AXIS));
        evaluationsPanel.setBackground(new Color(241, 245, 249));

        evaluationsPanel.add(createEvaluationCard("Chan Sophea", "HR Manager", "Pending", "2024-12-25", true));
        evaluationsPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        evaluationsPanel.add(createEvaluationCard("Self Evaluation", "Senior Developer", "Pending", "2024-12-25", true));
        evaluationsPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        evaluationsPanel.add(createEvaluationCard("Lim Pisey", "Sales Lead", "Completed", "2024-12-20", false));
        evaluationsPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        evaluationsPanel.add(createEvaluationCard("Rath Vanna", "Team Leader", "Completed", "2024-12-18", false));

        JScrollPane scrollPane = new JScrollPane(evaluationsPanel);
        scrollPane.setBorder(null);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createEvaluationCard(String staffName, String position, String status,
                                        String dueDate, boolean isPending) {
        JPanel card = new JPanel(new BorderLayout(15, 10));
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(226, 232, 240), 1),
                BorderFactory.createEmptyBorder(20, 25, 20, 25)
        ));
        card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBackground(Color.WHITE);

        JLabel nameLabel = new JLabel(staffName);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        JLabel posLabel = new JLabel(position);
        posLabel.setFont(new Font("Arial", Font.PLAIN, 13));
        posLabel.setForeground(new Color(100, 116, 139));
        JLabel dateLabel = new JLabel("Due: " + dueDate);
        dateLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        dateLabel.setForeground(new Color(100, 116, 139));

        infoPanel.add(nameLabel);
        infoPanel.add(posLabel);
        infoPanel.add(dateLabel);

        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        rightPanel.setBackground(Color.WHITE);

        JLabel statusLabel = new JLabel(status);
        statusLabel.setFont(new Font("Arial", Font.BOLD, 12));
        statusLabel.setOpaque(true);
        statusLabel.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));

        if (isPending) {
            statusLabel.setBackground(new Color(254, 243, 199));
            statusLabel.setForeground(new Color(161, 98, 7));

            JButton startBtn = new JButton("Start Evaluation");
            startBtn.setBackground(new Color(34, 197, 94));
            startBtn.setForeground(Color.WHITE);
            startBtn.setFocusPainted(false);
            startBtn.setFont(new Font("Arial", Font.BOLD, 13));
            startBtn.addActionListener(e -> cardLayout.show(contentPanel, "form"));
            rightPanel.add(startBtn);
        } else {
            statusLabel.setBackground(new Color(220, 252, 231));
            statusLabel.setForeground(new Color(22, 163, 74));

            JButton viewBtn = new JButton("View Details");
            viewBtn.setBackground(new Color(59, 130, 246));
            viewBtn.setForeground(Color.WHITE);
            viewBtn.setFocusPainted(false);
            viewBtn.setFont(new Font("Arial", Font.BOLD, 13));
            rightPanel.add(viewBtn);
        }

        rightPanel.add(statusLabel);

        card.add(infoPanel, BorderLayout.WEST);
        card.add(rightPanel, BorderLayout.EAST);

        return card;
    }

    private JPanel createEvaluationFormPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(new Color(241, 245, 249));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(241, 245, 249));

        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        titlePanel.setBackground(new Color(241, 245, 249));

        JLabel headerLabel = new JLabel("Evaluation Form");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 28));
        headerLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel subLabel = new JLabel("Evaluating: Chan Sophea - HR Manager");
        subLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        subLabel.setForeground(new Color(100, 116, 139));
        subLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        titlePanel.add(headerLabel);
        titlePanel.add(subLabel);
        headerPanel.add(titlePanel, BorderLayout.WEST);

        JButton backBtn = new JButton("← Back");
        backBtn.setBackground(new Color(100, 116, 139));
        backBtn.setForeground(Color.WHITE);
        backBtn.setFocusPainted(false);
        backBtn.addActionListener(e -> cardLayout.show(contentPanel, "evaluations"));
        headerPanel.add(backBtn, BorderLayout.EAST);

        panel.add(headerPanel, BorderLayout.NORTH);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBackground(new Color(241, 245, 249));

        String[] criteria = {
                "Work Quality",
                "Communication Skills",
                "Problem Solving",
                "Team Collaboration",
                "Time Management"
        };

        for (String criterion : criteria) {
            formPanel.add(createCriteriaPanel(criterion));
            formPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        }

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(new Color(241, 245, 249));

        JButton submitBtn = new JButton("Submit Evaluation");
        submitBtn.setBackground(new Color(34, 197, 94));
        submitBtn.setForeground(Color.WHITE);
        submitBtn.setFocusPainted(false);
        submitBtn.setFont(new Font("Arial", Font.BOLD, 16));
        submitBtn.setPreferredSize(new Dimension(200, 45));
        submitBtn.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Evaluation submitted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            cardLayout.show(contentPanel, "evaluations");
        });

        buttonPanel.add(submitBtn);
        formPanel.add(buttonPanel);

        JScrollPane scrollPane = new JScrollPane(formPanel);
        scrollPane.setBorder(null);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createCriteriaPanel(String criteriaName) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(226, 232, 240), 1),
                BorderFactory.createEmptyBorder(20, 25, 20, 25)
        ));
        panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 120));

        JLabel nameLabel = new JLabel(criteriaName);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        nameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JPanel scorePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        scorePanel.setBackground(Color.WHITE);
        scorePanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel scoreLabel = new JLabel("Score (1-10):");
        scoreLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        SpinnerModel model = new SpinnerNumberModel(5, 1, 10, 1);
        JSpinner scoreSpinner = new JSpinner(model);
        scoreSpinner.setFont(new Font("Arial", Font.BOLD, 14));
        ((JSpinner.DefaultEditor) scoreSpinner.getEditor()).getTextField().setColumns(3);

        scorePanel.add(scoreLabel);
        scorePanel.add(scoreSpinner);

        panel.add(nameLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(scorePanel);

        return panel;
    }

    private JPanel createMyResultsPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(new Color(241, 245, 249));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel headerLabel = new JLabel("My Evaluation Results");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 28));
        panel.add(headerLabel, BorderLayout.NORTH);

        JPanel resultsPanel = new JPanel(new GridLayout(2, 1, 0, 15));
        resultsPanel.setBackground(new Color(241, 245, 249));

        JPanel summaryCard = new JPanel();
        summaryCard.setLayout(new BoxLayout(summaryCard, BoxLayout.Y_AXIS));
        summaryCard.setBackground(Color.WHITE);
        summaryCard.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(226, 232, 240), 1),
                BorderFactory.createEmptyBorder(25, 30, 25, 30)
        ));

        JLabel summaryTitle = new JLabel("Overall Performance - December 2024");
        summaryTitle.setFont(new Font("Arial", Font.BOLD, 18));
        summaryTitle.setAlignmentX(Component.LEFT_ALIGNMENT);

        JPanel scoresGrid = new JPanel(new GridLayout(2, 3, 20, 15));
        scoresGrid.setBackground(Color.WHITE);
        scoresGrid.setAlignmentX(Component.LEFT_ALIGNMENT);
        scoresGrid.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

        scoresGrid.add(createScoreItem("Work Quality", "8.5"));
        scoresGrid.add(createScoreItem("Communication", "9.0"));
        scoresGrid.add(createScoreItem("Problem Solving", "8.0"));
        scoresGrid.add(createScoreItem("Team Collaboration", "8.8"));
        scoresGrid.add(createScoreItem("Time Management", "7.5"));
        scoresGrid.add(createScoreItem("Average Score", "8.36"));

        summaryCard.add(summaryTitle);
        summaryCard.add(scoresGrid);

        JPanel rankingsCard = new JPanel();
        rankingsCard.setLayout(new BoxLayout(rankingsCard, BoxLayout.Y_AXIS));
        rankingsCard.setBackground(Color.WHITE);
        rankingsCard.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(226, 232, 240), 1),
                BorderFactory.createEmptyBorder(25, 30, 25, 30)
        ));

        JLabel rankTitle = new JLabel("My Rankings");
        rankTitle.setFont(new Font("Arial", Font.BOLD, 18));
        rankTitle.setAlignmentX(Component.LEFT_ALIGNMENT);

        JPanel rankGrid = new JPanel(new GridLayout(1, 2, 20, 0));
        rankGrid.setBackground(Color.WHITE);
        rankGrid.setAlignmentX(Component.LEFT_ALIGNMENT);
        rankGrid.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

        rankGrid.add(createRankItem("Department Rank", "#3", "out of 12"));
        rankGrid.add(createRankItem("Office Rank", "#5", "out of 25"));

        rankingsCard.add(rankTitle);
        rankingsCard.add(rankGrid);

        resultsPanel.add(summaryCard);
        resultsPanel.add(rankingsCard);

        panel.add(resultsPanel, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createScoreItem(String label, String score) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(249, 250, 251));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel scoreLabel = new JLabel(score);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 24));
        scoreLabel.setForeground(new Color(34, 197, 94));
        scoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel labelText = new JLabel(label);
        labelText.setFont(new Font("Arial", Font.PLAIN, 13));
        labelText.setForeground(new Color(100, 116, 139));
        labelText.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(scoreLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 5)));
        panel.add(labelText);

        return panel;
    }

    private JPanel createRankItem(String label, String rank, String total) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(249, 250, 251));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel rankLabel = new JLabel(rank);
        rankLabel.setFont(new Font("Arial", Font.BOLD, 32));
        rankLabel.setForeground(new Color(168, 85, 247));
        rankLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel labelText = new JLabel(label);
        labelText.setFont(new Font("Arial", Font.BOLD, 14));
        labelText.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel totalText = new JLabel(total);
        totalText.setFont(new Font("Arial", Font.PLAIN, 12));
        totalText.setForeground(new Color(100, 116, 139));
        totalText.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(rankLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 5)));
        panel.add(labelText);
        panel.add(totalText);

        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            StaffDashboard dashboard = new StaffDashboard();
            dashboard.setVisible(true);
        });
    }
}
