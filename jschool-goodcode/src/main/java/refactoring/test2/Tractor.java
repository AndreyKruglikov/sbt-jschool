package refactoring.test2;

public class Tractor {

    int[] position = new int[] { 0, 0 };
    int[] field = new int[] { 5, 5 };
    Orientation orientation;

    public Tractor(Orientation orientation) {
        this.orientation = orientation;
    }

    public void move(String command) {
        if (command == "F") {
            moveForwards(orientation);
        } else if (command == "T") {
            turnClockwise(orientation);
        }
    }

    public void moveForwards(Orientation orientation) {
        position = orientation.getNewPosition(position);
        if (position[0] > field[0] || position[1] > field[1]) {
            throw new TractorInDitchException();
        }
    }

    public void turnClockwise(Orientation orientation) {
        this.orientation = orientation.getDirectionToTurn();
    }

    public int getPositionX() {
        return position[0];
    }

    public int getPositionY() {
        return position[1];
    }

    public Orientation getOrientation() {
        return orientation;
    }

}
