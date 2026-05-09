import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import javax.swing.*;

import javax.imageio.ImageIO;

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
        getGhosts().clear(); 
        getPlayer().setLives(1); 
        this.grid = SnakeData.GRID; 
        getCollectable().clear();
        spawnOneFruit(); 
        spawnOneFruit(); 
    }

    @Override
    public void spawnRandomEvent() {
        // Chặn spam lightpoint
    }

    @Override
    public void spawnOneFruit() {
        short[][] currentGrid = getGrid();
        ArrayList<Point> emptySpots = new ArrayList<>();
        ArrayList<Collectable> existingItems = getCollectable(); 
        for (int r = 0; r < currentGrid.length; r++) {
            for (int c = 0; c < currentGrid[0].length; c++) {
                if (currentGrid[r][c] == 0) {
                    Point spot = new Point(c * 32, r * 32);
                    boolean isOccupied = false;
                    for (BodyPart bp : snakeBody) {
                        if (Math.hypot(bp.point.x - spot.x, bp.point.y - spot.y) < 24) {
                            isOccupied = true; break;
                        }
                    }
                    if (!isOccupied) {
                        for (Collectable item : existingItems) {
                            if (Math.hypot(item.getX() - spot.x, item.getY() - spot.y) < 32) { 
                                isOccupied = true; break;
                            }
                        }
                    }
                    if (!isOccupied) emptySpots.add(spot);
                }
            }
        }
    
        if (!emptySpots.isEmpty()) {
            Point target = emptySpots.get(rand.nextInt(emptySpots.size()));
            int type = rand.nextInt(6);
            int x = target.x; int y = target.y;
            switch (type) {
                case 0 -> getCollectable().add(new Apple(x, y, "Apple"));
                case 1 -> getCollectable().add(new Chilli(x, y, "Chilli"));
                case 2 -> getCollectable().add(new Durian(x, y, "Durian"));
                case 3 -> getCollectable().add(new Kiwi(x, y, "Kiwi"));
                case 4 -> getCollectable().add(new Watermelon(x, y, "Watermelon"));
                default -> getCollectable().add(new DragonFruit(x, y, "Dragon Fruit"));
            }
        }
    }

    @Override
    public void update() {
        int nextDir = getPlayer().getDirection();
        if (lastDirection != -1) {
            boolean isOpposite = (lastDirection == 0 && nextDir == 2) || (lastDirection == 2 && nextDir == 0) || 
                                 (lastDirection == 1 && nextDir == 3) || (lastDirection == 3 && nextDir == 1);
            if (isOpposite) getPlayer().setDirection(lastDirection); 
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
        Point head = new Point(getPlayer().getX(), getPlayer().getY());
        for (int i = 15; i < snakeBody.size(); i++) {
            Point bodyPoint = snakeBody.get(i).point;
            if (Math.hypot(head.x - bodyPoint.x, head.y - bodyPoint.y) < 14) {
                handlePlayerDeath(); 
                break;
            }
        }
    }
    @Override
    public void checkEntityCollisions() {
        ArrayList<Collectable> colls = getCollectable();
        for (int i = 0; i < colls.size(); i++) {
            Collectable f = colls.get(i);
            if (Math.hypot(getPlayer().getX() - f.getX(), getPlayer().getY() - f.getY()) < 16) {
                getPlayer().addScore(100);
                this.snakeLength += 25; 
                currentBodyColor = getColorFromFruit(f);
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
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if (snakeBody != null && snakeBody.size() > 1) {
            g2d.setStroke(new BasicStroke(32f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            for (int i = 5; i < snakeBody.size() - 1; i++) {
                BodyPart p1 = snakeBody.get(i);
                BodyPart p2 = snakeBody.get(i + 1);
                g2d.setColor(p1.color); 
                g2d.drawLine(p1.point.x + 16, p1.point.y + 16, p2.point.x + 16, p2.point.y + 16);
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