package view;
import controller.Map2Controller;
import gacha.SkinManager;
import game.*;

import java.awt.*;
public class Map2Panel extends MapPanel {
    private final FogOfWar fogEffect;
    public Map2Panel(Map2Controller map2controller, SkinManager skinManager, 
        GameRenderer renderer, 
        GameStateListener gameStateListener, 
        Image wallImg, 
        Image backGroundImg,  FogOfWar fogEffect,
        GridManager gridManager,
        EntityManager entityManager,
        SpawnManager spawnManager,
        CollisionManager collisionManager,
        GameStateManager gameStateManager,
        String musicPath) {
        super(map2controller, skinManager,
            renderer,
            gameStateListener,
            wallImg,
            backGroundImg,
            gridManager,
            entityManager,
            spawnManager,
            collisionManager,
            gameStateManager, 
            musicPath);
        this.fogEffect=fogEffect;
    }
    @Override
    public Map setMap(SkinManager skinManager, 
        GameRenderer renderer, 
        GameStateListener gameStateListener, 
        Image wallImg, 
        Image backGroundImg,
        GridManager gridManager,
        EntityManager entityManager,
        SpawnManager spawnManager,
        CollisionManager collisionManager,
        GameStateManager gameStateManager){
        return new InvisibleMap(skinManager,
            renderer,
            gameStateListener,
            wallImg,
            backGroundImg, fogEffect,
            gridManager,
            entityManager,
            spawnManager,
            collisionManager,
            gameStateManager);
    }
}