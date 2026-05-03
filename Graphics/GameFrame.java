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

        JPanel mainMenuPanel = createMainMenu();
        JPanel mapPanel = createMapMenu();
        JPanel gachaPanel = createGachaMenu();
        JPanel skinPanel = createSkinMenu();

        mainContainer.add(mainMenuPanel, "MainMenu");
        mainContainer.add(mapPanel, "MapMenu");
        mainContainer.add(gachaPanel, "GachaMenu");
        mainContainer.add(skinPanel, "SkinMenu");

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

    private JPanel createMapMenu(){
        JPanel panel1 = new JPanel(null);

        ImageIcon originalMap1 = new ImageIcon("C:\\Users\\Admin\\OOP_Lab\\IUers-s-Pacman\\Graphics\\mapmenu\\map1.png");
        Image scaledMap1Img = originalMap1.getImage().getScaledInstance(210, 120, Image.SCALE_SMOOTH);
        ImageIcon map1Icon = new ImageIcon(scaledMap1Img);
        JButton map1 = new JButton(map1Icon);
        map1.setBounds(231, 148, 210, 120);
        map1.setContentAreaFilled(false);
        map1.setBorderPainted(false);
        map1.setFocusPainted(false);
        map1.setActionCommand("Map 1");
        map1.addActionListener(mapcontroller);

        ImageIcon originalMap2 = new ImageIcon("C:\\Users\\Admin\\OOP_Lab\\IUers-s-Pacman\\Graphics\\mapmenu\\map2.png");
        Image scaledMap2Img = originalMap2.getImage().getScaledInstance(210, 120, Image.SCALE_SMOOTH);
        ImageIcon map2Icon = new ImageIcon(scaledMap2Img);
        JButton map2 = new JButton(map2Icon);
        map2.setBounds(231, 233, 210, 120);
        map2.setContentAreaFilled(false);
        map2.setBorderPainted(false);
        map2.setFocusPainted(false);
        map2.setActionCommand("Map 2");
        map2.addActionListener(mapcontroller);

        ImageIcon originalMap3 = new ImageIcon("C:\\Users\\Admin\\OOP_Lab\\IUers-s-Pacman\\Graphics\\mapmenu\\map3.png");
        Image scaledMap3Img = originalMap3.getImage().getScaledInstance(210, 120, Image.SCALE_SMOOTH);
        ImageIcon map3Icon = new ImageIcon(scaledMap3Img);
        JButton map3 = new JButton(map3Icon);
        map3.setBounds(231, 318, 210, 120);
        map3.setContentAreaFilled(false);
        map3.setBorderPainted(false);
        map3.setFocusPainted(false);
        map3.setActionCommand("Map 3");
        map3.addActionListener(mapcontroller);

        ImageIcon originalEndless = new ImageIcon("C:\\Users\\Admin\\OOP_Lab\\IUers-s-Pacman\\Graphics\\mapmenu\\endless.png");
        Image scaledEndlessImg = originalEndless.getImage().getScaledInstance(210, 120, Image.SCALE_SMOOTH);
        ImageIcon endlessIcon = new ImageIcon(scaledEndlessImg);
        JButton endless = new JButton(endlessIcon);
        endless.setBounds(231, 403, 210, 120);
        endless.setContentAreaFilled(false);
        endless.setBorderPainted(false);
        endless.setFocusPainted(false);
        endless.setActionCommand("Endless");
        endless.addActionListener(mapcontroller);

        panel1.add(map1);
        panel1.add(map2);
        panel1.add(map3);
        panel1.add(endless);

        ImageIcon bgIcon = new ImageIcon("C:\\Users\\Admin\\OOP_Lab\\IUers-s-Pacman\\Graphics\\mapmenu\\bgr.png");
        JLabel background = new JLabel(bgIcon);
        background.setBounds(0, 0, 672, 672);
        panel1.add(background);
        return panel1;
    }
//GachaMenu chua chen photos
    private JPanel createGachaMenu(){
        JPanel panel2 = new JPanel(null);

        ImageIcon original1Roll = new ImageIcon("C:\\\\Users\\\\Admin\\\\OOP_Lab\\\\IUers-s-Pacman\\\\Graphics\\\\mapmenu\\\\endless.png");
        Image scaled1RollImg = original1Roll.getImage().getScaledInstance(210, 120,Image.SCALE_SMOOTH);
        ImageIcon roll1Icon = new ImageIcon(scaled1RollImg);
        JButton roll1 = new JButton(roll1Icon);
        roll1.setBounds(231, 190, 210, 120);
        roll1.setContentAreaFilled(false);
        roll1.setBorderPainted(false);
        roll1.setFocusPainted(false);
        roll1.setActionCommand("1RRoll");
        roll1.addActionListener(gachacontroller);

        ImageIcon original10Rolls = new ImageIcon("C:\\\\Users\\\\Admin\\\\OOP_Lab\\\\IUers-s-Pacman\\\\Graphics\\\\mapmenu\\\\endless.png");
        Image scaled10RollsImg = original10Rolls.getImage().getScaledInstance(210, 120,Image.SCALE_SMOOTH);
        ImageIcon rolls10Icon = new ImageIcon(scaled10RollsImg);
        JButton rolls10 = new JButton(rolls10Icon);
        rolls10.setBounds(231, 254, 210, 120);
        rolls10.setContentAreaFilled(false);
        rolls10.setBorderPainted(false);
        rolls10.setFocusPainted(false);
        rolls10.setActionCommand("10Rolls");
        rolls10.addActionListener(gachacontroller);

        ImageIcon original100Rolls = new ImageIcon("C:\\\\Users\\\\Admin\\\\OOP_Lab\\\\IUers-s-Pacman\\\\Graphics\\\\mapmenu\\\\endless.png");
        Image scaled100RollsImg = original100Rolls.getImage().getScaledInstance(210, 120,Image.SCALE_SMOOTH);
        ImageIcon rolls100Icon = new ImageIcon(scaled100RollsImg);
        JButton rolls100 = new JButton(rolls100Icon);
        rolls100.setBounds(231, 318, 210, 120);
        rolls100.setContentAreaFilled(false);
        rolls100.setBorderPainted(false);
        rolls100.setFocusPainted(false);
        rolls100.setActionCommand("100Rolls");
        rolls100.addActionListener(gachacontroller);

        ImageIcon originalBack = new ImageIcon("C:\\Users\\Admin\\OOP_Lab\\IUers-s-Pacman\\Graphics\\mapmenu\\endless.png");
        Image scaledBackImg = originalBack.getImage().getScaledInstance(210, 120, Image.SCALE_SMOOTH);
        ImageIcon backIcon = new ImageIcon(scaledBackImg);
        JButton back = new JButton(backIcon);
        back.setBounds(231, 403, 210, 120);
        back.setContentAreaFilled(false);
        back.setBorderPainted(false);
        back.setFocusPainted(false);
        back.setActionCommand("Back");
        back.addActionListener(gachacontroller);

        panel2.add(roll1);
        panel2.add(rolls10);
        panel2.add(rolls100);
        panel2.add(back);

        ImageIcon bgIcon = new ImageIcon("C:\\Users\\Admin\\OOP_Lab\\IUers-s-Pacman\\Graphics\\mapmenu\\bgr.png");
        JLabel background = new JLabel(bgIcon);
        background.setBounds(0, 0, 672, 672);
        panel2.add(background);
        return panel2;
    }

    private JPanel createSkinMenu(){
        JPanel panel3 = new JPanel(null);



        ImageIcon bgIcon = new ImageIcon("C:\\Users\\Admin\\OOP_Lab\\IUers-s-Pacman\\Graphics\\mapmenu\\bgr.png");
        JLabel background = new JLabel(bgIcon);
        background.setBounds(0, 0, 672, 672);
        panel3.add(background);
        return panel3;
    }
}
