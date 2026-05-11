package controller;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

public class Map3Controller implements ActionListener{
    private CardLayout cardLayout;
    private JPanel mainContainer;
    public Map3Controller( CardLayout cardLayout, JPanel map3Container){
        this.cardLayout = cardLayout;
        this.mainContainer = map3Container;
    }
    @Override
    public void actionPerformed(ActionEvent click) {
       
    }
}