package view;
import controller.*;
import java.awt.*;
import javax.swing.*;
import utils.UIUtils;

public class MainMenuPanel extends JPanel {
    public MainMenuPanel(MainMenuController controller) {
        setLayout(null);
        
        ImageIcon originalStart = new ImageIcon("src/assets/Menu Graphics/start.png");
        Image scaledStartImg = originalStart.getImage().getScaledInstance(210, 120, Image.SCALE_SMOOTH);
        ImageIcon startIcon = new ImageIcon(scaledStartImg);
        JButton start = new JButton(startIcon);
        start.setBounds(231, 190, 210, 120);
        UIUtils.makeButtonTransparent(start);
        UIUtils.setupZoomEffect(start, startIcon, 210, 120);
        start.setActionCommand("Start");
        start.addActionListener(controller);

        ImageIcon originalGacha = new ImageIcon("src/assets/Menu Graphics/gacha.png");
        Image scaledGachaImg = originalGacha.getImage().getScaledInstance(210, 120, Image.SCALE_SMOOTH);
        ImageIcon gachaIcon = new ImageIcon(scaledGachaImg);
        JButton gacha = new JButton(gachaIcon);
        gacha.setBounds(231, 275, 210, 120);
        UIUtils.makeButtonTransparent(gacha);
        UIUtils.setupZoomEffect(gacha, gachaIcon, 210, 120);
        gacha.setActionCommand("Gacha");
        gacha.addActionListener(controller);
        
        ImageIcon originalSkin = new ImageIcon("src/assets/Menu Graphics/skin.png");
        Image scaledSkinImg = originalSkin.getImage().getScaledInstance(210, 120, Image.SCALE_SMOOTH);
        ImageIcon skinIcon = new ImageIcon(scaledSkinImg);
        JButton skin = new JButton(skinIcon);
        skin.setBounds(231, 360, 210, 120);
        UIUtils.makeButtonTransparent(skin);
        UIUtils.setupZoomEffect(skin, skinIcon, 210, 120);
        skin.setActionCommand("Skin");
        skin.addActionListener(controller);
        
        ImageIcon originalQuit = new ImageIcon("src/assets/Menu Graphics/quit.png");
        Image scaledQuitImg = originalQuit.getImage().getScaledInstance(210, 120, Image.SCALE_SMOOTH);
        ImageIcon quitIcon = new ImageIcon(scaledQuitImg);
        JButton quit = new JButton(quitIcon);
        quit.setBounds(231, 445, 210, 120);
        UIUtils.makeButtonTransparent(quit);
        UIUtils.setupZoomEffect(quit, quitIcon, 210, 120);
        quit.setActionCommand("Quit");
        quit.addActionListener(controller);

        add(start);
        add(gacha);
        add(skin);
        add(quit);

        ImageIcon bgIcon = new ImageIcon("src/assets/Menu Graphics/bgr1.png");
        JLabel background = new JLabel(bgIcon);
        background.setBounds(0, 0, 672, 672);
        add(background);
        return;

    }

}
