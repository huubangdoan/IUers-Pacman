import java.awt.*;
import java.awt.event.*;
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
    public Map() {
        grid= new short[][]{};; // mấy bạn làm map làm lại cái này nhé, 1 là tường, 0 là trống, 2 là pacman, 3 là ma
        player = new PacMan(32, 32, 2);
        collectable = new ArrayList<>();
        ghosts = new ArrayList<>();
        addKeyListener(new Tadapter(player));
        setFocusable(true);
        timer = new Timer(16, this);
        timer.start();
        setPreferredSize(new Dimension(640, 640));
        spawnRandomEvent();
    }

    public void update() {
        player.move(this);
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
    }

    public void spawnRandomEvent() {for (int r = 0; r < grid.length; r++) {
        for (int c = 0; c < grid[0].length; c++) {
            if (grid[r][c] == 0) {
                collectable.add(new LightPoint(c * 32, r * 32));
            }}}
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
            //vẽ thêm PacMan vs lightPoint nx nhen mấy cưng
        }    
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
