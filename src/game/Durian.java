package game;
public class Durian extends Fruit{
    public Durian(int x, int y, String name){
        super(x, y, "Durian");
    }
    @Override
    public void onCollected(PacMan player){
        player.setHasThorns(true);
        player.addScore(100);
    }
}