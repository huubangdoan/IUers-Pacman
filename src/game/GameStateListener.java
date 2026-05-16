package game;

public interface GameStateListener {
    void onGameOver(int finalScore);
    void onGameWon(int finalScore);
}