public class Board {
    private Spot[][] board = new Spot[8][8];
    private final static String BOARD_LETTERS = "  a b c d e f g h";

    public Board(){
        init();
        printBoard();
    }

    private void init(){
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                board[i][j] = new Spot(j, i, new Piece(Player.NONE, Type.EMPTY));
            }
        }

        board[0][0] = new Spot(0, 0, new Piece(Player.BLACK, Type.ROOK));
        board[0][1] = new Spot(1, 0, new Piece(Player.BLACK, Type.KNIGHT));
        board[0][2] = new Spot(2, 0, new Piece(Player.BLACK, Type.BISHOP));
        board[0][3] = new Spot(3, 0, new Piece(Player.BLACK, Type.QUEEN));
        board[0][4] = new Spot(4, 0, new Piece(Player.BLACK, Type.KING));
        board[0][5] = new Spot(5, 0, new Piece(Player.BLACK, Type.BISHOP));
        board[0][6] = new Spot(6, 0, new Piece(Player.BLACK, Type.KNIGHT));
        board[0][7] = new Spot(7, 0, new Piece(Player.BLACK, Type.ROOK));

        for(int i = 0; i < 8; i++){
            board[1][i] = new Spot(i, 1, new Piece(Player.BLACK, Type.PAWN));
        }

        board[7][0] = new Spot(0, 7, new Piece(Player.WHITE, Type.ROOK));
        board[7][1] = new Spot(1, 7, new Piece(Player.WHITE, Type.KNIGHT));
        board[7][2] = new Spot(2, 7, new Piece(Player.WHITE, Type.BISHOP));
        board[7][3] = new Spot(3, 7, new Piece(Player.WHITE, Type.QUEEN));
        board[7][4] = new Spot(4, 7, new Piece(Player.WHITE, Type.KING));
        board[7][5] = new Spot(5, 7, new Piece(Player.WHITE, Type.BISHOP));
        board[7][6] = new Spot(6, 7, new Piece(Player.WHITE, Type.KNIGHT));
        board[7][7] = new Spot(7, 7, new Piece(Player.WHITE, Type.ROOK));

        for(int i = 0; i < 8; i++){
            board[6][i] = new Spot(i, 6, new Piece(Player.WHITE, Type.PAWN));
        }
    }

    public void printBoard(){
        System.out.println();
        System.out.println(BOARD_LETTERS);

        for(int i = 0; i < 8; i++){
            System.out.print((8 - i) + " ");

            for (int j = 0; j < 8; j++){
                    Piece piece = board[i][j].getPiece();
                    System.out.print(piece.getPlayer() == Player.WHITE ? piece.getType().toString().toLowerCase()  : piece.getType().toString().toUpperCase());
                    System.out.print(" ");
            }

            System.out.println(8 - i);
        }

        System.out.println(BOARD_LETTERS);
        System.out.println();
    }

    public Spot getSpot(int x, int y){
        return board[y][x];
    }

    private boolean isInvalidSpot(Spot spot){
        return spot.getX() < 0 || spot.getX() >= 8 || spot.getY() < 0 || spot.getY() >= 8;
    }

    public boolean isValidMove(Spot startSpot, Spot endSpot, Player currentPlayer){
        Piece piece = startSpot.getPiece();

        if (piece.getType() == Type.EMPTY || piece.getPlayer() != currentPlayer || isInvalidSpot(startSpot) || isInvalidSpot(endSpot) || startSpot == endSpot || endSpot.getPiece().getPlayer() == currentPlayer){
            return false;
        }

        return switch (piece.getType()) {
            case PAWN -> isValidPawnMove(startSpot, endSpot);
            case ROOK -> isValidRookMove(startSpot, endSpot);
            case KNIGHT -> isValidKnightMove(startSpot, endSpot);
            case BISHOP -> isValidBishopMove(startSpot, endSpot);
            case QUEEN -> isValidQueenMove(startSpot, endSpot);
            case KING -> isValidKingMove(startSpot, endSpot);
            case EMPTY -> false;
        };
    }

    private boolean isValidKingMove(Spot startSpot, Spot endSpot) {
        return Math.abs(startSpot.getX() - endSpot.getX()) <= 1 && Math.abs(startSpot.getY() - endSpot.getY()) <= 1;
    }

    private boolean isValidQueenMove(Spot startSpot, Spot endSpot) {
        return isValidBishopMove(startSpot, endSpot) || isValidRookMove(startSpot, endSpot);
    }

    private boolean isValidBishopMove(Spot startSpot, Spot endSpot) {
        if (Math.abs(startSpot.getX() - endSpot.getX()) != Math.abs(startSpot.getY() - endSpot.getY())){
            return false;
        }
        for (int i = 1; i < Math.abs(startSpot.getX() - endSpot.getX()); i++){
            if (board[Math.min(startSpot.getY(), endSpot.getY()) + i][Math.min(startSpot.getX(), endSpot.getX()) + i].getPiece().getType() != Type.EMPTY){
                return false;
            }
        }
        return true;
    }

    private boolean isValidKnightMove(Spot startSpot, Spot endSpot) {
        if (Math.abs(startSpot.getX() - endSpot.getX()) == 2 && Math.abs(startSpot.getY() - endSpot.getY()) == 1){
            return true;
        }
        if (Math.abs(startSpot.getX() - endSpot.getX()) == 1 && Math.abs(startSpot.getY() - endSpot.getY()) == 2){
            return true;
        }
        return false;
    }

    private boolean isValidRookMove(Spot startSpot, Spot endSpot) {
        if (startSpot.getX() == endSpot.getX()){
            for (int i = Math.min(startSpot.getY(), endSpot.getY()) + 1; i < Math.max(startSpot.getY(), endSpot.getY()); i++){
                if (board[i][startSpot.getX()].getPiece().getType() != Type.EMPTY){
                    return false;
                }
            }
            return true;
        }
        if (startSpot.getY() == endSpot.getY()){
            for (int i = Math.min(startSpot.getX(), endSpot.getX()) + 1; i < Math.max(startSpot.getX(), endSpot.getX()); i++){
                if (board[startSpot.getY()][i].getPiece().getType() != Type.EMPTY){
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    /*
    TODO: capture diagonally
     */
    private boolean isValidPawnMove(Spot startSpot, Spot endSpot) {
        int direction = startSpot.getPiece().getPlayer() == Player.WHITE ? -1 : 1;

        if (endSpot.getY() == startSpot.getY() + direction && endSpot.getX() == startSpot.getX() && endSpot.getPiece().getType() == Type.EMPTY){
            return true;
        }
        if (endSpot.getY() == startSpot.getY() + 2 * direction && endSpot.getX() == startSpot.getX() && startSpot.getY() == (direction == 1? 1 : 6) && endSpot.getPiece().getType() == Type.EMPTY && board[startSpot.getY() + direction][startSpot.getX()].getPiece().getType() == Type.EMPTY){
            return true;
        }
        return false;
    }
}
