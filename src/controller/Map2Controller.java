package controller;
import view.*;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

public class Map2Controller implements ActionListener{
    private CardLayout cardLayout;
    private JPanel mainContainer;
    public Map2Controller( CardLayout cardLayout, JPanel map2Container){
        this.cardLayout = cardLayout;
        this.mainContainer = map2Container;
    }
    @Override
    public void actionPerformed(ActionEvent click) {
        String command = click.getActionCommand();
        switch (command){
            case "Back":
                cardLayout.show(mainContainer, "MapMenu");
                break;
            default:
                System.out.println("chua set tinh nang cho nut nay");
                break;
        }
    }
}