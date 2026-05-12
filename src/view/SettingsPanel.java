package view;
import controller.*;
import java.awt.*;
import javax.swing.*;
import utils.UIUtils;

public class SettingsPanel extends JPanel {
    private TutorialOverlay tutorialOverlay;
    public SettingsPanel(SettingsController settingscontroller) {
        setLayout(null); 

        ImageIcon originalS = new ImageIcon("src/assets/Menu Graphics/s.png");
        Image scaledSImg = originalS.getImage().getScaledInstance(210, 120, Image.SCALE_SMOOTH);
        ImageIcon sIcon = new ImageIcon(scaledSImg);
        JButton s = new JButton(sIcon);
        s.setBounds(231, 190, 210, 120);
        UIUtils.makeButtonTransparent(s);
        UIUtils.setupZoomEffect(s, sIcon, 210, 120);
        s.setActionCommand("Sound");
        s.addActionListener(settingscontroller);

        ImageIcon originalEasy = new ImageIcon("src/assets/Menu Graphics/easy.png");
        Image scaledEasyImg = originalEasy.getImage().getScaledInstance(210, 120, Image.SCALE_SMOOTH);
        ImageIcon easyIcon = new ImageIcon(scaledEasyImg);
        JButton easy = new JButton(easyIcon);
        easy.setBounds(231, 275, 210, 120);
        UIUtils.makeButtonTransparent(easy);
        UIUtils.setupZoomEffect(easy, easyIcon, 210, 120);
        easy.setActionCommand("Easy");
        easy.addActionListener(settingscontroller);

        ImageIcon originalNormal = new ImageIcon("src/assets/Menu Graphics/normal.png");
        Image scaledNormalImg = originalNormal.getImage().getScaledInstance(210, 120, Image.SCALE_SMOOTH);
        ImageIcon normalIcon = new ImageIcon(scaledNormalImg);
        JButton normal = new JButton(normalIcon);
        normal.setBounds(231, 360, 210, 120);
        UIUtils.makeButtonTransparent(normal);
        UIUtils.setupZoomEffect(normal, normalIcon, 210, 120);
        normal.setActionCommand("Normal");
        normal.addActionListener(settingscontroller);

        ImageIcon originalHard = new ImageIcon("src/assets/Menu Graphics/hard.png");
        Image scaledHardImg = originalHard.getImage().getScaledInstance(210, 120, Image.SCALE_SMOOTH);
        ImageIcon hardIcon = new ImageIcon(scaledHardImg);
        JButton hard = new JButton(hardIcon);
        hard.setBounds(231, 445, 210, 120);
        UIUtils.makeButtonTransparent(hard);
        UIUtils.setupZoomEffect(hard, hardIcon, 210, 120);
        hard.setActionCommand("Hard");
        hard.addActionListener(settingscontroller);

        ImageIcon originalBack = new ImageIcon("src/assets/Menu Graphics/back.png");
        Image scaledBackImg = originalBack.getImage().getScaledInstance(105, 60, Image.SCALE_SMOOTH);
        ImageIcon backIcon = new ImageIcon(scaledBackImg);
        JButton back = new JButton(backIcon);
        back.setBounds(25, 15, 105, 60);
        UIUtils.makeButtonTransparent(back);
        UIUtils.setupZoomEffect(back, backIcon, 105, 60);
        back.setActionCommand("Back");
        back.addActionListener(settingscontroller);

        ImageIcon originalTutorial = new ImageIcon("src/assets/Menu Graphics/tutorial.png");
        Image scaledTutorialImg = originalTutorial.getImage().getScaledInstance(105, 60, Image.SCALE_SMOOTH);
        ImageIcon tutorialIcon = new ImageIcon(scaledTutorialImg);
        JButton tutorial = new JButton(tutorialIcon);
        tutorial.setBounds(450, 15, 105, 60);
        UIUtils.makeButtonTransparent(tutorial);
        UIUtils.setupZoomEffect(back, tutorialIcon, 105, 60);
        tutorial.addActionListener(e -> {
            tutorialOverlay.setVisible(true);
        });

        ImageIcon canvaImg = new ImageIcon("src/assets/Menu Graphics/guide_canva.png");
        tutorialOverlay = new TutorialOverlay(canvaImg);
        
        add(tutorialOverlay);
        add(s);
        add(easy);
        add(normal);
        add(hard);
        add(back);
        add(tutorial);

        ImageIcon bgIcon = new ImageIcon("src/assets/Menu Graphics/bgr.png");
        JLabel background = new JLabel(bgIcon);
        background.setBounds(0, 0, 672, 672);
        add(background);
        return;
    }

} 