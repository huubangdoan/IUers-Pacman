import javax.swing.*;
import java.awt.*;
public class GameFrame extends JFrame{
    private CardLayout cardLayout;
    private JPanel mainContainer;
    private MenuController controller;

    public GameFrame(){
        setTitle("Pac-Man: Multiverse");
        getContentPane().setPreferredSize(new Dimension(672, 672));
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        mainContainer = new JPanel(cardLayout);
        controller = new MenuController(cardLayout, mainContainer);
        
        JPanel mainMenuPanel = createMainMenu();
        /*JPanel mapPanel = createMapMenu();
        JPanel gachaPanel = createGachaMenu();*/

        mainContainer.add(mainMenuPanel, "MainMenu");
        /*mainContainer.add(mapPanel, "MapMenu");
        mainContainer.add(gachaPanel, "GachaMenu");*/
        add(mainContainer);
        cardLayout.show(mainContainer, "MainMenu");
    }
    private JPanel createMainMenu(){
        JPanel panel = new JPanel(null);
        
        ImageIcon originalStart = new ImageIcon("C:\\Users\\Admin\\OOP_Lab\\IUers-s-Pacman\\Graphics\\start.png");
        Image scaledStartImg = originalStart.getImage().getScaledInstance(210, 120, Image.SCALE_SMOOTH);
        ImageIcon startIcon = new ImageIcon(scaledStartImg);
        JButton start = new JButton(startIcon);
        start.setBounds(231, 190, 210, 120);
        start.setContentAreaFilled(false);
        start.setBorderPainted(false);
        start.setFocusPainted(false);
        start.setActionCommand("Start");
        start.addActionListener(controller);

        ImageIcon originalGacha = new ImageIcon("C:\\Users\\Admin\\OOP_Lab\\IUers-s-Pacman\\Graphics\\gacha.png");
        Image scaledGachaImg = originalGacha.getImage().getScaledInstance(210, 120, Image.SCALE_SMOOTH);
        ImageIcon gachaIcon = new ImageIcon(scaledGachaImg);
        JButton gacha = new JButton(gachaIcon);
        gacha.setBounds(231, 275, 210, 120);
        gacha.setContentAreaFilled(false);
        gacha.setBorderPainted(false);
        gacha.setFocusPainted(false);
        gacha.setActionCommand("Gacha");
        gacha.addActionListener(controller);
        
        ImageIcon originalSkin = new ImageIcon("C:\\Users\\Admin\\OOP_Lab\\IUers-s-Pacman\\Graphics\\skin.png");
        Image scaledSkinImg = originalSkin.getImage().getScaledInstance(210, 120, Image.SCALE_SMOOTH);
        ImageIcon skinIcon = new ImageIcon(scaledSkinImg);
        JButton skin = new JButton(skinIcon);
        skin.setBounds(231, 360, 210, 120);
        skin.setContentAreaFilled(false);
        skin.setBorderPainted(false);
        skin.setFocusPainted(false);
        skin.setActionCommand("Skin");
        skin.addActionListener(controller);
        
        ImageIcon originalQuit = new ImageIcon("C:\\Users\\Admin\\OOP_Lab\\IUers-s-Pacman\\Graphics\\quit.png");
        Image scaledQuitImg = originalQuit.getImage().getScaledInstance(210, 120, Image.SCALE_SMOOTH);
        ImageIcon quitIcon = new ImageIcon(scaledQuitImg);
        JButton quit = new JButton(quitIcon);
        quit.setBounds(231, 445, 210, 120);
        quit.setContentAreaFilled(false);
        quit.setBorderPainted(false);
        quit.setFocusPainted(false);
        quit.setActionCommand("Quit");
        quit.addActionListener(controller);

        panel.add(start);
        panel.add(gacha);
        panel.add(skin);
        panel.add(quit);

        ImageIcon bgIcon = new ImageIcon( "C:\\Users\\Admin\\OOP_Lab\\IUers-s-Pacman\\Graphics\\bgr1.png");
        JLabel background = new JLabel(bgIcon);
        background.setBounds(0, 0, 672, 672);
        panel.add(background);
        return panel;

    }

}
