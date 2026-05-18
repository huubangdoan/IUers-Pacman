package view;
import controller.*;
import java.awt.*;
import javax.swing.*;
import utils.UIUtils;

public class SettingsPanel extends JPanel {
    private JLabel infoPopup;
    private JPanel aboutPopup; 
    private JLabel helpPopup;
    private Font customFont;

    public SettingsPanel(SettingsController settingscontroller) {
        setLayout(null); 

        try {
            customFont = Font.createFont(Font.TRUETYPE_FONT, new java.io.File("src/assets/Font/SquadaOne-Regular.ttf"));
        } catch (Exception e) {
            e.printStackTrace();
            customFont = new Font("Consolas", Font.BOLD, 18);
        }
        Font titleFont = customFont.deriveFont(Font.BOLD, 28f);
        Font nameFont = customFont.deriveFont(Font.PLAIN, 20f);
        Font versionFont = customFont.deriveFont(Font.ITALIC, 16f);

        ImageIcon originalBack = new ImageIcon("src/assets/Menu Graphics/back.png");
        Image scaledBackImg = originalBack.getImage().getScaledInstance(105, 60, Image.SCALE_SMOOTH);
        ImageIcon backIcon = new ImageIcon(scaledBackImg);
        JButton back = new JButton(backIcon);
        back.setBounds(25, 15, 105, 60);
        UIUtils.makeButtonTransparent(back);
        UIUtils.setupZoomEffect(back, backIcon, 105, 60);
        back.setActionCommand("Back");
        back.addActionListener(settingscontroller);

        aboutPopup = new JPanel(null) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                GradientPaint gp = new GradientPaint(
                    0, 0, new Color(20, 70, 60, 220), 
                    0, getHeight(), new Color(25, 90, 75, 230)
                );
                g2.setPaint(gp);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30); 
                
                g2.setColor(new Color(80, 220, 160)); 
                g2.setStroke(new BasicStroke(3f));
                g2.drawRoundRect(1, 1, getWidth() - 2, getHeight() - 2, 30, 30);
                g2.dispose();
            }
        };
        aboutPopup.setOpaque(false);
        int aboutW = 400;
        int aboutH = 300;
        int centerAboutX = (672 - aboutW) / 2;
        int centerAboutY = ((672 - aboutH) / 2) + 30;
        aboutPopup.setBounds(centerAboutX, centerAboutY, aboutW, aboutH);

        JLabel titleLabel = new JLabel("PAC-MAN: MULTIVERSE", SwingConstants.CENTER);
        titleLabel.setFont(titleFont);
        titleLabel.setForeground(new Color(150, 255, 200)); 
        titleLabel.setBounds(0, 20, aboutW, 35);
        aboutPopup.add(titleLabel);

        JLabel versionLabel = new JLabel("Version 1.0 - OOP Project", SwingConstants.CENTER);
        versionLabel.setFont(versionFont);
        versionLabel.setForeground(Color.LIGHT_GRAY);
        versionLabel.setBounds(0, 60, aboutW, 25);
        aboutPopup.add(versionLabel);

        String[] devs = {
            "Doan Huu Bang",
            "Le Phuong Chau",
            "Nguyen Hoang Hai",
            "Le Ngoc Quyen",
            "Tran Thi Huyen Trang"
        };

        int yPos = 110; 
        for (String dev : devs) {
            JLabel devLabel = new JLabel("✦ " + dev + " ✦", SwingConstants.CENTER);
            devLabel.setFont(nameFont);
            devLabel.setForeground(Color.WHITE); 
            devLabel.setBounds(0, yPos, aboutW, 30);
            aboutPopup.add(devLabel);
            yPos += 35; 
        }
        aboutPopup.setVisible(false);
       

        ImageIcon originalAboutus = new ImageIcon("src/assets/Menu Graphics/aboutus.png");
        Image scaledAboutusImg = originalAboutus.getImage().getScaledInstance(153, 90, Image.SCALE_SMOOTH);
        ImageIcon aboutusIcon = new ImageIcon(scaledAboutusImg);
        JButton aboutus = new JButton(aboutusIcon);
        aboutus.setBounds(260, 290, 153, 90);
        UIUtils.makeButtonTransparent(aboutus);
        UIUtils.setupZoomEffect(aboutus, aboutusIcon, 153, 90);
        aboutus.addActionListener(e -> {
            aboutPopup.setVisible(true);
        });

        ImageIcon originalHelp = new ImageIcon("src/assets/Menu Graphics/help.png");
        Image scaledHelpImg = originalHelp.getImage().getScaledInstance(153, 90, Image.SCALE_SMOOTH);
        ImageIcon helpIcon = new ImageIcon(scaledHelpImg);
        JButton help = new JButton(helpIcon);
        help.setBounds(260, 375, 153, 90);
        UIUtils.makeButtonTransparent(help);
        UIUtils.setupZoomEffect(help, helpIcon, 153, 90);
        help.addActionListener(e -> {
            helpPopup.setVisible(true);
        });

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

        ImageIcon helpImg = new ImageIcon("src/assets/Menu Graphics/guide.png");
        helpPopup = new JLabel(helpImg); 
        int helpW = 500; 
        int helpH = 500; 
        int helpcenterX = (672 - helpW) / 2;
        int helpcenterY = (672 - helpH) / 2;

        helpPopup.setBounds(helpcenterX, helpcenterY, helpW, helpH);
        helpPopup.setVisible(false); 

        add(aboutPopup, 0);
        add(helpPopup, 0);
        add(infoPopup, 0);
        add(s);
        add(back);
        add(aboutus);
        add(help);
        add(tutorial);


        ImageIcon bgIcon = new ImageIcon("src/assets/Menu Graphics/setbgr1.png");
        JLabel background = new JLabel(bgIcon);
        background.setBounds(0, 0, 672, 672);

        background.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                if (infoPopup != null && infoPopup.isVisible()) {
                    infoPopup.setVisible(false);
                }
                if (aboutPopup != null && aboutPopup.isVisible()) {
                    aboutPopup.setVisible(false);
                }
                if (helpPopup != null && helpPopup.isVisible()) {
                    helpPopup.setVisible(false);
                }
            }
        });
        add(background);
        return;
    }

} 