import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

public class GachaMenuController implements ActionListener{
    private CardLayout cardLayout;
    private JPanel mainContainer;
    public GachaMenuController( CardLayout cardLayout, JPanel mainContainer){
        this.cardLayout = cardLayout;
        this.mainContainer = mainContainer;
    }
    @Override
    public void actionPerformed(ActionEvent click) {
        String command = click.getActionCommand();
        switch (command){
            case "1Roll":
                cardLayout.show(mainContainer, "1 Roll");
                break;
            case "10Rolls":
                cardLayout.show(mainContainer, "10 Rolls");
                break;
            case "100Rolls":
                cardLayout.show(mainContainer, "100 Rolls");
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
