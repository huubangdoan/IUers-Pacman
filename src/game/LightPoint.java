package game;
public class LightPoint implements Collectable{
    private int x;
    private int y;
    public LightPoint(int x, int y){
        this.x=x;
        this.y=y;
    }
    @Override
    public void onCollected(PacMan player){
        player.addScore(10);
    }
    @Override
    public int getX(){return x;}
    @Override
    public int getY(){return y;}
}