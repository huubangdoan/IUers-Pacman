package game;
import gacha.*;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
public class GameRunner {
    public static void main(String[] args) {
        
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("IUers-s-Pacman");
            ScoreManager scoreManager= new ScoreManager();
            SkinManager skinManager = new SkinManager(scoreManager);
            GameAssets assets= new GameAssets(skinManager);
            GameRenderer renderer = new GameRenderer(assets);
            frame.setDefaultCloseOperation(3);
            frame.add(new ChaoTilesMap(skinManager, renderer, ChaoData.GRID));
            frame.pack();
            frame.setResizable(false);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
