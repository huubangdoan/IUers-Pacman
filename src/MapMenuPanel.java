import javax.swing.*;
import java.awt.*;

public class MapMenuPanel extends JPanel {
    public MapMenuPanel(MapMenuController mapcontroller) {
        setLayout(null);

        ImageIcon originalMap1 = new ImageIcon("C:\\Users\\Admin\\OOP_Lab\\IUers-s-Pacman\\Graphics\\mapmenu\\map1.png");
        Image scaledMap1Img = originalMap1.getImage().getScaledInstance(210, 120, Image.SCALE_SMOOTH);
        ImageIcon map1Icon = new ImageIcon(scaledMap1Img);
        JButton map1 = new JButton(map1Icon);
        map1.setBounds(231, 148, 210, 120);
        UIUtils.makeButtonTransparent(map1);
        UIUtils.setupZoomEffect(map1, map1Icon, 210, 120);
        map1.setActionCommand("Map 1");
        map1.addActionListener(mapcontroller);

        ImageIcon originalMap2 = new ImageIcon("C:\\Users\\Admin\\OOP_Lab\\IUers-s-Pacman\\Graphics\\mapmenu\\map2.png");
        Image scaledMap2Img = originalMap2.getImage().getScaledInstance(210, 120, Image.SCALE_SMOOTH);
        ImageIcon map2Icon = new ImageIcon(scaledMap2Img);
        JButton map2 = new JButton(map2Icon);
        map2.setBounds(231, 233, 210, 120);
        UIUtils.makeButtonTransparent(map2);
        UIUtils.setupZoomEffect(map2, map2Icon, 210, 120);
        map2.setActionCommand("Map 2");
        map2.addActionListener(mapcontroller);

        ImageIcon originalMap3 = new ImageIcon("C:\\Users\\Admin\\OOP_Lab\\IUers-s-Pacman\\Graphics\\mapmenu\\map3.png");
        Image scaledMap3Img = originalMap3.getImage().getScaledInstance(210, 120, Image.SCALE_SMOOTH);
        ImageIcon map3Icon = new ImageIcon(scaledMap3Img);
        JButton map3 = new JButton(map3Icon);
        map3.setBounds(231, 318, 210, 120);
        UIUtils.makeButtonTransparent(map3);
        UIUtils.setupZoomEffect(map3, map3Icon, 210, 120);
        map3.setActionCommand("Map 3");
        map3.addActionListener(mapcontroller);

        ImageIcon originalEndless = new ImageIcon("C:\\Users\\Admin\\OOP_Lab\\IUers-s-Pacman\\Graphics\\mapmenu\\endless.png");
        Image scaledEndlessImg = originalEndless.getImage().getScaledInstance(210, 120, Image.SCALE_SMOOTH);
        ImageIcon endlessIcon = new ImageIcon(scaledEndlessImg);
        JButton endless = new JButton(endlessIcon);
        endless.setBounds(231, 403, 210, 120);
        UIUtils.makeButtonTransparent(endless);
        UIUtils.setupZoomEffect(endless, endlessIcon, 210, 120);
        endless.setActionCommand("Endless");
        endless.addActionListener(mapcontroller);

        add(map1);
        add(map2);
        add(map3);
        add(endless);

        ImageIcon bgIcon = new ImageIcon("C:\\Users\\Admin\\OOP_Lab\\IUers-s-Pacman\\Graphics\\mapmenu\\bgr.png");
        JLabel background = new JLabel(bgIcon);
        background.setBounds(0, 0, 672, 672);
        add(background);
        return;

    }
}