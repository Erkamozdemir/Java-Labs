import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;

public class SandLab extends JFrame implements ActionListener, MouseListener, MouseMotionListener {
    private static final int ROWS = 100;
    private static final int COLS = 100;
    private static final int RADIUS = 3;
    private static final int NUM_PASSES = 8;
    private World world;
    private Timer timer;
    private int current;
    private JPanel sandPanel;

    public SandLab() {
        this.world = new World(COLS, ROWS);
        this.timer = new Timer(35, this);
        this.current = 2;

        this.setTitle("Cell-Based Particle Simulation");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        this.sandPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                int cellWidth = getWidth() / COLS;
                int cellHeight = getHeight() / ROWS;
                for (int row = 0; row < ROWS; row++) {
                    for (int col = 0; col < COLS; col++) {
                        Particle p = world.getParticle(row, col);
                        if (p != null) {
                            g.setColor(p.getColor());
                        } else {
                            g.setColor(Color.BLACK);
                        }
                        g.fillRect(col * cellWidth, row * cellHeight, cellWidth, cellHeight);
                    }
                }
            }
        };
        sandPanel.addMouseListener(this);
        sandPanel.addMouseMotionListener(this);
        this.add(sandPanel, BorderLayout.CENTER);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(8, 1));
        this.add(panel, BorderLayout.EAST);

        JButton button1 = new JButton("Step");
        button1.addActionListener(this);
        panel.add(button1);
        JButton button2 = new JButton("Stop");
        button2.addActionListener(this);
        panel.add(button2);
        JButton button3 = new JButton("Clear");
        button3.addActionListener(this);
        panel.add(button3);
        JButton button4 = new JButton("Place Stone");
        button4.addActionListener(this);
        panel.add(button4);
        JButton button5 = new JButton("Place Sand");
        button5.addActionListener(this);
        panel.add(button5);
        JButton button6 = new JButton("Place Water");
        button6.addActionListener(this);
        panel.add(button6);
        JButton button7 = new JButton("Place Smoke");
        button7.addActionListener(this);
        panel.add(button7);
        JButton button8 = new JButton("Place Lava");
        button8.addActionListener(this);
        panel.add(button8);

        this.setSize(COLS * 6 + 150, ROWS * 6);
        this.setVisible(true);
    }

    private void updateWorld() {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                Particle p = world.getParticle(row, col);
                if (p != null) {
                    p.setUpdated(false);
                }
            }
        }
        ArrayList<int[]> positions = new ArrayList<>();
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                positions.add(new int[] { row, col });
            }
        }
        for (int pass = 0; pass < NUM_PASSES; pass++) {
            Collections.shuffle(positions);
            for (int[] pos : positions) {
                int row = pos[0];
                int col = pos[1];
                Particle p = world.getParticle(row, col);
                if (p != null && !p.isUpdated()) {
                    p.update(world, row, col);
                }
            }
        }
    }

    private void clearWorld() {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                world.setParticle(row, col, null);
            }
        }
        sandPanel.repaint();
    }

    private void placeParticles(int mouseX, int mouseY) {
        int cellWidth = sandPanel.getWidth() / COLS;
        int cellHeight = sandPanel.getHeight() / ROWS;
        if (cellWidth == 0 || cellHeight == 0)
            return;

        int col = mouseX / cellWidth;
        int row = mouseY / cellHeight;

        for (int dr = -RADIUS; dr <= RADIUS; dr++) {
            for (int dc = -RADIUS; dc <= RADIUS; dc++) {
                if (Math.sqrt(dr * dr + dc * dc) <= RADIUS) {
                    int newRow = row + dr;
                    int newCol = col + dc;
                    if (world.isRight(newRow, newCol) && world.isEmpty(newRow, newCol)) {
                        Particle p = null;
                        if (current == 1) {
                            p = new Stone();
                        } else if (current == 2) {
                            p = new Sand();
                        } else if (current == 3) {
                            p = new Water();
                        } else if (current == 4) {
                            p = new Smoke();
                        } else if (current == 5) {
                            p = new Lava();
                        }
                        world.setParticle(newRow, newCol, p);
                    }
                }
            }
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == timer) {
            updateWorld();
            sandPanel.repaint();
        } else if (e.getSource() instanceof JButton) {
            JButton button = (JButton) e.getSource();
            if (button.getText().equals("Step")) {
                timer.start();
            } else if (button.getText().equals("Stop")) {
                timer.stop();
            } else if (button.getText().equals("Place Stone")) {
                this.current = 1;
            } else if (button.getText().equals("Place Sand")) {
                this.current = 2;
            } else if (button.getText().equals("Place Water")) {
                this.current = 3;
            } else if (button.getText().equals("Place Smoke")) {
                this.current = 4;
            } else if (button.getText().equals("Place Lava")) {
                this.current = 5;
            } else if (button.getText().equals("Clear")) {
                clearWorld();
            }
        }
    }

    public void mousePressed(MouseEvent e) {
        placeParticles(e.getX(), e.getY());
        sandPanel.repaint();
    }

    public void mouseDragged(MouseEvent e) {
        placeParticles(e.getX(), e.getY());
        sandPanel.repaint();
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseMoved(MouseEvent e) {
    }

    public static void main(String[] args) {
        new SandLab();
    }
}
