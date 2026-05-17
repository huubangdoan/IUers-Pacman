package game;

import gacha.SkinManager;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import controller.*;

public class Map extends JPanel implements ActionListener {
    private PacMan player;
    protected short[][] grid;
    private ArrayList<Collectable> collectable;
    private List<Ghost> ghosts;
    private Timer timer;
    private GameRenderer renderer;
    public static final int FRUIT_DURATION = 300;
    private long startTime;
    private ScoreManager scoreManager;
    private Image backGroundImg, wallImg;
    protected final java.util.Random rand = new java.util.Random();
    protected int gridRows, gridCols;
    protected int playerX, playerY;
    private GameStateListener gameStateListener;

    public Map( SkinManager skinManager, GameRenderer renderer, short[][] grid,GameStateListener gameStateListener, Image wallImg, Image backGroundImg) {
        this.scoreManager = skinManager != null ? skinManager.getScoreManager() : new ScoreManager();
        this.grid        = grid;
        this.renderer    = renderer;
        this.gameStateListener= gameStateListener;
        this.wallImg= wallImg;
        this.backGroundImg= backGroundImg;
        this.collectable = new ArrayList<>();
        this.ghosts      = new ArrayList<>();
        this.startTime   = System.currentTimeMillis();

        updateGridCache();
        initEntities();
        addKeyListener(new PacmanInput(player));
        setFocusable(true);
        setPreferredSize(new Dimension(672, 672));
        spawnRandomEvent();
        timer = new Timer(16, this);
        timer.start();
    }
    protected void updateGridCache() {
        gridRows = grid.length;
        gridCols = grid[0].length;
    }

    public void initEntities() {
        List<Point> occupied = new ArrayList<>();
        Point pacmanSpot = findRandomEmptySpot(rand, occupied);
        this.player = new PacMan(pacmanSpot.x * 32, pacmanSpot.y * 32, 2);
        occupied.add(pacmanSpot);
        String[] ghostTypes = {"blinky", "pinky", "inky", "clyde"};
        for (String type : ghostTypes) {
            Point spot = findRandomEmptySpot(rand, occupied);
            ghosts.add(new Ghost(spot.x * 32, spot.y * 32, 2, type));
            occupied.add(spot);
        }
    }


    public void update() {
        if (!player.hasChilli()) {
            player.setSpeed(utils.GameConfig.pacmanSpeed);
        }
        player.move(this);
        player.updateAnimation();
        player.updatePowerup();
        player.updateDragon();
        playerX = player.getX();
        playerY = player.getY();

        for (Ghost g : ghosts) {
            g.move(this);
            g.updateFrightened();
        }
        checkEntityCollisions();
        checkGameStatus();
        repaint();
    }
    public void checkGameStatus() {
        if (player.getLives() <= 0) {
            if (timer.isRunning()) {
                timer.stop(); 
            }
            if (gameStateListener != null) {
                gameStateListener.onGameOver(player.getScore());
            } return; 
        }
        boolean noDotsLeft = true;
        for (Collectable c : collectable) {
            if (c instanceof LightPoint|| c instanceof Fruit) {
                noDotsLeft = false; 
                break;
            }
        }
        if (noDotsLeft) {
            timer.stop();
            if (gameStateListener != null) {
                gameStateListener.onGameWon(player.getScore());
            }
        }
    }
    public Point findRandomEmptySpot(java.util.Random r, List<Point> occupied) {
        while (true) {
            int row = r.nextInt(gridRows);
            int col = r.nextInt(gridCols);
            Point spot = new Point(col, row);
            if (grid[row][col] == 0 && !occupied.contains(spot)) return spot;
        }
    }

    public boolean isWall(int x, int y) {
        int startCol = x >> 5,      endCol = (x + 31) >> 5;
        int startRow = y >> 5,      endRow = (y + 31) >> 5;
        for (int r = startRow; r <= endRow; r++) {
            for (int c = startCol; c <= endCol; c++) {
                if (r < 0 || r >= gridRows || c < 0 || c >= gridCols || grid[r][c] == 1)
                    return true;
            }
        }
        return false;
    }

    public void checkEntityCollisions() {
        final int px = playerX, py = playerY;
        boolean[] fruitEaten = {false};

        collectable.removeIf(f -> {
            int dx = px - f.getX(), dy = py - f.getY();
            if (dx * dx + dy * dy < 256) { 
                f.onCollected(player);
                if (f instanceof Apple)  { for (Ghost g : ghosts) g.setFrightened(true, 300); }
                if (f instanceof Chilli) { player.activateChilliPower(9000); }
                if (f instanceof Kiwi)   { player.activateKiwiDisguise(); }
                if (!(f instanceof LightPoint)) fruitEaten[0] = true;
                return true;
            }
            return false;
        });
        handleFruitLogic();
        checkLive();
        if (fruitEaten[0]) spawnOneFruit();
    }

    public void checkLive() {
        final int px = playerX, py = playerY;
        for (Ghost g : ghosts) {
            int dx = px - g.getX(), dy = py - g.getY();
            if (dx * dx + dy * dy < 256) { 
                if (!player.hasThorns() && !g.getIsFrighted() && !player.isDisguised()) {
                    handlePlayerDeath();
                    return;
                }
            }
        }
    }

    public void handleFruitLogic() {
        final int px = playerX, py = playerY;
        if (player.hasWatermelon()) {
            for (Ghost g : ghosts) {
                int dx = px - g.getX(), dy = py - g.getY();
                if (dx * dx + dy * dy < 9216) { // 96²
                    g.setFrozen(true, 180);
                    player.setHasWatermelon(false);
                    break;
                }
            }
        }
        for (Ghost g : ghosts) {
            if (player.hasThorns() || g.getIsFrighted()) {
                int dx = px - g.getX(), dy = py - g.getY();
                if (dx * dx + dy * dy < 256) { // 16²
                    g.respawnAtRandomLocation(grid);
                    g.setFrightened(false, 0);
                    player.setHasThorns(false);
                    break;
                }
            }
        }
        if (player.isDragonMode()) {
            for (Ghost g : ghosts) {
                int dx = px - g.getX(), dy = py - g.getY();
                if (dx * dx + dy * dy < 4096) { 
                    g.knockbackFrom(player, this);
                    g.setStunned(true, 90);
                    player.setDragonMode(false);
                    break;
                }
            }
        }
    }

    public void spawnRandomEvent() {
        for (int r = 0; r < gridRows; r++)
            for (int c = 0; c < gridCols; c++)
                if (grid[r][c] == 0) collectable.add(new LightPoint(c * 32, r * 32));
        spawnOneFruit();
    }

    public void spawnOneFruit() {
        while (true) {
            int r = rand.nextInt(gridRows), c = rand.nextInt(gridCols);
            if (grid[r][c] == 0) {
                int type = rand.nextInt(6);
                if      (type == 0) collectable.add(new Durian(c*32, r*32, "Durian"));
                else if (type == 1) collectable.add(new Apple(c*32, r*32, "Apple"));
                else if (type == 2) collectable.add(new Kiwi(c*32, r*32, "Kiwi"));
                else if (type == 3) collectable.add(new DragonFruit(c*32, r*32, "Dragon Fruit"));
                else if (type == 4) collectable.add(new Watermelon(c*32, r*32, "Watermelon"));
                else                collectable.add(new Chilli(c*32, r*32, "Chilli"));
                return;
            }
        }
    }

    public void handlePlayerDeath() {
        player.loseLife();
        if (player.getLives() <= 0) {
            scoreManager.saveData(player.getScore());
            timer.stop();
        } else {
            player.setPosition(32, 32);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        renderer.render((Graphics2D) g, this, wallImg, backGroundImg);
    }

    @Override
    public void actionPerformed(ActionEvent e) { update(); }
    public GameRenderer getRenderer()              { return renderer; }
    public PacMan getPlayer()                      { return player; }
    public Image getWallImg()                      { return wallImg; }
    public Image getBackGroundImg()                      { return backGroundImg; }
    public Timer getTimer()                        { return timer; }
    public short[][] getGrid()                     { return grid; }
    public void setGrid( short[][] grid)           {this.grid=grid;}
    public List<Ghost> getGhosts()                 { return ghosts; }
    public ArrayList<Collectable> getCollectable() { return collectable; }
    public int getElapsedSeconds() {
        return (int) ((System.currentTimeMillis() - startTime) / 1000);
    }
    public ScoreManager getScoreManager()          { return scoreManager; }
    public void setGameStateListener(GameStateListener listener) {
        this.gameStateListener = listener;
    }
}