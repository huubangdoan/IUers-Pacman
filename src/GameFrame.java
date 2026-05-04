import javax.swing.*;
import java.awt.*;
public class GameFrame extends JFrame{
    private CardLayout cardLayout;
    private JPanel mainContainer;
    private MainMenuController controller;
    private MapMenuController mapcontroller;
    private GachaMenuController gachacontroller;
    private SkinMenuController skincontroller;

    public GameFrame(){
        setTitle("Pac-Man: Multiverse");
        getContentPane().setPreferredSize(new Dimension(672, 672));
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        mainContainer = new JPanel(cardLayout);

        controller = new MainMenuController(cardLayout, mainContainer);
        mapcontroller = new MapMenuController(cardLayout, mainContainer);
        gachacontroller = new GachaMenuController(cardLayout, mainContainer);
        skincontroller = new SkinMenuController(cardLayout, mainContainer);

       mainContainer.add(new MainMenuPanel(controller), "MainMenu");
        mainContainer.add(new GachaMenuPanel(gachacontroller), "GachaMenu");
        mainContainer.add(new SkinMenuPanel(skincontroller), "SkinMenu");
        mainContainer.add(new MapMenuPanel(mapcontroller), "MapMenu");

        add(mainContainer);
        cardLayout.show(mainContainer, "MainMenu");
    }
}
