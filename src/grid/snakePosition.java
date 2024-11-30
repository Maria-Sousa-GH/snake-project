package grid;

import mechanics.Direction;
import java.util.LinkedList;

public class snakePosition {
    private LinkedList<Position> segments;
    private Direction direction;
    private GameBoard gamebBoard;

    public snakePosition(GameBoard gamebBoard) {
        this.grid = gamebBoard;
        segments = new LinkedList<>();
        direction = Direction.UP; // Initial direction of the snake

        // Initial snake segment
        Position head = new Position(grid);
        segments.add(head);
    }

    public LinkedList<Position> getSegments() {
        return segments;
    }

    public void move() {
        Position head = segments.getFirst();
        int newCol = head.getCol();
        int newRow = head.getRow();

        switch (direction) {
            case UP -> newRow--;
            case DOWN -> newRow++;
            case LEFT -> newCol--;
            case RIGHT -> newCol++;
        }

        // Wrap around if the snake goes off the grid
        if (newCol < 0) newCol = gamebBoard.getCols() - 1;
        if (newRow < 0) newRow = gamebBoard.getRows() - 1;
        if (newCol >= gamebBoard.getCols()) newCol = 0;
        if (newRow >= gamebBoard.getRows()) newRow = 0;

        Position newHead = new Position(grid);
        newHead.setCol(newCol);
        newHead.setRow(newRow);

        segments.addFirst(newHead);
        segments.removeLast(); // Remove the tail
    }

    public void grow() {
        Position tail = segments.getLast();
        Position newSegment = new Position(grid);
        newSegment.setCol(tail.getCol());
        newSegment.setRow(tail.getRow());
        segments.addLast(newSegment); // Add a new segment to the tail
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
