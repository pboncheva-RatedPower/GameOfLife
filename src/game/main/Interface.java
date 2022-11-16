package game.main;

import java.util.Scanner;

public class Interface {
    private BoardInitialization preferences;
    private int size;
    private int getUserInput() throws NumberFormatException {
        int userInput;
        Scanner in = new Scanner(System.in);
        userInput = Integer.parseInt(in.nextLine());
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
    private static String getName() {
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }
    private void startScreen() {
        System.out.println("Welcome to Game of Life.");
        System.out.println("Please enter the type of the board you want to initialize:");
        System.out.println("LIFE");
        System.out.println("GLIDER");
        System.out.println("RANDOM");
        System.out.println("PULSAR");
        preferences=BoardInitialization.getBoardInitializationPref(setBoardInitialization(getName().toUpperCase()));
        startGameSpace();
        startGame();
            }
    private void printGameSpace(){
        System.out.println("Please choose a game space: ");
        System.out.println("1. 8x8 ");
        System.out.println("2. 15x15");
        System.out.println("3. Enter a size (ex. for 50x50, enter 50");
    }
    private void startGameSpace(){
        printGameSpace();
        switch(getUserInput()){
            case 1:
                size=8;
                break;
            case 2:
                size=15;
                break;
            case 3:
                size = getUserInput();
                break;
            default: throw new IllegalArgumentException();
        }

    }
    private void startGame() {
        BoardState board;
        board = new BoardState(preferences, size, size);
        printGeneration(board,size,size);
        String name;

        do {
            System.out.println("Do you want to print next generation? [Y/N]");
             name = getName();
            if (name.equals("Y")) {
                board.getNextGeneration();
                printGeneration(board,size,size);
            }
            else break;
        }while(!board.getIsOutOfBoundaries());

    }
    public Interface(){
        startScreen();
    }

    public void printGeneration(BoardState board, int row, int col){
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
            System.out.println();
        }

    }


}
