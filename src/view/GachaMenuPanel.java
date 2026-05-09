package view;
import controller.*;
import utils.UIUtils;
import javax.swing.*;
import java.awt.*;

public class GachaMenuPanel extends JPanel {
    public GachaMenuPanel(GachaMenuController gachacontroller) {
        setLayout(null);

        ImageIcon original1Roll = new ImageIcon("src/assets/Menu Graphics/roll1.png");
        Image scaled1RollImg = original1Roll.getImage().getScaledInstance(210, 120,Image.SCALE_SMOOTH);
        ImageIcon roll1Icon = new ImageIcon(scaled1RollImg);
        JButton roll1 = new JButton(roll1Icon);
        roll1.setBounds(390, 195, 210, 120);
        UIUtils.makeButtonTransparent(roll1);
        UIUtils.setupZoomEffect(roll1, roll1Icon, 210, 120);
        roll1.setActionCommand("1RRoll");
        roll1.addActionListener(gachacontroller);

        ImageIcon original10Rolls = new ImageIcon("src/assets/Menu Graphics/rolls10.png");
        Image scaled10RollsImg = original10Rolls.getImage().getScaledInstance(210, 120,Image.SCALE_SMOOTH);
        ImageIcon rolls10Icon = new ImageIcon(scaled10RollsImg);
        JButton rolls10 = new JButton(rolls10Icon);
        rolls10.setBounds(390, 295, 210, 120);
        UIUtils.makeButtonTransparent(rolls10);
        UIUtils.setupZoomEffect(rolls10, rolls10Icon, 210, 120);
        rolls10.setActionCommand("10Rolls");
        rolls10.addActionListener(gachacontroller);

        ImageIcon original100Rolls = new ImageIcon("src/assets/Menu Graphics/rolls100.png");
        Image scaled100RollsImg = original100Rolls.getImage().getScaledInstance(210, 120,Image.SCALE_SMOOTH);
        ImageIcon rolls100Icon = new ImageIcon(scaled100RollsImg);
        JButton rolls100 = new JButton(rolls100Icon);
        rolls100.setBounds(390, 395, 210, 120);
        UIUtils.makeButtonTransparent(rolls100);
        UIUtils.setupZoomEffect(rolls100, rolls100Icon, 210, 120);
        rolls100.setActionCommand("100Rolls");
        rolls100.addActionListener(gachacontroller);

        ImageIcon originalBack = new ImageIcon("src/assets/Menu Graphics/back.png");
        Image scaledBackImg = originalBack.getImage().getScaledInstance(105, 60, Image.SCALE_SMOOTH);
        ImageIcon backIcon = new ImageIcon(scaledBackImg);
        JButton back = new JButton(backIcon);
        back.setBounds(25, 15, 105, 60);
        UIUtils.makeButtonTransparent(back);
        UIUtils.setupZoomEffect(back, backIcon, 105, 60);
        back.setActionCommand("Back");
        back.addActionListener(gachacontroller);

        add(roll1);
        add(rolls10);
        add(rolls100);
        add(back);

        ImageIcon bgIcon = new ImageIcon("src/assets/Menu Graphics/bgr2.png");
        JLabel background = new JLabel(bgIcon);
        background.setBounds(0, 0, 672, 672);
        add(background);
        return;
    }
}