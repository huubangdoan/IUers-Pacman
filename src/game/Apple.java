package game;
import java.awt.*;
public class Apple extends Fruit{
    public Apple(int x, int y, String name){
        super(x, y, "Apple");
    }
    @Override
    public void onCollected(PacMan player){
        player.activatePowerup(300); 
        player.addScore(200);
    }
    @Override
    public void draw(Graphics2D g2d, GameAssets assets) {
        g2d.drawImage(assets.appleImg, getX(), getY(), 32, 32, null);
    }
}
