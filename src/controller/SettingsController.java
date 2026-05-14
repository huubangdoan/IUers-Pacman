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
            case "Slow":
                System.out.println("Da chon che do: Slow");
                utils.GameConfig.pacmanSpeed = 2;
                break;
            case "Normal":
                System.out.println("Da chon che do: Normal");
                utils.GameConfig.pacmanSpeed = 4;
                break;
            case "Fast":
                System.out.println("Da chon che do: Fast");
                utils.GameConfig.pacmanSpeed = 8;
                break;
            case "Back":
                cardLayout.show(mainContainer, "MainMenu"); 
                break;
            case "Sound":
                utils.GameConfig.isSoundOn = !utils.GameConfig.isSoundOn; 
                if (utils.GameConfig.isSoundOn) {
                    SoundManager.playBGM("src/assets/sounds/bgm.wav");
                    System.out.println("Trang thai: DA MO AM THANH");
                // Ghi chú: thêm lệnh đổi hình cái nút thành 'Loa đang phát' ở đây
                } else {
                    SoundManager.stopBGM();
                    System.out.println("Trang thai: DA TAT AM THANH");
                // Ghi chú: thêm lệnh đổi hình cái nút thành 'Loa bị gạch chéo' ở đây
            }
                break;
            default:
                System.out.println("chua set tinh nang cho nut nay");
                break;
        }
    }
}