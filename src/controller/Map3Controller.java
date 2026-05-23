package controller;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

import utils.SoundManager;

public class Map3Controller extends MapController{
    public Map3Controller( CardLayout cardLayout, JPanel map3Container){
        super(cardLayout, map3Container);
    }
    @Override
    public void actionPerformed(ActionEvent click) {
       String command = click.getActionCommand();
         switch (command){
            case "Back":
                cardLayout.show(mainContainer, "MainMenu"); 
                break;
         }
    }
}