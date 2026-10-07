package score;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class java extends JPanel implements ActionListener, KeyListener, MouseListener {
    
    final int WIDTH = 800;
    final int HEIGHT = 600;
    final int GROUND_HEIGHT = 50;

    int birdX = 150;
    double birdY = 250;

    int birdWidth = 40;
    int birdHeight = 30;

    double verticalVelocity = 0.0;
    double gravity = 0.5;
    double jumpForce = -9.0;

    ArrayList<Pipe> pipes = new ArrayList<>();
    Random random = new Random();

    int pipeTimer = 0;

    final int PIPE_WIDTH = 70;
    final int PIPE_GAP = 170;
    final int PIPE_SPEED = 3;

    int score = 0;

    boolean gameOver = false;

    Timer timer;

    public java() {

        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(new Color(135, 206, 235));

        setFocusable(true);

        addKeyListener(this);
        addMouseListener(this);

        createPipe();

        timer = new Timer(16, this);
        timer.start();
    }

    public void createPipe() {

        int minHeight = 80;
        int maxHeight = 280;

        int topHeight =
                minHeight + random.nextInt(maxHeight - minHeight + 1);

        Pipe p = new Pipe(
                WIDTH,
                topHeight,
                PIPE_GAP,
                PIPE_WIDTH,
                PIPE_SPEED
        );

        pipes.add(p);
    }

    public void updateBird() {

        verticalVelocity += gravity;

        birdY += verticalVelocity;

        if (birdY < 0) {
            birdY = 0;
            verticalVelocity = 0;
        }
    }

    public void jump() {

        if (!gameOver) {
            verticalVelocity = jumpForce;
        }
    }

    public void checkCollision() {

        Rectangle birdHitbox = new Rectangle(
                birdX,
                (int) birdY,
                birdWidth,
                birdHeight
        );

        if (birdY + birdHeight >= HEIGHT - GROUND_HEIGHT) {

            gameOver = true;
        }

        for (Pipe p : pipes) {

            Rectangle topPipeHitbox = new Rectangle(
                    p.x,
                    0,
                    p.width,
                    p.topHeight
            );

            Rectangle bottomPipeHitbox = new Rectangle(
                    p.x,
                    p.bottomY,
                    p.width,
                    HEIGHT - GROUND_HEIGHT - p.bottomY
            );

            if (birdHitbox.intersects(topPipeHitbox)
                    || birdHitbox.intersects(bottomPipeHitbox)) {

                gameOver = true;
            }
        }
    }

    public void updateScore() {

        for (Pipe p : pipes) {

            if (!p.passed && p.x + p.width < birdX) {

                score++;

                p.passed = true;
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (!gameOver) {

            updateBird();

            pipeTimer++;

            if (pipeTimer >= 100) {

                createPipe();

                pipeTimer = 0;
            }

            for (Pipe p : pipes) {

                p.move();
            }
            pipes.removeIf(p -> p.isOffScreen());

            checkCollision();

            updateScore();

            repaint();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        g.setColor(new Color(135, 206, 235));

        g.fillRect(
                0,
                0,
                WIDTH,
                HEIGHT
        );

        g.setColor(Color.WHITE);

        g.fillOval(80, 80, 80, 40);
        g.fillOval(120, 65, 80, 55);
        g.fillOval(170, 80, 80, 40);

        g.fillOval(550, 120, 80, 40);
        g.fillOval(590, 100, 80, 60);
        g.fillOval(650, 120, 80, 40);

        for (Pipe p : pipes) {

            g.setColor(Color.GREEN);

            g.fillRect(
                    p.x,
                    0,
                    p.width,
                    p.topHeight
            );
            
            g.fillRect(
                    p.x - 5,
                    p.topHeight - 20,
                    p.width + 10,
                    20
            );

    
            g.fillRect(
                    p.x,
                    p.bottomY,
                    p.width,
                    HEIGHT - GROUND_HEIGHT - p.bottomY
            );

            g.fillRect(
                    p.x - 5,
                    p.bottomY,
                    p.width + 10,
                    20
            );

            g.setColor(Color.BLACK);

            g.drawRect(
                    p.x,
                    0,
                    p.width,
                    p.topHeight
            );

            g.drawRect(
                    p.x,
                    p.bottomY,
                    p.width,
                    HEIGHT - GROUND_HEIGHT - p.bottomY
            );
        }

        g.setColor(new Color(100, 200, 70));

        g.fillRect(
                0,
                HEIGHT - GROUND_HEIGHT,
                WIDTH,
                GROUND_HEIGHT
        );

        g.setColor(new Color(60, 150, 40));

        g.fillRect(
                0,
                HEIGHT - GROUND_HEIGHT,
                WIDTH,
                5
        );

        g.setColor(Color.YELLOW);

        g.fillOval(
                birdX,
                (int) birdY,
                birdWidth,
                birdHeight
        );

        g.setColor(Color.BLACK);

        g.fillOval(
                birdX + 25,
                (int) birdY + 7,
                6,
                6
        );

        g.setColor(Color.ORANGE);

        int[] beakX = {
            birdX + birdWidth,
            birdX + birdWidth + 12,
            birdX + birdWidth
        };

        int[] beakY = {
            (int) birdY + 10,
            (int) birdY + 15,
            (int) birdY + 20
        };

        g.fillPolygon(
                beakX,
                beakY,
                3
        );

 
        g.setColor(Color.ORANGE);

        g.fillOval(
                birdX + 8,
                (int) birdY + 15,
                18,
                10
        );

        g.setColor(Color.BLACK);

        g.setFont(
                new Font("Arial", Font.BOLD, 28)
        );

        g.drawString(
                "Score: " + score,
                20,
                50
        );

        g.setFont(
                new Font("Arial", Font.BOLD, 18)
        );

        g.drawString(
                "Press SPACE or Click Mouse to Fly",
                230,
                40
        );

        if (gameOver) {

            g.setColor(Color.WHITE);

            g.fillRect(
                    250,
                    200,
                    300,
                    180
            );

            g.setColor(Color.BLACK);

            g.setFont(
                    new Font("Arial", Font.BOLD, 40)
            );

            g.drawString(
                    "GAME OVER",
                    300,
                    260
            );

            g.setFont(
                    new Font("Arial", Font.BOLD, 25)
            );

            g.drawString(
                    "Score: " + score,
                    350,
                    305
            );

            g.setFont(
                    new Font("Arial", Font.PLAIN, 18)
            );

            g.drawString(
                    "Press R to Restart",
                    320,
                    345
            );
        }
    }

    public void restartGame() {

        birdY = 250;
        verticalVelocity = 0;

        score = 0;

        pipeTimer = 0;

        pipes.clear();

        gameOver = false;

        createPipe();

        requestFocusInWindow();

        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_SPACE) {

            jump();
        }

        if (e.getKeyCode() == KeyEvent.VK_R) {

            if (gameOver) {

                restartGame();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {

        jump();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    public static void main(String[] args) {

        JFrame frame = new JFrame("Fluffy Bird Game");

        java game = new java();

        frame.add(game);

        frame.pack();

        frame.setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );

        frame.setLocationRelativeTo(null);

        frame.setResizable(false);

        frame.setVisible(true);

        game.requestFocusInWindow();
    }

    class Pipe {

        int x;
        int topHeight;
        int bottomY;

        int gap;
        int width;
        int speed;

        boolean passed = false;

        public Pipe(
                int x,
                int topHeight,
                int gap,
                int width,
                int speed) {

            this.x = x;
            this.topHeight = topHeight;
            this.gap = gap;
            this.width = width;
            this.speed = speed;

            bottomY = topHeight + gap;
        }

        public void move() {

            x = x - speed;
        }

        public boolean isOffScreen() {

            return x + width < 0;
        }
    }
}
 