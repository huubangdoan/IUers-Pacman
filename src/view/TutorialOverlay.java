package view;

import javax.swing.*;
import java.awt.*;

public class TutorialOverlay extends JPanel {
    public TutorialOverlay(ImageIcon tutorialImg) {
        setLayout(null);
        setOpaque(false);
        setBounds(0, 0, 672, 672); 

        JLabel board = new JLabel(tutorialImg);
        board.setBounds(86, 136, 500, 400); 
        
        JButton closeBtn = new JButton("X");
        closeBtn.setBounds(550, 140, 45, 45); 
        closeBtn.addActionListener(e -> {
            this.setVisible(false); 
        });
        
        JPanel shadow = new JPanel();
        shadow.setBackground(new Color(0, 0, 0, 150)); 
        shadow.setBounds(0, 0, 672, 672);
        
        add(shadow);
        add(board);
        add(closeBtn);
        
        setVisible(false); 
    }
}