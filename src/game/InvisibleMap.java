package game;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class InvisibleMap extends Map {
    private FogOfWar fogEffect;
    public InvisibleMap() {
        super(); 
        this.grid = InvisibleMazeData.GRID;
        getCollectable().clear(); 
        spawnRandomEvent();
        this.fogEffect = new FogOfWar(InvisibleMazeData.INITIAL_VISION_RADIUS);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        if (fogEffect != null && getPlayer() != null) {
            int px = getPlayer().getX() + 16;
            int py = getPlayer().getY() + 16;
            fogEffect.draw(g2d, px, py, getWidth(), getHeight());
        }
    }
}