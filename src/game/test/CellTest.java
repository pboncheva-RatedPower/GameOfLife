package game.test;
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
    public void aliveDiesUnderpopulation() {
        Cell cell = new Cell(CellState.ALIVE);
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
