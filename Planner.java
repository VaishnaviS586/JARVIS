import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class Planner extends JFrame {

    private JTextField subjectField;
    private JTextField taskField;
    private JTextField dateField;
    private JComboBox<String> priorityBox;

    private JTable planTable;
    private DefaultTableModel tableModel;

    private static final String FILE_NAME = "Planner.txt";

    // J.A.R.V.I.S. colors
    private final Color backgroundColor = new Color(15, 23, 42);
    private final Color panelColor = new Color(30, 41, 59);
    private final Color inputColor = new Color(51, 65, 85);
    private final Color accentColor = new Color(0, 180, 255);
    private final Color textColor = Color.WHITE;
    private final Color secondaryTextColor = new Color(180, 200, 220);

    public Planner() {
        createGUI();
        loadUserPlans();
        setVisible(true);
    }

    // Create planner GUI
    private void createGUI() {

        setTitle("J.A.R.V.I.S. STUDY PLANNER");
        setSize(900, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout(15, 15));
        mainPanel.setBackground(backgroundColor);
        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(15, 15, 15, 15)
        );

        // Title
        JLabel titleLabel = new JLabel(
                "J.A.R.V.I.S. STUDY PLANNER",
                SwingConstants.CENTER
        );

        titleLabel.setFont(
                new Font("Arial", Font.BOLD, 26)
        );

        titleLabel.setForeground(accentColor);

        JLabel userLabel = new JLabel(
                getUserDisplayText(),
                SwingConstants.CENTER
        );

        userLabel.setFont(
                new Font("Arial", Font.PLAIN, 14)
        );

        userLabel.setForeground(secondaryTextColor);

        JPanel titlePanel = new JPanel(
                new GridLayout(2, 1, 0, 5)
        );

        titlePanel.setBackground(backgroundColor);
        titlePanel.add(titleLabel);
        titlePanel.add(userLabel);

        mainPanel.add(titlePanel, BorderLayout.NORTH);

        // Input panel
        JPanel inputPanel = new JPanel(
                new GridBagLayout()
        );

        inputPanel.setBackground(panelColor);

        inputPanel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(60, 80, 110)
                        ),
                        BorderFactory.createEmptyBorder(
                                12, 12, 12, 12
                        )
                )
        );

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(7, 7, 7, 7);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel subjectLabel = createLabel("Subject:");
        JLabel taskLabel = createLabel("Task:");
        JLabel dateLabel = createLabel("Date:");
        JLabel priorityLabel = createLabel("Priority:");

        subjectField = createTextField();
        taskField = createTextField();
        dateField = createTextField();

        priorityBox = new JComboBox<>(
                new String[]{
                        "Low",
                        "Medium",
                        "High"
                }
        );

        styleComboBox(priorityBox);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0;
        inputPanel.add(subjectLabel, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1;
        inputPanel.add(subjectField, gbc);

        gbc.gridx = 2;
        gbc.weightx = 0;
        inputPanel.add(taskLabel, gbc);

        gbc.gridx = 3;
        gbc.weightx = 1;
        inputPanel.add(taskField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0;
        inputPanel.add(dateLabel, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1;
        inputPanel.add(dateField, gbc);

        gbc.gridx = 2;
        gbc.weightx = 0;
        inputPanel.add(priorityLabel, gbc);

        gbc.gridx = 3;
        gbc.weightx = 1;
        inputPanel.add(priorityBox, gbc);

        // Buttons
        JButton addButton = createButton(
                "ADD PLAN",
                accentColor
        );

        JButton deleteButton = createButton(
                "DELETE SELECTED",
                new Color(220, 70, 70)
        );

        JButton clearButton = createButton(
                "CLEAR",
                new Color(100, 116, 139)
        );

        JButton refreshButton = createButton(
                "REFRESH",
                new Color(30, 180, 120)
        );

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 1;
        inputPanel.add(addButton, gbc);

        gbc.gridx = 1;
        inputPanel.add(deleteButton, gbc);

        gbc.gridx = 2;
        inputPanel.add(clearButton, gbc);

        gbc.gridx = 3;
        inputPanel.add(refreshButton, gbc);

        // Table
        String[] columns = {
                "Subject",
                "Task",
                "Date",
                "Priority"
        };

        tableModel = new DefaultTableModel(
                columns,
                0
        ) {
            @Override
            public boolean isCellEditable(
                    int row,
                    int column) {
                return false;
            }
        };

        planTable = new JTable(tableModel);

        planTable.setRowHeight(30);

        planTable.setFont(
                new Font("Arial", Font.PLAIN, 14)
        );

        planTable.setForeground(Color.WHITE);
        planTable.setBackground(
                new Color(30, 41, 59)
        );

        planTable.setSelectionBackground(
                new Color(0, 120, 180)
        );

        planTable.setSelectionForeground(Color.WHITE);

        planTable.setGridColor(
                new Color(60, 80, 110)
        );

        planTable.setShowGrid(true);

        JTableHeaderStyle();

        JScrollPane scrollPane =
                new JScrollPane(planTable);

        scrollPane.setBorder(
                BorderFactory.createLineBorder(
                        new Color(60, 80, 110)
                )
        );

        scrollPane.getViewport().setBackground(
                panelColor
        );

        JPanel tablePanel = new JPanel(
                new BorderLayout()
        );

        tablePanel.setBackground(backgroundColor);

        JLabel tableTitle = new JLabel(
                "YOUR STUDY PLANS"
        );

        tableTitle.setFont(
                new Font("Arial", Font.BOLD, 16)
        );

        tableTitle.setForeground(accentColor);

        tableTitle.setBorder(
                BorderFactory.createEmptyBorder(
                        5, 5, 8, 5
                )
        );

        tablePanel.add(
                tableTitle,
                BorderLayout.NORTH
        );

        tablePanel.add(
                scrollPane,
                BorderLayout.CENTER
        );

        JPanel centerPanel = new JPanel(
                new BorderLayout(12, 12)
        );

        centerPanel.setBackground(backgroundColor);

        centerPanel.add(
                inputPanel,
                BorderLayout.NORTH
        );

        centerPanel.add(
                tablePanel,
                BorderLayout.CENTER
        );

        mainPanel.add(
                centerPanel,
                BorderLayout.CENTER
        );

        add(mainPanel);

        // Button actions
        addButton.addActionListener(
                e -> addPlan()
        );

        deleteButton.addActionListener(
                e -> deleteSelectedPlan()
        );

        clearButton.addActionListener(
                e -> clearFields()
        );

        refreshButton.addActionListener(
                e -> loadUserPlans()
        );
    }

    // Create label
    private JLabel createLabel(String text) {

        JLabel label = new JLabel(text);

        label.setFont(
                new Font("Arial", Font.BOLD, 14)
        );

        label.setForeground(textColor);

        return label;
    }

    // Create text field
    private JTextField createTextField() {

        JTextField field = new JTextField();

        field.setFont(
                new Font("Arial", Font.PLAIN, 14)
        );

        field.setForeground(Color.WHITE);

        field.setBackground(inputColor);

        field.setCaretColor(Color.WHITE);

        field.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(80, 100, 130)
                        ),
                        BorderFactory.createEmptyBorder(
                                5, 8, 5, 8
                        )
                )
        );

        return field;
    }

    // Style combo box
    private void styleComboBox(
            JComboBox<String> comboBox) {

        comboBox.setFont(
                new Font("Arial", Font.PLAIN, 14)
        );

        comboBox.setForeground(Color.WHITE);

        comboBox.setBackground(inputColor);

        comboBox.setFocusable(false);
    }

    // Create button
    private JButton createButton(
            String text,
            Color color) {

        JButton button = new JButton(text);

        button.setFont(
                new Font("Arial", Font.BOLD, 12)
        );

        button.setForeground(Color.WHITE);

        button.setBackground(color);

        button.setFocusPainted(false);

        button.setBorder(
                BorderFactory.createEmptyBorder(
                        10, 12, 10, 12
                )
        );

        button.setCursor(
                new Cursor(Cursor.HAND_CURSOR)
        );

        return button;
    }

    // Style table header
    private void JTableHeaderStyle() {

        planTable.getTableHeader().setFont(
                new Font("Arial", Font.BOLD, 14)
        );

        planTable.getTableHeader().setForeground(
                Color.WHITE
        );

        planTable.getTableHeader().setBackground(
                new Color(15, 60, 90)
        );

        planTable.getTableHeader().setPreferredSize(
                new Dimension(0, 35)
        );
    }

    // Get current user display text
    private String getUserDisplayText() {

        if (CurrentUser.isLoggedIn()) {

            String name =
                    CurrentUser.getName();

            String username =
                    CurrentUser.getUsername();

            if (name != null &&
                    !name.trim().isEmpty()) {

                return "Logged in as: "
                        + name
                        + " ("
                        + username
                        + ")";
            }

            return "Logged in as: "
                    + username;
        }

        return "No user logged in";
    }

    // Add a new study plan
    private void addPlan() {

        if (!CurrentUser.isLoggedIn()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please login first.",
                    "Login Required",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        String subject =
                subjectField.getText().trim();

        String task =
                taskField.getText().trim();

        String date =
                dateField.getText().trim();

        String priority =
                priorityBox.getSelectedItem()
                        .toString();

        if (subject.isEmpty()
                || task.isEmpty()
                || date.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please fill all fields.",
                    "Missing Information",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        String username =
                CurrentUser.getUsername();

        String name =
                CurrentUser.getName();

        try (
                FileWriter fw =
                        new FileWriter(
                                FILE_NAME,
                                true
                        );

                PrintWriter pw =
                        new PrintWriter(fw)
        ) {

            pw.println(
                    "USER:" + username
            );

            pw.println(
                    "NAME:" + name
            );

            pw.println(
                    "SUBJECT:" + subject
            );

            pw.println(
                    "TASK:" + task
            );

            pw.println(
                    "DATE:" + date
            );

            pw.println(
                    "PRIORITY:" + priority
            );

            pw.println("END_PLAN");

            // Show the new task immediately
            tableModel.addRow(
                    new Object[]{
                            subject,
                            task,
                            date,
                            priority
                    }
            );

            JOptionPane.showMessageDialog(
                    this,
                    "Study plan added successfully!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );

            clearFields();

        } catch (IOException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error saving plan: "
                            + ex.getMessage(),
                    "File Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // Load plans for current user
    private void loadUserPlans() {

        tableModel.setRowCount(0);

        if (!CurrentUser.isLoggedIn()) {
            return;
        }

        File file =
                new File(FILE_NAME);

        if (!file.exists()) {
            return;
        }

        String currentUsername =
                CurrentUser.getUsername()
                        .trim();

        String username = "";
        String subject = "";
        String task = "";
        String date = "";
        String priority = "";

        try (
                BufferedReader br =
                        new BufferedReader(
                                new FileReader(file)
                        )
        ) {

            String line;

            while ((line = br.readLine())
                    != null) {

                line = line.trim();

                if (line.startsWith("USER:")) {

                    username =
                            line.substring(5)
                                    .trim();

                } else if (
                        line.startsWith("SUBJECT:")
                ) {

                    subject =
                            line.substring(8)
                                    .trim();

                } else if (
                        line.startsWith("TASK:")
                ) {

                    task =
                            line.substring(5)
                                    .trim();

                } else if (
                        line.startsWith("DATE:")
                ) {

                    date =
                            line.substring(5)
                                    .trim();

                } else if (
                        line.startsWith("PRIORITY:")
                ) {

                    priority =
                            line.substring(9)
                                    .trim();

                } else if (
                        line.equals("END_PLAN")
                ) {

                    if (username.equalsIgnoreCase(
                            currentUsername)) {

                        tableModel.addRow(
                                new Object[]{
                                        subject,
                                        task,
                                        date,
                                        priority
                                }
                        );
                    }

                    username = "";
                    subject = "";
                    task = "";
                    date = "";
                    priority = "";
                }
            }

        } catch (IOException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error loading plans: "
                            + ex.getMessage(),
                    "File Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // Delete selected plan
    private void deleteSelectedPlan() {

        int selectedRow =
                planTable.getSelectedRow();

        if (selectedRow == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a plan to delete.",
                    "No Selection",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        String subject =
                tableModel.getValueAt(
                        selectedRow, 0
                ).toString();

        String task =
                tableModel.getValueAt(
                        selectedRow, 1
                ).toString();

        String date =
                tableModel.getValueAt(
                        selectedRow, 2
                ).toString();

        String priority =
                tableModel.getValueAt(
                        selectedRow, 3
                ).toString();

        int confirm =
                JOptionPane.showConfirmDialog(
                        this,
                        "Are you sure you want to delete this plan?",
                        "Confirm Delete",
                        JOptionPane.YES_NO_OPTION
                );

        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        deletePlanFromFile(
                subject,
                task,
                date,
                priority
        );
    }

    // Delete plan from file
    private void deletePlanFromFile(
            String subject,
            String task,
            String date,
            String priority) {

        if (!CurrentUser.isLoggedIn()) {
            return;
        }

        File file =
                new File(FILE_NAME);

        if (!file.exists()) {
            return;
        }

        String currentUsername =
                CurrentUser.getUsername()
                        .trim();

        ArrayList<String> records =
                new ArrayList<>();

        String username = "";
        String name = "";
        String currentSubject = "";
        String currentTask = "";
        String currentDate = "";
        String currentPriority = "";

        try (
                BufferedReader br =
                        new BufferedReader(
                                new FileReader(file)
                        )
        ) {

            String line;

            while ((line = br.readLine())
                    != null) {

                line = line.trim();

                if (line.startsWith("USER:")) {

                    username =
                            line.substring(5)
                                    .trim();

                } else if (line.startsWith("NAME:")) {

                    name =
                            line.substring(5)
                                    .trim();

                } else if (
                        line.startsWith("SUBJECT:")
                ) {

                    currentSubject =
                            line.substring(8)
                                    .trim();

                } else if (
                        line.startsWith("TASK:")
                ) {

                    currentTask =
                            line.substring(5)
                                    .trim();

                } else if (
                        line.startsWith("DATE:")
                ) {

                    currentDate =
                            line.substring(5)
                                    .trim();

                } else if (
                        line.startsWith("PRIORITY:")
                ) {

                    currentPriority =
                            line.substring(9)
                                    .trim();

                } else if (
                        line.equals("END_PLAN")
                ) {

                    boolean sameUser =
                            username.equalsIgnoreCase(
                                    currentUsername
                            );

                    boolean samePlan =
                            currentSubject.equals(subject)
                                    && currentTask.equals(task)
                                    && currentDate.equals(date)
                                    && currentPriority.equals(priority);

                    if (!(sameUser && samePlan)) {

                        records.add(
                                "USER:" + username
                        );

                        records.add(
                                "NAME:" + name
                        );

                        records.add(
                                "SUBJECT:" + currentSubject
                        );

                        records.add(
                                "TASK:" + currentTask
                        );

                        records.add(
                                "DATE:" + currentDate
                        );

                        records.add(
                                "PRIORITY:" + currentPriority
                        );

                        records.add("END_PLAN");
                    }

                    username = "";
                    name = "";
                    currentSubject = "";
                    currentTask = "";
                    currentDate = "";
                    currentPriority = "";
                }
            }

        } catch (IOException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error reading plans: "
                            + ex.getMessage(),
                    "File Error",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }

        try (
                PrintWriter pw =
                        new PrintWriter(
                                new FileWriter(
                                        file,
                                        false
                                )
                        )
        ) {

            for (String record : records) {
                pw.println(record);
            }

            loadUserPlans();

            JOptionPane.showMessageDialog(
                    this,
                    "Plan deleted successfully.",
                    "Deleted",
                    JOptionPane.INFORMATION_MESSAGE
            );

        } catch (IOException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error deleting plan: "
                            + ex.getMessage(),
                    "File Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // Clear input fields
    private void clearFields() {

        subjectField.setText("");
        taskField.setText("");
        dateField.setText("");

        priorityBox.setSelectedIndex(0);

        subjectField.requestFocus();
    }
}