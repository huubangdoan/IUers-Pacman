package view;

import controller.GachaMenuController;
import gacha.SkinManager;
import java.awt.*;
import javax.swing.*;
import utils.UIUtils;

public class GachaMenuPanel extends JPanel {

    private SkinManager skinManager;
    private JLabel scoreLabel;

    public GachaMenuPanel(GachaMenuController gachacontroller, SkinManager skinManager) {
        this.skinManager = skinManager;
        setLayout(null);

        // thiết kế lại cái title của cái gacha nhen
        JLabel title = new JLabel("GACHA", SwingConstants.CENTER);
        title.setFont(new Font("Consolas", Font.BOLD, 32));
        title.setForeground(new Color(255, 215, 0));
        title.setBounds(186, 30, 300, 45);
        add(title);
        // thiết kế lại cái sroce  tổng của gacha nhen
        scoreLabel = new JLabel("", SwingConstants.CENTER);
        scoreLabel.setFont(new Font("Consolas", Font.BOLD, 16));
        scoreLabel.setForeground(new Color(180, 255, 180));
        scoreLabel.setBounds(136, 78, 400, 28);
        add(scoreLabel);
        refreshScore(); 

        //thiết kế lại cái mô tả score  của gacha nhen 
        JLabel priceInfo = new JLabel(
            "1 Roll = " + SkinManager.ROLL_COST + " pts  |  Trùng hoàn " + SkinManager.REFUND_COST + " pts",
            SwingConstants.CENTER);
        priceInfo.setFont(new Font("Consolas", Font.PLAIN, 12));
        priceInfo.setForeground(new Color(200, 200, 200));
        priceInfo.setBounds(136, 108, 400, 20);
        add(priceInfo);

        // thiết kế lại nút roll 1 luôn
        ImageIcon original1Roll = new ImageIcon("src/assets/Menu Graphics/roll1.png");
        Image scaled1RollImg = original1Roll.getImage().getScaledInstance(210, 120, Image.SCALE_SMOOTH);
        ImageIcon roll1Icon = new ImageIcon(scaled1RollImg);
        JButton roll1 = new JButton(roll1Icon);
        roll1.setBounds(390, 185, 210, 120);
        UIUtils.makeButtonTransparent(roll1);
        UIUtils.setupZoomEffect(roll1, roll1Icon, 210, 120);
        roll1.setActionCommand("1Roll");
        roll1.addActionListener(gachacontroller);

        // thiết kế lại label giá dưới
        JLabel price1 = makeGoldLabel(SkinManager.ROLL_COST + " pts", 390, 310, 210);
        add(price1);

        // thiết kế lại label giá dưới
        ImageIcon original10Rolls = new ImageIcon("src/assets/Menu Graphics/rolls10.png");
        Image scaled10RollsImg = original10Rolls.getImage().getScaledInstance(210, 120, Image.SCALE_SMOOTH);
        ImageIcon rolls10Icon = new ImageIcon(scaled10RollsImg);
        JButton rolls10 = new JButton(rolls10Icon);
        rolls10.setBounds(390, 330, 210, 120);
        UIUtils.makeButtonTransparent(rolls10);
        UIUtils.setupZoomEffect(rolls10, rolls10Icon, 210, 120);
        rolls10.setActionCommand("10Rolls");
        rolls10.addActionListener(gachacontroller);

        JLabel price10 = makeGoldLabel((SkinManager.ROLL_COST * 10) + " pts", 390, 455, 210);
        add(price10);

        // thiết kế lại nút 100
        ImageIcon original100Rolls = new ImageIcon("src/assets/Menu Graphics/rolls100.png");
        Image scaled100RollsImg = original100Rolls.getImage().getScaledInstance(210, 120, Image.SCALE_SMOOTH);
        ImageIcon rolls100Icon = new ImageIcon(scaled100RollsImg);
        JButton rolls100 = new JButton(rolls100Icon);
        rolls100.setBounds(390, 475, 210, 120);
        UIUtils.makeButtonTransparent(rolls100);
        UIUtils.setupZoomEffect(rolls100, rolls100Icon, 210, 120);
        rolls100.setActionCommand("100Rolls");
        rolls100.addActionListener(gachacontroller);

        JLabel price100 = makeGoldLabel((SkinManager.ROLL_COST * 100) + " pts", 390, 600, 210);
        add(price100);

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
    }

    public void refreshScore() {
        long pts = skinManager.getScoreManager().getCumulativeScore();
        scoreLabel.setText("💰 accumatited: " + String.format("%,d", pts) + " pts");
    }

    private JLabel makeGoldLabel(String text, int x, int y, int w) {
        JLabel lbl = new JLabel(text, SwingConstants.CENTER);
        lbl.setFont(new Font("Consolas", Font.BOLD, 12));
        lbl.setForeground(new Color(255, 215, 0));
        lbl.setBounds(x, y, w, 20);
        return lbl;
    }
}