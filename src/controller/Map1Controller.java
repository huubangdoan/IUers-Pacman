package controller;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

public class Map1Controller implements ActionListener{
    private CardLayout cardLayout;
    private JPanel mainContainer;
    public Map1Controller( CardLayout cardLayout, JPanel map1Container){
        this.cardLayout = cardLayout;
        this.mainContainer = map1Container;
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