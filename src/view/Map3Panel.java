package view;

import controller.*;
import gacha.SkinManager;
import game.*;
import java.awt.*;
public class Map3Panel extends MapPanel {
    public Map3Panel(MapController map3controller, SkinManager skinManager, GameRenderer renderer, short[][] grid, GameStateListener gameStateListener,Image wallImg, Image backGroundImg) {
        super(map3controller, skinManager, renderer, grid, gameStateListener, wallImg, backGroundImg);
    }
    @Override
    public Map setMap(SkinManager skinManager, GameRenderer renderer, short[][] grid){
        return new ChaoTilesMap(skinManager, renderer, grid, getGameStateListener(), getWallImg(), getBackGroundImg());
    }
}