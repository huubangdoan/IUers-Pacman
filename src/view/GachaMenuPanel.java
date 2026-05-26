package view;

import controller.GachaMenuController;
import gacha.SkinManager;
import java.awt.*;
import java.io.File;
import javax.swing.*;
import utils.UIUtils;

public class GachaMenuPanel extends JPanel {

    private SkinManager skinManager;
    private JLabel scoreLabel;

    public GachaMenuPanel(GachaMenuController gachacontroller, SkinManager skinManager) {
        this.skinManager = skinManager;
        setLayout(null);

        Font sciFiFont = new Font("Consolas", Font.BOLD, 18); 
        Font sciFiFontSmall = new Font("Consolas", Font.PLAIN, 14); 

        try {
            File fontFile = new File("src/assets/Font/SquadaOne-Regular.ttf"); 
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, fontFile);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(customFont);
            
            sciFiFont = customFont.deriveFont(26f); 
            sciFiFontSmall = customFont.deriveFont(16f); 
        } catch (Exception e) {
            System.out.println("Không load được Font tùy chỉnh, xài đỡ Consolas nha!");
        }

        JLabel energyTitle = new JLabel("💠 TOTAL ENERGY:", SwingConstants.CENTER);
        energyTitle.setFont(sciFiFontSmall); 
        energyTitle.setForeground(new Color(200, 225, 255)); 
        energyTitle.setBounds(350, 150, 260, 25);
        add(energyTitle);

        scoreLabel = new JLabel("", SwingConstants.CENTER);
        scoreLabel.setFont(sciFiFont);
        scoreLabel.setForeground(new Color(225, 220, 0)); 
        scoreLabel.setBounds(350, 180, 260,35);
        add(scoreLabel);
        refreshScore(); 

        JLabel costLabel = new JLabel("⚡ Cost: " + String.format("%,d", SkinManager.ROLL_COST) + " pts/Roll", SwingConstants.CENTER);
        costLabel.setFont(sciFiFontSmall);
        costLabel.setForeground(new Color(200, 225, 255)); 
        costLabel.setBounds(350, 220, 260, 25);
        add(costLabel);

        JLabel refundLabel = new JLabel("🔄 Refund: +" + String.format("%,d", SkinManager.REFUND_COST) + " pts", SwingConstants.CENTER);
        refundLabel.setFont(sciFiFontSmall);
        refundLabel.setForeground(new Color(200, 225, 255));
        refundLabel.setBounds(350, 250, 260, 25);
        add(refundLabel);

        ImageIcon original1Roll = new ImageIcon("src/assets/Menu Graphics/roll1.png");
        Image scaled1RollImg = original1Roll.getImage().getScaledInstance(143, 80, Image.SCALE_SMOOTH);
        ImageIcon roll1Icon = new ImageIcon(scaled1RollImg);
        JButton roll1 = new JButton(roll1Icon);
        roll1.setBounds(408, 338, 143, 80);
        UIUtils.makeButtonTransparent(roll1);
        UIUtils.setupZoomEffect(roll1, roll1Icon, 143, 80);
        roll1.setActionCommand("1Roll");
        roll1.addActionListener(gachacontroller);

        ImageIcon original10Rolls = new ImageIcon("src/assets/Menu Graphics/rolls10.png");
        Image scaled10RollsImg = original10Rolls.getImage().getScaledInstance(143, 80, Image.SCALE_SMOOTH);
        ImageIcon rolls10Icon = new ImageIcon(scaled10RollsImg);
        JButton rolls10 = new JButton(rolls10Icon);
        rolls10.setBounds(408, 411, 143, 80);
        UIUtils.makeButtonTransparent(rolls10);
        UIUtils.setupZoomEffect(rolls10, rolls10Icon, 143, 80);
        rolls10.setActionCommand("10Rolls");
        rolls10.addActionListener(gachacontroller);

        ImageIcon original100Rolls = new ImageIcon("src/assets/Menu Graphics/rolls100.png");
        Image scaled100RollsImg = original100Rolls.getImage().getScaledInstance(143, 80, Image.SCALE_SMOOTH);
        ImageIcon rolls100Icon = new ImageIcon(scaled100RollsImg);
        JButton rolls100 = new JButton(rolls100Icon);
        rolls100.setBounds(408, 484, 143, 80);
        UIUtils.makeButtonTransparent(rolls100);
        UIUtils.setupZoomEffect(rolls100, rolls100Icon, 143, 80);
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

        ImageIcon bgIcon = new ImageIcon("src/assets/Menu Graphics/bgr2.1.png");
        JLabel background = new JLabel(bgIcon);
        background.setBounds(0, 0, 672, 672);
        add(background);
    }

    public void refreshScore() {
        long pts = skinManager.getScoreManager().getCumulativeScore();
        scoreLabel.setText(String.format("%,d", pts) + " pts");
    }
}