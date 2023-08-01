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
                board[i][j] = new Spot(i, j, null);
            }
        }

        board[0][0] = new Spot(0, 0, new Piece(Player.BLACK, Type.ROOK));
        board[0][1] = new Spot(0, 1, new Piece(Player.BLACK, Type.KNIGHT));
        board[0][2] = new Spot(0, 2, new Piece(Player.BLACK, Type.BISHOP));
        board[0][3] = new Spot(0, 3, new Piece(Player.BLACK, Type.QUEEN));
        board[0][4] = new Spot(0, 4, new Piece(Player.BLACK, Type.KING));
        board[0][5] = new Spot(0, 5, new Piece(Player.BLACK, Type.BISHOP));
        board[0][6] = new Spot(0, 6, new Piece(Player.BLACK, Type.KNIGHT));
        board[0][7] = new Spot(0, 7, new Piece(Player.BLACK, Type.ROOK));

        for(int i = 0; i < 8; i++){
            board[1][i] = new Spot(1, i, new Piece(Player.BLACK, Type.PAWN));
        }


        board[7][0] = new Spot(7, 0, new Piece(Player.WHITE, Type.ROOK));
        board[7][1] = new Spot(7, 1, new Piece(Player.WHITE, Type.KNIGHT));
        board[7][2] = new Spot(7, 2, new Piece(Player.WHITE, Type.BISHOP));
        board[7][3] = new Spot(7, 3, new Piece(Player.WHITE, Type.QUEEN));
        board[7][4] = new Spot(7, 4, new Piece(Player.WHITE, Type.KING));
        board[7][5] = new Spot(7, 5, new Piece(Player.WHITE, Type.BISHOP));
        board[7][6] = new Spot(7, 6, new Piece(Player.WHITE, Type.KNIGHT));
        board[7][7] = new Spot(7, 7, new Piece(Player.WHITE, Type.ROOK));

        for(int i = 0; i < 8; i++){
            board[6][i] = new Spot(6, i, new Piece(Player.WHITE, Type.PAWN));
        }
    }

    public void printBoard(){
        System.out.println();
        System.out.println(BOARD_LETTERS);

        for(int i = 0; i < 8; i++){
            System.out.print((8 - i) + " ");

            for (int j = 0; j < 8; j++){
                if (board[i][j].getPiece() == null){
                    System.out.print("- ");
                } else {
                    Piece piece = board[i][j].getPiece();
                    System.out.print(piece.getPlayer() == Player.WHITE ? piece.getType().toString().toLowerCase()  : piece.getType().toString().toUpperCase());
                    System.out.print(" ");
                }
            }

            System.out.println(8 - i);
        }

        System.out.println(BOARD_LETTERS);
        System.out.println();
    }

    public Spot getSpot(int x, int y){
        return board[y][x];
    }

    public void setSpot(int x, int y, Piece piece){
        board[y][x].setPiece(piece);
    }
}
