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
    public static boolean isRiver(model.ChessboardPoint point) {
        // iDefine the conditions for a point to be considered as a river
        return point.equals(Constant.RIVER_1)
                || point.equals(Constant.RIVER_2)
                || point.equals(Constant.RIVER_3)
                || point.equals(Constant.RIVER_4)
                || point.equals(Constant.RIVER_5)
                || point.equals(Constant.RIVER_6)
                || point.equals(Constant.RIVER_7)
                || point.equals(Constant.RIVER_8)
                || point.equals(Constant.RIVER_9)
                || point.equals(Constant.RIVER_10)
                || point.equals(Constant.RIVER_11)
                || point.equals(Constant.RIVER_12);
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
        model.ChessboardPoint temp = (model.ChessboardPoint) obj;
        return (temp.getRow() == this.row) && (temp.getCol() == this.col);
    }
    public static boolean isDen(ChessboardPoint point) {return point.equals(Constant.DEN_RED)||point.equals(Constant.DEN_BLUE);}
    @Override
    public String toString() {
        return "("+row + ","+col+") " + "on the chessboard is clicked!";
    }
}
