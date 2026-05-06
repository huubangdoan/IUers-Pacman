import java.awt.*;
import java.io.File;
import javax.swing.*;

public class GameAssets {
    public Image dotImg, appleImg, durianImg, watermelonImg, chilliImg, kiwiImg, dragonFruitImg;
    public Image ghostImg, blinkyImg, pinkyImg, inkyImg, clydeImg, frightenedImg;
    public Image[] pacmanRight, pacmanLeft, pacmanUp, pacmanDown;

    public GameAssets() {
        File assetsBase = new File(System.getProperty("user.dir"), "src/assets/Default Skin");
        String path = assetsBase.getAbsolutePath();

        // Load Pacman
        pacmanRight = loadFrames(assetsBase, "pacman-right");
        pacmanLeft = loadFrames(assetsBase, "pacman-left");
        pacmanUp = loadFrames(assetsBase, "pacman-up");
        pacmanDown = loadFrames(assetsBase, "pacman-down");

        // Load Ghosts
        ghostImg = new ImageIcon(path + "/ghosts/blue_ghost.png").getImage();
        blinkyImg = new ImageIcon(path + "/ghosts/blinky.png").getImage();
        pinkyImg = new ImageIcon(path + "/ghosts/pinky.png").getImage();
        inkyImg = new ImageIcon(path + "/ghosts/inky.png").getImage();
        clydeImg = new ImageIcon(path + "/ghosts/clyde.png").getImage();
        frightenedImg = ghostImg;

        // Load Items
        dotImg = new ImageIcon(path + "/other/dot.png").getImage();
        appleImg = new ImageIcon(path + "/other/apple.png").getImage();
        durianImg = new ImageIcon("src/assets/New Fruit/Durian.png").getImage();
        watermelonImg = new ImageIcon("src/assets/New Fruit/watermelon.png").getImage();
        chilliImg = new ImageIcon("src/assets/New Fruit/chilli.png").getImage();
        kiwiImg = new ImageIcon("src/assets/New Fruit/Kiwi.png").getImage();
        dragonFruitImg = new ImageIcon("src/assets/New Fruit/DragonFruit.png").getImage();
    }

    private Image[] loadFrames(File base, String folder) {
        Image[] imgs = new Image[3];
        for (int i = 0; i < 3; i++) {
            imgs[i] = new ImageIcon(new File(new File(base, folder), (i + 1) + ".png").getAbsolutePath()).getImage();
        }
        return imgs;
    }
    
}