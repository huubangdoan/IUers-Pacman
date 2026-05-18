package view;

import controller.*;
import gacha.SkinManager;
import game.*;
import java.awt.*;

public class EndlessPanel extends MapPanel {
    private final FogOfWar fogEffect;
    public EndlessPanel(MapController endlesscontroller, 
        SkinManager skinManager, GameRenderer renderer, GameStateListener gameStateListener, 
        Image wallImg, Image backGroundImg, FogOfWar fogEffect, GridManager gridManager,
        EntityManager entityManager, SpawnManager spawnManager,
        CollisionManager collisionManager, GameStateManager gameStateManager ){
        super(endlesscontroller, skinManager,
            renderer,
            gameStateListener,
            wallImg,
            backGroundImg,
            gridManager,
            entityManager,
            spawnManager,
            collisionManager,
            gameStateManager);
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