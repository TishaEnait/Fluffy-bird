import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main extends JPanel implements ActionListener, KeyListener, MouseListener {

    final int WIDTH = 800;
    final int HEIGHT = 600;
    int birdX = 150;
    double birdY = 250;
    int birdWidth = 40;
    int birdHeight = 30;
    double verticalVelocity = 0.0;
    double gravity = 0.5;
    double jumpForce = -9.0;
    Timer timer;
public Main() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(new Color(135, 206, 235));
        setFocusable(true);
        addKeyListener(this);
        addMouseListener(this); 
        timer = new Timer(16, this);
        timer.start();
    }
 public void updateBird() {
        verticalVelocity += gravity;
        birdY += verticalVelocity;
        if (birdY < 0) {
            birdY = 0;
            verticalVelocity = 0;
        }
        if (birdY + birdHeight > HEIGHT - 50) {
            birdY = HEIGHT - 50 - birdHeight;
            verticalVelocity = 0;
        }
    }
    public void jump() {

        verticalVelocity = jumpForce;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        updateBird();

        repaint();
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color(135, 206, 235));
        g.fillRect(0, 0, WIDTH, HEIGHT);
        g.setColor(Color.WHITE);
        g.fillOval(80, 80, 80, 40);
        g.fillOval(120, 65, 80, 55);
        g.fillOval(170, 80, 80, 40);
        g.fillOval(550, 120, 80, 40);
        g.fillOval(590, 100, 80, 60);
        g.fillOval(650, 120, 80, 40);
        g.setColor(new Color(100, 200, 70));
        g.fillRect(0, HEIGHT - 50, WIDTH, 50);
        g.setColor(new Color(60, 150, 40));
        g.fillRect(0, HEIGHT - 50, WIDTH, 5);
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
        g.fillPolygon(beakX, beakY, 3);

        g.setColor(Color.ORANGE);
        g.fillOval(
                birdX + 8,
                (int) birdY + 15,
                18,
                10
        );
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 18));

        g.drawString(
                "Press SPACE or Click Mouse to Fly",
                230,
                40
        );
        g.setFont(new Font("Arial", Font.PLAIN, 14));
        g.drawString(
                "Vertical Velocity: " +
                String.format("%.2f", verticalVelocity),
                20,
                30
        );
    }
    @Override
    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            jump();
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
        Main game = new Main();
        frame.add(game);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
        game.requestFocusInWindow();
    }
}
