package game.main;

public class Cell{
    private CellState state;

    public Cell(CellState state) {
        this.state=state;
    }
    private void updateState(int numberOfAliveNeigh){
        this.state = canLive(numberOfAliveNeigh) ? CellState.ALIVE : CellState.DEAD;
    }
    private boolean canLive(int numberOfAliveNeigh){
        return canReproduceIfDead(numberOfAliveNeigh) || canLiveIfAlive(numberOfAliveNeigh);
    }
    private boolean canLiveIfAlive(int numberOfAliveNeigh){
        return (!isOverpopulatedOrUnderPopulated(numberOfAliveNeigh)&& state==CellState.ALIVE);
    }
    private boolean canReproduceIfDead(int numberOfAliveNeigh){
        return (canReproduce(numberOfAliveNeigh)&& state==CellState.DEAD);
    }
    private boolean canReproduce(int numberOfAliveNeigh){
        return numberOfAliveNeigh == 3;
    }
    private boolean isOverpopulatedOrUnderPopulated(int numberOfAliveNeigh){
        return (numberOfAliveNeigh > 3)||(numberOfAliveNeigh<2);
    }
    public CellState getState() {
        return state;
    }
    public void setState(int numberOfAliveNeigh){
        updateState(numberOfAliveNeigh);
    }
}