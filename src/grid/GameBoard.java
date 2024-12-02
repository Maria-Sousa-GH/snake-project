package grid;

import com.codeforall.online.simplegraphics.graphics.Text;
import com.codeforall.online.simplegraphics.pictures.Picture;

public class GameBoard {

    private Picture picture;
    private static int pictureWidth;
    private static int pictureHeight;
    private int cellSize = 25;
    private int cols;
    private int rows;

    public GameBoard(Picture picture, int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.picture = picture;
        picture.draw();
        pictureWidth = picture.getWidth();
        pictureHeight = picture.getHeight();
    }

    public void init() {
        picture.draw();
    }

    public int getCellSize() {
        return cellSize;
    }

    public static int getPictureWidth() {
        return pictureWidth;
    }
    public static int getPictureHeight() {
        return pictureHeight;
    }
    public Picture getPicture() {
        return picture;
    }

    public int getCols() {
        return cols;
    }

    public int getRows() {
        return rows;
    }

    public int rowToY(int row) {
        return pictureWidth + cellSize * row;
    }

    public int colToX(int col) {
        return pictureWidth + cellSize * col;
    }



}