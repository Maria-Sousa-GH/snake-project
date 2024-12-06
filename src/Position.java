import com.codeforall.online.simplegraphics.graphics.Color;
import com.codeforall.online.simplegraphics.graphics.Rectangle;

public class     Position {
    private int col;
    private int row;
    private Grid grid;
    private Rectangle rectangle;

    public Position(Grid grid) {
        this.grid = grid;
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

    public void move(Direction direction) {

        switch (direction) {

            case UP:
                rectangle.translate(0, grid.getCellSize() * -1);
                row--;
                break;
            case DOWN:
                rectangle.translate(0, grid.getCellSize());
                row++;
                break;
            case LEFT:
                rectangle.translate(grid.getCellSize() * -1, 0);
                col--;
                break;
            case RIGHT:
                rectangle.translate(grid.getCellSize(), 0);
                col++;
                break;
            default:
                rectangle.translate(0, 0);
                break;
        }
    }
}