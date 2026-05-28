package game;

import java.util.ArrayList;
import java.util.Random;

public class SpawnManager {
    private final ArrayList<Collectable> collectables = new ArrayList<>();
    private final Random rand = new Random();

    public void spawnInitialCollectables(GridManager gridManager) {
        collectables.clear();
        short[][] grid = gridManager.getGrid();
        for (int r = 0; r < gridManager.getGridRows(); r++) {
            for (int c = 0; c < gridManager.getGridCols(); c++) {
                if (grid[r][c] == 0) {
                    collectables.add(new LightPoint(c * 32, r * 32));
                }
            }
        }
        spawnOneFruit(gridManager);
    }

    public void spawnOneFruit(GridManager gridManager) {
        short[][] grid = gridManager.getGrid();
        while (true) {
            int r = rand.nextInt(gridManager.getGridRows());
            int c = rand.nextInt(gridManager.getGridCols());
            if (grid[r][c] == 0) {
                collectables.add(FruitFactory.createRandom(c, r, rand));
                return;
            }
        }
    }

    public ArrayList<Collectable> getCollectables() { return collectables; }
}