package game;
import java.awt.*;
public class Chilli extends Fruit{
    public Chilli(int x, int y, String name){
        super(x, y, "Chilli");
    }
    
    @Override
    public void onCollected(PacMan player){
        player.addScore(50);
            }
    @Override
    public void draw(Graphics2D g2d, GameAssets assets) {
        g2d.drawImage(assets.chilliImg, getX(), getY(), 32, 32, null);
        }
    }
