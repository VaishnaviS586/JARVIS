import javax.swing.*;
import java.awt.*;
import java.io.*;

public class Settings extends JFrame {

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

    private JCheckBox notificationCheckBox;

    private JCheckBox soundCheckBox;

    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public Settings() {

        setTitle(
                "J.A.R.V.I.S. - Settings"
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

        loadSettings();

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
                        "J.A.R.V.I.S. SETTINGS",
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
                        "SYSTEM CONFIGURATION",
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
        // MAIN PANEL
        // =====================================================

        JPanel mainPanel =
                new JPanel(
                        new GridLayout(
                                3,
                                1,
                                15,
                                15
                        )
                );

        mainPanel.setBackground(
                BACKGROUND
        );

        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        15,
                        35,
                        15,
                        35
                )
        );

        // =====================================================
        // USER INFORMATION PANEL
        // =====================================================

        JPanel userPanel =
                createSectionPanel(
                        "USER INFORMATION"
                );

        JButton profileButton =
                createButton(
                        "VIEW PROFILE"
                );

        profileButton.addActionListener(
                e -> showProfile()
        );

        userPanel.add(
                profileButton
        );

        mainPanel.add(
                userPanel
        );

        // =====================================================
        // APPLICATION SETTINGS PANEL
        // =====================================================

        JPanel applicationPanel =
                createSectionPanel(
                        "APPLICATION"
                );

        notificationCheckBox =
                createCheckBox(
                        "Notifications"
                );

        soundCheckBox =
                createCheckBox(
                        "Sound Effects"
                );

        applicationPanel.add(
                notificationCheckBox
        );

        applicationPanel.add(
                soundCheckBox
        );

        mainPanel.add(
                applicationPanel
        );

        // =====================================================
        // ABOUT PANEL
        // =====================================================

        JPanel aboutPanel =
                createSectionPanel(
                        "ABOUT"
                );

        JButton aboutButton =
                createButton(
                        "ABOUT J.A.R.V.I.S."
                );

        aboutButton.addActionListener(
                e -> showAbout()
        );

        aboutPanel.add(
                aboutButton
        );

        mainPanel.add(
                aboutPanel
        );

        add(
                mainPanel,
                BorderLayout.CENTER
        );

        // =====================================================
        // BOTTOM BUTTON PANEL
        // =====================================================

        JPanel bottomPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.CENTER,
                                15,
                                12
                        )
                );

        bottomPanel.setBackground(
                BACKGROUND
        );

        JButton saveButton =
                createButton(
                        "SAVE SETTINGS"
                );

        JButton closeButton =
                createButton(
                        "CLOSE"
                );

        saveButton.addActionListener(
                e -> saveSettings()
        );

        closeButton.addActionListener(
                e -> dispose()
        );

        bottomPanel.add(
                saveButton
        );

        bottomPanel.add(
                closeButton
        );

        add(
                bottomPanel,
                BorderLayout.SOUTH
        );
    }

    // =========================================================
    // CREATE SECTION PANEL
    // =========================================================

    private JPanel createSectionPanel(
            String title
    ) {

        JPanel panel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.LEFT,
                                15,
                                15
                        )
                );

        panel.setBackground(
                PANEL
        );

        panel.setBorder(
                BorderFactory.createTitledBorder(

                        BorderFactory.createLineBorder(
                                CYAN
                        ),

                        title,

                        javax.swing.border.TitledBorder.LEFT,

                        javax.swing.border.TitledBorder.TOP,

                        new Font(
                                "Arial",
                                Font.BOLD,
                                13
                        ),

                        CYAN
                )
        );

        return panel;
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
    // CREATE CHECKBOX
    // =========================================================

    private JCheckBox createCheckBox(
            String text
    ) {

        JCheckBox checkBox =
                new JCheckBox(
                        text
                );

        checkBox.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        checkBox.setForeground(
                WHITE
        );

        checkBox.setBackground(
                PANEL
        );

        checkBox.setFocusPainted(
                false
        );

        return checkBox;
    }

    // =========================================================
    // SHOW CURRENT USER PROFILE
    // =========================================================

    private void showProfile() {

        // =====================================================
        // GET CURRENT USER
        // =====================================================

        String currentUsername =
                CurrentUser.getUsername();

        if (
                currentUsername == null
                || currentUsername.isEmpty()
        ) {

            JOptionPane.showMessageDialog(
                    this,

                    "No user is currently logged in.",

                    "J.A.R.V.I.S.",

                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        // =====================================================
        // USER FILE
        // =====================================================

        File file =
                new File(
                        "User.txt"
                );

        if (
                !file.exists()
        ) {

            JOptionPane.showMessageDialog(
                    this,

                    "User information was not found.",

                    "J.A.R.V.I.S.",

                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        // =====================================================
        // PROFILE DATA
        // =====================================================

        StringBuilder profile =
                new StringBuilder();

        boolean insideUserRecord =
                false;

        boolean userFound =
                false;

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

                // =================================================
                // FIND USERNAME
                // =================================================

                if (
                        line.startsWith(
                                "Username:"
                        )
                ) {

                    String username =
                            line.substring(
                                    "Username:".length()
                            )
                            .trim();

                    if (
                            username.equalsIgnoreCase(
                                    currentUsername
                            )
                    ) {

                        insideUserRecord = true;

                        userFound = true;

                        profile =
                                new StringBuilder();

                        profile.append(
                                line
                        );

                        profile.append(
                                "\n"
                        );

                    } else {

                        insideUserRecord = false;
                    }

                    continue;
                }

                // =================================================
                // ADD CURRENT USER'S DATA
                // =================================================

                if (
                        insideUserRecord
                ) {

                    // Do not display password
                    if (
                            !line
                                    .toLowerCase()
                                    .startsWith(
                                            "password:"
                                    )
                    ) {

                        profile.append(
                                line
                        );

                        profile.append(
                                "\n"
                        );
                    }

                    // =================================================
                    // END OF CURRENT USER RECORD
                    // =================================================

                    if (
                            line.equals(
                                    "========================================"
                            )
                    ) {

                        break;
                    }
                }
            }

            reader.close();

        } catch (
                IOException e
        ) {

            JOptionPane.showMessageDialog(
                    this,

                    "Unable to read user information.\n\n"
                            + e.getMessage(),

                    "J.A.R.V.I.S. ERROR",

                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }

        // =====================================================
        // USER NOT FOUND
        // =====================================================

        if (
                !userFound
        ) {

            JOptionPane.showMessageDialog(
                    this,

                    "Your profile could not be found.",

                    "J.A.R.V.I.S.",

                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        // =====================================================
        // PROFILE TEXT AREA
        // =====================================================

        JTextArea textArea =
                new JTextArea(
                        profile.toString()
                );

        textArea.setFont(
                new Font(
                        "Monospaced",
                        Font.PLAIN,
                        13
                )
        );

        textArea.setForeground(
                WHITE
        );

        textArea.setBackground(
                new Color(
                        10,
                        22,
                        35
                )
        );

        textArea.setEditable(
                false
        );

        textArea.setLineWrap(
                true
        );

        textArea.setWrapStyleWord(
                true
        );

        textArea.setBorder(
                BorderFactory.createEmptyBorder(
                        15,
                        15,
                        15,
                        15
                )
        );

        // =====================================================
        // SCROLL PANE
        // =====================================================

        JScrollPane scrollPane =
                new JScrollPane(
                        textArea
                );

        scrollPane.setPreferredSize(
                new Dimension(
                        450,
                        300
                )
        );

        // =====================================================
        // SHOW PROFILE
        // =====================================================

        JOptionPane.showMessageDialog(
                this,

                scrollPane,

                "J.A.R.V.I.S. - My Profile",

                JOptionPane.INFORMATION_MESSAGE
        );
    }

    // =========================================================
    // SAVE SETTINGS
    // =========================================================

    private void saveSettings() {

        boolean notifications =
                notificationCheckBox.isSelected();

        boolean sounds =
                soundCheckBox.isSelected();

        try {

            PrintWriter writer =
                    new PrintWriter(
                            new FileWriter(
                                    "settings.txt"
                            )
                    );

            writer.println(
                    "J.A.R.V.I.S. SETTINGS"
            );

            writer.println(
                    "===================="
            );

            writer.println();

            writer.println(
                    "Notifications="
                            + notifications
            );

            writer.println(
                    "SoundEffects="
                            + sounds
            );

            writer.close();

            JOptionPane.showMessageDialog(
                    this,

                    "Settings saved successfully!",

                    "J.A.R.V.I.S.",

                    JOptionPane.INFORMATION_MESSAGE
            );

        } catch (
                IOException e
        ) {

            JOptionPane.showMessageDialog(
                    this,

                    "Unable to save settings.\n\n"
                            + e.getMessage(),

                    "J.A.R.V.I.S. ERROR",

                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =========================================================
    // LOAD SETTINGS
    // =========================================================

    private void loadSettings() {

        File file =
                new File(
                        "settings.txt"
                );

        // =====================================================
        // DEFAULT VALUES
        // =====================================================

        notificationCheckBox.setSelected(
                true
        );

        soundCheckBox.setSelected(
                true
        );

        if (
                !file.exists()
        ) {

            return;
        }

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

                // =================================================
                // NOTIFICATIONS
                // =================================================

                if (
                        line.startsWith(
                                "Notifications="
                        )
                ) {

                    String value =
                            line.substring(
                                    "Notifications=".length()
                            );

                    notificationCheckBox.setSelected(
                            Boolean.parseBoolean(
                                    value
                            )
                    );
                }

                // =================================================
                // SOUND EFFECTS
                // =================================================

                if (
                        line.startsWith(
                                "SoundEffects="
                        )
                ) {

                    String value =
                            line.substring(
                                    "SoundEffects=".length()
                            );

                    soundCheckBox.setSelected(
                            Boolean.parseBoolean(
                                    value
                            )
                    );
                }
            }

            reader.close();

        } catch (
                IOException e
        ) {

            System.out.println(
                    "Could not load settings."
            );
        }
    }

    // =========================================================
    // ABOUT J.A.R.V.I.S.
    // =========================================================

    private void showAbout() {

        String message =
                "J.A.R.V.I.S.\n\n"
                        + "JUST A RATHER VERY "
                        + "INTELLIGENT SYSTEM\n\n"
                        + "Version: 1.0\n\n"
                        + "A Java-based desktop "
                        + "assistant application "
                        + "developed using Java Swing.\n\n"
                        + "Modules include:\n"
                        + "• Calculator\n"
                        + "• Notes\n"
                        + "• Timer\n"
                        + "• Java Quiz\n"
                        + "• Dino Game\n"
                        + "• System Information\n"
                        + "• Study Planner\n"
                        + "• Daily Progress\n"
                        + "• Settings";

        JOptionPane.showMessageDialog(
                this,

                message,

                "About J.A.R.V.I.S.",

                JOptionPane.INFORMATION_MESSAGE
        );
    }
}
