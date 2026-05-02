import javax.swing.SwingUtilities;
public class Main {
public static void main(String[] args){
    SwingUtilities.invokeLater(()->{
        GameFrame game = new GameFrame();
        game.setVisible(true);
    });
}}


















    /* 
    ImageIcon bgrgachaframe = new ImageIcon("C:\\Users\\Admin\\OOP_Lab\\IUers-s-Pacman\\Graphics\\fn1.png");
    ImageIcon maygacha = new ImageIcon("C:\\Users\\Admin\\OOP_Lab\\IUers-s-Pacman\\Graphics\\main.png");

    JFrame frame = new JFrame("Pac-Man: Cuoc chien Qua mong");
    frame.setSize(672, 672);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    frame.setLayout(null);

    JButton start = new JButton("Start");
    start.setBounds(236, 200, 200 ,50);
    frame.add(start);
    start.addActionListener(e -> {
        frame.dispose();
        JFrame mapframe = new JFrame("Lua chon map");
        mapframe.setSize(672, 672);
        mapframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mapframe.setLayout(null);

        JLabel title = new JLabel("Select a map:");
        title.setForeground(java.awt.Color.GREEN);
        title.setBounds(236, 80, 200, 50);
        mapframe.add(title);

        JButton map1 = new JButton("map....");
        map1.setBounds(136, 180, 400, 50);
        mapframe.add(map1);

        JButton map2 = new JButton("map....");
        map2.setBounds(136, 260, 400, 50);
        mapframe.add(map2);

        JButton map3 = new JButton("map....");
        map3.setBounds(136, 340, 400, 50);
        mapframe.add(map3);

        JButton endless = new JButton("Endless");
        endless.setBounds(136, 420, 400, 50);
        mapframe.add(endless);

        JButton back = new JButton("Back");
        back.setBounds(10, 10, 100, 30);
        mapframe.add(back);

        mapframe.setVisible(true);
    });

    JButton gacha = new JButton("Gacha");
    gacha.setBounds(236, 280, 200, 50);
    frame.add(gacha);
    gacha.addActionListener(e -> {
        frame.dispose();
        JFrame gachaframe = new JFrame("Vong quay nhan pham");
        gachaframe.setSize(672, 672);
        gachaframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gachaframe.setLayout(null);

        JLabel may = new JLabel(maygacha);
        may.setBounds(186, 100, 300, 300);
        gachaframe.add(may);

        JButton roll1 = new JButton("QUAY 1 LAN (100 coins)");
        roll1.setBounds(236, 430,200, 50);
        gachaframe.add(roll1);
       
        JButton roll2 = new JButton("QUAY 10 LAN (900 coins)");
        roll2.setBounds(236, 500,200, 50);
        gachaframe.add(roll2);

        JButton roll3 = new JButton("QUAY DAC BIET (THE VIP)");
        roll3.setBounds(236, 570,200, 50);
        gachaframe.add(roll3);

        JButton back = new JButton("Quay lai main menu");
        back.setBounds(10, 10, 100, 30);
        gachaframe.add(back);
        
        
        JLabel bground = new JLabel(bgrgachaframe);
        bground.setBounds(0, 0, 672, 672);
        gachaframe.add(bground);
        gachaframe.setVisible(true);
    });

    JButton skin = new JButton("Skin");
    skin.setBounds(236, 360, 200, 50);
    frame.add(skin);

    JButton quit = new JButton("Quit");
    quit.setBounds(236, 440, 200, 50);
    frame.add(quit);
    quit.addActionListener(e -> {
        System.exit(0);
    });
    frame.setVisible(true);
    */
