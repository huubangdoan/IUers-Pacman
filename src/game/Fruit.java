package game;
public abstract class Fruit implements Collectable{
    private int x;
    private int y;
    private String name;
    private boolean isVisible = true;
    public Fruit(int x, int y, String name){
        this.x=x;
        this.y=y;
        this.name=name;
    }
    public String getName() {
    return name;
}
    public void hide() {
        this.isVisible = false;
    }
    public boolean isVisible() {
        return isVisible;
    }
    @Override
    public abstract void onCollected(PacMan player);
    @Override
    public int getX(){return x;}
    @Override
    public int getY(){return y;}
}