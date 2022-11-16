package game.main;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class BoardState {
    private Map<Position, CellState> generation;
    private int gameSpace;
    public BoardState(BoardInitialization boardInitialization, int row, int col){
            this.generation = boardInitialization.getBoardInitialization(row, col);
            this.gameSpace=row;
    }



    private void currentGenerationWithNeighbours(){
        Map<Position, CellState> currentGenerationWithNeighbours=new HashMap<>();
        Map<Position,CellState> neighboursMap = new HashMap<>();
        generation.values().removeAll(Collections.singleton(CellState.D));
        for(Map.Entry<Position, CellState> entry: generation.entrySet()){
            Position key=entry.getKey();
            neighboursMap.clear();
            Position[] neighbours=getNeighbours(key);
            for(Position neighboursKey: neighbours){
                currentGenerationWithNeighbours.put(neighboursKey, generation.get(neighboursKey));
                currentGenerationWithNeighbours.putIfAbsent(neighboursKey, CellState.D);
            }
        }
        generation =currentGenerationWithNeighbours;
    }
    private Map<Position, CellState> calculateNextGeneration(){
        currentGenerationWithNeighbours();
        Map<Position,CellState> nextGeneration = new HashMap<>();
        for(Map.Entry<Position, CellState> entry: generation.entrySet()){
            nextGeneration.clear();
            Position key=entry.getKey();
            CellState state = entry.getValue();
            nextGeneration.put(key,state);
        }
        for(Position key : generation.keySet()){
            nextGeneration.putIfAbsent(key, CellState.getCellState(this,key));
            nextGeneration.put(key, CellState.getCellState(this,key));
        }

        nextGeneration.values().removeAll(Collections.singleton(CellState.D));
        generation = nextGeneration;
        return generation;

    }
    private int nbOfNeighbours(Position position, Map<Position,CellState> board){
        int countNeighbours =0;
        Position[] positionsOfNeighbours = getNeighbours(position);
        for(Position positionOfNeighbour : positionsOfNeighbours){
                if (board.getOrDefault(positionOfNeighbour,CellState.D).equals(CellState.A)) {
                    countNeighbours++;
                }
        }
        return countNeighbours;
    }
    private Position[] getNeighbours (Position position) {
        return new Position[]{
                new Position(position.getXCoordinate() - 1, position.getYCoordinate() - 1),
                new Position(position.getXCoordinate() - 1, position.getYCoordinate()),
                new Position(position.getXCoordinate() - 1, position.getYCoordinate() + 1),
                new Position(position.getXCoordinate(), position.getYCoordinate() - 1),
                new Position(position.getXCoordinate(), position.getYCoordinate() + 1),
                new Position(position.getXCoordinate() + 1, position.getYCoordinate() - 1),
                new Position(position.getXCoordinate() + 1, position.getYCoordinate()),
                new Position(position.getXCoordinate() + 1, position.getYCoordinate() + 1)
        };
    }
    public Map<Position,CellState> getNextGeneration(){
        if(isOutOfBoundaries()){
            System.out.println("No more generations for the defined board space.");
        }
        else{
        calculateNextGeneration();
        }

        return this.generation;
    }
    private boolean isOutOfBoundaries(){

            Position[] positions = generation.keySet().toArray(new Position[generation.size()]);
            for (Position pos : positions) {
                if (pos.getYCoordinate() == gameSpace-1 || pos.getXCoordinate() == gameSpace-1) {
                    return true;
                }
            }
            return false;
    }
    public boolean getIsOutOfBoundaries(){
        return isOutOfBoundaries();
    }
    public Map<Position, CellState> getGeneration(){
        return this.generation;
    }
    public int getNbOfNeighbours(Position position, Map<Position,CellState> board){
        return nbOfNeighbours(position, board);
    }

}
