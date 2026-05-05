public class DragonFruit extends Fruit{
    public DragonFruit(int x, int y, String name){
        super(x, y, "DragonFruit");
    }
    @Override
    public void onCollected (PacMan player){
    player.addScore(200);
    player.setDragonMode(true);
    player.setDragonTimer(300); //tgian hiệu lực là 5s
    }
}