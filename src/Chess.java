import java.util.Scanner;

public class Chess{
    private Board board;
    private boolean isCheckmate;
    private Player currentPlayer;

    private Scanner scanner;
    private static final String LETTERS = "abcdefgh";

    public Chess(){
        init();
        run();
    }

    private void init(){
        board = new Board();
        isCheckmate = false;
        scanner = new Scanner(System.in);
        currentPlayer = Player.WHITE;
    }

    private String getInput(){
        String input = "";

        System.out.println(currentPlayer + " player, enter your move: ");
        input = scanner.nextLine();

        while (!validateInput(input)){
            System.out.println("Invalid input. Please try again: e.g. a2a3");
            input = scanner.nextLine();
        }

        return input;
    }

    //TODO: and check if the move is valid
    private boolean validateInput(String input){
        return input.matches("[a-h][1-8][a-h][1-8]");
    }

    private void run(){
        String input = "";
        int x1, y1, x2, y2;

        input = getInput();

        x1 = LETTERS.indexOf(input.charAt(0));
        y1 = 8 - Character.getNumericValue(input.charAt(1));
        x2 = LETTERS.indexOf(input.charAt(2));
        y2 = 8 - Character.getNumericValue(input.charAt(3));

        Piece currentPiece = board.getSpot(x1, y1).getPiece();

        if (currentPiece.isMoveValid(x1, 8 - y1, x2, 8 - y2) && currentPiece.getPlayer() == currentPlayer) {
            move(x1, y1, x2, y2);
            board.printBoard();
            switchPlayer();
        }
        else {
            System.out.println("Invalid move. Please try again.");
        }

        if (!isCheckmate) run();
    }

    private void move(int x1, int y1, int x2, int y2){
        board.setSpot(x2, y2, board.getSpot(x1, y1).getPiece());
        board.setSpot(x1, y1, null);
    }

    private void switchPlayer(){
        currentPlayer = currentPlayer == Player.WHITE ? Player.BLACK : Player.WHITE;
    }

    public static void main(String[] args) {
        new Chess();
    }
}