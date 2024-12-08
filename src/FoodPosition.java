import com.codeforall.online.simplegraphics.pictures.Picture;

public class FoodPosition {

    private Position position;
    private Picture picture;

    public FoodPosition(Grid grid) {
        this.position = new Position(grid);
        createFood();
    }

    public void createFood() {
        if (position.getPicture() != null){
            position.getPicture().delete();

        }

        position.setCol((int) (Math.random() * position.getGrid().getCols()));
        position.setRow((int) (Math.random() * position.getGrid().getRows()));

        picture = new Picture(position.getGrid().columnToX(position.getCol()),position.getGrid().rowToY(position.getRow()),"resources/applebig.png");
        picture.draw();
        position.setPicture(picture);

    }

    public int getCols() {
        return position.getCol();
    }

    public int getRows() {
        return position.getRow();
    }
}
