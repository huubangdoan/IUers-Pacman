package controller;
import view.*;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

public class SkinMenuController implements ActionListener{
    private CardLayout cardLayout;
    private JPanel mainContainer;
    public SkinMenuController( CardLayout cardLayout, JPanel mainContainer){
        this.cardLayout = cardLayout;
        this.mainContainer = mainContainer;
    }
    @Override
    public void actionPerformed(ActionEvent click) {
        String command = click.getActionCommand();
        switch (command){
            case "Skin 1":
                cardLayout.show(mainContainer, "Skin1");
                break;
            case "Skin 2":
                cardLayout.show(mainContainer, "Skin2");
                break;
            case "Skin 3":
                cardLayout.show(mainContainer, "Skin3");
                break;
            case "Back":
                cardLayout.show(mainContainer, "MainMenu");
                break;
            default:
                System.out.println("chua set tinh nang cho nut nay");
                break;
        }
    }
}