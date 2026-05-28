package game;

import javax.swing.*;
import java.awt.event.ActionEvent;
//Key Bindings
public class PacmanInput {
    
    public static void setupInput(JPanel gamePanel, PacMan player) {
        InputMap inputMap = gamePanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = gamePanel.getActionMap();
        String moveUp = "MOVE_UP";
        String moveRight = "MOVE_RIGHT";
        String moveDown = "MOVE_DOWN";
        String moveLeft = "MOVE_LEFT";
        inputMap.put(KeyStroke.getKeyStroke("UP"), moveUp);
        inputMap.put(KeyStroke.getKeyStroke("W"), moveUp);
        
        inputMap.put(KeyStroke.getKeyStroke("RIGHT"), moveRight);
        inputMap.put(KeyStroke.getKeyStroke("D"), moveRight);
        
        inputMap.put(KeyStroke.getKeyStroke("DOWN"), moveDown);
        inputMap.put(KeyStroke.getKeyStroke("S"), moveDown);
        
        inputMap.put(KeyStroke.getKeyStroke("LEFT"), moveLeft);
        inputMap.put(KeyStroke.getKeyStroke("A"), moveLeft);
        actionMap.put(moveUp, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                player.setNextDirection(0);
            }
        });
        actionMap.put(moveRight, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                player.setNextDirection(1);
            }
        });

        actionMap.put(moveDown, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                player.setNextDirection(2);
            }
        });

        actionMap.put(moveLeft, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                player.setNextDirection(3);
            }
        });
    }
}