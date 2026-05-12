package game;
import java.awt.*;
public class ChaoTilesMap extends Map{
    public ChaoTilesMap(){
        super();
        this.grid= ChaoData.GRID;
        getCollectable().clear(); 
        spawnRandomEvent();
    }
    @Override
    public void update() {
        getPlayer().move(this);
        getPlayer().updateAnimation();
        getPlayer().updatePowerup();
        getPlayer().updateDragon(); 
        getPlayer().updateDragon();
        
        checkChaosTiles();
        
        for (Ghost g : getGhosts()) {
            g.move(this);
            g.updateFrightened();
        }
        checkEntityCollisions();
        repaint();
    }
    public void checkChaosTiles() {

        int row = getPlayer().getY() / 32;
        int col = getPlayer().getX() / 32;
    
        if (row < 0 || row >= grid.length ||
            col < 0 || col >= grid[0].length) {
            return;
        }
        else{
    
        short tile = grid[row][col];
    
        // TILE 7 = reverse
        if (tile == 7) {
    
            getPlayer().reverseDirection();
    
            grid[row][col] = 0;
        }
        // TILE 8 = trap
        else if (tile == 8) {
            getPlayer().loseLife();
            getPlayer().setPosition(32, 32);
            grid[row][col] = 0;
        }
    
        // TILE 9 = xuyên tường
        else if (tile == 9) {
    
            getPlayer().activateWallHack();
    
            grid[row][col] = 0;
        }
        }
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        getRenderer().render((Graphics2D) g2d, this);
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                if (grid[r][c] == 7) {
                    g2d.setColor(Color.GREEN);
                    g2d.fillRect(c * 32, r * 32, 32, 32);
                }else if (grid[r][c] == 8) {
                    g2d.setColor(Color.MAGENTA);
                    g2d.fillRect(c * 32, r * 32, 32, 32);
                }else if (grid[r][c] == 9) {
                    g2d.setColor(Color.RED);
                    g2d.fillRect(c * 32, r * 32, 32, 32);
                }
            }
        }
    }
}