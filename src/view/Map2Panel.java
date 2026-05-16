package view;
import controller.Map2Controller;
import gacha.SkinManager;
import game.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.*;
import utils.UIUtils;

public class Map2Panel extends JPanel {

    private SkinManager skinManager;
    private Map2Controller map2controller;
    private Map currentMap;
    private GameRenderer renderer;
    private short[][] grid;

    public Map2Panel(Map2Controller map2controller, SkinManager skinManager, GameRenderer renderer, short[][] grid) {
        this.map2controller = map2controller;
        this.skinManager    = skinManager;
        this.renderer=renderer;
        this.grid=grid;
        setLayout(null);
        ImageIcon originalBack = new ImageIcon("src/assets/Menu Graphics/back.png");
        Image scaledBackImg = originalBack.getImage().getScaledInstance(105, 60, Image.SCALE_SMOOTH);
        ImageIcon backIcon = new ImageIcon(scaledBackImg);
        JButton back = new JButton(backIcon);
        back.setBounds(25, 15, 105, 60);
        UIUtils.makeButtonTransparent(back);
        UIUtils.setupZoomEffect(back, backIcon, 105, 60);
        back.setActionCommand("Back");
        back.addActionListener(map2controller);
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
        currentMap = new game.SnakeMap(skinManager, renderer, grid);
        currentMap.setBounds(0, 0, 672, 672);
        add(currentMap);
        setComponentZOrder(currentMap, getComponentCount() - 1);
        revalidate();
        repaint();
        currentMap.requestFocusInWindow();
    }
}