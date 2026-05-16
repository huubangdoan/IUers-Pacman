package view;

import controller.*;
import game.*;
import gacha.SkinManager;

public class Map1Panel extends MapPanel {

    public Map1Panel(MapController map1controller, SkinManager skinManager, GameRenderer renderer, short[][] grid) {
        super(map1controller, skinManager, renderer, grid);
    }
}