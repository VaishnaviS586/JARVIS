import javax.swing.*;
import java.awt.*;
import java.io.File;

public class SystemInfo extends JFrame {

    // =========================================================
    // COLORS
    // =========================================================

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

    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public SystemInfo() {

        setTitle(
                "J.A.R.V.I.S. - System Information"
        );

        setSize(
                700,
                650
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
                        "SYSTEM INFORMATION",
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
                        "J.A.R.V.I.S. SYSTEM DIAGNOSTICS",
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
        // INFORMATION PANEL
        // =====================================================

        JPanel informationPanel =
                new JPanel(
                        new BorderLayout()
                );

        informationPanel.setBackground(
                PANEL
        );

        informationPanel.setBorder(
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

        // =====================================================
        // TEXT AREA
        // =====================================================

        JTextArea informationArea =
                new JTextArea();

        informationArea.setFont(
                new Font(
                        "Monospaced",
                        Font.PLAIN,
                        14
                )
        );

        informationArea.setForeground(
                WHITE
        );

        informationArea.setBackground(
                new Color(
                        10,
                        22,
                        35
                )
        );

        informationArea.setEditable(
                false
        );

        informationArea.setLineWrap(
                true
        );

        informationArea.setWrapStyleWord(
                true
        );

        informationArea.setBorder(
                BorderFactory.createEmptyBorder(
                        15,
                        15,
                        15,
                        15
                )
        );

        // =====================================================
        // GET SYSTEM INFORMATION
        // =====================================================

        String systemInfo =
                generateSystemInfo();

        informationArea.setText(
                systemInfo
        );

        JScrollPane scrollPane =
                new JScrollPane(
                        informationArea
                );

        scrollPane.setBorder(
                BorderFactory.createLineBorder(
                        new Color(
                                40,
                                80,
                                110
                        )
                )
        );

        informationPanel.add(
                scrollPane,
                BorderLayout.CENTER
        );

        add(
                informationPanel,
                BorderLayout.CENTER
        );

        // =====================================================
        // BUTTON PANEL
        // =====================================================

        JPanel buttonPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.CENTER,
                                15,
                                15
                        )
                );

        buttonPanel.setBackground(
                BACKGROUND
        );

        JButton refreshButton =
                createButton(
                        "REFRESH"
                );

        JButton closeButton =
                createButton(
                        "CLOSE"
                );

        // =====================================================
        // REFRESH ACTION
        // =====================================================

        refreshButton.addActionListener(
                e -> {

                    informationArea.setText(
                            generateSystemInfo()
                    );

                }
        );

        // =====================================================
        // CLOSE ACTION
        // =====================================================

        closeButton.addActionListener(
                e -> dispose()
        );

        buttonPanel.add(
                refreshButton
        );

        buttonPanel.add(
                closeButton
        );

        add(
                buttonPanel,
                BorderLayout.SOUTH
        );
    }

    // =========================================================
    // GENERATE SYSTEM INFORMATION
    // =========================================================

    private String generateSystemInfo() {

        StringBuilder info =
                new StringBuilder();

        // =====================================================
        // OPERATING SYSTEM
        // =====================================================

        info.append(
                "==============================================\n"
        );

        info.append(
                "          J.A.R.V.I.S. SYSTEM REPORT\n"
        );

        info.append(
                "==============================================\n\n"
        );

        info.append(
                "OPERATING SYSTEM\n"
        );

        info.append(
                "----------------------------------------------\n"
        );

        info.append(
                "OS Name          : "
        );

        info.append(
                System.getProperty(
                        "os.name"
                )
        );

        info.append(
                "\n"
        );

        info.append(
                "OS Version       : "
        );

        info.append(
                System.getProperty(
                        "os.version"
                )
        );

        info.append(
                "\n"
        );

        info.append(
                "Architecture     : "
        );

        info.append(
                System.getProperty(
                        "os.arch"
                )
        );

        info.append(
                "\n\n"
        );

        // =====================================================
        // JAVA INFORMATION
        // =====================================================

        info.append(
                "JAVA ENVIRONMENT\n"
        );

        info.append(
                "----------------------------------------------\n"
        );

        info.append(
                "Java Version     : "
        );

        info.append(
                System.getProperty(
                        "java.version"
                )
        );

        info.append(
                "\n"
        );

        info.append(
                "Java Vendor      : "
        );

        info.append(
                System.getProperty(
                        "java.vendor"
                )
        );

        info.append(
                "\n"
        );

        info.append(
                "Java Home        : "
        );

        info.append(
                System.getProperty(
                        "java.home"
                )
        );

        info.append(
                "\n\n"
        );

        // =====================================================
        // PROCESSOR INFORMATION
        // =====================================================

        info.append(
                "PROCESSOR\n"
        );

        info.append(
                "----------------------------------------------\n"
        );

        info.append(
                "Available Cores  : "
        );

        info.append(
                Runtime
                        .getRuntime()
                        .availableProcessors()
        );

        info.append(
                "\n\n"
        );

        // =====================================================
        // MEMORY INFORMATION
        // =====================================================

        Runtime runtime =
                Runtime.getRuntime();

        long totalMemory =
                runtime.totalMemory();

        long freeMemory =
                runtime.freeMemory();

        long maxMemory =
                runtime.maxMemory();

        long usedMemory =
                totalMemory -
                freeMemory;

        info.append(
                "MEMORY\n"
        );

        info.append(
                "----------------------------------------------\n"
        );

        info.append(
                "Used Memory      : "
        );

        info.append(
                formatMemory(
                        usedMemory
                )
        );

        info.append(
                "\n"
        );

        info.append(
                "Free Memory      : "
        );

        info.append(
                formatMemory(
                        freeMemory
                )
        );

        info.append(
                "\n"
        );

        info.append(
                "Allocated Memory  : "
        );

        info.append(
                formatMemory(
                        totalMemory
                )
        );

        info.append(
                "\n"
        );

        info.append(
                "Maximum Memory   : "
        );

        info.append(
                formatMemory(
                        maxMemory
                )
        );

        info.append(
                "\n\n"
        );

        // =====================================================
        // USER INFORMATION
        // =====================================================

        info.append(
                "USER ENVIRONMENT\n"
        );

        info.append(
                "----------------------------------------------\n"
        );

        info.append(
                "User Name        : "
        );

        info.append(
                System.getProperty(
                        "user.name"
                )
        );

        info.append(
                "\n"
        );

        info.append(
                "User Home        : "
        );

        info.append(
                System.getProperty(
                        "user.home"
                )
        );

        info.append(
                "\n"
        );

        info.append(
                "Current Folder   : "
        );

        info.append(
                System.getProperty(
                        "user.dir"
                )
        );

        info.append(
                "\n\n"
        );

        // =====================================================
        // FILE SYSTEM
        // =====================================================

        File currentFolder =
                new File(
                        System.getProperty(
                                "user.dir"
                        )
                );

        info.append(
                "FILE SYSTEM\n"
        );

        info.append(
                "----------------------------------------------\n"
        );

        info.append(
                "Project Folder   : "
        );

        info.append(
                currentFolder
                        .getAbsolutePath()
        );

        info.append(
                "\n"
        );

        info.append(
                "Disk Space Total : "
        );

        info.append(
                formatMemory(
                        currentFolder
                                .getTotalSpace()
                )
        );

        info.append(
                "\n"
        );

        info.append(
                "Disk Space Free  : "
        );

        info.append(
                formatMemory(
                        currentFolder
                                .getFreeSpace()
                )
        );

        info.append(
                "\n\n"
        );

        // =====================================================
        // STATUS
        // =====================================================

        info.append(
                "SYSTEM STATUS\n"
        );

        info.append(
                "----------------------------------------------\n"
        );

        info.append(
                "J.A.R.V.I.S.    : ONLINE\n"
        );

        info.append(
                "Java Runtime    : ACTIVE\n"
        );

        info.append(
                "Diagnostics     : COMPLETE\n"
        );

        info.append(
                "\n"
        );

        info.append(
                "==============================================\n"
        );

        info.append(
                "             SYSTEM READY\n"
        );

        info.append(
                "==============================================\n"
        );

        return info.toString();
    }

    // =========================================================
    // FORMAT MEMORY
    // =========================================================

    private String formatMemory(
            long bytes
    ) {

        double megabytes =
                bytes /
                (1024.0 * 1024.0);

        double gigabytes =
                bytes /
                (1024.0 * 1024.0 * 1024.0);

        if (gigabytes >= 1) {

            return String.format(
                    "%.2f GB",
                    gigabytes
            );

        }

        return String.format(
                "%.2f MB",
                megabytes
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
}
