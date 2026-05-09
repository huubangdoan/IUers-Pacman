import controller.*;
import view.*; 
import javax.swing.*;
import java.awt.*;
public class GameFrame extends JFrame{
    private CardLayout cardLayout;
    private JPanel mainContainer;
    private MainMenuController controller;
    private MapMenuController mapcontroller;
    private GachaMenuController gachacontroller;
    private SkinMenuController skincontroller;
    private Map1Controller map1controller;
    private Map2Controller map2controller;
    private Map3Controller map3controller;
    private EndlessController endlesscontroller;

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
        map1controller = new Map1Controller(cardLayout, mainContainer);
        map2controller = new Map2Controller(cardLayout, mainContainer);
        map3controller = new Map3Controller(cardLayout, mainContainer);
        endlesscontroller = new EndlessController(cardLayout, mainContainer);

        mainContainer.add(new MainMenuPanel(controller), "MainMenu");
        mainContainer.add(new GachaMenuPanel(gachacontroller), "GachaMenu");
        mainContainer.add(new SkinMenuPanel(skincontroller), "SkinMenu");
        mainContainer.add(new MapMenuPanel(mapcontroller), "MapMenu");
        mainContainer.add(new Map1Panel(map1controller), "Map1");
        mainContainer.add(new Map2Panel(map2controller), "Map2");
        mainContainer.add(new Map3Panel(map3controller), "Map3");
        mainContainer.add(new EndlessPanel(endlesscontroller), "Endless");
        add(mainContainer);
        cardLayout.show(mainContainer, "MainMenu");
    }
}
