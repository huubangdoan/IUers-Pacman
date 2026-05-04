import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
public class Map extends JPanel implements ActionListener {
    private PacMan player;
    protected short[][] grid;
    private ArrayList<Collectable> collectable;
    private List<Ghost> ghosts;
    private Timer timer;
    private Image lightPointImg, wallImg;
    private Image[] pacmanRightImgs, pacmanLeftImgs, pacmanUpImgs, pacmanDownImgs;
    private Image ghostImg, fruitImg, dotImg;
    private Image blinkyImg, pinkyImg, inkyImg, clydeImg, frightenedImg;
    public Map() {
        grid = new short[][] {
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
        };
        player = new PacMan(32, 32, 2);
        collectable = new ArrayList<>();
        ghosts = new ArrayList<>();

        File assetsBaseFile = new File(System.getProperty("user.dir"), "assets/Default Skin");
        if (!assetsBaseFile.exists()) {
            assetsBaseFile = new File("assets/Default Skin");
        }
        if (!assetsBaseFile.exists()) {
            assetsBaseFile = new File(System.getProperty("user.dir"), "src/assets/Default Skin");
        }
        if (!assetsBaseFile.exists()) {
            assetsBaseFile = new File("src/assets/Default Skin");
        }
        System.out.println("Map assets base: " + assetsBaseFile.getAbsolutePath() + " exists=" + assetsBaseFile.exists());

        pacmanRightImgs = new Image[3];
        pacmanLeftImgs = new Image[3];
        pacmanUpImgs = new Image[3];
        pacmanDownImgs = new Image[3];
        for (int i = 0; i < 3; i++) {
            pacmanRightImgs[i] = new ImageIcon(new File(new File(assetsBaseFile, "pacman-right"), (i + 1) + ".png").getAbsolutePath()).getImage();
            pacmanLeftImgs[i] = new ImageIcon(new File(new File(assetsBaseFile, "pacman-left"), (i + 1) + ".png").getAbsolutePath()).getImage();
            pacmanUpImgs[i] = new ImageIcon(new File(new File(assetsBaseFile, "pacman-up"), (i + 1) + ".png").getAbsolutePath()).getImage();
            pacmanDownImgs[i] = new ImageIcon(new File(new File(assetsBaseFile, "pacman-down"), (i + 1) + ".png").getAbsolutePath()).getImage();
        }
        ghostImg = new ImageIcon(new File(new File(assetsBaseFile, "ghosts"), "blue_ghost.png").getAbsolutePath()).getImage();
        blinkyImg = new ImageIcon(new File(new File(assetsBaseFile, "ghosts"), "blinky.png").getAbsolutePath()).getImage();
        pinkyImg = new ImageIcon(new File(new File(assetsBaseFile, "ghosts"), "pinky.png").getAbsolutePath()).getImage();
        inkyImg = new ImageIcon(new File(new File(assetsBaseFile, "ghosts"), "inky.png").getAbsolutePath()).getImage();
        clydeImg = new ImageIcon(new File(new File(assetsBaseFile, "ghosts"), "clyde.png").getAbsolutePath()).getImage();
        frightenedImg = ghostImg; // blue_ghost.png
        dotImg = new ImageIcon(new File(new File(assetsBaseFile, "other"), "dot.png").getAbsolutePath()).getImage();
        fruitImg = new ImageIcon(new File(new File(assetsBaseFile, "other"), "apple.png").getAbsolutePath()).getImage();

        ghosts.add(new Ghost(32 * 10, 32 * 10, 2, "blinky"));
        ghosts.add(new Ghost(32 * 1, 32 * 1, 2, "pinky"));
        ghosts.add(new Ghost(32 * 19, 32 * 1, 2, "inky"));
        ghosts.add(new Ghost(32 * 1, 32 * 19, 2, "clyde"));

        addKeyListener(new Tadapter(player));
        setFocusable(true);
        timer = new Timer(16, this);
        timer.start();
        setPreferredSize(new Dimension(672, 672));
        spawnRandomEvent();
    }

    public void update() {
        player.move(this);
        player.updateAnimation();
        for (Ghost g : ghosts) g.move(this);
        checkEntityCollisions();
    }
    public boolean isWall(int x, int y) {
        int size = 28; 
        int left = x;
        int right = x + size - 1;
        int top = y;
        int bottom = y + size - 1;
        int startCol = left / 32;
        int endCol = right / 32;
        int startRow = top / 32;
        int endRow = bottom / 32;
        for (int r = startRow; r <= endRow; r++) {
            for (int c = startCol; c <= endCol; c++) {
                if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length) {
                    return true; 
                }
                if (grid[r][c] == 1) {
                    return true;
                }
            }
        }
        return false;
    }

// mấy cái logic nặng sẽ ở trong cái method này nhen, ví dụ chức năng của mấy cái fruit á
    private void checkEntityCollisions() {
        collectable.removeIf(f -> {
            if (Math.hypot(player.getX() - f.getX(), player.getY() - f.getY()) < 16) {
                f.onCollected(player);
                return true;
            }
            return false;
        });
        //Kiwi
        for (Ghost g : ghosts) {
            if (Math.hypot(player.getX() - g.getX(), player.getY() - g.getY()) < 16) {
                if (player.isDisguised()) {
                    System.out.println("Pac-Man đang tàng hình!");
                    } else {
                        System.out.println("Pac-Man bị Ma cắn!");
                // thêm code trừ mạng 
            }
            
        }
    }
        //durian
        ghosts.removeIf(g -> {
            if (Math.hypot(player.getX() - g.getX(), player.getY() - g.getY()) < 16) {
                return player.hasThorns();
            }
            return false;
        });
        // PacMan collision with ghosts
        for (Ghost g : ghosts) {
            if (Math.hypot(player.getX() - g.getX(), player.getY() - g.getY()) < 16) {
                if (!player.hasThorns()) {
                    player.loseLife();
                    player.setPosition(32, 32); // reset to start
                    if (player.getLives() <= 0) {
                        timer.stop();
                    }
                }
            }
        }
    }

    public void spawnRandomEvent() {
        if (grid.length == 0 || grid[0].length == 0) {
            return;
        }
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                if (grid[r][c] == 0) {
                    collectable.add(new LightPoint(c * 32, r * 32));
                }
            }
        }
        java.util.Random rand = new java.util.Random();
        int numberOfFruits = 7; 
        for (int i = 0; i < numberOfFruits; i++) {
            boolean fruitPlaced = false;
            while (!fruitPlaced) {
                int randomR = rand.nextInt(grid.length);
                int randomC = rand.nextInt(grid[0].length);
                if (grid[randomR][randomC] == 0) {
                    int fruitType = rand.nextInt(3); 
                    switch (fruitType) {
                        case 0 -> collectable.add(new Durian(randomC * 32, randomR * 32, "Durian"));
                        case 1 -> collectable.add(new Kiwi(randomC * 32, randomR * 32, "Kiwi"));
                        //tạo class fruit mới rồi thêm case vô đây copy y chang durian là đc
                }
                fruitPlaced = true;
        }}}}
    public Boolean checkWin() {
        return collectable.isEmpty();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        update();
        repaint();
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g.setColor(Color.BLACK);
    g.fillRect(0, 0, getWidth(), getHeight());
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                if (grid[r][c] == 1) {
                    g2d.setColor(Color.BLUE);
                    g2d.fillRect(c * 32, r * 32, 32, 32);
                }
            }
        }

        for (Collectable f : collectable) {
            if (f instanceof LightPoint) {
                if (dotImg != null) {
                    g2d.drawImage(dotImg, f.getX(), f.getY(), 32, 32, this);
                } else {
                    g2d.setColor(Color.WHITE);
                    g2d.fillOval(f.getX() + 8, f.getY() + 8, 16, 16);
                }
            } else {
                if (fruitImg != null) {
                    g2d.drawImage(fruitImg, f.getX(), f.getY(), 32, 32, this);
                } else {
                    g2d.setColor(Color.ORANGE);
                    g2d.fillOval(f.getX() + 4, f.getY() + 4, 24, 24);
                }
            }
        }

        for (Ghost ghost : ghosts) {
            Image img = frightenedImg;
            if (!ghost.getIsFrighted()) {
                switch (ghost.getGhostType()) {
                    case "blinky" -> img = blinkyImg;
                    case "pinky" -> img = pinkyImg;
                    case "inky" -> img = inkyImg;
                    case "clyde" -> img = clydeImg;
                }
            }
            if (img != null) {
                g2d.drawImage(img, ghost.getX(), ghost.getY(), 32, 32, this);
            } else {
                g2d.setColor(Color.RED);
                g2d.fillRect(ghost.getX(), ghost.getY(), 32, 32);
            }
        }

        Image[] pacmanFrames = pacmanRightImgs;
        switch (player.getDirection()) {
            case 3 -> pacmanFrames = pacmanLeftImgs;
            case 2 -> pacmanFrames = pacmanDownImgs;
            case 0 -> pacmanFrames = pacmanUpImgs;
        }
        Image pacmanImg = pacmanFrames[player.getAnimIndex()];
        if (pacmanImg != null) {
            g2d.drawImage(pacmanImg, player.getX(), player.getY(), 32, 32, this);
        } else {
            g2d.setColor(Color.YELLOW);
            g2d.fillOval(player.getX(), player.getY(), 32, 32);
        }

        g2d.setColor(Color.WHITE);
        g2d.drawString("Score: " + player.getScore(), 10, 20);
        g2d.drawString("Lives: " + player.getLives(), 10, 40);
    }
    public PacMan getPlayer() {return player;}
    public void setPlayer(PacMan player) {this.player = player;}
    public short[][] getGrid() {return grid;}
    public void setGrid(short[][] grid) {this.grid = grid;}
    public ArrayList<Collectable> getCollectable() {return collectable;}
    public List<Ghost> getGhosts() {return ghosts;}
    public void setGhosts(List<Ghost> ghosts) {this.ghosts = ghosts;}
    public Timer getTimer() {return timer;}

    private class Tadapter extends KeyAdapter {
        PacMan player;
        public Tadapter(PacMan player) {
            this.player = player;
        }
        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            if (key == KeyEvent.VK_LEFT) {
                player.setNextDirection(3);
            } else if (key == KeyEvent.VK_RIGHT) {
                player.setNextDirection(1);
            } else if (key == KeyEvent.VK_UP) {
                player.setNextDirection(0);
            } else if (key == KeyEvent.VK_DOWN) {
                player.setNextDirection(2);
}}}}
