package model;

/**
 * This class represents positions on the checkerboard, such as (0, 0), (0, 7), and so on
 * Where, the upper left corner is (0, 0), the lower left corner is (7, 0), the upper right corner is (0, 7), and the lower right corner is (7, 7).
 */
public class ChessboardPoint {
    private final int row;
    private final int col;

    public ChessboardPoint(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
    public boolean isInRiver() {
        // iDefine the conditions for a point to be considered as a river
        ChessboardPoint ths;
        return (this.equals(Constant.RIVER_1) || this.equals(Constant.RIVER_2) || this.equals(Constant.RIVER_3)
                || this.equals(Constant.RIVER_4) || this.equals(Constant.RIVER_5) || this.equals(Constant.RIVER_6)
                || this.equals(Constant.RIVER_7) || this.equals(Constant.RIVER_8) || this.equals(Constant.RIVER_9)
                || this.equals(Constant.RIVER_10) || this.equals(Constant.RIVER_11) || this.equals(Constant.RIVER_12));
    }

    @Override
    public int hashCode() {
        return row + col;
    }

    @Override
    @SuppressWarnings("ALL")
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        ChessboardPoint temp = (ChessboardPoint) obj;
        return (temp.getRow() == this.row) && (temp.getCol() == this.col);
    }

    @Override
    public String toString() {
        return "("+row + ","+col+") " + "on the chessboard is clicked!";
    }
}
