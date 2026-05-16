package view;

import controller.*;
import gacha.SkinManager;
import game.*;
public class Map3Panel extends MapPanel {
    public Map3Panel(MapController map3controller, SkinManager skinManager, GameRenderer renderer, short[][] grid) {
        super(map3controller, skinManager, renderer, grid);
    }
    @Override
    public Map setMap(SkinManager skinManager, GameRenderer renderer, short[][] grid){
        return new ChaoTilesMap(skinManager, renderer, grid);
    }
}