package utils;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UIUtils {
    public static void makeButtonTransparent(JButton button) {
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
    }
    
    public static void setupZoomEffect(JButton button, ImageIcon icon, int originalW, int originalH) {
        int zoomW = originalW + 10;
        int zoomH = originalH + 6;
        
        ImageIcon zoomedIcon = new ImageIcon(icon.getImage().getScaledInstance(zoomW, zoomH, Image.SCALE_SMOOTH));

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setIcon(zoomedIcon);
                button.setBounds(button.getX() - 5, button.getY() - 3, zoomW, zoomH);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setIcon(icon);
                button.setBounds(button.getX() + 5, button.getY() + 3, originalW, originalH);
            }
        });
    }

    
}