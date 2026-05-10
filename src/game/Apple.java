package game;
public class Apple extends Fruit{
    public Apple(int x, int y, String name){
        super(x, y, "Apple");
    }
    @Override
    public void onCollected(PacMan player){
        player.activatePowerup(300); 
        player.addScore(200);
    }
}
