package game;

import javax.swing.Timer;

public class GameStateManager {
    public void checkGameStatus(PacMan player, SpawnManager sm, GameStateListener listener, Timer timer) {
        if (player.getLives() <= 0) {
            if (timer.isRunning()) {
                timer.stop();
            }
            if (listener != null) {
                listener.onGameOver(player.getScore());
            }
            return;
        }
        boolean noDotsLeft = true;
        for (Collectable c : sm.getCollectables()) {
            if (c instanceof LightPoint || c instanceof Fruit) {
                noDotsLeft = false;
                break;
            }
        }
        if (noDotsLeft) {
            timer.stop();
            if (listener != null) {
                listener.onGameWon(player.getScore());
            }
        }
    }
}