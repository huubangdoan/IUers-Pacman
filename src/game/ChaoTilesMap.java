package game;
import java.awt.*;
import java.util.ArrayList;

import gacha.SkinManager;

public class ChaoTilesMap extends Map {

    private final ArrayList<Point> specialTiles = new ArrayList<>();
    public ChaoTilesMap(SkinManager skinManager) {
        super(skinManager);
        this.grid = ChaoData.GRID;
        updateGridCache();         
        respawnEntitiesForGrid();
        getCollectable().clear();
        buildSpecialTileCache();  
        spawnRandomEvent();
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
            getPlayer().activateWallHack();
            grid[row][col] = 0;
            specialTiles.removeIf(p -> p.x == col && p.y == row);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        for (Point p : specialTiles) {
            short tile = grid[p.y][p.x];
            if      (tile == 7) g2d.setColor(Color.GREEN);
            else if (tile == 8) g2d.setColor(Color.MAGENTA);
            else if (tile == 9) g2d.setColor(Color.RED);
            else continue;
            g2d.fillRect(p.x * 32, p.y * 32, 32, 32);
        }
    }
}