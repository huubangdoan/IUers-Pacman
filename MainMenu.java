import javax.swing.*;
public class MainMenu {
public static void main(String[] args){
    JFrame frame = new JFrame("Pac-Man: Cuoc chien Qua mong");
    frame.setSize(672, 672);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    frame.setLayout(null);

    JButton start = new JButton("Start");
    start.setBounds(236, 200, 200 ,50);
    frame.add(start);
    start.addActionListener(e -> {
        frame.dispose();
        JFrame map = new JFrame("Chon map - Chuan bi chien dau!");
        map.setSize(672, 672);
        map.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        map.setLayout(null);

        JButton map1 = new JButton("map....");
        map1.setBounds(136, 300, 400, 50);
        map.add(map1);
        map.setVisible(true);
    });

    JButton gacha = new JButton("Gacha");
    gacha.setBounds(236, 280, 200, 50);
    frame.add(gacha);

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
}
}
