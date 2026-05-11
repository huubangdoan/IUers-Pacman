package controller;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

public class Map2Controller implements ActionListener{
    private CardLayout cardLayout;
    private JPanel mainContainer;
    public Map2Controller( CardLayout cardLayout, JPanel map2Container){
        this.cardLayout = cardLayout;
        this.mainContainer = map2Container;
    }
    @Override
    public void actionPerformed(ActionEvent click) {
        
    }
}