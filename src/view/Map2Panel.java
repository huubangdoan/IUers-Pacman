package view;
import controller.Map2Controller;
import gacha.SkinManager;
import java.awt.*;
import game.*;
public class Map2Panel extends MapPanel {
    public Map2Panel(Map2Controller map2controller, SkinManager skinManager, GameRenderer renderer, short[][] grid, Image wallImg, Image backGroundImg) {
        super(map2controller, skinManager, renderer, grid, wallImg, backGroundImg);
        
    }
    @Override
    public Map setMap(SkinManager skinManager, GameRenderer renderer, short[][] grid){
        return new SnakeMap(skinManager, renderer, grid, getWallImg(), getBackGroundImg());
    }
}