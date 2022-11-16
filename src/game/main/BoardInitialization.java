package game.main;

import java.util.HashMap;
import java.util.Map;

public enum BoardInitialization {
    BLOCK {
        @Override
        public Map<Position, CellState> getBoardInitialization(int row, int col) {
            Map<Position, CellState> lifeMap = new HashMap<>();
            lifeMap.put(new Position(2, 2), CellState.A);
            lifeMap.put(new Position(2, 3), CellState.A);
            lifeMap.put(new Position(3, 2), CellState.A);
            lifeMap.put(new Position(3, 3), CellState.A);

            return lifeMap;
        }
    },
    PULSAR {
        @Override
        public Map<Position, CellState> getBoardInitialization(int row, int col) {
            if (checkSize(row,col)) {
                System.out.println("Size should be higher than 8.");
                return null;
            } else {
                int mapSize = row * col;
                Map<Position, CellState> lifeMap = new HashMap<>(mapSize);
                lifeMap.put(new Position(0, 0), CellState.D);
                lifeMap.put(new Position(0, 1), CellState.D);
                lifeMap.put(new Position(1, 0), CellState.D);
                lifeMap.put(new Position(1, 1), CellState.D);
                return lifeMap;
            }
        }
    },
    GLIDER {
        @Override
        public Map<Position, CellState> getBoardInitialization(int row, int col) {

                int mapSize=row*col;
                Map<Position, CellState> lifeMap = new HashMap<>(mapSize);
                lifeMap.put(new Position(2, 2), CellState.A);
                lifeMap.put(new Position(3, 3), CellState.A);
                lifeMap.put(new Position(4, 1), CellState.A);
                lifeMap.put(new Position(4, 2), CellState.A);
                lifeMap.put(new Position(4, 3), CellState.A);
                return lifeMap;
        }
    },
    RANDOM {
        @Override
        public Map<Position, CellState> getBoardInitialization(int row, int col) {
            Map<Position, CellState> lifeMap = new HashMap<>();
            lifeMap.put(new Position(3, 3), CellState.A);

            return lifeMap;
        }
    };

    public abstract Map<Position, CellState> getBoardInitialization(int row, int col);
    private static final int MIN_SIZE=8;
    private static final int MAX_SIZE=16;

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

    private static boolean checkSize(int row, int col) {

        return row < MIN_SIZE || row>MAX_SIZE;
    }
}
