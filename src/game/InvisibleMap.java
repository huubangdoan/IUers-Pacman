package game;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class InvisibleMap extends Map {
    private FogOfWar fogEffect;
    private long startTime;

    public InvisibleMap() {
        super(); // Gọi constructor của lớp Map
        this.grid = InvisibleMazeData.GRID;
        this.fogEffect = new FogOfWar(InvisibleMazeData.INITIAL_VISION_RADIUS);
        this.startTime = System.currentTimeMillis();
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

        drawScore(g2d);
    }

    private void drawScore(Graphics2D g2d) {
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.BOLD, 15));
        if (getPlayer() != null) {
            g2d.drawString("Score: " + getPlayer().getScore(), 10, 20);
            long time = (System.currentTimeMillis() - startTime) / 1000;
            g2d.drawString("Survival: " + time + "s", 10, 40);
        }
    }
}