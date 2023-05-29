package model;

import controller.GameController;
import view.ChessGameFrame;
import java.util.ArrayList;
import java.util.List;

/**
 * This class store the real chess information.
 * The Chessboard has 9*7 cells, and each cell has a position for chess
 */
public class Chessboard {
    private Cell[][] grid;
    private ArrayList<String> history = new ArrayList<>();


    public Chessboard() {
        this.grid =
                new Cell[Constant.CHESSBOARD_ROW_SIZE.getNum()][Constant.CHESSBOARD_COL_SIZE.getNum()];//19X19

        initGrid();
        initPieces();
        //initDenAndTraps(); // 添加该行代码进行巢穴和陷阱的初始化

    }

    private void initGrid() {
        for (int i = 0; i < Constant.CHESSBOARD_ROW_SIZE.getNum(); i++) {
            for (int j = 0; j < Constant.CHESSBOARD_COL_SIZE.getNum(); j++) {
                grid[i][j] = new Cell();
            }
        }
    }

    public void removeAllPieces() {
        for (int i = 0; i < Constant.CHESSBOARD_ROW_SIZE.getNum(); i++) {
            for (int j = 0; j < Constant.CHESSBOARD_COL_SIZE.getNum(); j++) {
                if (grid[i][j].getPiece() != null) {
                    grid[i][j].setPiece(null);
                }
            }
        }
    }

    public void initPieces() {
        grid[0][0].setPiece(new ChessPiece(PlayerColor.BLUE, "Lion", 7));
        grid[8][6].setPiece(new ChessPiece(PlayerColor.RED, "Lion", 7));
        grid[0][6].setPiece(new ChessPiece(PlayerColor.BLUE, "Tiger", 6));
        grid[8][0].setPiece(new ChessPiece(PlayerColor.RED, "Tiger", 6));
        grid[1][1].setPiece(new ChessPiece(PlayerColor.BLUE, "Dog", 3));
        grid[7][5].setPiece(new ChessPiece(PlayerColor.RED, "Dog", 3));
        grid[1][5].setPiece(new ChessPiece(PlayerColor.BLUE, "Cat", 2));
        grid[7][1].setPiece(new ChessPiece(PlayerColor.RED, "Cat", 2));
        grid[2][6].setPiece(new ChessPiece(PlayerColor.BLUE, "Elephant", 8));
        grid[6][0].setPiece(new ChessPiece(PlayerColor.RED, "Elephant", 8));
        grid[2][4].setPiece(new ChessPiece(PlayerColor.BLUE, "Wolf", 4));
        grid[6][2].setPiece(new ChessPiece(PlayerColor.RED, "Wolf", 4));
        grid[2][2].setPiece(new ChessPiece(PlayerColor.BLUE, "Leopard", 5));
        grid[6][4].setPiece(new ChessPiece(PlayerColor.RED, "Leopard", 5));
        grid[2][0].setPiece(new ChessPiece(PlayerColor.BLUE, "Rat", 1));
        grid[6][6].setPiece(new ChessPiece(PlayerColor.RED, "Rat", 1));
        grid[2][0].getPiece().setInRiver(true);
        grid[6][6].getPiece().setInRiver(true);
        // 设置老鼠可以进入河里
    }

    public void intiatePieces(List<String> lines) {
        for (int i = 0; i < Constant.CHESSBOARD_ROW_SIZE.getNum(); i++) {
            for (int j = 0; j < Constant.CHESSBOARD_COL_SIZE.getNum(); j++) {
                if (lines.get(i).charAt(j) == '1') {
                    grid[i][j].setPiece(new ChessPiece(PlayerColor.BLUE, "Rat", 1));
                    grid[i][j].getPiece().setInRiver(true);
                } else if (lines.get(i).charAt(j) == 'a') {
                    grid[i][j].setPiece(new ChessPiece(PlayerColor.RED, "Rat", 1));
                    grid[i][j].getPiece().setInRiver(true);
                } else if (lines.get(i).charAt(j) == '2') {
                    grid[i][j].setPiece(new ChessPiece(PlayerColor.BLUE, "Cat", 2));
                } else if (lines.get(i).charAt(j) == 'b') {
                    grid[i][j].setPiece(new ChessPiece(PlayerColor.RED, "Cat", 2));
                } else if (lines.get(i).charAt(j) == '3') {
                    grid[i][j].setPiece(new ChessPiece(PlayerColor.BLUE, "Dog", 3));
                } else if (lines.get(i).charAt(j) == 'c') {
                    grid[i][j].setPiece(new ChessPiece(PlayerColor.RED, "Dog", 3));
                } else if (lines.get(i).charAt(j) == '4') {
                    grid[i][j].setPiece(new ChessPiece(PlayerColor.BLUE, "Wolf", 4));
                } else if (lines.get(i).charAt(j) == 'd') {
                    grid[i][j].setPiece(new ChessPiece(PlayerColor.RED, "Wolf", 4));
                } else if (lines.get(i).charAt(j) == '5') {
                    grid[i][j].setPiece(new ChessPiece(PlayerColor.BLUE, "Leopard", 5));
                } else if (lines.get(i).charAt(j) == 'e') {
                    grid[i][j].setPiece(new ChessPiece(PlayerColor.RED, "Leopard", 5));
                } else if (lines.get(i).charAt(j) == '6') {
                    grid[i][j].setPiece(new ChessPiece(PlayerColor.BLUE, "Tiger", 6));
                } else if (lines.get(i).charAt(j) == 'f') {
                    grid[i][j].setPiece(new ChessPiece(PlayerColor.RED, "Tiger", 6));
                } else if (lines.get(i).charAt(j) == '7') {
                    grid[i][j].setPiece(new ChessPiece(PlayerColor.BLUE, "Lion", 7));
                } else if (lines.get(i).charAt(j) == 'g') {
                    grid[i][j].setPiece(new ChessPiece(PlayerColor.RED, "Lion", 7));
                } else if (lines.get(i).charAt(j) == '8') {
                    grid[i][j].setPiece(new ChessPiece(PlayerColor.BLUE, "Elephant", 8));
                } else if (lines.get(i).charAt(j) == 'h') {
                    grid[i][j].setPiece(new ChessPiece(PlayerColor.RED, "Elephant", 8));
                }
            }

        }

    }
//    private void initDenAndTraps() {
//        grid[Constant.DEN_BLUE.getRow()][Constant.DEN_BLUE.getCol()].setPiece(new ChessPiece(PlayerColor.BLUE, "Den", 0));
//        grid[Constant.DEN_RED.getRow()][Constant.DEN_RED.getCol()].setPiece(new ChessPiece(PlayerColor.RED, "Den", 0));
//
//        grid[Constant.TRAP_BLUE_1.getRow()][Constant.TRAP_BLUE_1.getCol()].setPiece(new ChessPiece(PlayerColor.BLUE, "Trap", 0));
//        grid[Constant.TRAP_BLUE_2.getRow()][Constant.TRAP_BLUE_2.getCol()].setPiece(new ChessPiece(PlayerColor.BLUE, "Trap", 0));
//        grid[Constant.TRAP_BLUE_3.getRow()][Constant.TRAP_BLUE_3.getCol()].setPiece(new ChessPiece(PlayerColor.BLUE, "Trap", 0));
//
//        grid[Constant.TRAP_RED_1.getRow()][Constant.TRAP_RED_1.getCol()].setPiece(new ChessPiece(PlayerColor.RED, "Trap", 0));
//        grid[Constant.TRAP_RED_2.getRow()][Constant.TRAP_RED_2.getCol()].setPiece(new ChessPiece(PlayerColor.RED, "Trap", 0));
//        grid[Constant.TRAP_RED_3.getRow()][Constant.TRAP_RED_3.getCol()].setPiece(new ChessPiece(PlayerColor.RED, "Trap", 0));
//    }
//   private void initRivers() {
//        ChessPiece riverPiece = new ChessPiece(PlayerColor.GREEN, "River", 0);
//        grid[Constant.RIVER_1.getRow()][Constant.RIVER_1.getCol()].setPiece(riverPiece);
//        grid[Constant.RIVER_2.getRow()][Constant.RIVER_2.getCol()].setPiece(riverPiece);
//        grid[Constant.RIVER_3.getRow()][Constant.RIVER_3.getCol()].setPiece(riverPiece);
//        grid[Constant.RIVER_4.getRow()][Constant.RIVER_4.getCol()].setPiece(riverPiece);
//        grid[Constant.RIVER_5.getRow()][Constant.RIVER_5.getCol()].setPiece(riverPiece);
//        grid[Constant.RIVER_6.getRow()][Constant.RIVER_6.getCol()].setPiece(riverPiece);
//        grid[Constant.RIVER_7.getRow()][Constant.RIVER_7.getCol()].setPiece(riverPiece);
//        grid[Constant.RIVER_8.getRow()][Constant.RIVER_8.getCol()].setPiece(riverPiece);
//        grid[Constant.RIVER_9.getRow()][Constant.RIVER_9.getCol()].setPiece(riverPiece);
//        grid[Constant.RIVER_10.getRow()][Constant.RIVER_10.getCol()].setPiece(riverPiece);
//        grid[Constant.RIVER_11.getRow()][Constant.RIVER_11.getCol()].setPiece(riverPiece);
//        grid[Constant.RIVER_12.getRow()][Constant.RIVER_12.getCol()].setPiece(riverPiece);
//    }


    public ChessPiece getChessPieceAt(ChessboardPoint point) {
        return getGridAt(point).getPiece();
    }

    private Cell getGridAt(ChessboardPoint point) {
        return grid[point.getRow()][point.getCol()];
    }

    private int calculateDistance(ChessboardPoint src, ChessboardPoint dest) {
        return Math.abs(src.getRow() - dest.getRow()) + Math.abs(src.getCol() - dest.getCol());
    }

    public ChessPiece removeChessPiece(ChessboardPoint point) {
        ChessPiece chessPiece = getChessPieceAt(point);
        getGridAt(point).removePiece();
        return chessPiece;
    }

    private void setChessPiece(ChessboardPoint point, ChessPiece chessPiece) {
        getGridAt(point).setPiece(chessPiece);//将第二个点覆盖到第一个点
    }


    //
    public void moveChessPiece(ChessboardPoint src, ChessboardPoint dest) {
        if (!this.isValidMove(src, dest)) {
            throw new IllegalArgumentException("Illegal chess move!");
        } else {
            ChessPiece movingPiece = this.getChessPiece(src);
            if (this.isTrap(dest)) {
                // Handle moving into a trap
                if ((movingPiece.getOwner() == PlayerColor.BLUE && isBlueTrap(dest)) || (movingPiece.getOwner() == PlayerColor.RED && isRedTrap(dest))) {
                    this.setChessPiece(dest, movingPiece);
                } else {
                    movingPiece.setRank(0);
                    this.setChessPiece(dest, movingPiece);
                }
            } else if (this.isTrap(src)) {
                // Handle moving out of a trap
                // Restore the piece's rank
                movingPiece.restoreRank();
                this.setChessPiece(dest, movingPiece);
            } else if (!ChessboardPoint.isRiver(src) && ChessboardPoint.isRiver(dest)) {
                this.setChessPiece(dest, movingPiece);
            } else if (movingPiece.getRank() == 6 || movingPiece.getRank() == 7) {
                // Check if the moving piece is a lion or tiger
                if (isJumpAcrossRiver(src, dest)) {
                    if (isRatInInterveningSquares(src, dest)) {
                        // Jumping move is blocked if a rat is present
                        return;
                    } else {
                        this.setChessPiece(dest, movingPiece);
                    }

                } else {
                    this.setChessPiece(dest, movingPiece);
                }
            } else {
                this.setChessPiece(dest, movingPiece);
            }

            String step = movingPiece.getName() + " from " + src.toString() + " to " + dest.toString();

            this.history.add(step);
            removeChessPiece(src);
        }
    }


    public void captureChessPiece(ChessboardPoint src, ChessboardPoint dest) {
        if (!isValidCapture(src, dest)) {
            throw new IllegalArgumentException("Illegal chess capture!");
        }
        ChessPiece srcPiece = getChessPieceAt(src);
        ChessPiece destPiece = getChessPieceAt(dest);
        if (srcPiece.canCapture(destPiece)) {
            removeChessPiece(dest);
            ChessPiece capturedPiece = removeChessPiece(src);
            this.setChessPiece(dest, capturedPiece);
            String step = srcPiece.getName() + " from " + src.toString() + " captured " + destPiece.getName() + " at " + dest.toString();
            history.add(step);
        } else {
            throw new IllegalArgumentException("Illegal chess capture!");
        }

    }


    public Cell[][] getGrid() {
        return grid;
    }

    public PlayerColor getChessPieceOwner(ChessboardPoint point) {
        return getGridAt(point).getPiece().getOwner();
    }

    public ChessPiece getChessPiece(ChessboardPoint point) {
        return getGridAt(point).getPiece();
    }

    public boolean isValidMove(ChessboardPoint src, ChessboardPoint dest) {
        if (getChessPieceAt(src) == null || getChessPieceAt(dest) != null) {
            return false;
        }
        if (ChessboardPoint.isDen(dest)) {
            ChessPiece movingPiece = getChessPieceAt(src);
            if ((movingPiece.getOwner() == PlayerColor.BLUE && isBlueDen(dest)) ||
                    (movingPiece.getOwner() == PlayerColor.RED && isRedDen(dest))) {
                return false; // Cannot move into own den
            }
        }

        ChessPiece movingPiece = getChessPieceAt(src);
        if (ChessboardPoint.isRiver(dest) && !movingPiece.isInRiver()) {
            return false; // If the destination position is in the river, but the piece cannot enter the river, return false
        }

        int distance = calculateDistance(src, dest);

        if (movingPiece.getRank() == 6 || movingPiece.getRank() == 7) {
            // Check if the moving piece is a lion or tiger
            if (isJumpAcrossRiver(src, dest)) {
                if (isRatInInterveningSquares(src, dest)) {
                    return false; // Jumping move is blocked if a rat is present
                } else {
                    return true; // Valid jumping move
                }
            } else {
                // Only allow distance of 1 when not jumping across the river
                return distance == 1;
            }
        } else {
            return distance == 1;
        }
    }

    public boolean isValidCapture(ChessboardPoint src, ChessboardPoint dest) {
        ChessPiece srcPiece = getChessPieceAt(src);
        ChessPiece destPiece = getChessPieceAt(dest);

        if (srcPiece == null || destPiece == null || srcPiece.getOwner() == destPiece.getOwner()) {
            return false;
        }

        if ((srcPiece.getRank() == 6 || srcPiece.getRank() == 7) && isJumpAcrossRiver(src, dest)) {
            if (isRatInInterveningSquares(src, dest)) {
                return false; // If there is a rat in the intervening squares, return false
            }
            return destPiece.getRank() <= srcPiece.getRank(); // Can capture if the destination piece has equal or lower rank
        }

        return calculateDistance(src, dest) == 1;
    }

    private boolean isBlueDen(ChessboardPoint point) {
        return point.equals(Constant.DEN_BLUE);
    }

    private boolean isRedDen(ChessboardPoint point) {
        return point.equals(Constant.DEN_RED);
    }

    private boolean isTrap(ChessboardPoint point) {
        return point.equals(Constant.TRAP_BLUE_1) || point.equals(Constant.TRAP_BLUE_2) || point.equals(Constant.TRAP_BLUE_3) ||
                point.equals(Constant.TRAP_RED_1) || point.equals(Constant.TRAP_RED_2) || point.equals(Constant.TRAP_RED_3);
    }

    private boolean isBlueTrap(ChessboardPoint point) {
        return point.equals(Constant.TRAP_BLUE_1) || point.equals(Constant.TRAP_BLUE_2) || point.equals(Constant.TRAP_BLUE_3);
    }

    private boolean isRedTrap(ChessboardPoint point) {
        return point.equals(Constant.TRAP_RED_1) || point.equals(Constant.TRAP_RED_2) || point.equals(Constant.TRAP_RED_3);
    }

    private boolean isRatInInterveningSquares(ChessboardPoint src, ChessboardPoint dest) {
        int srcRow = src.getRow();
        int srcCol = src.getCol();
        int destRow = dest.getRow();
        int destCol = dest.getCol();

        // Check if the move is vertical
        if (srcCol == destCol) {
            int startRow = Math.min(srcRow, destRow);
            int endRow = Math.max(srcRow, destRow);
            for (int row = startRow + 1; row < endRow; row++) {
                ChessboardPoint currentPoint = new ChessboardPoint(row, srcCol);
                if (this.getChessPiece(currentPoint) != null && this.getChessPiece(currentPoint).getRank() == 1) {
                    return true; // Rat found in intervening squares
                }
            }
        }
        // Check if the move is horizontal
        else if (srcRow == destRow) {
            int startCol = Math.min(srcCol, destCol);
            int endCol = Math.max(srcCol, destCol);
            for (int col = startCol + 1; col < endCol; col++) {
                ChessboardPoint currentPoint = new ChessboardPoint(srcRow, col);
                if (this.getChessPiece(currentPoint) != null && this.getChessPiece(currentPoint).getRank() == 1) {
                    return true; // Rat found in intervening squares
                }
            }
        }

        return false; // No rat found in intervening squares
    }

    private boolean isJumpAcrossRiver(ChessboardPoint src, ChessboardPoint dest) {
        int srcRow = src.getRow();
        int srcCol = src.getCol();
        int destRow = dest.getRow();
        int destCol = dest.getCol();

        // Check if the move is vertical across the river
        if (srcCol == destCol) {
            int startRow = Math.min(srcRow, destRow);
            int endRow = Math.max(srcRow, destRow);

            // Check if the move crosses the river and the distance is 3
            if (startRow == 2 && endRow == 6 && Math.abs(destRow - srcRow) == 4) {
                return true; // Jumping across the river vertically
            }
        }
        // Check if the move is horizontal across the river
        else if (srcRow == destRow) {
            int startCol = Math.min(srcCol, destCol);
            int endCol = Math.max(srcCol, destCol);

            // Check if the move crosses the river and the distance is 2
            if ((srcRow == 3 || srcRow == 4 || srcRow == 5) && ((startCol == 0 && endCol == 3) || (startCol == 3 && endCol == 6)) && Math.abs(destCol - srcCol) == 3) {
                return true; // Jumping across the river horizontally
            }
        }

        return false; // Not jumping across the river
    }



    /*private ChessPiece[][] chessboard;
    public List<ChessboardPoint> getChessPiecesByOwner(PlayerColor owner) {
        List<ChessboardPoint> chessPieces = new ArrayList<>();
        for (int row = 0; row < Constant.CHESSBOARD_ROW_SIZE.getNum(); row++) {
            for (int col = 0; col < Constant.CHESSBOARD_COL_SIZE.getNum(); col++) {
                ChessPiece chessPiece = chessboard[row][col];
                if (chessPiece != null && chessPiece.getOwner() == owner) {
                    chessPieces.add(new ChessboardPoint(row, col));
                }
            }
        }
        return chessPieces;
    }*/


    public ArrayList<String> getHistory() {
        return history;
    }

}
