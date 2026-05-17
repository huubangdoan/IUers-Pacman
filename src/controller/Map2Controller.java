package controller;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

public class Map2Controller extends MapController{
    public Map2Controller( CardLayout cardLayout, JPanel map2Container){
        super(cardLayout, map2Container);
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