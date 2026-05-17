package view;

import controller.*;
import game.*;
import gacha.SkinManager;
import java.awt.*;

public class Map1Panel extends MapPanel {

    public Map1Panel(MapController map1controller, SkinManager skinManager, 
        GameRenderer renderer, 
        GameStateListener gameStateListener, 
        Image wallImg, 
        Image backGroundImg,
        GridManager gridManager,
        EntityManager entityManager,
        SpawnManager spawnManager,
        CollisionManager collisionManager,
        GameStateManager gameStateManager) {
        super(map1controller, skinManager,
            renderer,
            gameStateListener,
            wallImg,
            backGroundImg,
            gridManager,
            entityManager,
            spawnManager,
            collisionManager,
            gameStateManager);
    }
}