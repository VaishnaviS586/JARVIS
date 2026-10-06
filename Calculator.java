import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Calculator {

    private JFrame frame;

    private JLabel expressionLabel;
    private JTextField display;

    private double firstNumber = 0;
    private String operator = "";
    private boolean newNumber = true;

    // Stores the last calculated answer
    private double lastAnswer = 0;

    // JARVIS COLORS
    private final Color BACKGROUND = new Color(8, 15, 25);
    private final Color PANEL = new Color(15, 28, 45);
    private final Color DISPLAY = new Color(5, 12, 20);
    private final Color BUTTON = new Color(20, 38, 60);
    private final Color CYAN = new Color(0, 220, 255);
    private final Color LIGHT_CYAN = new Color(120, 240, 255);
    private final Color WHITE = new Color(240, 245, 250);
    private final Color GRAY = new Color(150, 165, 180);
    private final Color GREEN = new Color(50, 220, 120);

    // CONSTRUCTOR
    public Calculator() {
        createCalculator();
    }

    // CREATE CALCULATOR
    private void createCalculator() {

        frame = new JFrame(
                "J.A.R.V.I.S. - Scientific Calculator"
        );

        frame.setSize(600, 700);

        frame.setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        frame.setLocationRelativeTo(null);

        frame.setLayout(new BorderLayout());

        frame.getContentPane().setBackground(
                BACKGROUND
        );

        // HEADER
        JPanel header = new JPanel(
                new BorderLayout()
        );

        header.setBackground(BACKGROUND);

        header.setBorder(
                BorderFactory.createEmptyBorder(
                        15, 20, 10, 20
                )
        );

        JLabel title = new JLabel(
                "J.A.R.V.I.S. CALCULATOR",
                SwingConstants.CENTER
        );

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        24
                )
        );

        title.setForeground(CYAN);

        JLabel status = new JLabel(
                "● SCIENTIFIC CALCULATION SYSTEM ONLINE",
                SwingConstants.CENTER
        );

        status.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        11
                )
        );

        status.setForeground(GREEN);

        header.add(
                title,
                BorderLayout.CENTER
        );

        header.add(
                status,
                BorderLayout.SOUTH
        );

        frame.add(
                header,
                BorderLayout.NORTH
        );

        // MAIN CONTENT PANEL
        JPanel mainPanel = new JPanel(
                new BorderLayout()
        );

        mainPanel.setBackground(BACKGROUND);

        // DISPLAY PANEL
        JPanel displayPanel = new JPanel(
                new BorderLayout()
        );

        displayPanel.setBackground(DISPLAY);

        displayPanel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                CYAN,
                                1
                        ),
                        BorderFactory.createEmptyBorder(
                                8, 15, 8, 15
                        )
                )
        );

        expressionLabel = new JLabel(
                " ",
                SwingConstants.RIGHT
        );

        expressionLabel.setFont(
                new Font(
                        "Consolas",
                        Font.PLAIN,
                        18
                )
        );

        expressionLabel.setForeground(GRAY);

        display = new JTextField("0");

        display.setFont(
                new Font(
                        "Consolas",
                        Font.BOLD,
                        34
                )
        );

        display.setHorizontalAlignment(
                SwingConstants.RIGHT
        );

        display.setForeground(CYAN);

        display.setBackground(DISPLAY);

        display.setCaretColor(CYAN);

        display.setBorder(
                BorderFactory.createEmptyBorder(
                        2, 2, 2, 2
                )
        );

        display.setEditable(false);

        JPanel displayContainer = new JPanel(
                new BorderLayout()
        );

        displayContainer.setBackground(DISPLAY);

        displayContainer.add(
                expressionLabel,
                BorderLayout.NORTH
        );

        displayContainer.add(
                display,
                BorderLayout.CENTER
        );

        displayPanel.add(
                displayContainer,
                BorderLayout.CENTER
        );

        // FIXED DISPLAY HEIGHT
        displayPanel.setPreferredSize(
                new Dimension(
                        0,
                        120
                )
        );

        mainPanel.add(
                displayPanel,
                BorderLayout.NORTH
        );

        // BUTTON PANEL
        JPanel buttonPanel = new JPanel(
                new GridLayout(
                        7,
                        5,
                        8,
                        8
                )
        );

        buttonPanel.setBackground(BACKGROUND);

        buttonPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        10, 20, 20, 20
                )
        );

        // ROW 1

        addButton(
                buttonPanel,
                "C",
                e -> clear()
        );

        addButton(
                buttonPanel,
                "⌫",
                e -> backspace()
        );

        addButton(
                buttonPanel,
                "%",
                e -> percentage()
        );

        addButton(
                buttonPanel,
                "√",
                e -> squareRoot()
        );

        addButton(
                buttonPanel,
                "÷",
                e -> setOperator("/")
        );

        // ROW 2

        addButton(
                buttonPanel,
                "sin",
                e -> trigonometric("sin")
        );

        addButton(
                buttonPanel,
                "cos",
                e -> trigonometric("cos")
        );

        addButton(
                buttonPanel,
                "tan",
                e -> trigonometric("tan")
        );

        addButton(
                buttonPanel,
                "x²",
                e -> square()
        );

        addButton(
                buttonPanel,
                "×",
                e -> setOperator("*")
        );

        // ROW 3

        addButton(
                buttonPanel,
                "log",
                e -> logarithm()
        );

        addButton(
                buttonPanel,
                "ln",
                e -> naturalLog()
        );

        addButton(
                buttonPanel,
                "1/x",
                e -> reciprocal()
        );

        addButton(
                buttonPanel,
                "π",
                e -> pi()
        );

        addButton(
                buttonPanel,
                "−",
                e -> setOperator("-")
        );

        // ROW 4

        addButton(
                buttonPanel,
                "7",
                e -> number("7")
        );

        addButton(
                buttonPanel,
                "8",
                e -> number("8")
        );

        addButton(
                buttonPanel,
                "9",
                e -> number("9")
        );

        addButton(
                buttonPanel,
                "e",
                e -> eConstant()
        );

        addButton(
                buttonPanel,
                "+",
                e -> setOperator("+")
        );

        // ROW 5

        addButton(
                buttonPanel,
                "4",
                e -> number("4")
        );

        addButton(
                buttonPanel,
                "5",
                e -> number("5")
        );

        addButton(
                buttonPanel,
                "6",
                e -> number("6")
        );

        addButton(
                buttonPanel,
                "+/-",
                e -> changeSign()
        );

        addButton(
                buttonPanel,
                "=",
                e -> calculate()
        );

        // ROW 6

        addButton(
                buttonPanel,
                "1",
                e -> number("1")
        );

        addButton(
                buttonPanel,
                "2",
                e -> number("2")
        );

        addButton(
                buttonPanel,
                "3",
                e -> number("3")
        );

        addButton(
                buttonPanel,
                "(",
                e -> number("(")
        );

        addButton(
                buttonPanel,
                ")",
                e -> number(")")
        );

        // ROW 7

        addButton(
                buttonPanel,
                "0",
                e -> number("0")
        );

        addButton(
                buttonPanel,
                ".",
                e -> decimal()
        );

        addButton(
                buttonPanel,
                "00",
                e -> number("00")
        );

        // ANS
        addButton(
                buttonPanel,
                "ANS",
                e -> answer()
        );

        addButton(
                buttonPanel,
                "EXIT",
                e -> frame.dispose()
        );

        mainPanel.add(
                buttonPanel,
                BorderLayout.CENTER
        );

        // ADD MAIN PANEL
        frame.add(
                mainPanel,
                BorderLayout.CENTER
        );

        frame.setVisible(true);
    }

    // CREATE BUTTON
    private void addButton(
            JPanel panel,
            String text,
            java.awt.event.ActionListener action
    ) {

        JButton button = new JButton(text);

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

        button.setBorder(
                BorderFactory.createLineBorder(
                        new Color(35, 70, 95),
                        1
                )
        );

        button.addMouseListener(
                new MouseAdapter() {

                    @Override
                    public void mouseEntered(
                            MouseEvent e
                    ) {

                        button.setBackground(
                                new Color(
                                        0,
                                        150,
                                        180
                                )
                        );

                        button.setForeground(
                                Color.WHITE
                        );
                    }

                    @Override
                    public void mouseExited(
                            MouseEvent e
                    ) {

                        button.setBackground(
                                BUTTON
                        );

                        button.setForeground(
                                WHITE
                        );
                    }
                }
        );

        button.addActionListener(action);

        panel.add(button);
    }

    // NUMBER
    private void number(String value) {

        if (newNumber) {

            display.setText(value);

            newNumber = false;

        } else {

            display.setText(
                    display.getText() + value
            );
        }
    }

    // DECIMAL
    private void decimal() {

        if (newNumber) {

            display.setText("0.");

            newNumber = false;

        } else if (
                !display.getText().contains(".")
        ) {

            display.setText(
                    display.getText() + "."
            );
        }
    }

    // OPERATOR
    private void setOperator(String op) {

        try {

            firstNumber =
                    Double.parseDouble(
                            display.getText()
                    );

            operator = op;

            expressionLabel.setText(
                    formatResult(firstNumber)
                            + " "
                            + getOperatorSymbol(op)
            );

            newNumber = true;

        } catch (Exception e) {

            display.setText("ERROR");
        }
    }

    // OPERATOR SYMBOL
    private String getOperatorSymbol(String op) {

        switch (op) {

            case "*":
                return "×";

            case "/":
                return "÷";

            case "-":
                return "−";

            case "+":
                return "+";

            default:
                return op;
        }
    }

    // CALCULATE
    private void calculate() {

        try {

            if (operator.isEmpty()) {
                return;
            }

            double secondNumber =
                    Double.parseDouble(
                            display.getText()
                    );

            double result;

            switch (operator) {

                case "+":

                    result =
                            firstNumber
                                    + secondNumber;

                    break;

                case "-":

                    result =
                            firstNumber
                                    - secondNumber;

                    break;

                case "*":

                    result =
                            firstNumber
                                    * secondNumber;

                    break;

                case "/":

                    if (secondNumber == 0) {

                        display.setText(
                                "DIVIDE BY ZERO"
                        );

                        return;
                    }

                    result =
                            firstNumber
                                    / secondNumber;

                    break;

                default:

                    return;
            }

            expressionLabel.setText(
                    formatResult(firstNumber)
                            + " "
                            + getOperatorSymbol(operator)
                            + " "
                            + formatResult(secondNumber)
            );

            display.setText(
                    formatResult(result)
            );

            // Save result for ANS
            lastAnswer = result;

            newNumber = true;

            operator = "";

        } catch (Exception e) {

            display.setText("ERROR");

            newNumber = true;
        }
    }

    // ANSWER
    private void answer() {

        display.setText(
                formatResult(lastAnswer)
        );

        newNumber = true;
    }

    // CLEAR
    private void clear() {

        display.setText("0");

        expressionLabel.setText(" ");

        firstNumber = 0;

        operator = "";

        newNumber = true;
    }

    // BACKSPACE
    private void backspace() {

        String text =
                display.getText();

        if (
                text.length() <= 1
                        || text.equals("ERROR")
                        || text.equals("INVALID")
                        || text.equals("UNDEFINED")
                        || text.equals("DIVIDE BY ZERO")
        ) {

            display.setText("0");

            newNumber = true;

        } else {

            display.setText(
                    text.substring(
                            0,
                            text.length() - 1
                    )
            );
        }
    }

    // PERCENTAGE
    private void percentage() {

        try {

            double value =
                    Double.parseDouble(
                            display.getText()
                    );

            double result =
                    value / 100;

            display.setText(
                    formatResult(result)
            );

            lastAnswer = result;

            newNumber = true;

        } catch (Exception e) {

            display.setText("ERROR");
        }
    }

    // SQUARE ROOT
    private void squareRoot() {

        try {

            double value =
                    Double.parseDouble(
                            display.getText()
                    );

            if (value < 0) {

                display.setText("INVALID");

                return;
            }

            double result =
                    Math.sqrt(value);

            display.setText(
                    formatResult(result)
            );

            lastAnswer = result;

            newNumber = true;

        } catch (Exception e) {

            display.setText("ERROR");
        }
    }

    // SQUARE
    private void square() {

        try {

            double value =
                    Double.parseDouble(
                            display.getText()
                    );

            double result =
                    value * value;

            display.setText(
                    formatResult(result)
            );

            lastAnswer = result;

            newNumber = true;

        } catch (Exception e) {

            display.setText("ERROR");
        }
    }

    // RECIPROCAL
    private void reciprocal() {

        try {

            double value =
                    Double.parseDouble(
                            display.getText()
                    );

            if (value == 0) {

                display.setText("INVALID");

                return;
            }

            double result =
                    1 / value;

            display.setText(
                    formatResult(result)
            );

            lastAnswer = result;

            newNumber = true;

        } catch (Exception e) {

            display.setText("ERROR");
        }
    }

    // TRIGONOMETRY
    private void trigonometric(
            String function
    ) {

        try {

            double value =
                    Double.parseDouble(
                            display.getText()
                    );

            double radians =
                    Math.toRadians(value);

            double result;

            switch (function) {

                case "sin":

                    result =
                            Math.sin(radians);

                    break;

                case "cos":

                    result =
                            Math.cos(radians);

                    break;

                case "tan":

                    double cosValue =
                            Math.cos(radians);

                    if (
                            Math.abs(cosValue) < 1E-10
                    ) {

                        display.setText(
                                "UNDEFINED"
                        );

                        return;
                    }

                    result =
                            Math.tan(radians);

                    break;

                default:

                    return;
            }

            display.setText(
                    formatResult(result)
            );

            lastAnswer = result;

            newNumber = true;

        } catch (Exception e) {

            display.setText("ERROR");
        }
    }

    // LOG
    private void logarithm() {

        try {

            double value =
                    Double.parseDouble(
                            display.getText()
                    );

            if (value <= 0) {

                display.setText("INVALID");

                return;
            }

            double result =
                    Math.log10(value);

            display.setText(
                    formatResult(result)
            );

            lastAnswer = result;

            newNumber = true;

        } catch (Exception e) {

            display.setText("ERROR");
        }
    }

    // NATURAL LOG
    private void naturalLog() {

        try {

            double value =
                    Double.parseDouble(
                            display.getText()
                    );

            if (value <= 0) {

                display.setText("INVALID");

                return;
            }

            double result =
                    Math.log(value);

            display.setText(
                    formatResult(result)
            );

            lastAnswer = result;

            newNumber = true;

        } catch (Exception e) {

            display.setText("ERROR");
        }
    }

    // PI
    private void pi() {

        display.setText(
                formatResult(
                        Math.PI
                )
        );

        newNumber = true;
    }

    // E
    private void eConstant() {

        display.setText(
                formatResult(
                        Math.E
                )
        );

        newNumber = true;
    }

    // CHANGE SIGN
    private void changeSign() {

        try {

            double value =
                    Double.parseDouble(
                            display.getText()
                    );

            value = -value;

            display.setText(
                    formatResult(value)
            );

        } catch (Exception e) {

            display.setText("ERROR");
        }
    }

    // FORMAT RESULT
    private String formatResult(
            double value
    ) {

        if (
                Double.isNaN(value)
                        || Double.isInfinite(value)
        ) {

            return "ERROR";
        }

        if (
                value == (long) value
        ) {

            return String.format(
                    "%d",
                    (long) value
            );

        } else {

            return String.valueOf(value);
        }
    }
}