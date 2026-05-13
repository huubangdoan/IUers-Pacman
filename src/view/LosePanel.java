package view;
import controller.*;
import java.awt.*;
import javax.swing.*;
import utils.UIUtils;

public class LosePanel extends JPanel {
    public LosePanel(LoseController losecontroller) {
        setLayout(null);

        ImageIcon originalTryag = new ImageIcon("src/assets/Menu Graphics/map1.png");
        Image scaledTryagImg = originalTryag.getImage().getScaledInstance(210, 120, Image.SCALE_SMOOTH);
        ImageIcon tryagIcon = new ImageIcon(scaledTryagImg);
        JButton tryag = new JButton(tryagIcon);
        tryag.setBounds(231, 148, 210, 120);
        UIUtils.makeButtonTransparent(tryag);
        UIUtils.setupZoomEffect(tryag, tryagIcon, 210, 120);
        tryag.setActionCommand("Try again");
        tryag.addActionListener(losecontroller);

        ImageIcon originalMainmenu = new ImageIcon("src/assets/Menu Graphics/map2.png");
        Image scaledMainmenuImg = originalMainmenu.getImage().getScaledInstance(210, 120, Image.SCALE_SMOOTH);
        ImageIcon mainmenuIcon = new ImageIcon(scaledMainmenuImg);
        JButton mainmenu = new JButton(mainmenuIcon);
        mainmenu.setBounds(231, 233, 210, 120);
        UIUtils.makeButtonTransparent(mainmenu);
        UIUtils.setupZoomEffect(mainmenu, mainmenuIcon, 210, 120);
        mainmenu.setActionCommand("Main menu");
        mainmenu.addActionListener(losecontroller);

        ImageIcon originalBack = new ImageIcon("src/assets/Menu Graphics/back.png");
        Image scaledBackImg = originalBack.getImage().getScaledInstance(105, 60, Image.SCALE_SMOOTH);
        ImageIcon backIcon = new ImageIcon(scaledBackImg);
        JButton back = new JButton(backIcon);
        back.setBounds(25, 15, 105, 60);
        UIUtils.makeButtonTransparent(back);
        UIUtils.setupZoomEffect(back, backIcon, 105, 60);
        back.setActionCommand("Back");
        back.addActionListener(losecontroller);

        add(tryag);
        add(mainmenu);
        add(back);

        ImageIcon bgIcon = new ImageIcon("src/assets/Menu Graphics/losebgr.png");
        JLabel background = new JLabel(bgIcon);
        background.setBounds(0, 0, 672, 672);
        add(background);
        return;

    }
}