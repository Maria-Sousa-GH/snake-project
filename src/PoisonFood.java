import com.codeforall.online.simplegraphics.pictures.Picture;

public class PoisonFood {
    private Position position;
    private Picture picture;

    public PoisonFood(Grid grid) {
        this.position = new Position(grid);
        createPoison();
    }
    // TODO --->>> Class incomplete!!!
    // TODO --->>> Poison's methods still need to be implemented!!!
    //

    public void createPoison() {
        if (position.getPicture() != null){
            position.getPicture().delete();

        }

        position.setCol((int) (Math.random() * position.getGrid().getCols()));
        position.setRow((int) (Math.random() * position.getGrid().getRows()));

        picture = new Picture(position.getGrid().columnToX(position.getCol()),position.getGrid().rowToY(position.getRow()),"resources/poison.png");
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
