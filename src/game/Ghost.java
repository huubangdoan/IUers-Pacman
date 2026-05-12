package game;
public class Ghost extends MoveSystem{
    private String ghostType;
    private boolean isFrighted = false; //con ma có trong trạng thái có thể ăn được hay không
    private int frightenedDuration = 0;
    private boolean stunned = false;
    private int stunTimer = 0;
    private boolean isFrozen = false;
    private int freezeTimer = 0;
    public Ghost(int x, int y, int speed, String ghostType){
        super(x,y, speed);
        this.ghostType=ghostType;
        this.dx = 1;
        this.dy = 0;
    }

    @Override
    public void move(Map map){
        if (isFrozen) {
            freezeTimer--;
        if (freezeTimer <= 0) {
            isFrozen = false;
        }
        return; 
    }
        if (stunned) {
            stunTimer--;
            if(stunTimer <= 0) {
                stunned = false;
            }
            return; 
        }
    PacMan player = map.getPlayer();
    if (x % 32 == 0 && y % 32 == 0) {
        calculateBestDirection(map, player);
            }
            if (!map.isWall(x + dx * speed, y + dy * speed)) {
                x += dx * speed;
                y += dy * speed;
            } else {
                calculateBestDirection(map, player);
            }
        }

    private void calculateBestDirection(Map map, PacMan player) {
            int[] dirsX = {0, 0, -1, 1}; // Lên, Xuống, Trái, Phải
            int[] dirsY = {-1, 1, 0, 0};
            
            double minDistance = Double.MAX_VALUE;
            int bestDx = dx;
            int bestDy = dy;
            
            java.util.List<Integer> validDirs = new java.util.ArrayList<>();
        
            // Tìm các hướng không phải là tường và không phải hướng ngược lại
            for (int i = 0; i < 4; i++) {
                int nextX = x + dirsX[i] * 32;
                int nextY = y + dirsY[i] * 32;
        
                if (!map.isWall(nextX, nextY)) {
                    // Cản Ma quay đầu 180 độ trừ khi vào đường cụt
                    if (!(dirsX[i] == -dx && dirsY[i] == -dy)) {
                        validDirs.add(i);
                    }
                }
            }
        
            if (validDirs.isEmpty()) {
                this.dx = -dx;
                this.dy = -dy;
                return;
            }
        
            java.util.Random rand = new java.util.Random();
            // 60% Ma sẽ đi thông minh hướng về Pacman
            boolean beSmart = rand.nextInt(100) < 30;
            
            if (beSmart && !isFrighted) {
                for (int dirIdx : validDirs) {
                    // Tính khoảng cách từ ô tiếp theo tới Pacman
                    double dist = Math.hypot(
                        (x + dirsX[dirIdx] * 32) - player.getX(),
                        (y + dirsY[dirIdx] * 32) - player.getY()
                    );
                    if (dist < minDistance) {
                        minDistance = dist;
                        bestDx = dirsX[dirIdx];
                        bestDy = dirsY[dirIdx];
                    }
                }
            } else {
                // 70% còn lại cho Ma đi ngẫu nhiên để game không quá khó
                int randomIdx = validDirs.get(rand.nextInt(validDirs.size()));
                bestDx = dirsX[randomIdx];
                bestDy = dirsY[randomIdx];
            }
        
            this.dx = bestDx;
            this.dy = bestDy;
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

    public void setFrozen(boolean status, int duration) {
    this.isFrozen = status;
    this.freezeTimer = duration;
    }

    public boolean isFrozen() {
        return isFrozen;
}
    public void setStunned(boolean status, int duration) {
        this.stunned = status;
        this.stunTimer = duration;
    }

    public void knockbackFrom(PacMan player) {
        int pushDistance = 32;
                    if (x < player.getX()) {
                        x -= pushDistance;
                    } else {
                        x += pushDistance;
                    }
                    if (y < player.getY()) {
                        y -= pushDistance;
                    } else {
                        y += pushDistance;
                    }
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
    public int getSpeed(){return speed;}

    
}