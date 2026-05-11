package view;

import controller.GachaResultController;
import gacha.Skin;
import gacha.SkinManager.RollResult;
import java.awt.*;
import java.util.List;
import javax.swing.*;
import utils.UIUtils;

public class GachaResultPanel extends JPanel {

    private GachaResultController resultController;
    private JPanel contentArea;

    public GachaResultPanel(GachaResultController resultController) {
        this.resultController = resultController;
        setLayout(null);

        // ── Nút Back ───────────────────────────────────────────────────────
        ImageIcon originalBack = new ImageIcon("src/assets/Menu Graphics/back.png");
        Image scaledBack = originalBack.getImage().getScaledInstance(105, 60, Image.SCALE_SMOOTH);
        ImageIcon backIcon = new ImageIcon(scaledBack);
        JButton back = new JButton(backIcon);
        back.setBounds(25, 15, 105, 60);
        UIUtils.makeButtonTransparent(back);
        UIUtils.setupZoomEffect(back, backIcon, 105, 60);
        back.setActionCommand("Back");
        back.addActionListener(resultController);
        add(back);

        // ── Nút View My Skins ──────────────────────────────────────────────
        JButton viewSkins = new JButton("My Skins");
        viewSkins.setFont(new Font("Consolas", Font.BOLD, 13));
        viewSkins.setForeground(Color.WHITE);
        viewSkins.setBackground(new Color(30, 30, 80));
        viewSkins.setBounds(536, 25, 110, 35);
        viewSkins.setActionCommand("ViewSkins");
        viewSkins.addActionListener(resultController);
        add(viewSkins);

        // ── Content area ───────────────────────────────────────────────────
        contentArea = new JPanel(null);
        contentArea.setOpaque(false);
        contentArea.setBounds(0, 80, 672, 560);
        add(contentArea);

        // ── Background ─────────────────────────────────────────────────────
        ImageIcon bgIcon = new ImageIcon("src/assets/Menu Graphics/bgr2.png");
        JLabel background = new JLabel(bgIcon);
        background.setBounds(0, 0, 672, 672);
        add(background);
    }

    public void showResults(List<RollResult> results) {
        contentArea.removeAll();
        if (results.size() == 1) showSingle(results.get(0));
        else showMultiple(results);
        contentArea.revalidate();
        contentArea.repaint();
    }

    private void showSingle(RollResult result) {
        Skin skin = result.skin;
        Color rColor = rarityColor(skin.getRarity());

        // ── Card nền ───────────────────────────────────────────────────────
        JPanel card = createCard(rColor);
        card.setBounds(236, 30, 200, 310);
        contentArea.add(card);

        // Ảnh preview
        ImageIcon original = new ImageIcon(skin.getPreviewPath());
        Image scaled = original.getImage().getScaledInstance(160, 160, Image.SCALE_SMOOTH);
        JLabel imgLabel = new JLabel(new ImageIcon(scaled));
        imgLabel.setBounds(20, 20, 160, 160);
        card.add(imgLabel);

        // Tên
        JLabel nameLabel = new JLabel(skin.getName(), SwingConstants.CENTER);
        nameLabel.setFont(new Font("Consolas", Font.BOLD, 22));
        nameLabel.setForeground(rColor);
        nameLabel.setBounds(0, 190, 200, 35);
        card.add(nameLabel);

        // Rarity
        JLabel rarityLabel = new JLabel(skin.getRarity().name(), SwingConstants.CENTER);
        rarityLabel.setFont(new Font("Consolas", Font.BOLD, 16));
        rarityLabel.setForeground(rColor);
        rarityLabel.setBounds(0, 228, 200, 25);
        card.add(rarityLabel);

        // NEW / DUPLICATE
        if (result.isDuplicate) {
            JLabel dupLabel = new JLabel("✦ TRÙNG  +800 pts ✦", SwingConstants.CENTER);
            dupLabel.setFont(new Font("Consolas", Font.BOLD, 13));
            dupLabel.setForeground(new Color(255, 180, 0));
            dupLabel.setBounds(0, 260, 200, 25);
            card.add(dupLabel);
        } else {
            JLabel newLabel = new JLabel("✦ NEW ✦", SwingConstants.CENTER);
            newLabel.setFont(new Font("Consolas", Font.BOLD, 15));
            newLabel.setForeground(Color.YELLOW);
            newLabel.setBounds(0, 260, 200, 25);
            card.add(newLabel);
        }
    }

    private void showMultiple(List<RollResult> results) {
        int cols  = 5;
        int cellW = 120, cellH = 155;
        int startX = (672 - cols * cellW) / 2;

        for (int i = 0; i < results.size(); i++) {
            RollResult rr   = results.get(i);
            Skin skin       = rr.skin;
            Color rColor    = rarityColor(skin.getRarity());
            int x = startX + (i % cols) * cellW + 4;
            int y = (i / cols) * cellH + 8;

            // Card nền
            JPanel card = createCard(rColor);
            card.setBounds(x, y, cellW - 8, cellH - 10);
            contentArea.add(card);

            // Ảnh
            ImageIcon original = new ImageIcon(skin.getPreviewPath());
            Image scaled = original.getImage().getScaledInstance(72, 72, Image.SCALE_SMOOTH);
            JLabel imgLabel = new JLabel(new ImageIcon(scaled));
            imgLabel.setBounds(14, 6, 72, 72);
            card.add(imgLabel);

            // Tên
            JLabel nameLabel = new JLabel(skin.getName(), SwingConstants.CENTER);
            nameLabel.setFont(new Font("Consolas", Font.BOLD, 10));
            nameLabel.setForeground(rColor);
            nameLabel.setBounds(0, 80, cellW - 8, 18);
            card.add(nameLabel);

            // Rarity
            JLabel rarityLabel = new JLabel(skin.getRarity().name(), SwingConstants.CENTER);
            rarityLabel.setFont(new Font("Consolas", Font.PLAIN, 9));
            rarityLabel.setForeground(rColor);
            rarityLabel.setBounds(0, 98, cellW - 8, 16);
            card.add(rarityLabel);

            // Duplicate / New badge
            if (rr.isDuplicate) {
                JLabel dupLabel = new JLabel("+800 pts", SwingConstants.CENTER);
                dupLabel.setFont(new Font("Consolas", Font.BOLD, 10));
                dupLabel.setForeground(new Color(255, 200, 0));
                dupLabel.setBounds(0, 116, cellW - 8, 16);
                card.add(dupLabel);
            } else {
                JLabel newLabel = new JLabel("NEW!", SwingConstants.CENTER);
                newLabel.setFont(new Font("Consolas", Font.BOLD, 10));
                newLabel.setForeground(Color.YELLOW);
                newLabel.setBounds(0, 116, cellW - 8, 16);
                card.add(newLabel);
            }
        }
    }

    /** Tạo card với nền gradient tối + viền rarity */
    private JPanel createCard(Color borderColor) {
        JPanel card = new JPanel(null) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                // Nền gradient tối
                GradientPaint gp = new GradientPaint(
                    0, 0, new Color(20, 20, 40, 220),
                    0, getHeight(), new Color(40, 10, 60, 220));
                g2.setPaint(gp);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 12, 12);
                // Viền rarity
                g2.setColor(borderColor);
                g2.setStroke(new BasicStroke(2f));
                g2.drawRoundRect(1, 1, getWidth() - 2, getHeight() - 2, 12, 12);
                g2.dispose();
            }
        };
        card.setOpaque(false);
        return card;
    }

    private Color rarityColor(Skin.Rarity rarity) {
        return switch (rarity) {
            case EPIC   -> new Color(163, 53, 238);
            case RARE   -> new Color(0, 150, 255);
            case COMMON -> new Color(180, 180, 180);
        };
    }
}