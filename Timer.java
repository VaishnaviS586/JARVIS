import javax.swing.*;
import java.awt.*;

public class Timer extends JFrame {

    // =========================================================
    // COLORS
    // =========================================================

    private final Color BACKGROUND =
            new Color(7, 15, 25);

    private final Color PANEL =
            new Color(15, 30, 48);

    private final Color BUTTON =
            new Color(20, 55, 80);

    private final Color CYAN =
            new Color(0, 220, 255);

    private final Color GREEN =
            new Color(50, 220, 120);

    private final Color WHITE =
            new Color(240, 245, 250);

    // =========================================================
    // COMPONENTS
    // =========================================================

    private JLabel timeLabel;

    private JLabel statusLabel;

    private JSpinner hourSpinner;

    private JSpinner minuteSpinner;

    private JSpinner secondSpinner;

    private JProgressBar progressBar;

    private javax.swing.Timer countdownTimer;

    // =========================================================
    // TIMER VARIABLES
    // =========================================================

    private int totalSeconds = 0;

    private int remainingSeconds = 0;

    private boolean running = false;

    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public Timer() {

        setTitle(
                "J.A.R.V.I.S. - Timer"
        );

        setSize(
                650,
                600
        );

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        setLocationRelativeTo(null);

        setResizable(false);

        createGUI();

        setVisible(true);
    }

    // =========================================================
    // CREATE GUI
    // =========================================================

    private void createGUI() {

        getContentPane().setBackground(
                BACKGROUND
        );

        setLayout(
                new BorderLayout()
        );

        // =====================================================
        // HEADER
        // =====================================================

        JPanel headerPanel =
                new JPanel(
                        new BorderLayout()
                );

        headerPanel.setBackground(
                BACKGROUND
        );

        headerPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        20,
                        20,
                        15,
                        20
                )
        );

        JLabel title =
                new JLabel(
                        "J.A.R.V.I.S. TIMER",
                        SwingConstants.CENTER
                );

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        30
                )
        );

        title.setForeground(
                CYAN
        );

        JLabel subtitle =
                new JLabel(
                        "FOCUS • STUDY • ACHIEVE",
                        SwingConstants.CENTER
                );

        subtitle.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        13
                )
        );

        subtitle.setForeground(
                GREEN
        );

        headerPanel.add(
                title,
                BorderLayout.CENTER
        );

        headerPanel.add(
                subtitle,
                BorderLayout.SOUTH
        );

        add(
                headerPanel,
                BorderLayout.NORTH
        );

        // =====================================================
        // CENTER PANEL
        // =====================================================

        JPanel centerPanel =
                new JPanel(
                        new BorderLayout(
                                10,
                                15
                        )
                );

        centerPanel.setBackground(
                BACKGROUND
        );

        centerPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        10,
                        40,
                        10,
                        40
                )
        );

        // =====================================================
        // TIME DISPLAY
        // =====================================================

        timeLabel =
                new JLabel(
                        "00:00:00",
                        SwingConstants.CENTER
                );

        timeLabel.setFont(
                new Font(
                        "Monospaced",
                        Font.BOLD,
                        65
                )
        );

        timeLabel.setForeground(
                CYAN
        );

        timeLabel.setBorder(
                BorderFactory.createCompoundBorder(

                        BorderFactory.createLineBorder(
                                CYAN,
                                2
                        ),

                        BorderFactory.createEmptyBorder(
                                20,
                                10,
                                20,
                                10
                        )
                )
        );

        centerPanel.add(
                timeLabel,
                BorderLayout.NORTH
        );

        // =====================================================
        // INPUT PANEL
        // =====================================================

        JPanel inputPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.CENTER,
                                15,
                                15
                        )
                );

        inputPanel.setBackground(
                PANEL
        );

        inputPanel.setBorder(
                BorderFactory.createTitledBorder(

                        BorderFactory.createLineBorder(
                                CYAN
                        ),

                        "SET TIMER",

                        javax.swing.border.TitledBorder.CENTER,

                        javax.swing.border.TitledBorder.TOP,

                        new Font(
                                "Arial",
                                Font.BOLD,
                                13
                        ),

                        CYAN
                )
        );

        // HOURS

        JLabel hourLabel =
                createInputLabel(
                        "Hours"
                );

        hourSpinner =
                createSpinner(
                        0,
                        0,
                        23
                );

        // MINUTES

        JLabel minuteLabel =
                createInputLabel(
                        "Minutes"
                );

        minuteSpinner =
                createSpinner(
                        0,
                        0,
                        59
                );

        // SECONDS

        JLabel secondLabel =
                createInputLabel(
                        "Seconds"
                );

        secondSpinner =
                createSpinner(
                        0,
                        0,
                        59
                );

        inputPanel.add(
                hourLabel
        );

        inputPanel.add(
                hourSpinner
        );

        inputPanel.add(
                minuteLabel
        );

        inputPanel.add(
                minuteSpinner
        );

        inputPanel.add(
                secondLabel
        );

        inputPanel.add(
                secondSpinner
        );

        centerPanel.add(
                inputPanel,
                BorderLayout.CENTER
        );

        // =====================================================
        // PROGRESS BAR
        // =====================================================

        progressBar =
                new JProgressBar(
                        0,
                        100
                );

        progressBar.setValue(
                0
        );

        progressBar.setStringPainted(
                true
        );

        progressBar.setString(
                "READY"
        );

        progressBar.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        12
                )
        );

        progressBar.setForeground(
                CYAN
        );

        progressBar.setBackground(
                new Color(
                        20,
                        30,
                        40
                )
        );

        centerPanel.add(
                progressBar,
                BorderLayout.SOUTH
        );

        add(
                centerPanel,
                BorderLayout.CENTER
        );

        // =====================================================
        // STATUS
        // =====================================================

        statusLabel =
                new JLabel(
                        "SYSTEM READY",
                        SwingConstants.CENTER
                );

        statusLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        13
                )
        );

        statusLabel.setForeground(
                GREEN
        );

        // =====================================================
        // BUTTON PANEL
        // =====================================================

        JPanel bottomPanel =
                new JPanel(
                        new BorderLayout()
                );

        bottomPanel.setBackground(
                BACKGROUND
        );

        JPanel buttonPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.CENTER,
                                12,
                                10
                        )
                );

        buttonPanel.setBackground(
                BACKGROUND
        );

        JButton startButton =
                createButton(
                        "START"
                );

        JButton pauseButton =
                createButton(
                        "PAUSE"
                );

        JButton resetButton =
                createButton(
                        "RESET"
                );

        JButton closeButton =
                createButton(
                        "CLOSE"
                );

        buttonPanel.add(
                startButton
        );

        buttonPanel.add(
                pauseButton
        );

        buttonPanel.add(
                resetButton
        );

        buttonPanel.add(
                closeButton
        );

        bottomPanel.add(
                statusLabel,
                BorderLayout.NORTH
        );

        bottomPanel.add(
                buttonPanel,
                BorderLayout.CENTER
        );

        add(
                bottomPanel,
                BorderLayout.SOUTH
        );

        // =====================================================
        // BUTTON ACTIONS
        // =====================================================

        startButton.addActionListener(
                e -> startTimer()
        );

        pauseButton.addActionListener(
                e -> pauseTimer()
        );

        resetButton.addActionListener(
                e -> resetTimer()
        );

        closeButton.addActionListener(
                e -> {

                    if (countdownTimer != null) {

                        countdownTimer.stop();

                    }

                    dispose();
                }
        );

        // =====================================================
        // COUNTDOWN TIMER
        // =====================================================

        countdownTimer =
                new javax.swing.Timer(
                        1000,
                        e -> updateTimer()
                );
    }

    // =========================================================
    // CREATE INPUT LABEL
    // =========================================================

    private JLabel createInputLabel(
            String text
    ) {

        JLabel label =
                new JLabel(
                        text
                );

        label.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        13
                )
        );

        label.setForeground(
                WHITE
        );

        return label;
    }

    // =========================================================
    // CREATE SPINNER
    // =========================================================

    private JSpinner createSpinner(
            int value,
            int minimum,
            int maximum
    ) {

        JSpinner spinner =
                new JSpinner(
                        new SpinnerNumberModel(
                                value,
                                minimum,
                                maximum,
                                1
                        )
                );

        spinner.setPreferredSize(
                new Dimension(
                        65,
                        30
                )
        );

        JComponent editor =
                spinner.getEditor();

        if (editor instanceof JSpinner.DefaultEditor) {

            JTextField textField =
                    ((JSpinner.DefaultEditor)
                            editor)
                            .getTextField();

            textField.setHorizontalAlignment(
                    JTextField.CENTER
            );

            textField.setFont(
                    new Font(
                            "Arial",
                            Font.BOLD,
                            14
                    )
            );
        }

        return spinner;
    }

    // =========================================================
    // CREATE BUTTON
    // =========================================================

    private JButton createButton(
            String text
    ) {

        JButton button =
                new JButton(
                        text
                );

        button.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        13
                )
        );

        button.setForeground(
                WHITE
        );

        button.setBackground(
                BUTTON
        );

        button.setFocusPainted(
                false
        );

        button.setOpaque(
                true
        );

        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        button.setBorder(
                BorderFactory.createCompoundBorder(

                        BorderFactory.createLineBorder(
                                CYAN
                        ),

                        BorderFactory.createEmptyBorder(
                                8,
                                15,
                                8,
                                15
                        )
                )
        );

        return button;
    }

    // =========================================================
    // START TIMER
    // =========================================================

    private void startTimer() {

        // If timer is already running
        if (running) {

            return;
        }

        // If timer has not been initialized
        if (remainingSeconds <= 0) {

            int hours =
                    (Integer)
                            hourSpinner
                                    .getValue();

            int minutes =
                    (Integer)
                            minuteSpinner
                                    .getValue();

            int seconds =
                    (Integer)
                            secondSpinner
                                    .getValue();

            totalSeconds =
                    (hours * 3600)
                    + (minutes * 60)
                    + seconds;

            remainingSeconds =
                    totalSeconds;
        }

        // =====================================================
        // VALIDATE TIMER
        // =====================================================

        if (remainingSeconds <= 0) {

            JOptionPane.showMessageDialog(
                    this,

                    "Please set a timer first.",

                    "J.A.R.V.I.S. TIMER",

                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        running = true;

        statusLabel.setText(
                "TIMER RUNNING..."
        );

        statusLabel.setForeground(
                GREEN
        );

        progressBar.setString(
                "RUNNING"
        );

        countdownTimer.start();
    }

    // =========================================================
    // UPDATE TIMER
    // =========================================================

    private void updateTimer() {

        if (remainingSeconds > 0) {

            remainingSeconds--;

            updateDisplay();

            updateProgress();

        }

        if (remainingSeconds <= 0) {

            countdownTimer.stop();

            running = false;

            timeLabel.setText(
                    "00:00:00"
            );

            progressBar.setValue(
                    100
            );

            progressBar.setString(
                    "COMPLETED"
            );

            statusLabel.setText(
                    "TIMER COMPLETED!"
            );

            statusLabel.setForeground(
                    GREEN
            );

            showCompletionMessage();
        }
    }

    // =========================================================
    // UPDATE DISPLAY
    // =========================================================

    private void updateDisplay() {

        int hours =
                remainingSeconds / 3600;

        int minutes =
                (remainingSeconds % 3600)
                        / 60;

        int seconds =
                remainingSeconds % 60;

        String time =
                String.format(
                        "%02d:%02d:%02d",
                        hours,
                        minutes,
                        seconds
                );

        timeLabel.setText(
                time
        );
    }

    // =========================================================
    // UPDATE PROGRESS
    // =========================================================

    private void updateProgress() {

        if (totalSeconds <= 0) {

            return;
        }

        int elapsed =
                totalSeconds
                - remainingSeconds;

        int progress =
                (int)
                        (
                                (elapsed
                                * 100.0)
                                / totalSeconds
                        );

        progressBar.setValue(
                progress
        );
    }

    // =========================================================
    // PAUSE TIMER
    // =========================================================

    private void pauseTimer() {

        if (!running) {

            return;
        }

        countdownTimer.stop();

        running = false;

        statusLabel.setText(
                "TIMER PAUSED"
        );

        statusLabel.setForeground(
                CYAN
        );

        progressBar.setString(
                "PAUSED"
        );
    }

    // =========================================================
    // RESET TIMER
    // =========================================================

    private void resetTimer() {

        if (countdownTimer != null) {

            countdownTimer.stop();
        }

        running = false;

        totalSeconds = 0;

        remainingSeconds = 0;

        timeLabel.setText(
                "00:00:00"
        );

        progressBar.setValue(
                0
        );

        progressBar.setString(
                "READY"
        );

        statusLabel.setText(
                "SYSTEM READY"
        );

        statusLabel.setForeground(
                GREEN
        );

        hourSpinner.setValue(
                0
        );

        minuteSpinner.setValue(
                0
        );

        secondSpinner.setValue(
                0
        );
    }

    // =========================================================
    // COMPLETION MESSAGE
    // =========================================================

    private void showCompletionMessage() {

        Toolkit
                .getDefaultToolkit()
                .beep();

        JOptionPane.showMessageDialog(
                this,

                "⏰ Time is up!\n\n"
                        + "J.A.R.V.I.S. TIMER\n"
                        + "SESSION COMPLETED.",

                "J.A.R.V.I.S. TIMER",

                JOptionPane.INFORMATION_MESSAGE
        );
    }
}
