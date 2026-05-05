import java.util.Random;
public class Ghost extends MoveSystem{
    private String ghostType;
    private boolean isFrighted = false; //con ma có trong trạng thái có thể ăn được hay không
    private int frightenedDuration = 0;
    
    public Ghost(int x, int y, int speed, String ghostType){
        super(x,y, speed);
        this.ghostType=ghostType;
        this.dx = 1;
        this.dy = 0;
    }
    @Override
    public void move(Map map){
            if (x % 32 == 0 && y % 32 == 0) {
                if (map.isWall(x + dx * 32, y + dy * 32)) {
                    generateRandomDirection(map);
                } else {
                    if (new Random().nextInt(100) < 5) { 
                        generateRandomDirection(map);
                    }
                }
            }
            // Try to move in current direction
            if (!map.isWall(x + dx * speed, y + dy * speed)) {
                x += dx * speed;
                y += dy * speed;
            } else {
                // Stuck: snap to grid and try new direction
                if (dx != 0) {
                    int remainder = x % 32;
                    if (remainder > 16) x = x + (32 - remainder);
                    else if (remainder > 0) x = x - remainder;
                }
                if (dy != 0) {
                    int remainder = y % 32;
                    if (remainder > 16) y = y + (32 - remainder);
                    else if (remainder > 0) y = y - remainder;
                }
                generateRandomDirection(map);
            }
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
                return;
            }
            attempts++;
        }
        // If no valid direction found, reverse direction
        this.dx = -this.dx;
        this.dy = -this.dy;
    }
    public String getGhostType(){return ghostType;}
    
    public Boolean getIsFrighted(){return isFrighted;}
    
    public void setFrightened(boolean frightened, int duration) {
        this.isFrighted = frightened;
        this.frightenedDuration = duration;
    }
    
    public void updateFrightened() {
        if (isFrighted) {
            frightenedDuration--;
            if (frightenedDuration <= 0) {
                isFrighted = false;
                frightenedDuration = 0;
            }
        }
    }
    
    public int getFrightenedDuration() {
        return frightenedDuration;
    }
    
    public void respawnAtRandomLocation(short[][] grid) {
        java.util.Random rand = new java.util.Random();
        boolean positioned = false;
        while (!positioned) {
            int randomR = rand.nextInt(grid.length);
            int randomC = rand.nextInt(grid[0].length);
            if (grid[randomR][randomC] == 0) {
                this.x = randomC * 32;
                this.y = randomR * 32;
                this.dx = 1;
                this.dy = 0;
                positioned = true;
            }
        }
    }
}