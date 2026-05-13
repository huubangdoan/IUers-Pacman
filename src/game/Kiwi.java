package game;
public class Kiwi extends Fruit{
    public Kiwi(int x, int y, String name){
        super(x, y, name);
    }
    @Override
    public void onCollected(PacMan player){
                player.activateKiwiDisguise();
            }
        }