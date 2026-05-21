package view;
import controller.*;
import java.awt.*;
import javax.swing.*;
import utils.UIUtils;

public class LosePanel extends JPanel {
    public LosePanel(LoseController losecontroller) {
        setLayout(null);

        ImageIcon originalTryag = new ImageIcon("src/assets/Menu Graphics/tryagain.png");
        Image scaledTryagImg = originalTryag.getImage().getScaledInstance(210, 120, Image.SCALE_SMOOTH);
        ImageIcon tryagIcon = new ImageIcon(scaledTryagImg);
        JButton tryag = new JButton(tryagIcon);
        tryag.setBounds(231, 250, 210, 120);
        UIUtils.makeButtonTransparent(tryag);
        UIUtils.setupZoomEffect(tryag, tryagIcon, 210, 120);
        tryag.setActionCommand("Try again");
        tryag.addActionListener(losecontroller);

        ImageIcon originalMainmenu = new ImageIcon("src/assets/Menu Graphics/mainmenu.png");
        Image scaledMainmenuImg = originalMainmenu.getImage().getScaledInstance(210, 120, Image.SCALE_SMOOTH);
        ImageIcon mainmenuIcon = new ImageIcon(scaledMainmenuImg);
        JButton mainmenu = new JButton(mainmenuIcon);
        mainmenu.setBounds(231, 340, 210, 120);
        UIUtils.makeButtonTransparent(mainmenu);
        UIUtils.setupZoomEffect(mainmenu, mainmenuIcon, 210, 120);
        mainmenu.setActionCommand("Main menu");
        mainmenu.addActionListener(losecontroller);

        add(tryag);
        add(mainmenu);
        
        ImageIcon bgIcon = new ImageIcon("src/assets/Menu Graphics/losebgr.png");
        JLabel background = new JLabel(bgIcon);
        background.setBounds(0, 0, 672, 672);
        add(background);
        return;

    }
}