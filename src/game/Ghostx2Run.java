import javax.swing.JFrame;
import javax.swing.SwingUtilities;
public class Ghostx2Run {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("IUers-s-Pacman");
            frame.setDefaultCloseOperation(3);
            frame.add(new Ghostx2Map());
            frame.pack();
            frame.setResizable(false);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
