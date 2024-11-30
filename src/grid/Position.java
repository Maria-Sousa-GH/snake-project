package grid;

import com.codeforall.online.simplegraphics.graphics.Color;
import com.codeforall.online.simplegraphics.graphics.Rectangle;

public class Position {
    private int cols;
    private int rows;
    private GameBoard gameBoard;
    private Rectangle rectangle;

    public Position(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
        cols = (int) (Math.random() * gameBoard.getCols());
        rows = (int) (Math.random() * gameBoard.getRows());
    }

    public int getCols() {
        return cols;
    }

    public void setCols(int cols) {
        this.cols = cols;
    }

    public int getRows() {
        return rows;
    }

    public void setRow(int rows) {
        this.rows = rows;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setRecColor(Color color) {
        if (rectangle != null) {
            rectangle.setColor(color);
        }
    }
}
