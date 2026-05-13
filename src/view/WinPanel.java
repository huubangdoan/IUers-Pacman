package view;
import controller.*;
import java.awt.*;
import javax.swing.*;
import utils.UIUtils;

public class WinPanel extends JPanel {
    public WinPanel(WinController wincontroller) {
        setLayout(null);

        ImageIcon originalNext = new ImageIcon("src/assets/Menu Graphics/map1.png");
        Image scaledNextImg = originalNext.getImage().getScaledInstance(210, 120, Image.SCALE_SMOOTH);
        ImageIcon nextIcon = new ImageIcon(scaledNextImg);
        JButton next = new JButton(nextIcon);
        next.setBounds(231, 148, 210, 120);
        UIUtils.makeButtonTransparent(next);
        UIUtils.setupZoomEffect(next, nextIcon, 210, 120);
        next.setActionCommand("Next chapter");
        next.addActionListener(wincontroller);

        ImageIcon originalMainmenu = new ImageIcon("src/assets/Menu Graphics/map2.png");
        Image scaledMainmenuImg = originalMainmenu.getImage().getScaledInstance(210, 120, Image.SCALE_SMOOTH);
        ImageIcon mainmenuIcon = new ImageIcon(scaledMainmenuImg);
        JButton mainmenu = new JButton(mainmenuIcon);
        mainmenu.setBounds(231, 233, 210, 120);
        UIUtils.makeButtonTransparent(mainmenu);
        UIUtils.setupZoomEffect(mainmenu, mainmenuIcon, 210, 120);
        mainmenu.setActionCommand("Main menu");
        mainmenu.addActionListener(wincontroller);

        ImageIcon originalBack = new ImageIcon("src/assets/Menu Graphics/back.png");
        Image scaledBackImg = originalBack.getImage().getScaledInstance(105, 60, Image.SCALE_SMOOTH);
        ImageIcon backIcon = new ImageIcon(scaledBackImg);
        JButton back = new JButton(backIcon);
        back.setBounds(25, 15, 105, 60);
        UIUtils.makeButtonTransparent(back);
        UIUtils.setupZoomEffect(back, backIcon, 105, 60);
        back.setActionCommand("Back");
        back.addActionListener(wincontroller);

        add(next);
        add(mainmenu);
        add(back);

        ImageIcon bgIcon = new ImageIcon("src/assets/Menu Graphics/winbgr.png");
        JLabel background = new JLabel(bgIcon);
        background.setBounds(0, 0, 672, 672);
        add(background);
        return;

    }
}