package game.main;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class BoardState {
    private Map<Position, CellState> generation;
    private final int gameSpaceSize;
    private int numberOfGenerations;

    public BoardState(BoardInitialization boardInitialization, int row, int col, double probability){
            this.generation = boardInitialization.getBoardInitialization(row, col, probability);
            this.gameSpaceSize =row;
    }

    private Map<Position, CellState> calculateNextGeneration(){
        Map<Position, CellState> nextGeneration = new HashMap<>();
        generation = aliveCellsInGenerationWithNeighbours();
        modifiedPut(nextGeneration);
        nextGeneration.values().removeAll(Collections.singleton(CellState.D));
        numberOfGenerations++;
        generation=nextGeneration;
        return generation;
        }

    private Map<Position, CellState> modifiedPut(Map<Position,CellState> nextGeneration) {
        for (Position key : generation.keySet()) {
            Map<String, Boolean> isOutOfBoundaries = isOutOfBoundaries(key);
            if (isOutOfBoundaries.containsValue(true)) {
                nextGeneration.put(key, CellState.D);
            }
            else {
                nextGeneration.put(key, CellState.getCellState(this, key));
            }
        }
        return nextGeneration;
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
            Map<String, Position[]> allNeighbours = allNeighboursOfTheCell(key);
            for (Map.Entry<String, Position[]> neighbours: allNeighbours.entrySet()){
                Position[] neighboursKey = neighbours.getValue();
                for (Position pos : neighboursKey) {
                    aliveAndTheirNeighbours.put(pos, generation.get(pos));
                    aliveAndTheirNeighbours.putIfAbsent(pos, CellState.D);
                }
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
        Map<String,Position[]> allNeighbours = allNeighboursOfTheCell(position);
        for(Map.Entry<String, Position[]> positionOfNeighbours: allNeighbours.entrySet()){
            Position[] positionOfNeighbour = positionOfNeighbours.getValue();
            for(Position key : positionOfNeighbour) {
                if (board.getOrDefault(key, CellState.D).equals(CellState.A)) {
                     countNeighbours++;
                }
            }
       }
        return countNeighbours;
    }
    private Map<String, Boolean> isOutOfBoundaries(Position position){
        Map<String, Boolean> outOfBoundaries = new HashMap<>();
        outOfBoundaries.put("topBoundary", false);
        outOfBoundaries.put("bottomBoundary", false);
        outOfBoundaries.put("leftBoundary", false);
        outOfBoundaries.put("rightBoundary", false);

        if(!allNeighboursOfTheCell(position).containsKey("top")){
            outOfBoundaries.replace("topBoundary", true);
        }
        if(!allNeighboursOfTheCell(position).containsKey("bottom")){
            outOfBoundaries.replace("bottomBoundary", true);
        }
        if(!allNeighboursOfTheCell(position).containsKey("right")){
            outOfBoundaries.replace("rightBoundary", true);
        }
        if(!allNeighboursOfTheCell(position).containsKey("left")){
            outOfBoundaries.replace("leftBoundary", true);
        }

        return outOfBoundaries;
    }
    private Position[] topNeighboursOfTheCell(Position position){
       return new Position[]{
                new Position(position.getXCoordinate() - 1, position.getYCoordinate() - 1),
                new Position(position.getXCoordinate() - 1, position.getYCoordinate()),
                new Position(position.getXCoordinate() - 1, position.getYCoordinate() + 1),
        };

    }
    private Position[] bottomNeighboursOfTheCell(Position position){
       return new Position[]{
                new Position(position.getXCoordinate() + 1, position.getYCoordinate() - 1),
                new Position(position.getXCoordinate() + 1, position.getYCoordinate()),
                new Position(position.getXCoordinate() + 1, position.getYCoordinate() + 1)};
    }
    private  Position[] leftNeighboursOfTheCell(Position position){
       return new Position[]{
               new Position(position.getXCoordinate(), position.getYCoordinate() - 1),
       };
    }
    private  Position[] rightNeighboursOfTheCell(Position position){
        return new Position[]{
                new Position(position.getXCoordinate(), position.getYCoordinate() + 1)
        };
    }
    private Map<String, Position[]> allNeighboursOfTheCell(Position position) {
        Map<String, Position[]> allNeighbours = new HashMap<>();
        allNeighbours.put("top", topNeighboursOfTheCell(position));
        allNeighbours.put("bottom", bottomNeighboursOfTheCell(position));
        allNeighbours.put("left", leftNeighboursOfTheCell(position));
        allNeighbours.put("right", rightNeighboursOfTheCell(position));
        
        if (position.getXCoordinate() == 0) {
            allNeighbours.remove("top");
        }
        if (position.getXCoordinate() == gameSpaceSize) {
            allNeighbours.remove("bottom");
        }
        if (position.getYCoordinate() == 0) {
            allNeighbours.remove("left");
        }
        if (position.getYCoordinate() == gameSpaceSize) {
            allNeighbours.remove("right");
        }
        return allNeighbours;
    }
    public Map<Position, CellState> getGeneration(){
        return this.generation;
    }
    public int getNbOfNeighbours(Position position, Map<Position,CellState> board){
        return numberOfAliveNeighboursOfTheCell(position, board);
    }
    public Map<Position,CellState> getNextGeneration(){
        return calculateNextGeneration();
    }
    public int getNumberOfGenerations(){
        return numberOfGenerations;
    }
    public int getNumberOfAliveCells(){
        int numberOfAliveCells=0;
        for(Map.Entry<Position,CellState> entry: generation.entrySet()){
            if(entry.getValue().equals(CellState.A)){
                numberOfAliveCells++;
            }
        }
        return numberOfAliveCells;
    }


}


