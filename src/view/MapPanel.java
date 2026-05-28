package view;

import controller.*;
import gacha.SkinManager;
import game.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.*;
import utils.SoundManager;

public class MapPanel extends JPanel {

    private SkinManager skinManager;
    private MapController mapcontroller;
    private Map currentMap;
    private GameRenderer renderer;
    private Image wallImg;
    private Image backGroundImg;
    private  GameStateListener gameStateListener;
    private final GridManager gridManager;
    private final EntityManager entityManager;
    private final SpawnManager spawnManager;
    private final CollisionManager collisionManager;
    private final GameStateManager gameStateManager;
    private String musicPath;

    public MapPanel(MapController mapcontroller, 
        SkinManager skinManager, 
        GameRenderer renderer, 
        GameStateListener gameStateListener, 
        Image wallImg, 
        Image backGroundImg,
        GridManager gridManager,
        EntityManager entityManager,
        SpawnManager spawnManager,
        CollisionManager collisionManager,
        GameStateManager gameStateManager,
        String MusicPath) {
        this.mapcontroller = mapcontroller;
        this.skinManager    = skinManager;
        this.renderer = renderer;
        this.gameStateListener = gameStateListener;
        this.wallImg = wallImg;
        this.backGroundImg = backGroundImg;
        this.gridManager = gridManager;
        this.entityManager = entityManager;
        this.spawnManager = spawnManager;
        this.collisionManager = collisionManager;
        this.gameStateManager = gameStateManager;
        this.musicPath=MusicPath;
        setLayout(null);
        ImageIcon bgIcon = new ImageIcon("src/assets/Menu Graphics/bgr.png");
        JLabel background = new JLabel(bgIcon);
        background.setBounds(0, 0, 672, 672);
        add(background);
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                SoundManager.playBGM(musicPath);
                reloadMap();
            }
        });
    }

    private void reloadMap() {
        if (currentMap != null) {
            currentMap.getTimer().stop();
            remove(currentMap);
        }
        currentMap = setMap(skinManager,
            renderer,
            gameStateListener,
            wallImg,
            backGroundImg,
            gridManager,
            entityManager,
            spawnManager,
            collisionManager,
            gameStateManager);
        currentMap.setGameStateListener(gameStateListener);
        //currentMap.getCollectable().clear(); 
        currentMap.setBounds(0, 0, 672, 672);
        add(currentMap);
        setComponentZOrder(currentMap, getComponentCount() - 1);
        revalidate();
        repaint();
        currentMap.requestFocusInWindow();
    }
    public Map setMap(SkinManager skinManager, 
        GameRenderer renderer, 
        GameStateListener gameStateListener, 
        Image wallImg, 
        Image backGroundImg,
        GridManager gridManager,
        EntityManager entityManager,
        SpawnManager spawnManager,
        CollisionManager collisionManager,
        GameStateManager gameStateManager){
        return new Map(skinManager,
            renderer,
            gameStateListener,
            wallImg,
            backGroundImg,
            gridManager,
            entityManager,
            spawnManager,
            collisionManager,
            gameStateManager);
    }
    public Map getMap(){return currentMap;}
    public Image getWallImg(){return wallImg;}
    public Image getBackGroundImg(){return backGroundImg;}
    public  GameStateListener getGameStateListener(){return  gameStateListener;}
}