package grid;

import com.codeforall.online.simplegraphics.graphics.Color;
import com.codeforall.online.simplegraphics.graphics.Rectangle;

public class Position {
    private int col;
    private int row;
    private GameBoard gameboard;
    private Rectangle rectangle;

    public Position(GameBoard gameBoard) {
        this.gameboard = gameBoard;
        col = (int) (Math.random() * gameboard.getCols());
        row = (int) (Math.random() * gameboard.getRows());
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
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
