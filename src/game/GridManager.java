package game;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GridManager {
    private short[][] grid;
    private int gridRows;
    private int gridCols;
    private final ArrayList<Point> specialTiles = new ArrayList<>();

    public GridManager(short[][] grid) {
        setGrid(grid);
    }

    public void setGrid(short[][] grid) {
        this.grid = grid;
        this.gridRows = grid.length;
        this.gridCols = grid[0].length;
        buildSpecialTileCache();
    }

    public void buildSpecialTileCache() {
        specialTiles.clear();
        for (int r = 0; r < gridRows; r++) {
            for (int c = 0; c < gridCols; c++) {
                if (grid[r][c] == 7 || grid[r][c] == 8 || grid[r][c] == 9) {
                    specialTiles.add(new Point(c, r));
                }
            }
        }
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
        while (true) {
            int row = r.nextInt(gridRows);
            int col = r.nextInt(gridCols);
            Point spot = new Point(col, row);
            if (grid[row][col] == 0 && !occupied.contains(spot)) return spot;
        }
    }

    public short[][] getGrid() { return grid; }
    public int getGridRows() { return gridRows; }
    public int getGridCols() { return gridCols; }
    public ArrayList<Point> getSpecialTiles() { return specialTiles; }
}