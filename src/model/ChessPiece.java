package model;

public class ChessPiece {
    // the owner of the chess
    private PlayerColor owner;

    // Elephant? Cat? Dog? ...
    private String name;
    private int rank;
    private boolean inRiver;
    private int originalRank;
    public ChessPiece(PlayerColor owner, String name, int rank) {
        this.owner = owner;
        this.name = name;
        this.rank = rank;
        this.originalRank = rank; // 保存初始rank值
        this.inRiver = false; // 默认情况下，棋子不能进入河流
    }
    public void restoreRank() {
        rank = originalRank;
    }
    public boolean canCapture(model.ChessPiece target) {
        int a = this.getRank();
        int b = target.getRank();
        if (a == 1 && b == 8) {
            return true;
        } else if (a >= b) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isInRiver() {
        return inRiver;
    }

    public void setInRiver(boolean inRiver) {
        this.inRiver = inRiver;
    }

    public String getName() {
        return name;
    }

    public PlayerColor getOwner() {
        return owner;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
}
