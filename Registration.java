import javax.swing.*;
import java.awt.*;
import java.io.*;

public class Registration extends JFrame {

    private final Color BACKGROUND =
            new Color(7, 15, 25);

    private final Color PANEL =
            new Color(15, 30, 48);

    private final Color CYAN =
            new Color(0, 220, 255);

    private final Color GREEN =
            new Color(50, 220, 120);

    private final Color WHITE =
            new Color(240, 245, 250);

    private final Color BUTTON =
            new Color(20, 55, 80);

    private JTextField nameField;
    private JTextField usernameField;
    private JTextField ageField;
    private JTextField emailField;
    private JTextField courseField;
    private JTextField collegeField;

    private JPasswordField passwordField;

    public Registration() {

        setTitle(
                "J.A.R.V.I.S. - Registration"
        );

        setSize(
                600,
                700
        );

        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );

        setLocationRelativeTo(null);

        setResizable(false);

        createGUI();

        setVisible(true);
    }

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

        JPanel header =
                new JPanel(
                        new BorderLayout()
                );

        header.setBackground(BACKGROUND);

        header.setBorder(
                BorderFactory.createEmptyBorder(
                        20,
                        20,
                        15,
                        20
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
                        34
                )
        );

        title.setForeground(CYAN);

        JLabel subtitle =
                new JLabel(
                        "NEW USER REGISTRATION",
                        SwingConstants.CENTER
                );

        subtitle.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        subtitle.setForeground(GREEN);

        header.add(
                title,
                BorderLayout.CENTER
        );

        header.add(
                subtitle,
                BorderLayout.SOUTH
        );

        add(
                header,
                BorderLayout.NORTH
        );

        // =====================================================
        // FORM
        // =====================================================

        JPanel form =
                new JPanel(
                        new GridBagLayout()
                );

        form.setBackground(PANEL);

        form.setBorder(
                BorderFactory.createCompoundBorder(

                        BorderFactory.createLineBorder(
                                CYAN
                        ),

                        BorderFactory.createEmptyBorder(
                                18,
                                30,
                                18,
                                30
                        )
                )
        );

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.insets =
                new Insets(
                        5,
                        6,
                        5,
                        6
                );

        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        gbc.weightx = 1;

        nameField = createTextField();
        usernameField = createTextField();
        ageField = createTextField();
        emailField = createTextField();
        courseField = createTextField();
        collegeField = createTextField();

        passwordField =
                new JPasswordField();

        styleTextField(passwordField);

        addField(
                form,
                gbc,
                0,
                "Full Name",
                nameField
        );

        addField(
                form,
                gbc,
                1,
                "Username",
                usernameField
        );

        addField(
                form,
                gbc,
                2,
                "Age",
                ageField
        );

        addField(
                form,
                gbc,
                3,
                "Email",
                emailField
        );

        addField(
                form,
                gbc,
                4,
                "Course",
                courseField
        );

        addField(
                form,
                gbc,
                5,
                "College",
                collegeField
        );

        addField(
                form,
                gbc,
                6,
                "Password",
                passwordField
        );

        add(
                form,
                BorderLayout.CENTER
        );

        // =====================================================
        // BUTTONS
        // =====================================================

        JPanel buttons =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.CENTER,
                                10,
                                10
                        )
                );

        buttons.setBackground(BACKGROUND);

        JButton register =
                createButton("REGISTER");

        JButton clear =
                createButton("CLEAR");

        JButton login =
                createButton("BACK TO LOGIN");

        buttons.add(register);
        buttons.add(clear);
        buttons.add(login);

        add(
                buttons,
                BorderLayout.SOUTH
        );

        // =====================================================
        // ACTIONS
        // =====================================================

        register.addActionListener(
                e -> registerUser()
        );

        clear.addActionListener(
                e -> clearFields()
        );

        login.addActionListener(
                e -> {

                    dispose();

                    new Login();
                }
        );
    }

    // =========================================================
    // ADD FIELD
    // =========================================================

    private void addField(
            JPanel panel,
            GridBagConstraints gbc,
            int row,
            String text,
            JComponent field
    ) {

        JLabel label =
                new JLabel(text);

        label.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        13
                )
        );

        label.setForeground(WHITE);

        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.gridwidth = 1;
        gbc.weightx = 0;

        panel.add(
                label,
                gbc
        );

        gbc.gridx = 1;
        gbc.gridwidth = 2;
        gbc.weightx = 1;

        panel.add(
                field,
                gbc
        );
    }

    // =========================================================
    // REGISTER
    // =========================================================

    private void registerUser() {

        String name =
                nameField.getText().trim();

        String username =
                usernameField.getText().trim();

        String age =
                ageField.getText().trim();

        String email =
                emailField.getText().trim();

        String course =
                courseField.getText().trim();

        String college =
                collegeField.getText().trim();

        String password =
                new String(
                        passwordField.getPassword()
                );

        // =====================================================
        // EMPTY CHECK
        // =====================================================

        if (
                name.isEmpty()
                || username.isEmpty()
                || age.isEmpty()
                || email.isEmpty()
                || course.isEmpty()
                || college.isEmpty()
                || password.isEmpty()
        ) {

            showWarning(
                    "Please fill in all fields."
            );

            return;
        }

        // =====================================================
        // USERNAME
        // =====================================================

        if (username.contains(" ")) {

            showWarning(
                    "Username cannot contain spaces."
            );

            return;
        }

        // =====================================================
        // DUPLICATE USERNAME
        // =====================================================

        if (
                usernameAlreadyExists(
                        username
                )
        ) {

            int choice =
                    JOptionPane.showConfirmDialog(
                            this,

                            "This username is already registered.\n\n"
                                    + "Would you like to login instead?",

                            "J.A.R.V.I.S.",

                            JOptionPane.YES_NO_OPTION
                    );

            if (
                    choice ==
                    JOptionPane.YES_OPTION
            ) {

                dispose();

                new Login();
            }

            return;
        }

        // =====================================================
        // AGE
        // =====================================================

        try {

            int ageNumber =
                    Integer.parseInt(age);

            if (
                    ageNumber <= 0
                    || ageNumber > 120
            ) {

                showWarning(
                        "Please enter a valid age."
                );

                return;
            }

        } catch (NumberFormatException e) {

            showWarning(
                    "Age must contain numbers only."
            );

            return;
        }

        // =====================================================
        // EMAIL
        // =====================================================

        if (
                !email.contains("@")
                || !email.contains(".")
        ) {

            showWarning(
                    "Please enter a valid email address."
            );

            return;
        }

        // =====================================================
        // SAVE
        // =====================================================

        saveUser(
                name,
                username,
                age,
                email,
                course,
                college,
                password
        );
    }

    // =========================================================
    // DUPLICATE CHECK
    // =========================================================

    private boolean usernameAlreadyExists(
            String username
    ) {

        File file =
                new File("User.txt");

        if (!file.exists()) {

            return false;
        }

        try (
                BufferedReader reader =
                        new BufferedReader(
                                new FileReader(file)
                        )
        ) {

            String line;

            while (
                    (line = reader.readLine())
                            != null
            ) {

                if (
                        line.startsWith(
                                "Username:"
                        )
                ) {

                    String existing =
                            line.substring(
                                    9
                            ).trim();

                    if (
                            existing.equalsIgnoreCase(
                                    username
                            )
                    ) {

                        return true;
                    }
                }
            }

        } catch (IOException e) {

            JOptionPane.showMessageDialog(
                    this,

                    "Unable to read User.txt.\n\n"
                            + e.getMessage(),

                    "J.A.R.V.I.S. ERROR",

                    JOptionPane.ERROR_MESSAGE
            );

            return true;
        }

        return false;
    }

    // =========================================================
    // SAVE USER
    // =========================================================

    private void saveUser(
            String name,
            String username,
            String age,
            String email,
            String course,
            String college,
            String password
    ) {

        try (
                PrintWriter writer =
                        new PrintWriter(
                                new FileWriter(
                                        "User.txt",
                                        true
                                )
                        )
        ) {

            writer.println();

            writer.println(
                    "========================================"
            );

            writer.println(
                    "          J.A.R.V.I.S. USER DATA"
            );

            writer.println(
                    "========================================"
            );

            writer.println();

            writer.println(
                    "Name: " + name
            );

            writer.println(
                    "Username: " + username
            );

            writer.println(
                    "Age: " + age
            );

            writer.println(
                    "Email: " + email
            );

            writer.println(
                    "Course: " + course
            );

            writer.println(
                    "College: " + college
            );

            writer.println(
                    "Password: " + password
            );

            writer.println();

            writer.println(
                    "========================================"
            );

            // =================================================
            // SET CURRENT USER
            // =================================================

            CurrentUser.setUser(
                    username,
                    name
            );

            JOptionPane.showMessageDialog(
                    this,

                    "Registration successful!\n\n"
                            + "Welcome to J.A.R.V.I.S., "
                            + name
                            + "!",

                    "J.A.R.V.I.S. ONLINE",

                    JOptionPane.INFORMATION_MESSAGE
            );

            dispose();

            Main.startDashboard();

        } catch (IOException e) {

            JOptionPane.showMessageDialog(
                    this,

                    "Unable to save user details.\n\n"
                            + e.getMessage(),

                    "J.A.R.V.I.S. ERROR",

                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =========================================================
    // CLEAR
    // =========================================================

    private void clearFields() {

        nameField.setText("");
        usernameField.setText("");
        ageField.setText("");
        emailField.setText("");
        courseField.setText("");
        collegeField.setText("");
        passwordField.setText("");

        nameField.requestFocus();
    }

    // =========================================================
    // WARNING
    // =========================================================

    private void showWarning(
            String message
    ) {

        JOptionPane.showMessageDialog(
                this,

                message,

                "J.A.R.V.I.S. WARNING",

                JOptionPane.WARNING_MESSAGE
        );
    }

    // =========================================================
    // TEXT FIELD
    // =========================================================

    private JTextField createTextField() {

        JTextField field =
                new JTextField();

        styleTextField(field);

        return field;
    }

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

        field.setForeground(WHITE);

        field.setBackground(
                new Color(
                        10,
                        22,
                        35
                )
        );

        field.setCaretColor(CYAN);

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
                                6,
                                8,
                                6,
                                8
                        )
                )
        );
    }

    // =========================================================
    // BUTTON
    // =========================================================

    private JButton createButton(
            String text
    ) {

        JButton button =
                new JButton(text);

        button.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        12
                )
        );

        button.setForeground(WHITE);

        button.setBackground(BUTTON);

        button.setFocusPainted(false);

        button.setOpaque(true);

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
                                12,
                                8,
                                12
                        )
                )
        );

        return button;
    }
}