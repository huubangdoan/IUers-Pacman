package game;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class SnakeMap extends Map {
    private class BodyPart {
        Point point;
        Color color;

        BodyPart(Point p, Color c) {
            this.point = p;
            this.color = c;
        }
    }

    private LinkedList<BodyPart> snakeBody;
    private int snakeLength = 30; 
    private Random rand;
    private int lastDirection = -1;
    private Color currentBodyColor = new Color(255, 230, 0);

    public SnakeMap() {
        super();
        this.rand = new Random();
        this.snakeBody = new LinkedList<>();
        this.setDoubleBuffered(true);
        if (getGhosts() != null) getGhosts().clear(); 
        if (getPlayer() != null) getPlayer().setLives(1);     
        this.grid = SnakeData.GRID; 
        if (getCollectable() != null) getCollectable().clear();
        spawnOneFruit(); 
        spawnOneFruit(); 
    }

    @Override
    public void spawnRandomEvent() {
        // Chặn spam lightpoint
    }
    @Override
    public void spawnOneFruit() {
        int maxAttempts = 100;
        for (int i = 0; i < maxAttempts; i++) {
            int r = rand.nextInt(grid.length);
            int c = rand.nextInt(grid[0].length);
            if (grid[r][c] == 0) {
                int targetX = c * 32;
                int targetY = r * 32;
                boolean isOccupied = false;
                for (BodyPart bp : snakeBody) {
                    int dx = bp.point.x - targetX;
                    int dy = bp.point.y - targetY;
                    if ((dx * dx + dy * dy) < 576) { 
                        isOccupied = true;
                        break;
                    }
                }

                if (!isOccupied) {
                    addFruitAt(targetX, targetY);
                    return;
                }
            }
        }
    }

    private void addFruitAt(int x, int y) {
        int type = rand.nextInt(6);
        ArrayList<Collectable> items = getCollectable();
        switch (type) {
            case 0 -> items.add(new Apple(x, y, "Apple"));
            case 1 -> items.add(new Chilli(x, y, "Chilli"));
            case 2 -> items.add(new Durian(x, y, "Durian"));
            case 3 -> items.add(new Kiwi(x, y, "Kiwi"));
            case 4 -> items.add(new Watermelon(x, y, "Watermelon"));
            default -> items.add(new DragonFruit(x, y, "Dragon Fruit"));
        }
    }

    @Override
    public void update() {
        int nextDir = getPlayer().getDirection();
        if (lastDirection != -1) {
            boolean isOpposite = (lastDirection == 0 && nextDir == 2) || (lastDirection == 2 && nextDir == 0) || 
                                 (lastDirection == 1 && nextDir == 3) || (lastDirection == 3 && nextDir == 1);
            if (isOpposite) getPlayer().setNextDirection(lastDirection); 
        }

        int oldX = getPlayer().getX();
        int oldY = getPlayer().getY();
        
        getPlayer().move(this);
        getPlayer().updateAnimation();
        lastDirection = getPlayer().getDirection();
        if (getPlayer().getX() < 32 || getPlayer().getX() > getWidth() - 64 || 
            getPlayer().getY() < 32 || getPlayer().getY() > getHeight() - 64) {
            handlePlayerDeath(); 
            return;
        }
        if (getPlayer().getX() != oldX || getPlayer().getY() != oldY) {
            snakeBody.addFirst(new BodyPart(new Point(oldX, oldY), currentBodyColor));
            if (snakeBody.size() > snakeLength) {
                snakeBody.removeLast();
            }
        }

        checkSelfCollision();
        checkEntityCollisions();
        repaint();
    }

    private void checkSelfCollision() {
        int headX = getPlayer().getX();
        int headY = getPlayer().getY();
        int count = 0;
        for (BodyPart bp : snakeBody) {
            if (count < 15) { count++; continue; }
            int dx = headX - bp.point.x;
            int dy = headY - bp.point.y;
            if ((dx * dx + dy * dy) < 196) {
                handlePlayerDeath(); 
                break;
            }
            count++;
        }
    }

    @Override
    public void checkEntityCollisions() {
        ArrayList<Collectable> colls = getCollectable();
        int headX = getPlayer().getX();
        int headY = getPlayer().getY();
        
        for (int i = 0; i < colls.size(); i++) {
            Collectable f = colls.get(i);
            int dx = headX - f.getX();
            int dy = headY - f.getY();
            
            if ((dx * dx + dy * dy) < 256) {
                getPlayer().addScore(100);
                this.snakeLength += 10; 
                this.currentBodyColor = getColorFromFruit(f);
                colls.remove(i);
                spawnOneFruit();
                return;
            }
        } 
    }

    public Color getColorFromFruit(Collectable f) {
        if (f instanceof Apple) return new Color(255, 50, 50);      
        else if (f instanceof Chilli) return new Color(255, 120, 0); 
        else if (f instanceof Watermelon) return new Color(50, 255, 50); 
        else if (f instanceof DragonFruit) return new Color(255, 50, 255); 
        else if (f instanceof Kiwi) return new Color(160, 255, 50);  
        else return new Color(255, 230, 0); 
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
        if (snakeBody != null && snakeBody.size() > 1) {
            g2d.setStroke(new BasicStroke(32f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            
            BodyPart prev = null;
            int index = 0;
            for (BodyPart current : snakeBody) {
                if (index < 5) { 
                    prev = current; 
                    index++; 
                    continue; 
                }
                
                if (prev != null) {
                    g2d.setColor(current.color); 
                    g2d.drawLine(prev.point.x + 16, prev.point.y + 16, 
                                 current.point.x + 16, current.point.y + 16);
                }
                prev = current;
                index++;
            }
        }

        if (getPlayer() != null) {
            getRenderer().drawPlayer(g2d, getPlayer()); 
        }
    }

    public void setCurrentBodyColor(Color color){
        this.currentBodyColor = color;
    }
}