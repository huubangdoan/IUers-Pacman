package game;
import gacha.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class InvisibleMap extends Map {
    private FogOfWar fogEffect;
    public InvisibleMap(SkinManager skinManager) {
        super(); 
        this.grid = InvisibleMazeData.GRID;
        this.fogEffect = new FogOfWar(InvisibleMazeData.INITIAL_VISION_RADIUS);
        getCollectable().clear(); 
        spawnRandomEvent();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        getRenderer().render((Graphics2D) g, this);
        if (fogEffect != null && getPlayer() != null) {
            int px = getPlayer().getX() + 16;
            int py = getPlayer().getY() + 16;
            fogEffect.draw(g2d, px, py, getWidth(), getHeight());
        }
    }
}