package view;

import controller.Map1Controller;
import gacha.SkinManager;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.*;
import utils.UIUtils;

public class Map1Panel extends JPanel {

    private SkinManager skinManager;
    private Map1Controller map1controller;
    private game.Map currentMap;

    public Map1Panel(Map1Controller map1controller, SkinManager skinManager) {
        this.map1controller = map1controller;
        this.skinManager    = skinManager;
        setLayout(null);
        ImageIcon originalBack = new ImageIcon("src/assets/Menu Graphics/back.png");
        Image scaledBackImg = originalBack.getImage().getScaledInstance(105, 60, Image.SCALE_SMOOTH);
        ImageIcon backIcon = new ImageIcon(scaledBackImg);
        JButton back = new JButton(backIcon);
        back.setBounds(25, 15, 105, 60);
        UIUtils.makeButtonTransparent(back);
        UIUtils.setupZoomEffect(back, backIcon, 105, 60);
        back.setActionCommand("Back");
        back.addActionListener(map1controller);
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
        game.TrackableMap trackableMap = new game.TrackableMap(skinManager);
        trackableMap.setGameStateListener(new game.GameStateListener() {
            @Override
            public void onGameOver(int finalScore) {
                map1controller.showLoseScreen(); 
            }

            @Override
            public void onGameWon(int finalScore) {
                map1controller.showWinScreen(); 
            }
        });
        
        currentMap = trackableMap;
        currentMap.setBounds(0, 0, 672, 672);
        add(currentMap);
        setComponentZOrder(currentMap, getComponentCount() - 1);
        revalidate();
        repaint();
        currentMap.requestFocusInWindow();
    }
}