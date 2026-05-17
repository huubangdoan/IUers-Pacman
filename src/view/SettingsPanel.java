package view;
import controller.*;
import java.awt.*;
import javax.swing.*;
import utils.UIUtils;

public class SettingsPanel extends JPanel {
    private JLabel infoPopup;
    public SettingsPanel(SettingsController settingscontroller) {
        setLayout(null); 

        ImageIcon originalBack = new ImageIcon("src/assets/Menu Graphics/back.png");
        Image scaledBackImg = originalBack.getImage().getScaledInstance(105, 60, Image.SCALE_SMOOTH);
        ImageIcon backIcon = new ImageIcon(scaledBackImg);
        JButton back = new JButton(backIcon);
        back.setBounds(25, 15, 105, 60);
        UIUtils.makeButtonTransparent(back);
        UIUtils.setupZoomEffect(back, backIcon, 105, 60);
        back.setActionCommand("Back");
        back.addActionListener(settingscontroller);

        ImageIcon originalAboutus = new ImageIcon("src/assets/Menu Graphics/aboutus.png");
        Image scaledAboutusImg = originalAboutus.getImage().getScaledInstance(153, 90, Image.SCALE_SMOOTH);
        ImageIcon aboutusIcon = new ImageIcon(scaledAboutusImg);
        JButton aboutus = new JButton(aboutusIcon);
        aboutus.setBounds(260, 290, 153, 90);
        UIUtils.makeButtonTransparent(aboutus);
        UIUtils.setupZoomEffect(aboutus, aboutusIcon, 153, 90);
        aboutus.setActionCommand("About Us");
        aboutus.addActionListener(settingscontroller);

        ImageIcon originalHelp = new ImageIcon("src/assets/Menu Graphics/help.png");
        Image scaledHelpImg = originalHelp.getImage().getScaledInstance(153, 90, Image.SCALE_SMOOTH);
        ImageIcon helpIcon = new ImageIcon(scaledHelpImg);
        JButton help = new JButton(helpIcon);
        help.setBounds(260, 375, 153, 90);
        UIUtils.makeButtonTransparent(help);
        UIUtils.setupZoomEffect(help, helpIcon, 153, 90);
        help.setActionCommand("Help");
        help.addActionListener(settingscontroller);

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
            infoPopup.setVisible(true);
        });

        ImageIcon canvaImg = new ImageIcon("src/assets/Menu Graphics/guide.png");
        infoPopup = new JLabel(canvaImg); 
        int overlayW = 500; 
        int overlayH = 500; 
        int centerX = (672 - overlayW) / 2;
        int centerY = (672 - overlayH) / 2;

        infoPopup.setBounds(centerX, centerY, overlayW, overlayH);
        infoPopup.setVisible(false); 

        infoPopup.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                infoPopup.setVisible(false);
            }
        });

        add(infoPopup, 0);
        add(s);
        add(back);
        add(aboutus);
        add(help);
        add(tutorial);

        ImageIcon bgIcon = new ImageIcon("src/assets/Menu Graphics/setbgr1.png");
        JLabel background = new JLabel(bgIcon);
        background.setBounds(0, 0, 672, 672);
        add(background);
        return;
    }

} 