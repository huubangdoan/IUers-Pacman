package controller;
import view.*;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

public class SettingsController implements ActionListener {
    private CardLayout cardLayout;
    private JPanel settingsContainer;

    public SettingsController(CardLayout cardLayout, JPanel settingsContainer) {
        this.cardLayout = cardLayout;
        this.settingsContainer = settingsContainer;
    }

    @Override
    public void actionPerformed(ActionEvent click) {
        String command = click.getActionCommand();
         switch (command){
            case "Back":
                cardLayout.show(settingsContainer, "MainMenu"); 
                break;
            case "Easy":
                System.out.println("Da chon che do: Easy");
                utils.GameConfig.difficulty = 1;
                break;
            case "Normal":
                System.out.println("Da chon che do: Normal");
                utils.GameConfig.difficulty = 2;
                break;
            case "Hard":
                System.out.println("Da chon che do: Hard");
                utils.GameConfig.difficulty = 3;
                break;
            case "Sound":
                utils.GameConfig.isSoundOn = !utils.GameConfig.isSoundOn; 
                if (utils.GameConfig.isSoundOn) {
                    System.out.println("Trang thai: DA MO AM THANH");
                // Ghi chú: thêm lệnh đổi hình cái nút thành 'Loa đang phát' ở đây
                } else {
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