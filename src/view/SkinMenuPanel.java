package view;

import controller.SkinMenuController;
import gacha.Skin;
import gacha.SkinManager;
import java.awt.*;
import java.util.List;
import javax.swing.*;
import utils.UIUtils;

public class SkinMenuPanel extends JPanel {

    private SkinManager skinManager;
    private SkinMenuController skinController;
    private JPanel skinGrid;
    private JLabel selectedLabel;

    private static final int COLS   = 4;
    private static final int CELL_W = 148;
    private static final int CELL_H = 155;

    public SkinMenuPanel(SkinMenuController skinController, SkinManager skinManager) {
        this.skinController = skinController;
        this.skinManager    = skinManager;
        setLayout(null);

        // ── Tiêu đề ────────────────────────────────────────────────────────
        JLabel title = new JLabel("SELECT SKIN", SwingConstants.CENTER);
        title.setFont(new Font("Consolas", Font.BOLD, 24));
        title.setForeground(Color.YELLOW);
        title.setBounds(186, 80, 300, 40);
        add(title);

        // ── Skin đang dùng ─────────────────────────────────────────────────
        selectedLabel = new JLabel("Using: Default Skin", SwingConstants.CENTER);
        selectedLabel.setFont(new Font("Consolas", Font.PLAIN, 14));
        selectedLabel.setForeground(Color.WHITE);
        selectedLabel.setBounds(136, 118, 400, 25);
        add(selectedLabel);

        // ── Nút Back ───────────────────────────────────────────────────────
        ImageIcon originalBack = new ImageIcon("src/assets/Menu Graphics/back.png");
        Image scaledBack = originalBack.getImage().getScaledInstance(105, 60, Image.SCALE_SMOOTH);
        ImageIcon backIcon = new ImageIcon(scaledBack);
        JButton back = new JButton(backIcon);
        back.setBounds(25, 15, 105, 60);
        UIUtils.makeButtonTransparent(back);
        UIUtils.setupZoomEffect(back, backIcon, 105, 60);
        back.setActionCommand("Back");
        back.addActionListener(skinController);
        add(back);

        // ── Nút Default ────────────────────────────────────────────────────
        ImageIcon originalDefault = new ImageIcon("src/assets/Menu Graphics/default.png");
        Image scaledDefaultImg = originalDefault.getImage().getScaledInstance(105, 60, Image.SCALE_SMOOTH);
        ImageIcon defaultIcon = new ImageIcon(scaledDefaultImg);
        JButton defaultBtn = new JButton(defaultIcon);
        defaultBtn.setBounds(536, 15, 105, 60);
        UIUtils.makeButtonTransparent(defaultBtn);
        UIUtils.setupZoomEffect(defaultBtn, defaultIcon, 105, 60);
        defaultBtn.addActionListener(e -> {
            skinManager.selectSkin(SkinManager.DEFAULT_SKIN_FOLDER);
            selectedLabel.setText("Using: Default Skin");
        });
        add(defaultBtn);

        // ── skinGrid trong ScrollPane ───────────────────────────────────────
        skinGrid = new JPanel(null);
        skinGrid.setOpaque(false);

        JScrollPane scrollPane = new JScrollPane(skinGrid,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); 
        scrollPane.setBounds(20, 150, 634, 490);
        
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(null);

        scrollPane.getVerticalScrollBar().setUI(new utils.ModernScrollBarUI()); 
        scrollPane.getVerticalScrollBar().setOpaque(false);
        scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(8, 0)); 
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        
        add(scrollPane);

        // ── Background ─────────────────────────────────────────────────────
        ImageIcon bgIcon = new ImageIcon("src/assets/Menu Graphics/bgr.png");
        JLabel background = new JLabel(bgIcon);
        background.setBounds(0, 0, 672, 672);
        add(background);
    }

    public void refresh() {
        skinGrid.removeAll();
        selectedLabel.setText("Using: " + skinManager.getSelectedSkinFolder());

        List<Skin> unlocked = skinManager.getUnlockedSkins();

        if (unlocked.isEmpty()) {
            JLabel empty = new JLabel("Chưa có skin nào! Hãy thử Gacha.", SwingConstants.CENTER);
            empty.setFont(new Font("Consolas", Font.ITALIC, 16));
            empty.setForeground(Color.LIGHT_GRAY);
            empty.setBounds(0, 50, 632, 30);
            skinGrid.add(empty);
            skinGrid.setPreferredSize(new Dimension(632, 100));
        } else {
            int rows   = (int) Math.ceil((double) unlocked.size() / COLS);
            int gridH  = rows * CELL_H + 10;
            int startX = (632 - COLS * CELL_W) / 2;

            skinGrid.setPreferredSize(new Dimension(632, gridH));

            for (int i = 0; i < unlocked.size(); i++) {
                Skin skin = unlocked.get(i);
                Color rColor = rarityColor(skin.getRarity());
                int x = startX + (i % COLS) * CELL_W;
                int y = (i / COLS) * CELL_H + 5;

                // ── Card với nền tối, không bị chìm vào background ────────
                JPanel card = new JPanel(null) {
                    @Override
                    protected void paintComponent(Graphics g) {
                        Graphics2D g2 = (Graphics2D) g.create();
                        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                                RenderingHints.VALUE_ANTIALIAS_ON);
                        // Nền gradient tối
                        GradientPaint gp = new GradientPaint(
                            0, 0,          new Color(15, 15, 35, 230),
                            0, getHeight(), new Color(35, 10, 55, 230));
                        g2.setPaint(gp);
                        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 12, 12);
                        // Viền rarity
                        g2.setColor(rColor);
                        g2.setStroke(new BasicStroke(2f));
                        g2.drawRoundRect(1, 1, getWidth() - 2, getHeight() - 2, 12, 12);
                        g2.dispose();
                    }
                };
                card.setOpaque(false);
                card.setBounds(x, y, CELL_W - 8, CELL_H - 8);

                // Ảnh preview
                ImageIcon original = new ImageIcon(skin.getPreviewPath());
                Image scaled = original.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
                JLabel imgLabel = new JLabel(new ImageIcon(scaled));
                imgLabel.setBounds((CELL_W - 8 - 70) / 2, 8, 70, 70);
                card.add(imgLabel);

                // Tên
                JLabel nameLabel = new JLabel(skin.getName(), SwingConstants.CENTER);
                nameLabel.setFont(new Font("Consolas", Font.BOLD, 11));
                nameLabel.setForeground(rColor);
                nameLabel.setBounds(0, 82, CELL_W - 8, 18);
                card.add(nameLabel);

                // Rarity badge
                JLabel rarityLabel = new JLabel(skin.getRarity().name(), SwingConstants.CENTER);
                rarityLabel.setFont(new Font("Consolas", Font.PLAIN, 10));
                rarityLabel.setForeground(rColor);
                rarityLabel.setBounds(0, 100, CELL_W - 8, 16);
                card.add(rarityLabel);

                // Nút Equip
                JButton equipBtn = new JButton("Equip");
                equipBtn.setFont(new Font("Consolas", Font.BOLD, 11));
                equipBtn.setForeground(Color.WHITE);
                equipBtn.setBackground(new Color(30, 30, 80));
                equipBtn.setBorder(BorderFactory.createLineBorder(rColor, 1));
                equipBtn.setBounds(18, 118, 88, 24);
                final String folder      = skin.getFolderName();
                final String displayName = skin.getName();
                equipBtn.addActionListener(e -> {
                    skinManager.selectSkin(folder);
                    equipBtn.setText("Equiped");
                    selectedLabel.setText("Using: " + displayName);
                });
                card.add(equipBtn);

                skinGrid.add(card);
            }
        }

        skinGrid.revalidate();
        skinGrid.repaint();
    }

    private Color rarityColor(Skin.Rarity rarity) {
        return switch (rarity) {
            case EPIC   -> new Color(163, 53, 238);
            case RARE   -> new Color(0, 150, 255);
            case COMMON -> new Color(160, 160, 160);
        };
    }
}