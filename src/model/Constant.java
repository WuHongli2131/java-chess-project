package model;

public enum Constant {
    CHESSBOARD_ROW_SIZE(9),CHESSBOARD_COL_SIZE(7);
    public static final ChessboardPoint DEN_BLUE = new ChessboardPoint(0, 3);
    public static final ChessboardPoint DEN_RED = new ChessboardPoint(8, 3);
    public static final ChessboardPoint TRAP_BLUE_1 = new ChessboardPoint(0, 2);
    public static final ChessboardPoint TRAP_BLUE_2 = new ChessboardPoint(0, 4);
    public static final ChessboardPoint TRAP_BLUE_3 = new ChessboardPoint(1, 5);
    public static final ChessboardPoint TRAP_RED_1 = new ChessboardPoint(8, 2);
    public static final ChessboardPoint TRAP_RED_2 = new ChessboardPoint(7, 3);
    public static final ChessboardPoint TRAP_RED_3 = new ChessboardPoint(8, 4);

    public static final ChessboardPoint RIVER_1 = new ChessboardPoint(3, 1);
    public static final ChessboardPoint RIVER_2 = new ChessboardPoint(3, 2);
    public static final ChessboardPoint RIVER_3 = new ChessboardPoint(3, 4);
    public static final ChessboardPoint RIVER_4 = new ChessboardPoint(3, 5);
    public static final ChessboardPoint RIVER_5 = new ChessboardPoint(4, 1);
    public static final ChessboardPoint RIVER_6 = new ChessboardPoint(4, 2);
    public static final ChessboardPoint RIVER_7 = new ChessboardPoint(4, 4);
    public static final ChessboardPoint RIVER_8 = new ChessboardPoint(4, 5);
    public static final ChessboardPoint RIVER_9 = new ChessboardPoint(5, 1);
    public static final ChessboardPoint RIVER_10 = new ChessboardPoint(5, 2);
    public static final ChessboardPoint RIVER_11 = new ChessboardPoint(5, 4);
    public static final ChessboardPoint RIVER_12 = new ChessboardPoint(5, 5);
    private final int num;
    Constant(int num){
        this.num = num;
    }

    public int getNum() {
        return num;
    }
}
