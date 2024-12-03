

public class SnakeParts {

    private Position pos;
    private Direction direction;
    private boolean crashed;

    public SnakeParts(Position pos) {
        this.pos = pos;
    }

    public Position getPos() {
        return pos;
    }

    public boolean isCrashed() {
        return crashed;
    }

    public void setPos(Position pos) {
        this.pos = pos;
    }

    public void setCrashed() {
        this.crashed = true;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Direction getDirection() {
        return direction;
    }

    public void move() {

        if (!crashed) {
            getPos().move(direction);
        }

    }
}
