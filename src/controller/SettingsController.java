package controller;
import view.*;
import utils.SoundManager;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

public class SettingsController implements ActionListener {
    private CardLayout cardLayout;
    private JPanel mainContainer;

    public SettingsController(CardLayout cardLayout, JPanel mainContainer) {
        this.cardLayout = cardLayout;
        this.mainContainer = mainContainer;
    }

    @Override
    public void actionPerformed(ActionEvent click) {
        String command = click.getActionCommand();
         switch (command){
            
            case "Back":
                cardLayout.show(mainContainer, "MainMenu"); 
                break;
            case "Sound":
                utils.GameConfig.isSoundOn = !utils.GameConfig.isSoundOn; 
                if (utils.GameConfig.isSoundOn) {
                    SoundManager.playBGM("src/assets/Sound/sound.wav");
                    System.out.println("Trang thai: DA MO AM THANH");
                } else {
                    SoundManager.stopBGM();
                    System.out.println("Trang thai: DA TAT AM THANH");
            }
                break;
            default:
                System.out.println("chua set tinh nang cho nut nay");
                break;
        }
    }
}