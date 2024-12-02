package grid;

import mechanics.Direction;
import java.util.LinkedList;

public class snakePosition {
    private LinkedList<Position> segments;
    private Direction direction;
    private GameBoard gameBoard;

    public snakePosition(GameBoard gameBoard) {
        this.GameBoard = gameBoard;
        segments = new LinkedList<>();
        direction = Direction.UP; // Initial direction of the snake

        // Initial snake segment
        Position head = new Position(gameBoard);
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
        if (newCol < 0) newCol = gameBoard.getCols() - 1;
        if (newRow < 0) newRow = gameBoard.getRows() - 1;
        if (newCol >= gameBoard.getCols()) newCol = 0;
        if (newRow >= gameBoard.getRows()) newRow = 0;

        Position newHead = new Position(gameBoard);
        newHead.setCol(newCol);
        newHead.setRow(newRow);

        segments.addFirst(newHead);
        segments.removeLast(); // Remove the tail
    }

    public void grow() {
        Position tail = segments.getLast();
        Position newSegment = new Position(gameBoard);
        newSegment.setCol(tail.getCol());
        newSegment.setRow(tail.getRow());
        segments.addLast(newSegment); // Add a new segment to the tail
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
