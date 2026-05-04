 
public class PacMan {
    private int x,y; 
    private int direction = 1; //hướng hiện tại
    private int nextDirection = 1; //hướng người chơi input
    private int speed;
    private int score = 0;
    private int lives = 3;
    private boolean hasThorns = false;
    private final int SIZE = 28;

    public PacMan(int x, int y, int speed) {
        this.x = x;
        this.y = y;
        this.speed = speed;
    }

    public int getX() {
        return x; 
    }
    public int getY() {
        return y;
    }

    //input
    public void setNextDirection(int dir) {
        this.nextDirection = dir;
    }
    //chuyển hướng dx,dy
    private int getDx(int dir) {
         switch(dir) {
            case 1:
                return 1; // trái
            case 3:
                return -1; // phải
            default:
                return  0;
        
        }
    }
    
    private int getDy(int dir) {
        switch(dir) {
            case 2:
                return 1; // lên
            case 0:
                return -1; // xuống
            default:
                return 0;
        }
    }
 public void move(Map map){ 
            if (x % 32 == 0 && y % 32 == 0) {
                int ndx = getDx(nextDirection);
                int ndy = getDy(nextDirection);
                
                if (!map.isWall(x + ndx * speed, y + ndy * speed)) {
                    direction = nextDirection;
                }
            }
            int dx = getDx(direction);
            int dy = getDy(direction);
            if (!map.isWall(x + dx * speed, y + dy * speed)) {
                 x += dx * speed;
                 y += dy * speed;
            }
        }
        
    public boolean hasThorns() {
        return hasThorns;
    }

    public void setHasThorns(boolean hasThorns) {
        this.hasThorns = hasThorns;
    }

    public void addScore(int point) {
        score += point;
    }
}
