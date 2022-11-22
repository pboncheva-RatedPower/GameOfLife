package game.test;
import game.main.BoardInitialization;
import game.main.BoardState;
import game.main.CellState;
import game.main.Position;
import org.junit.Assert;
import org.junit.Test;

public class CellTest {

    BoardState board = new BoardState(BoardInitialization.GLIDER, 8,8,0);


    @Test
    public void aliveDiesUnderpopulation() {
        BoardState board = new BoardState(BoardInitialization.TESTING, 6,6, 0);
        int numberOfAliveNeighbours = 1;
        cell.setState(numberOfAliveNeighbours);
        Assert.assertEquals(CellState.DEAD,cell.getState());
    }

    @Test
    public void aliveSurvivesWithTwo() {
        Cell cell = new Cell(CellState.ALIVE);
        int numberOfAliveNeighbours = 2;
        cell.setState(numberOfAliveNeighbours);
        Assert.assertEquals(CellState.ALIVE, cell.getState());
    }

    @Test
    public void aliveSurvivesWithThree() {
        Cell cell = new Cell(CellState.ALIVE);
        int umberOfAliveNeighbours = 3;
        cell.setState(umberOfAliveNeighbours);
        Assert.assertEquals(CellState.ALIVE, cell.getState());
    }

    @Test
    public void aliveDiesOverPopulation() {
        Cell cell = new Cell(CellState.ALIVE);
        int numberOfAliveNeighbours = 4;
        cell.setState(numberOfAliveNeighbours);
        Assert.assertEquals(CellState.DEAD, cell.getState());
    }

    @Test
    public void deadReproducesWithThree() {
        Cell cell = new Cell(CellState.DEAD);
        int numberOfAliveNeighbours = 3;
        cell.setState(numberOfAliveNeighbours);
        Assert.assertEquals(CellState.ALIVE, cell.getState());

    }

    @Test
    public void deadStillDeadWithMoreThanThree() {
        Cell cell = new Cell(CellState.DEAD);
        int numberOfAliveNeighbours = 4;
        cell.setState(numberOfAliveNeighbours);
        Assert.assertEquals(CellState.DEAD, cell.getState());
    }

    @Test
    public void deadStillDeadWithLessThanThree() {
        Cell cell = new Cell(CellState.DEAD);
        int numberOfAliveNeighbours = 2;
        cell.setState(numberOfAliveNeighbours);
        Assert.assertEquals(CellState.DEAD, cell.getState());
    }


}
