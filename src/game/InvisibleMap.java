package game;
import gacha.*;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class InvisibleMap extends Map {
    private FogOfWar fogEffect;

    public InvisibleMap(SkinManager skinManager, GameRenderer renderer, FogOfWar fogEffect, short[][] grid) {
        super(skinManager, renderer, grid);
        this.fogEffect = fogEffect;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (fogEffect != null && getPlayer() != null) {
            Graphics2D g2d = (Graphics2D) g;
            int px = getPlayer().getX() + 16;
            int py = getPlayer().getY() + 16;
            fogEffect.draw(g2d, px, py, getWidth(), getHeight());
        }
    }
}