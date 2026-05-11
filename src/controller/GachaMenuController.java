package controller;

import gacha.SkinManager;
import gacha.SkinManager.RollResult;
import game.ScoreManager;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import view.GachaMenuPanel;
import view.GachaResultPanel;

public class GachaMenuController implements ActionListener {
    private CardLayout cardLayout;
    private JPanel mainContainer;
    private SkinManager skinManager;
    private GachaResultPanel resultPanel;
    private GachaMenuPanel gachaMenuPanel; // để refresh label điểm

    public GachaMenuController(CardLayout cardLayout, JPanel mainContainer,
                                SkinManager skinManager, GachaResultPanel resultPanel) {
        this.cardLayout    = cardLayout;
        this.mainContainer = mainContainer;
        this.skinManager   = skinManager;
        this.resultPanel   = resultPanel;
    }

    /** Inject sau khi tạo GachaMenuPanel để controller có thể refresh điểm */
    public void setGachaMenuPanel(GachaMenuPanel panel) {
        this.gachaMenuPanel = panel;
    }

    @Override
    public void actionPerformed(ActionEvent click) {
        String command = click.getActionCommand();
        ScoreManager sm = skinManager.getScoreManager();

        switch (command) {
            case "1Roll" -> {
                if (sm.getCumulativeScore() < SkinManager.ROLL_COST) {
                    JOptionPane.showMessageDialog(null,
                        "Không đủ điểm! Cần " + SkinManager.ROLL_COST + " điểm.",
                        "Thiếu điểm", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                RollResult result = skinManager.rollOne();
                if (result == null) return;

                List<RollResult> results = new ArrayList<>();
                results.add(result);
                resultPanel.showResults(results);
                if (gachaMenuPanel != null) gachaMenuPanel.refreshScore();
                cardLayout.show(mainContainer, "GachaResult");
            }
            case "10Rolls" -> {
                long cost = (long) SkinManager.ROLL_COST * 10;
                if (sm.getCumulativeScore() < cost) {
                    JOptionPane.showMessageDialog(null,
                        "Not enought score! need " + cost + " point.",
                        "insufficient", JOptionPane.WARNING_MESSAGE);
                        //nhờ Pchau thiết kế lại cái này
                    return;
                }
                List<RollResult> results = skinManager.rollTen();
                if (results == null) return;
                resultPanel.showResults(results);
                if (gachaMenuPanel != null) gachaMenuPanel.refreshScore();
                cardLayout.show(mainContainer, "GachaResult");
            }
            case "100Rolls" -> {
                long cost = (long) SkinManager.ROLL_COST * 100;
                if (sm.getCumulativeScore() < cost) {
                    JOptionPane.showMessageDialog(null,
                        "Not enought score! need " + cost + " score.",
                        "insufficient ", JOptionPane.WARNING_MESSAGE);
                        //thiết kế lại cái này nữa
                    return;
                }
                List<RollResult> results = skinManager.rollHundred();
                if (results == null) return;
                resultPanel.showResults(results);
                if (gachaMenuPanel != null) gachaMenuPanel.refreshScore();
                cardLayout.show(mainContainer, "GachaResult");
            }
            case "Back" -> cardLayout.show(mainContainer, "MainMenu");
        }
    }
}