package controller;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

import utils.SoundManager;

public class MapController implements ActionListener{
    CardLayout cardLayout;
    JPanel mainContainer;
    public MapController( CardLayout cardLayout, JPanel mapContainer){
        this.cardLayout = cardLayout;
        this.mainContainer = mapContainer;
    }
    @Override
    public void actionPerformed(ActionEvent click) {
        
        }
    public void showLoseScreen() {
        cardLayout.show(mainContainer, "Lose");
    }

    public void showWinScreen() {
        cardLayout.show(mainContainer, "Win");
    }
}