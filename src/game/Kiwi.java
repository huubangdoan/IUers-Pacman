package game;
import java.awt.*;
public class Kiwi extends Fruit{
    public Kiwi(int x, int y, String name){
        super(x, y, "Kiwi");
    }
    @Override
    public void onCollected(PacMan player){
                player.activateKiwiDisguise();
                player.addScore(150);
            }
            @Override
    public void draw(Graphics2D g2d, GameAssets assets) {
        g2d.drawImage(assets.kiwiImg, getX(), getY(), 32, 32, null);
}
    }