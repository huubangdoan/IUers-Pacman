 
public class PacMan {
    private int x, y; 
    private int direction = 1; //hướng hiện tại
    private int nextDirection = 1; //hướng người chơi input
    private int speed;
    private int score = 0;
    private int lives = 3;
    private boolean hasThorns = false;
    private boolean hasPowerup = false;
    private int powerupDuration = 0;
<<<<<<< HEAD
=======
    private boolean dragonMode = false;
    private int dragonTimer = 0;
>>>>>>> cb98a270e84ab74c6db74044a09d4e9bf51f5faf
    private int animTick = 0;
    private int animIndex = 0;
    private boolean moving = false;
    private boolean wasStuck = false; // Track if we were stuck last frame
    private final int SIZE = 28;
    private final int TILE_SIZE = 32;

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
                return 1; // phải
            case 3:
                return -1; // trái
            default:
                return  0;
        }
    }
    
    private int getDy(int dir) {
        switch(dir) {
            case 2:
                return 1; // xuống
            case 0:
                return -1; // lên
            default:
                return 0;
        }
    }
 public void move(Map map){ 
            if (nextDirection == (direction + 2) % 4) {
                direction = nextDirection;
            } else if (x % 32 == 0 && y % 32 == 0) {
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
                 moving = true;
                 wasStuck = false;
            } else {
                 moving = false;
                 if (!wasStuck) {
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
                     wasStuck = true;
                 }
            }
        }
        
    public void updateAnimation() {
        if (moving) {
            animTick++;
            if (animTick >= 6) {
                animTick = 0;
                animIndex = (animIndex + 1) % 3;
            }
        } else {
            animTick = 0;
            animIndex = 0;
        }
    }

    public int getAnimIndex() {
        return animIndex;
    }

    public boolean isMoving() {
        return moving;
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

    public int getScore() {
        return score;
    }

    public int getLives() {
        return lives;
    }

    public void loseLife() {
        lives--;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getDirection() {
        return direction;
    }

    public void activatePowerup(int duration) {
        this.hasPowerup = true;
        this.powerupDuration = duration;
    }

    public boolean hasPowerup() {
        return hasPowerup;
    }

    public int getPowerupDuration() {
        return powerupDuration;
    }

    public void updatePowerup() {
        if (hasPowerup) {
            powerupDuration--;
            if (powerupDuration <= 0) {
                hasPowerup = false;
                powerupDuration = 0;
            }
        }
    }
<<<<<<< HEAD
=======

    public void setDragonMode(boolean value){
        this.dragonMode = value;
    }

    public boolean isDragonMode() {
        return dragonMode;
    }

    public void setDragonTimer(int time) {
        this.dragonTimer = time ;
    }

    public void updateDragon() {
        if (dragonMode) {
            dragonTimer --;
            if (dragonTimer <= 0) {
                dragonMode = false;
                dragonTimer = 0;
            }
        }
    }
>>>>>>> cb98a270e84ab74c6db74044a09d4e9bf51f5faf
}

