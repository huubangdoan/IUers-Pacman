package game;
import javax.swing.Timer;
import utils.*;

public class GameStateManager {
    public void checkGameStatus(PacMan player, SpawnManager sm, GameStateListener listener, Timer timer) {
        if (player.getLives() <= 0) {
            if (timer.isRunning()) {
                timer.stop();
                SoundManager.stopBGM();
            }
            if (listener != null) {
                listener.onGameOver(player.getScore());
            }
            return;
        }
        boolean noDotsLeft = true;
        for (Collectable c : sm.getCollectables()) {
            if (c instanceof LightPoint) {
                noDotsLeft = false;
                break;
            }
        }
        if (noDotsLeft) {
            timer.stop();
            SoundManager.stopBGM();
            if (listener != null) {
                listener.onGameWon(player.getScore());
            }
        }
    }
}