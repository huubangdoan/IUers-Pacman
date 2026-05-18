package game;

import gacha.SkinManager;
import java.awt.*;

public class InvisibleMap extends Map {
    private final FogOfWar fogEffect;

    public InvisibleMap(SkinManager skinManager, GameRenderer renderer, GameStateListener gameStateListener, 
                        Image wallImg, Image backGroundImg, FogOfWar fogEffect, GridManager gridManager,
                        EntityManager entityManager, SpawnManager spawnManager,
                        CollisionManager collisionManager, GameStateManager gameStateManager) {
        super(skinManager, renderer, gameStateListener, wallImg, backGroundImg, gridManager, 
              entityManager, spawnManager, collisionManager, gameStateManager);
        this.fogEffect = fogEffect;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g); // Vẽ map nền, Pacman và Ghost từ ClassicPacmanMap trước
        if (fogEffect != null && getPlayer() != null) {
            Graphics2D g2d = (Graphics2D) g;
            int px = getPlayer().getX() + 16;
            int py = getPlayer().getY() + 16;
            fogEffect.draw(g2d, px, py, getWidth(), getHeight());
        }
    }
}