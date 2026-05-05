public class Apple extends Fruit{
    public Apple(int x, int y, String name){
        super(x, y, "Apple");
    }
    @Override
    public void onCollected(PacMan player){
        player.activatePowerup(300); // 300 frames = ~5 seconds at 60 fps
        player.addScore(200);
    }
}
