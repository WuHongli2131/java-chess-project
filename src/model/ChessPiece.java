package model;


public class ChessPiece {
    // the owner of the chess
    private PlayerColor owner;

    // Elephant? Cat? Dog? ...
    private String name;
    private int rank;
    private boolean inRiver;


    public ChessPiece(PlayerColor owner, String name, int rank) {
        this.owner = owner;
        this.name = name;
        this.rank = rank;
        this.inRiver = false;
    }

    public boolean canCapture(ChessPiece target) {
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
    public int getRank(){ return rank;}
    public void setRank(int rank) {
        this.rank = rank;
    }
}
