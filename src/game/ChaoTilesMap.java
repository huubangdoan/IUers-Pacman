package game;
import gacha.SkinManager;
import java.awt.*;

public class ChaoTilesMap extends Map {
    public ChaoTilesMap( SkinManager skinManager, GameRenderer renderer, short[][] grid,GameStateListener gameStateListener, Image wallImg, Image backGroundImg) {
        super(skinManager, renderer, grid,  gameStateListener, wallImg, backGroundImg);

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
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        getRenderer().render((Graphics2D) g, this, getWallImg(), getBackGroundImg());
    }
}