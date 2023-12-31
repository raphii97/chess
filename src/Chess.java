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

    private boolean validateInput(String input){
        return input.matches("[a-h][1-8][a-h][1-8]");
    }

    private void run(){
        String playerInput = getInput();

        Spot startSpot = board.getSpot(LETTERS.indexOf(playerInput.charAt(0)), 8 - Character.getNumericValue(playerInput.charAt(1)));
        Spot endSpot = board.getSpot(LETTERS.indexOf(playerInput.charAt(2)), 8 - Character.getNumericValue(playerInput.charAt(3)));

        if (board.isValidMove(startSpot, endSpot, currentPlayer)){
            move(startSpot, endSpot);
            board.printBoard();
            switchPlayer();
        }
        else {
            System.out.println("Invalid move. Please try again.");
        }

        if (!isCheckmate) run();
    }

    private void move(Spot startSpot, Spot endSpot){
        endSpot.setPiece(startSpot.getPiece());
        startSpot.removePiece();
    }

    private void switchPlayer(){
        currentPlayer = currentPlayer == Player.WHITE ? Player.BLACK : Player.WHITE;
    }

    public static void main(String[] args) {
        new Chess();
    }
}