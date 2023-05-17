package model;

import java.util.ArrayList;

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
        initDenAndTraps(); // 添加该行代码进行巢穴和陷阱的初始化
        initRivers();
    }

    private void initGrid() {
        for (int i = 0; i < Constant.CHESSBOARD_ROW_SIZE.getNum(); i++) {
            for (int j = 0; j < Constant.CHESSBOARD_COL_SIZE.getNum(); j++) {
                grid[i][j] = new Cell();
            }
        }
    }

    private void initPieces() {
        grid[0][0].setPiece(new ChessPiece(PlayerColor.BLUE, "Lion",7));
        grid[8][6].setPiece(new ChessPiece(PlayerColor.RED, "Lion",7));
        grid[0][6].setPiece(new ChessPiece(PlayerColor.BLUE, "Tiger",6));
        grid[8][0].setPiece(new ChessPiece(PlayerColor.RED, "Tiger",6));
        grid[1][1].setPiece(new ChessPiece(PlayerColor.BLUE, "Dog",3));
        grid[7][5].setPiece(new ChessPiece(PlayerColor.RED, "Dog",3));
        grid[1][5].setPiece(new ChessPiece(PlayerColor.BLUE, "Cat",2));
        grid[7][1].setPiece(new ChessPiece(PlayerColor.RED, "Cat",2));
        grid[2][6].setPiece(new ChessPiece(PlayerColor.BLUE, "Elephant",8));
        grid[6][0].setPiece(new ChessPiece(PlayerColor.RED, "Elephant",8));
        grid[2][4].setPiece(new ChessPiece(PlayerColor.BLUE, "Wolf",4));
        grid[6][2].setPiece(new ChessPiece(PlayerColor.RED, "Wolf",4));
        grid[2][2].setPiece(new ChessPiece(PlayerColor.BLUE, "Leopard",5));
        grid[6][4].setPiece(new ChessPiece(PlayerColor.RED, "Leopard",5));
        grid[2][0].setPiece(new ChessPiece(PlayerColor.BLUE, "Rat",1));
        grid[6][6].setPiece(new ChessPiece(PlayerColor.RED, "Rat",1));
    }
    private void initDenAndTraps() {
        grid[Constant.DEN_BLUE.getRow()][Constant.DEN_BLUE.getCol()].setPiece(new ChessPiece(PlayerColor.BLUE, "Den", 0));
        grid[Constant.DEN_RED.getRow()][Constant.DEN_RED.getCol()].setPiece(new ChessPiece(PlayerColor.RED, "Den", 0));

        grid[Constant.TRAP_BLUE_1.getRow()][Constant.TRAP_BLUE_1.getCol()].setPiece(new ChessPiece(PlayerColor.BLUE, "Trap", 0));
        grid[Constant.TRAP_BLUE_2.getRow()][Constant.TRAP_BLUE_2.getCol()].setPiece(new ChessPiece(PlayerColor.BLUE, "Trap", 0));
        grid[Constant.TRAP_BLUE_3.getRow()][Constant.TRAP_BLUE_3.getCol()].setPiece(new ChessPiece(PlayerColor.BLUE, "Trap", 0));

        grid[Constant.TRAP_RED_1.getRow()][Constant.TRAP_RED_1.getCol()].setPiece(new ChessPiece(PlayerColor.RED, "Trap", 0));
        grid[Constant.TRAP_RED_2.getRow()][Constant.TRAP_RED_2.getCol()].setPiece(new ChessPiece(PlayerColor.RED, "Trap", 0));
        grid[Constant.TRAP_RED_3.getRow()][Constant.TRAP_RED_3.getCol()].setPiece(new ChessPiece(PlayerColor.RED, "Trap", 0));
    }
    private void initRivers() {
        ChessPiece riverPiece = new ChessPiece(PlayerColor.GREEN, "River", 0);
        grid[Constant.RIVER_1.getRow()][Constant.RIVER_1.getCol()].setPiece(riverPiece);
        grid[Constant.RIVER_2.getRow()][Constant.RIVER_2.getCol()].setPiece(riverPiece);
        grid[Constant.RIVER_3.getRow()][Constant.RIVER_3.getCol()].setPiece(riverPiece);
        grid[Constant.RIVER_4.getRow()][Constant.RIVER_4.getCol()].setPiece(riverPiece);
        grid[Constant.RIVER_5.getRow()][Constant.RIVER_5.getCol()].setPiece(riverPiece);
        grid[Constant.RIVER_6.getRow()][Constant.RIVER_6.getCol()].setPiece(riverPiece);
        grid[Constant.RIVER_7.getRow()][Constant.RIVER_7.getCol()].setPiece(riverPiece);
        grid[Constant.RIVER_8.getRow()][Constant.RIVER_8.getCol()].setPiece(riverPiece);
        grid[Constant.RIVER_9.getRow()][Constant.RIVER_9.getCol()].setPiece(riverPiece);
        grid[Constant.RIVER_10.getRow()][Constant.RIVER_10.getCol()].setPiece(riverPiece);
        grid[Constant.RIVER_11.getRow()][Constant.RIVER_11.getCol()].setPiece(riverPiece);
        grid[Constant.RIVER_12.getRow()][Constant.RIVER_12.getCol()].setPiece(riverPiece);
    }

    private ChessPiece getChessPieceAt(ChessboardPoint point) {
        return getGridAt(point).getPiece();
    }

    private Cell getGridAt(ChessboardPoint point) {
        return grid[point.getRow()][point.getCol()];
    }

    private int calculateDistance(ChessboardPoint src, ChessboardPoint dest) {
        return Math.abs(src.getRow() - dest.getRow()) + Math.abs(src.getCol() - dest.getCol());
    }

    private ChessPiece removeChessPiece(ChessboardPoint point) {
        ChessPiece chessPiece = getChessPieceAt(point);
        getGridAt(point).removePiece();
        return chessPiece;
    }

    private void setChessPiece(ChessboardPoint point, ChessPiece chessPiece) {
        getGridAt(point).setPiece(chessPiece);
    }

    public void moveChessPiece(ChessboardPoint src, ChessboardPoint dest) {
        if (!this.isValidMove(src, dest)) {
            throw new IllegalArgumentException("Illegal chess move!");
        } else {
            ChessPiece capturedPiece = this.removeChessPiece(dest);
            ChessPiece movingPiece = this.removeChessPiece(src);

            if (this.isTrap(dest)) {
                if (capturedPiece == null && movingPiece.getOwner() == this.getChessPieceOwner(dest)) {
                    this.setChessPiece(dest, movingPiece);
                } else {
                    movingPiece.setRank(0);
                    this.setChessPiece(dest, movingPiece);
                }
            } else if (dest.isInRiver()) {
                if (movingPiece.getRank() == 1) { // Assuming rank 1 represents the rat
                    movingPiece.setInRiver(true);
                    this.setChessPiece(dest, movingPiece);
                } else {
                    // If the chess piece is not a rat, it cannot move to the river
                    return;
                }
            } else if (src.isInRiver() && movingPiece.getRank() == 1 && capturedPiece != null && capturedPiece.getRank() == 1) {
                // Two rats in the river can capture each other
                this.setChessPiece(dest, movingPiece);
            } else if (capturedPiece != null && !src.isInRiver() && dest.isInRiver()) {
                // Cannot capture a piece from outside the river when moving into the river
                return;
            } else if (movingPiece.getRank() == 6 || movingPiece.getRank() == 7) {
                // Check if the moving piece is a lion or tiger
                if (isJumpAcrossRiver(src, dest)) {
                    if (isRatInInterveningSquares(src, dest)) {
                        // Jumping move is blocked if a rat is present
                        return;
                    } else if (capturedPiece != null && capturedPiece.getRank() <= movingPiece.getRank()) {
                        // Capture the enemy piece if it has equal or lower rank
                        this.setChessPiece(dest, movingPiece);
                    } else {
                        // Invalid jumping move if the destination square does not contain an enemy piece
                        return;
                    }
                } else {
                    // Invalid move if it's not a jump across the river
                    return;
                }
            } else {
                this.setChessPiece(dest, movingPiece);
            }

            String step = movingPiece.getName() + " from " + src.toString() + " to " + dest.toString();
            if (capturedPiece != null) {
                step = step + " captured " + capturedPiece.getName();
            }

            this.history.add(step);
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
            setChessPiece(dest, capturedPiece);
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
        if (isDen(dest) || isTrap(dest)) {
            return false;
        }
        return calculateDistance(src, dest) == 1;
    }


    public boolean isValidCapture(ChessboardPoint src, ChessboardPoint dest) {
        // TODO:Fix this method
        ChessPiece srcPiece = getChessPieceAt(src);
        ChessPiece destPiece = getChessPieceAt(dest);

        if (srcPiece == null || destPiece == null || srcPiece.getOwner() == destPiece.getOwner()) {
            return false;
        }

        int distance = calculateDistance(src, dest);
        if (distance != 1) {
            return false;
        }

        return true;}
    private boolean isDen(ChessboardPoint point) {
        return point.equals(Constant.DEN_BLUE) || point.equals(Constant.DEN_RED);
    }

    private boolean isTrap(ChessboardPoint point) {
        return point.equals(Constant.TRAP_BLUE_1) || point.equals(Constant.TRAP_BLUE_2) || point.equals(Constant.TRAP_BLUE_3) ||
                point.equals(Constant.TRAP_RED_1) || point.equals(Constant.TRAP_RED_2) || point.equals(Constant.TRAP_RED_3);
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

            // Check if the move crosses the river
            if (startRow < 5 && endRow > 4) {
                return true; // Jumping across the river vertically
            }
        }
        // Check if the move is horizontal across the river
        else if (srcRow == destRow) {
            int startCol = Math.min(srcCol, destCol);
            int endCol = Math.max(srcCol, destCol);

            // Check if the move crosses the river
            if (startCol < 3 && endCol > 2) {
                return true; // Jumping across the river horizontally
            }
        }

        return false; // Not jumping across the river
    }


    public ArrayList<String> getHistory() {
        return history;
    }

}
