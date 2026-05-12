public class ChaosTileData {

    private int row;
    private int col;
    private int type;

    public ChaosTileData(int row, int col, int type) {
        this.row = row;
        this.col = col;
        this.type = type;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public int getType() {
        return type;
    }
}