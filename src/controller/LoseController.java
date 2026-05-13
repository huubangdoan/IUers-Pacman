package controller;
import view.*;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

public class LoseController implements ActionListener{
    private CardLayout cardLayout;
    private JPanel mapContainer;
    public LoseController(CardLayout cardLayout, JPanel mapContainer){
        this.cardLayout = cardLayout;
        this.mapContainer = mapContainer;
    }
    @Override
    public void actionPerformed(ActionEvent click){
        String command = click.getActionCommand();
        switch (command){
            case "Try again":
                cardLayout.show(mapContainer, "MapMenu");
                break;
            case "Main menu":
                cardLayout.show(mapContainer, "MainMenu");
                break;
            default:
                System.out.println("chua set tinh nang cho nut nay");
                break;
        }
    }

}
