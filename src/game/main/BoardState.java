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

    private void calculateNextGeneration(){
        Map<Position, CellState> nextGeneration = new HashMap<>();
        generation = aliveCellsInGenerationWithNeighbours();
        for(Position key : generation.keySet()){
            nextGeneration.put(key, CellState.getCellState(this,key));
        }
        nextGeneration.values().removeAll(Collections.singleton(CellState.D));
        generation=nextGeneration;
    }
    private Map<Position,CellState> aliveCellsInGeneration(){
        Map<Position,CellState> aliveCells = copyFromGeneration();
        aliveCells.values().removeAll(Collections.singleton(CellState.D));
        return aliveCells;
    }
    private Map<Position,CellState> aliveCellsInGenerationWithNeighbours(){
        Map<Position,CellState> aliveAndTheirNeighbours = new HashMap<>();
        for(Map.Entry<Position, CellState> entry: aliveCellsInGeneration().entrySet()) {
            Position key = entry.getKey();
            Position[] neighbours = allNeighboursOfTheCell(key);
            for (Position neighboursKey : neighbours) {
                aliveAndTheirNeighbours.put(neighboursKey, generation.get(neighboursKey));
                aliveAndTheirNeighbours.putIfAbsent(neighboursKey, CellState.D);
            }
        }
        return aliveAndTheirNeighbours;
    }
    private Map<Position,CellState> copyFromGeneration(){
        Map<Position,CellState> copiedMap = new HashMap<>();
        for(Map.Entry<Position, CellState> entry: generation.entrySet()){
            Position position=entry.getKey();
            CellState cellState = entry.getValue();
            copiedMap.put(position,cellState);
        }
        return copiedMap;
    }
    private int numberOfAliveNeighboursOfTheCell(Position position, Map<Position,CellState> board){
        int countNeighbours =0;
        Position[] positionsOfNeighbours = allNeighboursOfTheCell(position);
        for(Position positionOfNeighbour : positionsOfNeighbours){
                if (board.getOrDefault(positionOfNeighbour,CellState.D).equals(CellState.A)) {
                    countNeighbours++;
                }
        }
        return countNeighbours;
    }
    private Position[] allNeighboursOfTheCell(Position position) {
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
        return numberOfAliveNeighboursOfTheCell(position, board);
    }
    public Map<Position,CellState> getNextGeneration(){
        if(isOutOfBoundaries()){
            System.out.println("No more generations for the defined board space.");
        }
        else{
            calculateNextGeneration();
        }

        return generation;
    }

}
