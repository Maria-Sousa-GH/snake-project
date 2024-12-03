import com.codeforall.online.simplegraphics.graphics.Color;
import com.codeforall.online.simplegraphics.graphics.Rectangle;

public class FoodPosition {
    private int cols;
    private int rows;
    private Grid grid;
    private Rectangle rectangle;

    public FoodPosition(Grid grid) {
        this.grid = grid;
        createFood();
    }

    public void createFood() {
        if (rectangle != null){
            rectangle.delete();
        }
        cols = (int) (Math.random() * grid.getCols());
        rows = (int) (Math.random() * grid.getRows());

        rectangle = new Rectangle(grid.columnToX(cols), grid.rowToY(rows), grid.getCellSize(), grid.getCellSize());
        rectangle.fill();

    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
        //  rectangle.setPosition(cols * grid.getCellSize(), rows * grid.getCellSize());
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
