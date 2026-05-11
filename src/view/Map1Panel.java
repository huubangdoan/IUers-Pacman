package view;
import game.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import controller.*;
import utils.UIUtils;
import javax.swing.*;
import java.awt.*;

public class Map1Panel extends JPanel {
    public Map1Panel(Map1Controller map1controller) {
        setLayout(null);

        game.Map gameMap1 = new game.Map();
        gameMap1.setBounds(0, 0, 672, 672);

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
        add(gameMap1);

        setComponentZOrder(back, 0);
        setComponentZOrder(gameMap1, 1);

        this.addComponentListener(new ComponentAdapter(){
            @Override
            public void componentShown(ComponentEvent e){
                gameMap1.requestFocusInWindow();
            
            }
        });

        ImageIcon bgIcon = new ImageIcon("src/assets/Menu Graphics/bgr.png");
        JLabel background = new JLabel(bgIcon);
        background.setBounds(0, 0, 672, 672);
        add(background);
        return;
    }
}