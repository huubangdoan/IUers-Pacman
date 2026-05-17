package game;

import gacha.SkinManager;
import java.awt.*;

public class ChaoTilesMap extends ClassicPacmanMap {
    public ChaoTilesMap(SkinManager skinManager, GameRenderer renderer, GameStateListener gameStateListener, 
                        Image wallImg, Image backGroundImg, GridManager gridManager,
                        EntityManager entityManager, SpawnManager spawnManager,
                        CollisionManager collisionManager, GameStateManager gameStateManager) {
        super(skinManager, renderer, gameStateListener, wallImg, backGroundImg, gridManager, 
              entityManager, spawnManager, collisionManager, gameStateManager);
    }
    // Hoàn toàn không vi phạm LSP vì không sửa đổi hay phá hủy bất cứ hành vi nào của lớp cha!
}