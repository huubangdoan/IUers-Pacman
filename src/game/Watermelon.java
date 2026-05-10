package game;
public class Watermelon extends Fruit{
    public Watermelon(int x, int y, String name){
        super(x, y, "Watermelon");
    }
    
    @Override
    public void onCollected(PacMan player){
        player.setHasWatermelon(true); 
        this.hide(); 
    }
}