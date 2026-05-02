import javax.swing.SwingUtilities;
public class Main1 {
    public static void main (String[] args){
        SwingUtilities.invokeLater(()->{
            GameFrame game = new GameFrame();
            game.setVisible(true);
        });
    }
}