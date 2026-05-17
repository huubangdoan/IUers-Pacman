package game;
import gacha.SkinManager;
import java.awt.*;
import java.util.ArrayList;

public class ChaoTilesMap extends Map {
    private final ArrayList<Point> specialTiles = new ArrayList<>();
    public ChaoTilesMap( SkinManager skinManager, GameRenderer renderer, short[][] grid,GameStateListener gameStateListener, Image wallImg, Image backGroundImg) {
        super(skinManager, renderer, grid,  gameStateListener, wallImg, backGroundImg);
        buildSpecialTileCache();  
    }

    private void buildSpecialTileCache() {
        specialTiles.clear();
        for (int r = 0; r < gridRows; r++)
            for (int c = 0; c < gridCols; c++)
                if (grid[r][c] == 7 || grid[r][c] == 8 || grid[r][c] == 9)
                    specialTiles.add(new Point(c, r));
    }

    @Override
    public void update() {
        getPlayer().move(this);
        getPlayer().updateAnimation();
        getPlayer().updatePowerup();
        getPlayer().updateDragon();

        playerX = getPlayer().getX();
        playerY = getPlayer().getY();

        checkChaosTiles();

        for (Ghost g : getGhosts()) {
            g.move(this);
            g.updateFrightened();
        }
        checkEntityCollisions();
        checkGameStatus();
        repaint();
    }

    public void checkChaosTiles() {
        int row = playerY >> 5;
        int col = playerX >> 5;

        if (row < 0 || row >= gridRows || col < 0 || col >= gridCols) return;

        short tile = grid[row][col];
        if (tile == 0 || tile == 1) return; 

        if (tile == 7) {
            getPlayer().reverseDirection();
            grid[row][col] = 0;
            specialTiles.removeIf(p -> p.x == col && p.y == row); // sync cache
        } else if (tile == 8) {
            getPlayer().loseLife();
            getPlayer().setPosition(32, 32);
            grid[row][col] = 0;
            specialTiles.removeIf(p -> p.x == col && p.y == row);
        } else if (tile == 9) {
            getPlayer().activateWallHack(this);
            grid[row][col] = 0;
            specialTiles.removeIf(p -> p.x == col && p.y == row);
        }
    }
    public ArrayList<Point> getSpecialTiles(){return specialTiles;}
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        getRenderer().render((Graphics2D) g, this, getWallImg(), getBackGroundImg());
    }
}