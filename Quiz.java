import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Quiz extends JFrame {

    // =====================================================
    // COLORS
    // =====================================================

    private final Color BACKGROUND = new Color(8, 15, 25);
    private final Color PANEL = new Color(15, 28, 45);
    private final Color BUTTON = new Color(20, 38, 60);
    private final Color CYAN = new Color(0, 220, 255);
    private final Color GREEN = new Color(50, 220, 120);
    private final Color WHITE = new Color(240, 245, 250);

    // =====================================================
    // QUIZ VARIABLES
    // =====================================================

    private List<Question> questions = new ArrayList<>();

    private int currentQuestion = 0;
    private int score = 0;

    private int[] selectedAnswers;

    private String selectedSubject;

    // =====================================================
    // GUI COMPONENTS
    // =====================================================

    private JLabel questionNumberLabel;
    private JLabel questionLabel;
    private JLabel scoreLabel;
    private JLabel progressLabel;

    private JRadioButton optionA;
    private JRadioButton optionB;
    private JRadioButton optionC;
    private JRadioButton optionD;

    private ButtonGroup optionGroup;

    private JButton previousButton;
    private JButton nextButton;

    // =====================================================
    // CONSTRUCTOR
    // =====================================================

    public Quiz() {
        showSubjectSelection();
    }

    // =====================================================
    // SUBJECT SELECTION
    // =====================================================

    private void showSubjectSelection() {

        JFrame subjectFrame =
                new JFrame("J.A.R.V.I.S. - Select Quiz");

        subjectFrame.setSize(700, 600);

        subjectFrame.setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        subjectFrame.setLocationRelativeTo(null);

        subjectFrame.setLayout(
                new BorderLayout()
        );

        subjectFrame.getContentPane()
                .setBackground(BACKGROUND);

        // -------------------------------------------------
        // HEADER
        // -------------------------------------------------

        JPanel header =
                new JPanel(new BorderLayout());

        header.setBackground(BACKGROUND);

        header.setBorder(
                BorderFactory.createEmptyBorder(
                        25, 20, 20, 20
                )
        );

        JLabel title =
                new JLabel(
                        "J.A.R.V.I.S. QUIZ",
                        SwingConstants.CENTER
                );

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        32
                )
        );

        title.setForeground(CYAN);

        JLabel subtitle =
                new JLabel(
                        "SELECT A SUBJECT",
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

        subjectFrame.add(
                header,
                BorderLayout.NORTH
        );

        // -------------------------------------------------
        // SUBJECT BUTTONS
        // -------------------------------------------------

        JPanel subjectPanel =
                new JPanel(
                        new GridLayout(
                                0,
                                2,
                                15,
                                15
                        )
                );

        subjectPanel.setBackground(BACKGROUND);

        subjectPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        20,
                        50,
                        30,
                        50
                )
        );

        String[] subjects = {

                "GK",
                "Space Science",
                "Mathematics",
                "Algebra",
                "Computer Science",
                "Python",
                "AI",
                "Data Science",
                "Prompting",
                "Machine Learning",
                "Frontend Development",
                "Git",
                "HTML"
        };

        for (String subject : subjects) {

            JButton button =
                    createSubjectButton(subject);

            button.addActionListener(e -> {

                String fileName =
                        getFileName(subject);

                if (loadQuestions(fileName)) {

                    selectedSubject =
                            subject;

                    subjectFrame.dispose();

                    createQuizWindow();
                }
            });

            subjectPanel.add(button);
        }

        subjectFrame.add(
                subjectPanel,
                BorderLayout.CENTER
        );

        subjectFrame.setVisible(true);
    }

    // =====================================================
    // FILE NAME
    // =====================================================

    private String getFileName(
            String subject
    ) {

        switch (subject) {

            case "GK":
                return "GK.txt";

            case "Space Science":
                return "SpaceScience.txt";

            case "Mathematics":
                return "Mathematics.txt";

            case "Algebra":
                return "Algebra.txt";

            case "Computer Science":
                return "ComputerScience.txt";

            case "Python":
                return "Python.txt";

            case "AI":
                return "AI.txt";

            case "Data Science":
                return "DataScience.txt";

            case "Prompting":
                return "Prompting.txt";

            case "Machine Learning":
                return "MachineLearning.txt";

            case "Frontend Development":
                return "FrontendDevelopment.txt";

            case "Git":
                return "Git.txt";

            case "HTML":
                return "HTML.txt";

            default:
                return "";
        }
    }

    // =====================================================
    // LOAD QUESTIONS
    // =====================================================

    private boolean loadQuestions(
            String fileName
    ) {

        questions.clear();

        BufferedReader reader = null;

        try {

            reader = findQuizFile(fileName);

            if (reader == null) {

                JOptionPane.showMessageDialog(
                        null,
                        "Quiz file not found:\n\n"
                                + fileName
                                + "\n\n"
                                + "Please place the quiz files "
                                + "inside a folder named:\n"
                                + "quiz_data\n\n"
                                + "Example:\n"
                                + "quiz_data/"
                                + fileName,
                        "J.A.R.V.I.S. ERROR",
                        JOptionPane.ERROR_MESSAGE
                );

                return false;
            }

            String line;

            String questionText = null;

            String[] options =
                    new String[4];

            String correctAnswer = null;

            while (
                    (line = reader.readLine()) != null
            ) {

                line = line.trim();

                if (line.isEmpty()) {
                    continue;
                }

                if (line.startsWith("Q:")) {

                    questionText =
                            line.substring(2).trim();

                } else if (line.startsWith("A:")) {

                    options[0] =
                            line.substring(2).trim();

                } else if (line.startsWith("B:")) {

                    options[1] =
                            line.substring(2).trim();

                } else if (line.startsWith("C:")) {

                    options[2] =
                            line.substring(2).trim();

                } else if (line.startsWith("D:")) {

                    options[3] =
                            line.substring(2).trim();

                } else if (line.startsWith("ANSWER:")) {

                    correctAnswer =
                            line.substring(7).trim();

                    if (
                            questionText != null
                            && correctAnswer != null
                            && options[0] != null
                            && options[1] != null
                            && options[2] != null
                            && options[3] != null
                    ) {

                        int correctIndex =
                                getAnswerIndex(
                                        correctAnswer
                                );

                        if (correctIndex >= 0) {

                            questions.add(
                                    new Question(
                                            questionText,
                                            options,
                                            correctIndex
                                    )
                            );
                        }

                        questionText = null;

                        options =
                                new String[4];

                        correctAnswer = null;
                    }
                }
            }

            reader.close();

        } catch (IOException e) {

            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException ignored) {
            }

            JOptionPane.showMessageDialog(
                    null,
                    "Error reading quiz file:\n\n"
                            + e.getMessage(),
                    "J.A.R.V.I.S. ERROR",
                    JOptionPane.ERROR_MESSAGE
            );

            return false;
        }

        if (questions.isEmpty()) {

            JOptionPane.showMessageDialog(
                    null,
                    "No valid questions found in:\n"
                            + fileName
                            + "\n\n"
                            + "Check the TXT file format.",
                    "J.A.R.V.I.S. ERROR",
                    JOptionPane.ERROR_MESSAGE
            );

            return false;
        }

        return true;
    }

    // =====================================================
    // FIND QUIZ FILE
    // =====================================================

    private BufferedReader findQuizFile(
            String fileName
    ) throws IOException {

        /*
         * JARVIS will check several possible locations.
         */

        String[] possiblePaths = {

                "quiz_data" + File.separator + fileName,

                "src" + File.separator
                        + "quiz_data"
                        + File.separator
                        + fileName,

                "src" + File.separator
                        + "JARVIS"
                        + File.separator
                        + "quiz_data"
                        + File.separator
                        + fileName,

                "JARVIS" + File.separator
                        + "quiz_data"
                        + File.separator
                        + fileName
        };

        // -------------------------------------------------
        // CHECK NORMAL FILE LOCATIONS
        // -------------------------------------------------

        for (String path : possiblePaths) {

            File file = new File(path);

            if (file.exists() && file.isFile()) {

                System.out.println(
                        "Quiz file found: "
                                + file.getAbsolutePath()
                );

                return new BufferedReader(
                        new InputStreamReader(
                                new FileInputStream(file),
                                StandardCharsets.UTF_8
                        )
                );
            }
        }

        // -------------------------------------------------
        // CHECK CLASSPATH
        // -------------------------------------------------

        String resourcePath =
                "/quiz_data/" + fileName;

        InputStream input =
                getClass().getResourceAsStream(
                        resourcePath
                );

        if (input != null) {

            System.out.println(
                    "Quiz file found in resources: "
                            + resourcePath
            );

            return new BufferedReader(
                    new InputStreamReader(
                            input,
                            StandardCharsets.UTF_8
                    )
            );
        }

        // -------------------------------------------------
        // FILE NOT FOUND
        // -------------------------------------------------

        System.out.println(
                "Could not find quiz file: "
                        + fileName
        );

        System.out.println(
                "Current working directory:"
        );

        System.out.println(
                new File(".")
                        .getAbsolutePath()
        );

        return null;
    }

    // =====================================================
    // ANSWER INDEX
    // =====================================================

    private int getAnswerIndex(
            String answer
    ) {

        switch (
                answer.toUpperCase()
        ) {

            case "A":
                return 0;

            case "B":
                return 1;

            case "C":
                return 2;

            case "D":
                return 3;

            default:
                return -1;
        }
    }

    // =====================================================
    // CREATE QUIZ WINDOW
    // =====================================================

    private void createQuizWindow() {

        currentQuestion = 0;

        score = 0;

        selectedAnswers =
                new int[questions.size()];

        for (
                int i = 0;
                i < selectedAnswers.length;
                i++
        ) {

            selectedAnswers[i] = -1;
        }

        setTitle(
                "J.A.R.V.I.S. - "
                        + selectedSubject
        );

        setSize(800, 650);

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        setLocationRelativeTo(null);

        setLayout(
                new BorderLayout()
        );

        getContentPane()
                .setBackground(BACKGROUND);

        // =================================================
        // HEADER
        // =================================================

        JPanel header =
                new JPanel(
                        new BorderLayout()
                );

        header.setBackground(
                BACKGROUND
        );

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
                        "J.A.R.V.I.S. - "
                                + selectedSubject
                                + " QUIZ",
                        SwingConstants.CENTER
                );

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        27
                )
        );

        title.setForeground(CYAN);

        JLabel subtitle =
                new JLabel(
                        "TEST YOUR KNOWLEDGE",
                        SwingConstants.CENTER
                );

        subtitle.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        13
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

        // =================================================
        // CENTER
        // =================================================

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
                        45,
                        10,
                        45
                )
        );

        questionNumberLabel =
                new JLabel(
                        "",
                        SwingConstants.CENTER
                );

        questionNumberLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        16
                )
        );

        questionNumberLabel.setForeground(
                GREEN
        );

        centerPanel.add(
                questionNumberLabel,
                BorderLayout.NORTH
        );

        // =================================================
        // QUESTION
        // =================================================

        questionLabel =
                new JLabel(
                        "",
                        SwingConstants.CENTER
                );

        questionLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        19
                )
        );

        questionLabel.setForeground(
                WHITE
        );

        questionLabel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                CYAN
                        ),
                        BorderFactory.createEmptyBorder(
                                25,
                                20,
                                25,
                                20
                        )
                )
        );

        centerPanel.add(
                questionLabel,
                BorderLayout.CENTER
        );

        // =================================================
        // OPTIONS
        // =================================================

        JPanel optionsPanel =
                new JPanel(
                        new GridLayout(
                                4,
                                1,
                                10,
                                10
                        )
                );

        optionsPanel.setBackground(
                BACKGROUND
        );

        optionA =
                createOptionButton();

        optionB =
                createOptionButton();

        optionC =
                createOptionButton();

        optionD =
                createOptionButton();

        optionGroup =
                new ButtonGroup();

        optionGroup.add(optionA);
        optionGroup.add(optionB);
        optionGroup.add(optionC);
        optionGroup.add(optionD);

        optionsPanel.add(optionA);
        optionsPanel.add(optionB);
        optionsPanel.add(optionC);
        optionsPanel.add(optionD);

        centerPanel.add(
                optionsPanel,
                BorderLayout.SOUTH
        );

        add(
                centerPanel,
                BorderLayout.CENTER
        );

        // =================================================
        // FOOTER
        // =================================================

        JPanel footer =
                new JPanel(
                        new BorderLayout()
                );

        footer.setBackground(
                BACKGROUND
        );

        footer.setBorder(
                BorderFactory.createEmptyBorder(
                        10,
                        30,
                        20,
                        30
                )
        );

        scoreLabel =
                new JLabel("Score: 0");

        scoreLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        scoreLabel.setForeground(CYAN);

        progressLabel =
                new JLabel(
                        "",
                        SwingConstants.CENTER
                );

        progressLabel.setForeground(
                WHITE
        );

        JPanel buttonPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT
                        )
                );

        buttonPanel.setBackground(
                BACKGROUND
        );

        previousButton =
                createButton("PREVIOUS");

        nextButton =
                createButton("NEXT");

        JButton exitButton =
                createButton("EXIT");

        buttonPanel.add(previousButton);
        buttonPanel.add(nextButton);
        buttonPanel.add(exitButton);

        footer.add(
                scoreLabel,
                BorderLayout.WEST
        );

        footer.add(
                progressLabel,
                BorderLayout.CENTER
        );

        footer.add(
                buttonPanel,
                BorderLayout.EAST
        );

        add(
                footer,
                BorderLayout.SOUTH
        );

        // =================================================
        // ACTIONS
        // =================================================

        previousButton.addActionListener(
                e -> previousQuestion()
        );

        nextButton.addActionListener(
                e -> nextQuestion()
        );

        exitButton.addActionListener(
                e -> dispose()
        );

        setVisible(true);

        showQuestion();
    }

    // =====================================================
    // OPTION BUTTON
    // =====================================================

    private JRadioButton createOptionButton() {

        JRadioButton button =
                new JRadioButton();

        button.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        15
                )
        );

        button.setForeground(WHITE);

        button.setBackground(PANEL);

        button.setFocusPainted(false);

        button.setOpaque(true);

        return button;
    }

    // =====================================================
    // NORMAL BUTTON
    // =====================================================

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

        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        return button;
    }

    // =====================================================
    // SUBJECT BUTTON
    // =====================================================

    private JButton createSubjectButton(
            String text
    ) {

        JButton button =
                new JButton(text);

        button.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        button.setForeground(WHITE);

        button.setBackground(BUTTON);

        button.setFocusPainted(false);

        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        return button;
    }

    // =====================================================
    // SHOW QUESTION
    // =====================================================

    private void showQuestion() {

        Question question =
                questions.get(
                        currentQuestion
                );

        questionNumberLabel.setText(
                "QUESTION "
                        + (currentQuestion + 1)
                        + " OF "
                        + questions.size()
        );

        questionLabel.setText(
                "<html><div style='text-align:center;'>"
                        + question.text
                        + "</div></html>"
        );

        optionA.setText(
                "A. " + question.options[0]
        );

        optionB.setText(
                "B. " + question.options[1]
        );

        optionC.setText(
                "C. " + question.options[2]
        );

        optionD.setText(
                "D. " + question.options[3]
        );

        optionGroup.clearSelection();

        int selected =
                selectedAnswers[currentQuestion];

        if (selected == 0) {

            optionA.setSelected(true);

        } else if (selected == 1) {

            optionB.setSelected(true);

        } else if (selected == 2) {

            optionC.setSelected(true);

        } else if (selected == 3) {

            optionD.setSelected(true);
        }

        progressLabel.setText(
                "Question "
                        + (currentQuestion + 1)
                        + " / "
                        + questions.size()
        );

        scoreLabel.setText(
                "Score: "
                        + calculateScore()
        );

        previousButton.setEnabled(
                currentQuestion > 0
        );

        if (
                currentQuestion
                        == questions.size() - 1
        ) {

            nextButton.setText(
                    "FINISH"
            );

        } else {

            nextButton.setText(
                    "NEXT"
            );
        }
    }

    // =====================================================
    // SAVE ANSWER
    // =====================================================

    private void saveCurrentAnswer() {

        if (optionA.isSelected()) {

            selectedAnswers[currentQuestion] =
                    0;

        } else if (optionB.isSelected()) {

            selectedAnswers[currentQuestion] =
                    1;

        } else if (optionC.isSelected()) {

            selectedAnswers[currentQuestion] =
                    2;

        } else if (optionD.isSelected()) {

            selectedAnswers[currentQuestion] =
                    3;
        }
    }

    // =====================================================
    // NEXT QUESTION
    // =====================================================

    private void nextQuestion() {

        saveCurrentAnswer();

        if (
                selectedAnswers[currentQuestion]
                        == -1
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select an answer first.",
                    "J.A.R.V.I.S.",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        if (
                currentQuestion
                        == questions.size() - 1
        ) {

            finishQuiz();

            return;
        }

        currentQuestion++;

        showQuestion();
    }

    // =====================================================
    // PREVIOUS QUESTION
    // =====================================================

    private void previousQuestion() {

        saveCurrentAnswer();

        if (currentQuestion > 0) {

            currentQuestion--;

            showQuestion();
        }
    }

    // =====================================================
    // CALCULATE SCORE
    // =====================================================

    private int calculateScore() {

        int result = 0;

        for (
                int i = 0;
                i < selectedAnswers.length;
                i++
        ) {

            if (
                    selectedAnswers[i]
                            == questions
                            .get(i)
                            .correctAnswer
            ) {

                result++;
            }
        }

        return result;
    }

    // =====================================================
    // FINISH QUIZ
    // =====================================================

    private void finishQuiz() {

        score =
                calculateScore();

        int total =
                questions.size();

        double percentage =
                (score * 100.0) / total;

        String message;

        if (percentage >= 80) {

            message =
                    "Excellent work!";

        } else if (percentage >= 50) {

            message =
                    "Good job! Keep practicing.";

        } else {

            message =
                    "Keep learning and try again!";
        }

        JOptionPane.showMessageDialog(
                this,
                "<html>"
                        + "<h2>QUIZ COMPLETED</h2>"
                        + "<p>Subject: "
                        + selectedSubject
                        + "</p>"
                        + "<p>Score: "
                        + score
                        + " / "
                        + total
                        + "</p>"
                        + "<p>Percentage: "
                        + String.format(
                                "%.1f",
                                percentage
                        )
                        + "%</p>"
                        + "<p>"
                        + message
                        + "</p>"
                        + "</html>",
                "J.A.R.V.I.S. RESULT",
                JOptionPane.INFORMATION_MESSAGE
        );

        dispose();
    }

    // =====================================================
    // QUESTION CLASS
    // =====================================================

    private static class Question {

        String text;

        String[] options;

        int correctAnswer;

        Question(
                String text,
                String[] options,
                int correctAnswer
        ) {

            this.text = text;

            this.options =
                    options.clone();

            this.correctAnswer =
                    correctAnswer;
        }
    }
}