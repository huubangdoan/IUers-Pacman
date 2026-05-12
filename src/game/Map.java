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
    private GameAssets assets; 
    private GameRenderer renderer;
    public static final int FRUIT_DURATION = 300;
    private long startTime;
    private ScoreManager scoreManager;
    public Map() {
        this.scoreManager = new ScoreManager();
        this.grid = MapData.GRID;
        this.assets = new GameAssets();
        this.renderer = new GameRenderer(this.assets);
        this.collectable = new ArrayList<>();
        this.ghosts = new ArrayList<>();
        this.startTime = System.currentTimeMillis();

        List<Point> occupiedPositions = new ArrayList<>();
        java.util.Random rand = new java.util.Random();
        Point pacmanSpot = findRandomEmptySpot(rand, occupiedPositions);
        this.player = new PacMan(pacmanSpot.x * 32, pacmanSpot.y * 32, 2);
        occupiedPositions.add(pacmanSpot);

        String[] ghostTypes = {"blinky", "pinky", "inky", "clyde"};
        for (String type : ghostTypes) {
            Point ghostSpot = findRandomEmptySpot(rand, occupiedPositions);
            ghosts.add(new Ghost(ghostSpot.x * 32, ghostSpot.y * 32, 2, type));
            occupiedPositions.add(ghostSpot); // Đánh dấu ô này đã có Ma đứng
        }
        addKeyListener(new PacmanInput(player));
        setFocusable(true);
        setPreferredSize(new Dimension(672, 672));
        spawnRandomEvent();
        timer = new Timer(16, this);
        timer.start();
    }
    public void update() {
        player.move(this);
        player.updateAnimation();
        player.updatePowerup();
        player.updateDragon();
        
        checkChaosTiles();
        
        for (Ghost g : ghosts) {
            g.move(this);
            g.updateFrightened();
        }
        checkEntityCollisions();
        repaint();
    }
    private Point findRandomEmptySpot(java.util.Random rand, List<Point> occupied) {
        while (true) {
            int r = rand.nextInt(grid.length);
            int c = rand.nextInt(grid[0].length);
            Point spot = new Point(c, r); 
            if (grid[r][c] == 0 && !occupied.contains(spot)) {
                return spot;
            }
        }
    }
    public boolean isWall(int x, int y) {
        final int size = 32; 
        int startCol = x / 32;
        int endCol = (x + size - 1) / 32;
        int startRow = y / 32;
        int endRow = (y + size - 1) / 32;
        
        for (int r = startRow; r <= endRow; r++) {
            for (int c = startCol; c <= endCol; c++) {
                if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length || grid[r][c] == 1) {
                    return true;
                }
            }
        }
        return false;
    }

    private void checkEntityCollisions() {
        boolean[] fruitEaten = {false};

        collectable.removeIf(f -> {
            if (Math.hypot(player.getX() - f.getX(), player.getY() - f.getY()) < 16) {
                f.onCollected(player);
                //apple
                if (f instanceof Apple) {
                    for (Ghost g : ghosts){ 
                        g.setFrightened(true, 300);
                    }
                }
                if (f instanceof Chilli) {
                    player.activateChilliPower(9000);
                }
                if (f instanceof Kiwi) {
                    player.activateKiwiDisguise();
                }
                if (!(f instanceof LightPoint)) {
                    fruitEaten[0] = true;
                }
                return true;
            }
            return false;
        });
        handleFruitLogic();
        checkLive();
        if (fruitEaten[0]) {
            spawnOneFruit();
        }
    }

    private void checkChaosTiles() {

    int row = player.getY() / 32;
    int col = player.getX() / 32;

    if (row < 0 || row >= grid.length ||
        col < 0 || col >= grid[0].length) {
        return;
    }

    short tile = grid[row][col];

    // TILE 7 = reverse
    if (tile == 7) {

        player.reverseDirection();

        grid[row][col] = 0;
    }

    // TILE 8 = trap
    else if (tile == 8) {

        player.loseLife();
        player.setPosition(32, 32);

        grid[row][col] = 0;
    }

    // TILE 9 = xuyên tường
    else if (tile == 9) {

        player.activateWallHack();

        grid[row][col] = 0;
    }
}

    private void checkLive(){
        for (Ghost g : ghosts){
            if (Math.hypot(player.getX() - g.getX(), player.getY() - g.getY()) < 16){
                if (!player.hasThorns() && !g.getIsFrighted() && !player.isDisguised()) {
                    handlePlayerDeath(); 
                    return;}
            }
        }
    }
    private void handleFruitLogic() {
        if (player.hasWatermelon()) {
            for (Ghost g : ghosts) {
                if (Math.hypot(player.getX() - g.getX(), player.getY() - g.getY()) < 96) {
                    g.setFrozen(true, 180);
                    player.setHasWatermelon(false);
                    break;
                }
            }
        }
        // Durian // apple
            for (Ghost g : ghosts) {
                if (player.hasThorns()|| g.getIsFrighted()) {
                if (Math.hypot(player.getX() - g.getX(), player.getY() - g.getY()) < 16) {
                    g.respawnAtRandomLocation(MapData.GRID);
                    g.setFrightened(false, 0);
                    player.setHasThorns(false);
                    break;
                }
            }
        }
    
        // DragonFruit 
        if (player.isDragonMode()) {
            for (Ghost g : ghosts) {
                if ((Math.hypot(player.getX() - g.getX(), player.getY() - g.getY()) < 64)){
                    g.knockbackFrom(player);
                    g.setStunned(true, 90);
                    player.setDragonMode(false);
                    break;
                }
            }
        }
    }
    public void spawnRandomEvent() {
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                if (grid[r][c] == 0) collectable.add(new LightPoint(c * 32, r * 32));
            }
        }
        spawnOneFruit();
    }

    private void spawnOneFruit() {
        java.util.Random rand = new java.util.Random();
        boolean fruitPlaced = false;
        while (!fruitPlaced) {
            int randomR = rand.nextInt(grid.length);
            int randomC = rand.nextInt(grid[0].length);
            if (grid[randomR][randomC] == 0) {
                int type = rand.nextInt(6);
                if (type == 0) collectable.add(new Durian(randomC * 32, randomR * 32, "Durian"));
                else if (type == 1) collectable.add(new Apple(randomC * 32, randomR * 32, "Apple"));
                else if (type == 2) collectable.add(new Kiwi(randomC * 32, randomR * 32, "Kiwi"));
                else if (type == 3) collectable.add(new DragonFruit(randomC * 32, randomR * 32, "Dragon Fruit"));
                else if (type == 4) collectable.add(new Watermelon(randomC * 32, randomR * 32, "Watermelon"));
                else collectable.add(new Chilli(randomC * 32, randomR * 32, "Chilli"));
                fruitPlaced = true;
            }
        }
    }
    private void handlePlayerDeath() {
        player.loseLife();
        if (player.getLives() <= 0) {
            scoreManager.saveData(player.getScore());
            timer.stop();
        } else {
            player.setPosition(32, 32);
        }
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        renderer.render((Graphics2D) g, this);
    }

    @Override 
    public void actionPerformed(ActionEvent e) { update(); repaint(); }
    public PacMan getPlayer() { return player; }
    public Timer getTimer() { return timer; }
    public short[][] getGrid() { return grid; }
    public List<Ghost> getGhosts() { return ghosts; }
    public ArrayList<Collectable> getCollectable() { return collectable; }
    public int getElapsedSeconds(){
        long now = System.currentTimeMillis();
        return (int) ((now - this.startTime) / 1000);}
    public ScoreManager getScoreManager() { return scoreManager; }
}