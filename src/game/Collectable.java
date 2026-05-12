package game;
public interface Collectable{
    public void onCollected(PacMan player);
    public int getX();
    public int getY();
}