package game;
import java.awt.*;
public interface Collectable{
    public void onCollected(PacMan player);
    public int getX();
    public int getY();
    void draw(Graphics2D g2d, GameAssets assets);
}