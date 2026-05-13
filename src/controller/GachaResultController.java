package controller;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import view.SkinMenuPanel;

public class GachaResultController implements ActionListener {
    private CardLayout cardLayout;
    private JPanel mainContainer;
    private SkinMenuPanel skinMenuPanel;

    public GachaResultController(CardLayout cardLayout, JPanel mainContainer,
                                  SkinMenuPanel skinMenuPanel) {
        this.cardLayout    = cardLayout;
        this.mainContainer = mainContainer;
        this.skinMenuPanel = skinMenuPanel;
    }

    @Override
    public void actionPerformed(ActionEvent click) {
        switch (click.getActionCommand()) {
            case "Back"      -> cardLayout.show(mainContainer, "GachaMenu");
            case "ViewSkins" -> {
                skinMenuPanel.refresh();
                cardLayout.show(mainContainer, "SkinMenu");
            }
            default -> System.out.println("chua set tinh nang cho nut nay");
        }
    }
}