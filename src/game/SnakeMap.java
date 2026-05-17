package game;
import gacha.*;
import java.awt.*;
import java.awt.geom.GeneralPath;
import java.util.ArrayList;
import java.util.LinkedList;

public class SnakeMap extends Map {

    private static class BodyPart {       
        int x, y;                        
        Color color;
        BodyPart(int x, int y, Color c) { this.x = x; this.y = y; this.color = c; }
    }

    private final LinkedList<BodyPart> snakeBody = new LinkedList<>();
    private int snakeLength = 30;
    private int lastDirection = -1;
    private Color currentBodyColor = new Color(255, 230, 0);

    private static final Color COLOR_APPLE       = new Color(255, 50,  50);
    private static final Color COLOR_CHILLI      = new Color(255, 120,  0);
    private static final Color COLOR_WATERMELON  = new Color( 50, 255, 50);
    private static final Color COLOR_DRAGONFRUIT = new Color(255,  50, 255);
    private static final Color COLOR_KIWI        = new Color(160, 255, 50);
    private static final Color COLOR_DEFAULT     = new Color(255, 230,  0);
    private static final BasicStroke SNAKE_STROKE =
        new BasicStroke(32f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
    private final GeneralPath snakePath = new GeneralPath();

    public SnakeMap(SkinManager skinManager, GameRenderer renderer, short[][] grid) {
        super(skinManager, renderer, grid);
        this.setDoubleBuffered(true);
        if (getGhosts() != null) getGhosts().clear();
        if (getPlayer() != null) getPlayer().setLives(1);
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
            int r = rand.nextInt(gridRows); 
            int c = rand.nextInt(gridCols);
            if (grid[r][c] == 0) {
                int tx = c << 5;             
                int ty = r << 5;
                if (!isBodyAt(tx, ty)) {
                    addFruitAt(tx, ty);
                    return;
                }
            }
        }
    }
    private boolean isBodyAt(int tx, int ty) {
        for (BodyPart bp : snakeBody) {
            int dx = bp.x - tx, dy = bp.y - ty;
            if (dx * dx + dy * dy < 576) return true;
        }
        return false;
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
        PacMan player = getPlayer();
        int nextDir = player.getDirection();
        if (lastDirection != -1) {
            boolean isOpposite = (lastDirection == 0 && nextDir == 2)
                              || (lastDirection == 2 && nextDir == 0)
                              || (lastDirection == 1 && nextDir == 3)
                              || (lastDirection == 3 && nextDir == 1);
            if (isOpposite) player.setNextDirection(lastDirection);
        }

        int oldX = player.getX();
        int oldY = player.getY();

        player.move(this);
        player.updateAnimation();
        lastDirection = player.getDirection();
        playerX = player.getX();
        playerY = player.getY();
        if (playerX < 32 || playerX > getWidth() - 64
         || playerY < 32 || playerY > getHeight() - 64) {
            handlePlayerDeath();
            return;
        }

        if (playerX != oldX || playerY != oldY) {
            // tái sử dụng BodyPart cuối thay vì tạo mới nếu body đã đầy
            if (snakeBody.size() >= snakeLength) {
                BodyPart recycled = snakeBody.removeLast();
                recycled.x     = oldX;
                recycled.y     = oldY;
                recycled.color = currentBodyColor;
                snakeBody.addFirst(recycled);
            } else {
                snakeBody.addFirst(new BodyPart(oldX, oldY, currentBodyColor));
            }
        }

        checkSelfCollision();
        checkEntityCollisions();
        repaint();
    }

    private void checkSelfCollision() {
        final int hx = playerX, hy = playerY;
        int count = 0;
        for (BodyPart bp : snakeBody) {
            if (count++ < 15) continue; 
            int dx = hx - bp.x, dy = hy - bp.y;
            if (dx * dx + dy * dy < 196) {
                handlePlayerDeath();
                return;
            }
        }
    }

    @Override
    public void checkEntityCollisions() {
        ArrayList<Collectable> colls = getCollectable();
        final int hx = playerX, hy = playerY;
        int size = colls.size();
        for (int i = 0; i < size; i++) {
            Collectable f = colls.get(i);
            int dx = hx - f.getX(), dy = hy - f.getY();
            if (dx * dx + dy * dy < 256) {
                getPlayer().addScore(100);
                snakeLength += 25;
                currentBodyColor = getColorFromFruit(f);
                colls.remove(i);
                spawnOneFruit();
                return;
            }
        }
    }

    public Color getColorFromFruit(Collectable f) {
        if (f instanceof Apple)       return COLOR_APPLE;
        if (f instanceof Chilli)      return COLOR_CHILLI;
        if (f instanceof Watermelon)  return COLOR_WATERMELON;
        if (f instanceof DragonFruit) return COLOR_DRAGONFRUIT;
        if (f instanceof Kiwi)        return COLOR_KIWI;
        return COLOR_DEFAULT;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        getRenderer().render((Graphics2D) g, this, GameAssets.wall3Img);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,    RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL,  RenderingHints.VALUE_STROKE_PURE);
        int bodySize = snakeBody.size();
        if (bodySize > 1) {
            g2d.setStroke(SNAKE_STROKE);
            snakePath.reset();
            Color lastColor = null;
            BodyPart prev = null;
            int index = 0;

            for (BodyPart current : snakeBody) {
                if (index++ < 5) { prev = current; continue; }
                if (prev == null) { prev = current; continue; }

                Color c = current.color;
                if (!c.equals(lastColor)) {
                    if (lastColor != null && snakePath.getCurrentPoint() != null) {
                        g2d.setColor(lastColor);
                        g2d.draw(snakePath);
                    }
                    snakePath.reset();
                    snakePath.moveTo(prev.x + 16, prev.y + 16);
                    lastColor = c;
                }
                snakePath.lineTo(current.x + 16, current.y + 16);
                prev = current;
            }
            if (lastColor != null && snakePath.getCurrentPoint() != null) {
                g2d.setColor(lastColor);
                g2d.draw(snakePath);
            }
        }

        if (getPlayer() != null) {
            getRenderer().drawPlayer(g2d, getPlayer());
        }
    }

    public void setCurrentBodyColor(Color color) { this.currentBodyColor = color; }
}