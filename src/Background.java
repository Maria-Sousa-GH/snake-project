import com.codeforall.online.simplegraphics.pictures.Picture;

public class Background {

    private Picture background;
    private static int backgroundWidth;
    private static int backgroundHeight;

    public Background() {
        background = new Picture(0,0,"resources/snakeback2.jpg");
        background.draw();
        backgroundWidth = background.getMaxX();
        backgroundHeight = background.getMaxY();
    }

    public static int getWidth() {
        return backgroundWidth;
    }
    public static int getHeight() {
        return backgroundHeight;
    }

    public Picture getBackground() {
        return background;
    }
    public int getBackgroundWidth() {
        return backgroundWidth;
    }
    public int getBackgroundHeight() {
        return backgroundHeight;
    }

}
