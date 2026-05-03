import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

public class MainMenuController implements ActionListener{
    private CardLayout cardLayout;
    private JPanel mainContainer;
    public MainMenuController( CardLayout cardLayout, JPanel mainContainer){
        this.cardLayout = cardLayout;
        this.mainContainer = mainContainer;
    }
    @Override
    public void actionPerformed(ActionEvent click) {
        String command = click.getActionCommand();
        switch (command){
            case "Start":
                cardLayout.show(mainContainer, "MapMenu");
                break;
            case "Gacha":
                cardLayout.show(mainContainer, "GachaMenu");
                break;
            case "Skin":
                cardLayout.show(mainContainer, "SkinMenu");
                break;
            case "Quit":
                System.exit(0);
                break;
            default:
                System.out.println("chua set tinh nang cho nut nay");
                break;
        }
    }
}
