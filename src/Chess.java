import java.util.Scanner;

public class Chess{
    private Board board;
    private boolean isCheckmate;
    private Player currentPlayer;

    private Scanner scanner;

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

    private void run(){
        String input = "";

        while (!isCheckmate){
            input = scanner.nextLine();

        }
    }

    public static void main(String[] args) {
        new Chess();
    }
}