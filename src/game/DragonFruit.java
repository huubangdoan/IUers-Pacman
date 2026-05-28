package game;
import java.awt.*;
public class DragonFruit extends Fruit{
    public DragonFruit(int x, int y, String name){
        super(x, y, "DragonFruit");
    }
    @Override
    public void onCollected (PacMan player){
    player.addScore(200);
    player.setDragonMode(true);
    player.setDragonTimer(300);
    }
    @Override
    public void draw(Graphics2D g2d, GameAssets assets) {
        g2d.drawImage(assets.dragonFruitImg, getX(), getY(), 32, 32, null);
    }
}