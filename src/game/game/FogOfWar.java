package game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

public class FogOfWar {
    private int visionRadius;

    public FogOfWar(int radius){
        this.visionRadius = radius;
    }
    public void draw(Graphics2D g2d, int centerX, int centerY, int screenWidth, int screenHeight) {
        Area fog = new Area(new Rectangle(0, 0, screenWidth, screenHeight));

        Ellipse2D.Double visionCircle = new Ellipse2D.Double(
            centerX - visionRadius, centerY - visionRadius,
            visionRadius * 2, visionRadius * 2
        ) ;
        fog.subtract(new Area(visionCircle));

        g2d.setColor(new Color(0, 0 , 0, 240));
        g2d.fill(fog);

    }
    public void setVisionRadius(int radius){ this.visionRadius = radius;}
    public int getVisionRadius(){ return visionRadius;}
}
