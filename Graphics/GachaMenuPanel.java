import javax.swing.*;
import java.awt.*;

public class GachaMenuPanel extends JPanel {
    public GachaMenuPanel(GachaMenuController gachacontroller) {
        setLayout(null);

        ImageIcon original1Roll = new ImageIcon("C:\\\\Users\\\\Admin\\\\OOP_Lab\\\\IUers-s-Pacman\\\\Graphics\\\\mapmenu\\\\endless.png");
        Image scaled1RollImg = original1Roll.getImage().getScaledInstance(210, 120,Image.SCALE_SMOOTH);
        ImageIcon roll1Icon = new ImageIcon(scaled1RollImg);
        JButton roll1 = new JButton(roll1Icon);
        roll1.setBounds(231, 190, 210, 120);
        UIUtils.makeButtonTransparent(roll1);
        UIUtils.setupZoomEffect(roll1, roll1Icon, 210, 120);
        roll1.setActionCommand("1RRoll");
        roll1.addActionListener(gachacontroller);

        ImageIcon original10Rolls = new ImageIcon("C:\\\\Users\\\\Admin\\\\OOP_Lab\\\\IUers-s-Pacman\\\\Graphics\\\\mapmenu\\\\endless.png");
        Image scaled10RollsImg = original10Rolls.getImage().getScaledInstance(210, 120,Image.SCALE_SMOOTH);
        ImageIcon rolls10Icon = new ImageIcon(scaled10RollsImg);
        JButton rolls10 = new JButton(rolls10Icon);
        rolls10.setBounds(231, 254, 210, 120);
        UIUtils.makeButtonTransparent(rolls10);
        UIUtils.setupZoomEffect(rolls10, rolls10Icon, 210, 120);
        rolls10.setActionCommand("10Rolls");
        rolls10.addActionListener(gachacontroller);

        ImageIcon original100Rolls = new ImageIcon("C:\\\\Users\\\\Admin\\\\OOP_Lab\\\\IUers-s-Pacman\\\\Graphics\\\\mapmenu\\\\endless.png");
        Image scaled100RollsImg = original100Rolls.getImage().getScaledInstance(210, 120,Image.SCALE_SMOOTH);
        ImageIcon rolls100Icon = new ImageIcon(scaled100RollsImg);
        JButton rolls100 = new JButton(rolls100Icon);
        rolls100.setBounds(231, 318, 210, 120);
        UIUtils.makeButtonTransparent(rolls100);
        UIUtils.setupZoomEffect(rolls100, rolls100Icon, 210, 120);
        rolls100.setActionCommand("100Rolls");
        rolls100.addActionListener(gachacontroller);

        ImageIcon originalBack = new ImageIcon("C:\\Users\\Admin\\OOP_Lab\\IUers-s-Pacman\\Graphics\\mapmenu\\endless.png");
        Image scaledBackImg = originalBack.getImage().getScaledInstance(210, 120, Image.SCALE_SMOOTH);
        ImageIcon backIcon = new ImageIcon(scaledBackImg);
        JButton back = new JButton(backIcon);
        back.setBounds(231, 403, 210, 120);
        UIUtils.makeButtonTransparent(back);
        UIUtils.setupZoomEffect(back, backIcon, 210, 120);
        back.setActionCommand("Back");
        back.addActionListener(gachacontroller);

        add(roll1);
        add(rolls10);
        add(rolls100);
        add(back);

        ImageIcon bgIcon = new ImageIcon("C:\\Users\\Admin\\OOP_Lab\\IUers-s-Pacman\\Graphics\\mapmenu\\bgr.png");
        JLabel background = new JLabel(bgIcon);
        background.setBounds(0, 0, 672, 672);
        add(background);
        return;
    }
}