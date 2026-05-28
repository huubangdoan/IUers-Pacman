package game;

import gacha.SkinManager;
import java.awt.*;

public class ChaoTilesMap extends Map {
    public ChaoTilesMap(SkinManager skinManager, GameRenderer renderer, GameStateListener gameStateListener, 
                        Image wallImg, Image backGroundImg, GridManager gridManager,
                        EntityManager entityManager, SpawnManager spawnManager,
                        CollisionManager collisionManager, GameStateManager gameStateManager) {
        super(skinManager, renderer, gameStateListener, wallImg, backGroundImg, gridManager, 
              entityManager, spawnManager, collisionManager, gameStateManager);
    }
}