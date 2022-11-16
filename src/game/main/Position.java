package game.main;

public class Position {

    private int xCoordinate;
    private int yCoordinate;

    public Position(int x, int y){
        this.xCoordinate = x;
        this.yCoordinate = y;
    }
    public int getXCoordinate(){
        return this.xCoordinate;
    }
    public int getYCoordinate(){
        return this.yCoordinate;
    }

    @Override
    public int hashCode() {
        int result = xCoordinate;
        result = 31 * result + yCoordinate;
        return result;
    }

    @Override
    public boolean equals(Object other)
    {
        if (this == other)
            return true;

        if (!(other instanceof Position))
            return false;

        Position otherPoint = (Position) other;
        return otherPoint.xCoordinate == xCoordinate && otherPoint.yCoordinate == yCoordinate;
    }


}