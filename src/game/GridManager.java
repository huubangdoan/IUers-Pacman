package game;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GridManager {
    private short[][] grid;
    private int gridRows;
    private int gridCols;
    private final ArrayList<Point> specialTiles = new ArrayList<>();
    private final ArrayList<Point> emptyTiles = new ArrayList<>();

    public GridManager(short[][] grid) {
        setGrid(grid);
    }

    public void setGrid(short[][] grid) {
        this.grid = grid;
        this.gridRows = grid.length;
        this.gridCols = grid[0].length;
        buildSpecialTileCache();
    }

    public void buildCaches() {
        emptyTiles.clear();
        specialTiles.clear();
        for (int r = 0; r < gridRows; r++) {
            for (int c = 0; c < gridCols; c++) {
                short tile = grid[r][c];
                if (tile == 7 || tile == 8 || tile == 9) {
                    specialTiles.add(new Point(c, r));
                } else if (tile == 0) {
                emptyTiles.add(new Point(c, r));
                }
            }
        }
    }
    public void buildSpecialTileCache() {
        buildCaches();
    }

    public boolean isWall(int x, int y) {
        int startCol = x >> 5,      endCol = (x + 31) >> 5;
        int startRow = y >> 5,      endRow = (y + 31) >> 5;
        for (int r = startRow; r <= endRow; r++) {
            for (int c = startCol; c <= endCol; c++) {
                if (r < 0 || r >= gridRows || c < 0 || c >= gridCols || grid[r][c] == 1)
                    return true;
            }
        }
        return false;
    }

    public Point findRandomEmptySpot(Random r, List<Point> occupied) {
        List<Point> candidates = new ArrayList<>(emptyTiles);
        Collections.shuffle(candidates, r);
        for (Point p : candidates) {
            if (!occupied.contains(p)) return p;
        }
        return emptyTiles.isEmpty() ? new Point(1, 1) : emptyTiles.get(0);
    }

    public short[][] getGrid() { return grid; }
    public int getGridRows() { return gridRows; }
    public int getGridCols() { return gridCols; }
    public ArrayList<Point> getSpecialTiles() { return specialTiles; }
    public List<Point> getEmptyTiles() {
        return Collections.unmodifiableList(emptyTiles);
    }
}