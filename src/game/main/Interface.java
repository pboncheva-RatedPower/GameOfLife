package game.main;

import java.util.Scanner;

public class Interface {
    private BoardInitialization pattern;
    private int gameSpaceSize;
    private double probabilityOfLiveCells =0;
    private int getGameSpaceSize() throws NumberFormatException {
        int userInput;
        Scanner in = new Scanner(System.in);
        userInput = Integer.parseInt(in.nextLine());
        return userInput;
    }
    private double getProbabilityForRandom() throws NumberFormatException {
        double userInput;
        Scanner in = new Scanner(System.in);
        userInput = Double.parseDouble(in.nextLine());
        return userInput;
    }
    private static BoardInitialization setBoardInitialization(String boardType) {
        switch(BoardInitialization.valueOf(boardType)){
            case BLOCK: return BoardInitialization.BLOCK;
            case GLIDER: return BoardInitialization.GLIDER;
            case RANDOM: return BoardInitialization.RANDOM;
            case PULSAR: return BoardInitialization.PULSAR;
            default: throw new IllegalArgumentException("The collection that you entered does not exist");
        }
    }
    private static String getBoardInitialization() {
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }
    private void startScreen() {
        System.out.println("Welcome to Game of Life.");
        System.out.println("Please enter the type of the board you want to initialize:");
        System.out.println("BLOCK");
        System.out.println("GLIDER");
        System.out.println("RANDOM");
        System.out.println("PULSAR");
        pattern =BoardInitialization.getBoardInitializationPref(setBoardInitialization(getBoardInitialization().toUpperCase()));
        if(pattern.equals(BoardInitialization.RANDOM)){
            enterProbabilityForRandom();
        }
        startGameSpace();
        startGame();
            }
    private void chooseGameSpace(){
        System.out.println("Please choose a game space: ");
        System.out.println("1. 8x8 ");
        System.out.println("2. 15x15");
        System.out.println("3. Enter a size (ex. for 50x50, enter 50)");
    }
    private void enterProbabilityForRandom(){
        System.out.println("Enter probability of live cells (ex. 0.2 for 20%).");
        probabilityOfLiveCells = getProbabilityForRandom();
    }
    private void startGameSpace(){
        chooseGameSpace();
        switch(getGameSpaceSize()){
            case 1:
                gameSpaceSize =8;
                break;
            case 2:
                gameSpaceSize =15;
                break;
            case 3:
                gameSpaceSize = getGameSpaceSize();
                break;
            default: throw new IllegalArgumentException();
        }

    }
    private void startGame() {
        BoardState board = new BoardState(pattern, gameSpaceSize, gameSpaceSize, probabilityOfLiveCells);
        printGameOfLifeBoard(board, gameSpaceSize, gameSpaceSize);
        String name;

        do {
            System.out.println("Do you want to print next generation? [Y/N]");
             name = getBoardInitialization();
            if (name.equals("Y")) {
                board.getNextGeneration();
                printGameOfLifeBoard(board, gameSpaceSize, gameSpaceSize);
            }
            else break;
        }while(!board.getGeneration().isEmpty());

    }
    private void printGameOfLifeBoard(BoardState board, int row, int col){
        System.out.println("Generation number " + board.getNumberOfGenerations() + " with "
                + board.getNumberOfAliveCells() + " alive cells.");
        System.out.println();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if(board.getGeneration().containsKey(new Position(i,j))){
                    System.out.print("|");
                    System.out.print(board.getGeneration().get(new Position(i,j)));
                }
                else{
                    System.out.print("|");
                    System.out.print(" ");
                }
            }
            System.out.print("|");
            System.out.println();
        }
        System.out.println();
    }
    public Interface(){
        startScreen();
    }
}
