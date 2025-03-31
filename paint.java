import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.swing.*;

public class BasicPaintApp extends JFrame {
   // private static final long serialVersionUID = 1L;
//The serialVersionUID is only necessary when you need to serialize and deserialize objects

    private final JButton clearBtn, blackBtn, blueBtn, greenBtn, redBtn, eraserBtn;
    private final JPanel controlsPanel;
    private DrawingPanel drawPanel; // Custom drawing panel for better control
    private Color drawColor = Color.BLACK;
    private int lastX, lastY;
    private int brushSize = 5; // Default brush size
    private BufferedImage image; // Image to store the drawing
    private String currentTool = "brush"; // Current drawing or erasing tool

    public BasicPaintApp() {
        setTitle("Basic Paint App");

        // Set size to match screen dimensions
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width, screenSize.height);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Disable window resizing
        setResizable(false);

        // Controls panel & Buttons
        controlsPanel = new JPanel();
        clearBtn = new JButton("Clear");
        blackBtn = new JButton("Black");
        blueBtn = new JButton("Blue");
        greenBtn = new JButton("Green");
        redBtn = new JButton("Red");
        eraserBtn = new JButton("Eraser");

        // Add buttons to the controls panel
        controlsPanel.add(clearBtn);
        controlsPanel.add(blackBtn);
        controlsPanel.add(blueBtn);
        controlsPanel.add(greenBtn);
        controlsPanel.add(redBtn);
        controlsPanel.add(eraserBtn);

        // Brush size dropdown menu
        JComboBox<Integer> sizeDropdown = new JComboBox<>();
        for (int size = 5; size <= 25; size++) {
            sizeDropdown.addItem(size);
        }
        sizeDropdown.setSelectedItem(brushSize); // Set default size

        // Add size dropdown to controls panel
        controlsPanel.add(new JLabel("Brush & Eraser Size:"));
        controlsPanel.add(sizeDropdown);

        // Draw panel
        drawPanel = new DrawingPanel();
        drawPanel.setBackground(Color.WHITE);

        // Add panels to the frame
        add(controlsPanel, BorderLayout.NORTH);
        add(drawPanel, BorderLayout.CENTER);

        // Event listeners for buttons
        clearBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clearImage();
            }
        });

        blackBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                drawColor = Color.BLACK;
                currentTool = "brush"; // Switch to brush mode
            }
        });

        blueBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                drawColor = Color.BLUE;
                currentTool = "brush"; // Switch to brush mode
            }
        });

        greenBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                drawColor = Color.GREEN;
                currentTool = "brush"; // Switch to brush mode
            }
        });

        redBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                drawColor = Color.RED;
                currentTool = "brush"; // Switch to brush mode
            }
        });

        eraserBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                currentTool = "eraser"; // Set the current tool to eraser
            }
        });

        // Event listener for size dropdown
        sizeDropdown.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                brushSize = (int) sizeDropdown.getSelectedItem();
            }
        });
    }

    // Method to clear the drawing
    private void clearImage() {
        image = new BufferedImage(drawPanel.getWidth(), drawPanel.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = image.createGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, drawPanel.getWidth(), drawPanel.getHeight());
        g.dispose(); // Clean up graphics object
        drawPanel.repaint();
    }

    // Custom drawing panel to handle drawing operations
    class DrawingPanel extends JPanel {
        private static final long serialVersionUID = 1L;

        public DrawingPanel() {
            super();

            addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    lastX = e.getX();
                    lastY = e.getY();
                    if (currentTool.equals("eraser")) {
                        erase(e.getX(), e.getY());
                    }
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    if (currentTool.equals("brush")) {
                        draw(e.getX(), e.getY());
                    }
                }
            });

            addMouseMotionListener(new MouseAdapter() {
                @Override
                public void mouseDragged(MouseEvent e) {
                    if (currentTool.equals("brush")) {
                        draw(e.getX(), e.getY());
                    } else if (currentTool.equals("eraser")) {
                        erase(e.getX(), e.getY());
                    }
                }
            });
        }

        private void draw(int x, int y) {
            Graphics2D g = (Graphics2D) image.getGraphics();
            g.setColor(drawColor);
            g.setStroke(new BasicStroke(brushSize, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            g.drawLine(lastX, lastY, x, y);
            g.dispose(); // Clean up graphics object
            repaint();

            lastX = x;
            lastY = y;
        }

        private void erase(int x, int y) {
            Graphics2D g = (Graphics2D) image.getGraphics();
            g.setColor(Color.WHITE); // Set eraser color to background color
            g.setStroke(new BasicStroke(brushSize, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            g.drawLine(lastX, lastY, x, y);
            g.dispose(); // Clean up graphics object
            repaint();

            lastX = x;
            lastY = y;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (image == null) {
                image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
            }
            g.drawImage(image, 0, 0, null);
        }

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                BasicPaintApp app = new BasicPaintApp();
                app.setVisible(true);
            }
        });
    }
}
