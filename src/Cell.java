public class Cell {

    private Tile data;
    private int row;
    private int col;
    private boolean occupied;
    private boolean isCurrent;

    public Cell(Tile data, int row, int col){
        this.data = data;
        this.row = row;
        this.col = col;
        occupied = false;
    }

    public Cell(int row, int col){
        data = null;
        this.row = row;
        this.col = col;
        occupied = false;
        isCurrent = false;
    }

    public Tile getData() {
        return data;
    }

    public void setData(Tile data) {
        this.data = data;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    public boolean isCurrent() {
        return isCurrent;
    }

    public void setCurrent(boolean current) {
        isCurrent = current;
    }
}
