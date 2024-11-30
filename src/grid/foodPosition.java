package grid;

import com.codeforall.online.simplegraphics.graphics.Color;
import com.codeforall.online.simplegraphics.graphics.Rectangle;

public class foodPosition {
    private int cols;
    private int rows;
    private GameBoard gameBoard;
    private Rectangle rectangle;

    public foodPosition(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
        createFood();
    }

    public void createFood() {
        cols = (int) (Math.random() * gameBoard.getCols());
        rows = (int) (Math.random() * gameBoard.getRows());
        if (rectangle != null) {
            rectangle.setPosition(cols * gameBoard.getCellSize(), rows * gameBoard.getCellSize());
        }
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
        rectangle.setPosition(cols * gameBoard.getCellSize(), rows * gameBoard.getCellSize());
        rectangle.setColor(Color.RED);
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public int getCols() {
        return cols;
    }

    public int getRows() {
        return rows;
    }
}
