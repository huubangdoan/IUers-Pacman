public abstract class Fruit implements Collectable{
    private int x;
    private int y;
    private String name;
    public Fruit(int x, int y, String name){
        this.x=x;
        this.y=y;
        this.name=name;
    }
    @Override
    public abstract void onCollected(PacMan player);
    @Override
    public int getX(){return x;}
    @Override
    public int getY(){return y;}
}