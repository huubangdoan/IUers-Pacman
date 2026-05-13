package game;
public abstract class MoveSystem{
    int x, y;
    int dx, dy;
    int speed;
    public MoveSystem(int x, int y, int speed){
        this.x=x;
        this.y=y;
        this.speed=speed;
    }
    public abstract void move(Map map);
    public void setDirection(int dx, int dy){
        this.dx=dx;
        this.dy=dy;
    }
    public int getX(){return x;}
    public int getY(){return y;}
    public int getDx(){return dx;}
    public int getDy(){return dy;}
}