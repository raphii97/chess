public class Piece {
    private Player player;
    private Type type;

    public Piece(Player player, Type type){
        this.player = player;
        this.type = type;
    }

    public boolean isMoveValid(int x1, int y1, int x2, int y2){
        return switch (this.type) {
            case PAWN -> isPawnMoveValid(x1, y1, x2, y2);
            case ROOK -> isRookMoveValid(x1, y1, x2, y2);
            case KNIGHT -> isKnightMoveValid(x1, y1, x2, y2);
            case BISHOP -> isBishopMoveValid(x1, y1, x2, y2);
            case QUEEN -> isQueenMoveValid(x1, y1, x2, y2);
            case KING -> isKingMoveValid(x1, y1, x2, y2);
        };
    }

    private boolean isKingMoveValid(int x1, int y1, int x2, int y2) {
        if (Math.abs(x2 - x1) <= 1 && Math.abs(y2 - y1) <= 1)
            return true;
        else
            return false;
    }

    private boolean isQueenMoveValid(int x1, int y1, int x2, int y2) {
        if (isBishopMoveValid(x1, y1, x2, y2) || isRookMoveValid(x1, y1, x2, y2))
            return true;
        else
            return false;
    }

    private boolean isBishopMoveValid(int x1, int y1, int x2, int y2) {
        if (Math.abs(x2 - x1) == Math.abs(y2 - y1))
            return true;
        else
            return false;
    }

    private boolean isKnightMoveValid(int x1, int y1, int x2, int y2) {
        if (Math.abs(x2 - x1) == 2 && Math.abs(y2 - y1) == 1)
            return true;
        else if (Math.abs(x2 - x1) == 1 && Math.abs(y2 - y1) == 2)
            return true;
        else
            return false;
    }

    private boolean isRookMoveValid(int x1, int y1, int x2, int y2) {
        if (x1 == x2 && y1 != y2)
            return true;
        else if (x1 != x2 && y1 == y2)
            return true;
        else
            return false;
    }

    private boolean isPawnMoveValid(int x1, int y1, int x2, int y2) {
        if (x2 - x1 == 0 && y2 - y1 == 2)
            return true;
        else if (x2 - x1 == 0 && y2 - y1 == 2 && y1 == 1)
            return true;
        else
            return false;
    }

    public Player getPlayer(){
        return this.player;
    }

    public Type getType(){
        return this.type;
    }
}
