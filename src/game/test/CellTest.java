package game.test;
import game.main.Cell;
import game.main.CellState;
import org.junit.Assert;
import org.junit.Test;

public class CellTest {

    @Test
    public void stateAliveIfAlive() {
        Cell cell = new Cell(CellState.ALIVE);
        Assert.assertEquals(CellState.ALIVE, cell.getState());
    }

    @Test
    public void stateDeadIfDead() {
        Cell cell = new Cell(CellState.DEAD);
        Assert.assertEquals(CellState.DEAD, cell.getState());
    }

    @Test
    public void LiveDiesUnderpopulation() {
        Cell cell = new Cell(CellState.ALIVE);
        int numberOfAliveNeighbours = 1;
        cell.setState(numberOfAliveNeighbours);
        Assert.assertEquals(CellState.DEAD,cell.getState());
    }

    @Test
    public void LiveSurvivesWithTwo() {
        Cell cell = new Cell(CellState.ALIVE);
        int numberOfAliveNeighbours = 2;
        cell.setState(numberOfAliveNeighbours);
        Assert.assertEquals(CellState.ALIVE, cell.getState());
    }

    @Test
    public void LiveSurvivesWithThree() {
        Cell cell = new Cell(CellState.ALIVE);
        int umberOfAliveNeighbours = 3;
        cell.setState(umberOfAliveNeighbours);
        Assert.assertEquals(CellState.ALIVE, cell.getState());
    }

    @Test
    public void LiveDiesOverPopulation() {
        Cell cell = new Cell(CellState.ALIVE);
        int numberOfAliveNeighbours = 4;
        cell.setState(numberOfAliveNeighbours);
        Assert.assertEquals(CellState.DEAD, cell.getState());
    }

    @Test
    public void DeadReproducesWithThree() {
        Cell cell = new Cell(CellState.DEAD);
        int numberOfAliveNeighbours = 3;
        cell.setState(numberOfAliveNeighbours);
        Assert.assertEquals(CellState.ALIVE, cell.getState());

    }

    @Test
    public void DeadStillDeadWithMoreThanThree() {
        Cell cell = new Cell(CellState.DEAD);
        int numberOfAliveNeighbours = 4;
        cell.setState(numberOfAliveNeighbours);
        Assert.assertEquals(CellState.DEAD, cell.getState());
    }

    @Test
    public void DeadStillDeadWithLessThanThree() {
        Cell cell = new Cell(CellState.DEAD);
        int numberOfAliveNeighbours = 2;
        cell.setState(numberOfAliveNeighbours);
        Assert.assertEquals(CellState.DEAD, cell.getState());
    }


}
