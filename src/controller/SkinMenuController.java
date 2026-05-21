package controller;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import view.SkinMenuPanel;

public class SkinMenuController implements ActionListener {
    private CardLayout cardLayout;
    private JPanel mainContainer;
    private SkinMenuPanel skinMenuPanel; // ref để gọi refresh()

    public SkinMenuController(CardLayout cardLayout, JPanel mainContainer) {
        this.cardLayout    = cardLayout;
        this.mainContainer = mainContainer;
    }
    public void setSkinMenuPanel(SkinMenuPanel panel) {
        this.skinMenuPanel = panel;
    }

    @Override
    public void actionPerformed(ActionEvent click) {
        String command = click.getActionCommand();
        switch (command) {
            case "Back" -> cardLayout.show(mainContainer, "MainMenu");
            default -> System.out.println("chua set tinh nang cho nut nay: " + command);
        }
    }
}