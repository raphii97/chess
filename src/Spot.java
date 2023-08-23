public class Spot {
    private Piece piece;
    private int x;
    private int y;

    public Spot(int x, int y, Piece piece){
        this.setPiece(piece);
        this.x = x;
        this.y = y;
    }

    public Piece getPiece() {
        return this.piece;
    }

    public void setPiece(Piece p) {
        this.piece = p;
    }

    public void removePiece() {
        this.piece = new Piece(Player.NONE, Type.EMPTY);
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }
}
