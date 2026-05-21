package game;
import controller.*;
public class GameStateListener {
    private MapController mapController;
    public GameStateListener(MapController mapController){
        this.mapController=mapController;
    }
    public void onGameOver(int finalScore){
        mapController.showLoseScreen(); 
    }
    public void onGameWon(int finalScore){
        mapController.showWinScreen(); 
    }
}