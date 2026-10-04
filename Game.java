import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class Game extends JFrame {

    private GamePanel gamePanel;

    public Game() {

        setTitle("J.A.R.V.I.S. - Dino Game");

        setSize(900, 500);

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        setLocationRelativeTo(null);

        setResizable(false);

        gamePanel = new GamePanel();

        add(gamePanel);

        setVisible(true);

        // Give keyboard focus to the game
        gamePanel.requestFocusInWindow();
    }


    // =====================================================
    // GAME PANEL
    // =====================================================

    class GamePanel extends JPanel
            implements ActionListener, KeyListener {

        // =================================================
        // COLORS
        // =================================================

        private final Color BACKGROUND =
                new Color(8, 15, 25);

        private final Color GROUND =
                new Color(35, 55, 75);

        private final Color CYAN =
                new Color(0, 220, 255);

        private final Color GREEN =
                new Color(50, 220, 120);

        private final Color WHITE =
                new Color(240, 245, 250);

        private final Color RED =
                new Color(255, 80, 80);


        // =================================================
        // GAME TIMER
        // =================================================

        private Timer timer;


        // =================================================
        // DINO
        // =================================================

        private int dinoX = 100;

        private int dinoY;

        private final int dinoWidth = 45;

        private final int dinoHeight = 50;


        // =================================================
        // JUMP
        // =================================================

        private boolean jumping = false;

        private int jumpVelocity = 0;

        private final int gravity = 1;


        // =================================================
        // OBSTACLE
        // =================================================

        private int obstacleX;

        private int obstacleY;

        private final int obstacleWidth = 25;

        private final int obstacleHeight = 45;


        // =================================================
        // GAME VARIABLES
        // =================================================

        private int score = 0;

        private int highScore = 0;

        private boolean gameStarted = false;

        private boolean gameOver = false;


        // =================================================
        // GAME SPEED
        // =================================================

        private int speed = 7;


        // =================================================
        // RANDOM
        // =================================================

        private final Random random =
                new Random();


        // =================================================
        // CONSTRUCTOR
        // =================================================

        public GamePanel() {

            setFocusable(true);

            setPreferredSize(
                    new Dimension(
                            900,
                            500
                    )
            );

            addKeyListener(this);

            setBackground(
                    BACKGROUND
            );


            // Initial positions

            dinoY =
                    getGroundY()
                    - dinoHeight;


            obstacleX =
                    800;


            obstacleY =
                    getGroundY()
                    - obstacleHeight;


            // =================================================
            // SWING TIMER
            // =================================================

            timer =
                    new Timer(
                            20,
                            this
                    );


            timer.start();
        }


        // =====================================================
        // GET GROUND POSITION
        // =====================================================

        private int getGroundY() {

            return getHeight() - 70;
        }


        // =====================================================
        // PAINT COMPONENT
        // =====================================================

        @Override
        protected void paintComponent(
                Graphics g
        ) {

            super.paintComponent(g);

            Graphics2D g2 =
                    (Graphics2D) g;


            // Anti-aliasing

            g2.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON
            );


            // =================================================
            // TITLE
            // =================================================

            g2.setColor(
                    CYAN
            );

            g2.setFont(
                    new Font(
                            "Arial",
                            Font.BOLD,
                            26
                    )
            );

            g2.drawString(
                    "J.A.R.V.I.S. DINO RUN",
                    30,
                    40
            );


            // =================================================
            // STATUS
            // =================================================

            g2.setFont(
                    new Font(
                            "Arial",
                            Font.PLAIN,
                            13
                    )
            );

            g2.setColor(
                    GREEN
            );

            g2.drawString(
                    "● GAME SYSTEM ONLINE",
                    32,
                    62
            );


            // =================================================
            // SCORE
            // =================================================

            g2.setFont(
                    new Font(
                            "Arial",
                            Font.BOLD,
                            16
                    )
            );

            g2.setColor(
                    WHITE
            );

            g2.drawString(
                    "SCORE: " + score,
                    700,
                    35
            );

            g2.drawString(
                    "HIGH SCORE: " + highScore,
                    700,
                    58
            );


            // =================================================
            // GROUND
            // =================================================

            int groundY =
                    getGroundY();

            g2.setColor(
                    GROUND
            );

            g2.fillRect(
                    0,
                    groundY,
                    getWidth(),
                    3
            );


            // =================================================
            // DINO
            // =================================================

            drawDino(g2);


            // =================================================
            // OBSTACLE
            // =================================================

            drawObstacle(g2);


            // =================================================
            // START SCREEN
            // =================================================

            if (!gameStarted) {

                drawStartScreen(g2);
            }


            // =================================================
            // GAME OVER
            // =================================================

            if (gameOver) {

                drawGameOver(g2);
            }
        }


        // =====================================================
        // DRAW DINO
        // =====================================================

        private void drawDino(
                Graphics2D g2
        ) {

            g2.setColor(
                    CYAN
            );


            // Body

            g2.fillRect(
                    dinoX,
                    dinoY,
                    35,
                    35
            );


            // Head

            g2.fillRect(
                    dinoX + 25,
                    dinoY - 15,
                    25,
                    25
            );


            // Neck

            g2.fillRect(
                    dinoX + 20,
                    dinoY - 5,
                    15,
                    15
            );


            // Left leg

            g2.fillRect(
                    dinoX + 5,
                    dinoY + 35,
                    8,
                    15
            );


            // Right leg

            g2.fillRect(
                    dinoX + 25,
                    dinoY + 35,
                    8,
                    15
            );


            // Tail

            g2.fillRect(
                    dinoX - 12,
                    dinoY + 10,
                    15,
                    8
            );


            // Eye

            g2.setColor(
                    Color.BLACK
            );

            g2.fillOval(
                    dinoX + 42,
                    dinoY - 10,
                    5,
                    5
            );
        }


        // =====================================================
        // DRAW OBSTACLE
        // =====================================================

        private void drawObstacle(
                Graphics2D g2
        ) {

            g2.setColor(
                    GREEN
            );


            // Main cactus

            g2.fillRect(
                    obstacleX,
                    obstacleY,
                    obstacleWidth,
                    obstacleHeight
            );


            // Left branch

            g2.fillRect(
                    obstacleX - 10,
                    obstacleY + 15,
                    10,
                    8
            );


            // Right branch

            g2.fillRect(
                    obstacleX + obstacleWidth,
                    obstacleY + 25,
                    10,
                    8
            );


            // Top part

            g2.fillRect(
                    obstacleX + 8,
                    obstacleY - 8,
                    9,
                    12
            );
        }


        // =====================================================
        // START SCREEN
        // =====================================================

        private void drawStartScreen(
                Graphics2D g2
        ) {

            g2.setColor(
                    WHITE
            );

            g2.setFont(
                    new Font(
                            "Arial",
                            Font.BOLD,
                            28
                    )
            );


            String text =
                    "PRESS SPACE TO START";


            int width =
                    g2.getFontMetrics()
                            .stringWidth(text);


            g2.drawString(
                    text,
                    (getWidth() - width) / 2,
                    200
            );


            g2.setFont(
                    new Font(
                            "Arial",
                            Font.PLAIN,
                            16
                    )
            );


            String instruction =
                    "SPACE = JUMP";


            int instructionWidth =
                    g2.getFontMetrics()
                            .stringWidth(
                                    instruction
                            );


            g2.drawString(
                    instruction,
                    (getWidth() - instructionWidth) / 2,
                    235
            );
        }


        // =====================================================
        // GAME OVER SCREEN
        // =====================================================

        private void drawGameOver(
                Graphics2D g2
        ) {

            g2.setColor(
                    RED
            );

            g2.setFont(
                    new Font(
                            "Arial",
                            Font.BOLD,
                            35
                    )
            );


            String text =
                    "GAME OVER";


            int width =
                    g2.getFontMetrics()
                            .stringWidth(text);


            g2.drawString(
                    text,
                    (getWidth() - width) / 2,
                    200
            );


            g2.setColor(
                    WHITE
            );

            g2.setFont(
                    new Font(
                            "Arial",
                            Font.PLAIN,
                            17
                    )
            );


            String restart =
                    "PRESS SPACE TO RESTART";


            int restartWidth =
                    g2.getFontMetrics()
                            .stringWidth(
                                    restart
                            );


            g2.drawString(
                    restart,
                    (getWidth() - restartWidth) / 2,
                    235
            );
        }


        // =====================================================
        // GAME LOOP
        // =====================================================

        @Override
        public void actionPerformed(
                ActionEvent e
        ) {

            if (
                    gameStarted
                    && !gameOver
            ) {

                updateGame();
            }


            repaint();
        }


        // =====================================================
        // UPDATE GAME
        // =====================================================

        private void updateGame() {

            // =================================================
            // DINO JUMP
            // =================================================

            if (jumping) {

                dinoY += jumpVelocity;

                jumpVelocity += gravity;


                int ground =
                        getGroundY();


                if (
                        dinoY + dinoHeight
                        >= ground
                ) {

                    dinoY =
                            ground
                            - dinoHeight;

                    jumping = false;

                    jumpVelocity = 0;
                }
            }


            // =================================================
            // OBSTACLE MOVEMENT
            // =================================================

            obstacleX -= speed;


            // =================================================
            // RESET OBSTACLE
            // =================================================

            if (
                    obstacleX
                    < -50
            ) {

                obstacleX =
                        getWidth()
                        + random.nextInt(300);


                obstacleY =
                        getGroundY()
                        - obstacleHeight;


                score++;


                // Increase difficulty

                if (
                        score % 5 == 0
                        && speed < 15
                ) {

                    speed++;
                }
            }


            // =================================================
            // COLLISION DETECTION
            // =================================================

            Rectangle dinoRectangle =
                    new Rectangle(
                            dinoX,
                            dinoY,
                            dinoWidth,
                            dinoHeight
                    );


            Rectangle obstacleRectangle =
                    new Rectangle(
                            obstacleX,
                            obstacleY,
                            obstacleWidth,
                            obstacleHeight
                    );


            if (
                    dinoRectangle.intersects(
                            obstacleRectangle
                    )
            ) {

                gameOver();
            }
        }


        // =====================================================
        // START GAME
        // =====================================================

        private void startGame() {

            gameStarted = true;

            gameOver = false;

            score = 0;

            speed = 7;

            jumping = false;

            jumpVelocity = 0;


            dinoY =
                    getGroundY()
                    - dinoHeight;


            obstacleX =
                    getWidth()
                    + 150;


            obstacleY =
                    getGroundY()
                    - obstacleHeight;


            requestFocusInWindow();
        }


        // =====================================================
        // GAME OVER
        // =====================================================

        private void gameOver() {

            gameOver = true;


            if (
                    score > highScore
            ) {

                highScore =
                        score;
            }
        }


        // =====================================================
        // KEY PRESSED
        // =====================================================

        @Override
        public void keyPressed(
                KeyEvent e
        ) {

            if (
                    e.getKeyCode()
                    == KeyEvent.VK_SPACE
            ) {

                // Start

                if (!gameStarted) {

                    startGame();

                    return;
                }


                // Restart

                if (gameOver) {

                    startGame();

                    return;
                }


                // Jump

                if (!jumping) {

                    jumping = true;

                    jumpVelocity = -15;
                }
            }
        }


        // =====================================================
        // REQUIRED KEY LISTENER METHODS
        // =====================================================

        @Override
        public void keyTyped(
                KeyEvent e
        ) {
            // Not used
        }


        @Override
        public void keyReleased(
                KeyEvent e
        ) {
            // Not used
        }
    }
}
