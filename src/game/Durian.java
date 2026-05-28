package game;
import java.awt.*;
public class Durian extends Fruit{
    public Durian(int x, int y, String name){
        super(x, y, "Durian");
    }
    @Override
    public void onCollected(PacMan player){
        player.setHasThorns(true);
        player.addScore(100);
    }
    @Override
    public void draw(Graphics2D g2d, GameAssets assets) {
        g2d.drawImage(assets.durianImg, getX(), getY(), 32, 32, null);
    }
}