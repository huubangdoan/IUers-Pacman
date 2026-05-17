package view;

import controller.*;
import game.*;
import gacha.SkinManager;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.*;
import utils.UIUtils;

public class MapPanel extends JPanel {

    private SkinManager skinManager;
    private MapController mapcontroller;
    private Map currentMap;
    private GameRenderer renderer;
    private short[][] grid;
    private Image wallImg;
    private Image backGroundImg;
    private  GameStateListener gameStateListener;

    public MapPanel(MapController mapcontroller, SkinManager skinManager, GameRenderer renderer, short[][] grid,  GameStateListener gameStateListener, Image wallImg, Image backGroundImg) {
        this.mapcontroller = mapcontroller;
        this.skinManager    = skinManager;
        this.renderer    = renderer;
        this.grid= grid;
        this.wallImg= wallImg;
        this.backGroundImg= backGroundImg;
        this.gameStateListener= gameStateListener;
        setLayout(null);
        ImageIcon originalBack = new ImageIcon("src/assets/Menu Graphics/back.png");
        Image scaledBackImg = originalBack.getImage().getScaledInstance(105, 60, Image.SCALE_SMOOTH);
        ImageIcon backIcon = new ImageIcon(scaledBackImg);
        JButton back = new JButton(backIcon);
        back.setBounds(25, 15, 105, 60);
        UIUtils.makeButtonTransparent(back);
        UIUtils.setupZoomEffect(back, backIcon, 105, 60);
        back.setActionCommand("Back");
        back.addActionListener(mapcontroller);
        add(back);
        setComponentZOrder(back, 0);
        ImageIcon bgIcon = new ImageIcon("src/assets/Menu Graphics/bgr.png");
        JLabel background = new JLabel(bgIcon);
        background.setBounds(0, 0, 672, 672);
        add(background);
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                reloadMap();
            }
        });
    }

    private void reloadMap() {
        if (currentMap != null) {
            currentMap.getTimer().stop();
            remove(currentMap);
        }
        currentMap = setMap(skinManager, renderer, grid);
        currentMap.setGameStateListener(gameStateListener);
        currentMap.setBounds(0, 0, 672, 672);
        add(currentMap);
        setComponentZOrder(currentMap, getComponentCount() - 1);
        revalidate();
        repaint();
        currentMap.requestFocusInWindow();
    }
    public Map setMap(SkinManager skinManager, GameRenderer renderer, short[][] grid){
        return new Map(skinManager, renderer, grid, gameStateListener, wallImg, backGroundImg);
    }
    public Map getMap(){return currentMap;}
    public Image getWallImg(){return wallImg;}
    public Image getBackGroundImg(){return backGroundImg;}
    public  GameStateListener getGameStateListener(){return  gameStateListener;}
}