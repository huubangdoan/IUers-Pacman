package view;
import controller.*;
import java.awt.*;
import javax.swing.*;
import utils.UIUtils;

public class SettingsPanel extends JPanel {
    private TutorialOverlay tutorialOverlay;
    public SettingsPanel(SettingsController settingscontroller) {
        setLayout(null); 

        ImageIcon originalSlow = new ImageIcon("src/assets/Menu Graphics/slow.png");
        Image scaledSlowImg = originalSlow.getImage().getScaledInstance(210, 120, Image.SCALE_SMOOTH);
        ImageIcon slowIcon = new ImageIcon(scaledSlowImg);
        JButton slow = new JButton(slowIcon);
        slow.setBounds(231, 245, 210, 120);
        UIUtils.makeButtonTransparent(slow);
        UIUtils.setupZoomEffect(slow, slowIcon, 210, 120);
        slow.setActionCommand("Slow");
        slow.addActionListener(settingscontroller);

        ImageIcon originalNormal = new ImageIcon("src/assets/Menu Graphics/normal.png");
        Image scaledNormalImg = originalNormal.getImage().getScaledInstance(210, 120, Image.SCALE_SMOOTH);
        ImageIcon normalIcon = new ImageIcon(scaledNormalImg);
        JButton normal = new JButton(normalIcon);
        normal.setBounds(231, 330, 210, 120);
        UIUtils.makeButtonTransparent(normal);
        UIUtils.setupZoomEffect(normal, normalIcon, 210, 120);
        normal.setActionCommand("Normal");
        normal.addActionListener(settingscontroller);

        ImageIcon originalFast = new ImageIcon("src/assets/Menu Graphics/fast.png");
        Image scaledFastImg = originalFast.getImage().getScaledInstance(210, 120, Image.SCALE_SMOOTH);
        ImageIcon fastIcon = new ImageIcon(scaledFastImg);
        JButton fast = new JButton(fastIcon);
        fast.setBounds(231, 415, 210, 120);
        UIUtils.makeButtonTransparent(fast);
        UIUtils.setupZoomEffect(fast, fastIcon, 210, 120);
        fast.setActionCommand("Fast");
        fast.addActionListener(settingscontroller);

        ImageIcon originalBack = new ImageIcon("src/assets/Menu Graphics/back.png");
        Image scaledBackImg = originalBack.getImage().getScaledInstance(105, 60, Image.SCALE_SMOOTH);
        ImageIcon backIcon = new ImageIcon(scaledBackImg);
        JButton back = new JButton(backIcon);
        back.setBounds(25, 15, 105, 60);
        UIUtils.makeButtonTransparent(back);
        UIUtils.setupZoomEffect(back, backIcon, 105, 60);
        back.setActionCommand("Back");
        back.addActionListener(settingscontroller);

        ImageIcon originalS = new ImageIcon("src/assets/Menu Graphics/s.png");
        Image scaledSImg = originalS.getImage().getScaledInstance(105, 60, Image.SCALE_SMOOTH);
        ImageIcon sIcon = new ImageIcon(scaledSImg);
        JButton s = new JButton(sIcon);
        s.setBounds(510, 15, 105, 60);
        UIUtils.makeButtonTransparent(s);
        UIUtils.setupZoomEffect(s, sIcon, 105, 60);
        s.setActionCommand("Sound");
        s.addActionListener(settingscontroller);

        ImageIcon originalTutorial = new ImageIcon("src/assets/Menu Graphics/tut.png");
        Image scaledTutorialImg = originalTutorial.getImage().getScaledInstance(105, 60, Image.SCALE_SMOOTH);
        ImageIcon tutorialIcon = new ImageIcon(scaledTutorialImg);
        JButton tutorial = new JButton(tutorialIcon);
        tutorial.setBounds(580, 15, 105, 60);
        UIUtils.makeButtonTransparent(tutorial);
        UIUtils.setupZoomEffect(tutorial, tutorialIcon, 105, 60);
        tutorial.addActionListener(e -> {
            tutorialOverlay.setVisible(true);
        });

        ImageIcon canvaImg = new ImageIcon("src/assets/Menu Graphics/guide_canva.png");
        tutorialOverlay = new TutorialOverlay(canvaImg);
        tutorialOverlay.setBounds(86, 86, 500, 500); 
        tutorialOverlay.setVisible(false);
        
        add(tutorialOverlay, 0);
        add(s);
        add(slow);
        add(normal);
        add(fast);
        add(back);
        add(tutorial);

        ImageIcon bgIcon = new ImageIcon("src/assets/Menu Graphics/setbgr1.png");
        JLabel background = new JLabel(bgIcon);
        background.setBounds(0, 0, 672, 672);
        add(background);
        return;
    }

} 