public class Chilli extends Fruit{
    public Chilli(int x, int y, String name){
        super(x, y, "Chilli");
    }
    
    @Override
    public void onCollected(PacMan player){
        player.addScore(50);
            }
        }
