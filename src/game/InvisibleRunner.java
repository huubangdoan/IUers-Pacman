package game;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import gacha.SkinManager;
public class InvisibleRunner {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("IUers-s-Pacman");
            frame.setDefaultCloseOperation(3);
             ScoreManager scoreManager= new ScoreManager();
            SkinManager skinManager = new SkinManager(scoreManager);
            GameAssets assets= new GameAssets(skinManager);
            GameRenderer renderer = new GameRenderer(assets);
            FogOfWar fogEffect = new FogOfWar(InvisibleMazeData.INITIAL_VISION_RADIUS);
            frame.add(new InvisibleMap(skinManager, renderer, fogEffect, InvisibleMazeData.GRID));
            frame.pack();
            frame.setResizable(false);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
