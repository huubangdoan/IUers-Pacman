package view;

import controller.*;
import game.*;
import gacha.SkinManager;
import java.awt.*;

public class Map1Panel extends MapPanel {

    public Map1Panel(MapController map1controller, SkinManager skinManager, GameRenderer renderer, short[][] grid, GameStateListener gameStateListener, Image wallImg, Image backGroundImg) {
        super(map1controller, skinManager, renderer, grid, gameStateListener,wallImg, backGroundImg);
    }
}