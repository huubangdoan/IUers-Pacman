package game;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EntityManager {
    private PacMan player;
    private final List<Ghost> ghosts = new ArrayList<>();
    private final Random rand = new Random();

    public void initEntities(GridManager gridManager) {
        List<Point> occupied = new ArrayList<>();
        Point pacmanSpot = gridManager.findRandomEmptySpot(rand, occupied);
        this.player = new PacMan(pacmanSpot.x * 32, pacmanSpot.y * 32, 2);
        occupied.add(pacmanSpot);

        String[] ghostTypes = {"blinky", "pinky", "inky", "clyde"};
        ghosts.clear();
        for (String type : ghostTypes) {
            Point spot = gridManager.findRandomEmptySpot(rand, occupied);
            ghosts.add(new Ghost(spot.x * 32, spot.y * 32, 2, type));
            occupied.add(spot);
        }
    }

    public void updateEntities(Map map) {
        if (!player.hasChilli()) {
            player.setSpeed(utils.GameConfig.pacmanSpeed);
        }
        player.move(map);
        player.updateAnimation();
        player.updatePowerup();
        player.updateDragon();

        for (Ghost g : ghosts) {
            g.move(map);
            g.updateFrightened();
        }
    }

    public PacMan getPlayer() { return player; }
    public List<Ghost> getGhosts() { return ghosts; }
}