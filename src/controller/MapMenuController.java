package controller;
import view.*;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

public class MapMenuController implements ActionListener{
    private CardLayout cardLayout;
    private JPanel mapContainer;
    public MapMenuController(CardLayout cardLayout, JPanel mapContainer){
        this.cardLayout = cardLayout;
        this.mapContainer = mapContainer;
    }
    @Override
    public void actionPerformed(ActionEvent click){
        String command = click.getActionCommand();
        switch (command){
            case "Map 1":
                cardLayout.show(mapContainer, "Map1");
                break;
            case "Map 2":
                cardLayout.show(mapContainer, "Map3");
                break;
            case "Map 3":
                cardLayout.show(mapContainer, "Endless");
                break;
            case "Endless":
                cardLayout.show(mapContainer, "Map2");
                break;
            case "Back":
                cardLayout.show(mapContainer, "MainMenu");
                break;
            default:
                System.out.println("chua set tinh nang cho nut nay");
                break;
        }
    }

}
