package game;
import java.awt.*;
public class Watermelon extends Fruit{
    public Watermelon(int x, int y, String name){
        super(x, y, "Watermelon");
    }
    
    @Override
    public void onCollected(PacMan player){
        player.setHasWatermelon(true); 
        this.hide(); 
    }
    @Override
    public void draw(Graphics2D g2d, GameAssets assets) {
        g2d.drawImage(assets.watermelonImg, getX(), getY(), 32, 32, null);
    }
}