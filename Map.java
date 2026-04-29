import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class Map extends JPanel implements ActionListener {
    private PacMan player;
    protected short[][] grid;
    private ArrayList<Fruit> fruit;
    private List<Ghost> ghosts;
    private Timer timer;
    private Image lightPointImg, wallImg;
    private LightPoint lightPoint;

    public Map() {
        player = new PacMan(32, 32, 2);
        fruit = new ArrayList<>();
        ghosts = new ArrayList<>();
        addKeyListener(new Tadapter(player));
        setFocusable(true);
        
        timer = new Timer(16, this);
        timer.start();
    }

    public void update() {
        player.move(this);
        for (Ghost g : ghosts) g.move(this);
        checkEntityCollisions();
    }

    public boolean isWall(int x, int y) {
        int col = x / 32;
        int row = y / 32;
        // Giả sử grid[row][col] == 1 là tường
        if (row < 0 || col < 0 || row >= grid.length || col >= grid[0].length) return true;
        return grid[row][col] == 1;
    }

    private void checkEntityCollisions() {
        fruit.removeIf(f -> {
            if (Math.hypot(player.getX() - f.getX(), player.getY() - f.getY()) < 16) {
                f.onCollected(player);
                return true;
            }
            return false;
        });
    }

    public void spawnRandomEvent() { /* Logic tạo event */ }
    
    public Boolean checkWin() {
        return fruit.isEmpty(); // Ví dụ thắng khi hết fruit
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        update();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Vẽ tường, hạt sáng, player và ghost tại đây
    }public PacMan getPlayer() {
        return player;
    }

    public void setPlayer(PacMan player) {
        this.player = player;
    }

    public short[][] getGrid() {
        return grid;
    }

    public void setGrid(short[][] grid) {
        this.grid = grid;
    }

    public ArrayList<Fruit> getFruit() {
        return fruit;
    }

    public List<Ghost> getGhosts() {
        return ghosts;
    }

    public void setGhosts(List<Ghost> ghosts) {
        this.ghosts = ghosts;
    }

    public Timer getTimer() {
        return timer;
    }
   

}