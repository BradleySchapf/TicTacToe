import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class TicTacToe {

    static ArrayList<Integer> playerPositions = new ArrayList<Integer>();
    static ArrayList<Integer> cpuPositions = new ArrayList<Integer>();

    public static void main(String[] args) {
        
        char[][] gameBoard = {  {' ', '|', ' ', '|', ' ',}, 
                                {'-', '+', '-', '+', '-',},
                                {' ', '|', ' ', '|', ' ',},
                                {'-', '+', '-', '+', '-',},
                                {' ', '|', ' ', '|', ' ',},
        };

        printGameBoard(gameBoard);

        Scanner scan = new Scanner(System.in);
        while (true) {
            String win = checkWinner();
            if (win.length() > 0) {
                System.out.println(win);
                break;
            }

            System.out.println("Enter your placement (1-9): ");
            int playerPos = scan.nextInt();
            while (playerPositions.contains(playerPos) || cpuPositions.contains(playerPos)) {
                System.out.println("Position taken! try again: ");
                playerPos = scan.nextInt();
            }
            placePiece(playerPos, gameBoard, "player");

            win = checkWinner();
            if (win.length() > 0) {
                System.out.println(win);
                break;
            }
            
            Random rand = new Random();
            int cpuPos = rand.nextInt(9) + 1;
                while (playerPositions.contains(cpuPos) || cpuPositions.contains(cpuPos)) {
                    System.out.println("Position taken! try again: ");
                    cpuPos = rand.nextInt(9) + 1;
            }
            placePiece(cpuPos, gameBoard, "cpu");

            printGameBoard(gameBoard);
        }
    }

    //print the gameboard to the console
    public static void printGameBoard(char[][] gameBoard) {
        for(char[] row : gameBoard) {
            for(char c : row) {
                System.out.print(c);
            }
            System.out.println();
        }
    }

    //place a piece on the board
    public static void placePiece(int pos, char[][] gameBoard, String user) {
        
        char symbol;

        if (user == "player") {
            symbol = 'X';
            playerPositions.add(pos);
        }
        else {
            symbol = '0';
            cpuPositions.add(pos);
        }
        
        switch(pos) {
            case 1: 
                gameBoard[0][0] = symbol;
                break;
            case 2: 
                gameBoard[0][2] = symbol;
                break;
            case 3: 
                gameBoard[0][4] = symbol;
                break;
            case 4: 
                gameBoard[2][0] = symbol;
                break;
            case 5: 
                gameBoard[2][2] = symbol;
                break;
            case 6: 
                gameBoard[2][4] = symbol;
                break;
            case 7: 
                gameBoard[4][0] = symbol;
                break;
            case 8: 
                gameBoard[4][2] = symbol;
                break;
            case 9:
                gameBoard[4][4] = symbol;
                break;
            default:
                break;
        }
    }

    public static String checkWinner() {
        
        List topRow = Arrays.asList(1, 2, 3);
        List middleRow = Arrays.asList(4, 5, 6);
        List bottomRow = Arrays.asList(7, 8, 9);
        List leftCol = Arrays.asList(1, 4, 7);
        List middleCol = Arrays.asList(2, 5, 8);
        List rightCol = Arrays.asList(3, 6, 9);
        List diag1 = Arrays.asList(1, 5, 9);
        List diag2 = Arrays.asList(3, 5, 7);

        List<List> winning = new ArrayList<List>();
        winning.add(topRow);
        winning.add(middleRow);
        winning.add(bottomRow);
        winning.add(leftCol);
        winning.add(middleCol);
        winning.add(rightCol);
        winning.add(diag1);
        winning.add(diag2);

        for(List l: winning) {
            if(playerPositions.containsAll(l)) {
                return "Congratulations you won!";
            } else if(cpuPositions.containsAll(l)) {
                return "CPU wins :(";
            } 
        if(playerPositions.size() + cpuPositions.size() == 9) {
            return "Tie!";
            }   
        }
        return "";
    }
}
