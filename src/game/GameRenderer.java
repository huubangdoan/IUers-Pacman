package game;
import java.awt.*;
public class GameRenderer {
    private GameAssets assets;

    public GameRenderer(GameAssets assets) {
        this.assets = assets;
    }

    public void render(Graphics2D g2d, Map map) {
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        drawBackground(g2d, map);
        drawWalls(g2d, map.getGrid());
        drawCollectables(g2d, map.getCollectable());
        drawGhosts(g2d, map.getGhosts());
        drawPlayer(g2d, map.getPlayer());
        drawUI(g2d, map);
        drawActiveSkills(g2d, map); 
    }

    private void drawBackground(Graphics2D g2d, Map map) {
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, map.getWidth(), map.getHeight());
    }

    private void drawWalls(Graphics2D g2d, short[][] grid) {
        g2d.setColor(new Color(0, 50, 200));
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                if (grid[r][c] == 1) {
                    g2d.fillRoundRect(c * 32, r * 32, 30, 30, 8, 8);
                }
            }
        }
    }

    private void drawCollectables(Graphics2D g2d, java.util.List<Collectable> collectables) {
        for (Collectable f : collectables) {
            Image img = null;
            if (f instanceof LightPoint) img = assets.dotImg;
            else if (f instanceof Apple) img = assets.appleImg;
            else if (f instanceof Durian) img = assets.durianImg;
            else if (f instanceof Watermelon) img = assets.watermelonImg;
            else if (f instanceof Kiwi) img = assets.kiwiImg;
            else if (f instanceof DragonFruit) img = assets.dragonFruitImg;
            else if (f instanceof Chilli) img = assets.chilliImg;
            if (img != null) {
                g2d.drawImage(img, f.getX(), f.getY(), 32, 32, null);
            }
        }
    }

    private void drawGhosts(Graphics2D g2d, java.util.List<Ghost> ghosts) {
        for (Ghost ghost : ghosts) {
            Image gImg = ghost.getIsFrighted() ? assets.frightenedImg : switch(ghost.getGhostType()) {
                case "blinky" -> assets.blinkyImg;
                case "pinky" -> assets.pinkyImg;
                case "inky" -> assets.inkyImg;
                default -> assets.clydeImg;
            };
            // Hiệu ứng đóng băng (Watermelon)
            if (ghost.isFrozen()) {
                g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.6f));
                g2d.drawImage(gImg, ghost.getX(), ghost.getY(), 32, 32, null);
                g2d.setColor(new Color(100, 200, 255, 100)); // Lớp băng xanh mờ
                g2d.fillRect(ghost.getX(), ghost.getY(), 32, 32);
                g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
            } else {
                g2d.drawImage(gImg, ghost.getX(), ghost.getY(), 32, 32, null);
            }
        }
    }

    public void drawPlayer(Graphics2D g2d, PacMan player) {
        Image[] frames = switch(player.getDirection()) {
            case 3 -> assets.pacmanLeft;
            case 2 -> assets.pacmanDown;
            case 0 -> assets.pacmanUp;
            default -> assets.pacmanRight;
        };
        if (player.isDisguised()){
            g2d.drawImage(assets.frightenedImg, player.getX(), player.getY(), 32, 32, null);
        }else{
            g2d.drawImage(frames[player.getAnimIndex()], player.getX(), player.getY(), 32, 32, null);
        }
    }

    private void drawUI(Graphics2D g2d, Map map) {
        PacMan player = map.getPlayer();
        ScoreManager sm = map.getScoreManager();
        int totalSecs = map.getElapsedSeconds();
        g2d.setFont(new Font("Consolas", Font.BOLD, 14));
        drawShadowText(g2d, "SCORE:" + String.format("%04d", player.getScore()), 10, 25, Color.YELLOW);
        drawShadowText(g2d, "LIVES:", 120, 25, Color.WHITE);
        for (int i = 0; i < player.getLives(); i++) {
            g2d.setColor(new Color(255, 0, 0, 220));
            g2d.fillOval(170 + (i * 15), 14, 11, 11);
        }
        String timeStr = String.format("TIME:%02d:%02d", totalSecs / 60, totalSecs % 60);
        drawShadowText(g2d, timeStr, 260, 25, Color.GREEN);
        drawShadowText(g2d, "BEST:" + String.format("%04d", sm.getHighscore()), 410, 25, Color.CYAN);
        drawShadowText(g2d, "TOTAL:" + String.format("%06d", sm.getCumulativeScore()), 535, 25, Color.MAGENTA);
    }
    public void drawActiveSkills(Graphics2D g2d, Map map) {
        PacMan player = map.getPlayer();
        int px = player.getX();
        int py = player.getY();
        // 1. Hiệu ứng Sầu riêng (Durian)
        if (player.hasThorns()) {
            g2d.setColor(new Color(255, 255, 0, 80)); // Màu vàng
            g2d.setStroke(new BasicStroke(2, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 0, new float[]{5}, 0));
            g2d.drawOval(px - 5, py - 5, 42, 42);
            drawSkillText(g2d, "THORNS ACTIVE", px, py - 20);
        }
        if (player.isDragonMode()) {
            g2d.setColor(new Color(255, 0, 255, 100)); // tím
            g2d.setStroke(new BasicStroke(3));
            g2d.drawOval(px - 8, py - 8, 48, 48);
            drawSkillText(g2d, "DRAGON MODE", px, py - 35);
        }
    }

    //vẽ chữ lên đầu con pacman
    private void drawSkillText(Graphics2D g2d, String text, int x, int y) {
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.BOLD, 10));
        g2d.drawString(text, x - 5, y);
    }
    private void drawShadowText(Graphics2D g2d, String text, int x, int y, Color mainColor) {
        g2d.setColor(Color.BLACK);
        g2d.drawString(text, x + 1, y + 1); 
        g2d.setColor(mainColor);
        g2d.drawString(text, x, y); 
    }
}