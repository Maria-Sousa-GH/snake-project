package grid;

import com.codeforall.online.simplegraphics.graphics.Color;
import com.codeforall.online.simplegraphics.graphics.Rectangle;

public class foodPosition {
    private int col;
    private int row;
    private Grid grid;
    private Rectangle rectangle;

    public foodPosition(Grid grid) {
        this.grid = grid;
        createFood();
    }

    public void createFood() {
        col = (int) (Math.random() * grid.getCols());
        row = (int) (Math.random() * grid.getRows());
        if (rectangle != null) {
            rectangle.setPosition(col * grid.getCellSize(), row * grid.getCellSize());
        }
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
        rectangle.setPosition(col * grid.getCellSize(), row * grid.getCellSize());
        rectangle.setColor(Color.RED);
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }
}
