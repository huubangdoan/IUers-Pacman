package game;

public class CollisionManager {

    public void checkAllCollisions(Map map, EntityManager em, SpawnManager sm, GridManager gm) {
        checkEntityCollisions(map, em, sm, gm);
        checkChaosTiles(map, em, gm);
        checkLive(map, em, gm);
    }

    public void checkEntityCollisions(Map map, EntityManager em, SpawnManager sm, GridManager gm) {
        PacMan player = em.getPlayer();
        final int px = player.getX();
        final int py = player.getY();
        boolean[] fruitEaten = {false};

        sm.getCollectables().removeIf(f -> {
            int dx = px - f.getX(), dy = py - f.getY();
            if (dx * dx + dy * dy < 256) {
                f.onCollected(player);
                if (f instanceof Apple)  { for (Ghost g : map.getGhosts()) g.setFrightened(true, 300); }
                if (f instanceof Chilli) { player.activateChilliPower(9000); }
                if (f instanceof Kiwi)   { player.activateKiwiDisguise(); }
                if (!(f instanceof LightPoint)) fruitEaten[0] = true;
                return true;
            }
            return false;
        });

        handleFruitLogic(map, em, gm);
        if (fruitEaten[0]) {
            sm.spawnOneFruit(gm);
        }
    }

    public void checkChaosTiles(Map map, EntityManager em, GridManager gm) {
        PacMan player = em.getPlayer();
        int row = player.getY() >> 5;
        int col = player.getX() >> 5;
        short[][] grid = gm.getGrid();

        if (row < 0 || row >= gm.getGridRows() || col < 0 || col >= gm.getGridCols()) return;
        short tile = grid[row][col];
        if (tile == 0 || tile == 1) return;

        if (tile == 7) {
            player.reverseDirection();
            grid[row][col] = 0;
            gm.getSpecialTiles().removeIf(p -> p.x == col && p.y == row);
        } else if (tile == 8) {
            handlePlayerDeath(map, em);
            grid[row][col] = 0;
            gm.getSpecialTiles().removeIf(p -> p.x == col && p.y == row);
        } else if (tile == 9) {
            player.activateWallHack(map);
            grid[row][col] = 0;
            gm.getSpecialTiles().removeIf(p -> p.x == col && p.y == row);
        }
    }

    public void checkLive(Map map, EntityManager em, GridManager gm) {
        PacMan player = em.getPlayer();
        final int px = player.getX();
        final int py = player.getY();
        for (Ghost g : em.getGhosts()) {
            int dx = px - g.getX(), dy = py - g.getY();
            if (dx * dx + dy * dy < 256) {
                if (!player.hasThorns() && !g.getIsFrighted() && !player.isDisguised()) {
                    handlePlayerDeath(map, em);
                    return;
                }
            }
        }
    }

    public void handleFruitLogic(Map map, EntityManager em, GridManager gm) {
        PacMan player = em.getPlayer();
        final int px = player.getX();
        final int py = player.getY();

        if (player.hasWatermelon()) {
            for (Ghost g : em.getGhosts()) {
                int dx = px - g.getX(), dy = py - g.getY();
                if (dx * dx + dy * dy < 9216) {
                    g.setFrozen(true, 180);
                    player.setHasWatermelon(false);
                    break;
                }
            }
        }
        for (Ghost g : em.getGhosts()) {
            if (player.hasThorns() || g.getIsFrighted()) {
                int dx = px - g.getX(), dy = py - g.getY();
                if (dx * dx + dy * dy < 256) {
                    g.respawnAtRandomLocation(gm.getGrid());
                    g.setFrightened(false, 0);
                    player.setHasThorns(false);
                    break;
                }
            }
        }
        if (player.isDragonMode()) {
            for (Ghost g : em.getGhosts()) {
                int dx = px - g.getX(), dy = py - g.getY();
                if (dx * dx + dy * dy < 4096) {
                    g.knockbackFrom(player, map);
                    g.setStunned(true, 90);
                    player.setDragonMode(false);
                    break;
                }
            }
        }
    }

    public void handlePlayerDeath(Map map, EntityManager em) {
        PacMan player = em.getPlayer();
        player.loseLife();
        if (player.getLives() <= 0) {
            map.getScoreManager().saveData(player.getScore());
            map.getTimer().stop();
        } else {
            player.setPosition(32, 32);
        }
    }
}