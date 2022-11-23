package game.main;


import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public enum BoardInitialization {
    BLOCK {
        @Override
        public Map<Position, CellState> getBoardInitialization(int row, int col, double probability) {
            Map<Position, CellState> lifeMap = new HashMap<>();
            lifeMap.put(new Position(1, 1), CellState.A);
            lifeMap.put(new Position(1, 2), CellState.A);
            lifeMap.put(new Position(2, 2), CellState.A);
            lifeMap.put(new Position(2, 1), CellState.A);

            return lifeMap;
        }
    },
    PULSAR {
        @Override
        public Map<Position, CellState> getBoardInitialization(int row, int col, double probability) {
            Map<Position, CellState> lifeMap = new HashMap<>();
            lifeMap.put(new Position(1, 1), CellState.A);
            lifeMap.put(new Position(1, 2), CellState.A);
            lifeMap.put(new Position(2, 1), CellState.A);
            lifeMap.put(new Position(3, 4), CellState.A);
            lifeMap.put(new Position(4, 3), CellState.A);
            lifeMap.put(new Position(4, 4), CellState.A);
            return lifeMap;

        }
    },
    GLIDER {
        @Override
        public Map<Position, CellState> getBoardInitialization(int row, int col, double probability) {

            int mapSize = row * col;
            Map<Position, CellState> lifeMap = new HashMap<>(mapSize);
            lifeMap.put(new Position(0, 2), CellState.A);
            lifeMap.put(new Position(1, 3), CellState.A);
            lifeMap.put(new Position(2, 1), CellState.A);
            lifeMap.put(new Position(2, 2), CellState.A);
            lifeMap.put(new Position(2, 3), CellState.A);
            return lifeMap;
        }
    },
    RANDOM {
        @Override
        public Map<Position, CellState> getBoardInitialization(int row, int col, double probability) {
            Random random = new Random();
            Map<Position, CellState> lifeMap = new HashMap<>();
            int numberOfAliveCells = (int) Math.floor(probability*row*col);

            for(int index=0; index<numberOfAliveCells; index++){
                lifeMap.put(new Position(random.nextInt(row-1), random.nextInt(col-1)), CellState.A);
            }



            return lifeMap;
        }
    };


    public abstract Map<Position, CellState> getBoardInitialization(int row, int col, double probability);

    public static BoardInitialization getBoardInitializationPref(BoardInitialization initialization) {
        switch (initialization) {
            case BLOCK:
                return BoardInitialization.BLOCK;
            case PULSAR:
                return BoardInitialization.PULSAR;
            case GLIDER:
                return BoardInitialization.GLIDER;
            case RANDOM:
                return BoardInitialization.RANDOM;
            default:
                throw new IllegalArgumentException();
        }
    }


}
