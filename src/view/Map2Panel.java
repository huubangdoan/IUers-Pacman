package view;
import controller.Map2Controller;
import gacha.SkinManager;
import game.*;
public class Map2Panel extends MapPanel {
    public Map2Panel(Map2Controller map2controller, SkinManager skinManager, GameRenderer renderer, short[][] grid) {
        super(map2controller, skinManager, renderer, grid);
        setMap(new SnakeMap(skinManager, renderer, grid));
    }
}