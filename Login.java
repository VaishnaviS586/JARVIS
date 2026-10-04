import javax.swing.*;
import java.awt.*;
import java.io.*;

public class Login extends JFrame {

    private final Color BACKGROUND = new Color(7, 15, 25);
    private final Color PANEL = new Color(15, 30, 48);
    private final Color CYAN = new Color(0, 220, 255);
    private final Color GREEN = new Color(50, 220, 120);
    private final Color WHITE = new Color(240, 245, 250);
    private final Color BUTTON = new Color(20, 55, 80);

    private JTextField usernameField;
    private JPasswordField passwordField;

    public Login() {

        setTitle("J.A.R.V.I.S. - Login");

        setSize(500, 500);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLocationRelativeTo(null);

        setResizable(false);

        createGUI();

        setVisible(true);
    }

    private void createGUI() {

        getContentPane().setBackground(BACKGROUND);

        setLayout(new BorderLayout());

        // =====================================================
        // HEADER
        // =====================================================

        JPanel headerPanel = new JPanel(new BorderLayout());

        headerPanel.setBackground(BACKGROUND);

        headerPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        30, 20, 20, 20
                )
        );

        JLabel title = new JLabel(
                "J.A.R.V.I.S.",
                SwingConstants.CENTER
        );

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        38
                )
        );

        title.setForeground(CYAN);

        JLabel subtitle = new JLabel(
                "SYSTEM LOGIN",
                SwingConstants.CENTER
        );

        subtitle.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        15
                )
        );

        subtitle.setForeground(GREEN);

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
        // LOGIN PANEL
        // =====================================================

        JPanel loginPanel = new JPanel(
                new GridBagLayout()
        );

        loginPanel.setBackground(PANEL);

        loginPanel.setBorder(
                BorderFactory.createCompoundBorder(

                        BorderFactory.createLineBorder(
                                CYAN
                        ),

                        BorderFactory.createEmptyBorder(
                                30,
                                40,
                                30,
                                40
                        )
                )
        );

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.insets =
                new Insets(
                        10,
                        10,
                        10,
                        10
                );

        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        gbc.weightx = 1;

        // =====================================================
        // USERNAME
        // =====================================================

        JLabel usernameLabel =
                createLabel("Username");

        usernameField =
                new JTextField();

        styleTextField(
                usernameField
        );

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0;

        loginPanel.add(
                usernameLabel,
                gbc
        );

        gbc.gridx = 1;
        gbc.weightx = 1;

        loginPanel.add(
                usernameField,
                gbc
        );

        // =====================================================
        // PASSWORD
        // =====================================================

        JLabel passwordLabel =
                createLabel("Password");

        passwordField =
                new JPasswordField();

        styleTextField(
                passwordField
        );

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0;

        loginPanel.add(
                passwordLabel,
                gbc
        );

        gbc.gridx = 1;
        gbc.weightx = 1;

        loginPanel.add(
                passwordField,
                gbc
        );

        // =====================================================
        // LOGIN BUTTON
        // =====================================================

        JButton loginButton =
                createButton("LOGIN");

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;

        gbc.weightx = 1;

        loginPanel.add(
                loginButton,
                gbc
        );

        // =====================================================
        // REGISTER BUTTON
        // =====================================================

        JButton registerButton =
                createButton("NEW USER? REGISTER");

        gbc.gridy = 3;

        loginPanel.add(
                registerButton,
                gbc
        );

        add(
                loginPanel,
                BorderLayout.CENTER
        );

        // =====================================================
        // FOOTER
        // =====================================================

        JLabel footer =
                new JLabel(
                        "SECURE USER ACCESS",
                        SwingConstants.CENTER
                );

        footer.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        12
                )
        );

        footer.setForeground(GREEN);

        footer.setBorder(
                BorderFactory.createEmptyBorder(
                        10,
                        10,
                        15,
                        10
                )
        );

        add(
                footer,
                BorderLayout.SOUTH
        );

        // =====================================================
        // ACTIONS
        // =====================================================

        loginButton.addActionListener(
                e -> loginUser()
        );

        registerButton.addActionListener(
                e -> openRegistration()
        );

        passwordField.addActionListener(
                e -> loginUser()
        );
    }

    // =========================================================
    // LOGIN USER
    // =========================================================

    private void loginUser() {

        String username =
                usernameField
                        .getText()
                        .trim();

        String password =
                new String(
                        passwordField
                                .getPassword()
                );

        if (
                username.isEmpty()
                || password.isEmpty()
        ) {

            JOptionPane.showMessageDialog(
                    this,

                    "Please enter username and password.",

                    "J.A.R.V.I.S. WARNING",

                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        UserData user =
                findUser(
                        username,
                        password
                );

        if (user != null) {

            // IMPORTANT:
            // Set current user before dashboard opens.

            CurrentUser.setUser(
                    user.username,
                    user.name
            );

            JOptionPane.showMessageDialog(
                    this,

                    "Login successful!\n\n"
                            + "Welcome back, "
                            + user.name
                            + "!",

                    "J.A.R.V.I.S. ONLINE",

                    JOptionPane.INFORMATION_MESSAGE
            );

            dispose();

            Main.startDashboard();

        } else {

            JOptionPane.showMessageDialog(
                    this,

                    "Invalid username or password.\n\n"
                            + "If you are a new user, please register.",

                    "J.A.R.V.I.S. ACCESS DENIED",

                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =========================================================
    // FIND USER
    // =========================================================

    private UserData findUser(
            String username,
            String password
    ) {

        File file =
                new File("User.txt");

        if (!file.exists()) {

            return null;
        }

        try {

            BufferedReader reader =
                    new BufferedReader(
                            new FileReader(file)
                    );

            String line;

            String name = null;
            String storedUsername = null;
            String storedPassword = null;

            while (
                    (line = reader.readLine())
                            != null
            ) {

                if (line.startsWith("Name:")) {

                    name =
                            line.substring(
                                    5
                            ).trim();
                }

                else if (
                        line.startsWith("Username:")
                ) {

                    storedUsername =
                            line.substring(
                                    9
                            ).trim();
                }

                else if (
                        line.startsWith("Password:")
                ) {

                    storedPassword =
                            line.substring(
                                    9
                            ).trim();

                    // We have reached the end of a user record.

                    if (
                            storedUsername != null
                            && storedPassword != null
                    ) {

                        if (
                                storedUsername.equalsIgnoreCase(
                                        username
                                )
                                && storedPassword.equals(
                                        password
                                )
                        ) {

                            reader.close();

                            return new UserData(
                                    name,
                                    storedUsername
                            );
                        }
                    }

                    // Reset for next user.

                    name = null;

                    storedUsername = null;

                    storedPassword = null;
                }
            }

            reader.close();

        } catch (IOException e) {

            JOptionPane.showMessageDialog(
                    this,

                    "Unable to read User.txt.\n\n"
                            + e.getMessage(),

                    "J.A.R.V.I.S. ERROR",

                    JOptionPane.ERROR_MESSAGE
            );
        }

        return null;
    }

    // =========================================================
    // OPEN REGISTRATION
    // =========================================================

    private void openRegistration() {

        dispose();

        new Registration();
    }

    // =========================================================
    // LABEL
    // =========================================================

    private JLabel createLabel(
            String text
    ) {

        JLabel label =
                new JLabel(text);

        label.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        label.setForeground(WHITE);

        return label;
    }

    // =========================================================
    // TEXT FIELD STYLE
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
                                8,
                                8,
                                8,
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
                        13
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
                                10,
                                15,
                                10,
                                15
                        )
                )
        );

        return button;
    }

    // =========================================================
    // USER DATA
    // =========================================================

    private static class UserData {

        String name;
        String username;

        UserData(
                String name,
                String username
        ) {

            this.name = name;
            this.username = username;
        }
    }
}