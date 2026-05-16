package game;

public class TrackableMap extends Map {
    private GameStateListener gameStateListener;

    public TrackableMap(gacha.SkinManager skinManager) {
        super(skinManager); 
    }

    public void setGameStateListener(GameStateListener listener) {
        this.gameStateListener = listener;
    }

    @Override
    public void update() {
        super.update();

        // Check Thua:
        if (getPlayer().getLives() <= 0) {
            if (gameStateListener != null) {
                gameStateListener.onGameOver(getPlayer().getScore());
            }
        } 
        // Check Thắng (Ăn hết hạt sáng LightPoint):
        else {
            boolean noDotsLeft = true;
            for (Collectable c : getCollectable()) {
                if (c instanceof LightPoint) {
                    noDotsLeft = false; 
                    break;
                }
            }
            
            if (noDotsLeft) {
                getTimer().stop();
                if (gameStateListener != null) {
                    gameStateListener.onGameWon(getPlayer().getScore());
                }
            }
        }
    }
}