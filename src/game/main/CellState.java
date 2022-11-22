package game.main;

public enum CellState {
    A, D;
    public static CellState getCellState(BoardState board, Position position){
        return canLive(board,position) ? A : D;
    }
    private static boolean isAlive(BoardState board, Position position){
        return board.getGeneration().get(position).equals(CellState.A);
    }
    private static boolean canLive(BoardState board, Position position) {
        return (!isAlive(board, position) && canReproduce(board,position))
                || (isAlive(board, position) && !isOverpopulatedOrUnderPopulated(board,position));
    }
    private static boolean canReproduce(BoardState board, Position position){
        return board.getNbOfNeighbours(position,board.getGeneration())==3;
    }
    private static boolean isOverpopulatedOrUnderPopulated(BoardState board, Position position){
        return (board.getNbOfNeighbours(position,board.getGeneration()) > 3)||(board.getNbOfNeighbours(position,board.getGeneration())<2);
    }

    private static boolean isValidPosition(BoardState board, Position position, int row, int col){
        if(position.getXCoordinate()<0){
            return false;
        }
        if(position.getYCoordinate()<0){
            return false;
        }
        if(position.getXCoordinate()>col){
            return false;
        }
        if(position.getYCoordinate()>row){
            return false;
        }
        return true;
    }
}
