import javax.swing.*;
import java.awt.*;

public class Main {

    private static JFrame dashboardFrame;

    // =========================================================
    // APPLICATION START
    // =========================================================

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            // =================================================
            // LOGIN OPENS FIRST
            // =================================================

            new Login();

        });
    }

    // =========================================================
    // START DASHBOARD
    // =========================================================

    public static void startDashboard() {

        SwingUtilities.invokeLater(() -> {

            createDashboard();

        });
    }

    // =========================================================
    // CREATE DASHBOARD
    // =========================================================

    private static void createDashboard() {

        // Prevent duplicate dashboard

        if (
                dashboardFrame != null
                && dashboardFrame.isDisplayable()
        ) {

            dashboardFrame.toFront();

            dashboardFrame.requestFocus();

            return;
        }

        dashboardFrame =
                new JFrame(
                        "J.A.R.V.I.S."
                );

        dashboardFrame.setSize(
                1000,
                720
        );

        dashboardFrame.setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );

        dashboardFrame.setLocationRelativeTo(null);

        dashboardFrame.setLayout(
                new BorderLayout()
        );

        // =====================================================
        // COLORS
        // =====================================================

        Color background =
                new Color(
                        7,
                        15,
                        25
                );

        Color panelBackground =
                new Color(
                        12,
                        27,
                        43
                );

        Color buttonBackground =
                new Color(
                        20,
                        50,
                        75
                );

        Color cyan =
                new Color(
                        0,
                        220,
                        255
                );

        Color green =
                new Color(
                        50,
                        220,
                        120
                );

        Color white =
                new Color(
                        240,
                        245,
                        250
                );

        // =====================================================
        // HEADER
        // =====================================================

        JPanel headerPanel =
                new JPanel(
                        new BorderLayout()
                );

        headerPanel.setBackground(
                background
        );

        headerPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        20,
                        30,
                        15,
                        30
                )
        );

        JLabel title =
                new JLabel(
                        "J.A.R.V.I.S.",
                        SwingConstants.CENTER
                );

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        42
                )
        );

        title.setForeground(cyan);

        // =====================================================
        // CURRENT USER
        // =====================================================

        String currentName =
                CurrentUser.getName();

        if (
                currentName == null
                || currentName.isEmpty()
        ) {

            currentName = "USER";
        }

        JLabel subtitle =
                new JLabel(
                        "JUST A RATHER VERY INTELLIGENT SYSTEM"
                                + "  •  WELCOME "
                                + currentName.toUpperCase(),

                        SwingConstants.CENTER
                );

        subtitle.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        subtitle.setForeground(white);

        JPanel titlePanel =
                new JPanel(
                        new BorderLayout()
                );

        titlePanel.setBackground(
                background
        );

        titlePanel.add(
                title,
                BorderLayout.CENTER
        );

        titlePanel.add(
                subtitle,
                BorderLayout.SOUTH
        );

        headerPanel.add(
                titlePanel,
                BorderLayout.CENTER
        );

        // =====================================================
        // STATUS
        // =====================================================

        JLabel status =
                new JLabel(
                        "●  SYSTEM STATUS: ONLINE",
                        SwingConstants.CENTER
                );

        status.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        15
                )
        );

        status.setForeground(green);

        headerPanel.add(
                status,
                BorderLayout.SOUTH
        );

        dashboardFrame.add(
                headerPanel,
                BorderLayout.NORTH
        );

        // =====================================================
        // MODULE PANEL
        // =====================================================

        JPanel modulePanel =
                new JPanel(
                        new GridLayout(
                                3,
                                3,
                                18,
                                18
                        )
                );

        modulePanel.setBackground(
                background
        );

        modulePanel.setBorder(
                BorderFactory.createEmptyBorder(
                        25,
                        50,
                        25,
                        50
                )
        );

        // =====================================================
        // MODULE BUTTONS
        // =====================================================

        JButton calculatorButton =
                createModuleButton(
                        "CALCULATOR",
                        buttonBackground,
                        cyan
                );

        JButton notesButton =
                createModuleButton(
                        "NOTES",
                        buttonBackground,
                        cyan
                );

        JButton timerButton =
                createModuleButton(
                        "TIMER",
                        buttonBackground,
                        cyan
                );

        JButton quizButton =
                createModuleButton(
                        "QUIZ",
                        buttonBackground,
                        cyan
                );

        JButton gameButton =
                createModuleButton(
                        "DINO GAME",
                        buttonBackground,
                        cyan
                );

        JButton systemButton =
                createModuleButton(
                        "SYSTEM INFO",
                        buttonBackground,
                        cyan
                );

        JButton plannerButton =
                createModuleButton(
                        "STUDY PLANNER",
                        buttonBackground,
                        cyan
                );

        JButton progressButton =
                createModuleButton(
                        "DAILY PROGRESS",
                        buttonBackground,
                        cyan
                );

        JButton settingsButton =
                createModuleButton(
                        "SETTINGS",
                        buttonBackground,
                        cyan
                );

        // =====================================================
        // ACTIONS
        // =====================================================

        calculatorButton.addActionListener(
                e -> openCalculator()
        );

        notesButton.addActionListener(
                e -> openNotes()
        );

        timerButton.addActionListener(
                e -> openTimer()
        );

        quizButton.addActionListener(
                e -> openQuiz()
        );

        gameButton.addActionListener(
                e -> openGame()
        );

        systemButton.addActionListener(
                e -> openSystemInfo()
        );

        plannerButton.addActionListener(
                e -> openPlanner()
        );

        progressButton.addActionListener(
                e -> openDailyProgress()
        );

        settingsButton.addActionListener(
                e -> openSettings()
        );

        // =====================================================
        // ADD BUTTONS
        // =====================================================

        modulePanel.add(calculatorButton);
        modulePanel.add(notesButton);
        modulePanel.add(timerButton);

        modulePanel.add(quizButton);
        modulePanel.add(gameButton);
        modulePanel.add(systemButton);

        modulePanel.add(plannerButton);
        modulePanel.add(progressButton);
        modulePanel.add(settingsButton);

        dashboardFrame.add(
                modulePanel,
                BorderLayout.CENTER
        );

        // =====================================================
        // FOOTER
        // =====================================================

        JLabel footer =
                new JLabel(
                        "J.A.R.V.I.S. SYSTEM READY",
                        SwingConstants.CENTER
                );

        footer.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        13
                )
        );

        footer.setForeground(green);

        footer.setBackground(
                panelBackground
        );

        footer.setOpaque(true);

        footer.setBorder(
                BorderFactory.createEmptyBorder(
                        10,
                        10,
                        12,
                        10
                )
        );

        dashboardFrame.add(
                footer,
                BorderLayout.SOUTH
        );

        // =====================================================
        // SHOW DASHBOARD
        // =====================================================

        dashboardFrame.setVisible(true);
    }

    // =========================================================
    // MODULES
    // =========================================================

    private static void openCalculator() {

        new Calculator();
    }

    private static void openNotes() {

        new Notes();
    }

    private static void openTimer() {

        new Timer();
    }

    private static void openQuiz() {

        new Quiz();
    }

    private static void openGame() {

        new Game();
    }

    private static void openSystemInfo() {

        new SystemInfo();
    }

    private static void openPlanner() {

        new Planner();
    }

    private static void openDailyProgress() {

        new DailyProgress();
    }

    private static void openSettings() {

        new Settings();
    }

    // =========================================================
    // MODULE BUTTON
    // =========================================================

    private static JButton createModuleButton(
            String text,
            Color background,
            Color borderColor
    ) {

        JButton button =
                new JButton(text);

        button.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        15
                )
        );

        button.setForeground(
                Color.WHITE
        );

        button.setBackground(
                background
        );

        button.setFocusPainted(false);

        button.setOpaque(true);

        button.setBorder(
                BorderFactory.createCompoundBorder(

                        BorderFactory.createLineBorder(
                                borderColor,
                                1
                        ),

                        BorderFactory.createEmptyBorder(
                                10,
                                10,
                                10,
                                10
                        )
                )
        );

        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        return button;
    }
}