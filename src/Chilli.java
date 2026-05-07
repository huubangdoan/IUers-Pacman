public class Chilli extends Fruit{
    public Chilli(int x, int y, String name){
        super(x, y, "Chilli");
    }
    
    @Override
    public void onCollected(PacMan player){
        player.setChilliMode(true);
        player.addScore(50);
        player.activateChilliPower(5000);
            }
        }
