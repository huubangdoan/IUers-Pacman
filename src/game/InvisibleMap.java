package game;
import gacha.*;
import java.awt.*;
import java.awt.Graphics2D;

public class InvisibleMap extends Map {
    private FogOfWar fogEffect;

    public InvisibleMap(SkinManager skinManager, GameRenderer renderer, short[][] grid, Image wallImg, Image backGroundImg, FogOfWar fogEffect) {
        super(skinManager, renderer, grid, wallImg, backGroundImg);
        this.fogEffect = fogEffect;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        getRenderer().render((Graphics2D) g, this, getWallImg(), getBackGroundImg());
        if (fogEffect != null && getPlayer() != null) {
            Graphics2D g2d = (Graphics2D) g;
            int px = getPlayer().getX() + 16;
            int py = getPlayer().getY() + 16;
            fogEffect.draw(g2d, px, py, getWidth(), getHeight());
        }
    }
}