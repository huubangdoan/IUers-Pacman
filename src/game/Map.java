package game;

import gacha.SkinManager;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class Map extends JPanel implements ActionListener {
    // Các nhân viên chuyên môn được inject thông qua constructor
    private final GridManager gridManager;
    private final EntityManager entityManager;
    private final SpawnManager spawnManager;
    private final CollisionManager collisionManager;
    private final GameStateManager gameStateManager;
    
    private final Timer timer;
    private final GameRenderer renderer;
    private final ScoreManager scoreManager;
    private final Image backGroundImg, wallImg;
    private final long startTime;
    private GameStateListener gameStateListener;

    public static final int FRUIT_DURATION = 300;

    public Map(SkinManager skinManager, 
               GameRenderer renderer, 
               GameStateListener gameStateListener, 
               Image wallImg, 
               Image backGroundImg,
               GridManager gridManager,
               EntityManager entityManager,
               SpawnManager spawnManager,
               CollisionManager collisionManager,
               GameStateManager gameStateManager) {
        
        this.scoreManager = skinManager != null ? skinManager.getScoreManager() : new ScoreManager();
        this.renderer = renderer;
        this.gameStateListener = gameStateListener;
        this.wallImg = wallImg;
        this.backGroundImg = backGroundImg;
        this.startTime = System.currentTimeMillis();

        // Gán các Dependency thông qua Constructor Injection
        this.gridManager = gridManager;
        this.entityManager = entityManager;
        this.spawnManager = spawnManager;
        this.collisionManager = collisionManager;
        this.gameStateManager = gameStateManager;

        // Ủy thác việc khởi tạo trò chơi cho các Manager chuyên biệt
        this.spawnManager.spawnInitialCollectables(this.gridManager);
        this.entityManager.initEntities(this.gridManager);
        PacmanInput.setupInput(this, this.entityManager.getPlayer());
        setFocusable(true);
        setPreferredSize(new Dimension(672, 672));
        
        this.timer = new Timer(16, this);
        this.timer.start();
    }

    public void update() {
        // Luồng chạy mạch lạc, dễ đọc như một câu chuyện kể
        entityManager.updateEntities(this);
        collisionManager.checkAllCollisions(this, entityManager, spawnManager, gridManager);
        gameStateManager.checkGameStatus(entityManager.getPlayer(), spawnManager, gameStateListener, timer);
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        renderer.render((Graphics2D) g, this, wallImg, backGroundImg);
    }

    @Override
    public void actionPerformed(ActionEvent e) { 
        update(); 
    }
    public boolean isWall(int x, int y) { 
        return gridManager.isWall(x, y); 
    }
    
    public PacMan getPlayer()                      { return entityManager.getPlayer(); }
    public List<Ghost> getGhosts()                 { return entityManager.getGhosts(); }
    public ArrayList<Collectable> getCollectable() { return spawnManager.getCollectables(); }
    public short[][] getGrid()                     { return gridManager.getGrid(); }
    public void setGrid(short[][] grid)            { gridManager.setGrid(grid); }
    public ArrayList<Point> getSpecialTiles()      { return gridManager.getSpecialTiles(); }
    public GameRenderer getRenderer()              { return renderer; }
    public Image getWallImg()                      { return wallImg; }
    public Image getBackGroundImg()                { return backGroundImg; }
    public Timer getTimer()                        { return timer; }
    public ScoreManager getScoreManager()          { return scoreManager; }
    public int getElapsedSeconds() {
        return (int) ((System.currentTimeMillis() - startTime) / 1000);
    }
    public void setGameStateListener(GameStateListener listener) {
        this.gameStateListener = listener;
    }
}