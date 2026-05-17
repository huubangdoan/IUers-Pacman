import javax.swing.JFrame;
import javax.swing.SwingUtilities;
public class GameRunner {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("IUers-s-Pacman");
            frame.setDefaultCloseOperation(3);
            frame.add(new Map());
            frame.pack();
            frame.setResizable(false);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            JFrame frame1 = new JFrame("IUers-s-Pacman - X2 Ghost Mode");
            
        
        });
    }
}
