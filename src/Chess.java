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
            System.out.println("Invalid input. Please try again: e.g. a1a2");
            input = scanner.nextLine();
        }

        return input;
    }

    private boolean validateInput(String input){
        return input.matches("[a-h][1-8][a-h][1-8]");
    }

    private void run(){
        String input = "";

        while (!isCheckmate){
            input = getInput();
            move(LETTERS.indexOf(input.charAt(0)), 8 - Character.getNumericValue(input.charAt(1)), LETTERS.indexOf(input.charAt(2)), 8 - Character.getNumericValue(input.charAt(3)));
            board.printBoard();
            switchPlayer();
        }
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