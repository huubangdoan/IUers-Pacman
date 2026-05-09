import javax.swing.JFrame;
import javax.swing.SwingUtilities;
public class SnakeRunner {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("IUers-s-Pacman");
            frame.setDefaultCloseOperation(3);
            frame.add(new SnakeMap());
            frame.pack();
            frame.setResizable(false);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
