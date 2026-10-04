import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class Notes extends JFrame {

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
    // COMPONENTS
    // =========================================================

    private JTextArea noteArea;

    private DefaultListModel<String> listModel;

    private JList<String> notesList;

    // =========================================================
    // FILE
    // =========================================================

    private final String NOTES_FILE =
            "Notes.txt";

    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public Notes() {

        setTitle(
                "J.A.R.V.I.S. - Notes"
        );

        setSize(
                750,
                650
        );

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        setLocationRelativeTo(null);

        setResizable(false);

        createGUI();

        loadUserNotes();

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
                        "J.A.R.V.I.S. NOTES",
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

        String userName =
                CurrentUser.getName();

        if (userName == null) {

            userName =
                    "USER";
        }

        JLabel subtitle =
                new JLabel(
                        "PERSONAL NOTES • "
                                + userName,
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
                                1,
                                2,
                                15,
                                0
                        )
                );

        mainPanel.setBackground(
                BACKGROUND
        );

        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        10,
                        25,
                        10,
                        25
                )
        );

        // =====================================================
        // LEFT SIDE - NOTE LIST
        // =====================================================

        JPanel listPanel =
                new JPanel(
                        new BorderLayout(
                                5,
                                5
                        )
                );

        listPanel.setBackground(
                PANEL
        );

        listPanel.setBorder(
                BorderFactory.createTitledBorder(

                        BorderFactory.createLineBorder(
                                CYAN
                        ),

                        "MY NOTES",

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

        listModel =
                new DefaultListModel<>();

        notesList =
                new JList<>(
                        listModel
                );

        notesList.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        notesList.setForeground(
                WHITE
        );

        notesList.setBackground(
                new Color(
                        10,
                        22,
                        35
                )
        );

        notesList.setSelectionBackground(
                new Color(
                        25,
                        80,
                        105
                )
        );

        notesList.setSelectionForeground(
                WHITE
        );

        notesList.setFixedCellHeight(
                35
        );

        JScrollPane listScrollPane =
                new JScrollPane(
                        notesList
                );

        listScrollPane.setBorder(
                BorderFactory.createLineBorder(
                        new Color(
                                40,
                                80,
                                110
                        )
                )
        );

        listPanel.add(
                listScrollPane,
                BorderLayout.CENTER
        );

        mainPanel.add(
                listPanel
        );

        // =====================================================
        // RIGHT SIDE - NOTE EDITOR
        // =====================================================

        JPanel editorPanel =
                new JPanel(
                        new BorderLayout(
                                5,
                                5
                        )
                );

        editorPanel.setBackground(
                PANEL
        );

        editorPanel.setBorder(
                BorderFactory.createTitledBorder(

                        BorderFactory.createLineBorder(
                                CYAN
                        ),

                        "NOTE CONTENT",

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

        noteArea =
                new JTextArea();

        noteArea.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        15
                )
        );

        noteArea.setForeground(
                WHITE
        );

        noteArea.setBackground(
                new Color(
                        10,
                        22,
                        35
                )
        );

        noteArea.setCaretColor(
                CYAN
        );

        noteArea.setLineWrap(
                true
        );

        noteArea.setWrapStyleWord(
                true
        );

        noteArea.setBorder(
                BorderFactory.createEmptyBorder(
                        10,
                        10,
                        10,
                        10
                )
        );

        JScrollPane noteScrollPane =
                new JScrollPane(
                        noteArea
                );

        noteScrollPane.setBorder(
                BorderFactory.createLineBorder(
                        new Color(
                                40,
                                80,
                                110
                        )
                )
        );

        editorPanel.add(
                noteScrollPane,
                BorderLayout.CENTER
        );

        mainPanel.add(
                editorPanel
        );

        add(
                mainPanel,
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

        JButton newButton =
                createButton(
                        "NEW"
                );

        JButton saveButton =
                createButton(
                        "SAVE"
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
                newButton
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
        // BUTTON ACTIONS
        // =====================================================

        newButton.addActionListener(
                e -> {

                    notesList.clearSelection();

                    noteArea.setText("");

                    noteArea.requestFocus();
                }
        );

        saveButton.addActionListener(
                e -> saveNote()
        );

        deleteButton.addActionListener(
                e -> deleteNote()
        );

        clearButton.addActionListener(
                e -> noteArea.setText("")
        );

        closeButton.addActionListener(
                e -> dispose()
        );

        // =====================================================
        // LIST SELECTION
        // =====================================================

        notesList.addListSelectionListener(
                e -> {

                    if (!e.getValueIsAdjusting()) {

                        loadSelectedNote();
                    }
                }
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
    // SAVE NOTE
    // =========================================================

    private void saveNote() {

        String username =
                CurrentUser.getUsername();

        if (username == null
                || username.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,

                    "No active user found.\n"
                            + "Please register or login first.",

                    "J.A.R.V.I.S. ERROR",

                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }

        String note =
                noteArea
                        .getText()
                        .trim();

        if (note.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,

                    "Please enter a note first.",

                    "J.A.R.V.I.S. WARNING",

                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        try {

            // =================================================
            // APPEND NOTE
            // =================================================

            FileWriter fileWriter =
                    new FileWriter(
                            NOTES_FILE,
                            true
                    );

            PrintWriter writer =
                    new PrintWriter(
                            fileWriter
                    );

            writer.println(
                    "USER:" + username
            );

            writer.println(
                    "NOTE:" + note.replace(
                            "\n",
                            "\\n"
                    )
            );

            writer.println(
                    "END_NOTE"
            );

            writer.close();

            JOptionPane.showMessageDialog(
                    this,

                    "Note saved successfully!",

                    "J.A.R.V.I.S. NOTES",

                    JOptionPane.INFORMATION_MESSAGE
            );

            noteArea.setText("");

            notesList.clearSelection();

            loadUserNotes();

        } catch (
                IOException e
        ) {

            JOptionPane.showMessageDialog(
                    this,

                    "Unable to save note.\n\n"
                            + e.getMessage(),

                    "J.A.R.V.I.S. ERROR",

                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =========================================================
    // LOAD CURRENT USER'S NOTES
    // =========================================================

    private void loadUserNotes() {

        listModel.clear();

        String username =
                CurrentUser.getUsername();

        if (username == null
                || username.isEmpty()) {

            return;
        }

        File file =
                new File(
                        NOTES_FILE
                );

        if (!file.exists()) {

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

            String currentUser = null;

            String currentNote = null;

            while (
                    (line = reader.readLine())
                            != null
            ) {

                if (
                        line.startsWith(
                                "USER:"
                        )
                ) {

                    currentUser =
                            line.substring(
                                    5
                            );
                }

                else if (
                        line.startsWith(
                                "NOTE:"
                        )
                ) {

                    currentNote =
                            line.substring(
                                    5
                            );

                    currentNote =
                            currentNote.replace(
                                    "\\n",
                                    "\n"
                            );
                }

                else if (
                        line.equals(
                                "END_NOTE"
                        )
                ) {

                    if (
                            username.equals(
                                    currentUser
                            )
                    ) {

                        listModel.addElement(
                                createPreview(
                                        currentNote
                                )
                        );
                    }

                    currentUser = null;

                    currentNote = null;
                }
            }

            reader.close();

        } catch (
                IOException e
        ) {

            JOptionPane.showMessageDialog(
                    this,

                    "Unable to load notes.\n\n"
                            + e.getMessage(),

                    "J.A.R.V.I.S. ERROR",

                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =========================================================
    // CREATE NOTE PREVIEW
    // =========================================================

    private String createPreview(
            String note
    ) {

        if (note == null) {

            return "";
        }

        String preview =
                note.replace(
                        "\n",
                        " "
                );

        if (
                preview.length() > 35
        ) {

            preview =
                    preview.substring(
                            0,
                            35
                    )
                    + "...";
        }

        return preview;
    }

    // =========================================================
    // LOAD SELECTED NOTE
    // =========================================================

    private void loadSelectedNote() {

        int selectedIndex =
                notesList
                        .getSelectedIndex();

        if (selectedIndex < 0) {

            return;
        }

        String username =
                CurrentUser.getUsername();

        if (username == null) {

            return;
        }

        ArrayList<String> userNotes =
                getUserNotes();

        if (
                selectedIndex <
                userNotes.size()
        ) {

            noteArea.setText(
                    userNotes.get(
                            selectedIndex
                    )
            );
        }
    }

    // =========================================================
    // GET CURRENT USER'S NOTES
    // =========================================================

    private ArrayList<String> getUserNotes() {

        ArrayList<String> userNotes =
                new ArrayList<>();

        String username =
                CurrentUser.getUsername();

        if (
                username == null
                || username.isEmpty()
        ) {

            return userNotes;
        }

        File file =
                new File(
                        NOTES_FILE
                );

        if (!file.exists()) {

            return userNotes;
        }

        try {

            BufferedReader reader =
                    new BufferedReader(
                            new FileReader(
                                    file
                            )
                    );

            String line;

            String currentUser = null;

            String currentNote = null;

            while (
                    (line = reader.readLine())
                            != null
            ) {

                if (
                        line.startsWith(
                                "USER:"
                        )
                ) {

                    currentUser =
                            line.substring(
                                    5
                            );
                }

                else if (
                        line.startsWith(
                                "NOTE:"
                        )
                ) {

                    currentNote =
                            line.substring(
                                    5
                            );

                    currentNote =
                            currentNote.replace(
                                    "\\n",
                                    "\n"
                            );
                }

                else if (
                        line.equals(
                                "END_NOTE"
                        )
                ) {

                    if (
                            username.equals(
                                    currentUser
                            )
                    ) {

                        userNotes.add(
                                currentNote
                        );
                    }

                    currentUser = null;

                    currentNote = null;
                }
            }

            reader.close();

        } catch (
                IOException e
        ) {

            e.printStackTrace();
        }

        return userNotes;
    }

    // =========================================================
    // DELETE SELECTED NOTE
    // =========================================================

    private void deleteNote() {

        int selectedIndex =
                notesList
                        .getSelectedIndex();

        if (selectedIndex < 0) {

            JOptionPane.showMessageDialog(
                    this,

                    "Please select a note to delete.",

                    "J.A.R.V.I.S. WARNING",

                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        int choice =
                JOptionPane.showConfirmDialog(
                        this,

                        "Delete this note?",

                        "Confirm Delete",

                        JOptionPane.YES_NO_OPTION
                );

        if (
                choice !=
                JOptionPane.YES_OPTION
        ) {

            return;
        }

        ArrayList<NoteRecord> allNotes =
                readAllNotes();

        String username =
                CurrentUser.getUsername();

        int userIndex = 0;

        ArrayList<NoteRecord> updatedNotes =
                new ArrayList<>();

        for (
                NoteRecord record :
                allNotes
        ) {

            if (
                    username.equals(
                            record.username
                    )
            ) {

                if (
                        userIndex ==
                        selectedIndex
                ) {

                    userIndex++;

                    continue;
                }

                userIndex++;
            }

            updatedNotes.add(
                    record
            );
        }

        writeAllNotes(
                updatedNotes
        );

        noteArea.setText("");

        notesList.clearSelection();

        loadUserNotes();

        JOptionPane.showMessageDialog(
                this,

                "Note deleted successfully.",

                "J.A.R.V.I.S. NOTES",

                JOptionPane.INFORMATION_MESSAGE
        );
    }

    // =========================================================
    // NOTE RECORD CLASS
    // =========================================================

    private static class NoteRecord {

        String username;

        String note;

        NoteRecord(
                String username,
                String note
        ) {

            this.username =
                    username;

            this.note =
                    note;
        }
    }

    // =========================================================
    // READ ALL NOTES
    // =========================================================

    private ArrayList<NoteRecord> readAllNotes() {

        ArrayList<NoteRecord> notes =
                new ArrayList<>();

        File file =
                new File(
                        NOTES_FILE
                );

        if (!file.exists()) {

            return notes;
        }

        try {

            BufferedReader reader =
                    new BufferedReader(
                            new FileReader(
                                    file
                            )
                    );

            String line;

            String currentUser = null;

            String currentNote = null;

            while (
                    (line = reader.readLine())
                            != null
            ) {

                if (
                        line.startsWith(
                                "USER:"
                        )
                ) {

                    currentUser =
                            line.substring(
                                    5
                            );
                }

                else if (
                        line.startsWith(
                                "NOTE:"
                        )
                ) {

                    currentNote =
                            line.substring(
                                    5
                            );

                    currentNote =
                            currentNote.replace(
                                    "\\n",
                                    "\n"
                            );
                }

                else if (
                        line.equals(
                                "END_NOTE"
                        )
                ) {

                    if (
                            currentUser != null
                            && currentNote != null
                    ) {

                        notes.add(
                                new NoteRecord(
                                        currentUser,
                                        currentNote
                                )
                        );
                    }

                    currentUser = null;

                    currentNote = null;
                }
            }

            reader.close();

        } catch (
                IOException e
        ) {

            e.printStackTrace();
        }

        return notes;
    }

    // =========================================================
    // WRITE ALL NOTES
    // =========================================================

    private void writeAllNotes(
            ArrayList<NoteRecord> notes
    ) {

        try {

            PrintWriter writer =
                    new PrintWriter(
                            new FileWriter(
                                    NOTES_FILE,
                                    false
                            )
                    );

            for (
                    NoteRecord record :
                    notes
            ) {

                writer.println(
                        "USER:" +
                                record.username
                );

                writer.println(
                        "NOTE:" +
                                record.note
                                        .replace(
                                                "\n",
                                                "\\n"
                                        )
                );

                writer.println(
                        "END_NOTE"
                );
            }

            writer.close();

        } catch (
                IOException e
        ) {

            JOptionPane.showMessageDialog(
                    this,

                    "Unable to update Notes.txt.\n\n"
                            + e.getMessage(),

                    "J.A.R.V.I.S. ERROR",

                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}
