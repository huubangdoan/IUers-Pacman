package game;
import java.awt.Image;
import javax.swing.ImageIcon;
public class Skin {
    // [hướng][khung_hình]: 4 hướng, mỗi hướng 3 ảnh
    private Image[][] sprites = new Image[4][3]; 
    private String name;
    private boolean isUnlocked = false ;
    public Skin(String name, String[][] allPaths) {
        this.name = name;
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 3; col++) {
                this.sprites[row][col] = new ImageIcon(allPaths[row][col]).getImage();
            }
        }
    }
    public Image[] getImage(int direction) {
        return sprites[direction];
    }
    public String getName(){return name;}
    public void setUnlocked(boolean unlocked) { this.isUnlocked = unlocked; }
    public boolean isUnlocked() { return isUnlocked; }
}