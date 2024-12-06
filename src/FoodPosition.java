import com.codeforall.online.simplegraphics.graphics.Color;
import com.codeforall.online.simplegraphics.graphics.Rectangle;

public class FoodPosition {

    private Position position;

    public FoodPosition(Grid grid) {
        this.position = new Position(grid);
        createFood();
    }

    public void createFood() {
        if (position.getRectangle() != null){
            position.getRectangle().delete();
        }
        position.setCol((int) (Math.random() * position.getGrid().getCols()));
        position.setRow((int) (Math.random() * position.getGrid().getRows()));

        Rectangle rectangle = new Rectangle(position.getGrid().columnToX(position.getCol()), position.getGrid().rowToY(position.getRow()), position.getGrid().getCellSize(), position.getGrid().getCellSize());
        rectangle.fill();
        position.setRectangle(rectangle);
    }

    public int getCols() {
        return position.getCol();
    }

    public int getRows() {
        return position.getRow();
    }
}
