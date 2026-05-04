package view;
import controller.*;
import utils.UIUtils;
import javax.swing.*;
import java.awt.*;

public class SkinMenuPanel extends JPanel {
    public SkinMenuPanel(SkinMenuController skincontroller) {
        setLayout(null);

        ImageIcon originalStart = new ImageIcon("assets/Menu Graphics/start.png");
        Image scaledStartImg = originalStart.getImage().getScaledInstance(210, 120, Image.SCALE_SMOOTH);
        ImageIcon startIcon = new ImageIcon(scaledStartImg);
        JButton start = new JButton(startIcon);
        start.setBounds(231, 190, 210, 120);
        UIUtils.makeButtonTransparent(start);
        UIUtils.setupZoomEffect(start, startIcon, 210, 120);
        start.setActionCommand("Start");
        start.addActionListener(skincontroller);

        ImageIcon originalBack = new ImageIcon("assets/Menu Graphics/back.png");
        Image scaledBackImg = originalBack.getImage().getScaledInstance(105, 60, Image.SCALE_SMOOTH);
        ImageIcon backIcon = new ImageIcon(scaledBackImg);
        JButton back = new JButton(backIcon);
        back.setBounds(25, 15, 105, 60);
        UIUtils.makeButtonTransparent(back);
        UIUtils.setupZoomEffect(back, backIcon, 105, 60);
        back.setActionCommand("Back");
        back.addActionListener(skincontroller);

        add(start);
        add(back);

        ImageIcon bgIcon = new ImageIcon("assets/Menu Graphics/bgr.png");
        JLabel background = new JLabel(bgIcon);
        background.setBounds(0, 0, 672, 672);
        add(background);
        return;
    }
}