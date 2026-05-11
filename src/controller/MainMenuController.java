package controller;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import view.SkinMenuPanel;

public class MainMenuController implements ActionListener {
    private CardLayout cardLayout;
    private JPanel mainContainer;
    private SkinMenuPanel skinMenuPanel;

    public MainMenuController(CardLayout cardLayout, JPanel mainContainer) {
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
            case "Start" -> cardLayout.show(mainContainer, "MapMenu");
            case "Gacha" -> cardLayout.show(mainContainer, "GachaMenu");
            case "Skin"  -> {
                if (skinMenuPanel != null) skinMenuPanel.refresh();
                cardLayout.show(mainContainer, "SkinMenu");
            }
            case "Quit"  -> System.exit(0);
            default      -> System.out.println("chua set tinh nang cho nut nay: " + command);
        }
    }
}