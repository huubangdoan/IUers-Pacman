import java.util.Random;
public class Ghost extends MoveSystem{
    private String ghostType;
    private boolean isFrighted = false; //con ma có trong trạng thái có thể ăn được hay không
    public Ghost(int x, int y, int speed, String ghostType){
        super(x,y, speed);
        this.ghostType=ghostType;
        this.dx = 1;
        this.dy = 0;
    }
    @Override
    public void move(Map map){ //PacMan copy y chang khác mỗi cái nó di chuyển theo set Direction thôi
            if (x % 32 == 0 && y % 32 == 0) {
                if (map.isWall(x + dx * 32, y + dy * 32)) {
                    generateRandomDirection(map);
                } else {
                    if (new Random().nextInt(100) < 5) { 
                        generateRandomDirection(map);
                    }}}
            x += dx * speed;
            y += dy * speed;
        }
    public void generateRandomDirection(Map map){
    int[] directions = {-1, 0, 1}; 
    java.util.Random rand = new java.util.Random();
    int attempts = 0;
    while (attempts < 10) {
        int newDx = directions[rand.nextInt(3)]; 
        int newDy = (newDx == 0) ? directions[rand.nextInt(3)] : 0; 
        if ((newDx != 0 || newDy != 0) && !map.isWall(x + newDx * speed, y + newDy * speed)) {
            this.dx = newDx;
            this.dy = newDy;
            break; 
        }
        attempts++;
    }
    // If no valid direction found after attempts, reverse direction
    if (attempts >= 10) {
        this.dx = -this.dx;
        this.dy = -this.dy;
    }
}
    public String getGhostType(){return ghostType;}
    public Boolean getIsFrighted(){return isFrighted;}
}