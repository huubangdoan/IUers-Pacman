import java.util.Timer;
import java.util.TimerTask;


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
    private boolean dragonMode = false;
    private int dragonTimer = 0;
    private int animTick = 0;
    private int animIndex = 0;
    private boolean moving = false;
    private boolean wasStuck = false; // Track if we were stuck last frame
    private final int SIZE = 28;
    private boolean isDisguised = false;
    private long disguiseEndTime = 0;
    private boolean canShoot = false;
    private int seedAmmo = 0;
    private boolean chilliMode = false;
    private boolean hasChilli = false;
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
    public void move(Map map) {
        // 1. Kiểm tra thời gian hiệu lực của cải trang (Kiwi)
        if (isDisguised) {
            if (System.currentTimeMillis() > disguiseEndTime) {
                isDisguised = false;
            }
        }
    
        // 2. Chỉ thay đổi hướng hoặc xử lý di chuyển khi PacMan nằm gọn trong 1 ô (ô 32x32)
        if (x % 32 == 0 && y % 32 == 0) {
            // Quay đầu 180 độ ngay lập tức
            if (nextDirection == (direction + 2) % 4) {
                direction = nextDirection;
            } else {
                // Kiểm tra xem hướng mới (nextDirection) có đi được không
                int ndx = getDx(nextDirection);
                int ndy = getDy(nextDirection);
                if (!map.isWall(x + ndx * speed, y + ndy * speed)) {
                    direction = nextDirection;
                }
            }
        }
    
        // 3. Thực hiện di chuyển theo hướng hiện tại
        int dx = getDx(direction);
        int dy = getDy(direction);
    
        if (!map.isWall(x + dx * speed, y + dy * speed)) {
            x += dx * speed;
            y += dy * speed;
            moving = true;
            wasStuck = false;
        } else {
            // Xử lý khi đâm vào tường (Alignment - căn chỉnh vào ô)
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
        } 
        else {
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
//watermelon
    private boolean hasWatermelon = false;

public void setHasWatermelon(boolean status) {
    this.hasWatermelon = status;
}

public boolean hasWatermelon() {
    return hasWatermelon;
}

//kiwi
    public void activateKiwiDisguise(){
        this.isDisguised = true;
        this.disguiseEndTime = System.currentTimeMillis() + 15000;
    }
    public boolean isDisguised(){
        return this.isDisguised;
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
    public boolean getChilliMode(){
        return chilliMode;
    }
    
    public void setChilliMode(boolean chilliMode){
        this.chilliMode = chilliMode;

    }
    public void activateChilliPower(long duration) {
        this.setChilliMode(true);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                setChilliMode(false);

            }
        }, duration);
    }
    public boolean hasChilli() {
        return hasChilli;
    }
    // Lấy tốc độ hiện tại
    public int getSpeed() {
        return speed;
    }

    // Thay đổi tốc độ (dùng khi ăn ớt hoặc khi hết hiệu ứng)
    public void setSpeed(int newSpeed) {
        this.speed = newSpeed;
    }

    public void update() {
        // x += speed; (tùy vào hướng di chuyển)
    }



    



}

