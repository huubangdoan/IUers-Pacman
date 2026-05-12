import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PacmanInput extends KeyAdapter {
    private PacMan player;
    public PacmanInput(PacMan player) {
        this.player = player;
    }

   @Override
public void keyPressed(KeyEvent e) {

    int key = e.getKeyCode();

    boolean reverse = player.isReverseMode();

    if (!reverse) {

        if (key == KeyEvent.VK_UP)
            player.setNextDirection(0);

        else if (key == KeyEvent.VK_RIGHT)
            player.setNextDirection(1);

        else if (key == KeyEvent.VK_DOWN)
            player.setNextDirection(2);

        else if (key == KeyEvent.VK_LEFT)
            player.setNextDirection(3);

    } else {

        if (key == KeyEvent.VK_UP)
            player.setNextDirection(2);

        else if (key == KeyEvent.VK_RIGHT)
            player.setNextDirection(3);

        else if (key == KeyEvent.VK_DOWN)
            player.setNextDirection(0);

        else if (key == KeyEvent.VK_LEFT)
            player.setNextDirection(1);
    }
}
}