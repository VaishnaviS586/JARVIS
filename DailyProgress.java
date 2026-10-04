import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DailyProgress extends JFrame {

    // =========================================================
    // COLORS
    // =========================================================

    private final Color BACKGROUND =
            new Color(7, 15, 25);

    private final Color PANEL =
            new Color(15, 30, 48);

    private final Color FIELD =
            new Color(10, 22, 35);

    private final Color CYAN =
            new Color(0, 220, 255);

    private final Color GREEN =
            new Color(50, 220, 120);

    private final Color WHITE =
            new Color(240, 245, 250);

    private final Color BUTTON =
            new Color(20, 55, 80);

    // =========================================================
    // FILE
    // =========================================================

    private final String PROGRESS_FILE =
            "DailyProgress.txt";

    // =========================================================
    // COMPONENTS
    // =========================================================

    private JTextField subjectField;

    private JTextField hoursField;

    private JSlider progressSlider;

    private JLabel percentageLabel;

    private DefaultListModel<String> listModel;

    private JList<String> progressList;

    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public DailyProgress() {

        setTitle(
                "J.A.R.V.I.S. - Daily Progress"
        );

        setSize(
                800,
                680
        );

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        setLocationRelativeTo(null);

        setResizable(false);

        createGUI();

        loadUserProgress();

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
                        "J.A.R.V.I.S. DAILY PROGRESS",
                        SwingConstants.CENTER
                );

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        29
                )
        );

        title.setForeground(
                CYAN
        );

        JLabel subtitle =
                new JLabel(
                        getUserDisplayText(),
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
                                15,
                                15
                        )
                );

        centerPanel.setBackground(
                BACKGROUND
        );

        centerPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        5,
                        25,
                        5,
                        25
                )
        );

        // =====================================================
        // INPUT PANEL
        // =====================================================

        JPanel inputPanel =
                new JPanel(
                        new GridBagLayout()
                );

        inputPanel.setBackground(
                PANEL
        );

        inputPanel.setBorder(
                BorderFactory.createCompoundBorder(

                        BorderFactory.createLineBorder(
                                CYAN
                        ),

                        BorderFactory.createEmptyBorder(
                                15,
                                20,
                                15,
                                20
                        )
                )
        );

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.insets =
                new Insets(
                        6,
                        6,
                        6,
                        6
                );

        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        gbc.weightx = 1;

        // =====================================================
        // SUBJECT
        // =====================================================

        JLabel subjectLabel =
                createLabel(
                        "Subject"
                );

        subjectField =
                createTextField();

        addInput(
                inputPanel,
                gbc,
                0,
                subjectLabel,
                subjectField
        );

        // =====================================================
        // HOURS
        // =====================================================

        JLabel hoursLabel =
                createLabel(
                        "Study Hours"
                );

        hoursField =
                createTextField();

        addInput(
                inputPanel,
                gbc,
                1,
                hoursLabel,
                hoursField
        );

        // =====================================================
        // PROGRESS
        // =====================================================

        JLabel progressLabel =
                createLabel(
                        "Progress"
                );

        progressSlider =
                new JSlider(
                        0,
                        100,
                        0
                );

        progressSlider.setMajorTickSpacing(
                25
        );

        progressSlider.setMinorTickSpacing(
                5
        );

        progressSlider.setPaintTicks(
                true
        );

        progressSlider.setPaintLabels(
                true
        );

        progressSlider.setBackground(
                PANEL
        );

        progressSlider.setForeground(
                CYAN
        );

        percentageLabel =
                new JLabel(
                        "0%"
                );

        percentageLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        percentageLabel.setForeground(
                GREEN
        );

        JPanel progressPanel =
                new JPanel(
                        new BorderLayout(
                                10,
                                0
                        )
                );

        progressPanel.setBackground(
                PANEL
        );

        progressPanel.add(
                progressSlider,
                BorderLayout.CENTER
        );

        progressPanel.add(
                percentageLabel,
                BorderLayout.EAST
        );

        addInput(
                inputPanel,
                gbc,
                2,
                progressLabel,
                progressPanel
        );

        centerPanel.add(
                inputPanel,
                BorderLayout.NORTH
        );

        // =====================================================
        // PROGRESS LIST
        // =====================================================

        JPanel listPanel =
                new JPanel(
                        new BorderLayout()
                );

        listPanel.setBackground(
                BACKGROUND
        );

        listModel =
                new DefaultListModel<>();

        progressList =
                new JList<>(
                        listModel
                );

        progressList.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        progressList.setForeground(
                WHITE
        );

        progressList.setBackground(
                FIELD
        );

        progressList.setSelectionBackground(
                new Color(
                        25,
                        80,
                        105
                )
        );

        progressList.setSelectionForeground(
                WHITE
        );

        progressList.setFixedCellHeight(
                38
        );

        JScrollPane scrollPane =
                new JScrollPane(
                        progressList
                );

        scrollPane.setBorder(
                BorderFactory.createTitledBorder(

                        BorderFactory.createLineBorder(
                                CYAN
                        ),

                        "MY DAILY PROGRESS",

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

        listPanel.add(
                scrollPane,
                BorderLayout.CENTER
        );

        centerPanel.add(
                listPanel,
                BorderLayout.CENTER
        );

        add(
                centerPanel,
                BorderLayout.CENTER
        );

        // =====================================================
        // BUTTON PANEL
        // =====================================================

        JPanel buttonPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.CENTER,
                                10,
                                10
                        )
                );

        buttonPanel.setBackground(
                BACKGROUND
        );

        JButton saveButton =
                createButton(
                        "SAVE PROGRESS"
                );

        JButton deleteButton =
                createButton(
                        "DELETE"
                );

        JButton clearButton =
                createButton(
                        "CLEAR"
                );

        JButton closeButton =
                createButton(
                        "CLOSE"
                );

        buttonPanel.add(
                saveButton
        );

        buttonPanel.add(
                deleteButton
        );

        buttonPanel.add(
                clearButton
        );

        buttonPanel.add(
                closeButton
        );

        add(
                buttonPanel,
                BorderLayout.SOUTH
        );

        // =====================================================
        // SLIDER
        // =====================================================

        progressSlider.addChangeListener(
                e -> percentageLabel.setText(
                        progressSlider.getValue()
                                + "%"
                )
        );

        // =====================================================
        // BUTTON ACTIONS
        // =====================================================

        saveButton.addActionListener(
                e -> saveProgress()
        );

        deleteButton.addActionListener(
                e -> deleteProgress()
        );

        clearButton.addActionListener(
                e -> clearFields()
        );

        closeButton.addActionListener(
                e -> dispose()
        );
    }

    // =========================================================
    // USER DISPLAY
    // =========================================================

    private String getUserDisplayText() {

        if (
                CurrentUser.isLoggedIn()
        ) {

            return "STUDY TRACKER • "
                    + CurrentUser.getName()
                    + " ("
                    + CurrentUser.getUsername()
                    + ")";
        }

        return "STUDY TRACKER • NO ACTIVE USER";
    }

    // =========================================================
    // ADD INPUT
    // =========================================================

    private void addInput(
            JPanel panel,
            GridBagConstraints gbc,
            int row,
            JLabel label,
            JComponent component
    ) {

        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.weightx = 0;

        panel.add(
                label,
                gbc
        );

        gbc.gridx = 1;
        gbc.gridy = row;
        gbc.weightx = 1;

        panel.add(
                component,
                gbc
        );
    }

    // =========================================================
    // CREATE LABEL
    // =========================================================

    private JLabel createLabel(
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
    // CREATE TEXT FIELD
    // =========================================================

    private JTextField createTextField() {

        JTextField field =
                new JTextField();

        styleTextField(
                field
        );

        return field;
    }

    // =========================================================
    // STYLE TEXT FIELD
    // =========================================================

    private void styleTextField(
            JTextField field
    ) {

        field.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        field.setForeground(
                WHITE
        );

        field.setBackground(
                FIELD
        );

        field.setCaretColor(
                CYAN
        );

        field.setBorder(
                BorderFactory.createCompoundBorder(

                        BorderFactory.createLineBorder(
                                new Color(
                                        50,
                                        100,
                                        130
                                )
                        ),

                        BorderFactory.createEmptyBorder(
                                7,
                                8,
                                7,
                                8
                        )
                )
        );
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
    // SAVE PROGRESS
    // =========================================================

    private void saveProgress() {

        // =====================================================
        // CHECK USER
        // =====================================================

        if (
                !CurrentUser.isLoggedIn()
        ) {

            JOptionPane.showMessageDialog(
                    this,

                    "No active user found.\n\n"
                            + "Please register first.",

                    "J.A.R.V.I.S. WARNING",

                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        String username =
                CurrentUser.getUsername();

        String subject =
                subjectField
                        .getText()
                        .trim();

        String hours =
                hoursField
                        .getText()
                        .trim();

        int progress =
                progressSlider.getValue();

        // =====================================================
        // SUBJECT VALIDATION
        // =====================================================

        if (
                subject.isEmpty()
        ) {

            JOptionPane.showMessageDialog(
                    this,

                    "Please enter a subject.",

                    "J.A.R.V.I.S. WARNING",

                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        // =====================================================
        // HOURS VALIDATION
        // =====================================================

        if (
                hours.isEmpty()
        ) {

            JOptionPane.showMessageDialog(
                    this,

                    "Please enter study hours.",

                    "J.A.R.V.I.S. WARNING",

                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        try {

            double hourValue =
                    Double.parseDouble(
                            hours
                    );

            if (
                    hourValue < 0
                    || hourValue > 24
            ) {

                JOptionPane.showMessageDialog(
                        this,

                        "Study hours must be between 0 and 24.",

                        "J.A.R.V.I.S. WARNING",

                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }

        } catch (
                NumberFormatException e
        ) {

            JOptionPane.showMessageDialog(
                    this,

                    "Study hours must be a number.",

                    "J.A.R.V.I.S. WARNING",

                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        // =====================================================
        // SAVE
        // =====================================================

        try {

            PrintWriter writer =
                    new PrintWriter(
                            new FileWriter(
                                    PROGRESS_FILE,
                                    true
                            )
                    );

            writer.println(
                    "USER:"
                            + username
            );

            writer.println(
                    "SUBJECT:"
                            + cleanText(
                                    subject
                            )
            );

            writer.println(
                    "HOURS:"
                            + hours
            );

            writer.println(
                    "PROGRESS:"
                            + progress
            );

            writer.println(
                    "END_PROGRESS"
            );

            writer.println();

            writer.close();

            JOptionPane.showMessageDialog(
                    this,

                    "Daily progress saved successfully!",

                    "J.A.R.V.I.S. ONLINE",

                    JOptionPane.INFORMATION_MESSAGE
            );

            clearFields();

            loadUserProgress();

        } catch (
                IOException e
        ) {

            JOptionPane.showMessageDialog(
                    this,

                    "Unable to save daily progress.\n\n"
                            + e.getMessage(),

                    "J.A.R.V.I.S. ERROR",

                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =========================================================
    // LOAD USER PROGRESS
    // =========================================================

    private void loadUserProgress() {

        listModel.clear();

        if (
                !CurrentUser.isLoggedIn()
        ) {

            return;
        }

        String currentUsername =
                CurrentUser.getUsername();

        ArrayList<ProgressRecord> records =
                readAllProgress();

        for (
                ProgressRecord record :
                records
        ) {

            if (
                    currentUsername.equals(
                            record.username
                    )
            ) {

                listModel.addElement(
                        createDisplay(
                                record
                        )
                );
            }
        }
    }

    // =========================================================
    // CREATE DISPLAY
    // =========================================================

    private String createDisplay(
            ProgressRecord record
    ) {

        return record.subject
                + "   |   "
                + record.hours
                + " hrs   |   "
                + record.progress
                + "%";
    }

    // =========================================================
    // DELETE PROGRESS
    // =========================================================

    private void deleteProgress() {

        if (
                !CurrentUser.isLoggedIn()
        ) {

            JOptionPane.showMessageDialog(
                    this,

                    "No active user found.",

                    "J.A.R.V.I.S. WARNING",

                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        int selectedIndex =
                progressList.getSelectedIndex();

        if (
                selectedIndex < 0
        ) {

            JOptionPane.showMessageDialog(
                    this,

                    "Please select a progress record first.",

                    "J.A.R.V.I.S. WARNING",

                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        int choice =
                JOptionPane.showConfirmDialog(
                        this,

                        "Delete the selected progress record?",

                        "Confirm Delete",

                        JOptionPane.YES_NO_OPTION
                );

        if (
                choice !=
                JOptionPane.YES_OPTION
        ) {

            return;
        }

        String currentUsername =
                CurrentUser.getUsername();

        ArrayList<ProgressRecord> allRecords =
                readAllProgress();

        ArrayList<ProgressRecord> updatedRecords =
                new ArrayList<>();

        int currentUserRecordIndex = 0;

        boolean deleted = false;

        for (
                ProgressRecord record :
                allRecords
        ) {

            if (
                    currentUsername.equals(
                            record.username
                    )
            ) {

                if (
                        currentUserRecordIndex ==
                        selectedIndex
                        && !deleted
                ) {

                    deleted = true;

                    currentUserRecordIndex++;

                    continue;
                }

                currentUserRecordIndex++;
            }

            updatedRecords.add(
                    record
            );
        }

        if (
                !deleted
        ) {

            JOptionPane.showMessageDialog(
                    this,

                    "The selected record could not be found.",

                    "J.A.R.V.I.S. WARNING",

                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        if (
                writeAllProgress(
                        updatedRecords
                )
        ) {

            loadUserProgress();

            JOptionPane.showMessageDialog(
                    this,

                    "Progress record deleted successfully.",

                    "J.A.R.V.I.S. ONLINE",

                    JOptionPane.INFORMATION_MESSAGE
            );
        }
    }

    // =========================================================
    // READ ALL PROGRESS
    // =========================================================

    private ArrayList<ProgressRecord> readAllProgress() {

        ArrayList<ProgressRecord> records =
                new ArrayList<>();

        File file =
                new File(
                        PROGRESS_FILE
                );

        if (
                !file.exists()
        ) {

            return records;
        }

        String username = null;

        String subject = null;

        String hours = null;

        int progress = 0;

        try {

            BufferedReader reader =
                    new BufferedReader(
                            new FileReader(
                                    file
                            )
                    );

            String line;

            while (
                    (line =
                            reader.readLine())
                            != null
            ) {

                line =
                        line.trim();

                if (
                        line.startsWith(
                                "USER:"
                        )
                ) {

                    username =
                            line.substring(
                                    5
                            );
                }

                else if (
                        line.startsWith(
                                "SUBJECT:"
                        )
                ) {

                    subject =
                            line.substring(
                                    8
                            );
                }

                else if (
                        line.startsWith(
                                "HOURS:"
                        )
                ) {

                    hours =
                            line.substring(
                                    6
                            );
                }

                else if (
                        line.startsWith(
                                "PROGRESS:"
                        )
                ) {

                    try {

                        progress =
                                Integer.parseInt(
                                        line.substring(
                                                9
                                        )
                                );

                    } catch (
                            NumberFormatException e
                    ) {

                        progress = 0;
                    }
                }

                else if (
                        line.equals(
                                "END_PROGRESS"
                        )
                ) {

                    if (
                            username != null
                            && subject != null
                            && hours != null
                    ) {

                        records.add(
                                new ProgressRecord(
                                        username,
                                        subject,
                                        hours,
                                        progress
                                )
                        );
                    }

                    username = null;

                    subject = null;

                    hours = null;

                    progress = 0;
                }
            }

            reader.close();

        } catch (
                IOException e
        ) {

            JOptionPane.showMessageDialog(
                    this,

                    "Unable to read DailyProgress.txt.\n\n"
                            + e.getMessage(),

                    "J.A.R.V.I.S. ERROR",

                    JOptionPane.ERROR_MESSAGE
            );
        }

        return records;
    }

    // =========================================================
    // WRITE ALL PROGRESS
    // =========================================================

    private boolean writeAllProgress(
            List<ProgressRecord> records
    ) {

        try {

            PrintWriter writer =
                    new PrintWriter(
                            new FileWriter(
                                    PROGRESS_FILE,
                                    false
                            )
                    );

            for (
                    ProgressRecord record :
                    records
            ) {

                writer.println(
                        "USER:"
                                + record.username
                );

                writer.println(
                        "SUBJECT:"
                                + cleanText(
                                        record.subject
                                )
                );

                writer.println(
                        "HOURS:"
                                + record.hours
                );

                writer.println(
                        "PROGRESS:"
                                + record.progress
                );

                writer.println(
                        "END_PROGRESS"
                );

                writer.println();
            }

            writer.close();

            return true;

        } catch (
                IOException e
        ) {

            JOptionPane.showMessageDialog(
                    this,

                    "Unable to update DailyProgress.txt.\n\n"
                            + e.getMessage(),

                    "J.A.R.V.I.S. ERROR",

                    JOptionPane.ERROR_MESSAGE
            );

            return false;
        }
    }

    // =========================================================
    // CLEAN TEXT
    // =========================================================

    private String cleanText(
            String text
    ) {

        return text
                .replace(
                        "\n",
                        " "
                )
                .replace(
                        "\r",
                        " "
                );
    }

    // =========================================================
    // CLEAR FIELDS
    // =========================================================

    private void clearFields() {

        subjectField.setText("");

        hoursField.setText("");

        progressSlider.setValue(
                0
        );

        percentageLabel.setText(
                "0%"
        );

        subjectField.requestFocus();
    }

    // =========================================================
    // PROGRESS RECORD
    // =========================================================

    private static class ProgressRecord {

        String username;

        String subject;

        String hours;

        int progress;

        ProgressRecord(
                String username,
                String subject,
                String hours,
                int progress
        ) {

            this.username =
                    username;

            this.subject =
                    subject;

            this.hours =
                    hours;

            this.progress =
                    progress;
        }
    }
}
