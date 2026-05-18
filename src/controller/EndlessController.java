package controller;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

public class EndlessController extends MapController {
    public EndlessController( CardLayout cardLayout, JPanel map1Container){
        super(cardLayout, map1Container);
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