package controller;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

public class MapController implements ActionListener{
    private CardLayout cardLayout;
    private JPanel mainContainer;
    public MapController( CardLayout cardLayout, JPanel mapContainer){
        this.cardLayout = cardLayout;
        this.mainContainer = mapContainer;
    }
    @Override
    public void actionPerformed(ActionEvent click) {
        
    }
}