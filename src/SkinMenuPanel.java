import javax.swing.*;
import java.awt.*;

public class SkinMenuPanel extends JPanel {
    public SkinMenuPanel(SkinMenuController skincontroller) {
        setLayout(null);

        ImageIcon bgIcon = new ImageIcon("C:\\Users\\Admin\\OOP_Lab\\IUers-s-Pacman\\Graphics\\mapmenu\\bgr.png");
        JLabel background = new JLabel(bgIcon);
        background.setBounds(0, 0, 672, 672);
        add(background);
        return;
    }
}