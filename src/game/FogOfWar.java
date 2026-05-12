package game;

import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class FogOfWar {
    private int visionRadius;
    private static final Color FOG_COLOR = new Color(0, 0, 0, 240);
    private final Ellipse2D.Double visionCircle = new Ellipse2D.Double();
    private int cachedW = -1, cachedH = -1;
    private Area cachedScreenRect;

    public FogOfWar(int radius) {
        this.visionRadius = radius;
    }

    public void draw(Graphics2D g2d, int centerX, int centerY, int screenWidth, int screenHeight) {
        if (screenWidth != cachedW || screenHeight != cachedH) {
            cachedScreenRect = new Area(new Rectangle2D.Float(0, 0, screenWidth, screenHeight));
            cachedW = screenWidth;
            cachedH = screenHeight;
        }
        visionCircle.setFrame(
            centerX - visionRadius,
            centerY - visionRadius,
            visionRadius * 2,
            visionRadius * 2
        );
        Area fog = new Area(cachedScreenRect); 
        fog.subtract(new Area(visionCircle));
        g2d.setColor(FOG_COLOR);
        Shape oldClip = g2d.getClip();
        g2d.setClip(fog);
        g2d.fillRect(0, 0, screenWidth, screenHeight);
        g2d.setClip(oldClip);
    }

    public void setVisionRadius(int radius) { this.visionRadius = radius; }
    public int getVisionRadius()            { return visionRadius; }
}