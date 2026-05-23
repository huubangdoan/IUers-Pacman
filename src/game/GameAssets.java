package game;

import gacha.SkinManager;
import java.awt.*;
import java.io.File;
import javax.swing.*;

public class GameAssets {
    public Image dotImg, appleImg, durianImg, watermelonImg, chilliImg, kiwiImg, dragonFruitImg;
    public static Image wall1Img, wall2Img, wall3Img;
    public static Image wallHackImg, deadImg, teleportImg;
    public static Image backGround1Img, backGround2Img;
    public Image ghostImg, blinkyImg, pinkyImg, inkyImg, clydeImg, frightenedImg;
    public Image[] pacmanRight, pacmanLeft, pacmanUp, pacmanDown;
    public static String sound1, gigaSound, ratdanceSound, chibichabaSound,menuSound;
    public GameAssets(SkinManager skinManager) {
        String skinFolder = (skinManager != null)
                ? skinManager.getSelectedSkinFolder()
                : SkinManager.DEFAULT_SKIN_FOLDER;
        load(skinFolder);
    }
    public GameAssets() {
        load(SkinManager.DEFAULT_SKIN_FOLDER);
    }

    private void load(String skinFolder) {
        File assetsBase = resolveBase(skinFolder);
        pacmanRight = loadFrames(assetsBase, "right");
        pacmanLeft  = loadFrames(assetsBase, "left");
        pacmanUp    = loadFrames(assetsBase, "up");
        pacmanDown  = loadFrames(assetsBase, "down");

        ghostImg     = new ImageIcon("src/assets/Default Skin/ghosts/blue_ghost.png").getImage();
        blinkyImg    = new ImageIcon("src/assets/Default Skin/ghosts/blinky.png").getImage();
        pinkyImg     = new ImageIcon("src/assets/Default Skin/ghosts/pinky.png").getImage();
        inkyImg      = new ImageIcon("src/assets/Default Skin/ghosts/inky.png").getImage();
        clydeImg     = new ImageIcon("src/assets/Default Skin/ghosts/clyde.png").getImage();
        frightenedImg = ghostImg;

        dotImg         = new ImageIcon("src/assets/Default Skin/other/dot.png").getImage();
        appleImg       = new ImageIcon("src/assets/Default Skin/other/apple.png").getImage();
        durianImg      = new ImageIcon("src/assets/New Fruit/Durian.png").getImage();
        watermelonImg  = new ImageIcon("src/assets/New Fruit/watermelon.png").getImage();
        chilliImg      = new ImageIcon("src/assets/New Fruit/chilli.png").getImage();
        kiwiImg        = new ImageIcon("src/assets/New Fruit/Kiwi.png").getImage();
        dragonFruitImg = new ImageIcon("src/assets/New Fruit/DragonFruit.png").getImage();

        wall1Img = new ImageIcon("src/assets/Menu Graphics/gachmap1.png").getImage();
        wall2Img = new ImageIcon("src/assets/Menu Graphics/gachmap2.png").getImage();
        wall3Img = new ImageIcon("src/assets/Menu Graphics/gachmap3.png").getImage();

        backGround1Img = new ImageIcon("src/assets/Menu Graphics/backGround1.png").getImage();
        backGround2Img = new ImageIcon("src/assets/Menu Graphics/backGround2.png").getImage();

        deadImg = new ImageIcon("src/assets/Chao Tiles/dead.png").getImage();
        teleportImg = new ImageIcon("src/assets/Chao Tiles/teleport.png").getImage();
        wallHackImg = new ImageIcon("src/assets/Chao Tiles/wallHack.png").getImage();

        sound1="src/assets/Sound/sound1.wav";
        gigaSound="src/assets/Sound/sound2.wav";
        chibichabaSound="src/assets/Sound/sound.wav";
        ratdanceSound="src/assets/Sound/sound3.wav";
        menuSound="src/assets/Sound/menuSound.wav";

    }
    private File resolveBase(String skinFolder) {
        if (SkinManager.DEFAULT_SKIN_FOLDER.equals(skinFolder)) {
            return new File("src/assets/" + skinFolder);
        }
        String[] rarityFolders = {"Rare Skin", "Epic Skin", "Common Skin"};
        for (String rarity : rarityFolders) {
            File candidate = new File("src/assets/Gacha Skin/" + rarity + "/" + skinFolder);
            if (candidate.exists()) return candidate;
        }
        return new File("src/assets/Default Skin");
    }

    private Image[] loadFrames(File base, String folder) {
        Image[] imgs = new Image[3];
        for (int i = 0; i < 3; i++) {
            File f = new File(new File(base, folder), (i + 1) + ".png");
            imgs[i] = new ImageIcon(f.getAbsolutePath()).getImage();
        }
        return imgs;
    }
}